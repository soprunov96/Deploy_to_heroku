<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Finished flight" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">

    <c:set var="active_driver_list_auto_flights" value="active" scope="page"/>

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>

            <h3 class="text-white"><fmt:message key="driver.finished_flight_jsp.header_text"/></h3>

            <p style="color: red;">${errorString}</p>


            <form method="POST" action="controller?command=finishedFlight">

                <input type="hidden" name="command" value="editStation"> <input
                    type="hidden" name="id" value="${id}"/> <input type="hidden"
                                                                   name="vehicle_id" value="${vehicle_id}"/>

                <div class="editFlight text-white bg-dark">
                    <input type="hidden" name="id" value="${id}"/>

                    <div class="form-row d-flex justify-content-around">
                        <div class="form-group col-md-4 ">
                            <label><fmt:message
                                    key="driver.finished_flight_jsp.label.flight_id"/></label> <input type="text"
                                                                                                      class="form-control immutable-values"
                                                                                                      value="${id}"
                                                                                                      readonly
                                                                                                      disabled>
                        </div>
                        <div class="form-group col-md-4">
                            <label><fmt:message
                                    key="driver.finished_flight_jsp.label.flight_name"/></label> <input
                                type="text" class="form-control immutable-values"
                                value="${name}" name="name" disabled>
                        </div>
                        <div class="form-group col-md-3">
                            <label><fmt:message
                                    key="driver.finished_flight_jsp.label.flight_date"/></label> <input type="date"
                                                                                                        name="date"
                                                                                                        value="${date}"
                                                                                                        required
                                                                                                        class="form-control immutable-values"
                                                                                                        disabled>
                        </div>
                    </div>
                    <div class="form-row d-flex justify-content-around">
                        <div class="form-group col-md-4">
                            <label><fmt:message
                                    key="driver.finished_flight_jsp.label.flight_depart"/></label> <input type="text"
                                                                                                          name="departure_point"
                                                                                                          value="${departure_point}"
                                                                                                          required
                                                                                                          class="form-control immutable-values "
                                                                                                          disabled>
                        </div>
                        <div class="form-group col-md-4">
                            <label><fmt:message
                                    key="driver.finished_flight_jsp.label.flight_arrival"/></label> <input type="text"
                                                                                                           class="form-control immutable-values"
                                                                                                           value="${arrival_point}"
                                                                                                           name="arrival_point"
                                                                                                           disabled>
                        </div>
                        <div class="form-group col-md-3">
                            <label><fmt:message
                                    key="driver.finished_flight_jsp.label.flight_status"/></label> <select
                                id="FlightStatus"
                                name="status"
                                class="form-control"
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
                    <div class="form-row d-flex justify-content-around">
                        <div class="form-group col-md-4 ">
                            <label><fmt:message
                                    key="driver.finished_flight_jsp.label.flight_driver_name"/></label> <input
                                type="text"
                                class="form-control immutable-values" value="${driver_name}"
                                readonly disabled>
                        </div>
                        <div class="form-group col-md-4 ">
                            <label><fmt:message
                                    key="driver.finished_flight_jsp.label.car_id"/></label> <input type="text"
                                                                                                   class="form-control immutable-values"
                                                                                                   value="${vehicle_id}"
                                                                                                   disabled>
                        </div>
                        <div class="form-group col-md-3 ">
                            <label><fmt:message
                                    key="driver.finished_flight_jsp.label.car_model"/></label> <input type="text"
                                                                                                      class="form-control immutable-values"
                                                                                                      value="${model}"
                                                                                                      disabled>
                        </div>
                    </div>
                    <div class="form-row d-flex justify-content-around">
                        <div class="form-group col-md-4 ">
                            <label><fmt:message
                                    key="driver.finished_flight_jsp.label.car_range"/></label> <input type="text"
                                                                                                      class="form-control immutable-values"
                                                                                                      value="${range}"
                                                                                                      readonly
                                                                                                      disabled>
                        </div>
                        <div class="form-group col-md-4 ">
                            <label><fmt:message
                                    key="driver.finished_flight_jsp.label.car_type"/></label> <input type="text"
                                                                                                     class="form-control immutable-values"
                                                                                                     value="${type}"
                                                                                                     disabled>
                        </div>
                        <div class="form-group col-md-3">
                            <label><fmt:message
                                    key="driver.finished_flight_jsp.label.car_status"/></label> <select id="status"
                                                                                                        name="carStatus"
                                                                                                        class="form-control"
                                                                                                        required>
                            <option value="needs repair"
                            ${carStatus.equals('needs repair')? 'selected' : '' }>needs
                                repair
                            </option>
                            <option value="ready"
                            ${carStatus.equals('ready')? 'selected' : '' }>ready
                            </option>

                        </select>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary flight-btn"><fmt:message key="label.save"/></button>
                    <a class="btn btn-light text-primary flight-btn"
                       href="controller?command=driver_list_auto_flights" role="button"><fmt:message
                            key="label.cancel"/></a>

                </div>
            </form>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>
</html>