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
                    <g:link controller="main" action="index" class="navbar-brand">SchoolAttendance</g:link>
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
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Students <span class="glyphicon glyphicon-education" aria-hidden="true"></span><span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><g:link controller="student">View students <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span></g:link></li>
                                <li><g:link controller="student" action="create">Add a student <span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span></g:link></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Courses <span class="glyphicon glyphicon-book" aria-hidden="true"></span><span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><g:link controller="course">View courses <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span></g:link></li>
                                <li><g:link controller="course" action="create">Add a course <span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span></g:link></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Sessions <span class="glyphicon glyphicon-time" aria-hidden="true"></span><span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><g:link controller="session">View sessions <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span></g:link></li>
                                <li><g:link controller="session" action="create">Add a session <span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span></g:link></li>
                            </ul>
                        </li>
                    </sec:ifAllGranted>

                    <sec:ifNotGranted roles="ROLE_ADMIN">
                        <sec:ifAllGranted roles="ROLE_TEACHER">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Students <span class="glyphicon glyphicon-education" aria-hidden="true"></span><span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><g:link controller="student">View students <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span></g:link></li>
                                    <li><g:link controller="student" action="create">Add a student <span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span></g:link></li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Courses <span class="glyphicon glyphicon-book" aria-hidden="true"></span><span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><g:link controller="course">View courses <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span></g:link></li>
                                    <li><g:link controller="course" action="create">Add a course <span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span></g:link></li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Sessions <span class="glyphicon glyphicon-time" aria-hidden="true"></span><span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><g:link controller="session">View sessions <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span></g:link></li>
                                    <li><g:link controller="session" action="create">Add a session <span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span></g:link></li>
                                </ul>
                            </li>
                        </sec:ifAllGranted>
                    </sec:ifNotGranted>

                %{-- User info + logout section --}%
                    <sec:ifLoggedIn>
                        <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_TEACHER">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                    <sec:username/>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><g:link controller="user" action="show" id="${curUsr.info(id: true)}">My profile <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span></g:link></li>
                                    <li><g:link controller="logout">Logout <span class="glyphicon glyphicon-off" aria-hidden="true"></span></g:link></li>
                                </ul>
                            </li>
                        </sec:ifAnyGranted>
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
