<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Minotaur's Labyrinth</title>
		<style type="text/css">
		
		<%--set error text to red--%>
		.error {
			color: white;
		}
		<%-- set size, align all text--%>
		table.table{
			table-layout: fixed;
			width: 400px;
			height: 400px;
			text-align: center;
			background-color: white;
		}
		body {
  			background-image: url('https://images.pexels.com/photos/235985/pexels-photo-235985.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940');
  			background-size: cover;
 			background-repeat: no-repeat;
		}
		</style>		
	</head>
	<body>
		<form action="${pageContext.servletContext.contextPath}/minotaursLabyrinth" method="post">
			
		<table class = "textbox">
			<tr>
				<td><input name="textbox" autocomplete="off" type="text" maxlength="256" autocapitalize="off" aria-live="off" style="left: 0px; width: 300px;" value=""></td>
				<input name="location" type="hidden" value="${game.roomPosition}" />
			</tr>
		</table>
		
		</form>
		</br>
		<table>
			<c:if test="${! empty game.attackmessage}">
				<tr><td>${game.attackmessage}</td></tr>
			</c:if>
			<c:if test="${! empty game.defendmessage}">
				<tr><td>${game.defendmessage}</td></tr>
			</c:if>
		</table>
		
		<input name="location"  value="${game.roomPosition}" />
		<div id="Empty"></div>
		<div class="Message">${game.roomDescription}</div>
		<div class="Message">${game.message}</div>
		<%--error message based on what error--%>
		<div class="error">${game.error}</div>
		
		<%--used for persistence--%>
		<input name="playerHP" type="hidden" value="${game.playerHP}" />
		
		<input name="enemyHP" type="hidden" value="${game.enemyHP}" />
	</body>
</html>