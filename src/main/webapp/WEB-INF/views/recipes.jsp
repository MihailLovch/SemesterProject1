<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:page css="${pageContext.request.contextPath}/css/main.css" title="Recipes list"
        jsPath="${pageContext.request.contextPath}/javascript/recipeSlider.js">
<c:if test="${not empty error}">
    <div style="color: red; margin-top: 100px"> ${error}</div>
</c:if>
    <form action="" method="post" id="my-form" style="margin-top: 100px;">
        <c:forEach var="recipe" items="${recipes}" varStatus="loop">
            <div class="align-items-center">
                <span>${recipe.getName()}</span>
                <span id="calorie${loop.index}">Calories: ${recipe.getCalories()}</span>
                <span id="protein${loop.index}">Proteins: ${recipe.getProteins()}</span>
                <span id="fat${loop.index}">Fats: ${recipe.getFats()}</span>
                <span id="carb${loop.index}">Carbs: ${recipe.getCarbohydrates()}</span>
                <c:if test="${not empty user}">
                    <input type="submit" value="Eat it" id="submit${loop.index}" name="submit${loop.index}">
                    <c:if test="${loop.index < baseRecipesAmount}">
                        <input type="submit" value="Add to my preferred" name="preferred${loop.index}">
                    </c:if>
                    <div class="slidecontainer">
                        <input type="range" min="1" max="1000" value="100" class="slider" id="myRange${loop.index}">
                        <span id="grams${loop.index}"> </span>
                    </div>
                    <input name="grams${loop.index}" style="display: none" id="gram${loop.index}" value="100">
                </c:if>
            </div>
        </c:forEach>
    </form>
    <c:if test="${not empty user}">
        <a href="<c:url value='/recipes/add'/>" class="btn btn-warning" role="button">Add new recipe</a>
    </c:if>
    <div class="form-popup" id="myForm" style="display: ${display}">
        <form action="" method="post" class="form-container">
            <h1>Login</h1>

            <label for="email"><b>Email</b></label>
            <input type="text" placeholder="Enter Email" id="email" name="email" required value="${email}">

            <label for="psw"><b>Password</b></label>
            <input type="password" placeholder="Enter Password" id="psw" name="password" required>
            <c:if test="${not empty error}">
                <div style="color: red">${error}</div>
            </c:if>
            <button type="submit" name="login" class="btn-warning">Login</button>
        </form>
    </div>

</t:page>
