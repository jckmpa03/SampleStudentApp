<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<c:url var="student" value='/assets/style/student.css' />
<link href='${student}' rel='stylesheet' />

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script
	src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"
	type="text/javascript"></script>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css">



<script>
	$(document).ready(function() {
		$('#myTable').DataTable({
			"order" : [],
			"iDisplayLength": 100,
			"rowCallback": function( row, data, index ) {
			    
			}
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
			<c:url value="/student" var="newStudent" />
			<a href='${newStudent}' class='newButton'>New Student</a> <span
				class="confirmation">${msg}</span>
				
			<table id="myTable" class="display">
				<thead>
					<tr>
						<th>#</th>
						<th>Name</th>
						<th>Student ID</th>
						<th>School Year</th>
						<th>Campus</th>
						<th>Entry Date</th>
						<th>Grade Level</th>
						<th>Action</th>
					</tr>
				</thead>
				</tbody>
				<c:forEach var="student" items="${students}">
					
						
					<tr>
						<td>${student.id}</td>
						<td>${student.name}</td>
						<td align="center">${student.studentId}</td>
						<td align="center">${student.schoolYr}</td>
						<td align="center">${student.campus}</td>
						<td align="center"><fmt:formatDate pattern="MM/dd/yyyy"
								value="${student.entryDate}" /></td>
						<td align="center">${student.gradeLevel}</td>
						<td align="center">
							<c:url value="/student/${student.id}/update"	var="updateUrl" />
							<a href="${updateUrl}">Edit</a>
						</td>
						
						
						
						
					
					</tr>
				</c:forEach>
				</tbody>
			</table>

		</article>

	</div>


</body>
</html>
