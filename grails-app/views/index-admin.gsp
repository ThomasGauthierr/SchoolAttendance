<!doctype html>
<html>

<head>
    <meta name="layout" content="main"/>
    <title>Welcome Dear Admin</title>
</head>

<body>

<div class="svg" role="presentation">
    <div class="grails-logo-container">
        <asset:image src="grails-cupsonly-logo-white.svg" class="grails-logo"/>
    </div>
</div>

<div id="content" role="main">
    <section class="row colset-2-its">
        <h1>Welcome Dear Admin</h1>
        <div align="center">
            <g:if test="${user.previousConnection == null}">
                It's seems like you just joined us, hello ! o/
            </g:if>
            <g:else>
                Last connection : <f:display bean="user" property="previousConnection"/>
            </g:else>
        </div>

        <p>
            Last subscribed players
        </p>

        <div id="list-user" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${userList}" />

            <div class="pagination">
                <g:paginate total="${userCount ?: 0}" />
            </div>
        </div>

    </section>
</div>

</body>
</html>