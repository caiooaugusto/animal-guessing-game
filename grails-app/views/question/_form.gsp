<%@ page import="com.game.question.Question" %>



<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'text', 'error')} required">
	<label for="text">
		<g:message code="question.text.label" default="Text" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="text" required="" value="${questionInstance?.text}"/>

</div>

