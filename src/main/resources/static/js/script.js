// Function to handle tab switching between Client and Officer login forms
function openLoginForm(evt, formType) {
    var i, form, tablinks;
    
    // Hide all login forms
    form = document.getElementsByClassName("login-form");
    for (i = 0; i < form.length; i++) {
        form[i].style.display = "none";
    }
    
    // Remove the "active" class from all tab links
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    
    // Show the selected login form
    document.getElementById(formType).style.display = "block";
    
    // Add the "active" class to the clicked tab link
    evt.currentTarget.className += " active";
}

// Optional: You can add form validation or other interactive features here
