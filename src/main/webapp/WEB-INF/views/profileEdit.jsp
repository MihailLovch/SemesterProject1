<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:page title="Profile" css="${pageContext.request.contextPath}/css/profile.css">
    <t:profile edit="true"/>
</t:page>
