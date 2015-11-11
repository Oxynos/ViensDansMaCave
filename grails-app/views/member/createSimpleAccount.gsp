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

<div class="row">
    <div class="col-xs-12">
        <h1>Création du compte</h1>
    </div>

    <div class="row">
        <div class="col-xs10">
            <div class="row">
                <g:hasErrors bean="${retourCreation}">
                    <div class="alert alert-danger col-xs-12">
                        <g:renderErrors bean="${retourCreation}" as="list"/>
                    </div>
                </g:hasErrors>
            </div>

            <div class="row">
                <g:form class="customForm col-xs-4 col-xs-offset-4" url="[action: 'saveSimpleAccount']">
                    <div class="form-group">
                        <label>Nom d'utilisateur *</label>
                        <g:field type="text" name="username" required="true" class="form-control"></g:field>
                    </div>

                    <div class="form-group">
                        <label>Mot de passe *</label>
                        <g:field type="password" name="password" required="true" class="form-control"></g:field>
                    </div>

                    <div class="form-group">
                        <label>Confirmation du mot de passe *</label>
                        <g:field type="password" name="confirmation" required="true" class="form-control"></g:field>
                    </div>

                    <div class="text-right">
                        <g:actionSubmit value="Créer compte" action="saveSimpleAccount" class="btn btn-danger"/>
                    </div>
                </g:form>
            </div>
        </div>
    </div>

</div>

</body>
</html>