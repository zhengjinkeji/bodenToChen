package com.kingoin.king.core.common.constant.state;

/**
 * 卡片的状态
 *
 * @author jack
 * @Date 2017年1月10日 下午9:54:13
 */
public enum CardStatus {

    OK(1, "启用"), FREEZED(0, "冻结"), DELETED(2, "被删除");

    int code;
    String message;

    CardStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String valueOf(Integer value) {
        if (value == null) {
            return "";
        } else {
            for (CardStatus ms : CardStatus.values()) {
                if (ms.getCode() == value) {
                    return ms.getMessage();
                }
            }
            return "";
        }
    }
}
