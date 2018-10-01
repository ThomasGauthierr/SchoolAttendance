<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'message.label', default: 'Message')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-message" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.message}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.message}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.message}" method="POST">
                <fieldset class="form">
                    %{--ToDo : Prevent author = target --}%
                    <f:field bean="message" property="author" label="From"/>
                    <f:field bean="message" property="target" label="To"/>
                    <f:field bean="message" property="content" label="Message">
                        <g:textArea name="content" rows="5" cols="40"
                            maxlength="40" value="Write your message here...">
                        </g:textArea>
                    </f:field>
                </fieldset>


                <div id="create-message">
                    <button type="submit" class="save btn btn-primary" id="submit-second">
                        Create <span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
                    </button>

                    <g:link action="index" class="no-underline">
                        <button type="button" class="btn btn-danger">Cancel <span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                    </g:link>
                </div>
            </g:form>
        </div>
    </body>
</html>
