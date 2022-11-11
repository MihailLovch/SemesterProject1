<%@tag description="Page Tag" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@attribute name="title" required="true" type="java.lang.String" %>
<%@attribute name="category" required="true" type="java.lang.String" %>
<%@attribute name="max" required="true" type="java.lang.Float" %>
<%@attribute name="consumed" required="true" type="java.lang.Float" %>
<%@attribute name="percent" required="true" type="java.lang.Integer" %>
<%@attribute name="progress" required="true" type="java.lang.String" %>

<jsp:doBody/>
<div title="${title}">
    <div>
        <span>${category}</span>
        <span>${percent}%</span>
    </div>
    <div>
        <progress id="fats-progress" max="${max}" value="${consumed}"></progress>
    </div>
    <div>
        <span> ${consumed}g </span>
        <span style="margin-left: 15px"> ${progress}g </span>

    </div>
</div>
<jsp:doBody/>