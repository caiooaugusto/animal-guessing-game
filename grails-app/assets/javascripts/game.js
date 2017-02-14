//hide get started button and show yes/no buttons 
function showButtonsYesNo() {
    document.getElementById('button-get-started').style.display = "none";
    document.getElementById('button-yes').style.display = "inline-block";
    document.getElementById('button-no').style.display = "inline-block";
}
//show create animal form
function showCreateAnimal() {
    dialogA.showModal();
}
//show create question form
function showCreateQuestion() {
    dialogQ.showModal();
}
//get animal name from element to pass to controller
function getAnimalName() {
    return document.getElementById("animal-name").value;
}
//get question text from element to pass to controller
function getQuestionText() {
    return document.getElementById("question-text").value;
}
//reload de page and the game, without erase DB data
function reloadGame() {
    setTimeout(function () {
        location.reload(true);
    },2000);
}
