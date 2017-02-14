function showButtonsYesNo() {
    document.getElementById('button-get-started').style.display = "none";
    document.getElementById('button-yes').style.display = "inline-block";
    document.getElementById('button-no').style.display = "inline-block";
}

function showCreateAnimal() {
    dialogA.showModal();
}

function showCreateQuestion() {
    dialogQ.showModal();
}

function getAnimalName() {
    return document.getElementById("animal-name").value;
}

function getQuestionText() {
    return document.getElementById("question-text").value;
}

function reloadGame() {
    location.reload(true);
}
