<%@tag description="Page Tag" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@attribute name="display" required="true" type="java.lang.String" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<div class="form-popup" id="myForm" style="display: ${display}">
    <form action="" method="post" class="form-container">
        <button name="cancel" id="cancel" class="cancel">Close</button>
        <h1>Login</h1>
        <t:input id="email" name="Email" type="text" value="${logEmail}"/>
        <t:input id="password" name="Password" type="password" value="${email}"/>
        <c:if test="${not empty logError}">
            <div style="color: red">${logError}</div>
        </c:if>
        <c:forEach var="errors" items="${logErrors}">
            <div style="color: red">${errors}</div>
        </c:forEach>
        <button type="submit" name="login" class="btn-warning">Login</button>
    </form>
</div>