<%--
  Created by IntelliJ IDEA.
  User: Romain
  Date: 22/10/2015
  Time: 10:59
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title></title>
</head>

<a href="#list-wineCellar" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
</div>

<body>

<h1>Noter la cave de ${cellar.member.username}</h1>
<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>
<g:form action="addRate" id="${cellar.id}">
    <fieldset class="form">
        <div class="fieldcontain">
            <label for="rate1">1</label>
            <g:radio id="rate1" name="rate" value="1" checked="${rate && rate.rate == 1.floatValue()}"/>
            <label for="rate2">2</label>
            <g:radio id="rate2" name="rate" value="2" checked="${rate && rate.rate == 2.floatValue()}"/>
            <label for="rate3">3</label>
            <g:radio id="rate3" name="rate" value="3" checked="${rate && rate.rate == 3.floatValue()}"/>
            <label for="rate4">4</label>
            <g:radio id="rate4" name="rate" value="4" checked="${rate && rate.rate == 4.floatValue()}"/>
            <label for="rate5">5</label>
            <g:radio id="rate5" name="rate" value="5" checked="${rate && rate.rate == 5.floatValue()}"/>
        </div>
        <div class="fieldcontain">
            <g:actionSubmit action="addRate" value="Voter" />
        </div>
    </fieldset>
</g:form>

</body>
</html>