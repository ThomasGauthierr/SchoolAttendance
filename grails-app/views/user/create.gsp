<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
            <a href="#create-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

            <div class="nav" role="navigation">
                <ul>
                    <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                </ul>
            </div>

            %{--<div id="create-user" class="content scaffold-create" role="main">--}%

                %{--<h1><g:message code="default.create.label" args="[entityName]" /></h1>--}%

                %{--<g:if test="${flash.message}">--}%
                    %{--<div class="message" role="status">${flash.message}</div>--}%
                %{--</g:if>--}%

                %{--<g:hasErrors bean="${this.user}">--}%
                    %{--<ul class="errors" role="alert">--}%
                        %{--<g:eachError bean="${this.user}" var="error">--}%
                            %{--<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>--}%
                        %{--</g:eachError>--}%
                    %{--</ul>--}%
                %{--</g:hasErrors>--}%

                %{--<g:form resource="${this.user}" method="POST" enctype="multipart/form-data">--}%

                    %{--<div class="fieldcontain">--}%
                        %{--<label> Profile image </label>--}%
                        %{--<input type="file" name="profileImageFile" />--}%
                    %{--</div>--}%

                    %{--<fieldset class="form">--}%
                        %{--<f:all bean="user"/>--}%
                    %{--</fieldset>--}%

                    %{--<fieldset class="buttons">--}%
                        %{--<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />--}%
                    %{--</fieldset>--}%
                %{--</g:form>--}%
            %{--</div>--}%

            <h2> Cette partie est uniquement pour le drag and drop, utilisez le bouton "Create" en dessus </h2>
            <div id="dd-box"> Drop the the profile image from you computer </div>

            <form id="user-form-1" method="POST" enctype="multipart/form-data">

                <div class="fieldcontain">
                    <label>Username</label>
                    <input id="user-form-username" type="text" name="username" />
                </div>

                <div class="fieldcontain">
                    <label>Password</label>
                    <input id="user-form-password" type="text" name="password" />
                </div>
            </form>

            <button type="submit" class="save" id="submit-second">Create</button>

        </div>

        <g:javascript>
            let formData = new FormData();

            // Javascript and JQuerry goes here !!

            // Initialize all the events linked to the drag and drop in order to avoid event propagation

            $(document).on('dragenter', '#dd-box', function() {
                $(this).css('border', '3px dashed red');
                return false;
            })

            $(document).on('dragover', '#dd-box', function(e) {
                e.preventDefault();
                e.stopPropagation();
                $(this).css('border', '3px dashed red');
                return false;
            })

            $(document).on('dragleave', '#dd-box', function(e) {
                e.preventDefault();
                e.stopPropagation();
                $(this).css('border', '3px dashed #BBBBBB');
                return false;
            })

            $(document).on('dragstart', '#dd-box', function(e) {
                console.log('dragstart');
                e.dataTransfer.setData('text/html', 'foo');
                e.dataTransfer.setData('draggable', '');
            });

            $(document).on('drop', '#dd-box', function(e) {
                e.preventDefault();
                e.stopPropagation();
                if(e.originalEvent.dataTransfer) {
                    if(e.originalEvent.dataTransfer.files.length) {
                        $(this).css('border', '3px dashed green');
                        formData.append('profileImage', e.originalEvent.dataTransfer.files[0]);
                        $(this).text(e.originalEvent.dataTransfer.files[0].name);
                    }
                }
                else {
                    $(this).css('border', '3px dashed #BBBBBB');
                }

                return false
            })

            $('#submit-second').on('click', function (e) {

                formData.append('username', $('#user-form-username').val());
                formData.append('password', $('#user-form-password').val());
                let request = new XMLHttpRequest();

                request.onreadystatechange = function() {
                    if (request.readyState === 4) {
                        console.log(request.response);
                        console.log(JSON.parse(request.response).name);
                        window.location = 'show/' + JSON.parse(request.response).name;
                    }
                }

                request.open("POST", "/tp/user/save2");
                request.send(formData);
            })

        </g:javascript>

    </body>
</html>