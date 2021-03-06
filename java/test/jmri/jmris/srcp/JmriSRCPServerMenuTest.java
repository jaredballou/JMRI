package jmri.jmris.srcp;

import org.junit.After;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import java.awt.GraphicsEnvironment;


/**
 * Tests for the jmri.jmris.srcp.JmriSRCPServerMenu class
 *
 * @author Paul Bender Copyright (C) 2016
 */
public class JmriSRCPServerMenuTest {

    @Test public void testCtor() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        JmriSRCPServerMenu a = new JmriSRCPServerMenu();
        Assert.assertNotNull(a);
    }

    @Test public void testStringCtor() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        JmriSRCPServerMenu a = new JmriSRCPServerMenu("Hello World");
        Assert.assertNotNull(a);
    }

    @Before public void setUp() {
        apps.tests.Log4JFixture.setUp();
        jmri.util.JUnitUtil.resetInstanceManager();
    }

    @After public void tearDown() throws Exception {
        jmri.util.JUnitUtil.resetInstanceManager();
        apps.tests.Log4JFixture.tearDown();
    }

}
