<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
</script>
<script type="text/javascript">
	function intToFloat(val){
		return new Number(val).toFixed(2);
	}
	$(function(){
		$(".supplier").change(function(){
			var supplierUuid = $(this).val();
				$.post("order_ajaxGetGtmAndGm.action",{"supplierUuid":supplierUuid},function(data){
				$(".goodsType").empty();
				$(".goods").empty();
				var gtmList = data.gtmList;
				for(var i = 0;i < gtmList.length;i++){
					var gtm = gtmList[i];
					$op = $("<option value='"+gtm.uuid+"'>"+gtm.name+"</option>");
					$(".goodsType").append($op);
				}
				
				var gmList = data.gmList;
				for(var i = 0;i < gmList.length;i++){
					var gm = gmList[i];
					$op = $("<option value='"+gm.uuid+"'>"+gm.name+"</option>");
					$(".goods").append($op);
				}
				var price = data.gm.inPriceView;
				$(".num").val(1);
				$(".prices").val(price);
				$(".total").html(price+" 元");
				total2();
			})
		});
		//$(".goodsType").change(function(){
		$(".goodsType").live("change",function(){
				var $nowTr = $(this).parent().parent();
				var $goodSelect = $nowTr.children("td:eq(1)").children("select");
				var $num = $nowTr.children("td:eq(2)").children("input");
				var $prices = $nowTr.children("td:eq(3)").children("input");
				var $total = $nowTr.children("td:eq(4)");
				
				var gtmUuid = $(this).val();
				
				var goodsArr = $(".goods");
				var used = '';
				for(var i = 0;i < goodsArr.length;i++){
					used = used+"'"+goodsArr[i].value+"',";
				}
				
				$.post("order_ajaxGetGm.action",{"gtmUuid":gtmUuid,"used":used},function(data){
				
				
				$goodSelect.empty();
				
				var gmList = data.gmList;
				for(var i = 0;i < gmList.length;i++){
					var gm = gmList[i];
					$op = $("<option value='"+gm.uuid+"'>"+gm.name+"</option>");
					$goodSelect.append($op);
				}
				var price = data.gm.inPriceView;
				$num.val(1);
				$prices.val(price);
				$total.html(price+" 元");
				total2();	
			});
		});
		//$(".goods").change(function(){
		$(".goods").live("change",function(){
				var $nowTr = $(this).parent().parent();
				var $num = $nowTr.children("td:eq(2)").children("input");
				var $prices = $nowTr.children("td:eq(3)").children("input");
				var $total = $nowTr.children("td:eq(4)");
		
				var goodsUuid = $(this).val();
				$.post("order_ajaxGetPrice.action",{"goodsUuid":goodsUuid},function(data){
				var price = data.inPriceView;
				$num.val(1);
				$prices.val(price);
				$total.html(price+" 元");
				total2();
			});
		});
		
		
		var flag = true;
		$("#add").click(function(){
			$(".supplier").attr("disabled",true);
			$(".goodsType").attr("disabled",true);
			$(".goods").attr("disabled",true);
			if(!flag){
				return ;
			}
			flag = false;
			var supplierUuid = $(".supplier").val();
			var goodsArr = $(".goods");
			var used = '';
			for(var i = 0;i < goodsArr.length;i++){
				used = used+"'"+goodsArr[i].value+"',";
			}
			
			$.post("order_ajaxGetGtmAndGm2.action",{"supplierUuid":supplierUuid,"used":used},function(data){
				$tr = $('<tr align="center" bgcolor="#FFFFFF"></tr>');
				
				$td1 = $('<td height="30"></td>');
				//加载select:class="goodsType"
				$gtmSelect = $('<select class="goodsType"></select>');
				var gtmList = data.gtmList;
				for(var i = 0;i < gtmList.length;i++){
					var gtm = gtmList[i];
					$op = $('<option value="'+gtm.uuid+'">'+gtm.name+'</option>');
					$gtmSelect.append($op);
				}
				$td1.append($gtmSelect);
				$tr.append($td1);
				
				$td2 = $('<td></td>');
				//加载select:class="goods"
				$gmSelect = $('<select name="goodsUuids" class="goods"></select>');
				var gmList = data.gmList;
				for(var i = 0;i < gmList.length;i++){
					var gm = gmList[i];
					$op = $(' <option value="'+gm.uuid+'">'+gm.name+'</option>');
					$gmSelect.append($op);
				}
				$td2.append($gmSelect);
				$tr.append($td2);
			
				var price = data.gm.inPriceView;
				$td3 = $('<td><input name="nums" class="num order_num" style="width:67px;border:1px solid black;text-align:right;padding:2px" type="text" value="1"></td>');
				$tr.append($td3);
				
				$td4 = $('<td><input name="prices" class="prices order_num" style="width:93px;border:1px solid black;text-align:right;padding:2px" type="text" value="'+price+'"> 元</td>');
				$tr.append($td4);
				
				$td5 = $('<td class="total" align="right">'+price+'&nbsp;元</td>');
				$tr.append($td5);
				
				$td6 = $('<td><a class="deleteBtn delete xiu" value="4"><img src="images/icon_04.gif"> 删除</a></td>');
				$tr.append($td6);
				
				
				$("#finalTr").before($tr);
				
				if(gtmList.length == 1 && gmList.length == 1){
					$("#add").css("display","none");
				}
				
				flag = true;
				total2();
			});
		
		});
		
		$(".deleteBtn").live("click",function(){
			if($(".deleteBtn").length == 1){
				return ;
			}
			$delTr = $(this).parent().parent();
			$delTr.remove();q
			$("#add").css("display","inline");
			/* if($(".deleteBtn").length == 1){
				$(".deleteBtn").parent().empty();
			} */
			total2();
		});
		$(".num").live("keyup",function(){
			//先把非数字的都替换掉，除了数字 
			$(this).val($(this).val().replace(/[^\d]/g,""));
		
			totalPrice($(this));
			total2();
		});
		$(".prices").live("keyup",function(){
			//先把非数字的都替换掉，除了数字和. 
			$(this).val($(this).val().replace(/[^\d.]/g,""));
	        //必须保证第一个为数字而不是. 
	        $(this).val($(this).val().replace(/^\./g,"0."));
	        //保证只有出现一个.而没有多个. 
	        $(this).val($(this).val().replace(/\.{2,}/g,"."));
	        //保证.只出现一次，而不能出现两次以上
	        $(this).val($(this).val().replace(".","$#$").replace(/\./g,"").replace("$#$",".")); 
		
			totalPrice($(this));
			total2();
		});
		
		//求总计
		function total2(){
			var numArr = $(".num");
			var pricesArr = $(".prices");
			var sum = 0;
			for(var i = 0;i < numArr.length;i++)
			{
				var total = numArr[i].value * pricesArr[i].value;
				sum += total;
			}
			$(".all").html(intToFloat(sum)+" 元");
			
		}
		
		
		function totalPrice(obj){
			var $nowTr = obj.parent().parent();
			var $num = $nowTr.children("td:eq(2)").children("input");
			var $prices = $nowTr.children("td:eq(3)").children("input");
			var $total = $nowTr.children("td:eq(4)");
			var total = $num.val() * $prices.val();
			
			$total.html(intToFloat(total)+" 元");
		}
		
		$("#submitOrder").click(function(){
			$(".supplier").attr("disabled",false);
			$(".goodsType").attr("disabled",false);
			$(".goods").attr("disabled",false);
			$("form:first").submit();
		});
		
	});
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">订单管理</span>
		</div>
	</div>
	<div class="content-text">
		<s:form action="order_buySave" method="post">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="68px" height="30">供应商：</td>
						<td width="648px">
						<s:select name="om.sm.uuid" cssClass="supplier" list="supplierList" listKey="uuid" listValue="name"/>
						</td>
						<td height="30">
							<a id="add"><img src="images/can_b_02.gif" border="0" /> </a>
						</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table id="order" width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(images/table_bg.gif) repeat-x;">
						<td width="25%" height="30">商品类别</td>
						<td width="25%">商品名称</td>
						<td width="10%">采购数量</td>
						<td width="15%">单价</td>
						<td width="15%">合计</td>
						<td width="10%">操作</td>
					</tr>
					<tr align="center" bgcolor="#FFFFFF">
						<td height="30">
							<s:select cssClass="goodsType"  list="gtmList" listKey="uuid" listValue="name"/>
						</td>
						<td>
						<s:select name="goodsUuids" cssClass="goods"  list="goodsList" listKey="uuid" listValue="name"/>
						</td>
						<td><input name="nums" class="num order_num" style="width:67px;border:1px solid black;text-align:right;padding:2px" type="text" value="1"/></td>
						<td><input name="prices" class="prices order_num" style="width:93px;border:1px solid black;text-align:right;padding:2px" type="text" value="${goodsList.get(0).inPriceView }"/> 元</td>
						<td class="total" align="right">${goodsList.get(0).inPriceView }&nbsp;元</td>
						<td>
							<a class="deleteBtn delete xiu" value="4"><img src="images/icon_04.gif" /> 删除</a>
						</td>
					</tr>
					<tr id="finalTr" align="center"
						style="background:url(images/table_bg.gif) repeat-x;">
						<td height="30" colspan="4" align="right">总&nbsp;计:&nbsp;</td>
						<td class="all" width="16%" align="right">${goodsList.get(0).inPriceView }&nbsp;元</td>
						<td>&nbsp;</td>
					</tr>
				</table>
				<div class="order-botton">
				<div style="margin:1px auto auto 1px;">
					<table width="100%"  border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td>
					    	<a href="javascript:void(0)" id="submitOrder"><img src="images/order_tuo.gif" border="0" /></a>
					    </td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="images/order_tuo.gif" border="0" /></a></td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="images/order_tuo.gif" border="0" /></a></td>
					  </tr>
					</table>
				</div>
			</div>
			</div>
		</s:form>
	</div>
	
	<div class="content-bbg"></div>
</div>
