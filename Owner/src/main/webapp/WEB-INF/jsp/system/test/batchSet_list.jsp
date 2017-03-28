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
	if("${message}"=="deleteSuccess") {
		alert("删除成功！");
	}else if("${message}"=="deleteFail"){
		alert("删除失败,请稍后重试！");
	}
	
	
	function myFormReset(){
		$("#type").val("");
		$("#startTime").val("");
		$("#endTime").val("");
	}
	
	
	function formCheck(){
		var type=$("#type").val().trim();
		var startTime=$("#startTime").val();
		var endTime=$("#endTime").val();
		/* if(type=='' && startTime=='' && endTime ==''){
			alert("请输入查询参数.");
			return false;
		} */
		var startTime=$("#startTime").val();
		var endTime=$("#endTime").val();
		if(startTime!='' && endTime!=''){
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
		}
	}
	
	
	function del(id){
		if(confirm("确认要删除吗？")){
			window.location.href = "batchSet_delete.do?id="+id;
		}
	}
	
	function toAdd(){
		window.location.href="${basePath}/admin/batchSet_toAdd.do";
	}
	
	function toCheck(id) {
		//审核按钮
		window.location.href = "${basePath}/admin/batchSet_toCheck.do?id=" + id;
	}
	
	function toObdExcel(id) {
		//跳转上传升级列表页面
		window.location.href="${basePath}/admin/batchSet_toObdExcel.do?id=" + id;
	}
	
</script>
</head>
<body>
	<form name="myForm" id="myForm" action="batchSet_query.do" method="post" onsubmit="return formCheck();">
		<ul class="breadcrumb">
			<li><i class="icon-home icon-2x"></i></li>
			<li>当前位置：${currentPosition}</li>
		</ul>
		<div class="widget widget-table">
			<div class="widget-content">
				<table class="pn-ftable table-condensed" border="0" cellpadding="10">
					<tbody>
						<tr>
							<th>设置类型</th>
							<td class="pn-fcontent">
								<select name="type" id="type" style="width: 250px;">
									<option value="">全部</option>
									<option value="address" <c:if test="${type=='address'}">selected</c:if>>修改内存地址</option>
								</select>
							</td>
							<th>版本号</th>
							<td class="pn-fcontent">
								 <input type="text" size="24" id="version" name="version" value="${version}" />
							</td>
						</tr>
						
						<tr>
							<th>审核状态</th>
							<td class="pn-fcontent">
								<select name="auditState" id="auditState" style="width: 250px;">
									<option value="">全部</option>
									<option value="0" <c:if test="${auditState=='0'}">selected</c:if>>未审核</option>
									<option value="1" <c:if test="${auditState=='1'}">selected</c:if>>通过</option>
									<option value="-1" <c:if test="${auditState=='-1'}">selected</c:if>>不通过</option>
								</select>
							</td>
							<th>有效</th>
							<td class="pn-fcontent">
								 <select name="valid" id="valid" style="width: 250px;">
									<option value="">全部</option>
									<option value="0" <c:if test="${valid=='0'}">selected</c:if>>无效</option>
									<option value="1" <c:if test="${valid=='1'}">selected</c:if>>有效</option>
								</select>
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
						<button type="button" class="btn btn-primary" onclick="toAdd()">新增</button>
					</center>
				</div>
			</div>
			<!-- /widget-content -->
		</div>
		<div class="widget widget-table">
			<div class="widget-header">
				<i class="icon-th-list"></i>
				<h5>批量设置信息</h5>
			</div>
			<!-- /widget-header -->
			<div class="widget-content widget-list" style="overflow-x: scroll;">
				<table class="table table-striped table-bordered table-condensed table-hover sortable">
					<thead>
						<tr>
							<th>序号</th>
							<th>类型</th>
							<th>内容</th>
							<th>消息体</th>
							<th>版本号</th>
							<th>审核人员</th>
							<th>审核结果</th>
							<th>审核时间</th>
							<th>审核意见</th>
							<th>有效</th>
							<th>创建时间</th>
							<th>更新时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${batchSets}" var="item" varStatus="status">
							<tr>
								<td>${item.id}</td>
								<td>
									<c:choose>
										<c:when test="${item.type=='address'}">地址修改</c:when>
										<c:otherwise></c:otherwise>
									</c:choose>
								</td>
								
								<td id="'${id}'">${item.msg}</td>
								<td>${item.bodyMsg}</td>
								<td>${item.version}</td>
								<td>${item.auditOper}</td>
								<td>
									<c:choose>
										<c:when test="${item.auditState==0}">未审核</c:when>
										<c:when test="${item.auditState==1}">通过</c:when>
										<c:when test="${item.auditState==-1}">不通过</c:when>
										<c:otherwise></c:otherwise>
									</c:choose>
								</td>
								<td><fmt:formatDate value="${item.auditTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>${item.auditMsg}</td>
								<td>
									<c:choose>
										<c:when test="${item.valid==0}">0-无效</c:when>
										<c:when test="${item.valid==1}">1-有效</c:when>
										<c:otherwise></c:otherwise>
									</c:choose>
								</td>
								<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td><fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>
									<c:if test="${item.valid=='1'}">
										<a href="javascript:del('${item.id}',${pager.pageSize},${pager.currentPage});" class="btn btn-warning"><i class="icon-trash"></i>&nbsp;&nbsp;删除</a>
									</c:if>
									<c:if test="${item.auditState== 0}"> 
										<button type="button" class="btn" onclick="toCheck('${item.id}');" auth="auth-push" >审核</button>&nbsp;
									</c:if>
									<c:if test="${item.auditState==1}"> 
										<button type="button" class="btn" onclick="toObdExcel('${item.id}');" auth="auth-push">升级列表上传</button>&nbsp;
									</c:if>
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