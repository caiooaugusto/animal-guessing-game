<!-- Wide card with share menu button -->
<div class="main-card-wide mdl-card mdl-shadow--16dp">
    <div class="mdl-card__menu">
        <g:javascript>
            var resetGame = function(){
                ${remoteFunction(action:"resetGame", controller: "node", onComplete:"reloadGame()")}
            };
        </g:javascript>
        <button class="mdl-button mdl-button--icon mdl-js-button mdl-js-ripple-effect" href="javascript:void(0)" onclick="resetGame();return false;">
            <i id="reset-button" class="material-icons">delete_forever</i></button>
            <div class="mdl-tooltip mdl-tooltip--large" for="reset-button">
                Restart game data
            </div>
        </button>

    </div>
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
</div>
