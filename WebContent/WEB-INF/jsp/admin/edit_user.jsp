<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Edict user" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">

    <c:set var="active_list_of_users" value="active" scope="page"/>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>

            <h3 class="text-white">
                <fmt:message key="admin.edit_user_jsp.heading"/>
            </h3>

            <p style="color: red;">${errorString}</p>

            <form method="POST" action="controller?command=editUser">
                <div class="editFlight text-white bg-dark">
                    <input type="hidden" name="id" value="${id}"/>

                    <div class="form-row d-flex justify-content-around">
                        <div class="form-group col-md-5 ">
                            <label><fmt:message
                                    key="admin_jsp.label.edit_user"/></label> <input type="text"
                                                                                     class="form-control immutable-values"
                                                                                     value="${id}" readonly
                                                                                     disabled>
                        </div>
                        <div class="form-group col-md-5">
                            <label for="input_login"><fmt:message
                                    key="admin_jsp.label.login"/></label> <input type="text"
                                                                                 class="form-control immutable-values" id="input_login"
                                                                                 value="${login}"
                                                                                 name="login"  disabled pattern="[A-Za-zА-Яа-я]*">
                        </div>

                    </div>
                    <div class="form-row d-flex justify-content-around">
                        <div class="form-group col-md-5">
                            <label for="input_p]assword"><fmt:message
                                    key="admin_jsp.label.password"/></label> <input type="password"
                                                                                    name="password" id="input_p]assword"
                                                                                    value="${password}"
                                                                                    required autofocus class="form-control" pattern="[A-Za-zА-Яа-я0-9]*">
                        </div>
                        <div class="form-group col-md-5">
                            <label for="input_first_name"><fmt:message
                                    key="admin_jsp.label.first_name"/></label> <input type="text"
                                                                                      name="first_name"
                                                                                      id="input_first_name"
                                                                                      value="${first_name}"
                                                                                       class="form-control"
                                                                                      required pattern="[A-Za-zА-Яа-я]*">
                        </div>
                    </div>
                    <div class="form-row d-flex justify-content-around">
                        <div class="form-group col-md-5">
                            <label for="input_last_name"><fmt:message
                                    key="admin_jsp.label.last_name"/></label> <input type="text"
                                                                                     name="last_name"
                                                                                     id="input_last_name"
                                                                                     value="${last_name}"
                                                                                      class="form-control"
                                                                                     required pattern="[A-Za-zА-Яа-я]*">
                        </div>

                        <div class="form-group col-md-5">
                            <label><fmt:message
                                    key="admin_jsp.label.role_id"/></label> <select id="role_id"
                                                                                    name="role_id" class="form-control"
                                                                                    required>
                            <option value="0" ${role_id == 0? 'selected' : '' }>admin</option>
                            <option value="1" ${role_id == 1 ? 'selected' : '' }>dispatcher</option>
                            <option value="2" ${role_id.equals('2')? 'selected' : '' }>driver</option>
                        </select>
                        </div>
                    </div>
                    <div class="form-btns">
                        <button type="submit" class="btn btn-primary flight-btn">
                            <fmt:message key="label.save"/>
                        </button>
                        <a class="btn btn-light text-primary flight-btn"
                           href="controller?command=list_of_users" role="button"><fmt:message
                                key="label.cancel"/></a>
                    </div>
                </div>
            </form>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>
</html>