<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
    <title><g:message code="default.create.label" args="[entityName]" /></title>
</head>
<body>
<a href="#create-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<h2> Cette partie est uniquement pour le drag and drop, utilisez le bouton "Create" en dessus </h2>
<div id="dd-box"> Drop the the profile image from you computer </div>
<div id="create-user">
    <form id="user-form-1" method="POST" enctype="multipart/form-data">

        <div class="fieldcontain">
            <label>Username</label>
            <input id="user-form-username" type="text" name="username" />
        </div>

        <div class="fieldcontain">
            <label>Password</label>
            <input id="user-form-password" type="password" name="password" />
        </div>
    </form>

    <br/>

    <button type="submit" class="save btn btn-primary" id="submit-second">
        Create <span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
    </button>

    <g:link action="index" class="no-underline">
        <button type="button" class="btn btn-danger">Cancel <span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
    </g:link>
</div>

<g:javascript src="dragAndDrop.js" />


</body>
</html>