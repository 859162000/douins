/**
 * 
 */
package com.douins.sms.dao;

import org.apache.ibatis.annotations.Param;

import com.douins.common.persistence.annotation.MyBatisMapper;
import com.douins.sms.domain.model.SmsSend;

/**
 * @ClassName: SmsSendDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author G. F.
 * @date 2015年10月21日 下午3:18:46
 * 
 */
@MyBatisMapper
public interface SmsSendDao {

	public SmsSend selectByPrimaryKey(@Param("smsId")String smsId);

	public void insert(SmsSend sms);

	public void updateForStatus(SmsSend sms);

	public void softDeleteByPrimaryKey(@Param("smsId")String smsId);

	public SmsSend findSmsLast(SmsSend sms);
}
