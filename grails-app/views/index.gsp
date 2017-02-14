<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Animal Guessing Game</title>
	</head>

	<body>
		<g:render template="node/node"></g:render>

		<!-- modal input dialog-->
		<dialog id="dialog-animal" class="mdl-dialog">
			<h4 class="mdl-dialog__title">What was the animal you thought?</h4>
			<div class="mdl-dialog__content">
				<p>
					Enter the name of the animal:
				</p>
				<!-- Simple Textfield -->
				<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
					<input class="mdl-textfield__input" type="text" id="animal-name" name="animal-name"/>
					<label class="mdl-textfield__label" for="animal-name">Animal</label>
				</div>
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
					<input class="mdl-textfield__input" type="text" id="question-text" name="question-text"/>
					<label class="mdl-textfield__label" for="question-text">Diference</label>
				</div>
			</div>
			<div class="mdl-dialog__actions">
				<g:javascript>
				var createQuestion = function(){
					var questionText = getQuestionText();
					${remoteFunction(action:"createQuestion", controller: "question", params: '\'text=\' + questionText', onComplete:"reloadGame()")}
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
	</body>
</html>

