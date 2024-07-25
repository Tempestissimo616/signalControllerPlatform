package com.phoenix.signal.controller.platform.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.phoenix.signal.controller.platform.type.SysLogType;
import com.phoenix.signal.controller.platform.utils.baseModel.BasicStandardModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
public class BasicLog extends BasicStandardModel {
    private static final long serialVersionUID = -1277350298986674862L;


//    @Schema(description = "日志链路ID")
//    private String traceId;

//    @Schema(description = "资源编码")
//    private String resourceCode;

//    @Schema(description = "token")
//    private String token;

//    @Schema(description = "模块名称")
//    private String moduleName;

//    @Schema(description = "用户ID")
//    private Long userId;
//
//    @TableField(typeHandler = EncryptionHandler.class)
//    @Schema(description = "用户名")
//    private String username;


    @Schema(description = "日志名称")
    private String logName;

    @Schema(description = "请求地址")
    private String requestUrl;

    @Schema(description = "请求方法 GET/POST/UPDATE/DELETE")
    private String requestMethod;

    @Schema(description = "内容类型")
    private String contentType;

    @Schema(description = "请求参数")
    private Boolean jsonRequestBody;

    @Schema(description = "controller类名称")
    private String className;

    @Schema(description = "controller方法名称")
    private String methodName;

    @Schema(description = "请求参数")
    private String requestParam;

    @Schema(description = "请求ip")
    private String requestIp;

    @Schema(description = "0:其它,1:新增,2:修改,3:删除,4:详情查询,5:所有列表,6:分页列表,7:其它查询,8:上传文件")
    private SysLogType logType;

    @Schema(description = "响应时间")
    private LocalDateTime responseTime;

    @Schema(description = "0:失败,1:成功")
    private Boolean responseSuccess;

    @Schema(description = "响应结果状态码")
    private String responseCode;

    @Schema(description = "响应数据")
    private String responseData;

    @Schema(description = "异常名称")
    private String exceptionName;

    @Schema(description = "耗时时间：毫秒")
    private Long diffTime;

    @Schema(description = "耗时时间：描述")
    private String diffTimeDesc;

    @Schema(description = "请求来源")
    private String referer;

    @Schema(description = "请求来源服务器")
    private String requestService;

    @Schema(description = "请求类型")
    private String requestType;

    @Schema(description = "用户环境")
    private String userAgent;

    @Schema(description = "浏览器")
    private String browser;

    @Schema(description = "操作系统")
    private String os;

    public Boolean getResponseSuccess() {
        return Objects.isNull(responseSuccess) ? Boolean.FALSE : responseSuccess;
    }












}
