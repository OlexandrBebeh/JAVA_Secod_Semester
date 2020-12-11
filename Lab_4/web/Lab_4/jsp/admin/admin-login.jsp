<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>First JSP App</title>
    </head>
<body>
<form action="Admin-Login" method="POST">
    <p>Name: <input name="username" /></p>
	<p>Password: <input name="password" /></p>
    <p><input type="submit" value="Submit" /></p>
	<p>${error}</p>
</form>
</body>
</html>