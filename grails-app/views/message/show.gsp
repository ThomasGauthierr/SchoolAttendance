<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'message.label', default: 'Message')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-message" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <div id="show-message-infos">
                <f:display bean="message"/>
            </div>

            <sec:ifAllGranted roles="ROLE_ADMIN">
                <div id="show-message">
                    <g:link action="edit" params="${[id: this.message.id]}" class=" no-underline">
                        <button type="button" class="btn btn-primary btn-profile">Edit <span class="glyphicon glyphicon-edit" aria-hidden="true"></span></button>
                    </g:link>
                    <g:link action="delete" params="${[id: this.message.id]}" class=" no-underline">
                        <button type="button" class="btn btn-danger btn-profile">Delete <span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button>
                    </g:link>
                </div>
            </sec:ifAllGranted>
        </div>
    </body>
</html>
