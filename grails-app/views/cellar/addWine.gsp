<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title></title>
</head>

<a href="#list-wineCellar" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
</div>
<g:form url="[action: 'findWine']">
    <fieldset class="form">
        <div class="field-inline">
            <label for="name">Nom du vin</label>
            <g:select name="name" from="${names}" value="${params.name}"
                      noSelection="${["":'Choisir...']}" />
        </div>
        <div class="field-inline">
            <label for="year">Année</label>
            <g:select name="year" from="${years}" value="${params.year}"
                      noSelection="${['0':'Choisir...']}" />
        </div>
        <div class="field-inline">
            <g:actionSubmit action="findWine" value="Rechercher" />
        </div>
    </fieldset>
</g:form>
<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>
<g:hasErrors bean="${ret}">
    <div class="erreurBloc">
        <g:renderErrors bean="${ret}" as="list"  />
    </div>
</g:hasErrors>
<g:if test="${wines}">
    <fieldset class="form" url="[action: 'addWineInCellar']">
        <div class="field-inline">
            <g:select name="wines" from="${wines}" value="${params.wine}"
                      noSelection="${[null:'Choisir...']}" />
        </div>
        <div class="field-inline">
            <g:actionSubmit action="addWineInCellar" value="Ajouter" />
        </div>
    </fieldset>
</g:if>
</body>
</html>