<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'match.label', default: 'Match')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<a href="#list-match" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div id="list-match" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table >
        <tr>
            <th>Winner / score</th>
            <th>Looser / score</th>
            <th/>
        </tr>
        <g:each in="${matchList}">
            <tr>
                <td>
                    <g:if test="${!it.winner.isDeleted}">
                        <g:link action="show" controller="user" params="${[id: it.winner.id]}">${it.winner.username}</g:link> / ${it.winnerScore}
                    </g:if>
                    <g:else>
                        ${it.winner.username} / ${it.winnerScore}
                    </g:else>
                </td>
                <td>
                    <g:if test="${!it.looser.isDeleted}">
                        <g:link action="show" controller="user" params="${[id: it.looser.id]}">${it.looser.username} / ${it.looserScore}</g:link>
                    </g:if>
                    <g:else>
                        ${it.looser.username} / ${it.looserScore}
                    </g:else>
                </td>
                <td>
                    <g:link action="show" params="${[id: it.id]}">
                        <button type="button" class="btn btn-success">Show <span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                    </g:link>
                </td>
            </tr>
        </g:each>
    </table>

    <div class="pagination">
        <g:paginate total="${matchCount ?: 0}" />
    </div>
</div>
</body>
</html>