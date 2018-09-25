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
            <th/>
            <th>Author</th>
            <th>Target</th>
            <th>Content</th>
            <th/>
        </tr>
        <g:each in="${messageList}">
            <g:if test="${!it.read}"><tr class="not-read"></g:if>
            <g:else><tr></g:else>
                <td><g:if test="${!it.read}"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span></g:if></td>
                <td><g:link action="show" controller="user" params="${[id: it.author.id]}">${it.author.username}</g:link></td>
                <td><g:link action="show" controller="user" params="${[id: it.target.id]}">${it.target.username}</g:link></td>
                <td>${it.content}</td>
                <td>
                    <g:link action="show" params="${[id: it.id]}" class=" no-underline">
                        <button type="button" class="btn btn-success">Show <span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
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