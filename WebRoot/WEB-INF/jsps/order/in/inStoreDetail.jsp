<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	$(function(){
		var uuidArr = new Array();
		var nameArr = new Array();
		var i = 0;
		<s:iterator value="storeList">
			uuidArr[i] = ${uuid};
			nameArr[i] = "${name}";
			i++;
		</s:iterator>
		
	
		$(".oper").click(function(){
			$(".in").remove();
		
			$newTr = $(this).parent().parent();
			
			var odmUuid = $(this).attr("odm");
			
			$.post("orderDetail_ajaxGetSurplus",{"om.uuid":odmUuid},function(data){
				var surplus = data.surplus;
				
				$tr = $('<tr class="in"></tr>');
			
				$td1 = $('<td align="right">仓库：</td>');
				$tr.append($td1);
				
				$td2 = $('<td height="30"></td>');
				$select = $('<select id="store" style="width:200px"></select>');
				
				for(var i = 0;i < uuidArr.length;i++){
					$opt = $('<option value="'+uuidArr[i]+'">'+nameArr[i]+'</option>');
					$select.append($opt);
				}
				$td2.append($select);
				$tr.append($td2);
				
				$td3 = $('<td align="right">入库量：</td>');
				$tr.append($td3);
				
				$td4 = $('<td><input id="inNum" type="text" value="'+surplus+'"></td>');
				$tr.append($td4);
				
				$td5 = $('<td align="center"><a href="javascript:void(0)" class="ajaxIn xiu"><img src="images/icon_3.gif">确定</a></td>');
				$tr.append($td5);
				
				$newTr.after($tr); 
				
			});
		});
		
		$(".ajaxIn").live("click",function(){
			$nowTr = $(this).parent().parent();
			$preTr = $nowTr.prev();
			var jsonParam = {};
			jsonParam["num"] = $("#inNum").val();
			jsonParam["storeUuid"] = $("#store").val();
			jsonParam["odmUuid"] = $(this).parent().parent().prev().attr("odm");
			$.post("order_ajaxInStore.action",jsonParam,function(data){
				var num = data.num;
				var surplus = data.surplus;
				
				if($(".ins").length == 1 && surplus == 0){
					$("#allInTitle").show();
					$("#return").show();
					$("#inOrderTitle").hide();
					$("#inOrder").hide();
				}
				
				if(surplus == 0){
					$nowTr.remove();
					$preTr.remove();
				}
				
				
				$preTr.children("td:eq(2)").html(num-surplus);
				$preTr.children("td:eq(3)").html(surplus);
				$nowTr.children("td:eq(3)").children("input").val(surplus);
				
				
				
			
			});
		});
	});
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">入库</span>
		</div>
	</div>
	<div class="content-text">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td>订 单 号:</td>
						<td class="order_show_msg">${om.orderNum }</td>
						<td>商品总量:</td>
						<td class="order_show_msg">${om.totalNum }</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<center id="inOrderTitle" style="text-decoration:underline;font-size:16px; font-weight:bold; font-family:"黑体";">&nbsp;&nbsp;&nbsp;&nbsp;单&nbsp;&nbsp;据&nbsp;&nbsp;明&nbsp;&nbsp;细&nbsp;&nbsp;&nbsp;&nbsp;</center>
				<br/>
				<table id="inOrder" width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(images/table_bg.gif) repeat-x;">
						<td width="20%" height="30">商品名称</td>
						<td width="30%">总数量</td>
						<td width="10%">已入库数量</td>
						<td width="30%">剩余数量</td>
						<td width="10%">入库</td>
					</tr>
					<s:iterator value="om.odms">
						<s:if test="surplus > 0">
							<tr class="ins" odm="${uuid }" align="center" bgcolor="#FFFFFF">
								<input type="hidden" value=1/>
								<input type="hidden" value=2/>
								<td height="30">${gm.name }</td>
								<td>${num }</td>
								<td>${num-surplus }</td>
								<td>${surplus }</td>
								<td><a odm="${uuid }" href="javascript:void(0)" class="oper xiu"><img src="images/icon_3.gif" />入库</a></td>
							</tr>
						</s:if>
					</s:iterator>
				</table>
				
				<center id="allInTitle" style="display:none;font-size:16px; font-weight:bold; font-family:"黑体";">&nbsp;&nbsp;&nbsp;&nbsp;全&nbsp;&nbsp;部&nbsp;&nbsp;入&nbsp;&nbsp;库&nbsp;&nbsp;&nbsp;&nbsp;</center>
				<table id="return" style="display:none" >
					<tr>
						<td>&nbsp;</td>
						<td width="100%" align="center">
							<a action="list.jsp" style="color:#f00;font-size:20px;padding-top:2px;font-weight:bold;text-decoration:none;width:82px;height:28px;display:block;background:url(images/btn_bg.jpg)">
								返回
							</a>
						</td>
						<td>&nbsp;</td>
					</tr>
				</table>
			</div>
	</div>
	<div class="content-bbg"></div>
</div>
