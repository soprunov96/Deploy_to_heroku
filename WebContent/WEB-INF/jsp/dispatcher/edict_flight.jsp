<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Edict flight" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">

    <c:set var="active_list_of_auto_flights" value="active" scope="page"/>

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>

            <h3 class="text-white">
                <fmt:message key="flight.edit_flight_jsp.heading"/>
            </h3>

            <p style="color: red;">${errorString}</p>


            <form method="POST" action="controller?command=editFlight">
                <div class="editFlight text-white bg-dark">
                    <input type="hidden" name="id" value="${id}"/>

                    <div class="form-row d-flex justify-content-around">
                        <div class="form-group col-md-2 ">
                            <label><fmt:message
                                    key="flight.edit_flight_jsp.label.flight_id"/></label> <input
                                type="text" class="form-control immutable-values" value="${id}"
                                readonly disabled >
                        </div>
                        <div class="form-group col-md-6">
                            <label for="inputName"><fmt:message
                                    key="flight.list_jsp.label.name"/></label> <input type="text"
                                                                                      class="form-control"
                                                                                      value="${name}" id="inputName"
                                                                                      name="name" autofocus
                                                                                      required pattern="[A-Za-zА-Яа-я-]*">
                        </div>
                        <div class="form-group col-md-3">
                            <label for="inputDate"><fmt:message
                                    key="flight.list_jsp.label.date"/></label> <input type="date"
                                                                                      name="date" value="${date}"
                                                                                      min="${localDate}"
                                                                                      id="inputDate" required
                                                                                      class="form-control" >
                        </div>
                    </div>
                    <div class="form-row d-flex justify-content-around">
                        <div class="form-group col-md-4">
                            <label for="inputDepart"><fmt:message
                                    key="flight.list_jsp.label.depart"/></label> <input type="text"
                                                                                        name="departure_point"
                                                                                        value="${departure_point}"
                                                                                        id="inputDepart"
                                                                                        class="form-control " required pattern="[A-Za-zА-Яа-я]*">
                        </div>
                        <div class="form-group col-md-4">
                            <label for="inputArrival"><fmt:message
                                    key="flight.list_jsp.label.arrival"/></label> <input type="text"
                                                                                         class="form-control"
                                                                                         value="${arrival_point}"
                                                                                         name="arrival_point" required
                                                                                         id="inputArrival" pattern="[A-Za-zА-Яа-я]*">
                        </div>
                        <div class="form-group col-md-3">
                            <label><fmt:message
                                    key="admin_jsp.label.status"/></label> <select id="status"
                                                                                   name="status" class="form-control"
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


                    <h5 class="text-primary">
                        <fmt:message key="flight.edit_flight_jsp.label.immutable_values"/>
                    </h5>


                    <div class="form-row d-flex justify-content-around">
                        <div class="form-group col-md-4 ">
                            <label><fmt:message
                                    key="flight.list_jsp.label.driver_name"/></label> <input type="text"
                                                                                             class="form-control immutable-values"
                                                                                             value="${driver_name}"
                                                                                             readonly disabled>
                        </div>
                        <div class="form-group col-md-4 ">
                            <label><fmt:message
                                    key="flight.list_jsp.label.car_model"/></label> <input type="text"
                                                                                           class="form-control immutable-values"
                                                                                           value="${model}" disabled>
                        </div>
                        <div class="form-group col-md-3 ">
                            <label><fmt:message
                                    key="flight.edit_flight_jsp.label.range"/></label> <input
                                type="text" class="form-control immutable-values"
                                value="${range}" disabled>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary flight-btn">
                        <fmt:message key="label.save"/>
                    </button>
                    <a class="btn btn-light text-primary flight-btn"
                       href="controller?command=list_Auto_Flights" role="button"><fmt:message
                            key="label.cancel"/></a>

                </div>
            </form>


        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</table>

</body>
</html>