<%--
  Created by IntelliJ IDEA.
  User: Lucie
  Date: 2015-10-22
  Time: 09:27
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title></title>
</head>

<body>
    <div class="content">
        <h1>Informations du compte</h1>
        <g:form url="[resource:member, action:'update']" method="PUT" >
            <fieldset class="form">
                <label>Nom d'utilisateur *</label>
                <g:field type="text" name="username" required="true" value="${member?.username}"></g:field>
                <br/>
                <label>Email</label>
                <g:field type="text" name="email" value="${member?.email}"></g:field>
                <br/>
                <label>Date de naissance</label>
                <g:datePicker precision="day" name="birthday" value="${member?.birthday}"></g:datePicker>
                <br/>
                <label>Pays</label>
                <g:field type="text" name="country" value="${member?.country}"></g:field>
                <br/>
                <label>Ville</label>
                <g:field type="text" name="city" value="${member?.city}"></g:field>
                <br/>
            </fieldset>
            <fieldset class="buttons">
                <g:actionSubmit class="save" action="" value="Enregistrer les informations" />
            </fieldset>
        </g:form>
    </div>
</body>
</html>