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
	<%--set error text to red--%>
		.error {
			color: white;
		}
	body {
  		background-image: url('https://images.pexels.com/photos/235985/pexels-photo-235985.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940');
  		background-size: cover;
 		background-repeat: no-repeat;
	}
	</style>
	<body>
		<form action="${pageContext.servletContext.contextPath}/combat" method="post">
			
			<%--imports ogre pic--%>
			<img class='enemy' src='https://cdn.pixabay.com/photo/2013/07/13/01/23/troll-155646_1280.png'>
			<table class = "buttons">
			<tr>
				
				<td><input name="textbox" autocomplete="off" type="text" maxlength="256" autocapitalize="off" aria-live="off" style="left: 0px; width: 300px;" value=""></td>
			</tr>
			
			<c:if test="${! empty game.attackmessage}">
				<tr><td>${game.attackmessage}</td></tr>
			</c:if>
			<c:if test="${! empty game.defendmessage}">
				<tr><td>${game.defendmessage}</td></tr>
			</c:if>
			<c:if test="${! empty game.error}">
				<tr><td class="error">${game.error}</td></tr>
			</c:if>
		</form>
		
		<%--used for persistence--%>
		<input name="playerHP" type="hidden" value="${game.playerHP}" />
		
		<input name="enemyHP" type="hidden" value="${game.enemyHP}" />
	</body>
</html>