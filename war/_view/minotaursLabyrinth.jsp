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
			width=50px;
		}
		td.Corner{
			color:red;
		}
		table.Game{
			table-layout: fixed ;
		}
		
		</style>
	</head>

	<body>
		<form action="${pageContext.servletContext.contextPath}/minotaursLabyrinth" method="post">
			<table class="Game"'>
				<tr>
					<td class="Corner">${game.getValue(0,0)}</td>
					<td class="Game">${game.getValue(0,1)}</td>
					<td class="Corner">${game.getValue(0,2)}</td>					
				</tr>
				<tr>
					<td class="Game">${game.getValue(1,0)}</td>
					<td class="Game">${game.getValue(1,1)}</td>
					<td class="Game">${game.getValue(1,2)}</td>	
				</tr>
				<tr>
					<td class="Corner">${game.getValue(2,0)}</td>
					<td class="Game">${game.getValue(2,1)}</td>
					<td class="Corner">${game.getValue(2,2)}</td>	
				</tr>
				<tr>
					<td class="label">Result:</td>
				</tr>
			</table>
			<input type="Submit" name="submit" value="Add Numbers!">
			<%-- index button --%>
			<input name="Index" type="submit" value="Index" />
		</form>
	</body>
</html>