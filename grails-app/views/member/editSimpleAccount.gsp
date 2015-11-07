<%--
  Created by IntelliJ IDEA.
  User: Lucie
  Date: 2015-10-22
  Time: 09:27
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.Calendar"%>
<html>
<head>
    <meta name="layout" content="main">
    <title></title>
</head>

<body>
    <div class="content">
        <h1>Informations du compte</h1>
        <g:hasErrors bean="${member}">
            <ul class="errors" role="alert">
                <g:eachError bean="${member}" var="error">
                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
        </g:hasErrors>
        <g:form url="[resource:member, action:'update']" method="POST" >
            <fieldset class="form">
                <div class="${hasErrors(bean:member,field:'username', 'error')}">
                    <label>Nom d'utilisateur *</label>
                    <g:field type="text" name="username" required="true" value="${member?.username}"></g:field>
                </div>
                <div class="${hasErrors(bean:member,field:'password', 'error')}">
                    <label>Mot de passe *</label>
                    <g:field type="password" name="password" required="true" value="${member?.password}"></g:field>
                </div>
                <div class="${hasErrors(bean:member,field:'password', 'error')}">
                    <label>Confirmer le mot de passe *</label>
                    <g:field type="password" name="passwordConfirm" required="true" value="${passwordConfirm ? passwordConfirm : member?.password}"></g:field>
                </div>
                <div class="${hasErrors(bean:member,field:'email', 'error')}">
                    <label>Email</label>
                    <g:field type="text" name="email" value="${member?.email}"></g:field>
                </div>
                <div class="${hasErrors(bean:member,field:'birthday', 'error')}">
                    <label>Date de naissance</label>
                    <g:datePicker precision="day" name="birthday" value="${member?.birthday}" default="none" noSelection="['':'']" years="${Calendar.instance.get(Calendar.YEAR)-18..Calendar.instance.get(Calendar.YEAR)-200}"></g:datePicker>
                </div>
                <div class="${hasErrors(bean:member,field:'country', 'error')}">
                    <label>Pays</label>
                    <g:field type="text" name="country" value="${member?.country}"></g:field>
                </div>
                <div class="${hasErrors(bean:member,field:'city', 'error')}">
                    <label>Ville</label>
                    <g:field type="text" name="city" value="${member?.city}"></g:field>
                </div>
            </fieldset>
            <fieldset class="buttons">
                <g:actionSubmit class="save" action="updateSimpleAccount" value="Enregistrer les informations" />
            </fieldset>
        </g:form>
    </div>
</body>
</html>