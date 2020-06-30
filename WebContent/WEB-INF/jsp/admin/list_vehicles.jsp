<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="List vehicles" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>


<body>
<table id="main-container">

    <c:set var="active_list_of_vehicles" value="active" scope="page"/>

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <div class="modal fade" id="deleteEntity" tabindex="-1"
                 role="dialog" aria-labelledby="vehicleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="vehicleModalLabel"><fmt:message
                                    key="admin_jsp.modal.delete_vehicle"/> <span class="id"></span></h5>

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
                                <input type="hidden" name="command" value="deleteVehicle">
                                <div class="myid">
                                    <input type="hidden" name="id" value="">
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>

            <%-- CONTENT --%>

            <h3 class="text-white"><fmt:message key="admin_jsp.label.list_vehicles"/></h3>

            <p style="color: red;">${errorString}</p>

            <table class="table table-hover table-dark">
                <thead>
                <tr>
                    <th>№</th>
                    <th><fmt:message key="admin_jsp.label.model"/></th>
                    <th><fmt:message key="admin_jsp.label.range"/></th>
                    <th><fmt:message key="admin_jsp.label.type"/></th>
                    <th><fmt:message key="admin_jsp.label.status"/></th>
                    <th>
                        <fmt:message key="admin_jsp.label.edit"/>
                    </th>
                    <th>
                        <fmt:message key="admin_jsp.label.delete"/>
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listVechicles}" var="vechicle">
                    <tr>
                        <td>${vechicle.id}</td>
                        <td>${vechicle.model}</td>
                        <td>${vechicle.range}</td>
                        <td>${vechicle.type}</td>
                        <td>${vechicle.status}</td>
                        <td>
                            <div class="action_icons"><a class=""
                                                         href="controller?command=editVehicle&id=${vechicle.id}"><i
                                    class="fa fa-edit"></i>
                            </a></div>
                        </td>
                        <td>
                            <button type="button" class="btn btn-link icon_delete"
                                    data-toggle="modal" data-target="#deleteEntity"
                                    name="idToDelete"
                                    data-id="${vechicle.id}"><i class="fa fa-trash"></i></button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <a class="btn-create" href="controller?command=createVehicle"><fmt:message
                    key="admin_jsp.link.create_new_vehicles"/></a>


        </td>
    </tr>


    <script src="${pageContext.request.contextPath}/script/deleteEntity.js"></script>


    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>
</html>