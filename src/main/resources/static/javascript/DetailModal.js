
let iframeElement = document.getElementById("myIframe")
const myButtons = document.getElementsByClassName("detail")

for(let btn of myButtons){
    let id = btn.id
    btn.onclick = () => {
        iframeElement.src = "/detail/"+id
    }

}
