<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Add Numbers</title>
		<style type="text/css">
		.error {
			color: red;
		}
		
		td.label {
			text-align: right;
		}
		</style>
	</head>

	<body>
	<%-- IF there is an ERROR we throw the user the error STRINGS as we cannot save invalid doubles.--%>
		<c:if test="${! empty game.error}">
			<div class="error">${game.error}</div>
			<form action="${pageContext.servletContext.contextPath}/addNumbers" method="post">
			<table>
				<tr>
					<td class="label">First number:</td>
					<td><input type="text" name="first" size="12" value="${game.errorfirst}" /></td>
				</tr>
				<tr>
					<td class="label">Second number:</td>
					<td><input type="text" name="second" size="12" value="${game.errorsecond}" /></td>
				</tr>
				<tr>
					<td class="label">Third number:</td>
					<td><input type="text" name="third" size="12" value="${game.errorthird}" /></td>
				</tr>
				<tr>
					<td class="label">Result:</td>
				</tr>
			</table>
			<input type="Submit" name="submit" value="Add Numbers!">
			<%-- index button --%>
			<input name="Index" type="submit" value="Index" />
			</form>
		</c:if>
		<%-- if theres no errors, use the correct doubles--%>
		<c:if test="${empty game.error}">
		<form action="${pageContext.servletContext.contextPath}/addNumbers" method="post">
			<table>
				<tr>
					<td class="label">First number:</td>
					<td><input type="text" name="first" size="12" value="${game.first}" /></td>
				</tr>
				<tr>
					<td class="label">Second number:</td>
					<td><input type="text" name="second" size="12" value="${game.second}" /></td>
				</tr>
				<tr>
					<td class="label">Third number:</td>
					<td><input type="text" name="third" size="12" value="${game.third}" /></td>
				</tr>
				<tr>
					<td class="label">Result:</td>
					<td>${game.result}</td>
				</tr>
			</table>
			<input type="Submit" name="submit" value="Add Numbers!">
			<%-- index button --%>
			<input name="Index" type="submit" value="Index" />
		</form>
		</c:if>
	</body>
</html>