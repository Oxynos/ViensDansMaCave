<%@ page import="viensdansmacave.Wine" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'wine.label', default: 'Wine')}"/>
</head>

<body>

<div class="row">
    <div class="col-xs-12">
        <h1>Détails du vin</h1>
    </div>
</div>

<div class="row">
    <g:if test="${flash.message}">
        <div class="alert alert-info col-xs-6" role="status">${flash.message}</div>
    </g:if>
</div>

<div class="row margin-top-20">
    <div class="col-xs-7">
        <ol class="list-group">

            <g:if test="${wineInstance?.name}">
                <li class="list-group-item">
                    <div class="row">
                        <span id="name-label" class="col-xs-5 text-right">Nom</span>

                        <span class="col-xs-6 col-xs-offset-1" aria-labelledby="name-label"><g:fieldValue bean="${wineInstance}"
                                                                                          field="name"/></span>
                    </div>
                </li>
            </g:if>

            <g:if test="${wineInstance?.year}">
                <li class="list-group-item">
                    <div class="row">
                        <span id="year-label" class="col-xs-5 text-right">Année</span>

                        <span class="col-xs-6 col-xs-offset-1" aria-labelledby="year-label"><g:fieldValue bean="${wineInstance}"
                                                                                                field="year"/></span>
                    </div>
                </li>
            </g:if>

            <g:if test="${wineInstance?.color}">
                <li class="list-group-item">
                    <div class="row">
                        <span id="color-label" class="col-xs-5 text-right">Couleur</span>

                        <span class="col-xs-6 col-xs-offset-1" aria-labelledby="color-label"><g:fieldValue bean="${wineInstance}"
                                                                                                 field="color"/></span>
                    </div>
                </li>
            </g:if>

        </ol>
    </div>
</div>
</body>
</html>
