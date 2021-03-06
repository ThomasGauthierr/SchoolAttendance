<!doctype html>
<html>

<head>
    <meta name="layout" content="main"/>
    <title>Welcome dear Admin</title>
</head>

<body>

%{--<div class="svg" role="presentation">--}%
    <div class="grails-logo-container">
        <asset:image src="grails-cupsonly-logo-white.svg" class="grails-logo"/>
    </div>
</div>

<div id="content" role="main">
        <div align="center">
            <br/>
            <br/>
            <h1>Welcome <sec:username/> !</h1>
            <g:if test="${user.previousConnection == null}">
                <h3>It's seems like it's your first connection, nice to meet you ! o/</h3><br/>
            </g:if>
            <g:else>
                <h3>Previous connection : <f:display bean="user" property="previousConnection"/></h3><br/>
            </g:else>

            <g:if test="${userList != null && !userList.isEmpty()}">
                <p>
                    Last connected users
                </p>

                <table class="no-hover" style="width: 50%">
                    <tr>
                        <th> Username </th>
                        <th> Last connection </th>
                    </tr>
                    <g:each in="${userList}">
                        <tr>
                            <td>${it.username}</td>
                            <td>${it.lastConnection}</td>
                        </tr>
                    </g:each>
                </table>

            </g:if>
        </div>

</div>

</body>
</html>