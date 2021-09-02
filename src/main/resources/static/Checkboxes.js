function selectAllCheckboxes(source) {
    const checkboxes = document.querySelectorAll('input[name=category]');
    for (let i = 0; i < checkboxes.length; i++) {

            checkboxes[i].checked = source.checked;

    }
}