<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />

    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>
</head>
<body>

    <div class="navbar navbar-default navbar-static-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <div>
                    <g:link controller="main" action="index" class="navbar-brand">TP Grails</g:link>
                </div>
            </div>
            <div class="navbar-collapse collapse" aria-expanded="false" style="height: 0.8px;">
                <ul class="nav navbar-nav navbar-left">

                </ul>

                <ul class="nav navbar-nav navbar-right">

                    <sec:ifAllGranted roles="ROLE_ADMIN">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Users <span class="glyphicon glyphicon-user" aria-hidden="true"></span><span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><g:link controller="user">View users <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span></g:link></li>
                                <li><g:link controller="user" action="create">Create a user <span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span></g:link></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Matches <span class="glyphicon glyphicon-tower" aria-hidden="true"></span><span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><g:link controller="match">View matches <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span></g:link></li>
                                <li><g:link controller="match" action="create">Add a match <span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span></g:link></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Messages <span class="glyphicon glyphicon-comment" aria-hidden="true"></span><span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><g:link controller="message">View messages <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span></g:link></li>
                                <li><g:link controller="message" action="create">Send a message <span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span></g:link></li>
                            </ul>
                        </li>
                    </sec:ifAllGranted>

                    <sec:ifNotGranted roles="ROLE_ADMIN">
                        <sec:ifAllGranted roles="ROLE_USER">
                            <li><g:link controller="match" action="display">My matches <span class="glyphicon glyphicon-tower" aria-hidden="true"></span></g:link></li>
                            <li><g:link controller="message" action="display">My messages <span class="glyphicon glyphicon-comment" aria-hidden="true"></span></g:link></li>
                            %{--<li><g:link controller="user" action="display">My profile <span class="glyphicon glyphicon-user" aria-hidden="true"></span></g:link></li>--}%
                        </sec:ifAllGranted>
                    </sec:ifNotGranted>

                %{-- User info + logout section --}%
                    <sec:ifLoggedIn>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                <sec:username/>
                                %{--<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>--}%
                                <img class="avatar navbar-avatar" src="${appProperties.getFileUrl() + curUsr.info(imageName: true)}" onerror="arguments[0].currentTarget.style.display='none'"/>
                                <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li><g:link controller="user" action="show" id="${curUsr.info(id: true)}">My profile <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span></g:link></li>
                                <li><g:link controller="logout">Logout <span class="glyphicon glyphicon-off" aria-hidden="true"></span></g:link></li>
                            </ul>
                        </li>
                    </sec:ifLoggedIn>

                </ul>
            </div>
        </div>
    </div>

    <g:layoutBody/>

    <div class="footer" role="contentinfo"></div>

    <div id="spinner" class="spinner" style="display:none;">
        <g:message code="spinner.alt" default="Loading&hellip;"/>
    </div>

    <asset:javascript src="application.js"/>

</body>
</html>
