package jmri.jmrix.roco.z21;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Tests for the jmri.jmrix.roco.z21.Z21Reply class
 *
 * @author	Bob Jacobsen
 */
public class Z21ReplyTest {

    @Test
    public void testCtor() {
        Z21Reply m = new Z21Reply();
        Assert.assertNotNull(m);
    }

    // test the byte array  constructor.
    @Test
    public void testStringCtor() {
        byte msg[]={(byte)0x0D,(byte)0x00,(byte)0x04,(byte)0x00,(byte)0x12,(byte)0x34,(byte)0xAB,(byte)0x03,(byte)0x19,(byte)0x06,(byte)0x0B,(byte)0xB1};
        Z21Reply m = new Z21Reply(msg,12);
        Assert.assertEquals("length", 12, m.getNumDataElements());
        Assert.assertEquals("OpCode", 0x0004, m.getOpCode());
        Assert.assertEquals("0th byte", 0x0D, m.getElement(0) & 0xFF);
        Assert.assertEquals("1st byte", 0x00, m.getElement(1) & 0xFF);
        Assert.assertEquals("2nd byte", 0x04, m.getElement(2) & 0xFF);
        Assert.assertEquals("3rd byte", 0x00, m.getElement(3) & 0xFF);
        Assert.assertEquals("4th byte", 0x12, m.getElement(4) & 0xFF);
        Assert.assertEquals("5th byte", 0x34, m.getElement(5) & 0xFF);
        Assert.assertEquals("6th byte", 0xAB, m.getElement(6) & 0xFF);
        Assert.assertEquals("7th byte", 0x03, m.getElement(7) & 0xFF);
        Assert.assertEquals("8th byte", 0x19, m.getElement(8) & 0xFF);
        Assert.assertEquals("9th byte", 0x06, m.getElement(9) & 0xFF);
        Assert.assertEquals("10th byte", 0x0B, m.getElement(10) & 0xFF);
        Assert.assertEquals("11th byte", 0xB1, m.getElement(11) & 0xFF);
    }

    // Test XPressNet Tunnel related methods.
    @Test
    public void tunnelXPressNet(){
        byte msg[]={(byte)0x07,(byte)0x00,(byte)0x40,(byte)0x00,(byte)0x61,(byte)0x82,(byte)0xE3};
        Z21Reply m = new Z21Reply(msg,7);
        Assert.assertTrue("XPressNet Tunnel Message",m.isXPressNetTunnelMessage());
        byte msg1[]={(byte)0x11,(byte)0x00,(byte)0x88,(byte)0x00,(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};
        m = new Z21Reply(msg1,17);
        Assert.assertFalse("Not XPressNet Tunnel Message",m.isXPressNetTunnelMessage());
    }

    @Test
    public void getXPressNetReply(){
        byte msg[]={(byte)0x07,(byte)0x00,(byte)0x40,(byte)0x00,(byte)0x61,(byte)0x82,(byte)0xE3};
        Z21Reply m = new Z21Reply(msg,7);
        jmri.jmrix.lenz.XNetReply x = m.getXNetReply();
        Assert.assertEquals("0th byte", 0x61, x.getElement(0) & 0xFF);
        Assert.assertEquals("1st byte", 0x82, x.getElement(1) & 0xFF);
        Assert.assertEquals("2nd byte", 0xE3, x.getElement(2) & 0xFF);
    }

    //Test RailCom related methods.
    @Test
    public void railComReply(){
        byte msg[]={(byte)0x11,(byte)0x00,(byte)0x88,(byte)0x00,(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};
        Z21Reply m = new Z21Reply(msg,17);
        Assert.assertTrue("RailCom Reply",m.isRailComDataChangedMessage());
        byte msg1[]={(byte)0x07,(byte)0x00,(byte)0x40,(byte)0x00,(byte)0x61,(byte)0x82,(byte)0xE3};
        m = new Z21Reply(msg1,7);
        Assert.assertFalse("Not RailCom Reply",m.isRailComDataChangedMessage());
    }

    @Test
    public void railComEntries(){
        byte msg[]={(byte)0x11,(byte)0x00,(byte)0x88,(byte)0x00,(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};
        Z21Reply m = new Z21Reply(msg,17);
        Assert.assertEquals("RailCom Entries",1,m.getNumRailComDataEntries());
        byte msg1[]={(byte)0x07,(byte)0x00,(byte)0x40,(byte)0x00,(byte)0x61,(byte)0x82,(byte)0xE3};
        m = new Z21Reply(msg1,7);
        Assert.assertEquals("RailCom Entries",0,m.getNumRailComDataEntries());
    } 

    @Test
    public void railComAddress(){
        byte msg[]={(byte)0x11,(byte)0x00,(byte)0x88,(byte)0x00,(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};
        Z21Reply m = new Z21Reply(msg,17);
        Assert.assertTrue("RailCom Address",(new jmri.DccLocoAddress(1,false)).equals(m.getRailComLocoAddress(1)));
    }

    @Test
    public void railComRcvCount(){
        byte msg[]={(byte)0x11,(byte)0x00,(byte)0x88,(byte)0x00,(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};
        Z21Reply m = new Z21Reply(msg,17);
        Assert.assertEquals("RailCom Rcv Count",1,m.getRailComRcvCount(1));
    }

    @Test
    public void railComErrCount(){
        byte msg[]={(byte)0x11,(byte)0x00,(byte)0x88,(byte)0x00,(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};
        Z21Reply m = new Z21Reply(msg,17);
        Assert.assertEquals("RailCom Err Count",0,m.getRailComErrCount(1));
    }

    @Test
    public void railComSpeed(){
        byte msg[]={(byte)0x11,(byte)0x00,(byte)0x88,(byte)0x00,(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};
        Z21Reply m = new Z21Reply(msg,17);
        Assert.assertEquals("RailCom Speed",0,m.getRailComSpeed(1));
    }

    @Test
    public void railComOptions(){
        byte msg[]={(byte)0x11,(byte)0x00,(byte)0x88,(byte)0x00,(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};
        Z21Reply m = new Z21Reply(msg,17);
        Assert.assertEquals("RailCom Options",0,m.getRailComOptions(1));
    }

    @Test
    public void railComTemp(){
        byte msg[]={(byte)0x11,(byte)0x00,(byte)0x88,(byte)0x00,(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};
        Z21Reply m = new Z21Reply(msg,17);
        Assert.assertEquals("RailCom Temp",0,m.getRailComTemp(1));
    }

    // The minimal setup for log4J
    @Before
    public void setUp() {
        apps.tests.Log4JFixture.setUp();
    }

    @After
    public void tearDown() {
        apps.tests.Log4JFixture.tearDown();
    }

}
