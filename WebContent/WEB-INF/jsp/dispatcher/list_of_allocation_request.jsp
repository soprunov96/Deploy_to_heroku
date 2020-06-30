<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="List of allocation request" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">

    <c:set var="list_allocation_request" value="active" scope="page"/>

    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <tr>
        <td class="content">
            <%-- CONTENT --%>

            <h3 class="text-white">
                <fmt:message key="dispatcher_jspf.label.list_of_allocation_requests"/>

            </h3>

            <p style="color: red;">${errorString}</p>

            <table class="table table-hover table-dark">

                <tr>
                    <th>№</th>
                    <th><fmt:message key="request.list_jsp.label.range"/></th>
                    <th><fmt:message key="request.list_jsp.label.type"/></th>
                    <th><fmt:message key="admin_jsp.label.login"/></th>
                    <th><fmt:message key="admin_jsp.label.first_name"/></th>
                    <th><fmt:message key="admin_jsp.label.last_name"/></th>


                </tr>

                <c:forEach items="${listRequest}" var="request">
                    <c:forEach items="${listUsers}" var="user">
                        <c:if test="${user.id == request.driverId}">
                            <tr>
                                <td>${request.id}</td>
                                <td>${request.range}</td>
                                <td>${request.carType}</td>
<%--                                <td>${request.driverId}</td>--%>
                                <td>${user.login}</td>
                                <td>${user.firstName}</td>
                                <td>${user.lastName}</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </c:forEach>
            </table>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>

</body>
</html>