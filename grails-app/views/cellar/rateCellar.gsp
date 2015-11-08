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
</head>

<body>

<div class="row">
    <div class="col-xs-12">
        <h1>Noter la cave de ${cellar.member.username}</h1>
    </div>
</div>

<div class="row">
    <g:if test="${flash.message}">
        <div class="alert alert-info col-xs-6" role="status">${flash.message}</div>
    </g:if>
</div>

<div class="row margin-top-20">
    <g:form action="addRate" id="${cellar.id}" class="col-xs-6">
        <fieldset class="text-center">
            <label class="radio-inline">
            <g:radio id="rate1" name="rate" value="1" checked="${rate && rate.rate == 1.floatValue()}"/>1</label>
            <label class="radio-inline">
            <g:radio id="rate2" name="rate" value="2" checked="${rate && rate.rate == 2.floatValue()}"/>2</label>
            <label class="radio-inline">
            <g:radio id="rate3" name="rate" value="3" checked="${rate && rate.rate == 3.floatValue()}"/>3</label>
            <label class="radio-inline">
            <g:radio id="rate4" name="rate" value="4" checked="${rate && rate.rate == 4.floatValue()}"/>4</label>
            <label class="radio-inline">
            <g:radio id="rate5" name="rate" value="5" checked="${rate && rate.rate == 5.floatValue()}"/>5</label>


                <g:actionSubmit action="addRate" value="Voter" class="btn btn-danger margin-left-20"/>
        </fieldset>
    </g:form>
</div>

</body>
</html>