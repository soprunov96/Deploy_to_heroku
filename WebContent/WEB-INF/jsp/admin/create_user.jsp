<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Create user" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<%@ taglib uri="/WEB-INF/customtag" prefix="customTag" %>

<body>
<table id="main-container">

    <c:set var="active_list_of_users" value="active" scope="page"/>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>

            <h3 class="text-white"><fmt:message key="admin.create_user_jsp.heading"/></h3>

            <p style="color: red;">${errorString}</p>

            <form method="POST" class="needs-validation" novalidate>
                <input type="hidden" name="command" value="createUser"> <input
                    type="hidden" name="currentPage" value="${currentPage}">
                <input type="hidden" name="recordsPerPage"
                       value="${recordsPerPage}"> <input type="hidden" name="id"
                                                         value="${id}"/>
                <div class="editFlight text-white bg-dark">


                    <div class="form-row d-flex justify-content-around">

                        <div class="form-group col-md-5 create-user-form ">
                            <label for="input_login"><fmt:message key="admin_jsp.label.login"/></label> <input
                                type="text"
                                class="form-control " id="input_login" value="${login}" name="login"
                                onblur="checkExist()" autofocus
                                pattern="[A-Za-zА-Яа-я]*"
                                required>
                            <div class="tooltip-show">
                                <span id="user_tooltip_massage" class="invalid-tooltip tooltip-show"><fmt:message
                                        key="admin.create_user_jsp.invalid_tooltip"/></span>
                            </div>

                        </div>
                        <div class="form-group col-md-5 create-user-form" id="needs_validation_password">
                            <label for="input_password"><fmt:message key="admin_jsp.label.password"/></label> <input
                                type="password" name="password" id="input_password" value="${password}" required
                                pattern="[A-Za-zА-Яа-я0-9]*"
                                class="form-control">
                            <div class="valid-tooltip">
                                <fmt:message key="admin.create_user_jsp.valid_tooltip"/>
                            </div>
                            <div class="invalid-tooltip">
                                <fmt:message key="admin.create_user_jsp.invalid_tooltip_password"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-row d-flex justify-content-around">
                        <div class="form-group col-md-5 create-user-form" id="needs_validation_first_name">
                            <label for="input_first_name"><fmt:message key="admin_jsp.label.first_name"/></label> <input
                                type="text" name="first_name" id="input_first_name" value="${first_name}"
                                class="form-control" required
                                pattern="[A-Za-zА-Яа-я]*">
                            <div class="valid-tooltip">
                                <fmt:message key="admin.create_user_jsp.valid_tooltip"/>
                            </div>
                            <div class="invalid-tooltip">
                                <fmt:message key="admin.create_user_jsp.invalid_tooltip"/>
                            </div>
                        </div>
                        <div class="form-group col-md-5 create-user-form" id="needs_validation_last_name">
                            <label for="input_last_name"><fmt:message key="admin_jsp.label.last_name"/></label> <input
                                type="text" name="last_name" id="input_last_name" value="${last_name}"
                                class="form-control" required pattern="[A-Za-zА-Яа-я]*">
                            <div class="valid-tooltip">
                                <fmt:message key="admin.create_user_jsp.valid_tooltip"/>
                            </div>
                            <div class="invalid-tooltip">
                                <fmt:message key="admin.create_user_jsp.invalid_tooltip"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-row d-flex justify-content-around">
                        <div class="form-group col-md-5 create-user-form" id="needs_validation_role">
                            <label for="input_role_id"><fmt:message key="admin_jsp.label.role_id"/></label> <select
                                id="role_id"
                                name="role_id" id="input_role_id" class="form-control" required>
                            <option value="0">admin</option>
                            <option value="1">dispatcher</option>
                            <option value="2">driver</option>

                        </select>
                            <div class="valid-tooltip">
                                <fmt:message key="admin.create_user_jsp.valid_tooltip"/>
                            </div>
                            <div class="invalid-tooltip">
                                <fmt:message key="admin.create_user_jsp.invalid_tooltip"/>
                            </div>
                        </div>
                        <div class="form-group col-md-5 create-user-form">
                        </div>
                    </div>
                    <div class="form-btns">
                        <button type="submit" id="createBtn" class="btn btn-primary flight-btn"><fmt:message
                                key="label.create"/></button>
                        <a class="btn btn-light flight-btn text-primary"
                           href="controller?command=list_of_users" role="button"><fmt:message key="label.cancel"/></a>
                        <input class="btn btn-primary flight-btn" type="reset"
                               value="<fmt:message key="label.reset" />">
                    </div>
                </div>
            </form>

            <script src="${pageContext.request.contextPath}/script/validateCreateUser.js">
            </script>
            <script>function checkExist() {
                var xmlhttp = new XMLHttpRequest();
                var inputLogin = document.getElementById("input_login");
                var url = "check_user_exists.jsp?username=" + inputLogin.value;
                var login = document.getElementById("user_tooltip_massage");

                xmlhttp.onreadystatechange = function () {
                    if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {

                        var answer = xmlhttp.responseText.replace(/[\r\n]/g, '');

                        if (answer === "User already exists") {
                            console.log(xmlhttp.responseText);
                            login.classList.remove("valid-tooltip");
                            login.classList.add("invalid-tooltip");
                            document.getElementById("user_tooltip_massage").innerHTML = '<fmt:message key="admin.create_user_jsp.user_invalid"/>';
                            document.getElementById('createBtn').setAttribute("disabled", "true");
                            inputLogin.classList.remove("valid-input");
                            inputLogin.classList.add("invalid-input");
                        } else {
                            document.getElementById("user_tooltip_massage").innerHTML = '<fmt:message key="admin.create_user_jsp.user_valid"/>';
                            login.classList.remove("invalid-tooltip");
                            login.classList.add("valid-tooltip");
                            document.getElementById('createBtn').removeAttribute("disabled");
                            inputLogin.classList.remove("invalid-input");
                            inputLogin.classList.add("valid-input");
                            if (inputLogin.value.length === 0) {
                                login.classList.remove("valid-tooltip");
                                login.classList.add("invalid-tooltip");
                                document.getElementById("user_tooltip_massage").innerHTML = '<fmt:message key="admin.create_user_jsp.invalid_tooltip"/>';
                                document.getElementById('createBtn').setAttribute("disabled", "true");
                                inputLogin.classList.remove("valid-input");
                                inputLogin.classList.add("invalid-input");
                            }
                        }

                    }
                };
                try {
                    xmlhttp.open("GET", url, true);
                    xmlhttp.send();
                } catch (e) {
                    alert("unable to connect to server");
                }

            }</script>
</table>
</tr>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>
</html>