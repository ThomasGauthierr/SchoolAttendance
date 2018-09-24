<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#edit-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div id="edit-user" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
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
            <g:form resource="${this.user}" method="PUT">
                <g:hiddenField name="version" value="${this.user?.version}" />
                <fieldset class="form">
                    <f:field bean="user" property="username" label="Username"/>
                    <f:field bean="user" property="password" type="password" label="Password"/>
                </fieldset>
                <g:link action="edit" params="${[id: this.user.id]}" class=" no-underline">
                    <button type="submit" class="btn btn-primary btn-profile">Update <span class="glyphicon glyphicon-edit" aria-hidden="true"></span></button>
                </g:link>
                <g:link action="show" params="${[id: this.user.id]}" class=" no-underline">
                    <button type="button" class="btn btn-danger btn-profile">
                        Cancel <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                    </button>
                </g:link><br/>
            </g:form>
        </div>
    </body>
</html>
