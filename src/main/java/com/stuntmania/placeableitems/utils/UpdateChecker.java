package com.stuntmania.placeableitems.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.Logger;

import com.stuntmania.placeableitems.PlaceableItems;

public class UpdateChecker implements Runnable {
	
	Logger logger = PlaceableItems.logger;
	
	private static boolean upToDate = false;
	private static String latest = "";

	public void run() {
		logger.info("Checking for mod updates...");
		InputStream in = null;
		try {
			in = new URL("https://raw.githubusercontent.com/Ferdzz/PlaceableItems/master/version.txt").openStream();
		} catch (MalformedURLException e) {
			logger.error("Unable to check for updates!");
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("Unable to check for updates!");
			e.printStackTrace();
		}
		
		try {
			latest = IOUtils.readLines(in).get(0);
		} catch (IOException e) {
			logger.error("Unable to determine update!");
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(in);
		}

        logger.info("Latest version is " + latest + ".");
        upToDate = PlaceableItems.VERSION.equals(latest);
        if (upToDate) {
            logger.info("Mod is the latest version!");
        } else {
        	logger.warn("Mod is out of date! Please update.");
        }
	}

    public boolean upToDate() {
     return upToDate;
    }
    
    public String getLatestVersion() {
     return latest;
    }
}
