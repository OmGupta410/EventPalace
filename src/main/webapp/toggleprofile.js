 document.addEventListener('DOMContentLoaded', () => {
      const profileButton = document.getElementById('profile-button');
      const profileDropdown = document.getElementById('profile-dropdown');

      if (profileButton && profileDropdown) {
        // Toggle dropdown visibility
        profileButton.addEventListener('click', (event) => {
          event.stopPropagation(); // Prevent click from propagating
          profileDropdown.classList.toggle('hidden');
          console.log("Dropdown toggled. Current state:", profileDropdown.classList);
        });

        // Close dropdown if clicked outside
        document.addEventListener('click', (event) => {
          if (!profileDropdown.contains(event.target) && !profileButton.contains(event.target)) {
            profileDropdown.classList.add('hidden');
            console.log("Clicked outside. Dropdown hidden.");
          }
        });
      } else {
        console.error("Profile button or dropdown not found.");
      }
    });