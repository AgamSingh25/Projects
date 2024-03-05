const svgNS = "http://www.w3.org/2000/svg";

        // These global variables hold the "state" of the game/simulation/animation.
        // They will only be re-set when the page is re-loaded

        const boardsizeX = 1000;
        const boardsizeY = 1000;

        let score = 0;

        let myTimer = null;

        let cx = 0;
        let cy = 0;
        let xspeed, yspeed; 
        let selectedDifficulty;
        
        function setLevel(){
            if(selectedDifficulty == "1"){
                xspeed = 5;
                yspeed = 5;
                document.getElementById("selectedDifficulty").innerHTML = "Selected Difficulty: Easy";
            }
    
           else if(selectedDifficulty == "2"){
                xspeed = 10;
                yspeed = 10;
                document.getElementById("selectedDifficulty").innerHTML = "Selected Difficulty: Medium";
            }
    
           else if(selectedDifficulty == "3"){
                xspeed = 15;
                yspeed = 15;
                document.getElementById("selectedDifficulty").innerHTML = "Selected Difficulty: Hard";
            }
        }

        function makeRandomBall() {
            let ball = document.createElementNS(svgNS, "circle");
            ball.setAttribute("id", "ball0");

            // Randomioze the ball size and location 
            let radius = 20
            ball.setAttribute("r", radius);
            cx = Math.floor(Math.random() * (boardsizeX - 2 * radius) + radius);
            ball.setAttribute("cx", cx);
            cy = Math.floor(Math.random() * (boardsizeY * 0.75 - 2 * radius) + radius);
            ball.setAttribute("cy", cy);

            // Set a random rgb color ( Make sure we can see it (use a minimum of 55)			
            let r = Math.floor(Math.random() * 200) + 56;
            let g = Math.floor(Math.random() * 200) + 56;
            let b = Math.floor(Math.random() * 200) + 56;
            // ball.setAttribute("fill", "rgb("+ r +","+ g +","+ b +")");
            ball.setAttribute("fill", `rgb(${r},${g},${b})`);

            return ball;
        }	

        let paddleX = 200; // Initial position of the paddle

        function makePaddle() {
            const paddle = document.createElementNS(svgNS, "rect");
            const paddleWidth = 130;
            const paddleHeight = 30;
            paddle.setAttribute("x", paddleX - paddleWidth / 2);
            paddle.setAttribute("y", boardsizeY - paddleHeight);
            paddle.setAttribute("width", paddleWidth);
            paddle.setAttribute("height", paddleHeight);
            paddle.setAttribute("fill", "blue");
            paddle.setAttribute("id", "paddle");
            return paddle;
        }

        function checkCollision() {
            const svg = document.getElementById("board");
            const ball = document.getElementById("ball0");

            const ballRadius = parseFloat(ball.getAttribute("r"));
            const ballCenterX = cx;
            const ballCenterY = cy;

            const paddle = document.getElementById("paddle");
            const paddleWidth = parseFloat(paddle.getAttribute("width"));
            const paddleX = parseFloat(paddle.getAttribute("x"));
            const paddleY = parseFloat(paddle.getAttribute("y"));

            if (
                ballCenterX + ballRadius >= paddleX &&
                ballCenterX - ballRadius <= paddleX + paddleWidth &&
                ballCenterY + ballRadius >= paddleY
            ) {
                // Ball hits the paddle, reverse its Y-speed
                yspeed = -yspeed;
                score++;
                scoreValue = document.getElementById("score");
                scoreValue.innerHTML = "Score: " + score;

                
            } 
            else if (ballCenterY + ballRadius > boardsizeY) {
                // Ball goes below the canvas, show "Failed" and offer restart option
                clearInterval(myTimer);
                document.getElementById("output").innerHTML = "Your score was " + score + ". To restart the game press <strong>restart buttom</strong> at the bottom";
            }
        }

        // Remove the circle from the Svg element (if it is present)	
        function clearAll() {
            let svg = document.getElementById("board");	
            let nodeBall = document.getElementById("ball0");
            if (nodeBall != null)
                svg.removeChild(nodeBall);

            let nodePaddle = document.getElementById("paddle");
            if (nodePaddle != null)
                svg.removeChild(nodePaddle);
        }

        // Function to move the paddle based on mouse clicks
        function movePaddleWithMouse() {
            const svg = document.getElementById("board");
            const paddle = document.getElementById("paddle");

            if (leftButtonDown) {
                // Move the paddle left when left mouse button is down
                paddleX -= 10;
            }

            if (rightButtonDown) {
                // Move the paddle right when right mouse button is down
                paddleX += 10;
            }

            // Ensure the paddle stays within the SVG canvas boundaries
            const paddleWidth = parseFloat(paddle.getAttribute("width"));
            paddleX = Math.max(paddleX, paddleWidth / 2);
            paddleX = Math.min(paddleX, boardsizeX - paddleWidth / 2);

            paddle.setAttribute("x", paddleX - paddleWidth / 2);
        }

        document.addEventListener("contextmenu", function (event) {
            event.preventDefault();
        });

        // Variables to track the mouse button state
        let leftButtonDown = false;
        let rightButtonDown = false;

        // Event listeners for mouse down and up events
        window.addEventListener("mousedown", function (event) {
            if (event.button === 0) {
                // Left mouse button is pressed
                leftButtonDown = true;
            } else if (event.button === 2) {
                // Right mouse button is pressed
                rightButtonDown = true;
            }
        });

        window.addEventListener("mouseup", function (event) {
            if (event.button === 0) {
                // Left mouse button is released
                leftButtonDown = false;
            } else if (event.button === 2) {
                // Right mouse button is released
                rightButtonDown = false;
            }
        });

        // At start-up assign the listeners
        window.addEventListener("load", function () {
            document.getElementById("start").addEventListener("click", function (event) {
                selectedDifficulty = document.querySelector("input[type=radio]:checked").value;
                document.getElementById("start").textContent = "Restart";
                document.getElementById("output").innerHTML = "";
                score = 0;
                document.getElementById("score").innerHTML = "Score: " + score;
                clearAll();
                setLevel();
                if (myTimer != null) clearInterval(myTimer);

                let svg = document.getElementById("board");

                let ball = makeRandomBall();
                svg.appendChild(ball);

                let paddle = makePaddle();
                svg.appendChild(paddle);

                myTimer = setInterval(function () {
                    let ball = document.getElementById("ball0");
                    cx += xspeed;
                    cy += yspeed;
                    let r = parseFloat(ball.getAttribute("r"));
                    console.log("radius = " + r);
                    if (cx - r <= 0 || cx + r > boardsizeX)
                        xspeed = -xspeed;
                    if (cy - r <= 0 || cy + r > boardsizeY)
                        yspeed = -yspeed;

                    ball.setAttribute("cx", cx);
                    ball.setAttribute("cy", cy);

                    checkCollision(); // Check for collision with paddle or edges of canvas
                }, 1000 / 30); // 30 fps (frames per second)
            });

            setInterval(function () {
                movePaddleWithMouse();
            }, 1000 / 60); // 60 fps (frames per second)
        });