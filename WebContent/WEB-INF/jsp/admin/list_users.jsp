<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ page import="ua.nure.soprunov.SummaryTask.Util.Role" %>
<html>

<c:set var="title" value="List users" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">

    <c:set var="active_list_of_users" value="active" scope="page"/>


    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>
            <!-- Modal -->
            <div class="modal fade" id="deleteEntity" tabindex="-1"
                 role="dialog" aria-labelledby="userModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="userModalLabel"><fmt:message
                                    key="admin_jsp.modal.delete_user"/> <span class="id"></span></h5>

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
                                <input type="hidden" name="command" value="deleteUser">
                                <div class="myid">
                                    <input type="hidden" name="id" value="">
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
            <!-- end  Modal -->
            <h3 class="text-white"><fmt:message key="admin_jsp.label.user_list"/></h3>

            <p style="color: red;">${errorString}</p>

            <table class="table table-hover table-dark">

                <tr>
                    <th>№</th>
                    <th><fmt:message key="admin_jsp.label.login"/></th>
                    <th><fmt:message key="admin_jsp.label.first_name"/></th>
                    <th><fmt:message key="admin_jsp.label.last_name"/></th>
                    <th><fmt:message key="admin_jsp.label.role_id"/></th>
                    <th>
                        <div class="table-header-block"><fmt:message key="flight.list_jsp.label.action"/></div>
                    </th>
                </tr>
                <c:forEach items="${listUsers}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.login}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${Role.showUserRole(user.roleId)}</td>
                        <td>
                            <div class="action_icons"><a class=""
                                                         href="controller?command=editUser&login=${user.login}"><i
                                    class="fa fa-edit"></i>
                            </a> /
                                <button type="button" class="btn btn-link icon_delete"
                                        data-toggle="modal" data-target="#deleteEntity"
                                        name="idToDelete"
                                        data-id="${user.id}"><i class="fa fa-trash"></i></button>
                            </div>
                        </td>

                    </tr>
                </c:forEach>
            </table>
            <a class="btn-create" href="controller?command=createUser"><fmt:message
                    key="admin_jsp.link.create_new_user"/></a>


        </td>
    </tr>
    <script src="${pageContext.request.contextPath}/script/deleteEntity.js"></script>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>
</html>