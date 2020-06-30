var modal = document.getElementById("mySidenav");
var profile_icon = document.getElementById("profile_icon");
// var my_file = document.getElementById("myFile");

function openNav() {
    modal && (modal.style.width = "270px");
}

window.onclick = function (event) {
    if (event.target !== modal && event.target !== profile_icon ) {

        // closeNav()
    }
}

function closeNav() {
    modal && (modal.style.width = "0");
}