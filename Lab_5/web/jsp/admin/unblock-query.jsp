<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Unblock-Query</title>
    </head>
<body>
<jsp:include page="home.jsp"/>
<c:if test="${unblockQuery!=null}">
<table style="width:50%">
	<tr>
		<th>AccountID</th>
		<th>ClientID</th>
		<th>Account name</th>
		<th>Credit card number</th>
		<th>Score</th>
		<th>Blocked</th>
	</tr>
   <c:forEach var="acc" items="${unblockQuery}">

	<tr>
		<th><c:out value="${acc.accountID}" /></th>
		<th><c:out value="${acc.clientID}" /></th>
		<th><c:out value="${acc.accountName}" /></th>
		<th><c:out value="${acc.creditCardNumber}" /></th>
		<th><c:out value="${acc.score}" /></th>
		<th><c:out value="${acc.blocked}" /></th>
		<th>
			<form action="Unblock-Query" method="POST">
				 <button type="submit" name="unblock" value="${acc.accountID}">Unblock</button>
			</form>
		</th>
	</tr>

    </c:forEach>
</table>
</c:if>
<c:if test="${unblockQuery==null}">
<p>Query is empty!</p>
</c:if>

<c:set var="href" value="Unblock-Query?" scope="request"/>
<jsp:include page="../pagination/pagination.jsp"/>


	<p>${error}</p>

</body>
</html>


























