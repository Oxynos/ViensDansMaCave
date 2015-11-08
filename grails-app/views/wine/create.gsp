<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'wine.label', default: 'Wine')}"/>
</head>

<body>
<div class="row">
    <div class="col-xs-12">
        <h1>Créer un nouveau vin</h1>
    </div>
</div>

<div class="row">
    <g:if test="${flash.message}">
        <div class="alert alert-info col-xs-6" role="status">${flash.message}</div>
    </g:if>
</div>

<div class="row">
    <g:hasErrors bean="${wineInstance}">
        <ul class="alert alert-danger col-xs-6" role="alert">
            <g:eachError bean="${wineInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
</div>

<div class="row margin-top-20">
    <g:form url="[resource: wineInstance, action: 'save']" class="col-xs-6">
        <fieldset class="form">
            <g:render template="form"/>
        </fieldset>
        <fieldset class="text-right margin-top-20">
            <g:actionSubmit action="save" name="create" class="btn btn-danger"
                            value="Créer"/>
            <g:actionSubmit action="saveAndAddInCellar" name="createAndAddInCellar" class="btn btn-danger"
                            value="Créer et ajouter à ma cave"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
