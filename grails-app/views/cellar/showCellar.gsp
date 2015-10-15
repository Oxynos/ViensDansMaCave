<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title></title>
</head>

<body>

<a href="#list-wineCellar" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
        <li><g:link action="addWine">Ajouter un nouveau vin à ma cave</g:link></li>
    </ul>
</div>
<div id="list-wineCellar" class="content scaffold-list" role="main">
    <h1>La cave de <sec:loggedInUserInfo field="username"/> (Note : ${member.cellar.rate}/5)</h1>
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

                <td><g:link controller="wine" action="show" id="${wineCellarInstance.id}">${fieldValue(bean: wineCellarInstance, field: "wine")}</g:link></td>

                <td>${fieldValue(bean: wineCellarInstance.wine, field: "name")}</td>

                <td>${fieldValue(bean: wineCellarInstance.wine, field: "year")}</td>

                <td>${fieldValue(bean: wineCellarInstance.wine, field: "color")}</td>

                <td>${fieldValue(bean: wineCellarInstance, field: "quantity")}</td>

                <td><g:link controller="wine" action="show" id="${wineCellarInstance.wine.id}">Accéder au vin</g:link>
                    <g:link controller="cellar" action="removeWineFromCellar" id="${wineCellarInstance.wine.id}">Supprimer ce vin</g:link> </td>

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