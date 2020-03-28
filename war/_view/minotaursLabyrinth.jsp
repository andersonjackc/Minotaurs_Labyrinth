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
		<%--Movable rooms have a border--%>
		td.Game {
			border: 1px solid black;
		}
		<%-- non movable rooms are red --%>
		td.Corner{
			background-color: red;
		}
		<%-- set size, align all text--%>
		table.table{
			table-layout: fixed ;
			width: 400px;
			height: 400px;
			text-align: center;
			background-color: white;
		}
		<%-- move buttons to middle of map--%>
		table.buttons{
			margin-left: 0px;
		}
		body {
  			background-image: url('https://images.pexels.com/photos/235985/pexels-photo-235985.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940');
  			background-size: cover;
 			background-repeat: no-repeat;
		}
		</style>
		<%-- script to make 0's blank and the player a *--%>
		<script>
    		function checkNorth(val1){
				if(val1 == 0){
					document.getElementById("North").innerHTML = "&#160";
				}else if(val1 == 1){
					document.getElementById("North").innerHTML = "*";
				}
  			 }
  			 
  			 function checkWest(val2){
    			if(val2 == 0){
					document.getElementById("West").innerHTML = "&#160";
				}else if(val2 == 1){
					document.getElementById("West").innerHTML = "*";
					document.getElementById("Empty").innerHTML = "This room is empty, turn around and check out the other rooms!";
				}
  			 }
  			 
  			 function checkCenter(val3){
    			if(val3 == 0){
					document.getElementById("Center").innerHTML = "&#160";
				}else if(val3 == 1){
					document.getElementById("Center").innerHTML = "*";
				}    			  			
  			 }
  			 
  			 function checkEast(val4){
    			if(val4 == 0){
					document.getElementById("East").innerHTML = "&#160";
				}else if(val4 == 1){
					document.getElementById("East").innerHTML = "*";
				}    			
  			 }
  			 
  			 function checkSouth(val5){
    			if(val5 ==  0){
					document.getElementById("South").innerHTML = "&#160";
				}else if(val5 == 1){
					document.getElementById("South").innerHTML = "*";
				}

    			
  			 }
    </script>
	</head>
	<body>
		<form action="${pageContext.servletContext.contextPath}/minotaursLabyrinth" method="post">
			<%--draws the map--%>
			<table class="table">
				<tr>
					<td class="Corner"></td>
					<td id="North" class="Game"></td>
					<td class="Corner"></td>
				</tr>
				<tr>
					<td id="West" class="Game"></td>
					<td id="Center" class="Game"></td>
					<td id="East" class="Game"></td>	
				</tr>
				<tr>
					<td class="Corner"></td>
					
					<td id="South" class="Game"></td>
					
					<td class="Corner"></td>	
				</tr>
		</table>
		<table class = "buttons">
			<tr>
				<td><input name="textbox"  type="text" maxlength="256" autocapitalize="off" aria-live="off" style="left: 0px; width: 300px;" value=""></td>
				<%--used for persistence--%>
				<input name="xLoc" type="hidden" value="${game.posX}" />
				<input name="yLoc" type="hidden" value="${game.posY}" />
			</tr>
		</table>
		</form>
		</br>
		<div id="Empty"></div>
		<div class="Message">${game.message}</div>
		<%--error message based on what error--%>
		<div class="error">${game.error}</div>
		<%--run js scripts to enter " " at 0's and * at 1's --%>
		<script>
		checkNorth(${game.getValue(0,1)});
		checkWest(${game.getValue(1,0)});
		checkCenter(${game.getValue(1,1)});
		checkEast(${game.getValue(1,2)});
		checkSouth(${game.getValue(2,1)});
		</script>
	</body>
</html>