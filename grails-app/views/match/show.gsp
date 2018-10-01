<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'match.label', default: 'Match')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-match" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>

            <table style="width:100%">
                <tr>
                    <th></th>
                    <th>Name</th>
                    <th>Score</th>
                </tr>
                <tr>
                    <td>Winner</td>
                    <td>${this.match.winner.username}</td>
                    <td>${this.match.winnerScore}</td>
                </tr>
                <tr>
                    <td>Looser</td>
                    <td>${this.match.looser.username}</td>
                    <td>${this.match.looserScore}</td>
                </tr>
            </table>

            <sec:ifAllGranted roles="ROLE_ADMIN">
                <g:form resource="${this.match}" method="DELETE">
                    <fieldset class="buttons-container">
                        <div id="show-match">
                            <g:link action="edit" params="${[id: this.match.id]}" class="no-underline">
                                <button type="button" class="btn btn-primary btn-profile">Edit <span class="glyphicon glyphicon-edit" aria-hidden="true"></span></button>
                            </g:link>

                            <button type="submit" class="btn btn-danger btn-profile">Delete <span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button>
                        </div>
                    </fieldset>
                </g:form>
            </sec:ifAllGranted>
        </div>
    </body>
</html>
