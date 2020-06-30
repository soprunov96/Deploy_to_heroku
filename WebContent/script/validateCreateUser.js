// Example starter JavaScript for disabling form submissions if there are invalid fields
(function () {
    'use strict';
    window.addEventListener('load', function () {
        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.getElementsByClassName('needs-validation');
        // Loop over them and prevent submission
        var validation = Array.prototype.filter.call(forms, function (form) {
            form.addEventListener('submit', function (event) {
                var login = document.getElementById("input_login").value;
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                var validPassword = document.getElementById("needs_validation_password");
                validPassword.classList.add("was-validated");
                var validFirstName = document.getElementById("needs_validation_first_name");
                validFirstName.classList.add("was-validated");
                var validLastName = document.getElementById("needs_validation_last_name");
                validLastName.classList.add("was-validated");
                var validRole = document.getElementById("needs_validation_role");
                validRole.classList.add("was-validated");
            }, false);
        });
    }, false);
})();

