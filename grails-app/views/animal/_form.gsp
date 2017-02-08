<%@ page import="com.game.animal.Animal" %>



<div class="fieldcontain ${hasErrors(bean: animalInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="animal.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${animalInstance?.name}"/>

</div>

