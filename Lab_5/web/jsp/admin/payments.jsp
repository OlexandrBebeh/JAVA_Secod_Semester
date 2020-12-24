<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Account</title>
    </head>
<body>
<jsp:include page="home.jsp"/>
<form action="Payments" method="GET">
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

<table style="width:50%">
<tr>
		<th>Payment number</th>
		<th>AccountID</th>
		<th>Score</th>
		<th>Recipient account</th>
		<th>Date</th>
		<th>State</th>
		<th>Confirmation</th>
	</tr>
    <c:forEach var="pay" items="${payments}">
	<tr>
		<th><c:out value="${pay.paymentID}" /></th>
		<th><c:out value="${pay.accountID}" /></th>
		<th><c:out value="${pay.score}" /></th>
		<th><c:out value="${pay.recipientAccount}"/></th>
		<th><c:out value="${pay.paymentDate}" /></th>
		<th><c:out value="${pay.paymentState}"/></th>
		<th>
		<c:if test="${pay.paymentState=='PREPARED'}">
		<form action="Payments" method="POST">
			<input hidden="true" name="sort" value="${param.sort}">
			<input hidden="true" name="page" value="${param.page}">
			<button type="submit" name="payment_id" value="${pay.paymentID}">Confirm</button>
		</form>
		</c:if>
		</th>
	</tr>

    </c:forEach>
</table>

<c:if test="${param.sort==null}">
<c:set var="href" value="Payments?" scope="request"/>
<jsp:include page="../pagination/pagination.jsp"/>
</c:if>
<c:if test="${param.sort!=null}">
<c:set var="href" value="Payments?sort=${param.sort}&" scope="request"/>
<jsp:include page="../pagination/pagination.jsp"/>
</c:if>
</body>
</html>


























