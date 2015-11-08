<%--
  Created by IntelliJ IDEA.
  User: Lucie
  Date: 2015-10-08
  Time: 08:39
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title></title>
</head>

<body>

<div class="row">
    <div class="col-xs-10 col-xs-offset-1">

        <h1><sec:loggedInUserInfo field="username"/></h1>

        <g:if test="${flash.message}">
            <div class="message alert alert-success" role="status">${flash.message}</div>
        </g:if>

        <ol class="property-list member list-group">
            <li class="fieldcontain list-group-item">
                <div class="row">
                    <div class="property-label col-xs-4 text-right">Email</div>

                    <div class="property-value col-xs-5 text-right">${member.email}</div>
                </div>
            </li>
            <li class="fieldcontain list-group-item">
                <div class="row">
                    <div class="property-label col-xs-4 text-right">Date de naissance</div>

                    <div class="property-value col-xs-5 text-right"><g:formatDate format="dd/MM/yyyy" date="${member.birthday}"/></div>
                </div>
            </li>
            <li class="fieldcontain list-group-item">
                <div class="row">
                    <div class="property-label col-xs-4 text-right">Pays</div>

                    <div class="property-value col-xs-5 text-right">${member.country}</div>
                </div>
            </li>
            <li class="fieldcontain list-group-item">
                <div class="row">
                    <div class="property-label col-xs-4 text-right">Ville</div>

                    <div class="property-value col-xs-5 text-right">${member.city}</div>
                </div>
            </li>

        </ol>

        <div class="row">
            <g:form url="[resource: member, action: 'deleteSimpleAccount']" method="DELETE"
                    class="col-xs-6 col-xs-offset-3 text-center">
                <fieldset class="buttons">
                    <g:link class="edit btn btn-danger" action="editSimpleAccount"
                            resource="${member}">Modifier mes informations</g:link>
                    <g:actionSubmit class="delete btn btn-danger" action="deleteSimpleAccount"
                                    value="Supprimer mon compte"
                                    onclick="return confirm('Etes-vous sûr de vouloir supprimer votre compte ?');"/>
                </fieldset>
            </g:form>
        </div>
    </div>

</div>

</body>
</html>