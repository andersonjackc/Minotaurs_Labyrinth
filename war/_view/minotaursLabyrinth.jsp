<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>

<html>

<head>
	<title>Minotaur's Labyrinth</title>
	
	<style>
		<%@include file="minotaursStyles.css"%>
	</style>
	<script>
		<%@include file = "minotaursFunctions.js" %>
	</script>
	
</head>

<body>

	<form action="${pageContext.servletContext.contextPath}/minotaursLabyrinth" method="post">
		<div class="zeus">
			<img class="zeus" src="https://cdn.pixabay.com/photo/2013/07/12/13/24/mythology-146988_960_720.png">
		</div>
		<div class="hera">
			<img class="hera" src="https://cdn.pixabay.com/photo/2013/07/12/13/23/hera-146932_960_720.png">
		</div>
		<div class = "statBar">
			<table>
			
				<tr>
				<td><img class="statSymbol" src="https://image.flaticon.com/icons/svg/535/535234.svg"></td>
				<td>${game.HP}</td>
				</tr>
		
				<tr>
				<td><img class="statSymbol" src="https://image.flaticon.com/icons/svg/867/867785.svg"></td>				
				<td>${game.resource}</td>
				</tr>
				
				<tr>
				<td><img class="statSymbol" src="https://image.flaticon.com/icons/svg/1191/1191182.svg"></td>
				<td>${game.atk}</td>
				</tr>
				
				<tr>
				<td><img class="statSymbol" src="https://image.flaticon.com/icons/svg/786/786245.svg"></td>
				<td>${game.def}</td>
				</tr>
				
				<tr>
				<td><img class="statSymbol" src="https://image.flaticon.com/icons/svg/482/482506.svg"></td>				
				<td>${game.gold}</td>
				</tr>
				
				<tr>
				<td><img class="statSymbol" src="https://image.flaticon.com/icons/svg/1087/1087927.svg"></td>				
				<td>${game.XP}</td>
				</tr>
				
			</table>
		</div>
		<div id="gameText" class="gameText">
			<table class="center">
				<c:forEach items="${outputstrings}" var="strings">
				
					<c:if test="${strings.playerAction == 3}">
                        <tr class="castCommands">
                            <td name="output">${strings.message}</td>
                        </tr>
                    </c:if>
					<c:if test="${strings.playerAction == 2}">
						<tr class="attackCommands">
							<td name="output">${strings.message}</td>
						</tr>
					</c:if>
					
					<c:if test="${strings.playerAction == 1}">
						<tr class="userCommands">
							<td name="output">${strings.message}</td>
						</tr>
					</c:if>

					<c:if test="${strings.playerAction == 0}">
						<tr class="systemResponses">
							<td name="output">${strings.message}</td>
						</tr>
					</c:if>


				</c:forEach>
				<script>
						scrollBarFunction();

				</script>
			</table>
		</div>

		<div class="textbox">
			<table class="textbox">
				<tr>
					<td><input id="textbox" name="textbox" autofocus autocomplete="off" type="text" maxlength="256"
							autocapitalize="off" aria-live="off" style="left: 0px; width: 300px;" value=""></td>
				</tr>
			</table>
		</div>
		
		<div class="map">
		<c:forTokens items = "${game.mapString}" delims = "," var = "row">
			
					${row}
				
		<br>
		</c:forTokens>
		
		</div>
		<!-- dont worry about this -->
		${game.torqu3String}
		<input name="torqu3String" type="hidden" value="${game.torqu3Counter}" />
	</form>
</body>

</html>