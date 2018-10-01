<!doctype html>
<html>
<head>
    <title>Connection impossible</title>
    <meta name="layout" content="main">
    <g:if env="development"><asset:stylesheet src="errors.css"/></g:if>
</head>
<body>
<ul class="errors">
    <li>You have be banned by an admin</li>
</ul>
<g:link action="index" controller="logout" class=" no-underline" style="margin-left: 5px">
    <button type="button" class="btn btn-warning btn-profile">
        << Back to login form
    </button>
</g:link>
</body>
</html>
