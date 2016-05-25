/**
 * 
 */
package com.douins.agency.service.common.dao;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;


/** 
* @ClassName: AbstractDao 
* @Description: 抽象的 DAO
* @author G. F. 
* @date 2015年10月19日 下午3:56:49 
*  
*/

public class AbstractDao {

    @Resource
    protected SqlSessionTemplate writeSqlSession;

    protected String sql() {
        String sqlId = new RuntimeException().getStackTrace()[1]
                .getMethodName();
        return getClass().getCanonicalName() + '.' + sqlId;
    }

    protected <T> List<T> list(String sqlId, Object params) {
        return list(writeSqlSession, sqlId, params);
    }

    protected <T> List<T> list(SqlSessionTemplate writeSqlSession,
            String sqlId, Object params) {
        List<T> result = null;
        if (params == null)
            result = writeSqlSession.selectList(sqlId);
        else
            result = writeSqlSession.selectList(sqlId, params);

        if (result == null) {
            result = Collections.emptyList();
        }
        return result;
    }

    protected int size(String sqlId, Object params) {
        return size(writeSqlSession, sqlId, params);
    }

    protected int size(SqlSessionTemplate writeSqlSession, String sqlId,
            Object params) {
        Integer result = null;
        if (params == null) {
            result = writeSqlSession.selectOne(sqlId);
        } else {
            result = writeSqlSession.selectOne(sqlId, params);
        }
        return result == null ? 0 : result;
    }
}

