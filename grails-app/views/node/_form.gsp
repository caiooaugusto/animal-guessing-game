<%@ page import="com.game.node.Node" %>



<div class="fieldcontain ${hasErrors(bean: nodeInstance, field: 'parent', 'error')} ">
	<label for="parent">
		<g:message code="node.parent.label" default="Parent" />
		
	</label>
	<g:select id="parent" name="parent.id" from="${com.game.node.Node.list()}" optionKey="id" value="${nodeInstance?.parent?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: nodeInstance, field: 'growthTo', 'error')} required">
	<label for="growthTo">
		<g:message code="node.growthTo.label" default="Growth To" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="growthTo" from="${com.game.node.Node$Direction?.values()}" keys="${com.game.node.Node$Direction.values()*.name()}" required="" value="${nodeInstance?.growthTo?.name()}" />

</div>

<div class="fieldcontain ${hasErrors(bean: nodeInstance, field: 'animal', 'error')} required">
	<label for="animal">
		<g:message code="node.animal.label" default="Animal" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="animal" name="animal.id" from="${com.game.animal.Animal.list()}" optionKey="id" required="" value="${nodeInstance?.animal?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: nodeInstance, field: 'question', 'error')} required">
	<label for="question">
		<g:message code="node.question.label" default="Question" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="question" name="question.id" from="${com.game.question.Question.list()}" optionKey="id" required="" value="${nodeInstance?.question?.id}" class="many-to-one"/>

</div>

