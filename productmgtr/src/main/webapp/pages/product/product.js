define(['text!pages/product/product.html','uui'], function(template) {
	var app = null,
	viewModel = {
			mainDataTable: new u.DataTable({
			    meta: {
			        'productid':{
			            type:'string'
			        },//productid类型为 string
			        'productname': {
			            type: 'string'
			        },
			        'productnum': {
			            type: 'integer'
			        },//productnum类型为整形
			        'price': {
			            type: 'float'
			        },//price类型为浮点型
			        'supplier': {
			            type: 'string'
			        }, //供应商
			        'prodate': {
			            type: 'date'
			        }, //prodate类型为时间
			        'orgin': {
			            type: 'string'
			        } //原产地
			    },
			    pageSize: 10
			})
	//删除某行
	delRow: function() {
	    //获取选中的行号
	    var selectArray = viewModel.mainDataTable.selectedIndices();
	    //用户未选择行时，return
	    if (selectArray.length < 1) {
	        u.messageDialog({
	            msg: "请选择要删除的行!",
	            title: "提示",
	            btnText: "OK"
	        });
	        return;
	    }
	    //选中的行数大于0时，弹出框用户点击确认进行删除
	    u.confirmDialog({
	        msg: "是否确认删除？",
	        title: "测试确认",
	        onOk: function () {
	        $.ajax({
	            url:ctrlBathPath+'/del',
	            type:'post',
	            data:{data:JSON.stringify(viewModel.mainDataTable.getSimpleData({type:'select',fields:['productid']}))},
	            success: function(){
	                u.showMessage('删除成功！');
	                viewModel.events.queryMain();
	            }
	        });
	        },
	        onCancel: function () {

	        }
	    });
	}

	};
	//初始化方法,页面加载后被 调用
	var init=function  (argument) {
		app = u.createApp({el:'product',model:viewModel});
	};
	

	return {
        'model': viewModel,
        'template': template,
        'init': init
    };
    
});