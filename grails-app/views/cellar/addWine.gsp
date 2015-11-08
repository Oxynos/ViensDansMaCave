<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title></title>
</head>

<a href="#list-wineCellar" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">

</div>
<g:form url="[action: 'findWine']">
    <fieldset class="form">
        <div class="fieldcontain">
            <label for="name">Nom du vin</label>
            <g:select name="name" from="${names}" value="${params.name}"
                      noSelection="${["":'Choisir...']}" />
        </div>
        <div class="fieldcontain">
            <label for="year">Année</label>
            <g:select name="year" from="${years}" value="${params.year}"
                      noSelection="${['0':'Choisir...']}" />
        </div>
        <div class="fieldcontain">
            <g:actionSubmit action="findWine" value="Rechercher" />
        </div>
        <p>Votre vin n'est pas présent ? <g:link controller="wine" action="create">Créez le !</g:link></p>
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
    <g:form  url="[action: 'addWineInCellar']">
    <fieldset class="form">
        <div class="fieldcontain">
            <g:select name="wine" from="${wines}" optionKey="id" />
        </div>
        <div class="fieldcontain">
            <label for="quantity">Quantité : </label>
            <g:field type="number" min="1" required="" name="quantity" value="1" optionKey="id" />
        </div>
        <div class="fieldcontain">
            <g:actionSubmit action="addWineInCellar" value="Ajouter" />
        </div>
    </fieldset>
    </g:form>
</g:if>
</body>
</html>