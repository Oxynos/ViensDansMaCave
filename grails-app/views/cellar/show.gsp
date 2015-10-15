
<%@ page import="viensdansmacave.Cellar" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'cellar.label', default: 'Cellar')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-cellar" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-cellar" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list cellar">
			
				<g:if test="${cellarInstance?.rate}">
				<li class="fieldcontain">
					<span id="rate-label" class="property-label"><g:message code="cellar.rate.label" default="Rate" /></span>
					
						<span class="property-value" aria-labelledby="rate-label"><g:fieldValue bean="${cellarInstance}" field="rate"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${cellarInstance?.member}">
				<li class="fieldcontain">
					<span id="member-label" class="property-label"><g:message code="cellar.member.label" default="Member" /></span>
					
						<span class="property-value" aria-labelledby="member-label"><g:link controller="member" action="show" id="${cellarInstance?.member?.id}">${cellarInstance?.member?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${cellarInstance?.wines}">
				<li class="fieldcontain">
					<span id="wines-label" class="property-label"><g:message code="cellar.wines.label" default="Wines" /></span>
					
						<g:each in="${cellarInstance.wines}" var="w">
						<span class="property-value" aria-labelledby="wines-label"><g:link controller="wineCellar" action="show" id="${w.id}">${w?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:cellarInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${cellarInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
