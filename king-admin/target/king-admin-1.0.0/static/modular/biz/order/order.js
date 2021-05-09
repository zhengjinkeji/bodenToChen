/**
 * 订单管理的单例
 */
var Order = {
    id: "OrderTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Order.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '订单编号', field: 'orderNum', align: 'center', valign: 'middle', sortable: true},
        {title: '用户名', field: 'userAccount', align: 'center', valign: 'middle', sortable: true},
        {title: '金额', field: 'price', align: 'center', valign: 'middle', sortable: true},
        // {title: '喜豆金额', field: 'xidou', align: 'center', valign: 'middle', sortable: true},
        // {title: '积分', field: 'scores', align: 'center', valign: 'middle', sortable: true},
        {title: '地址', field: 'addressStr', align: 'center', valign: 'middle', sortable: true},
        {title: '备注', field: 'remark', align: 'center', valign: 'middle', sortable: true},
        {
    		title : '支付凭证',
    		field : 'voucher',
    		align : 'center',
    		valign : 'middle',
    		sortable : true,
    		formatter: function typeformatter(cellvalue, options, rowObject) {
    			if(cellvalue!=null && cellvalue!=undefined){
    				//var selectHtml="<img alt src= \""+Feng.ctxPath+"/upload_images/"+cellvalue+"/>";
    				var selectHtml="<a target='_blank' href=\""+"https://www.caiufh.com/cai_images/order/"+cellvalue+"\"><img src= \""+"https://www.caiufh.com/cai_images/order/"+cellvalue+"\"  alt='image'></img></a>";
    				//var selectHtml="<a target='_blank' href=\""+"http://111.229.181.74:8081/image/order/"+cellvalue+"\"><img src= \""+"http://111.229.181.74:8081/image/order/"+cellvalue+"\" alt='image'></img></a>";
    				//var selectHtml="<img src= \""+"http://localhost:8080/image/banner/"+cellvalue+"\" alt='image'></img>";
    				//var selectHtml=<img alt="image"  src="http://localhost:8080/image/banner/"+cellvalue+"\" alt='image'></img>";
    			}else {
    				var selectHtml="无";
    			}
                return selectHtml;
                }
    	},
    	{title: '支付方式', field: 'payType', align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'dateAdd', align: 'center', valign: 'middle', sortable: true},
        {title: '完成时间', field: 'dateFinish', align: 'center', valign: 'middle', sortable: true}
        
        ]
    return columns;
};


/**
 * 检查是否选中
 */
Order.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
    	Order.seItem = selected[0];
        return true;
    }
};

/**
 * 点击修改按钮时
 */
Order.openChange = function () {
    if (this.check()) {
    	
        var index = layer.open({
            type: 2,
            title: '修改订单信息',
            area: ['1000px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/order/order_edit/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};
/**
 * 点击查看详情按钮时
 */
Order.selectOrderDetail = function () {
    if (this.check()) {
    	
        var index = layer.open({
            type: 2,
            title: '订单详情',
            area: ['1000px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/order/detail/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 点击添加物流信息
 */
Order.openAdd = function () {
    if (this.check()) {
    	if(this.seItem.status==1){
    		 var index = layer.open({
    	            type: 2,
    	            title: '添加物流信息',
    	            area: ['800px', '300px'], //宽高
    	            fix: false, //不固定
    	            maxmin: true,
    	            content: Feng.ctxPath + '/order/tracking_add/' + this.seItem.id
    	        });
    	        this.layerIndex = index;
    	}else{
    		Feng.info("请先选中表格中的待发货订单！");
    	}
       
    }
};
/**
 * 点击通过
 */
Order.pass = function () {
    if (this.check()) {
    	var index = layer.open({
            type: 2,
            title: '添加支付方式',
            area: ['400px', '300px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/order/pay_type/' + this.seItem.id
        });
        this.layerIndex = index;
        
    }
};
/**
 * 确认完成订单
 */
Order.affirmFinish = function () {
    if (this.check()) {
        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/order/affirm", function () {
                Feng.success("确认成功!");
                Order.table.refresh();
            }, function (data) {
                Feng.error("确认失败!" + data.responseJSON.message + "!");
            });
            ajax.set("orderId", Order.seItem.id);
            ajax.start();
        };
        Feng.confirm("是否确认完成订单：  " + Order.seItem.orderNum +"  ,获得积分："+Order.seItem.scores,operation);
    }
};
/**
 * 不通过
 */
Order.reject = function () {
    if (this.check()) {
        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/order/reject", function () {
                Feng.success("驳回成功!");
                Order.table.refresh();
            }, function (data) {
                Feng.error("驳回失败!" + data.responseJSON.message + "!");
            });
            ajax.set("orderId", Order.seItem.id);
            ajax.start();
        };
        Feng.confirm("是否驳回 " + Order.seItem.orderNum ,operation);
    }
};
/**
 * 删除信息
 */
Order.del = function () {
    if (this.check()) {
        var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/order/remove", function () {
                Feng.success("删除成功!");
                Order.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("orderId", Order.seItem.id);
            ajax.start();
        };
        Feng.confirm("是否删除 " + Order.seItem.orderNum ,operation);
    }
};
/**
 * 查询物流
 */
Order.searchTracking= function (){
	if (this.check()) {
		if(this.seItem.trackingNum==undefined |this.seItem.trackingNum==""){
			Feng.info("没有物流信息！");
			return
		}
        var index = layer.open({
            type: 2,
            title: '查询物流信息',
            area: ['800px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/order/logistics/' + this.seItem.id
        });
        this.layerIndex = index;
    }
}
/**
 * 搜索发布管理
 */
Order.search = function () {
	
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Order.table.refresh({query: queryData});
};

/**
 * 重置
 */
Order.resetSearch = function () {
    $("#condition").val("");
    Order.search();
};
/**
 * 切换tab
 */

Order.current = function (a) {
	
	if(a==0){//待付款
		$("#pass").hide();
	    $("#reject").hide();
	    $("#openAdd").hide();
	    $("#searchTracking").hide();
	    $("#openChange").show();
	    $("#affirmFinish").hide();
	}
	if(a==4){//待审核
		$("#pass").show();
	    $("#reject").show();
	    $("#openAdd").hide();
	    $("#searchTracking").hide();
	    $("#openChange").hide();
	    $("#affirmFinish").hide();
	}
	if(a==1){//待发货
		$("#pass").hide();
	    $("#reject").hide();
	    $("#openAdd").show();
	    $("#searchTracking").hide();
	    $("#openChange").show();
	    $("#affirmFinish").hide();
	}
	if(a==2){//待收货
		$("#pass").hide();
	    $("#reject").hide();
	    $("#openAdd").hide();
	    $("#searchTracking").show();
	    $("#openChange").hide();
	    $("#affirmFinish").show();
	}
	if(a==3){//已完成
		$("#pass").hide();
	    $("#reject").hide();
	    $("#openAdd").hide();
	    $("#searchTracking").show();
	    $("#openChange").hide();
	    $("#affirmFinish").hide();
	}
	
	
	
	var queryData = {};
    queryData['currentStatus'] = a;
    Order.table.refresh({query: queryData});
};
$(function () {
    var defaultColunms = Order.initColumn();
    var table = new BSTable(Order.id, "/order/list", defaultColunms);
    table.setQueryParams({"currentStatus":"0"});
    table.setPaginationType("client");
    table.init();
    Order.table = table;
    //按钮
    $("#openAdd").hide();
    $("#searchTracking").hide();
    
    $("#affirmFinish").hide();
    $("#pass").hide();
    $("#reject").hide();
});

