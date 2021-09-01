
let iframeElement = document.getElementsByTagName("IFRAME")
const myButtons = document.getElementsByClassName("detail")

for(let btn of myButtons){
    let id = btn.id
    btn.onclick = () => {

        iframeElement.src = "localhost:8080/detail/"+id
    }

}
