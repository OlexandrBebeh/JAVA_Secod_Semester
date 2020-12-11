<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="project.kpi.model.dao.entities.Client"%>
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
<button onclick="window.location.href='Accounts'">View accounts</button> 
<button onclick="window.location.href='Payments'">View payments</button> 
<button onclick="window.location.href='Unblock-Query'">View unblock query</button>
</p>
</body>
</html>