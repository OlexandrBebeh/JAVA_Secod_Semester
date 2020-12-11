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
		<th>AccountID</th>
		<th>ClientID</th>
		<th>Account name</th>
		<th>Credit card number</th>
		<th>Score</th>
		<th>Blocked</th>
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
			<form action="Home" method="POST">
				 <button type="submit" name="unblock" value="${acc.accountID}">Unblock</button>
			</form>
		</th>
	</tr>

    </c:forEach>
</table>


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


























