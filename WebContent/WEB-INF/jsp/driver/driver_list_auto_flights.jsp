<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Driver list auto flights" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>


<body>
<table id="main-container">

    <c:set var="active_driver_list_auto_flights" value="active" scope="page"/>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>
            <div class="content-header">
                <h3 class="text-white"><fmt:message key="driver.driver_list_auto_flights_jsp.header_text"/></h3>

            </div>
            <p style="color: red;">${errorString}</p>

            <form action="controller" class="form-img">
                <input type="hidden" name="command"
                       value="driver_list_auto_flights"> <input type="hidden"
                                                                name="recordsPerPage" value="${recordsPerPage}"> <input
                    type="hidden" name="currentPage" value="${currentPage}">

                <table class="table table-sm table-hover table-dark">
                    <thead>
                    <tr>
                        <th>
                            <div class="table-header-block">
                                <span>№</span>
                                <div class="sort-arrows-block">
                                    <input type="submit" name="sortBy" value="id" class="orderBy"/>
                                    <input type="submit" name="sortBy" value="id DESC"
                                           class="orderByDesc"/>
                                </div>
                            </div>
                        </th>

                        <th>
                            <div class="table-header-block">
                                <span><fmt:message key="flight.list_jsp.label.name"/></span>
                                <div class="sort-arrows-block">
                                    <input type="submit" name="sortBy" value="name"
                                           class="orderBy"/> <input type="submit" name="sortBy"
                                                                    value="name DESC" class="orderByDesc"/>
                                </div>
                            </div>
                        </th>
                        <th>
                            <div class="table-header-block">
                                <span><fmt:message key="flight.list_jsp.label.date"/></span>
                                <div class="sort-arrows-block">
                                    <input type="submit" name="sortBy" value="date"
                                           class="orderBy"/> <input type="submit" name="sortBy"
                                                                    value="date DESC" class="orderByDesc"/>
                                </div>
                            </div>
                        </th>
                        <th>
                            <div class="table-header-block">
                                <span><fmt:message key="flight.list_jsp.label.depart"/></span>
                                <div class="sort-arrows-block">
                                    <input type="submit" name="sortBy" value="departure_point"
                                           class="orderBy"/> <input type="submit" name="sortBy"
                                                                    value="departure_point DESC" class="orderByDesc"/>
                                </div>
                            </div>
                        </th>
                        <th>
                            <div class="table-header-block">
                                <span><fmt:message key="flight.list_jsp.label.arrival"/></span>
                                <div class="sort-arrows-block">
                                    <input type="submit" name="sortBy" value="arrival_point"
                                           class="orderBy"/> <input type="submit" name="sortBy"
                                                                    value="arrival_point DESC" class="orderByDesc"/>
                                </div>
                            </div>
                        </th>
                        <th>
                            <div class="table-header-block">
										<span><fmt:message
                                                key="flight.list_jsp.label.driver_name"/></span>
                                <div class="sort-arrows-block">
                                    <input type="submit" name="sortBy" value="first_name"
                                           class="orderBy"/> <input type="submit" name="sortBy"
                                                                    value="first_name DESC" class="orderByDesc"/>
                                </div>
                            </div>
                        </th>
                        <th>
                            <div class="table-header-block">
										<span><fmt:message
                                                key="flight.list_jsp.label.car_model"/></span>
                                <div class="sort-arrows-block">
                                    <input type="submit" name="sortBy" value="model"
                                           class="orderBy"/> <input type="submit" name="sortBy"
                                                                    value="model DESC" class="orderByDesc"/>
                                </div>
                            </div>
                        </th>
                        <th>
                            <div class="table-header-block">
                                <span><fmt:message key="flight.list_jsp.label.status"/></span>
                                <div class="sort-arrows-block">
                                    <input type="submit" name="sortBy" value="status"
                                           class="orderBy"/> <input type="submit" name="sortBy"
                                                                    value="status DESC" class="orderByDesc"/>
                                </div>
                            </div>
                        </th>
                        <th>
                            <div class="table-header-block action-label"><fmt:message key="label.action"/></div>
                        </th>


                    </tr>
                    <thead>

                    <c:forEach items="${listFlight}" var="flight">
<%--                    <c:if test="${flight.driverId == user.id}">--%>
                    <tr>
                        <td>${flight.id}</td>
                        <td>${flight.name}</td>
                        <td>${flight.date}</td>
                        <td>${flight.arrival}</td>
                        <td>${flight.depart}</td>
                        <td>${flight.driverName}</td>
                        <td>${flight.carModel}</td>
                        <td>${flight.status}</td>
                        <td><a class="btn-finished"
                               href="controller?command=finishedFlight&id=${flight.id}"><fmt:message
                                key="driver.driver_list_auto_flights_jsp.finished_flight"/></a>
                        </td>

                    </tr>
<%--                    </c:if>--%>
                    </c:forEach>
                </table>
            </form>


        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>

</body>
</html>