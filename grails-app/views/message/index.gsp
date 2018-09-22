<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'message.label', default: 'Message')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-message" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div id="list-message" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <table >
                <tr>
                    <th>Author</th>
                    <th>Target</th>
                    <th>Content</th>
                    <th/>
                </tr>
                <g:each in="${messageList}">
                    <tr>
                        <td><g:link action="show" controller="user" params="${[id: it.author.id]}">${it.author.username}</g:link></td>
                        <td><g:link action="show" controller="user" params="${[id: it.target.id]}">${it.target.username}</g:link></td>
                        <td>${it.content}</td>
                        <td>
                            <g:link action="show" params="${[id: it.id]}">
                                <button type="button" class="btn btn-success">Show <span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                            </g:link>
                            <g:link action="edit" params="${[id: it.id]}">
                                <button type="button" class="btn btn-primary">Edit <span class="glyphicon glyphicon-edit" aria-hidden="true"></span></button>
                            </g:link>
                            <g:link action="delete" params="${[id: it.id]}">
                                <button type="button" class="btn btn-danger">Delete <span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button>
                            </g:link>
                        </td>
                    </tr>
                </g:each>
            </table>

            <div class="pagination">
                <g:paginate total="${messageCount ?: 0}" />
            </div>
        </div>
    </body>
</html>