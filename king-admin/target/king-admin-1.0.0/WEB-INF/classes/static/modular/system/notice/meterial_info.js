/**
 * 初始化通知详情对话框
 */
var MeterialInfoDlg = {
    noticeInfoData: {},
    editor: null,
    validateFields: {
        meterialName: {
            validators: {
                notEmpty: {
                    message: '文件夹名不能为空'
                }
            }
        },
        type: {
            validators: {
                notEmpty: {
                    message: '类型不能为空'
                }
            }
        }
    }
};


/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MeterialInfoDlg.set = function (key, val) {
    this.noticeInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MeterialInfoDlg.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MeterialInfoDlg.close = function () {
    parent.layer.close(window.parent.Meterial.layerIndex);
}
/**
 * 清除数据
 */
MeterialInfoDlg.clearData = function () {
    this.noticeInfoData = {};
}

/**
 * 收集数据
 */
MeterialInfoDlg.collectData = function () {
    //this.noticeInfoData['content'] = MeterialInfoDlg.editor.txt.html();
    this.set('id').set('meterialName').set('type').set('url').set('orderNum').set('remark').set('fileId');
}

/**
 * 验证数据是否为空
 */
MeterialInfoDlg.validate = function () {
    $('#noticeInfoForm').data("bootstrapValidator").resetForm();
    $('#noticeInfoForm').bootstrapValidator('validate');
    return $("#noticeInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
MeterialInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/notice/add", function (data) {
        Feng.success("添加成功!");
        window.parent.Meterial.table.refresh();
        MeterialInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.noticeInfoData);
    ajax.start();

}

/**
 * 提交添加
 */
MeterialInfoDlg.addNetSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/notice/addNetLinkFile", function (data) {
        Feng.success("添加成功!");
        window.parent.Meterial.table.refresh();
        MeterialInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.noticeInfoData);
    ajax.start();

}
/**
 * 提交修改
 */
MeterialInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/notice/meterialUpdate", function (data) {
        Feng.success("修改成功!");
        window.parent.Meterial.table.refresh();
        MeterialInfoDlg.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.noticeInfoData);
    ajax.start();
}

$(function () {
    Feng.initValidator("noticeInfoForm", MeterialInfoDlg.validateFields);

//    //初始化编辑器
//    var E = window.wangEditor;
//    var editor = new E('#editor');
//    editor.create();
//    editor.txt.html($("#contentVal").val());
//    MeterialInfoDlg.editor = editor;
    $("#type").val($("#type1").val());
    $("#orderNum").val($("#orderNum1").val());
    $("#meterialName").val($("#meterialName1").val());
     $("#remark").val($("#remark1").val());
    $("#fileId").val($("#fileId1").val());
});
