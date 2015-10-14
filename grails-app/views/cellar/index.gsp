
<%@ page import="viensdansmacave.Cellar" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cellar.label', default: 'Cellar')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-cellar" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-cellar" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="rate" title="${message(code: 'cellar.rate.label', default: 'Rate')}" />
					
						<th><g:message code="cellar.member.label" default="Member" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${cellarInstanceList}" status="i" var="cellarInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${cellarInstance.id}">${fieldValue(bean: cellarInstance, field: "rate")}</g:link></td>
					
						<td>${fieldValue(bean: cellarInstance, field: "member")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${cellarInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
