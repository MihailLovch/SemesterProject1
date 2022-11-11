<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:page title="Enter" css="${pageContext.request.contextPath}/css/formPopOut.css" jsPath="${pageContext.request.contextPath}/javascript/popOut.js"
 logInPage="true">
<form action="" method="post">
    <t:input id="email" name="Email" type="email" value="${email}"/>
    <t:input id="password" name="Password" type="password"/>
    <input type="submit" value="Enter">
</form>
<c:if test="${not empty error}">
    <div style="color: red">
        <p>${error}</p>
    </div>
</c:if>
</t:page>
