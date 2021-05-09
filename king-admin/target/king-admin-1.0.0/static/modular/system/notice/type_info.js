/**
 * 初始化通知详情对话框
 */
var TypeInfoDlg = {
    noticeInfoData: {},
//    editor: null,
        validateFields: {
               typeName: {
                   validators: {
                       notEmpty: {
                           message: '品类名不能为空'
                       }
                   }
               },
               remark: {
                           validators: {
                               notEmpty: {
                                   message: '备注不能为空'
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
TypeInfoDlg.set = function (key, val) {
    this.noticeInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TypeInfoDlg.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TypeInfoDlg.close = function () {
    parent.layer.close(window.parent.Type.layerIndex);
}
/**
 * 清除数据
 */
TypeInfoDlg.clearData = function () {
    this.noticeInfoData = {};
}

/**
 * 收集数据
 */
TypeInfoDlg.collectData = function () {
//    this.noticeInfoData['content'] = TypeInfoDlg.editor.txt.html();
    this.set('id').set('typeName').set('remark');
}

/**
 * 验证数据是否为空
 */
TypeInfoDlg.validate = function () {
       $('#noticeInfoForm').data("bootstrapValidator").resetForm();
       $('#noticeInfoForm').bootstrapValidator('validate');
    return $("#noticeInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
TypeInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();
    console.log("TypeInfoDlg:"+TypeInfoDlg)
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/notice/addType", function (data) {
        Feng.success("添加成功!");
        window.parent.Type.table.refresh();
        TypeInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.noticeInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TypeInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/notice/updateType", function (data) {
        Feng.success("修改成功!");
        window.parent.Type.table.refresh();
        TypeInfoDlg.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.noticeInfoData);
    ajax.start();
}

$(function () {
    Feng.initValidator("noticeInfoForm", TypeInfoDlg.validateFields);
    //初始化编辑器
//    var E = window.wangEditor;
//    var editor = new E('#editor');
//    editor.create();
//    editor.txt.html($("#contentVal").val());
//    TypeInfoDlg.editor = editor;
//    $("#typeName").val($("typeName1").val());
//    $("#remark").val($("#remark1").val());
});
