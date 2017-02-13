<g:include controller="node" action="initGame"/>

<!-- Wide card with share menu button -->
<div class="main-card-wide mdl-card mdl-shadow--2dp">
    <div class="mdl-card__title">
        <h2 class="mdl-card__title-text">Animal Guessing Game</h2>
    </div>
    <div id="message" class="mdl-card__supporting-text">
        Welcome to the animal guessing game<br>
        Thing in an animal and click on get started...
    </div>
    <div class="mdl-card__actions mdl-card--border">
        <g:remoteLink  id="button-get-started" action="getFirstQuestion" controller="node" update="message" onComplete="showButtonsYesNo()" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">Get Started</g:remoteLink>
        <g:remoteLink  id="button-yes" action="getLeftNode" controller="node" update="message" style="display:none" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect">Yes</g:remoteLink>
        <g:remoteLink  id="button-no" action="getRightNode" controller="node" update="message" onFailure="showCreateAnimal()" style="display:none" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect">No</g:remoteLink>
    </div>
    <div class="mdl-card__menu">
        <button class="mdl-button mdl-button--icon mdl-js-button mdl-js-ripple-effect">
            <i class="material-icons">share</i>
        </button>
    </div>
</div>

<!-- modal input dialog-->
<dialog id="dialog-animal" class="mdl-dialog">
    <h4 class="mdl-dialog__title">What was the animal you thought?</h4>
    <div class="mdl-dialog__content">
        <p>
            Enter the name of the animal:
        </p>
        <!-- Simple Textfield -->
        <form action="#">
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                <input class="mdl-textfield__input" type="text" id="animal-name" name="animal-name"/>
                <label class="mdl-textfield__label" for="animal-name">Animal</label>
            </div>
        </form>
    </div>
    <div class="mdl-dialog__actions">
        <g:javascript>
            var createAnimal = function(){
                var animalName = getAnimalName();
                ${remoteFunction(action:"createAnimal", controller: "animal", update:"question-dialog-message", params: '\'name=\' + animalName', onComplete:"showCreateQuestion()")}
            };
        </g:javascript>
        <a id="button-create-animal" class="mdl-button close" href="javascript:void(0)" onclick="createAnimal();return false;">Ok</a>
    </div>
</dialog>
<!-- modal input dialog-->
<dialog id="dialog-question" class="mdl-dialog">
    <h4 id="question-dialog-message" class="mdl-dialog__title"></h4>
    <div class="mdl-dialog__content">
        <p>
            Please complete the sentence above:
        </p>
        <!-- Simple Textfield -->
        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <input class="mdl-textfield__input" type="text" id="question-text"/>
            <label class="mdl-textfield__label" for="question-text">Diference</label>
        </div>
    </div>
    <div class="mdl-dialog__actions">
        <g:javascript>
            var createQuestion = function(){
                var questionText = getQuestionText();
                ${remoteFunction(action:"createQuestion", controller: "question", update:"question-dialog-message", params: '\'text=\' + questionText', onComplete:"reloadGame()")}
            };
        </g:javascript>
        <a id="button-create-question" class="mdl-button close" href="javascript:void(0)" onclick="createQuestion();return false;">Ok</a>
    </div>
</dialog>
<!-- modal input dialog javascript-->
<script>
    console.log("showDialog");
    var dialogA = document.getElementById('dialog-animal');
    var dialogQ = document.getElementById('dialog-question');
    if (! dialogA.showModal) {
        dialogPolyfill.registerDialog(dialogA);
    }
    if (! dialogQ.showModal) {
        dialogPolyfill.registerDialog(dialogQ);
    }
    dialogA.querySelector('.close').addEventListener('click', function() {
        dialogA.close();
    });
    dialogQ.querySelector('.close').addEventListener('click', function() {
        dialogQ.close();
    });
</script>