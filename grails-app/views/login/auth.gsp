<html>
<head>
    <meta name='layout' content='main'/>

</head>

<body>
<div class="row">
    <div class="col-xs-6 col-xs-offset-3">
        <div class="panel panel-default">
            <div class="panel-heading"><h2 class="panel-title"><g:message code="springSecurity.login.header"/></h2>
            </div>

            <div class="panel-body">
                <g:if test='${flash.message}'>
                    <div class='alert alert-danger'>${flash.message}</div>
                </g:if>

                <form action='${postUrl}' method='POST' id='loginForm' autocomplete='off' class="form-horizontal">
                    <div class="form-group">
                        <label for='username' class="control-label col-xs-5"><g:message
                                code="springSecurity.login.username.label"/>:</label>

                        <div class="col-xs-7">
                            <input type='text' class='form-control' name='j_username' id='username'/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for='password' class="control-label col-xs-5"><g:message
                                code="springSecurity.login.password.label"/>:</label>

                        <div class="col-xs-7">
                            <input type='password' class='form-control' name='j_password' id='password'/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-7 col-xs-offset-5">
                            <div class="checkbox">

                                <label for='remember_me'><input type='checkbox' name='${rememberMeParameter}'
                                                                id='remember_me'
                                                                <g:if test='${hasCookie}'>checked='checked'</g:if>/><g:message
                                        code="springSecurity.login.remember.me.label"/></label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-5 col-xs-7">
                            <input type='submit' class="btn btn-danger" id="submit" value='${message(code: "springSecurity.login.button")}'/>
                        </div>
                    </div>

                    <p>

                    </p>
                </form>
            </div>
        </div>
    </div>
</div>
<script type='text/javascript'>
    (function () {
        document.forms['loginForm'].elements['j_username'].focus();
    })();
</script>
</body>
</html>
