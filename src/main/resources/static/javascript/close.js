const btn = document.getElementById('close-modal');
const myModal = document.getElementById('myModal');

const closeModal = () => {
    myModal.classList.remove('show');
    window.location.reload(true);
    console.log("kom hit");
};