<%@ page import="fr.mbds.firstgrails.Course" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'session.label', default: 'Session')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-session" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-session" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.session}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.session}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.session}" method="POST">
                <fieldset class="form">
                    <g:select name="session.course"
                              from="${fr.mbds.firstgrails.Course.list()}"
                              value="session?.course.id"
                              noSelection="['':'-Choose the correspondig course-']"/>
                    <hr/>
                    <br/>
                    <span style="font-weight: bold">Start date</span>
                    <br/>
                    <g:datePicker name="session.startDate" value="${new Date()}"
                                  noSelection="['':'-Choose start date-']"
                                  />
                    <hr/>
                    <br/>
                    <span style="font-weight: bold">End date</span>
                    <br/>
                    <g:datePicker name="session.endDate" value="${new Date()}"
                                  noSelection="['':'-Choose end date-']"/>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
