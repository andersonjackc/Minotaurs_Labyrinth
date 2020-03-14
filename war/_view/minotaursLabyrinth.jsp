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
	</head>
	<body>
		<form action="${pageContext.servletContext.contextPath}/minotaursLabyrinth" method="post">
			<div class="error">${game.error}</div>
			<table class="table">
				<tr>
					<td class="Corner"></td>
					<td class="Game">${game.getValue(0,1)}</td>
					<td class="Corner"></td>
				</tr>
				<tr>
					<td class="Game">${game.getValue(1,0)}</td>
					<td class="Game">${game.getValue(1,1)}</td>
					<td class="Game">${game.getValue(1,2)}</td>	
				</tr>
				<tr>
					<td class="Corner"></td>
					<td class="Game">${game.getValue(2,1)}</td>
					<td class="Corner"></td>	
				</tr>
				<tr>
				</tr>
		</table>
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