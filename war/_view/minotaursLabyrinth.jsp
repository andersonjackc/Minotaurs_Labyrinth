<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Minotaur's Labyrinth</title>
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
		<c:if test="${empty game.error}">
		<form action="${pageContext.servletContext.contextPath}/minotaursLabyrinth" method="post">
			<table>
				<tr>
					<td class="label">This works:</td>
					<td><input type="text" name="first" size="12" value="${game.playertest}" /></td>
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