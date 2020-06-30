<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Filed applications" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">

    <c:set var="active_field_list_applications" value="active" scope="page"/>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>

            <!-- Modal -->
            <div class="modal fade" id="deleteEntity" tabindex="-1"
                 role="dialog" aria-labelledby="applicationModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="applicationModalLabel"><fmt:message
                                    key="driver_jsp.modal.cancelApplication"/> <span class="id"></span></h5>

                            <button type="button" class="close" data-dismiss="modal"
                                    aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary"
                                    data-dismiss="modal"><fmt:message
                                    key="dispatcher.list_auto_flights_jsp.modal.cancel"/></button>
                            <form action="controller">
                                <button type="submit" class="btn btn-primary"><fmt:message
                                        key="dispatcher.list_auto_flights_jsp.modal.confirm"/></button>
                                <input type="hidden" name="command" value="cancelApplication">
                                <div class="myid">
                                    <input type="hidden" name="id" value="">
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
            <!-- end  Modal -->


            <div class="content-header">
                <h3 class="text-white"><fmt:message key="driver.field_applications_jsp.header_text"/></h3>

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

                            </div>
                        </th>

                        <th>
                            <div class="table-header-block">
                                <span><fmt:message key="flight.list_jsp.label.name"/></span>

                            </div>
                        </th>
                        <th>
                            <div class="table-header-block">
                                <span><fmt:message key="flight.list_jsp.label.date"/></span>

                            </div>
                        </th>
                        <th>
                            <div class="table-header-block">
                                <span><fmt:message key="flight.list_jsp.label.depart"/></span>

                            </div>
                        </th>
                        <th>
                            <div class="table-header-block">
                                <span><fmt:message key="flight.list_jsp.label.arrival"/></span>

                            </div>
                        </th>
                        <th>
                            <div class="table-header-block">
										<span><fmt:message
                                                key="flight.list_jsp.label.driver_name"/></span>

                            </div>
                        </th>
                        <th>
                            <div class="table-header-block">
										<span><fmt:message
                                                key="flight.list_jsp.label.car_model"/></span>
                            </div>
                        </th>
                        <th>
                            <div class="table-header-block">
                                <span><fmt:message key="flight.list_jsp.label.status"/></span>

                            </div>
                        </th>
                        <th>
                            <div class="table-header-block">
                                <span><fmt:message key="flight.list_jsp.label.request_id"/></span>

                            </div>
                        </th>
                        <th>
                            <div class="table-header-block">
                                <span> <fmt:message key="label.action"/></span>
                            </div>
                        </th>


                    </tr>
                    <thead>

                    <c:forEach items="${listFlight}" var="flight">
                    <c:forEach items="${listRequest}" var="request">

                    <c:if test="${(request.id == flight.requestId)and (request.driverId == user.id)}">
                    <tr>

                        <td>${flight.id}</td>
                        <td>${flight.name}</td>
                        <td>${flight.date}</td>
                        <td>${flight.arrival}</td>
                        <td>${flight.depart}</td>
                        <td>${flight.driverName}</td>
                        <td>${flight.carModel}</td>
                        <td>${flight.status}</td>
                        <td>${flight.requestId}</td>
                        <td>
                            <button type="button" class="btn btn-link icon_delete"
                                    data-toggle="modal" data-target="#deleteEntity"
                                    name="idToDelete"
                                    data-id="${flight.requestId}"><i class="fa fa-trash"></i></button>
                        </td>

                    </tr>

                    </c:if>

                    </c:forEach>
                    </c:forEach>
                    <h3 class="text-white"></h3>
                </table>

            </form>

        </td>
    </tr>
    <script src="${pageContext.request.contextPath}/script/deleteEntity.js"></script>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>

</body>
</html>