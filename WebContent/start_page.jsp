<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="start page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<fmt:setLocale value="${param.locale}" scope="session"/>
<body>
<div class="main-container">


    <%--===========================================================================
Here we use a table layout.  
Class page corresponds to the '.page' element in included CSS document.
===========================================================================--%>


    <%-- HEADER

    <%@ include file="/WEB-INF/jspf/header.jspf"%>  --%>
    <%-- HEADER --%>


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


    <div class="start-page">


        <div id="carouselExampleInterval" class="carousel slide"
             data-ride="carousel">


            <div class="carousel-indicators">
                <div class="carousel-caption d-none d-md-block">

                    <a href="controller?command=showLoginForm"
                       class="btn light   btn-outline-primary btn-lg " role="button"
                       aria-pressed="true"><fmt:message
                            key="start_page_jsp.btn.slogan"/></a>
                </div>
            </div>
            <div class="carousel-inner">
                <div class="carousel-item active carousel-item__img-wrapper"
                     data-interval="5000">
                    <img src="images/cars1.jpg"
                         class="d-block img-fluid carousel-item__img" alt="...">

                </div>
                <div class="carousel-item carousel-item__img-wrapper"
                     data-interval="5000">
                    <img src="images/cars2.jpg"
                         class="d-block  img-fluid carousel-item__img" alt="...">
                </div>
                <div class="carousel-item carousel-item__img-wrapper"
                     data-interval="5000">
                    <img src="images/cars3.jpg"
                         class="d-block img-fluid carousel-item__img" alt="...">
                </div>
            </div>

            <a class="carousel-control-prev" href="#carouselExampleInterval"
               role="button" data-slide="prev"> <span
                    class="carousel-control-prev-icon" aria-hidden="true"></span> <span
                    class="sr-only">Previous</span>
            </a> <a class="carousel-control-next" href="#carouselExampleInterval"
                    role="button" data-slide="next"> <span
                class="carousel-control-next-icon" aria-hidden="true"></span> <span
                class="sr-only">Next</span>
        </a>
            <c:if test="${ empty user}">
                <div class="start-page-header">

                    <div class="left-header">
                        <div class="icon-start-page">
                            <a class="" href="controller?command=showStartPage"><i
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

                                    <input type="hidden" name="locale" value="ru">
                                    <button class="dropdown-item" type="submit">
                                        <fmt:message key='settings_jsp.form.button_ru'/>
                                    </button>
                                </form>
                                <form id="setLocaleEn" action="controller?command=setLocale"
                                      method="post">

                                    <input type="hidden" name="locale" value="en">
                                    <button class="dropdown-item" type="submit">
                                        <fmt:message key='settings_jsp.form.button_en'/>
                                    </button>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>

    </div>

    <%-- CONTENT --%>
</div>

<%--<%@ include file="/WEB-INF/jspf/footer.jspf"%>--%>


</body>
</html>