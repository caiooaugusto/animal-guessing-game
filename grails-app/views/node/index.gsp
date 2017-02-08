
<%@ page import="com.game.node.Node" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'node.label', default: 'Node')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-node" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-node" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="node.parent.label" default="Parent" /></th>
					
						<g:sortableColumn property="growthTo" title="${message(code: 'node.growthTo.label', default: 'Growth To')}" />
					
						<th><g:message code="node.animal.label" default="Animal" /></th>
					
						<th><g:message code="node.question.label" default="Question" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${nodeInstanceList}" status="i" var="nodeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${nodeInstance.id}">${fieldValue(bean: nodeInstance, field: "parent")}</g:link></td>
					
						<td>${fieldValue(bean: nodeInstance, field: "growthTo")}</td>
					
						<td>${fieldValue(bean: nodeInstance, field: "animal")}</td>
					
						<td>${fieldValue(bean: nodeInstance, field: "question")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${nodeInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
