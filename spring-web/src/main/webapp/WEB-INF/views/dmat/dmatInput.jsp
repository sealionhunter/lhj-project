<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>

<p class="clearfloat"></p>
<div>D-MAT填写</div>
<p class="clearfloat"></p>
<div id="mainContent" class="contentblock">
    <div id="dayPicker" style="float:left;padding-right: 4px;padding-top:5px;padding-bottom:10px"></div>
    <div style="float:right;padding-left:5px;padding-top:5px;padding-bottom:10px">
        <div id="dmatDetailNavigation">
            <c:url var="previousLink" value="/dmat/dmatInput">
                <c:param name="day" value="${dmatCalendar.previousDay}" />
            </c:url>
            <a href="${ previousLink }">previous</a>
            <c:url var="nextLink" value="/dmat/dmatInput">
                <c:param name="day" value="${dmatCalendar.nextDay}" />
            </c:url>
            <a href="${ nextLink }">next</a>
        </div>
        <div id="tableDiv">
            <table id="tblProj">
                <thead>
                    <tr>
                        <th><span>部门</span></th>
                        <th><span>项目</span></th>
                        <th><span>大分类</span></th>
                        <th><span>小分类</span></th>
                        <th><span>时间</span></th>
                        <th><span>内容</span></th>
                    </tr>
                </thead>
                
                <c:forEach var="detail" items="${dmatCalendar.details }" varStatus="i">
                    <tr class="${i.index % 2 == 0 ? 'even' : ''}" data-dmatId="${detail.dmatId }"
                        data-guestDeptId="${detail.guestDeptId }" data-projectId="${detail.projectId }"
                        data-categoryId="${detail.categoryId }" data-subCategoryId="${detail.subcategoryId }">
                        <td>${detail.guestDeptName }</td>
                        <td>${detail.projectName }</td>
                        <td>${detail.categoryName }</td>
                        <td>${detail.subcategoryName }</td>
                        <td><joda:format value="${detail.localStartDate }" pattern="yyyy-MM-dd" /></td>
                        <td>${detail.duration }</td>
                        <td>${detail.details }</td>
                    </tr>
                </c:forEach>
            </table>
            <input type="button" class="btnNew" value="追加" id="addBtn"/>
        </div>

        <div class="btnbar">
            合计：<span id="sum" class="totaltime">0.5</span>小时
        </div>
    </div>
</div>
<div id="addDialog" title="Add Detail">
    <form id="addForm" action="<c:url value="/dmat/dmatInput" />" method="POST">
        <fieldset>
            <p>部门:
            <select id="guestDeptId" name="guestDeptId">
                <option value="-1"></option>
                <option value="1">CS1</option>
                <option value="2">CS2</option>
                <option value="3">CS3</option>
                <option value="4">CS4</option>
            </select>
            </p>
            <p>项目:
            <select id="projectId" name="projectId">
                <option value="-1"></option>
                <option value="1">DALM</option>
                <option value="2">DM120</option>
                <option value="3">AM120</option>
                <option value="4">LM120</option>
                <option value="5">DM(FC)</option>
                <option value="6">AM(FC)</option>
                <option value="7">LM(FC)</option>
            </select>
            </p>
            <p>大分类:
            <select id="categoryId" name="categoryId">
                <option value="-1"></option>
                <option value="1">Planning and Tracking</option>
            </select>
            </p>
            <p>小分类:
            <select id="subcategoryId" name="subcategoryId">
                <option value="-1"></option>
                <option value="1">Project Tracking</option>
                <option value="2">Estimation</option>
            </select></p>
            <p>时间:<input id="duration" name="duration" type="text" /></p>
            <p>内容:<textarea id="details" name="details" ></textarea></p>
            
            <p><input id="addButton" type="submit" value="追加" class="btnNew" /></p>
            <input type="hidden" id="userId" name="userId" value='<sec:authentication property="principal.username" />' />
            <input type="hidden" id="startDate" name="startDate" value='<joda:format value="${dmatCalendar.day}" />' />
        </fieldset>
    </form>
</div>

<p class="clearfloat"></p>

<script type="text/javascript">
	$(document).ready(function() {
		$("#dayPicker").datepicker({
			firstDay: 1,
			dateFormat: "yy-mm-dd",
			defaultDate: new Date(${dmatCalendar.dayMillis}),
			onSelect: function(dateText, instance) {
				window.location = "${pageContext.request.contextPath}/dmat/dmatInput?day=" + encodeURIComponent(dateText);
			}
		});
	});
	
	var showAddForm = function() {
		$("#addDialog").dialog("open");
		$("#guestDeptName").focus();
	}
	$("#addBtn").click(showAddForm);
	
	$("#addDialog").dialog({
		autoOpen: false,
		modal: true,
		close: function(event, ui) {
			$("#addForm")[0].reset();
		}			
	});
	
	$("#addButton").click(function(){
	    $.post("${pageContext.request.contextPath}/dmat/dmatInput", $("#addForm").serialize(), function(data) {
			$("#addDialog").dialog('close');
		});	
		return false;
	    
	});
	
</script>

