<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Dialogue</title>
	</head>
	<style>
	img.NPC{
	width: 20%;
	height: 20%;
	}
	<%-- move buttons to middle of map--%>
	table.buttons{
			margin-left: 120px;
	}
	</style>
	<body>
		<form action="${pageContext.servletContext.contextPath}/dialogue" method="post">
			
			<%--imports villager pic--%>
			<img class='enemy' src='https://cdn1.iconfinder.com/data/icons/avatar-flat-design-big-family/512/avatar_villager-512.png'>
			<table class = "buttons">
			<tr>
				<td></td>
				<td><input type="Submit" name="Greetings" value="Greetings"></td>
			</tr>
			<c:if test="${! empty game.NPCDesc}">
				<div>${game.NPCDesc}</div>
			</c:if>
			<c:if test="${! empty game.playerResp}">
				<div>${game.playerResp}</div>
			</c:if>
			<c:if test="${! empty game.NPCResp}">
				<div>${game.NPCResp}</div>
			</c:if>
		</form>
			
	</body>
</html>