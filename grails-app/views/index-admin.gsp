<!doctype html>
<html>

<head>
    <meta name="layout" content="main"/>
    <title>Welcome Dear Admin</title>
</head>

<body>

%{--<div class="svg" role="presentation">--}%
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

        <div id="list-user" class="content scaffold-list index-table" role="main">

            %{-- <f:table collection="${userList}" />--}%
            <p>
                Last subscribed players
            </p>

            <br>

            <table >
                <tr>
                    <th> Username </th>
                    <th> Last connected on </th>
                    <th> Last joined on </th>
                </tr>
                <g:each in="${userList}">
                    <tr>
                        <td>${it.username}</td>
                        <td>${it.previousConnection}</td>
                        <td>${it.dateCreated}</td>
                    </tr>
                </g:each>
            </table>

            <div class="pagination">
                <g:paginate total="${userCount ?: 0}" />
            </div>

        </div>

        <div id="list-matches" class="content scaffold-list index-table" role="main">

            %{-- <f:table collection="${userList}" />--}%
            <p>
                Last played matches
            </p>

            <br>

            <table >
                <tr>
                    <th>Winnder</th>
                    <th>Looser</th>
                </tr>
                <g:each in="${matches}">
                    <tr>
                        <td>${it.winner}</td>
                        <td>${it.looser}</td>
                    </tr>
                </g:each>
            </table>

            <div class="pagination">
                <g:paginate total="${userCount ?: 0}" />
            </div>

        </div>

    </section>
</div>

</body>
</html>