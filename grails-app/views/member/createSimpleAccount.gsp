<%--
  Created by IntelliJ IDEA.
  User: Lucie
  Date: 2015-10-02
  Time: 11:28
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title></title>
</head>

<body>

<h1>Création du compte</h1>

<g:hasErrors bean="${retourCreation}">
    <div class="erreurBloc">
        <g:renderErrors bean="${retourCreation}" as="list"  />
    </div>
</g:hasErrors>

<g:form class="customForm" url="[action: 'saveSimpleAccount']" >
    <label>Nom d'utilisateur *</label>
    <g:field type="text" name="username" required="true"></g:field>
    <br/>
    <label>Mot de passe *</label>
    <g:field type="text" name="password" required="true"></g:field>
    <br/>
    <g:actionSubmit value="Creer compte" action="saveSimpleAccount"/>
</g:form>


</body>
</html>