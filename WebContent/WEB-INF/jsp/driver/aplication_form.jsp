<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Aplication form" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">

    <c:set var="active_free_list_of_auto_flights" value="active" scope="page"/>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>

            <h3 class="text-white"><fmt:message key="driver.application_form_jsp.header_text"/></h3>

            <p style="color: red; ">${errorString}</p>

            <form method="POST" action="controller?command=prepareApplication">

                <input type="hidden" id="id" name="user_id" value="${user.id}"/>
                <div class="editFlight text-white bg-dark">
                    <input type="hidden" name="id" value="${id}"/>

                    <div class="form-row d-flex justify-content-around">
                        <div class="form-group col-md-4 ">
                            <label><fmt:message
                                    key="driver.application_form_jsp.label.flight_id"/></label>
                            <input type="text"
                                   class="form-control immutable-values"
                                   value="${id}"
                                   readonly
                                   disabled>
                        </div>
                        <div class="form-group col-md-4">
                            <label><fmt:message
                                    key="driver.application_form_jsp.label.flight_name"/></label>
                            <input
                                    type="text" class="form-control immutable-values"
                                    value="${name}" name="name" disabled>
                        </div>
                        <div class="form-group col-md-3">
                            <label><fmt:message
                                    key="driver.application_form_jsp.label.flight_date"/></label>
                            <input type="date"
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
                                    key="driver.application_form_jsp.label.flight_depart"/></label>
                            <input type="text"
                                   name="departure_point"
                                   value="${departure_point}"
                                   required
                                   class="form-control immutable-values "
                                   disabled>
                        </div>
                        <div class="form-group col-md-4">
                            <label><fmt:message
                                    key="driver.application_form_jsp.label.flight_arrival"/></label>
                            <input type="text"
                                   class="form-control immutable-values"
                                   value="${arrival_point}"
                                   name="arrival_point"
                                   disabled>
                        </div>
                        <div class="form-group col-md-3">
                            <label><fmt:message
                                    key="driver.application_form_jsp.label.flight_status"/></label>
                            <input type="text"
                                   class="form-control immutable-values"
                                   value="${status}"
                                   name="arrival_point"
                                   disabled>
                        </div>
                    </div>
                    <h5 class="text-primary"><fmt:message key="driver.application_form_jsp.label.requirement"/></h5>
                    <div class="form-row d-flex justify-content-around">

                        <div class="form-group col-md-5 ">
                            <label for="inputRange"><fmt:message
                                    key="driver.application_form_jsp.label.car_range"/></label>
                            <input type="number" id="inputRange"
                                   class="form-control "
                                   value="${range}"
                                   name="range"
                                   required>
                        </div>
                        <div class="form-group col-md-5 ">
                            <label for="inputType"><fmt:message
                                    key="driver.application_form_jsp.label.car_type"/></label>
                            <input type="text" id="inputType"
                                   class="form-control "
                                   name="type"
                                   value="${type}"
                                   pattern="[A-Za-zА-Яа-я]*"
                                   required>
                        </div>
                    </div>

                    <button type="submit" class="btn btn-primary flight-btn"><fmt:message key="label.save"/></button>
                    <a class="btn btn-light text-primary flight-btn"
                       href="controller?command=free_list_auto_flights" role="button"><fmt:message
                            key="label.cancel"/></a>

                </div>

            </form>

        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>
</html>