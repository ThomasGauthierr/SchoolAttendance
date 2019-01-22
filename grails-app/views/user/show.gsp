<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

        <div id="show-user" class="content scaffold-show" role="main">

            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>



            <fieldset>
                <b>Username</b> : <f:display bean="user" property="username"/><br/>
                <b>Last connection</b> : <f:display bean="user" property="lastConnection"/><br/>
                <b>Creation date</b> : <f:display bean="user" property="dateCreated"/>
            </fieldset>

            <sec:ifAllGranted roles="ROLE_ADMIN">
                <g:form resource="${this.user}" method="DELETE">
                    <fieldset class="buttons-container">
                        <g:link action="edit" params="${[id: this.user.id]}" class=" no-underline">
                            <button type="button" class="btn btn-primary btn-profile">Edit informations <span class="glyphicon glyphicon-edit" aria-hidden="true"></span></button>
                        </g:link><br/>
                        <g:link action="editImage" params="${[id: this.user.id]}" class=" no-underline">
                            <button type="button" class="btn btn-warning btn-profile">Edit picture <span class="glyphicon glyphicon-picture" aria-hidden="true"></span></button>
                        </g:link><br/>
                        <button type="submit" class="btn btn-danger btn-profile">Delete <span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button>
                    </fieldset>
                </g:form>
            </sec:ifAllGranted>

        </div>
    </body>
</html>
