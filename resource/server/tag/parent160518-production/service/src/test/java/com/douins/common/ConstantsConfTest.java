/**
 * 
 */
package com.douins.common;

import org.junit.Test;

import com.douins.AbstractTest;
import com.douins.common.util.KeyFileUtils;
import com.douins.common.util.ReadConfig;

/** 
* @ClassName: ConstantsConfTest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年12月3日 上午11:05:06 
*  
*/
public class ConstantsConfTest extends AbstractTest {

    @Test
    public void test() {
      String key = KeyFileUtils.loadKey(ReadConfig.get("key_priv_path_gfbc"));
      System.out.println(key);
    }

}
