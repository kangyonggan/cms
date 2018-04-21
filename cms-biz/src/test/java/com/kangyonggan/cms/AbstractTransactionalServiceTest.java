package com.kangyonggan.cms;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 有事物，会回滚
 *
 * @author kangyonggan
 * @date 2016/11/30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test-core.xml"})
public abstract class AbstractTransactionalServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

}
