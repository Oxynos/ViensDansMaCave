<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title></title>
</head>

<body>
<g:if test="${isMe}">
    <a href="#list-wineCellar" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
    <div class="nav" role="navigation">
        <ul>
            <li><g:link class="create" action="addWine">Ajouter un vin à ma cave</g:link></li>
        </ul>
    </div>
</g:if>
<div id="list-wineCellar" class="content scaffold-list" role="main">
    <h1>La cave de ${member.username} (Note : ${member.cellar.rate}/5)</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="name" title="Nom" />

            <g:sortableColumn property="year" title="Année" />

            <g:sortableColumn property="color" title="Couleur" />

            <g:sortableColumn property="quantity" title="Quantité" />

            <th></th>

        </tr>
        </thead>
        <tbody>
        <g:each in="${member.cellar.wineCellars}" status="i" var="wineCellarInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td>${fieldValue(bean: wineCellarInstance.wine, field: "name")}</td>

                <td>${fieldValue(bean: wineCellarInstance.wine, field: "year")}</td>

                <td>${fieldValue(bean: wineCellarInstance.wine, field: "color")}</td>

                <td>${fieldValue(bean: wineCellarInstance, field: "quantity")}</td>

                <td>
                    <fieldset class="buttons" style="display: inline; margin: 0 0.1em; padding: 0.1em 0.1em;">
                    <g:link controller="wine" action="show" id="${wineCellarInstance.wine.id}" style="margin: 0; padding: 0 0.2em;">Accéder au vin</g:link>
                    </fieldset>

                    <g:if test="${isMe}">
                        <fieldset class="buttons" style="display: inline; margin: 0 0.1em; padding: 0.1em 0.1em;">
                            <g:actionSubmit class="delete" controller="cellar" action="removeWineFromCellar" id="${wineCellarInstance.wine.id}"
                                        value="Supprimer" onclick="return confirm('Voulez-vous vraiment supprimer ce vin de votre cave ?');"
                            style="margin: 0; padding: 0 0.7em"/>
                        </fieldset>
                    </g:if>

                </td>

            </tr>
        </g:each>
        </tbody>
    </table>
    <div class="pagination">
        <g:paginate total="${wineCellarInstanceCount ?: 0}" />
    </div>
</div>

</body>
</html>