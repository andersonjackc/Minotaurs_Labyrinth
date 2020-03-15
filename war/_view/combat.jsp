<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Combat</title>
	</head>
	<style>
	img.enemy{
	width: 20%;
	height: 20%;
	}
	</style>
	<body>
		<form action="${pageContext.servletContext.contextPath}/combat" method="post">
			
			<%--draws the map--%>
			<img class='enemy' src='https://cdn.pixabay.com/photo/2013/07/13/01/23/troll-155646_1280.png'>
			<table class="table">
				<tr>
					<td>TEST</td>
				</tr>
			</table>
		</form>
	</body>
</html>