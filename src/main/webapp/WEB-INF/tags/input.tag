<%@tag description="Page Tag" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@attribute name="name" required="true" type="java.lang.String" %>
<%@attribute name="id" required="true" type="java.lang.String" %>
<%@attribute name="type" required="true" type="java.lang.String" %>
<%@attribute name="value" required="false" %>
<%@attribute name="min" required="false" type="java.lang.Integer" %>

<jsp:doBody/>
<label for="${id}"><b>${name}</b></label>
<input type="${type}" placeholder="Enter ${id}" id="${id}" name="${id}"
       <c:if test="${not empty name}">value="${value}"</c:if> min="${min}" required>

<jsp:doBody/>