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
<table>
  <caption>Classement des vins les plus présents</caption>
  <thead>
  <tr>

    <g:sortableColumn property="name" title="Nom" />

    <g:sortableColumn property="year" title="Année" />

    <g:sortableColumn property="color" title="Couleur" />

    <g:sortableColumn property="quantity" title="Quantité" />

    <th></th>

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
</body>
</html>