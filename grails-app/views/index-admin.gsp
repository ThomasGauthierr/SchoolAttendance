<!doctype html>
<html>

<head>
    <meta name="layout" content="main"/>
    <title>Welcome Dear Admin</title>
</head>

<body>
<content tag="nav">
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
                <li><a href="#">View messages</a></li>
                <li><a href="#">Add a messsage</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${user.username} <span class="caret"></span></a>
            <ul class="dropdown-menu">
                <li><g:link controller="logout">Logout</g:link></li>
            </ul>
        </li>
</content>

<div class="svg" role="presentation">
    <div class="grails-logo-container">
        <asset:image src="grails-cupsonly-logo-white.svg" class="grails-logo"/>
    </div>
</div>

<div id="content" role="main">
    <section class="row colset-2-its">
        <h1>Welcome Dear Admin</h1>

        <p>
            Last subscribed players
        </p>

        <div id="list-user" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${userList}" />

            <div class="pagination">
                <g:paginate total="${userCount ?: 0}" />
            </div>
        </div>

    </section>
</div>

</body>
</html>