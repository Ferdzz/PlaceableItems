package com.stuntmania.placeableitems.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import com.stuntmania.placeableitems.PlaceableItems;

public class UpdateChecker implements Runnable {
	
	private static boolean upToDate = false;
	private static String latest = "";

	public void run() {
		InputStream in = null;
		try {
			in = new URL("https://raw.githubusercontent.com/Ferdzz/PlaceableItems/7c2462633060e3466beead861a79bc389915ec7c/version.txt").openStream();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			latest = IOUtils.readLines(in).get(0);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(in);
		}

        System.out.println("Latest version = " + latest);
        upToDate = PlaceableItems.VERSION.equals(latest);
        if (upToDate) {
            System.out.println("Mod is the latest version!");
        } else {
        	System.out.println("Mod is out of date! Please update.");
        }
	}

    public boolean upToDate() {
     return upToDate;
    }
    
    public String getLatestVersion() {
     return latest;
    }
}
