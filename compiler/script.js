/*document.getElementById('runCode').addEventListener('click', () => {
    const code = document.getElementById('codeEditor').value;

    fetch('/webSocketTerminal/saveCode', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: 'code=' + encodeURIComponent(code)
    })
    .then(response => response.text())
    .then(data => {
        document.getElementById('output').innerText = data;
    });
});
*/

document.getElementById('runCode').addEventListener('click', () => {
    const code = document.getElementById('codeEditor').value;

    fetch('/webSocketTerminal/saveCode', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: 'code=' + encodeURIComponent(code)
    })
    .then(response => response.text())
    .then(data => {
        if (data.trim().includes("Compiled successfully")) {
            setTimeout(() => {
                window.location.href = "/webSocketTerminal/executer/index.html"; 
            }, 100); // 100ms delay
        } else {
            document.getElementById('output').innerText = data;
        }
    })
    .catch(err => {
        console.error("Error:", err);
    });
});





/*
let socket = null;

document.getElementById('runCode').addEventListener('click', () => {
    const code = document.getElementById('codeEditor').value;

    fetch('/webSocketTerminal/saveCode', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: 'code=' + encodeURIComponent(code)
    })
    .then(response => response.text())
    .then(data => {
        document.getElementById('output').innerText = data;

        if (data.includes("Saved")) {
            socket = new WebSocket("ws://" + window.location.host + "/webSocketTerminal/terminal");

            socket.onopen = () => {
                console.log("WebSocket connection established.");
            };

            socket.onmessage = (e) => {
                document.getElementById('output').innerText += e.data;
            };

            socket.onclose = () => {
                console.log("WebSocket closed.");
            };

            socket.onerror = (err) => {
                console.error("WebSocket error:", err);
            };
        }
    });
});

// Add listener to send input to Main program
document.getElementById('sendInput').addEventListener('click', () => {
    const input = document.getElementById('userInput').value;
    if (socket && socket.readyState === WebSocket.OPEN) {
        socket.send(input);
        document.getElementById('userInput').value = ""; // clear after sending
    }
});



*/
