<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${not empty mensagens}">
    <div class="alert alert-${erro ? 'danger' : 'success'} alert-dismissible fade show mt-4" role="alert">
        <ul style="margin-bottom: 0">
            <c:forEach var="mensagem" items="${mensagens}">
                <li>${mensagem}</li>
            </c:forEach>
        </ul>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</c:if>