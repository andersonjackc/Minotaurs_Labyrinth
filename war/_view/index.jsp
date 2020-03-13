<!DOCTYPE html>

<html>
	<head>
		<title>Index view</title>
	</head>

	<body>
		<div>
			<form action="${pageContext.servletContext.contextPath}/index" method="post">
			<input name="AddNumbers" type="submit" value="Add Numbers" />
			<input name="GuessingGame" type="submit" value="Guessing Game" />	
			<input name="MultiplyNumbers" type="submit" value="Multiply Numbers" />
			</form>
		</div>
	</body>
</html>
