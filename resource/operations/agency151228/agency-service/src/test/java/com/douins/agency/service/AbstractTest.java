/**
 * 
 */
package com.douins.agency.service;

/** 
* @ClassName: AbstractTest 
* @Description: 测试类的抽象类
* @author G. F. 
* @date 2015年10月19日 下午4:18:10 
*  
*/
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context.xml" })
public abstract class AbstractTest {
 
}
