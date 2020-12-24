<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Account</title>
    </head>
<body>

<p>Account name: <c:out value="${account.accountName}" /></p>
<p>Credit card number: <c:out value="${account.creditCardNumber}" /></p>
<p>Score: <c:out value="${account.score}" /></p>
<c:if test="${account.blocked==false}">
<button onclick="window.location.href='Replenish'">Replenish</button>
<button onclick="window.location.href='Make-Payment'">Make Payment</button>
<button onclick="window.location.href='Home'">Home</button>

<form action="Account" method="GET">
<p>
Select sort:
<select name="sort">
    <option value="0" selected>-</option>
    <option value="1">By number</option>
    <option value="2">By newest date</option>
    <option value="3">By latest date</option>
</select>
<input type="submit" value="Sort">
</p>
</form>

<table style="width:40%">
<tr>
		<th>Payment number</th>
		<th>Score</th>
		<th>Recipient account</th>
		<th>Date</th>
		<th>State</th>
	</tr>
    <c:forEach var="pay" items="${payments}">
	<tr>
		<th><c:out value="${pay.paymentID}" /></th>
		<th><c:out value="${pay.score}" /></th>
		<th><c:out value="${pay.recipientAccount}"/></th>
		<th><c:out value="${pay.paymentDate}" /></th>
		<th><c:out value="${pay.paymentState}"/></th>
	</tr>

    </c:forEach>
</table>

</c:if>

<c:if test="${param.sort==null}">
<c:set var="href" value="Account?" scope="request"/>
<jsp:include page="pagination/pagination.jsp"/>
</c:if>
<c:if test="${param.sort!=null}">
<c:set var="href" value="Account?sort=${param.sort}&" scope="request"/>
<jsp:include page="pagination/pagination.jsp"/>
</c:if>
<p>${error}</p>

</body>
</html>


























