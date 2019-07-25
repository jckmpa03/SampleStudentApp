<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<c:url var="student" value='/assets/style/student.css' />
<link href='${student}' rel='stylesheet' />
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
	$(function() {
		$("#datepicker").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange: '1945:'+(new Date).getFullYear() 
		});
	});
</script>
</head>
<body>
	<div id="content" class="container">
		<header>
			<jsp:include page="header.jsp" />
		</header>
		<nav>
		<jsp:include page="navigation.jsp" />
		</Nav>
		<article>
		<c:url value="/" var="studentListURL" />
		 	 <a href="${studentListURL}">Return to Student List</a><br><br>
			<spring:url value="/iStudent" var="iStudentUrl" />
			<form:form method="post" modelAttribute="studentForm"
				action="${iStudentUrl}">
				<table>
					<tr>
						<td>Full Name * <form:hidden path="id" /></td>
						<td><form:input path="name" /> <form:errors path="name" class="error"/></td>
					</tr>
					<tr>
						<td>Student ID </td>
						<td><form:input path="studentId" /></td>
					</tr>
					<tr>
						<td>School Year </td>
						<td><form:input path="schoolYr" /></td>
					</tr>

					<tr>
						<td>Campus</td>
						<td><form:input path="campus" /></td>
					</tr>
					<tr>
						<td>Entry Date</td>
						<td><form:input path="entryDate" id="datepicker" />
						</td>
					</tr>
					<tr>
						<td>Grade Level</td>
						<td><form:input path="gradeLevel" />
						</td>
					</tr>
					
					<tr>
						<td colspan='2'><button type="submit">Save</button></td>
					</tr>
				</table>
			</form:form>
		</article>
	</div>

</body>
</html>