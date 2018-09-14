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
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-user" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
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
            <g:form resource="${this.user}" method="POST" id="createForm"/>
            <g:form id="uploadImage"/>
                <fieldset class="form">
                        <f:field bean="user" property="username" form="createForm"/>
                        <f:field bean="user" property="password" form="uploadForm"/>
                        <f:hiddenField bean="user" property="imageName" form="createForm" value="${new Random(System.currentTimeMillis())}"/>
                        <input type="file" name="imageName" form="createForm"/>
                        <fieldset class="buttons">
                            <input class="save" type="submit" form="uploadImage" value="${message(code:'user.imageName.upload.label', default: 'Upload image')}"/>
                        </fieldset>

                        %{--<g:link action="editImage" resource="${this.user}">--}%
                            %{--<g:message code="user.imageName.edit.label" default="Browse"></g:message>--}%
                        %{--</g:link>--}%
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" form="createForm" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
        </div>
    </body>
</html>
