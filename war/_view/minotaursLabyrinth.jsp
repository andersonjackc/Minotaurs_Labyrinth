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
		td.Game {
			border: 1px solid black;
		}
		td.Corner{
			background-color: red;
		}
		table.table{
			table-layout: fixed ;
			width: 400px;
			height: 400px;
		}
		table.buttons{
			margin-left: 120px;
		}
		</style>
		<script>
		
    		function checkNorth(val1){
				if(val1 == 0){
					document.getElementById("North").innerHTML = "&#160";
				}else{
					document.getElementById("North").innerHTML = "*";
				}
  			 }
  			 
  			 function checkWest(val2){
    			if(val2 == 0){
					document.getElementById("West").innerHTML = "&#160";
				}else{
					document.getElementById("West").innerHTML = "*";
				}
  			 }
  			 
  			 function checkCenter(val3){
    			if(val3 == 0){
					document.getElementById("Center").innerHTML = "&#160";
				}else{
					document.getElementById("Center").innerHTML = "*";
				}    			  			
  			 }
  			 
  			 function checkEast(val4){
    			if(val4 == 0){
					document.getElementById("East").innerHTML = "&#160";
				}else{
					document.getElementById("East").innerHTML = "*";
				}    			
  			 }
  			 
  			 function checkSouth(val5){
    			if(val5 ==  0){
					document.getElementById("South").innerHTML = "&#160";
				}else{
					document.getElementById("South").innerHTML = "*";
				}

    			
  			 }
    </script>
	</head>
	<body>
		<form action="${pageContext.servletContext.contextPath}/minotaursLabyrinth" method="post">
			<div class="error">${game.error}</div>
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
				<tr>
				</tr>
		</table>
		<script>
		checkNorth(${game.getValue(0,1)});
		checkWest(${game.getValue(1,0)});
		checkCenter(${game.getValue(1,1)});
		checkEast(${game.getValue(1,2)});
		checkSouth(${game.getValue(2,1)});
		</script>
		<table class = "buttons">
			<tr>
				<td></td>
				<td><input type="Submit" name="North" value="North"></td>
			</tr>
			<tr>
				<td><input type="Submit" name="West" value="West"></td>
				<td><input type="Submit" name="South" value="South"></td>
				<td><input type="Submit" name="East" value="East"></td>
			</tr>
		</table>
		</form>
	</body>
</html>