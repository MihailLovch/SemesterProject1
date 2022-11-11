<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:page title="My favorites">
    <div>
        <c:forEach var="recipe" items="${recipes}" >
            <div class="align-items-center">
                <span>${recipe.getName()}</span>
                <span >${recipe.getCalories()}</span>
                <span >${recipe.getProteins()}</span>
                <span >${recipe.getFats()}</span>
                <span>${recipe.getCarbohydrates()}</span>
            </div>
        </c:forEach>
    </div>
</t:page>
