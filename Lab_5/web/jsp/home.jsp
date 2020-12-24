<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <meta charset="UTF-8" />
        <title>Home</title>
    </head>
<body>
<p>
Hello, <c:out value="${client.firstName} ${client.secondName}"/>
</p>
<p>
<button onclick="window.location.href='../Exit'">Quit</button> 
</p>

<form action="Home" method="GET">
<p>
Select sort:
<select name="sort">
        <option value="0" selected>-</option>
    <option value="1">By account name</option>
    <option value="2">By number</option>
    <option value="3">By score</option>
</select>
<input type="submit" value="Sort">
</p>
</form>
<table style="width:60%">
<tr>
		<th>Account number</th>
		<th>Account name</th>
		<th>Credit card number</th>
		<th>Score</th>
		<th>Visit</th>
		<th>Block</th>
	</tr>
    <c:forEach var="acc" items="${accounts}">

	<tr>
		<c:if test="${acc.blocked==false}">
		<th><c:out value="${acc.accountID}" /></th>
		<th><c:out value="${acc.accountName}" /></th>
		<th><c:out value="${acc.creditCardNumber}" /></th>
		<th><c:out value="${acc.score}" /></th>
		<th><form action="Account" method="POST">
				 <button type="submit" name="account_id" value="${acc.accountID}">Go to</button>
		</form></th>
		</c:if>
		<c:if test="${acc.blocked==true}">
		<th><c:out value="${acc.accountID}" /></th>
		<th><c:out value="${acc.accountName}" /></th>
		<th><c:out value="${acc.creditCardNumber}" /></th>
		<th><c:out value="${acc.score}" /></th>

			<th>BLOCKED</th>
		</c:if>
		<th>
		<c:if test="${acc.blocked==true}">
			<form action="Home" method="POST">
				 <button type="submit" name="unblock" value="${acc.accountID}">Unblock</button>
			</form>
		</c:if>
		<c:if test="${acc.blocked==false}">
			<form action="Home" method="POST">
				 <button type="submit" name="block" value="${acc.accountID}">Block</button>
			</form>
		</c:if>
		</th>
	</tr>

    </c:forEach>
	<p>
	${error}
	</p>
</table>
</body>
</html>