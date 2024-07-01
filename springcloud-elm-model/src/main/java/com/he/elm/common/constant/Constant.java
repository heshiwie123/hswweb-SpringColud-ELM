package com.he.elm.common.constant;
//系统常量相关
public interface Constant {

    //                                  rabbitMQ 队列和交换机相关                              //
    public final static String ORDERS = "topic.orders";
    public final static String EMAIL = "topic.email";
    public final static String TOPIC_EXCHANGE = "topicExchange";
    //                                  常用状态码code相关                              //
    //成功码
    public static final int SUCCESS_CODE = 200;
    //失败码
    public static final int FAIL_CODE = 0;
    //操作成功
    public static final boolean OPERATE_SUCCESS = true;
    //操作失败
    public static final boolean OPERATE_ERROR = false;
    //                                  用户登录注册相关                              //
    public final static String initPassword = "handsome@123";
    public final static String SUCCESS = "操作成功！";
    public final static String FAILED = "操作失败！";

    public final static String initIdentity = "普通用户";
    //                                  系统返回信息相关                              //
    public static final String USER_NOT_LOGIN = "用户未登录，请先登录";
    public static final String USER_NOT_EXIT = "用户不存在";
    public static final String USER_PASSWORD_ERROR = "密码输入出错";
    public static final String COMMON_ERROR = "系统出错";
    public static final String ACCOUNT_PASS_TIME = "账号过期";
    public static final String ACCOUNT_LOCK = "账号锁定";
    public static final String ACCOUNT_NOT_USE = "账号不可用";
    public static final String USER_PASSWORD_PASS_TIME = "密码过期";
    public static final String CLOUD_FLARE_MINIO_SHOW_URL = "https://minio-show.hswweb.cn";
}
