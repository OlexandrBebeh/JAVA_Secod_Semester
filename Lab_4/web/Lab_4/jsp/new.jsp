<%@page import="project.kpi.test.First"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>First JSP App</title>
    </head>
<body>
<jsp:text>Hello!</jsp:text>
        <jsp:include page="../index.html"/>
        <jsp:scriptlet>
                First second = new First();
                 out.println(second.test());
        </jsp:scriptlet>
<form action="../Test" method="GET">
    Name: <input name="username" />
    <input type="submit" value="Submit" />
</form>
</body>
</html>