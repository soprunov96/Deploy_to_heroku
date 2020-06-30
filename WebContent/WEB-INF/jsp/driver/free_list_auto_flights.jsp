<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value=" free list auto flights" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">

    <c:set var="active_free_list_of_auto_flights" value="active" scope="page"/>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>
            <div class="content-header">
                <h3 class="text-white">
                    <fmt:message key="driver.free_list_auto_flights_jsp.header_text"/>
                </h3>
                <form action="controller">
                    <input type="hidden" name="command" value="free_list_auto_flights">

                    <div class="form-group col-md-4 select-block">

                        <label for="recordsPerPage" class="text-white"><fmt:message
                                key="label.select_records_per-page"/></label>

                        <div class="dropdown dropleft">
                            <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownRecordsPerPage"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                ${recordsPerPage}
                            </button>
                            <div class="dropdown-menu" aria-labelledby="dropdownRecordsPerPage" id="recordsPerPage">
                                <a class="dropdown-item"
                                   href="controller?command=free_list_auto_flights&recordsPerPage=5">5</a>
                                <a class="dropdown-item"
                                   href="controller?command=free_list_auto_flights&recordsPerPage=10">10</a>
                                <a class="dropdown-item"
                                   href="controller?command=free_list_auto_flights&recordsPerPage=15">15</a>
                            </div>
                        </div>
                    </div>


                </form>
            </div>
            <p style="color: red;">${errorString}</p>

            <form action="controller" class="form-img">
                <input type="hidden" name="command"
                       value="free_list_auto_flights"> <input type="hidden"
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
                        <th> <div class="table-header-block action-label"><fmt:message key="label.action"/></div></th>


                    </tr>
                    <thead>

                    <c:forEach items="${listFlight}" var="flight">
                    <c:if test="${flight.requestId == 0 }">
                    <tr>
                        <td>${flight.id}</td>
                        <td>${flight.name}</td>
                        <td>${flight.date}</td>
                        <td>${flight.arrival}</td>
                        <td>${flight.depart}</td>
                        <td>${flight.driverName}</td>
                        <td>${flight.carModel}</td>
                        <td>${flight.status}</td>
                        <td><a class="btn-prepared"
                                href="controller?command=prepareApplication&id=${flight.id}">
                            <fmt:message key="driver.free_list_auto_flights_jsp.link.prepare_application"/></a></td>
                    </tr>
                    </c:if>
                    </c:forEach>
                </table>
            </form>
            <nav aria-label="Navigation for countries">
                <ul class="pagination">
                    <c:if test="${currentPage != 1}">
                        <c:out value="">one</c:out>
                        <li class="page-item"><a class="page-link"
                                                 href="controller?command=free_list_auto_flights&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}&sortBy=${sortBy}"><fmt:message
                                key="pagination.label.previous"/></a>
                        </li>
                    </c:if>

                    <c:forEach begin="1" end="${noOfPages}" var="i">
                        <c:out value="">two</c:out>
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <li class="page-item active"><a class="page-link">
                                        ${i} <span class="sr-only">(current)</span>
                                </a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link"
                                                         href="controller?command=free_list_auto_flights&recordsPerPage=${recordsPerPage}&currentPage=${i}&sortBy=${sortBy}">${i}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:if test="${currentPage lt noOfPages}">
                        <li class="page-item"><a class="page-link"
                                                 href="controller?command=free_list_auto_flights&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}&sortBy=${sortBy}"><fmt:message
                                key="pagination.label.next"/></a>
                        </li>
                    </c:if>
                </ul>
            </nav>


        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>

</body>
</html>