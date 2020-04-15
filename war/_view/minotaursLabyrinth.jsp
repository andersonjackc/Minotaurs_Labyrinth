<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>

<html>
	<head>
		<title>Minotaur's Labyrinth</title>
		<style type="text/css">

		body {
  			background-image: url('https://images.pexels.com/photos/235985/pexels-photo-235985.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940');
  			background-size: cover;
 			background-repeat: no-repeat;
		}
		div.zeus{
			position: absolute;
			right: 0%;
		}
		div.hera{
			position: absolute;
			left: 0%;
		}
		div.textbox{
			margin: 0;
    		position: absolute;
    		top: 90%;
    		left: 50%;
    		margin-right: -50%;
    		transform: translate(-50%, -50%);
    		text-align: center;
    		border-style: solid;
    		border-color: brown;
    		background-color: brown;
		}
		div.gameText{
			margin: 0;
    		position: absolute;
    		top: 45%;
    		left: 50%;
    		margin-right: -50%;
    		transform: translate(-50%, -50%);
    		text-align: center;
    		overflow:auto;
    		height: 600px;
    		bottom: 0;
    		font-size: 19px;
		}
		tr.userCommands{
			color: white;

		}
		tr.systemResponses{
		}
		</style>	
		<script>
			function scrollBarFunction(){
			var objDiv = document.getElementById("gameText");
			objDiv.scrollTop = objDiv.scrollHeight;
			}
		</script>	
	</head>
	<body>
		<form action="${pageContext.servletContext.contextPath}/minotaursLabyrinth" method="post">
		<div class="zeus">
		<img src="https://cdn.pixabay.com/photo/2013/07/12/13/24/mythology-146988_960_720.png">
		</div>
		<div class="hera">
		<img src="https://cdn.pixabay.com/photo/2013/07/12/13/23/hera-146932_960_720.png">
		</div>
		<div id="gameText" class="gameText">
		<table>
		<c:forEach items="${outputstrings}" var="strings">
				<c:if test="${strings.playerAction == 1}"> 
			        <tr class="userCommands">
			            <td name="output">${strings.message}</td>	
			        </tr>
			    </c:if>
			    
				<c:if test="${strings.playerAction == 0}"> 
			        <tr class = "systemResponses">
			            <td name="output">${strings.message}</td>	
			        </tr>
			    </c:if>
			    
			    <input name="test" type="hidden" value="${strings.message}" />
			    <input name="playerAction" type="hidden" value="${strings.playerAction}" />

			    </c:forEach>
			    <script>
					scrollBarFunction();
					
				</script>
		</table>
		</div>
		
		<div class="textbox">
		<table class = "textbox">
			<tr>
				<td><input id="textbox" name="textbox" autofocus autocomplete="off" type="text" maxlength="256" autocapitalize="off" aria-live="off" style="left: 0px; width: 300px;" value=""></td>
			</tr>
			<input name="location" type="hidden" value="${game.roomPosition}" />
			<input name="playerHP" type="hidden" value="${game.playerHP}" />
			<input name="enemyIsDead" type="hidden" value="${game.enemyDead}" />
			<input name="villagerIsDead" type="hidden" value="${game.villagerDead}" />
			<input name="enemyHP" type="hidden" value="${game.enemyHP}" />
			<input name="villagerHP" type="hidden" value="${game.villagerHP}" />

		</table>
		</div>
	</form>
	</body>
</html>