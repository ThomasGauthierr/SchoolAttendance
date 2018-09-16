<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <tit    le><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>

        <div id="create-user" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <g:hasErrors bean="${this.user}">
                <ul class="errors" role="alert">
                    <g:eachError bean="${this.user}" var="error">
                        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                    </g:eachError>
                </ul>
            </g:hasErrors>

            <g:form resource="${this.user}" method="POST">
                <fieldset class="form">
                    <f:all bean="user"/>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>

            <g:uploadForm name="uploadProfileImage" action="uploadProfileImage">
                <input type="file" name="profileImageFile" />
                <input class="save" type="submit" value="${message(code: 'user.profileImage.upload.label', default: 'Upload')}" />
            </g:uploadForm>

        </div>

    <asset:javascript src="application.js"/>

    <g:javascript>
            $('#uploadProfileImage').submit(doubleSubmit);

            function doubleSubmit(e1) {
                console.log("ok submitted the form")
                e1.preventDefault();
                e1.stopPropagation();
                var post_form1 = $.post($(this).action, $(this).serialize());

                post_form1.done(function(result) {
                    // would be nice to show some feedback about the first result here
                    $('#form2').submit();
                });
            };

        </g:javascript>

    </body>
</html>