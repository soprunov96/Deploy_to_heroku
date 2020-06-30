<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Create flight" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">

    <c:set var="active_list_of_auto_flights" value="active" scope="page"/>

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>

            <h3 class="text-white"><fmt:message key="flight.create_flight_jsp.heading"/></h3>

            <p style="color: red;">${errorString}</p>

            <table>

                <form method="POST">
                    <input type="hidden" name="command" value="createFlight">
                    <input type="hidden" name="currentPage" value="${currentPage}">
                    <input type="hidden" name="recordsPerPage" value="${recordsPerPage}">
                    <div class="editFlight text-white bg-dark">
                        <input type="hidden" name="id" value="${id}"/>

                        <div class="form-row d-flex justify-content-around">

                            <div class="form-group col-md-5">
                                <label for="inputName"><fmt:message
                                        key="flight.list_jsp.label.name"/></label> <input type="text"
                                                                                          class="form-control"
                                                                                          value="${name}" id="inputName"
                                                                                          name="name" autofocus
                                                                                          required
                                                                                          pattern="[A-Za-zА-Яа-я-]*">
                            </div>
                            <div class="form-group col-md-5">
                                <label for="inputDate"><fmt:message
                                        key="flight.list_jsp.label.date"/></label> <input type="date"
                                                                                          name="date" value="${date}"
                                                                                          min="${localDate}"
                                                                                          id="inputDate" required
                                                                                          class="form-control">
                            </div>
                        </div>
                        <div class="form-row d-flex justify-content-around">
                            <div class="form-group col-md-5">
                                <label for="inputDepart"><fmt:message
                                        key="flight.list_jsp.label.depart"/></label> <input type="text"
                                                                                            name="departure_point"
                                                                                            value="${departure_point}"
                                                                                            id="inputDepart"
                                                                                            class="form-control "
                                                                                            required
                                                                                            pattern="[A-Za-zА-Яа-я]*">
                            </div>
                            <div class="form-group col-md-5">
                                <label for="inputArrival"><fmt:message
                                        key="flight.list_jsp.label.arrival"/></label> <input type="text"
                                                                                             class="form-control"
                                                                                             value="${arrival_point}"
                                                                                             name="arrival_point"
                                                                                             required id="inputArrival"
                                                                                             pattern="[A-Za-zА-Яа-я]*">
                            </div>
                        </div>

                        <div class="create-flight-btn">
                            <button type="submit" class="btn btn-primary flight-btn"><fmt:message
                                    key="label.create"/></button>
                            <a class="btn btn-light flight-btn text-primary"
                               href="controller?command=list_Auto_Flights" role="button"><fmt:message
                                    key="label.cancel"/></a>
                            <input class="btn btn-primary flight-btn" type="reset"
                                   value="<fmt:message key="label.reset" />">
                        </div>
                    </div>
                </form>
            </table>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>
</html>