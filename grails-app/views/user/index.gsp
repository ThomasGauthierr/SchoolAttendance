<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/main')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-user" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            %{--<f:table collection="${userList}" />--}%

            <table >
                <tr>
                    <th/>
                    <th>Username</th>
                    <th>Last connection date</th>
                    <th/>
                </tr>
                <g:each in="${userList}">
                    <tr>
                        <td><!--ToDo : Add user pictures here--></td>
                        <td><g:link action="show" params="${[id: it.id]}">${it.username}</g:link></td>
                        <td>${it.previousConnection}</td>
                        <td>
                            <g:link action="edit" params="${[id: it.id]}">
                                <button type="button" class="btn btn-warning">Edit <span class="glyphicon glyphicon-edit" aria-hidden="true"></span></button>
                            </g:link>
                            <g:link action="delete" params="${[id: it.id]}">
                                <button type="button" class="btn btn-danger">Delete <span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button>
                            </g:link>
                        </td>
                    </tr>
                </g:each>
            </table>

            <div class="pagination">
                <g:paginate total="${userCount ?: 0}" />
            </div>
        </div>
    </body>
</html>