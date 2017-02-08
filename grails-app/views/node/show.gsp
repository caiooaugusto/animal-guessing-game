
<%@ page import="com.game.node.Node" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'node.label', default: 'Node')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-node" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-node" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list node">
			
				<g:if test="${nodeInstance?.parent}">
				<li class="fieldcontain">
					<span id="parent-label" class="property-label"><g:message code="node.parent.label" default="Parent" /></span>
					
						<span class="property-value" aria-labelledby="parent-label"><g:link controller="node" action="show" id="${nodeInstance?.parent?.id}">${nodeInstance?.parent?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${nodeInstance?.growthTo}">
				<li class="fieldcontain">
					<span id="growthTo-label" class="property-label"><g:message code="node.growthTo.label" default="Growth To" /></span>
					
						<span class="property-value" aria-labelledby="growthTo-label"><g:fieldValue bean="${nodeInstance}" field="growthTo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${nodeInstance?.animal}">
				<li class="fieldcontain">
					<span id="animal-label" class="property-label"><g:message code="node.animal.label" default="Animal" /></span>
					
						<span class="property-value" aria-labelledby="animal-label"><g:link controller="animal" action="show" id="${nodeInstance?.animal?.id}">${nodeInstance?.animal?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${nodeInstance?.question}">
				<li class="fieldcontain">
					<span id="question-label" class="property-label"><g:message code="node.question.label" default="Question" /></span>
					
						<span class="property-value" aria-labelledby="question-label"><g:link controller="question" action="show" id="${nodeInstance?.question?.id}">${nodeInstance?.question?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:nodeInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${nodeInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
