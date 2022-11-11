<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:page title="Profile" css="${pageContext.request.contextPath}/css/profile.css">
    <t:profile edit="true"/>
<%--    <form action="" method="post">--%>
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
<%--                                        <input type="text" class="margin-left-4" value="${user.getName()}" name="name" required>--%>
<%--                                </div>--%>
<%--                                <div class="media">--%>
<%--                                    <label>Email</label>--%>
<%--                                    <p>${user.getEmail()}</p>--%>
<%--                                </div>--%>
<%--                                <div class="media">--%>
<%--                                    <label>Date of birth</label>--%>
<%--                                    <input type="datetime-local" class="margin-left-4" value="${user.getDateOfBirth().toString()}" name="age" required>--%>
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
<%--                <div class="counter">--%>
<%--                    <div class="row">--%>
<%--                        <div class="col-6 col-lg-3">--%>
<%--                            <div class="count-data text-center">--%>
<%--                                <input type="number" class="count h2" name="weight" value="${user.getWeight()}" required>--%>
<%--                                <p class="m-0px font-w-600">Weight</p>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="col-6 col-lg-3">--%>
<%--                            <div class="count-data text-center">--%>
<%--                                <input type="number" class="count h2" name="height" value="${user.getHeight()}" required>--%>
<%--                                <p class="m-0px font-w-600">Height</p>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="col-6 col-lg-3">--%>
<%--                            <div class="count-data text-center">--%>
<%--                                <p class="count h2" type="number">${user.getAge()}</p>--%>
<%--                                <p class="m-0px font-w-600">Age</p>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="col-6 col-lg-3">--%>
<%--                            <div class="count-data text-center">--%>
<%--                                <select class="count h2" name="sex">--%>
<%--                                    <option value="true">Male</option>--%>
<%--                                    <option value="false">Female</option>--%>
<%--                                </select>--%>
<%--                                <p class="m-0px font-w-600">Sex</p>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>

<%--        </div>--%>
<%--    </section>--%>
<%--        <c:if test="${not empty notice}">--%>
<%--            <div style="color: red">--%>
<%--                <p>${notice}</p>--%>
<%--            </div>--%>
<%--        </c:if>--%>
<%--        <c:forEach var="errors" items="${errors}">--%>
<%--            <div style="color: red">${errors}</div>--%>
<%--        </c:forEach>--%>
<%--        <input type="submit" class="btn btn-outline-dark me-2" value="Submit Changes">--%>
<%--    </form>--%>

</t:page>
