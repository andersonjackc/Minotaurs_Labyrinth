<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Guessing Game</title>
	</head>

	<body>
		<form action="${pageContext.servletContext.contextPath}/guessingGame" method="post">
			<c:if test="${empty game}">
				<input name="startGame" type="submit" value="Start game" />
				<%-- index button --%>
				<input name="Index" type="submit" value="Index" />
			</c:if>
			<c:if test="${! empty game}">
				<c:if test="${game.done}">
					<div>
						The number you are thinking of is ${game.guess}
					</div>
					<div>
						<input name="startGame" type="submit" value="Play again" />
						<%-- index button --%>
						<input name="Index" type="submit" value="Index" />
					</div>
				</c:if>
				<c:if test="${!game.done}">
					<div>
						Are you thinking of ${game.guess}?
					</div>
					<div>
						<input name="gotIt" type="submit" value="Yes, that's it!" />
						<input name="less" type="submit" value="No, that's too big" />
						<input name="more" type="submit" value="No, that's too small" />
						<%-- index button --%>
						<input name="Index" type="submit" value="Index" />
						<input name="min" type="hidden" value="${game.min}" />
						<input name="max" type="hidden" value="${game.max}" />
					</div>
				</c:if>
			</c:if>
		</form>
	</body>
</html>