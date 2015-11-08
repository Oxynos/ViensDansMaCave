<%@ page import="viensdansmacave.Cellar" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'cellar.label', default: 'Cellar')}"/>
</head>

<body>
<div class="row">
    <div class="col-xs-12">
        <h1>Liste des caves</h1>
    </div>
</div>

<div class="row">
    <g:if test="${flash.message}">
        <div class="alert alert-info col-xs-10 col-xs-offset-1" role="status">${flash.message}</div>
    </g:if>
</div>

<div class="row margin-top-20">
    <div class="col-xs-8">
        <table class="table">
            <thead>
            <tr>
                <g:sortableColumn property="member" title="Membre"/>

                <g:sortableColumn property="rate" title="Note de la cave"/>

                <th></th>

            </tr>
            </thead>
            <tbody>
            <g:each in="${memberInstanceList}" status="i" var="memberInstance">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                    <td>
                        <g:if test="${currentMember == memberInstance}">
                            <g:link controller="member" action="showSimpleAccount"
                                    id="${memberInstance.id}">${fieldValue(bean: memberInstance, field: "username")}</g:link>
                        </g:if>
                        <g:else>
                            ${fieldValue(bean: memberInstance, field: "username")}
                        </g:else>
                    </td>

                    <td>${fieldValue(bean: memberInstance.cellar, field: "rate")}/5</td>

                    <td class="text-right">
                        <fieldset class="buttons" style="display: inline; margin: 0 0.1em; padding: 0.1em 0.1em;">
                            <g:if test="${currentMember == memberInstance}">
                                <g:link controller="cellar" action="showCellar" id="${memberInstance.id}"
                                        style="margin: 0; padding: 0 0.2em;" class="btn btn-danger">Accéder à ma cave</g:link>
                            </g:if>
                            <g:else>
                                <g:link controller="cellar" action="showCellar" id="${memberInstance.id}"
                                        style="margin: 0; padding: 0 0.2em;" class="btn btn-danger"><span
                                        class="glyphicon glyphicon-eye-open" aria-hidden="true"></span> Visiter</g:link>
                                 <g:link controller="cellar" action="rateCellar" id="${memberInstance.cellar.id}"
                                          style="margin: 0; padding: 0 0.2em;" class="btn btn-danger"><span
                                         class="glyphicon glyphicon-star" aria-hidden="true"></span> Noter</g:link>
                            </g:else>
                        </fieldset>
                    </td>

                </tr>
            </g:each>
            </tbody>
        </table>

        <div class="pagination">
            <g:paginate total="${memberInstanceCount ?: 0}"/>
        </div>
    </div>
</div>
</body>
</html>
