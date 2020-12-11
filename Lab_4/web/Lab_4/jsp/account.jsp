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

<form action="Account?AccountName=${account.accountName}" method="POST">
<p>
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

<c:if test="${page!=null}">
<c:choose>


	<c:when test="${page==1}">
		<c:if test="${lastPage==page+1}">
			<p><c:out value="${page}" /> <a href="Account?AccountName=${account.accountName}&page=${page+1}">next</a> </p>
		</c:if>
			<c:if test="${lastPage!=page+1}">
			<p><c:out value="${page}" /> <a href="Account?AccountName=${account.accountName}&page=${page+1}">next</a> ... <a href="Account?AccountName=${account.accountName}&page=${lastPage}">last</a></p>
		</c:if>
	</c:when>
	
	<c:when test="${page==lastPage}">
		<c:if test="${1==page-1}">
			<p><a href="Account?AccountName=${account.accountName}&page=${page-1}">prev</a> <c:out value="${page}"/></p>
		</c:if>
			<c:if test="${1!=page-1}">
			<p><a href="Account?AccountName=${account.accountName}">first</a> ... <a href="Account?AccountName=${account.accountName}&page=${page-1}">prev</a> <c:out value="${page}" /> </p>
		</c:if>
	</c:when>
	
	<c:otherwise>
		<p><a href="Account?AccountName=${account.accountName}">first</a> ... <a href="Account?AccountName=${account.accountName}&page=${page-1}">prev</a> <c:out value="${page}" /> <a href="Account?AccountName=${account.accountName}&page=${page+1}">next</a> ... <a href="Account?AccountName=${account.accountName}&page=${lastPage}">last</a>
	</c:otherwise>

</c:choose>
</c:if>



<c:if test="${account.blocked==true}">
	<p>${error}</p>
</c:if>
</body>
</html>


























