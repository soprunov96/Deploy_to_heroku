<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Login"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<fmt:setLocale value="${param.locale}" scope="session"/>
<link href="style/signin.css" rel="stylesheet">

<body>

<%--===========================================================================
Here we use a table layout.  
Class page corresponds to the '.page' element in included CSS document.
===========================================================================--%>

<table id="main-container">

    <%--===========================================================================
This is the HEADER, containing a top menu.
header.jspf contains all necessary functionality for it.
Just included it in this JSP document.
===========================================================================--%>

    <%-- HEADER --%>
        <c:if test="${not empty user}">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
        </c:if>
    <%-- HEADER --%>

    <%--===========================================================================
This is the CONTENT, containing the main part of the page.
===========================================================================--%>
    <tr>
        <td class="content center">
            <c:if test="${empty user}">
                <div class="left-header">
                    <div class="icon-start-page">
                        <a href="controller?command=showStartPage"><i
                                class="fa fa-bus"></i></a>
                    </div>
                </div>

                <div class="right-header">
                    <div class="dropdown">
                        <button class="btn btn-primary dropdown-toggle" type="button"
                                id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true"
                                aria-expanded="false">
                            <fmt:message key='settings_jsp.form.default_language'/>
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenu2">
                            <form id="setLocaleRu" action="controller?command=setLocale"
                                  method="post">
                                <input type="hidden" name="previousCommand"
                                       value="command=logout">
                                <input type="hidden" name="locale" value="ru">
                                <button class="dropdown-item" type="submit">
                                    <fmt:message key='settings_jsp.form.button_ru'/>
                                </button>
                            </form>
                            <form id="setLocaleEn" action="controller?command=setLocale"
                                  method="post">
                                <input type="hidden" name="previousCommand"
                                       value="command=logout">
                                <input type="hidden" name="locale" value="en">
                                <button class="dropdown-item" type="submit">
                                    <fmt:message key='settings_jsp.form.button_en'/>
                                </button>
                            </form>

                        </div>
                    </div>
                </div>
            </c:if>


            <%-- CONTENT --%> <%--===========================================================================
Defines the web form.
===========================================================================--%>
            <form id="login_form" action="controller" method="post"
                  class="form-signin">


                <%--===========================================================================
Hidden field. In the query it will act as command=login.
The purpose of this to define the command name, which have to be executed 
after you submit current form. 
===========================================================================--%>
                <input type="hidden" name="command" value="login"/>
                <div class="form-group">

                    <h1 class="h3 mb-3 font-weight-normal text-white">
                        <fmt:message key="login_jsp.label.login"/>
                    </h1>
                    <input type="text" id="inputLogin" class="form-control"
                           placeholder="<fmt:message key="login_jsp.label.user_name" />"
                           autofocus name="login" required/>

                </div>

                <input type="password" id="inputPassword" class="form-control"
                       placeholder="<fmt:message key="login_jsp.label.password" />"
                       requiredname="password" name="password" required/>

                <button class="btn btn-lg btn-primary btn-block" type="submit">
                    <fmt:message key="login_jsp.button.login"/>
                </button>
                <small id="inputEmail" class="form-text text-muted">
                    <fmt:message key="login_jsp.label.never_share_info"/></small>
            </form>
            <%-- CONTENT --%>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>
</html>