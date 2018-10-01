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

            <fieldset>
                <b>From</b> : ${this.message.author.username}<hr/>
                <b>To</b> : ${this.message.target.username}<hr/>
                <b>Date</b> : <f:display bean="message" property="dateCreated"/><hr/>
                <b>Content</b> : <f:display bean="message" property="content"/>
            </fieldset>

            <sec:ifAllGranted roles="ROLE_ADMIN">
                <div id="show-message">
                    <g:form resource="${this.message}" method="DELETE">
                        <fieldset class="buttons-container">
                            <g:link action="edit" params="${[id: this.message.id]}" class=" no-underline">
                                <button type="button" class="btn btn-primary btn-profile">Edit <span class="glyphicon glyphicon-edit" aria-hidden="true"></span></button>
                            </g:link>
                            <button type="submit" class="btn btn-danger btn-profile">Delete <span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button>
                        </fieldset>
                    </g:form>
                </div>
            </sec:ifAllGranted>
        </div>
    </body>
</html>
