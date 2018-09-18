<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <sec:ifAllGranted roles="ROLE_ADMIN">
                    <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                    <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                    <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                </sec:ifAllGranted>
            </ul>
        </div>

    <div id="show-user" class="content scaffold-show" role="main">
        <h1><g:message code="default.show.label" args="[entityName]" /></h1>

        <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
        </g:if>

        %{-- If the user has a profile image field, then we display that image by using its url --}%
        <g:if test="${this.user.profileImageName}">
            <img style="height: 120px;width: 102px;"src="${"http://localhost/img/" + this.user.profileImageName}" />
        </g:if>

        <fieldset>
            Username : <f:display bean="user" property="username"/><br/>
            Last connection : <f:display bean="user" property="lastConnection"/>
        </fieldset>

        <g:form resource="${this.user}" method="DELETE">
            <fieldset class="buttons">
                <g:link class="edit" action="edit" resource="${this.user}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
            </fieldset>
        </g:form>

        </div>
    </body>
</html>
