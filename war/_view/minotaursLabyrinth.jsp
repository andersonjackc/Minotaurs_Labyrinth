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
		</style>
		<script>
		
    		function checkNorth(val1){
				if(val1 == 0){
					document.getElementById("North").innerHTML = "";
				}else{
					document.getElementById("North").innerHTML = "*";
				}
  			 }
  			 
  			 function checkWest(val2){
    			if(val2 == 0){
					document.getElementById("West").innerHTML = "";
				}else{
					document.getElementById("West").innerHTML = "*";
				}
  			 }
  			 
  			 function checkCenter(val3){
    			if(val3 == 0){
					document.getElementById("Center").innerHTML = "";
				}else{
					document.getElementById("Center").innerHTML = "*";
				}    			  			
  			 }
  			 
  			 function checkEast(val4){
    			if(val4 == 0){
					document.getElementById("East").innerHTML = "";
				}else{
					document.getElementById("East").innerHTML = "*";
				}    			
  			 }
  			 
  			 function checkSouth(val5){
    			if(val5 == 0){
					document.getElementById("South").innerHTML = "";
				}else{
					document.getElementById("South").innerHTML = "*";
				}

    			
  			 }
    </script>
	</head>
	<body>
		<form action="${pageContext.servletContext.contextPath}/minotaursLabyrinth" method="post">
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
					<script>
					checkNorth(${game.getValue(0,1)});
					checkWest(${game.getValue(1,0)});
					checkCenter(${game.getValue(1,1)});
					checkEast(${game.getValue(1,2)});
					checkSouth(${game.getValue(2,1)});
					</script>
					<td id="South" class="Game"></td>
					<td class="Corner"></td>	
				</tr>
				<tr>
				</tr>
			</table>
			<input type="Submit" name="submit" value="Add Numbers!">
			<%-- index button --%>
			<input name="Index" type="submit" value="Index" />
		</form>
	</body>
</html>