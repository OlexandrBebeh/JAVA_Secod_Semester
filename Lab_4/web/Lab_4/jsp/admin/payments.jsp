<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Account</title>
    </head>
<body>
<jsp:include page="home.jsp"/>
<form action="Payments" method="POST">
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
		<th>AccountID</th>
		<th>Score</th>
		<th>Recipient account</th>
		<th>Date</th>
		<th>State</th>
	</tr>
    <c:forEach var="pay" items="${payments}">
	<tr>
		<th><c:out value="${pay.paymentID}" /></th>
		<th><c:out value="${pay.accountID}" /></th>
		<th><c:out value="${pay.score}" /></th>
		<th><c:out value="${pay.recipientAccount}"/></th>
		<th><c:out value="${pay.paymentDate}" /></th>
		<th><c:out value="${pay.paymentState}"/></th>
	</tr>

    </c:forEach>
</table>

<c:set var="href" value="Payments?sort=0${sort}" scope="request"/>
<jsp:include page="../pagination/pagination.jsp"/>

</body>
</html>


























