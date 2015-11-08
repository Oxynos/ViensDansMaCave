<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title></title>
</head>

<body>

<g:if test="${isMe}">
    <div class="row">
        <div class="col-xs-12 text-center">
            <g:link action="addWine" class="btn btn-danger">Ajouter un vin à ma cave</g:link>
        </div>
    </div>
</g:if>

<div class="row">
    <div class="col-xs-12" role="main">
        <h1>La cave de ${member.username} (Note : ${member.cellar.rate}/5)</h1>
    </div>
</div>

<div class="row margin-top-20">
    <div class="col-xs-10 col-xs-offset-1">
        <g:if test="${flash.message}">
            <div class="alert alert-info" role="status">${flash.message}</div>
        </g:if>
        <table class="table">
            <thead>
            <tr>

                <g:sortableColumn property="name" title="Nom"/>

                <g:sortableColumn property="year" title="Année"/>

                <g:sortableColumn property="color" title="Couleur"/>

                <g:sortableColumn property="quantity" title="Quantité"/>

                <th></th>

            </tr>
            </thead>
            <tbody>
            <g:each in="${member.cellar.wineCellars}" status="i" var="wineCellarInstance">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                    <td>${fieldValue(bean: wineCellarInstance.wine, field: "name")}</td>


                    <td>${fieldValue(bean: wineCellarInstance.wine, field: "year")}</td>

                    <td>${fieldValue(bean: wineCellarInstance.wine, field: "color")}</td>

                    <td><g:if test="${isMe}">
                        <g:link action="reduceQuantity" id="${wineCellarInstance.id}"
                                class="badge">-</g:link></g:if> ${fieldValue(bean: wineCellarInstance, field: "quantity")} <g:if
                            test="${isMe}"><g:link
                                action="increaseQuantity" id="${wineCellarInstance.id}"
                                class="badge">+</g:link></g:if></td>

                    <td><g:link controller="wine" action="show"
                                id="${wineCellarInstance.wine.id}" class="btn btn-danger" alt="Voir ce vin"
                                title="Voir ce vin"><span
                                class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></g:link>
                        <g:if test="${isMe}">
                            <g:link controller="cellar" action="removeWineFromCellar"
                                    id="${wineCellarInstance.wine.id}" onclick="return confirm('Voulez-vous vraiment supprimer ce vin de votre cave ?');" class="btn btn-danger" alt="Supprimer ce vin"
                                    title="Supprimer ce vin"><span
                                    class="glyphicon glyphicon-trash" aria-hidden="true"></span></g:link></g:if></td>

                </tr>
            </g:each>
            </tbody>
        </table>

        <div class="pagination">
            <g:paginate total="${wineCellarInstanceCount ?: 0}"/>
        </div>
    </div>
</div>
</div>

</body>
</html>