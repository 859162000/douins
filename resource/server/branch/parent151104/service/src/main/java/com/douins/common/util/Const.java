package com.douins.common.util;

public class Const {
	/**
	 * 身份验证状态
	 */
	public static final String VERIFY_RET_STATUS_NOT_VERIFIED = "0";// 未验证
	public static final String VERIFY_RET_STATUS_NOTEXIST = "1";// 库中无此号，不收费
	public static final String VERIFY_RET_STATUS_INCONSISTENT = "2";// 不一致，收费
	public static final String VERIFY_RET_STATUS_CONSISTENT = "3";// 一致,收费
	public static final String VERIFY_RET_STATUS_NOTRESULT = "4";// 由于网络等原因,造成的验证无返回结果，不收费
	public static final String VERIFY_RET_STATUS_ERR = "5";// 程序出现异常，不收费

	public static final String USER_VAILD = "1";// 用户身份已认证
	public static final String Email_VAILD = "1";// 邮箱已认证

	public static final String DEFAULT_DATE_FORMAT = "yyyy-mm-dd"; // 默认的日期格式

	public static final String DEFAULT_INVALID_DATE = "2099-01-01"; // 默认失效日期

	public static final String ENCODE_UTF8 = "UTF-8";

	public static final String DATEPATTERN_TIME = "yyyy-MM-dd HH:mm:ss";

	public static final String HTMLROOT = "html.root";

	public static final String CACHESPLIT = ":";
	
	// ############ Added by G.F. ############################
	public static final String VERIFY_TOKEN = "accessToken";         // 解析 token 使用的字段名
	public static final String REQUEST_TRANS = "requestTrans";     // 访问请求的字段名
	public static final String TRANS_ID = "transId";                              // 访问请求的 ID 字段名
	public static final String PUB_MESSAGE = "notice";                     //  公告类型的字段名
	public static final String PRIV_MESSAGE = "message";                // 私有消息类型的字段名
	
	public static final String XML_HEAD_FLAG = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	
	//############## 常量配置的名称 #########################
	public static final String ENVIROMENT = "env";         // 运行环境
	public static final String IP_DEV = "ipDev";                   // 开发服务器的 IP
	public static final String MAC_DEV = "macDev";         // 开发服务器的 mac
	public static final String IP_QA = "ipQa";                   // 测试服务器的 IP
    public static final String MAC_QA = "macQa";         // 测试服务器的 mac
    public static final String IP_ONLINE = "ipOline";                   // 线上服务器的 IP
    public static final String MAC_ONLINE = "macOnline";         // 线上服务器的 mac
	
}
