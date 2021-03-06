<%@ page isErrorPage="true" %>
<%@ page import="java.io.PrintWriter" %>
<%@ include file="/WebContent/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WebContent/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Error" scope="page" />
<%@ include file="/WebContent/WEB-INF/jspf/head.jspf" %>
	
<body>

	<table id="main-container">

		<%-- HEADER --%>
		<%@ include file="/WebContent/WEB-INF/jspf/header.jspf"%>
		<%-- HEADER --%>

		<tr >
			<td class="content">
			<%-- CONTENT --%>
				
				<h2 class="error">
					<fmt:message key="following_error_occurred" />
				</h2>
			
				<%-- this way we obtain an information about an exception (if it has been occurred) --%>
				<c:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
				<c:set var="message" value="${requestScope['javax.servlet.error.message']}"/>
				<c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>
				
				<c:if test="${not empty code}">
					<h3 class="text-white">Error code: ${code}</h3>
				</c:if>			
				
				<c:if test="${not empty message}">
					<h3 class="text-white">${message}</h3>
					<h3 class="text-white">${errorMessage}</h3>
				</c:if>
				
				<c:if test="${not empty exception}">
					<p class="text-white"><% exception.printStackTrace(new PrintWriter(out)); %></p>
				</c:if>
				
				<%-- if we get this page using forward --%>
				<c:if test="${not empty requestScope.errorMessage}">
					<h3 class="text-white">${requestScope.errorMessage}</h3>
				</c:if>

			<%-- CONTENT --%>
			</td>
		</tr>

		<%@ include file="/WebContent/WEB-INF/jspf/footer.jspf"%>
		
	</table>
</body>
</html>