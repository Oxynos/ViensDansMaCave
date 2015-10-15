
<%@ page import="viensdansmacave.Member" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'member.label', default: 'Member')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-member" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-member" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="username" title="${message(code: 'member.username.label', default: 'Username')}" />
					
						<g:sortableColumn property="password" title="${message(code: 'member.password.label', default: 'Password')}" />
					
						<g:sortableColumn property="email" title="${message(code: 'member.email.label', default: 'Email')}" />
					
						<g:sortableColumn property="birthday" title="${message(code: 'member.birthday.label', default: 'Birthday')}" />
					
						<g:sortableColumn property="country" title="${message(code: 'member.country.label', default: 'Country')}" />
					
						<g:sortableColumn property="city" title="${message(code: 'member.city.label', default: 'City')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${memberInstanceList}" status="i" var="memberInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${memberInstance.id}">${fieldValue(bean: memberInstance, field: "username")}</g:link></td>
					
						<td>${fieldValue(bean: memberInstance, field: "password")}</td>
					
						<td>${fieldValue(bean: memberInstance, field: "email")}</td>
					
						<td><g:formatDate date="${memberInstance.birthday}" /></td>
					
						<td>${fieldValue(bean: memberInstance, field: "country")}</td>
					
						<td>${fieldValue(bean: memberInstance, field: "city")}</td>
					
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
