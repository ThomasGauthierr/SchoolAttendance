<!DOCTYPE html>
<html>

<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
    <title><g:message code="default.create.label" args="[entityName]" /></title>
</head>

    <body>

        <div id="create-user" class="content scaffold-create" role="main">
            <h1><g:message code="default.editImage.label" args="[entityName]" /></h1>

            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <g:hasErrors bean="${this.user}">
                <ul class="errors" role="alert">
                    <g:eachError bean="${this.user}" var="error">
                        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                    </g:eachError>
                </ul>
            </g:hasErrors>

            <g:uploadForm name="editImage" action="editImage">
                <g:hiddenField name="username" value="${this.user.username}"/>
                <g:hiddenField name="password" value="${this.user.password}"/>

            </g:uploadForm>

            <div id="dd-box"> Drop the the profile image from you computer </div>
            <button type="submit" class="save" id="submit"> Submit </button>

            <input id="user-id" type="hidden" value="${this.user.id}"/>

        </div>

        <g:javascript src="dragAndDropImageUpload.js" />

    </body>
</html>
