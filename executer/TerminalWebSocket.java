import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/terminal")
public class TerminalWebSocket {

    private Process process;
    private BufferedReader reader;
    private BufferedWriter writer;

    @OnOpen
public void onOpen(Session session) {
    try {
        ProcessBuilder builder = new ProcessBuilder(
            "java", "-cp", "/home/ayyappankalai/Server/apache-tomcat-10.1.34/webapps/webSocketTerminal/logs", "Main"
        );
        builder.redirectErrorStream(true);
        process = builder.start();

        reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));

        // Thread to continuously send output to client
        new Thread(() -> {
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    session.getBasicRemote().sendText(line + "\n");
                }

                // Process is finished, now delete files
                File javaFile = new File("/home/ayyappankalai/Server/apache-tomcat-10.1.34/webapps/webSocketTerminal/logs/Main.java");
                File classFile = new File("/home/ayyappankalai/Server/apache-tomcat-10.1.34/webapps/webSocketTerminal/logs/Main.class");

                if (javaFile.exists()) javaFile.delete();
                if (classFile.exists()) classFile.delete();
                
                // Execute delete script
                ProcessBuilder deleteScriptBuilder = new ProcessBuilder("/home/ayyappankalai/Server/apache-tomcat-10.1.34/webapps/webSocketTerminal/executer/deleteFiles.sh");
                Process deleteProcess = deleteScriptBuilder.start();
                deleteProcess.waitFor();  // Wait for the script to finish
                if (deleteProcess.exitValue() != 0) {
                    System.out.println("Error: The script didn't execute successfully.");
                }

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    } catch (IOException e) {
        e.printStackTrace();
    }
}


    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            writer.write(message + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) {
        try {
            if (process != null) process.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


