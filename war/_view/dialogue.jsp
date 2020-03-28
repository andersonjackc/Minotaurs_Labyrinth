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
			margin-left: 0px;
	}
	.error {
			color: red;
		}
	</style>
	<body>
		<form action="${pageContext.servletContext.contextPath}/dialogue" method="post">
			
			<%--imports villager pic--%>
			<img class='enemy' src='https://cdn1.iconfinder.com/data/icons/avatar-flat-design-big-family/512/avatar_villager-512.png'>
			<table class = "buttons">
			<tr>
				<td><input name="textbox"  type="text" maxlength="256" autocapitalize="off" aria-live="off" style="left: 0px; width: 300px;" value=""></td>
			</tr>
			<c:if test="${! empty game.NPCDesc}">
				<tr><td>${game.NPCDesc}</td></tr>
			</c:if>
			<c:if test="${! empty game.playerResp}">
				<tr><td>${game.playerResp}</td></tr>
			</c:if>
			<c:if test="${! empty game.NPCResp}">
				<tr><td>${game.NPCResp}</td></tr>
			</c:if>
			<c:if test="${! empty game.error}">
				<tr><td class="error">${game.error}</td></tr>
			</c:if>
		</form>
			
	</body>
</html>