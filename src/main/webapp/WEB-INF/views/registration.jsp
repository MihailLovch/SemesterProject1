<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:page title="Registration">
    <c:if test="${not empty notice}">
        <div style="color: green">${notice}</div>
    </c:if>
    <div>
        <form action="" method="post" class="form-container">
            <t:input id="name" name="NickName" type="text" value="${name}"/>
            <t:input id="password" name="Password" type="password"/>
            <t:input id="email" name="Email" type="email" value="${email}"/>
            <t:input id="weight" name="Weight" type="number" value="${weight}"/>
            <t:input id="height" name="Height" type="number" value="${height}"/>
            <t:input id="age" name="Age" type="datetime-local"/>
            <p>Select your sex:</p>
            <t:input id="sex" name="Male" type="radio" value="true"/>
            <t:input id="sex" name="Female" type="radio" value="false"/>
            <br>
            <input type="submit" value="Send">
        </form>
    </div>
    <c:if test="${not empty error}">
        <div style="color: red">
            <p>${error}</p>
        </div>
    </c:if>
    <c:forEach var="errors" items="${errors}">
        <div style="color: red">${errors}</div>
    </c:forEach>
</t:page>