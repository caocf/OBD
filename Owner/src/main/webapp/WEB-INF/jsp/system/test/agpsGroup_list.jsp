<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<jsp:include page="../../include/common.jsp" />

<script language="javascript">
	function myFormReset(){
		$("#groupNum").val("");
		$("#startTime").val("");
		$("#endTime").val("");
	}
	
	
	function formCheck(){
		var groupNum=$("#groupNum").val().trim();
		var startTime=$("#startTime").val();
		var endTime=$("#endTime").val();
		/* if(startTime=='' || endTime ==''){
			alert("请选择开始时间和结束时间.");
			return false;
		} */

		if(startTime!=''&&endTime!='' ){
			//查询只能查询一周的记录
			var a = startTime.split(" ");     
			var b = a[0].split("-");     
			var c = a[1].split(":");     
			var oldTime = new Date(b[0], b[1]-1, b[2], c[0], c[1], c[2]);  

			var aa = endTime.split(" ");     
			var bb = aa[0].split("-");     
			var cc = aa[1].split(":");     
			var newTime = new Date(bb[0], bb[1]-1, bb[2], cc[0], cc[1], cc[2]);  
			var days = parseInt((newTime.getTime()-oldTime.getTime()) / (1000 * 60 * 60 * 24));
			if(newTime<=oldTime){
				alert("结束时间不能大于开始时间.");
				return false;
			}
			/* if(days > 31){
				alert("日期范围应在31天之内.");
				return false;
			} */
		}
		
	}
	
</script>
</head>
<body>
	<form name="myForm" id="myForm" action="agpsGroup_query.do;" method="post" onsubmit="return formCheck();">
		<ul class="breadcrumb">
			<li><i class="icon-home icon-2x"></i></li>
			<li>当前位置：${currentPosition}</li>
		</ul>
		<div class="widget widget-table">
			<div class="widget-content">
				<table class="pn-ftable table-condensed" border="0" cellpadding="10">
					<tbody>
						<tr>
							<th>分组编号</th>
							<td class="pn-fcontent">
								 <input type="text" size="24" id="groupNum" name="groupNum" value="${groupNum}" />
							</td>
							<th></th>
							<td class="pn-fcontent">
							</td>
						</tr>
						
						
						<tr>
							<th>开始时间</th>
							<td class="pn-fcontent">
								<input type="text" value="${startTime}" class="Wdate" readonly="readonly" name="startTime" id="startTime" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d %H:%m:%s'})"/>
							</td>
							<th>结束时间</th>
							<td class="pn-fcontent">
								<input type="text" value="${endTime}" class="Wdate" readonly="readonly" name="endTime" id="endTime"  onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d %H:%m:%s'})" />
							</td>
						</tr>
					</tbody>
				</table>
				<div class="widget-bottom">
					<center>
						<input class="btn btn-s-md btn-success" type="submit" value="查 询" />&nbsp;
						<input class="btn btn-danger pull-center" type="button" value="重置" onclick="myFormReset();"/>&nbsp;
					</center>
				</div>
			</div>
			<!-- /widget-content -->
		</div>
		<div class="widget widget-table">
			<div class="widget-header">
				<i class="icon-th-list"></i>
				<h5>agps分组信息</h5>
			</div>
			<!-- /widget-header -->
			<div class="widget-content widget-list" style="overflow-x: scroll;">
				<table class="table table-striped table-bordered table-condensed table-hover sortable">
					<thead>
						<tr>
							<th>序号</th>
							<th>分组编号</th>
							<th>数据大小</th>
							<th>创建时间</th>
							<th>更新时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${agpsGroupList}" var="item" varStatus="status">
							<tr>
								<td>${item.id}</td>
								<td>${item.groupNum}</td>
								<td>${item.size}</td>
								<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>
									<fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="widget-bottom">
					<jsp:include page="../../include/pager.jsp" />
				</div>
			</div>
			<!-- /widget-content -->
		</div>
	</form>
</body>
</html>