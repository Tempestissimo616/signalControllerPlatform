package com.phoenix.signal.controller.platform.type;

/**
 * 系统日志类型
 * 0:访问日志,1:新增,2:修改,3:删除,4:详情,5:所有列表,6:分页列表,7:其它查询,8:上传文件,9:登录,10:退出
 **/
public enum SysLogType {

    OTHER(0, "访问日志"),
    ADD(1, "新增"),
    UPDATE(2, "修改"),
    DELETE(3, "删除"),
    INFO(4, "详情查询"),
    LIST(5, "所有列表"),
    PAGE(6, "分页列表"),
    OTHER_QUERY(7, "其它查询"),
    UPLOAD(8, "上传文件"),
    LOGIN(9, "登录"),
    LOGOUT(10, "退出"),
    IMPORT(11, "批量导入"),
    EXPORT(12, "批量导出"),
    DOWNLOAD(13, "下载"),;

    private Integer code;
    private String desc;

    SysLogType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static SysLogType getSysLogEnum(Integer code) {
        if (code == null) {
            return OTHER;
        }
        for (SysLogType sysLogType : values()) {
            if (sysLogType.getCode().equals(code)) {
                return sysLogType;
            }
        }
        return OTHER;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
