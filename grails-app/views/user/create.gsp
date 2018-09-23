<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
    <title><g:message code="default.create.label" args="[entityName]" /></title>
</head>
<body>
<a href="#create-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
    </ul>
</div>

%{--<div id="create-user" class="content scaffold-create" role="main">--}%

%{--<h1><g:message code="default.create.label" args="[entityName]" /></h1>--}%

%{--<g:if test="${flash.message}">--}%
%{--<div class="message" role="status">${flash.message}</div>--}%
%{--</g:if>--}%

%{--<g:hasErrors bean="${this.user}">--}%
%{--<ul class="errors" role="alert">--}%
%{--<g:eachError bean="${this.user}" var="error">--}%
%{--<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>--}%
%{--</g:eachError>--}%
%{--</ul>--}%
%{--</g:hasErrors>--}%

%{--<g:form resource="${this.user}" method="POST" enctype="multipart/form-data">--}%

%{--<div class="fieldcontain">--}%
%{--<label> Profile image </label>--}%
%{--<input type="file" name="profileImageFile" />--}%
%{--</div>--}%

%{--<fieldset class="form">--}%
%{--<f:all bean="user"/>--}%
%{--</fieldset>--}%

%{--<fieldset class="buttons">--}%
%{--<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />--}%
%{--</fieldset>--}%
%{--</g:form>--}%
%{--</div>--}%

<h2> Cette partie est uniquement pour le drag and drop, utilisez le bouton "Create" en dessus </h2>
<div id="dd-box"> Drop the the profile image from you computer </div>

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

<button type="submit" class="save" id="submit-second">Create</button>

</div>

<g:javascript src="dragAndDrop.js" />


</body>
</html>