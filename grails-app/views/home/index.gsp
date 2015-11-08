<%--
  Created by IntelliJ IDEA.
  User: Thomas Zoratto
  Date: 01/10/2015
  Time: 10:55
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title></title>
</head>

<body>
<div class="row">
    <div class="col-xs-8">
        <div class="jumbotron">
            <h1>Bienvenue !</h1>
            <p>“Viens dans ma cave” est un site permettant de fédérer une communauté de passionnés de vin. Ce site permet à une communauté de membres de gérer les bouteilles de leur cave et de noter les caves des autres membres.</p>
            <p><g:link controller='member' action='createSimpleAccount' class="btn btn-danger btn-lg" >Rejoindre la communauté</g:link></p>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-xs-6">
        <table class="table table-striped">
            <caption>Classement des vins les plus présents</caption>
            <thead>
            <tr>

                <th>Nom</th>

                <th>Année</th>

                <th>Couleur</th>

                <th>Quantité</th>

            </tr>
            </thead>
            <tbody>

            <g:each in="${wines}" status="i" var="wineInstance">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                    <td>${fieldValue(bean: wineInstance[0], field: "name")}</td>

                    <td>${fieldValue(bean: wineInstance[0], field: "year")}</td>

                    <td>${fieldValue(bean: wineInstance[0], field: "color")}</td>

                    <td>${wineInstance[1]}</td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div class="col-xs-6">
        <table class="table table-striped">
            <caption>Caves les mieux notées</caption>
            <thead>
            <tr>

                <th>Nom</th>

                <th>Note</th>

            </tr>
            </thead>
            <tbody>

            <g:each in="${cellars}" status="i" var="cellarInstance">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                    <td>${fieldValue(bean: cellarInstance, field: "member.username")}</td>

                    <td>${fieldValue(bean: cellarInstance, field: "rate")}</td>

                </tr>
            </g:each>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>