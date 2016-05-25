package com.mango.fortune.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;

import com.mango.core.abstractclass.AbstractModel;
import com.mango.core.common.util.DateUtils;
import com.mango.core.logger.SimpleLogger;
import com.mango.exception.NestedException;

/**
 * 
 * 
 * 类的描述 Controller中新增或更新时始初化实体类所用的工具
 * 
 * @author raowei
 * @version 1.0 2014年5月4日
 * 
 */
public class SaveEntityUtils {
	private static SimpleLogger logger = SimpleLogger.getLogger(SaveEntityUtils.class);

	/**
	 * 在新增之前初始化实体类
	 * 
	 * @param t
	 *            实体类
	 * @param userName
	 *            保存的操作者
	 * @exception NestedException
	 */
	public static <T> void initEntityBeforeInsert(T t, String userName)throws NestedException {
		// 判断该对象是否为空，为空则抛出空指针异常
		if (t == null) {
			throw new NullPointerException("对象为空");
		}
		Class<?> clzz = t.getClass();
		// 判断此类是否是继承自BaseModel，如果不是则抛出异常
		if (!isExtendSpecificClass(clzz)) {
			throw new IllegalArgumentException(t.getClass().getName() + "is not a standard Entity for this framwork");
		}

		try {
			Method initprimary = clzz.getMethod("initPrimaryKey");
			Method setCreateDate = clzz.getMethod("setCreateDate", java.util.Date.class);
			Method setUpdateDate = clzz.getMethod("setUpdateDate", java.util.Date.class);
			initprimary.invoke(t);
			setCreateDate.invoke(t, DateUtils.today());
			setUpdateDate.invoke(t, DateUtils.today());
			if (StringUtils.isNotBlank(userName)) {
				Method setOpUser = clzz.getMethod("setOpUser", String.class);
				setOpUser.invoke(t, userName);
			}
		} catch (SecurityException e) {
			logger.error("该应用程序试图执行安全策略不允许的操作 : " + SaveEntityUtils.class.getClass().getName()
					+ ".initEntityBeforeInsert", e);
			throw new NestedException(SaveEntityUtils.class.getClass().getName()
					+ ".initEntityBeforeInsert该应用程序试图执行安全策略不允许的操作 : ",e);
		} catch (NoSuchMethodException e) {
			logger.error("没有这个方法签名，请确认是否存在该方法或者拼写错误" + SaveEntityUtils.class.getClass().getName()
					+ ".initEntityBeforeInsert", e);
			throw new NestedException("SaveEntityUtils.class.getClass().getName()反射调用方法出错，没有这个方法",e);
		} catch (IllegalArgumentException e) {
			logger.error("该方法传入参数有误，参数类型不匹配" + SaveEntityUtils.class.getClass().getName() + ".initEntityBeforeInsert",
					e);
			throw new NestedException("传入的参数有误", e);
		} catch (IllegalAccessException e) {
			logger.error("违法的访问异常，该方法为非public访问权限" + SaveEntityUtils.class.getClass().getName()
					+ ".initEntityBeforeInsert", e);
			throw new NestedException("违法的访问异常，该方法为非public访问权限",e);
		} catch (InvocationTargetException e) {
			logger.error("反射异常：对象调用方法方法抛出异常" + SaveEntityUtils.class.getClass().getName()
					+ ".initEntityBeforeInsert", e);
			throw new NestedException("反射异常：对象调用方法方法抛出异常",e);
		}

	}

	/**
	 * 更新之前修改更新时间
	 * 
	 * @param t
	 *            要更新的实体类
	 * @param userName
	 *            操作者姓名
	 * @exception NestedException  
	 */

	public static <T> void setUpdateForEntity(T t, String userName)  {
		// 判断该对象是否为空，为空则抛出空指针异常
		if (t == null) {
			throw new NullPointerException("对象为空");
		}
		Class<?> tClass = t.getClass();
		// 判断此类是否是继承自BaseModel，如果不是则抛出异常
		if (!isExtendSpecificClass(tClass)) {
			throw new IllegalArgumentException(t.getClass().getName() + "is not a standard Entity for this framwork");
		}

		try {
			Method setUpdateDate = tClass.getMethod("setUpdateDate", java.util.Date.class);
			setUpdateDate.invoke(t, DateUtils.today());
			if (StringUtils.isNotBlank(userName)) {
				Method setOpUser = tClass.getMethod("setOpUser", String.class);
				setOpUser.invoke(t, userName);
			}
		} catch (SecurityException e) {
			logger.error("该应用程序试图执行安全策略不允许的操作 : " + SaveEntityUtils.class.getClass().getName()
					+ ".initEntityBeforeInsert", e);
			throw new NestedException(SaveEntityUtils.class.getClass().getName()
					+ ".initEntityBeforeInsert该应用程序试图执行安全策略不允许的操作 : ",e);
		} catch (NoSuchMethodException e) {
			logger.error("没有这个方法签名，请确认是否存在该方法或者拼写错误" + SaveEntityUtils.class.getClass().getName()
					+ ".initEntityBeforeInsert", e);
			throw new NestedException("SaveEntityUtils.class.getClass().getName()反射调用方法出错，没有这个方法",e);
		} catch (IllegalArgumentException e) {
			logger.error("该方法传入参数有误，参数类型不匹配" + SaveEntityUtils.class.getClass().getName() + ".initEntityBeforeInsert",
					e);
			throw new NestedException("传入的参数有误", e);
		} catch (IllegalAccessException e) {
			logger.error("违法的访问异常，该方法为非public访问权限" + SaveEntityUtils.class.getClass().getName()
					+ ".initEntityBeforeInsert", e);
			throw new NestedException("违法的访问异常，该方法为非public访问权限",e);
		} catch (InvocationTargetException e) {
			logger.error("反射异常：对象调用方法方法抛出异常" + SaveEntityUtils.class.getClass().getName()
					+ ".initEntityBeforeInsert", e);
			throw new NestedException("反射异常：对象调用方法方法抛出异常",e);
		}


	}

	private static boolean isExtendSpecificClass(Class<?> enity) {
		return isExtendSpecificClass(enity, null);
	}

	private static boolean isExtendSpecificClass(Class<?> enity, Class<?> destCls) {
		boolean result = Boolean.FALSE;
		if (destCls == null) {
			destCls = AbstractModel.class;
		}
		String supClsName = enity.getClass().getSuperclass().getClass().getName();
		String destClsName = destCls.getClass().getName();
		if (supClsName.equals(destClsName)) {
			result = true;
		}

		return result;
	}

	private SaveEntityUtils() {
	}
	
	

}
