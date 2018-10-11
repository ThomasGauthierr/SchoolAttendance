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
    <section class="row colset-2-its">
        <h1>Welcome <sec:username/> !</h1>
        <div align="center">
            <g:if test="${user.previousConnection == null}">
                <h3>It's seems like it's your first connection, hello ! o/</h3><br/>
            </g:if>
            <g:else>
                <h3>Previous connection : <f:display bean="user" property="previousConnection"/></h3><br/>
            </g:else>
        </div>

        <div id="list-user" class="content scaffold-list index-table" role="main">

            %{-- <f:table collection="${userList}" />--}%
            <p>
                Last connected players
            </p>

            <table class="no-hover">
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
        </div>

        <div id="list-matches" class="content scaffold-list index-table" role="main">

            %{-- <f:table collection="${userList}" />--}%
            <p>
                Last played matches
            </p>

            <table >
                <tr>
                    <th>Winner</th>
                    <th>Looser</th>
                </tr>
                <g:each in="${matches}">
                    <tr>
                        <td>${it.winner}</td>
                        <td>${it.looser}</td>
                    </tr>
                </g:each>
            </table>

        </div>

    </section>
</div>

</body>
</html>