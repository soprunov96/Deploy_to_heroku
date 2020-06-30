<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Edict vehicle" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">

    <c:set var="active_list_of_vehicles" value="active" scope="page"/>

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>

            <h3 class="text-white"><fmt:message key="vehicles.edit_vehicle_jsp.heading"/></h3>

            <p style="color: red;">${errorString}</p>

            <form method="POST" action="controller?command=editVehicle">
                <div class="editFlight text-white bg-dark">
                    <input type="hidden" name="id" value="${id}"/>

                    <div class="form-row d-flex justify-content-around">
                        <div class="form-group col-md-5 ">
                            <label><fmt:message key="vehicle.create_vehicle_jsp.label.vehicleId"/></label> <input
                                type="text"
                                class="form-control immutable-values" value="${id}" readonly
                                disabled>
                        </div>
                        <div class="form-group col-md-5">
                            <label for="inputModel"><fmt:message key="vehicle.create_vehicle_jsp.label.model"/></label>
                            <input type="text"
                                   class="form-control" value="${model}" name="model" id="inputModel" autofocus
                                   pattern="[A-Za-zА-Яа-я-\s0-9]*"
                                   required>
                        </div>

                    </div>
                    <div class="form-row d-flex justify-content-around">
                        <div class="form-group col-md-5">
                            <label for="inputType"><fmt:message key="vehicle.create_vehicle_jsp.label.type"/></label>
                            <input type="text"
                                   name="type" value="${type}" id="inputType" class="form-control "
                                   pattern="[A-Za-zА-Яа-я]*"
                                   required>
                        </div>
                        <div class="form-group col-md-5">
                            <label for="inputRange"><fmt:message key="vehicle.create_vehicle_jsp.label.range"/></label>
                            <input type="number"
                                   name="range" value="${range}" id="inputRange" required class="form-control">
                        </div>
                    </div>
                    <div class="form-row d-flex justify-content-around">
                        <div class="form-group col-md-5">
                            <label for="status"><fmt:message key="vehicle.create_vehicle_jsp.label.status"/></label>
                            <select id="status"
                                    name="status" class="form-control" required>
                                <option value="ready"
                                ${status.equals('ready')? 'selected' : '' }>ready
                                </option>
                                <option value="needs repair"
                                ${status.equals('needs repair')? 'selected' : '' }>needs
                                    repair
                                </option>
                            </select>
                        </div>
                        <div class="form-group col-md-5"></div>

                    </div>
                    <div class="form-btns">
                        <button type="submit" class="btn btn-primary flight-btn"><fmt:message
                                key="label.save"/></button>
                        <a class="btn btn-light text-primary flight-btn"
                           href="controller?command=list_of_vehical" role="button"><fmt:message key="label.cancel"/></a>
                    </div>
                </div>
            </form>

    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>
</html>