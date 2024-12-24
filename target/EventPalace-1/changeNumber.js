 function animateNumber(element, targetValue) {
    let currentValue = 0;
    const step = Math.ceil(targetValue / 100); // Define step to increase the value faster
    const interval = setInterval(() => {
      if (currentValue < targetValue) {
        currentValue += step; // Increase by step
        if (currentValue > targetValue) currentValue = targetValue; // Ensure it doesn't exceed target
        element.textContent = currentValue.toLocaleString(); // Update text with comma formatting
      } else {
        clearInterval(interval); // Stop when target value is reached
      }
    }, 5); // Speed up the animation (decrease the interval time)
  }

  document.addEventListener('DOMContentLoaded', () => {
    const bookingsElement = document.querySelector('h2:first-of-type'); // Select the first <h2> (Bookings)
    const usersElement = document.querySelectorAll('h2')[1]; // Select the second <h2> (Users)
    const venuesElement = document.querySelectorAll('h2')[2]; // Select the third <h2> (Venues)

    animateNumber(bookingsElement, 2700); // Animate for 2.7K
    animateNumber(usersElement, 1300); // Animate for 1.3K
    animateNumber(venuesElement, 74); // Animate for 74
  });