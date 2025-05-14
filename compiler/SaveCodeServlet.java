/*import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/saveCode")
public class SaveCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");

        FileWriter writer = new FileWriter("/home/ayyappankalai/Server/apache-tomcat-10.1.34/webapps/webSocketTerminal/logs/Main.java"); // adjust the path
        writer.write(code);
        writer.close();

        response.getWriter().write("Saved Main.java successfully.");
        
        try {
    // Compile the Java file
    Process compileProcess = new ProcessBuilder("javac", "Main.java")
        .directory(new File("/home/ayyappankalai/Server/apache-tomcat-10.1.34/webapps/webSocketTerminal/logs"))  // set this to where Main.java is saved
        .redirectErrorStream(true)
        .start();

    // Read compile output
    BufferedReader compileReader = new BufferedReader(
        new InputStreamReader(compileProcess.getInputStream()));
    StringBuilder compileOutput = new StringBuilder();
    String line;
    while ((line = compileReader.readLine()) != null) {
        compileOutput.append(line).append("\n");
    }

    compileProcess.waitFor(); // wait for compilation to finish

    if (compileProcess.exitValue() != 0) {
        response.getWriter().write("Compilation failed:\n" + compileOutput.toString());
        return;
    }
    response.sendRedirect(request.getContextPath() + "/index.html");


    // Run the compiled class
    Process runProcess = new ProcessBuilder("java", "Main")
        .directory(new File("/home/ayyappankalai/Server/apache-tomcat-10.1.34/webapps/webSocketTerminal/logs"))
        .redirectErrorStream(true)
        .start();

    BufferedReader runReader = new BufferedReader(
        new InputStreamReader(runProcess.getInputStream()));
    StringBuilder runOutput = new StringBuilder();
    while ((line = runReader.readLine()) != null) {
        runOutput.append(line).append("\n");
    }

    runProcess.waitFor();
    response.getWriter().write("Output:\n" + runOutput.toString());

} catch (Exception e) {
    e.printStackTrace();
    response.getWriter().write("Error occurred: " + e.getMessage());
}

    }
    
}
*/
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/saveCode")
public class SaveCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");

        FileWriter writer = new FileWriter("/home/ayyappankalai/Server/apache-tomcat-10.1.34/webapps/webSocketTerminal/logs/Main.java");
        writer.write(code);
        writer.close();

        try {
            Process compileProcess = new ProcessBuilder("javac", "Main.java")
                .directory(new File("/home/ayyappankalai/Server/apache-tomcat-10.1.34/webapps/webSocketTerminal/logs"))
                .redirectErrorStream(true)
                .start();

            BufferedReader compileReader = new BufferedReader(
                new InputStreamReader(compileProcess.getInputStream()));
            StringBuilder compileOutput = new StringBuilder();
            String line;
            while ((line = compileReader.readLine()) != null) {
                compileOutput.append(line).append("\n");
            }

            compileProcess.waitFor();

            if (compileProcess.exitValue() != 0) {
                response.setContentType("text/plain");
                response.getWriter().write("Compilation failed:\n" + compileOutput.toString());
                return;
            }

            
            response.getWriter().write("Compiled successfully");
            
//ProcessBuilder builder = new ProcessBuilder("/home/ayyappankalai/Server/apache-tomcat-10.1.34/webapps/webSocketTerminal/open_index.sh");
//builder.start();


        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/plain");
            response.getWriter().write("Error occurred: " + e.getMessage());
        }
    }
}

