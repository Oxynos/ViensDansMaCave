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

    <div class="content">

        <h1><sec:loggedInUserInfo field="username"/></h1>

        <ol class="property-list member">
            <li class="fieldcontain">
                <span class="property-label"> Email </span>
                <span class="property-value"> ${member.email} </span>
            </li>
            <li class="fieldcontain">
                <span class="property-label">Date de naissance </span>
                <span class="property-value">${member.birthday}</span>
            </li>
            <li class="fieldcontain">
                <span class="property-label">Pays </span>
                <span class="property-value"> ${member.country}</span>
            </li>
            <li class="fieldcontain">
                <span class="property-label">Ville </span>
                <span class="property-value"> ${member.city}</span>
            </li>

        </ol>

        <g:form url="[resource:member, action:'deleteSimpleAccount']" method="DELETE">
            <fieldset class="buttons">
                <g:actionSubmit class="delete" action="deleteSimpleAccount" value="Supprimer mon compte" onclick="return confirm('Etes-vous sûr de vouloir supprimer votre compte ?');" />
            </fieldset>
        </g:form>

    </div>

</body>
</html>