#  PlayCodeIDE - Online Compiler with Terminal Execution

PlayCodeIDE is a lightweight online compiler that allows users to write, compile, and run Java (or other language) programs directly in the browser with real-time terminal interaction. This project combines a frontend code editor with backend Java servlets and WebSocket-based terminal I/O.

---

## 📁 Folder Structure

### 🔹 Folder 1: `PlayCodeIDE/` — Code Editor Interface

|    File                |                 Description                              |
|------------------------|----------------------------------------------------------|
| `main.html`            | The main interface to write and submit code.             |
| `script.js`            | Sends code to the backend servlet and handles navigation.|
| `styles.css`           | Provides styling for the editor and output display.      |
| `SaveCodeServlet.java` | Servlet that saves and compiles user-submitted code.     |

---

### 🔹 Folder 2: `executer/` — Terminal & Runtime

|    File                  |                   Description                                     |
|--------------------------|-------------------------------------------------------------------|
| `index.html`             | Terminal-style interface to display output and accept user input. |
| `TerminalWebSocket.java` | Java WebSocket endpoint to run compiled code and stream output.   |
| `deleteFiles.sh`         | Cleanup script to delete compiled files after execution.          |

---

## 🚀 Features

- ✍️ Online code editor with syntax-friendly interface.
- 🛠️ Backend compilation using Java Servlet (`javac`).
- 🖧 Real-time execution using WebSockets.
- 📤 Interactive terminal: send input and receive output live.
- 🧹 Auto-cleans files after execution using shell script.

---

## ⚙️ Technologies Used

- **Frontend**: HTML, CSS, JavaScript
- **Backend**: Java Servlets, Jakarta WebSockets
- **Server**: Apache Tomcat
- **Others**: Shell Scripting for cleanup

---

## 🧑‍💻 How It Works

1. User writes Java code in the browser (`main.html`).
2. Code is posted to the server via `script.js`.
3. `SaveCodeServlet.java` saves and compiles the code.
4. On success, the browser redirects to `executer/index.html`.
5. `TerminalWebSocket.java` launches the compiled program and streams output.
6. After execution ends, `deleteFiles.sh` removes temporary files.

---

## 📦 Deployment

> Ensure Tomcat is installed and deployed with servlet + WebSocket support.

1. Place `PlayCodeIDE` and `executer` folders inside `webapps/webSocketTerminal/`.
2. Update file paths in Java files if needed (for logs and scripts).
3. Compile `.java` files and deploy classes inside `WEB-INF/classes/`.
4. Start Tomcat and navigate to `/webSocketTerminal/main.html`.

---

## 📝 Notes

- Only Java is currently supported. C, Python options are in UI but not yet implemented.
- Ensure correct file permissions for shell scripts and folders.
- Code must have class named `Main` with `public static void main()` method.

---

## 📸 Screenshot

> *(Insert a screenshot of main.html editor and terminal interface here)*

---



## 🙌 Credits

Developed by KarthickRaja.k 
karthickraja182356@gmail.com

