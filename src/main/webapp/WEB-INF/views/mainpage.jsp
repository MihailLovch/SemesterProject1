<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:page css="${pageContext.request.contextPath}/css/formPopOut.css" title="Main Page">
<main>
        <div class="position-relative overflow-hidden p-3 p-md-5 m-md-3 text-center bg-light">
            <div class="col-md-5 p-lg-5 mx-auto my-5">
                <h1 class="display-4 fw-normal">First headline</h1>
                <p class="lead fw-normal">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Adipisci consequatur debitis dolores dolorum eos et, excepturi fuga fugiat harum ipsa iure mollitia odit quaerat quos reprehenderit soluta sunt unde voluptatum? Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus autem, consequatur cum delectus dicta dolor enim ex facere, fugit iure maiores nesciunt nobis perferendis quia quibusdam quo reprehenderit tenetur voluptates?</p>
            </div>
            <div class="product-device shadow-sm d-none d-md-block"></div>
            <div class="product-device product-device-2 shadow-sm d-none d-md-block"></div>
        </div>

        <div class=".d-flex flex-md-equal w-100 my-md-3 ps-md-3">
            <div class="bg-dark me-md-3 pt-3 px-3 pt-md-5 px-md-5 text-center text-white overflow-hidden">
                <div class="my-3 py-3">
                    <h2 class="display-5">Another headline</h2>
                    <p class="lead">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Alias dolorum id nemo nisi pariatur quibusdam saepe similique sunt. Magni maiores quos sapiente. Beatae deserunt id iusto odit omnis optio perspiciatis? Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aspernatur atque, deserunt dolor illo in magnam magni minima nemo omnis quos repudiandae voluptatem voluptatibus voluptatum? Ab asperiores officia quod! Cumque, minima.</p>
                </div>
                <div class="bg-light shadow-sm mx-auto" style="width: 80%; height: 300px; border-radius: 21px 21px 0 0;"></div>
            </div>
        </div>
</main>

    <t:logInForm display="${display}"/>
</t:page>
