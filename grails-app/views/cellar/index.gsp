
<%@ page import="viensdansmacave.Cellar" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cellar.label', default: 'Cellar')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="list-cellar" class="content scaffold-list" role="main">
			<h1>Liste des caves</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
						<g:sortableColumn property="member" title="Membre" />

						<g:sortableColumn property="rate" title="Note de la cave" />

						<th></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${memberInstanceList}" status="i" var="memberInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td>
							<g:if test="${currentMember == memberInstance}">
								<g:link controller="member" action="showSimpleAccount" id="${memberInstance.id}">${fieldValue(bean: memberInstance, field: "username")}</g:link>
							</g:if>
							<g:else>
								${fieldValue(bean: memberInstance, field: "username")}
							</g:else>
						</td>

						<td>${fieldValue(bean: memberInstance.cellar, field: "rate")}/5</td>

						<td>
							<fieldset class="buttons" style="display: inline; margin: 0 0.1em; padding: 0.1em 0.1em;">
								<g:if test="${currentMember == memberInstance}">
									<g:link controller="cellar" action="showCellar" id="${memberInstance.id}" style="margin: 0; padding: 0 0.2em;">Accéder à ma cave</g:link>
								</g:if>
								<g:else>
									<g:link controller="cellar" action="showCellar" id="${memberInstance.id}" style="margin: 0; padding: 0 0.2em;">Accéder à la cave de ${fieldValue(bean: memberInstance, field: "username")}</g:link>
								</g:else>
							</fieldset>
						</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${memberInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
