const expand = document.querySelector(".filter-outer");
const expandButton = document.querySelector(".expand-button");
const fas = document.getElementById("expand")

expandButton.onclick = function() {
    expand.classList.toggle("active");
    fas.classList.toggle("fa-chevron-right");
    fas.classList.toggle("fa-chevron-left");
}