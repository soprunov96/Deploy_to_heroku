<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Create vehicle" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">

    <c:set var="active_list_of_vehicles" value="active" scope="page"/>

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>

            <h3 class="text-white"><fmt:message key="vehicles.create_vehicle_jsp.heading"/></h3>

            <p style="color: red;">${errorString}</p>
            <form method="POST" autocomplete="off">
                <input type="hidden" name="command" value="createVehicle">
                <input type="hidden" name="currentPage" value="${currentPage}">
                <input type="hidden" name="recordsPerPage"
                       value="${recordsPerPage}"> <input type="hidden" name="id"
                                                         value="${id}"/>
                <div class="editFlight text-white bg-dark">
                    <input type="hidden" name="id" value="${id}"/>

                    <div class="form-row d-flex justify-content-around">

                        <div class="form-group col-md-5 autocomplete">
                            <label for="inputModel"><fmt:message key="vehicle.create_vehicle_jsp.label.model"/></label>
                            <input type="text"
                                   class="form-control" value="${model}" id="inputModel" name="model"
                                   placeholder="Model" autofocus
                                   pattern="[A-Za-zА-Яа-я-\s0-9]*"
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
                            <label for="inputType"><fmt:message key="vehicle.create_vehicle_jsp.label.type"/></label>
                            <input type="text"
                                   name="type" value="${type}" id="inputType" required class="form-control"
                                   pattern="[A-Za-zА-Яа-я]*"
                                   required>
                        </div>
                        <div class="form-group col-md-5">
                            <label for="status"><fmt:message key="vehicle.create_vehicle_jsp.label.status"/></label>
                            <select id="status"
                                    name="status" class="form-control" required>
                                <option value="ready">ready</option>
                                <option value="needs repair">needs repair</option>

                            </select>
                        </div>
                    </div>

                    <div class="form-btns">
                        <button type="submit" class="btn btn-primary flight-btn"><fmt:message
                                key="label.create"/></button>
                        <a class="btn btn-light flight-btn text-primary"
                           href="controller?command=list_of_vehical" role="button"><fmt:message key="label.cancel"/></a>
                        <input class="btn btn-primary flight-btn" type="reset"
                               value="<fmt:message key="label.reset" />">
                    </div>
                </div>
            </form>
    </tr>

    <script src="${pageContext.request.contextPath}/script/autocompleteModel.js"></script>


    <%@ include file="/WEB-INF/jspf/footer.jspf" %>


</table>
</body>
</html>