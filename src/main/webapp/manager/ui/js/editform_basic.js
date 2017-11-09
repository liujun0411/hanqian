//生成编辑项
function edit_basic_data(flag, obj) {
	var htmlArr = new Array();
	var title1 = $.trim($('#col_one').text());
	var title2 = $.trim($('#col_two').text());
	if (!(title1 != undefined && title1 != '')) {
		title1 = '数据类型';
	}
	if (!(title2 != undefined && title2 != '')) {
		title2 = '描述';
	}
	//如果是添加，先添加第一行
	if(flag == "add"){
		var headHtml = new Array();
		headHtml.push('<tr class="success addRows">');
		for(var i =0;i<$("#table_base thead tr").find("th").length;i++){
			if(i==0){
				headHtml.push('<td style="text-align: center;border:none;" class="tdCenter"><img class="comimg" com_id="" name="" id="" src="manager/images/common/close.png"></td>');
			}else if(i==1){
				headHtml.push('<td class="comName" style="border:none;"></td>');
			}else{
				headHtml.push('<td class="center" style="border:none;"></td>');
			}
		}
		headHtml.push('</tr>');
		$('#table_base tr').removeClass('success');
		if($('#table_base tbody tr').length == 0){
			$('#table_base tbody').append(headHtml.join(""));
		}else{
			$('#table_base tr').eq(1).before(headHtml.join(""));
		}
	}
	var type = flag=='add'?'':$.trim($(obj).parent().next().text());
    var descr = flag=='add'?'':$.trim($(obj).parent().next().next().text());
    htmlArr.push('<tr class="trEditForm_com_'+flag+'">');
	htmlArr.push('  <td colspan="8" style="padding:0; background:#FFFFFF;">');
	htmlArr.push('     <div style="background:#FFFFFF;">');
	htmlArr.push('        <table width="100%" class="editTable">');
	htmlArr.push('           <tbody>');
    htmlArr.push('                <tr>');
	htmlArr.push('	  <td align="center" style="width:10%;text-align:right;background:#FFFFFF; border:none;">');
	htmlArr.push(title1+":");
	htmlArr.push('	  </td>');
	htmlArr.push('	  <td align="center" class="ed_td" style="text-align:left;background:#FFFFFF;border:none;">');
	htmlArr.push('       <span id="content_one_'+ flag+'" class="content_td">'+type+'</span>');
    htmlArr.push('    </td>');
    htmlArr.push('    <td align="center" style="width:10%;text-align:right; background:#FFFFFF;border:none;">');
    htmlArr.push(        title2+":");
    htmlArr.push('	  </td>');
    htmlArr.push('	  <td align="center" class="ed_td" style="width:50%;text-align:left;background:#FFFFFF;border:none;">');
    htmlArr.push('       <span id="content_two_'+flag+'" class="content_td">'+descr+'</span>');
    htmlArr.push('   </td>');
    htmlArr.push('</tr>');
    if($('#qx').val()!=undefined && $('#qx').val()!=null && $.trim($('#qx').val())!='' && flag=="edit"){
    	htmlArr.push('<tr style="background:#FFFFFF;">');
        htmlArr.push('	  <td align="center" colspan="4" style="text-align:right;background:#FFFFFF;border:none;">');
	 	htmlArr.push('      <a id="edit_base_'+ flag+'" class="btn btn-primary"  href="javascript:void(0);">编  辑</a>');
		htmlArr.push('      <a id="cannel_base_'+flag+'" style="display:none;" opid='+$(obj).attr('id')+' class="btn btn-primary"  href="javascript:void(0);" style="margin-right:105px">取  消</a>');
    	htmlArr.push('	  </td>');
    	htmlArr.push('</tr>');
    }else{
        if(flag=='add'){
        	htmlArr.push('<tr style="background:#FFFFFF;">');
            htmlArr.push('	  <td align="center" colspan="4" style="text-align:right;background:#FFFFFF;border:none;">');
    	    htmlArr.push('      <a id="save_base_'+ flag+'" class="btn btn-primary"  href="javascript:void(0);">保  存</a>');
    	    htmlArr.push('      <a id="cannel_base_'+flag+'" class="btn btn-primary"  href="javascript:void(0);" style="margin-right:105px">取  消</a>');
        	htmlArr.push('	  </td>');
        	htmlArr.push('</tr>');	
        }
    }
	htmlArr.push('           </tbody>');
	htmlArr.push('        </table>');
	htmlArr.push('    </div>');
	htmlArr.push('  </td>');
	htmlArr.push('</tr>');
	var id = $(obj).attr('id');
	if(flag=='edit'){
		 $(obj).parent().parent().after(htmlArr.join(''));
		 $('#edit_base_'+flag).unbind();
		 //编辑按钮
		 $('#edit_base_'+flag).click(function(){
			$('#cannel_base_'+flag).show();
			$(this).text('保  存'); 
			replaceWithInput(flag);
			$(this).unbind('click');
			//保存按钮
			$(this).click(function(){
				addOrUpdate(id,flag);
			});
		 });
		 $('#cannel_base_'+flag).unbind();
		 $('#cannel_base_'+flag).click(function(){
			  $('#table_base tr.trEditForm_com_edit').remove();
	          $('#table_base img.comimg').attr('src', 'manager/images/common/open.png');
		 });
	}else{
		 $("#table_base tr.addRows").after(htmlArr.join(""));
		 replaceWithInput(flag);
		 $('#save_base_'+flag).unbind();
		 $('#save_base_'+flag).click(function(){
			 addOrUpdate(null,flag);
		 });
		 $('#cannel_base_'+flag).unbind();
		 $('#cannel_base_'+flag).click(function(){
			  $('#table_base tr.trEditForm_com_add').remove();
			  $("#table_base tr.addRows").remove();
		 });
	}
}

//替换文本为编辑框
function replaceWithInput(flag){
    $('#table_base').find('td.ed_td').each(function(i){
        if(i==0){
        	$(this).find('span').replaceWith('<input type="text" id="'+ $(this).find('span').attr('id') +'" value="'+ $.trim($(this).text()) +'" />');
        }else if(i==1){
            $(this).find('span').replaceWith('<textArea type="text" id="'+ $(this).find('span').attr('id') +'">'+$.trim($(this).text())+'</textArea>');
        }
    });
}

//更换类型查询
function changeType(obj){
	$('#typeId').val($(obj).val());
	$('#currentPage').val(1);
	document.myform.submit();
}

//添加或更新到数据库
function addOrUpdate(id,flag){
	if($.trim($('#content_one_'+flag).val())!=''){
	    var param = {id:id,cont1:$.trim($('#content_one_'+flag).val()),cont2:$.trim($('#content_two_'+flag).val()),type:$('#baseType_select').val(),flag:flag};
	    console.info(param);
	    $.ajax( {
			type : "POST",
			url : "baseCommAction_addOrUpdateBaseComm.action",
			data : param,
			dataType : 'json',
			cache : false,
			async : false,
			error : function(jsonObj) {
			},
			success : function(jsonObj) {
				if(jsonObj.resStr){
				   window.location.href='baseCommAction_getAllDataBase.action?typeId='+$('#baseType_select').val();
		        }else{
		           alert('操作失败');
		        }
		    }
		});
	}else{
	    alert('请填写'+$.trim($('#col_one').text()));
	}
}

function EditForm(){
	$('#table_base img.comimg').unbind('click');
	$('#table_base img.comimg').click(function(){
		$('#table_base tr.addRows').next().remove();
		$('#table_base tr.addRows').remove();
		if($(this).attr('src')=='manager/images/common/close.png'){
			$(this).attr('src', 'manager/images/common/open.png');
			$('a.btn-config-com').unbind('click');
			$('a.btn-edit-com').unbind('click');
			$('a.btn-update-com').unbind('click');
			$('#table_base tr.trEditForm_com_edit').remove();
		}else{
			$('#table_base tr.trEditForm_com_edit').remove();
        	$('#table_base img.comimg').attr('src', 'manager/images/common/open.png');
			$(this).attr('src', 'manager/images/common/close.png');
			edit_basic_data("edit",this);
		}
	});
	
	$('#btn-com-add').click(function(){
		 $('#table_base img.comimg').attr('src', 'manager/images/common/open.png');	
		 $('#table_base tr.trEditForm_com_edit').remove();
	   	 if(!$('#table_base tr').hasClass('addRows')){
	   		edit_basic_data("add",null);
	   	 }
	});
	
	$('#table_base tr').click(function(){
	   $('#table_base tr').removeClass('success');
	   if(!$(this).hasClass('success')){
		   $(this).addClass('success');
	   }
	});
	
	$('#btn-com-delete').click(function(){
	    if($('#table_base tr').hasClass('success')){
	    	var id = $('#table_base tr.success').find('img.comimg').attr('id');
	    	if(confirm("确定删除吗?")){
	    		 var param = {delId:id};
		         $.ajax( {
					type : "POST",
					url : "baseCommAction_deleteBaseData.action",
					data : param,
					dataType : 'json',
					cache : false,
					async : false,
					error : function(jsonObj) {
					},
					success : function(jsonObj) {
						if(jsonObj.resStr){
						   window.location.href='baseCommAction_getAllDataBase.action?typeId='+$('#baseType_select').val();
				        }else{
				           alert('操作失败');
				        }
				    }
				});
	    	}
	    }else{
	        alert('请选中一行后再操作!');	
	    }
	});
}

$(function(){
	EditForm();
	var pageSum = $('#pageSum').val();
	var optInit = getOptionsFromForm();
	$("#PaginationCom").pagination(pageSum, optInit);
});


function pageselectCallback(page_index, jq) {
	return false;
}

function getOptionsFromForm() {
	var opt = {
		callback : pageselectCallback
	};
	opt['items_per_page'] = 0;
	opt['num_display_entries'] = 5;
	opt['num_edge_entries'] = 2;
	opt['current_page'] = parseInt($('#currentPage').val()) - 1;
	opt['prev_text'] = '上一页';
	opt['next_text'] = '下一页';
	opt['callback_method'] = callback_method_com;
	var htmlspecialchars = {
		"&" : "&amp;",
		"<": "&lt;", ">" : "&gt;",
		'"' : "&quot;"
	};
	$.each(htmlspecialchars, function(k, v) {
		opt.prev_text = opt.prev_text.replace(k, v);
		opt.next_text = opt.next_text.replace(k, v);
	});
	return opt;
}

function callback_method_com(pageId){
	$('#currentPage').val(pageId);
	document.myform.submit();
}




