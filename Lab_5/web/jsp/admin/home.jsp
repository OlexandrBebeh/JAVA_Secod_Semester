<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <meta charset="UTF-8" />
        <title>Home</title>
    </head>
<body>
<p>
Hello, <c:out value="${admin.firstName} ${admin.secondName}"/>!
</p>
<p>
<button onclick="window.location.href='Clients'">View clients</button> 
<button onclick="window.location.href='Accounts?sort=0'">View accounts</button> 
<button onclick="window.location.href='Payments?sort=0'">View payments</button> 
<button onclick="window.location.href='Unblock-Query'">View unblock query</button>
<button onclick="window.location.href='../Exit'">Quit</button> 
</p>
</body>
</html>