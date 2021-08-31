const expand = document.querySelector(".filter-outer");
const expandButton = document.querySelector(".expand-button");
const fas = document.getElementById("expand")
const sidebar = document.querySelector(".left-sidebar");

expandButton.onclick = function() {
    expand.classList.toggle("active");
    sidebar.classList.toggle("active");
    fas.classList.toggle("fa-chevron-right");
    fas.classList.toggle("fa-chevron-left");
}