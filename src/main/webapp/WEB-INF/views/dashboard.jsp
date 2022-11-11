<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:page title="Cabinet" css="" jsPath="">

    <main class="position-relative overflow-hidden p-3 p-md-5 m-md-3 text-center bg-light">
        <div id="all-info" style="text-align: center; position:relative; margin-top: 20px">
            <div id="calories-pb" style="display: inline-block">
                <t:progress title="Calories analysis" category="Calories" max="${user.getCalories()}"
                            consumed="${user.getConsumedCalories()}" percent="${caloriePercents}" progress="${calProgress}"/>
            </div>
            <div style="display: flex">
                <div id="pcarbs">
                    <t:progress title="Carbs analysis" category="Carbs" max="${user.getCarbs()}"
                                consumed="${user.getConsumedCarbs()}" percent="${carbPercents}" progress="${carbProgress}"/>
                </div>
                <div id="pfats">
                    <t:progress title="Fats analysis" category="Fats" max="${user.getFats()}"
                                consumed="${user.getConsumedFats()}" percent="${fatPercents}" progress="${fatProgress}"/>
                </div>
                <div id="pproteins">
                    <t:progress title="Proteins analysis" category="Proteins" max="${user.getProteins()}"
                                consumed="${user.getConsumedProteins()}" percent="${proteinPercents}" progress="${proteinProgress}"/>
                </div>
            </div>

        </div>
    </main>
</t:page>
