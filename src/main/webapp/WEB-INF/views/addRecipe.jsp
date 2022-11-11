<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:page title="Add new recipe">
    <div>
        <form action="" method="post" class="form-container">
            <t:input id="name" name="Name" type="text" value="${name}"/>
            <t:input id="calorie" name="Calories" type="number" min="0"/>
            <t:input id="fat" name="Fats" type="number" min="0"/>
            <t:input id="carb" name="Carbs" type="number" min="0"/>
            <t:input id="protein" name="Proteins" type="number" min="0"/>

            <c:if test="${not empty error}">
                <div style="color: red">${error}</div>
            </c:if>
            <button type="submit" name="add" class="btn-warning">Add</button>
        </form>
    </div>
</t:page>
