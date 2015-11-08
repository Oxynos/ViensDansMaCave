<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default="Grails"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
    <link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
    <link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">

    <link rel="stylesheet" href="${resource(dir: 'css', file: 'style.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap.min.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap-theme.min.css')}" type="text/css">
    <g:layoutHead/>
    <g:javascript library="application"/>
    <r:layoutResources/>
</head>

<body>
<div id="page-header">
    <div class="container">
        <div role="banner" class="row">
            <a class="col-xs-7" href="${createLink(uri: '/')}"><img
                    src="${resource(dir: 'images', file: 'WineBottle.jpg')}"
                    alt="Logo" class="img-circle" id="logo">

                <h1>Viens dans ma cave</h1></a>
            <sec:ifNotLoggedIn>
                <div class="col-xs-5 text-right margin-top-20">
                    <g:link controller='login' action='index' class="btn btn-default"><span
                            class="glyphicon glyphicon-log-in" aria-hidden="true"></span> Se connecter</g:link>
                    <g:link controller='member' action='createSimpleAccount' class="btn btn-default"><span
                            class="glyphicon glyphicon-new-window" aria-hidden="true"></span> Créer un compte</g:link>
                </div>
            </sec:ifNotLoggedIn>
            <sec:ifLoggedIn>
                <div class="col-xs-5 text-right margin-top-20">
                    <g:link controller='logout' action='index' class="btn btn-default"><span
                            class="glyphicon glyphicon-log-out" aria-hidden="true"></span> Se déconnecter</g:link>
                    <g:link controller='member' action='showSimpleAccount' class="btn btn-default"><span
                            class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> Mon compte</g:link>
                    <g:link controller='Cellar' action='showCellar' class="btn btn-default"><span
                            class="glyphicon glyphicon-collapse-down" aria-hidden="true"></span> Ma cave</g:link>
                    <g:link controller='Cellar' action='index' class="btn btn-default"><span
                            class="glyphicon glyphicon-collapse-down" aria-hidden="true"></span>Toutes les caves</g:link>
                </div>
            </sec:ifLoggedIn>
        </div>
    </div>
</div>

<div class="container padding-top-40">

    <g:layoutBody/>


    <div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt"
                                                                       default="Loading&hellip;"/></div>

</div>

<div class="footer" role="contentinfo">
    <div class="container">
        <div class="row">
            <div class="col-xs-6">
                <a href="${createLink(uri: '/')}">
                    Retour à l'accueil
                </a>
            </div>

            <div class="col-xs-6 text-right">
                ViensDansMaCave 2015
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'js', file: 'bootstrap.min.js')}"></script>
<r:layoutResources/>
</body>
</html>
