<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:page title="Profile" css="${pageContext.request.contextPath}/css/profile.css" jsPath="${pageContext.request.contextPath}/javascript/deleteConfirmation.js">
    <t:profile edit="false"/>
<%--    <section class="section about-section gray-bg" id="about">--%>
<%--        <div class="container">--%>
<%--            <div class="row align-items-center flex-row-reverse">--%>
<%--                <div class="col-lg-6">--%>
<%--                    <div class="about-text go-to">--%>
<%--                        <h3 class="dark-color">About You</h3>--%>
<%--                        <h6 class="theme-color lead">Here presented your personal information, that you mentioned while--%>
<%--                            registration</h6>--%>
<%--                        <p>Here you can see and change your parameters by editing them and clicking submit button.</p>--%>
<%--                        <div class="row about-list">--%>
<%--                            <div class="col-md-6">--%>
<%--                                <div class="media">--%>
<%--                                    <label>Nickname</label>--%>
<%--                                    <p>${user.getName()}</p>--%>
<%--                                </div>--%>
<%--                                <div class="media">--%>
<%--                                    <label>Email</label>--%>
<%--                                    <p>${user.getEmail()}</p>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="col-lg-6">--%>
<%--                    <div class="about-avatar">--%>
<%--                        <img src="https://bootdey.com/img/Content/avatar/avatar7.png" title="" alt="">--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <form>--%>
<%--                <div class="counter">--%>
<%--                    <div class="row">--%>
<%--                        <div class="col-6 col-lg-3">--%>
<%--                            <div class="count-data text-center">--%>
<%--                                <h6 class="count h2" data-to="500" data-speed="500">${user.getWeight()}</h6>--%>
<%--                                <p class="m-0px font-w-600">Weight</p>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="col-6 col-lg-3">--%>
<%--                            <div class="count-data text-center">--%>
<%--                                <h6 class="count h2" data-to="150" data-speed="150">${user.getHeight()}</h6>--%>
<%--                                <p class="m-0px font-w-600">Height</p>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="col-6 col-lg-3">--%>
<%--                            <div class="count-data text-center">--%>
<%--                                <h6 class="count h2" data-to="850" data-speed="850">${user.getAge()}</h6>--%>
<%--                                <p class="m-0px font-w-600">Age</p>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="col-6 col-lg-3">--%>
<%--                            <div class="count-data text-center">--%>
<%--                                <h6 class="count h2" data-to="190" data-speed="190">--%>
<%--                                    <c:choose>--%>
<%--                                        <c:when test="${user.getSex()}">Male</c:when>--%>
<%--                                        <c:when test="${not user.getSex()}">Female</c:when>--%>
<%--                                    </c:choose></h6>--%>
<%--                                <p class="m-0px font-w-600">Sex</p>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </form>--%>
<%--        </div>--%>
<%--    </section>--%>
<%--    <a href="<c:url value="/signOut"/>" class="btn btn-outline-dark me-2">Sign Out</a>--%>
<%--    <a href="<c:url value="/profile/edit"/>" class="btn btn-outline-dark me-2">Edit Profile</a>--%>
<%--    <form action="" method="post" id="form">--%>
<%--        <input type="submit" class="btn btn-outline-danger" value="Delete Profile" id="delete" name="delete">--%>
<%--    </form>--%>
</t:page>
