// Image array to store image filenames
const imageArray = [
  ["imagesG1/image1-1.jpg", "imagesG1/image1-2.jpg", "imagesG1/image1-3.jpg"],
  ["imagesG2/image2-1.jpg", "imagesG2/image2-2.jpg", "imagesG2/image2-3.jpg"],
  ["imagesG3/image3-1.jpg", "imagesG3/image3-2.jpg", "imagesG3/image3-3.jpg"],
];

var initialTimer = 10000; // Store the initial timer value separately
let timerInterval;
var imageCounter = 0;

// Choosing random images from each sub array
function chooseRandomImages() {
  const randomImages = [];
  for (let i = 0; i < imageArray.length; i++) {
    const randomIndex = Math.floor(Math.random() * imageArray[i].length);
    randomImages.push(imageArray[i][randomIndex]);
  }
  return randomImages;
}

//changing the images on page with 3 randomly selected images 
function changeImages(images) {
  const imageElements = document.querySelectorAll("img");
  for (let i = 0; i < imageElements.length; i++) {
    imageElements[i].src = images[i];
  }
}

// on clicking images, this function performs the animation
function doAnimation(event) {
  const target = event.target;
  target.classList.remove("spin");
  target.removeEventListener("click", doAnimation); // Remove the click event listener temporarily
  setTimeout(function() {
    target.classList.add("spin");
    const randomImages = chooseRandomImages();
    changeImages(randomImages);
    incrementCounter(1);
    resetTimer();
    target.addEventListener("click", doAnimation); // Add the click event listener back
  }, 0);
}

//Handles the user input in number box for timer
function handleInputChange() {
  const timerValueInput = document.getElementById("timerValue");
  const timerValue = parseInt(timerValueInput.value);

  if (!isNaN(timerValue) && timerValue >= 500 && timerValue <= 10000) {
    resetTimer();
  } else {
    timerValueInput.value = initialtimer;
  }
}

//resets the timer and updates on the web page
function resetTimer() {
  clearInterval(timerInterval);
  timer = parseInt(document.getElementById("timerValue").value);
  if (!isNaN(timer) && timer >= 500 && timer <= 10000) {
    // Reset to the same value if within the valid range
    initialTimer = timer;
  } else {
    timer = initialTimer; // Reset to the initial timer value
  }
  var timerDisplay = document.getElementById("timerDisplay");
  timerDisplay.innerText = (timer / 1000).toFixed(1);
  timerInterval = setInterval(updateTimer, 100);
}

// This function changes the timer value
function updateTimer() {
  var timerDisplay = document.getElementById("timerDisplay");
  timerDisplay.innerText = (timer / 1000).toFixed(1);
  timer -= 100;

  if (timer >= 0.66 * initialTimer) {
    timerDisplay.style.backgroundColor = "green";
  } 
  else if (timer >= 0.33 * initialTimer && timer <= 0.66 * initialTimer) {
    timerDisplay.style.backgroundColor = "orange";
  } 
  else {
    timerDisplay.style.backgroundColor = "red";
  }
  
  if (timer < 0) {
    clearInterval(timerInterval);
    timer = 0; // Reset the timer to 0 instead of a negative value
    const randomImages = chooseRandomImages();
    resetTimer();
    changeImages(randomImages);
    incrementCounter(3);
  }
  
}

// Increasing the counter according to image change
function incrementCounter(x) {
  const counter = document.getElementById("counter");
  imageCounter = imageCounter + x;
  counter.innerText = "Image Counter: " + imageCounter;
}

// adding events to randomize button and numberbox
document.addEventListener("DOMContentLoaded", function () {
  const randomImages = chooseRandomImages();
  changeImages(randomImages);

  const timerValueInput = document.getElementById("timerValue");
  timerValueInput.addEventListener("change", handleInputChange);

  const randomizeButton = document.getElementById("randomizeButton");
  randomizeButton.addEventListener("click", function () {
    const randomImages = chooseRandomImages();
    changeImages(randomImages);
    incrementCounter(3);
    resetTimer();
  });
});
