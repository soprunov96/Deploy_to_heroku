<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Consider request" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">

    <c:set var="active_list_of_auto_flights" value="active" scope="page"/>

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">


            <h3 class="text-white">
                <fmt:message
                        key="dispatcher.consider_request_jsp.text.consider_request"/>
            </h3>

            <p style="color: red;">${errorString}</p>
            <
            <form action="controller" method="POST">
                <input type="hidden" name="command" value="considerRequest">
                <div class="editFlight text-white bg-dark">
                    <input type="hidden" name="id" value="${id}"/>

                    <div class="form-row d-flex justify-content-around">
                        <div class="form-group col-md-2 ">
                            <label><fmt:message key="dispatcher.consider_request_jsp.label.flight_id"/></label> <input
                                type="text"
                                class="form-control immutable-values" value="${id}" readonly
                                disabled>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="inputName"><fmt:message
                                    key="flight.list_jsp.label.name"/></label> <input type="text"
                                                                                      class="form-control"
                                                                                      value="${name}" id="inputName"
                                                                                      name="name" autofocus
                                                                                      pattern="[A-Za-zА-Яа-я-]*"
                                                                                      required>
                        </div>
                        <div class="form-group col-md-3">
                            <label for="inputDate"><fmt:message
                                    key="flight.list_jsp.label.date"/></label> <input type="date"
                                                                                      name="date" value="${date}"
                                                                                      min="${localDate}"
                                                                                      id="inputDate" required
                                                                                      class="form-control">
                        </div>
                    </div>
                    <div class="form-row d-flex justify-content-around">
                        <div class="form-group col-md-4">
                            <label for="inputDepart"><fmt:message
                                    key="flight.list_jsp.label.depart"/></label> <input type="text"
                                                                                        name="departure_point"
                                                                                        value="${departure_point}"
                                                                                        id="inputDepart"
                                                                                        class="form-control "
                                                                                        pattern="[A-Za-zА-Яа-я]*"
                                                                                        required>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="inputArrival"><fmt:message
                                    key="flight.list_jsp.label.arrival"/></label> <input type="text"
                                                                                         class="form-control"
                                                                                         value="${arrival_point}"
                                                                                         name="arrival_point"
                                                                                         pattern="[A-Za-zА-Яа-я]*"
                                                                                         required
                                                                                         id="inputArrival">
                        </div>
                        <div class="form-group col-md-3">
                            <label><fmt:message
                                    key="flight.list_jsp.label.status"/></label> <select id="status"
                                                                                         name="status"
                                                                                         class="form-control"
                                                                                         pattern="[A-Za-zА-Яа-я]*"
                                                                                         required>
                            <option value="open" ${status.equals('open')? 'selected' : '' }>open</option>
                            <option value="close"
                            ${status.equals('close')? 'selected' : '' }>close
                            </option>
                            <option value="in progress"
                            ${status.equals('in progress')? 'selected' : '' }>in
                                progress
                            </option>
                            <option value="canceled"
                            ${status.equals('canceled')? 'selected' : '' }>canceled
                            </option>
                        </select>
                        </div>
                    </div>
                    <h5 class="text-primary"><fmt:message
                            key="dispatcher.consider_request_jsp.label.request_values"/></h5>
                    <div class="form-row d-flex justify-content-around">
                        <div class="form-group col-md-4 ">
                            <label><fmt:message
                                    key="flight.list_jsp.label.driver_name"/></label> <input type="text"
                                                                                             class="form-control immutable-values"
                                                                                             value="${driver_name}"
                                                                                             readonly disabled>
                        </div>
                        <div class="form-group col-md-4 ">
                            <label><fmt:message key="dispatcher.consider_request_jsp.label.type"/></label> <input
                                type="text"
                                class="form-control immutable-values" value="${type}" disabled>
                        </div>
                        <div class="form-group col-md-3 ">
                            <label><fmt:message key="dispatcher.consider_request_jsp.label.range"/></label> <input
                                type="text"
                                class="form-control immutable-values" value="${range}" disabled>
                        </div>
                    </div>

                </div>


                <input type="hidden" name="driver_id" value="${driver_id}">
                <input type="hidden" name="id" value="${id}">
                <table class="table table-hover table-dark">
                    <tr>
                        <th><fmt:message key="dispatcher.consider_request_jsp.label.vehicle_id"/></th>
                        <th><fmt:message key="dispatcher.consider_request_jsp.label.model"/></th>
                        <th><fmt:message key="dispatcher.consider_request_jsp.label.range"/></th>
                        <th><fmt:message key="dispatcher.consider_request_jsp.label.type"/></th>
                        <th><fmt:message key="dispatcher.consider_request_jsp.label.status"/></th>
                        <th><fmt:message key="dispatcher.consider_request_jsp.label.choose"/></th>
                    </tr>
                    <c:forEach items="${listVechicles}" var="vechicle">
                        <tr>
                            <td>${vechicle.id}</td>
                            <td>${vechicle.model}</td>
                            <td>${vechicle.range}</td>
                            <td>${vechicle.type}</td>
                            <td>${vechicle.status}</td>

                            <td><label class="radio-buttons">
                                <input type="radio" name="vehicle_id" value="${vechicle.id}"  ${(vechicle.id).equals(checkVehicle)? 'checked' : '' } required>
                                <span class="checkmark"></span>
                            </label></td>
                        </tr>
                    </c:forEach>

                </table>

                <nav aria-label="Navigation for vehicles">
                    <ul class="pagination">
                        <c:if test="${currentPage != 1}">
                            <c:out value="">one</c:out>
                            <li class="page-item"><a class="page-link"
                                                     href="controller?command=considerRequest&request_id=${request_id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}&sortBy=${sortBy}"><fmt:message
                                    key="pagination.label.previous"/></a></li>
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
                                                             href="controller?command=considerRequest&request_id=${request_id}&recordsPerPage=${recordsPerPage}&currentPage=${i}&sortBy=${sortBy}">${i}</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <c:if test="${currentPage lt noOfPages}">
                            <li class="page-item"><a class="page-link"
                                                     href="controller?command=considerRequest&request_id=${request_id}&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}&sortBy=${sortBy}"><fmt:message
                                    key="pagination.label.next"/></a></li>
                        </c:if>
                    </ul>
                </nav>


                <button type="submit" class="btn btn-primary flight-btn">
                    <fmt:message key="flight.list_jsp.label.consider"/>
                </button>
                <a class="btn btn-light text-primary flight-btn"
                   href="controller?command=list_Auto_Flights"><fmt:message
                        key="label.cancel"/></a>
            </form>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>
</html>