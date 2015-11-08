<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title></title>
</head>

<div class="row">
    <div class="col-xs-12">
        <h1>Ajouter un vin à ma cave</h1>
    </div>
</div>

<div class="row margin-top-20">
<g:form url="[action: 'findWine']" class="col-xs-6">
    <fieldset class="form">
        <div class="form-group">
            <label for="name">Nom du vin</label>
            <g:select name="name" from="${names}" value="${params.name}"
                      noSelection="${["": 'Choisir...']}" class="form-control"/>
        </div>

        <div class="form-group">
            <label for="year">Année</label>
            <g:select name="year" from="${years}" value="${params.year}"
                      noSelection="${['0': 'Choisir...']}" class="form-control"/>
        </div>

        <div class="text-center">
            <g:actionSubmit action="findWine" value="Rechercher" class="btn btn-danger"/>
        </div>

        <p class="text-right margin-top-20">Votre vin n'est pas présent ? <g:link controller="wine" action="create"
                                                                                  class="label label-danger">Créez le !</g:link></p>
    </fieldset>
</g:form>
</div>

<div class="row">
    <g:if test="${flash.message}">
        <div class="col-xs-6 alert alert-info" role="status">${flash.message}</div>
    </g:if>
</div>

<div class="row">

    <g:hasErrors bean="${ret}">
        <div class="col-xs-6 alert alert-danger">
            <g:renderErrors bean="${ret}" as="list"/>
        </div>
    </g:hasErrors>
</div>
<g:if test="${wines}">
    <div class="row">
        <div class="col-xs-12">
            <h1>Résultat de la recherche</h1>
        </div>
    </div>
</g:if>

<div class="row margin-top-20">
    <g:if test="${wines}">
        <g:form url="[action: 'addWineInCellar']" class="col-xs-6">
            <fieldset class="form">
                <div class="form-group">
                    <g:select name="wine" from="${wines}" optionKey="id" class="form-control"/>
                </div>

                <div class="form-group">
                    <label for="quantity">Quantité :</label>
                    <g:field type="number" min="1" required="" name="quantity" value="1" optionKey="id"
                             class="form-control"/>
                </div>

                <div class="text-center">
                    <g:actionSubmit action="addWineInCellar" value="Ajouter" class="btn btn-danger"/>
                </div>
            </fieldset>
        </g:form>
    </g:if>
</div>
</body>
</html>