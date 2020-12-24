<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Pagination</title>
    </head>
<body>

<c:if test="${page!=null}">
<c:choose>

	<c:when test="${page==1}">
		<c:if test="${lastPage==page+1}">
			<p><c:out value="${page}" /> <a href="${href}page=${page+1}">next</a> </p>
		</c:if>
			<c:if test="${lastPage!=page+1}">
			<p><c:out value="${page}" /> <a href="${href}page=${page+1}">next</a> ... <a href="${href}page=${lastPage}">last</a></p>
		</c:if>
	</c:when>
	
	<c:when test="${page==lastPage}">
		<c:if test="${1==page-1}">
			<p><a href="${href}page=${page-1}">prev</a> <c:out value="${page}"/></p>
		</c:if>
			<c:if test="${1!=page-1}">
			<p><a href="${href}">first</a> ... <a href="${href}page=${page-1}">prev</a> <c:out value="${page}" /> </p>
		</c:if>
	</c:when>
	
	<c:otherwise>
		<p><a href="${href}">first</a> ... <a href="${href}page=${page-1}">prev</a> <c:out value="${page}" /> <a href="${href}page=${page+1}">next</a> ... <a href="${href}page=${lastPage}">last</a>
	</c:otherwise>

</c:choose>
</c:if>

</body>
</html>


























