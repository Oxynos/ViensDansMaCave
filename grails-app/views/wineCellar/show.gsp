
<%@ page import="viensdansmacave.WineCellar" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'wineCellar.label', default: 'WineCellar')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-wineCellar" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-wineCellar" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list wineCellar">
			
				<g:if test="${wineCellarInstance?.cellar}">
				<li class="fieldcontain">
					<span id="cellar-label" class="property-label"><g:message code="wineCellar.cellar.label" default="Cellar" /></span>
					
						<span class="property-value" aria-labelledby="cellar-label"><g:link controller="cellar" action="show" id="${wineCellarInstance?.cellar?.id}">${wineCellarInstance?.cellar?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${wineCellarInstance?.quantity}">
				<li class="fieldcontain">
					<span id="quantity-label" class="property-label"><g:message code="wineCellar.quantity.label" default="Quantity" /></span>
					
						<span class="property-value" aria-labelledby="quantity-label"><g:fieldValue bean="${wineCellarInstance}" field="quantity"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wineCellarInstance?.wine}">
				<li class="fieldcontain">
					<span id="wine-label" class="property-label"><g:message code="wineCellar.wine.label" default="Wine" /></span>
					
						<span class="property-value" aria-labelledby="wine-label"><g:link controller="wine" action="show" id="${wineCellarInstance?.wine?.id}">${wineCellarInstance?.wine?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:wineCellarInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${wineCellarInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
