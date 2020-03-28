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
	<%-- move buttons to middle of map--%>
	table.buttons{
			margin-left: 0px;
	}
	</style>
	<body>
		<form action="${pageContext.servletContext.contextPath}/combat" method="post">
			
			<%--imports ogre pic--%>
			<img class='enemy' src='https://cdn.pixabay.com/photo/2013/07/13/01/23/troll-155646_1280.png'>
			<table class = "buttons">
			<tr>
				<td></td>
				<%-- <td><input type="Submit" name="Attack" value="Attack"></td>--%>
				<td><input name="Attack" id="win980_input" class="Input LineInput" type="text" maxlength="256" autocapitalize="off" aria-live="off" style="left: 0px; width: 300px;"></td>
			</tr>
			<c:if test="${! empty game.attackmessage}">
				<div>${game.attackmessage}</div>
			</c:if>
			<c:if test="${! empty game.defendmessage}">
				<div>${game.defendmessage}</div>
			</c:if>
		</form>
		
		<%--used for persistence--%>
		<input name="playerHP" type="hidden" value="${game.playerHP}" />
		
		<input name="enemyHP" type="hidden" value="${game.enemyHP}" />
	</body>
</html>