function selectAllCheckboxes(source) {
    const checkboxes = document.querySelectorAll('input[name=category]');
    for (let i = 0; i < checkboxes.length; i++) {
           checkboxes[i].checked = source.checked;
    }
}

function clearCheckboxes(source) {
    const checkboxes = document.querySelectorAll('input[type="checkbox"]');
    for (let i = 0; i < checkboxes.length; i++) {
            checkboxes[i].checked = false;
    }
}