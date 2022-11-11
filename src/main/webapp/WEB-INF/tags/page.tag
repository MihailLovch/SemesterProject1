<%@tag description="Page Tag" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="title" required="true" type="java.lang.String"%>
<%@attribute name="css" required="false" type="java.lang.String"%>
<%@attribute name="jsPath" required="false" type="java.lang.String"%>
<%@attribute name="logInPage" required="false" type="java.lang.Boolean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="${css}">
    <script src="${pageContext.request.contextPath}/javascript/popOut.js"></script>
    <script src="${jsPath}"></script>
</head>
<body>
<header class="p-3 bg-secondary text-white fixed-top mb-3">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="<c:url value='/'/>" class="nav-link px-2 text-white">Home</a></li>
                <li><a href="<c:url value='/dashboard'/> " class="nav-link px-2 text-white">Dashboard</a></li>
                <li><a href="<c:url value='/recipes'/>" class="nav-link px-2 text-white">Recipes</a></li>
                <li><a href="<c:url value="/favoriteRecipes"/>" class="nav-link px-2 text-white">Favorite recipes</a></li>
                <li><a href="<c:url value='/profile'/>" class="nav-link px-2 text-white">Profile</a></li>
            </ul>
            <c:if test="${empty user and empty logInPage}">
                <button type="button" id="signIn" class="btn btn-outline-light me-2">Login</button>
            </c:if>
            <c:if test="${empty user}">
            <div class="text-end">
                <a href="<c:url value='/reg?'/>" class="btn btn-warning" role="button">Sign-up</a>
            </div>
            </c:if>

        </div>
    </div>
</header>
<div style="margin-top: 80px">
<jsp:doBody/>
</div>
</body>
</html>
