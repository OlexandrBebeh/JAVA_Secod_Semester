<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Replenish</title>
    </head>
<body>
<button onclick="window.location.href='Home'">Home</button>
<button onclick="window.location.href='Account'">Account</button>
<p>Account name: <c:out value="${account.accountName}" /></p>
<p>Credit card number: <c:out value="${account.creditCardNumber}" /></p>
<p>Score: <c:out value="${account.score}" /></p>
<form action="Make-Payment" method="Post">
    <p>Enter score: <input name="score" /></p>
	<p>Enter recipient account: <input name="recipient" /></p>
    <p><input type="submit" value="Submit" /></p>
	<p>${error}</p>
</form>
</body>
</html>