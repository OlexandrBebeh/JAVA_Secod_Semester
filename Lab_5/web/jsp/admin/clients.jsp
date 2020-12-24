<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Account</title>
    </head>
<body>
<jsp:include page="home.jsp"/>

<table style="width:40%">
<tr>
		<th>ClientID</th>
		<th>Username</th>
		<th>Firstname</th>
		<th>Secondname</th>
		<th>Email</th>
		<th>Blocked</th>
		<th>Action</th>
	</tr>
    <c:forEach var="client" items="${clients}">
	<tr>
		<th><c:out value="${client.clientID}" /></th>
		<th><c:out value="${client.clientName}" /></th>
		<th><c:out value="${client.firstName}"/></th>
		<th><c:out value="${client.secondName}" /></th>
		<th><c:out value="${client.email}"/></th>
		<th><c:out value="${client.blocked}"/></th>
		<th>
		<c:if test="${client.blocked==true}">
			<form action="Clients" method="POST">
				 <button type="submit" name="unblock" value="${client.clientID}">Unblock</button>
			</form>
		</c:if>
		<c:if test="${client.blocked==false}">
			<form action="Clients" method="POST">
				 <button type="submit" name="block" value="${client.clientID}">Block</button>
			</form>
		</c:if>
		</th>
	</tr>

    </c:forEach>
</table>

<c:set var="href" value="Clients?" scope="request"/>
<jsp:include page="../pagination/pagination.jsp"/>

<c:if test="${account.blocked==true}">
	<p>${error}</p>
</c:if>
</body>
</html>


























