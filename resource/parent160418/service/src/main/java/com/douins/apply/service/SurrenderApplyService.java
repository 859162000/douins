package com.douins.apply.service;

import com.douins.apply.domain.model.SurrenderApply;
import com.douins.apply.domain.vo.SurrenderInfoVo;
import com.mango.orm.DbOperateService;

public interface SurrenderApplyService extends DbOperateService<SurrenderApply>{
	public SurrenderInfoVo getSurrenderInfo(SurrenderApply surrenderApply);
}