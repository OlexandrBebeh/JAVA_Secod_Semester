<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Account</title>
    </head>
<body>
<jsp:include page="home.jsp"/>
<form action="Account" method="GET">
Select sort:
<select name="sort">
        <option value="0" selected>-</option>
    <option value="1">By number</option>
    <option value="2">By newest date</option>
    <option value="3">By latest date</option>
</select>
</form>
<table style="width:50%">
<tr>
		<th>AccountID</th>
		<th>ClientID</th>
		<th>Account name</th>
		<th>Credit card number</th>
		<th>Score</th>
		<th>Blocked</th>
		<th>Action</th>
	</tr>
    <c:forEach var="acc" items="${accounts}">

	<tr>
		<th><c:out value="${acc.accountID}" /></th>
		<th><c:out value="${acc.clientID}" /></th>
		<th><c:out value="${acc.accountName}" /></th>
		<th><c:out value="${acc.creditCardNumber}" /></th>
		<th><c:out value="${acc.score}" /></th>
		<th><c:out value="${acc.blocked}" /></th>
		<th>
		<c:if test="${acc.blocked==true}">
			<form action="Accounts" method="POST">
			<input hidden="true" name="sort" value="${param.sort}">
			<input hidden="true" name="page" value="${param.page}">
				 <button type="submit" name="unblock" value="${acc.accountID}">Unblock</button>
			</form>
		</c:if>
		<c:if test="${acc.blocked==false}">
			<form action="Accounts" method="POST">
			<input hidden="true" name="sort" value="${param.sort}">
			<input hidden="true" name="page" value="${param.page}">
				 <button type="submit" name="block" value="${acc.accountID}">Block</button>
			</form>
		</c:if>
		</th>
	</tr>

    </c:forEach>
</table>

<c:set var="href" value="Accounts?sort=${param.sort}&" scope="request"/>
<jsp:include page="../pagination/pagination.jsp"/>


<c:if test="${account.blocked==true}">
	<p>${error}</p>
</c:if>
</body>
</html>


























