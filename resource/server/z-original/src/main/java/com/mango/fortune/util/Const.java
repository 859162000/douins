package com.mango.fortune.util;

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
}
