<%--
  Created by IntelliJ IDEA.
  User: Lucie
  Date: 2015-10-22
  Time: 09:27
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.Calendar" %>
<html>
<head>
    <meta name="layout" content="main">
    <title></title>
</head>

<body>
<div class="row">
    <div class="col-xs-12">
        <h1>Informations du compte</h1>
    </div>
</div>

<div class="row">
    <div class="col-xs-10 col-xs-offset-1">
        <div class="row">
            <g:hasErrors bean="${member}">
                <ul class="alert alert-danger col-xs-12" role="alert">
                    <g:eachError bean="${member}" var="error">
                        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                                error="${error}"/></li>
                    </g:eachError>
                </ul>
            </g:hasErrors>
        </div>

        <div class="row margin-top-20">
            <g:form url="[resource: member, action: 'update']" method="POST" class="col-xs-6 col-xs-offset-3">
                <fieldset class="form">
                    <div class="${hasErrors(bean: member, field: 'username', 'has-error')} form-group">
                        <label class="control-label">Nom d'utilisateur *</label>
                        <g:field type="text" name="username" required="true" value="${member?.username}"
                                 class="form-control"></g:field>
                    </div>

                    <div class="${hasErrors(bean: member, field: 'password', 'has-error')} form-group">
                        <label class="control-label">Mot de passe *</label>
                        <g:field type="password" name="password" required="true" value="${member?.password}"
                                 class="form-control"></g:field>
                    </div>

                    <div class="${hasErrors(bean: member, field: 'password', 'has-error')} form-group">
                        <label class="control-label">Confirmer le mot de passe *</label>
                        <g:field type="password" name="passwordConfirm" required="true"
                                 value="${passwordConfirm != null ? passwordConfirm : member?.password}"
                                 class="form-control"></g:field>
                    </div>

                    <div class="${hasErrors(bean: member, field: 'email', 'has-error')} form-group">
                        <label class="control-label">Email</label>
                        <g:field type="text" name="email" value="${member?.email}" class="form-control"></g:field>
                    </div>

                    <div class="${hasErrors(bean: member, field: 'birthday', 'has-error')} form-group">
                        <label class="control-label">Date de naissance</label>

                        <div class="form-control">
                            <g:datePicker precision="day" name="birthday" value="${member?.birthday}" default="none"
                                          noSelection="['': '']"
                                          years="${Calendar.instance.get(Calendar.YEAR) - 18..Calendar.instance.get(Calendar.YEAR) - 200}"></g:datePicker>
                        </div>
                    </div>

                    <div class="${hasErrors(bean: member, field: 'country', 'has-error')} form-group">
                        <label class="control-label">Pays</label>
                        <g:field type="text" name="country" value="${member?.country}" class="form-control"></g:field>
                    </div>

                    <div class="${hasErrors(bean: member, field: 'city', 'has-error')} form-group">
                        <label class="control-label">Ville</label>
                        <g:field type="text" name="city" value="${member?.city}" class="form-control"></g:field>
                    </div>
                </fieldset>
                <fieldset class="buttons text-right">
                    <g:actionSubmit class="save btn btn-danger" action="updateSimpleAccount" value="Enregistrer les informations"/>
                </fieldset>
            </g:form>
        </div>
    </div>
</div>
</body>
</html>