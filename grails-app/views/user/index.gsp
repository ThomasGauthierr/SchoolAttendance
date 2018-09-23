<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
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
                    <th>Creation date</th>
                    <th/>
                </tr>
                <g:each in="${userList}">
                    <tr>
                        <td class="td-index-avatar"><img class="avatar index-avatar" src="${appProperties.getFileUrl() + it.profileImageName}" onerror="arguments[0].currentTarget.style.display='none'"/></td>
                        <td>${it.username}</td>
                        <td>${it.lastConnection}</td>
                        <td>${it.dateCreated}</td>
                        <td>
                            <g:link action="show" params="${[id: it.id]}">
                                <button type="button" class="btn btn-success">Show <span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                            </g:link>
                            <g:link action="edit" params="${[id: it.id]}">
                                <button type="button" class="btn btn-primary">Edit informations <span class="glyphicon glyphicon-edit" aria-hidden="true"></span></button>
                            </g:link>
                            <g:link action="editImage" params="${[id: it.id]}">
                                <button type="button" class="btn btn-warning">Edit picture <span class="glyphicon glyphicon-picture" aria-hidden="true"></span></button>
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