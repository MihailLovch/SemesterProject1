<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:page css="${pageContext.request.contextPath}/css/main.css" title="Main Page">
<main>
        <div class="position-relative overflow-hidden p-3 p-md-5 m-md-3 text-center bg-light">
            <div class="col-md-5 p-lg-5 mx-auto my-5">
                <h1 class="display-4 fw-normal">Punny headline</h1>
                <p class="lead fw-normal">And an even wittier subheading to boot. Jumpstart your marketing efforts with this example based on Apple’s marketing pages.</p>
            </div>
            <div class="product-device shadow-sm d-none d-md-block"></div>
            <div class="product-device product-device-2 shadow-sm d-none d-md-block"></div>
        </div>

        <div class=".d-flex flex-md-equal w-100 my-md-3 ps-md-3">
            <div class="bg-dark me-md-3 pt-3 px-3 pt-md-5 px-md-5 text-center text-white overflow-hidden">
                <div class="my-3 py-3">
                    <h2 class="display-5">Another headline</h2>
                    <p class="lead">And an even wittier subheading.</p>
                </div>
                <div class="bg-light shadow-sm mx-auto" style="width: 80%; height: 300px; border-radius: 21px 21px 0 0;"></div>
            </div>
        </div>
</main>

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
            <button type="submit"  class="btn-warning">Login</button>
        </form>
    </div>
</t:page>
