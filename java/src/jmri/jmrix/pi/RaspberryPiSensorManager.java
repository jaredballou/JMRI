
package jmri.jmrix.pi;

import jmri.Sensor;

/**
 * Manage the RaspberryPi specific Sensor implementation.
 *
 * System names are "PSnnn", where nnn is the sensor number without padding.
 *
 * @author			Paul Bender Copyright (C) 2015
 * 
 */
public class RaspberryPiSensorManager extends jmri.managers.AbstractSensorManager {

    // ctor has to register for RaspberryPi events
    public RaspberryPiSensorManager(String prefix) {
        super();
        this.prefix=prefix.toUpperCase();
    }

    /**
     * Provides access to the system prefix string.
     * This was previously called the "System letter"
     */
    @Override
    public String getSystemPrefix(){ return prefix; }

    private String prefix = null;

    // to free resources when no longer used
    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public Sensor createNewSensor(String systemName, String userName) {
        return new RaspberryPiSensor(systemName, userName);
    }

}


