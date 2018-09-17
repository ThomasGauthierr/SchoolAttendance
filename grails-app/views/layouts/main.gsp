<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
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
                    <sec:ifLoggedIn>
                        Hello <sec:username class="username-nav"/> :)
                    </sec:ifLoggedIn>
                </div>
            </div>
            <div class="navbar-collapse collapse" aria-expanded="false" style="height: 0.8px;">
                <ul class="nav navbar-nav navbar-right">
                    <sec:ifAllGranted roles="ROLE_ADMIN">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Users<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><g:link controller="user">View users</g:link></li>
                                <li><g:link controller="user" action="create">Create a user</g:link></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Matches<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><g:link controller="match">View matches</g:link></li>
                                <li><g:link controller="match" action="create">Add a match</g:link></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Messages<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><g:link controller="message">View messages</g:link></li>
                                <li><g:link controller="message" action="create">Add a messsage</g:link></li>
                            </ul>
                        </li>
                    </sec:ifAllGranted>
                    <sec:ifNotGranted roles="ROLE_ADMIN">
                        <sec:ifAllGranted roles="ROLE_USER">
                            <li><g:link controller="match" action="display">My matches</g:link></li>
                            <li><g:link controller="message" action="display">My messages</g:link></li>
                            <li><g:link controller="#">My profile</g:link></li>
                        </sec:ifAllGranted>
                    </sec:ifNotGranted>
                    <sec:ifLoggedIn>
                        <li><g:link controller="logout">Log out</g:link></li>
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
