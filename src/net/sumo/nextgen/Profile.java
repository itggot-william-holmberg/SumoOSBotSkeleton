package net.sumo.nextgen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import net.sumo.nextgen.data.Resources;

public class Profile {

	private File PATH_FILE;
	private String FILE_NAME = "PROFILE.ini";
	private final Properties prop = new Properties();
	public static String MINUTES_PLAYED = "MINUTES_PLAYED";

	public Profile(File f, String accountName) {
		if (!f.exists()) {
			f.mkdirs();
		}
		
		FILE_NAME = accountName.toUpperCase() + "_" + FILE_NAME;
		PATH_FILE = new File(f, FILE_NAME);
		load();
		update("AFK_TOILET_TIME", Integer.toString(getInt("AFK_TOILET_TIME") + 1337));
	}

	public synchronized void load() {
		try {
			if (PATH_FILE.exists()) {
				prop.load(new FileInputStream(PATH_FILE));
				
				Resources.afkToiletTime = Integer.parseInt(prop.getProperty("AFK_TOILET_TIME"));
				Resources.toiletTimesPerHour = Integer.parseInt(prop.getProperty("TOILET_TIMES_PER_HOUR"));
				Resources.afkSandwichTime = Integer.parseInt(prop.getProperty("AFK_SANDWICH_TIME"));
				Resources.sandwichTimesPerHour = Integer.parseInt(prop.getProperty("SANDWICH_TIMES_PER_HOUR"));
				Resources.afkMomYellingTime = Integer.parseInt(prop.getProperty("AFK_MOM_YELLING_TIME"));
				Resources.momYellingTimesPerHour = Integer.parseInt(prop.getProperty("MOM_YELLING_TIMES_PER_HOUR"));
				Resources.afkPhoneCallingTime = Integer.parseInt(prop.getProperty("AFK_PHONE_CALLING_TIME"));
				Resources.phoneCallingTimesPerHour = Integer.parseInt(prop.getProperty("PHONE_CALLING_TIMES_PER_HOUR"));
				Resources.afkNeedSomeWaterTime = Integer.parseInt(prop.getProperty("AFK_NEED_SOME_WATER_TIME"));
				Resources.needSomeWaterTimesPerHour = Integer.parseInt(prop.getProperty("NEED_SOME_WATER_TIMES_PER_HOUR"));
				Resources.reactionTime = Integer.parseInt(prop.getProperty("REACTION_TIME"));
			} else {
				newProfile();
			}
		} catch (Throwable e) {
		}
	}
	
	public synchronized int getInt(String key){
		return Integer.parseInt(prop.getProperty(key));
	}
	
	public synchronized String getString(String key){
		return prop.getProperty(key);
	}
	
	public synchronized void update(String key, int value){
		if (!PATH_FILE.canWrite()) {
			PATH_FILE.setWritable(true);
		}
		
		
		try {	
			prop.setProperty(key, Integer.toString(value));
			prop.store(new FileOutputStream(PATH_FILE), "Profile Settings");
			PATH_FILE.setReadOnly();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized void update(String key, String value){
		if (!PATH_FILE.canWrite()) {
			PATH_FILE.setWritable(true);
		}
		
		
		try {	
			prop.setProperty(key, value);
			prop.store(new FileOutputStream(PATH_FILE), "Profile Settings");
			PATH_FILE.setReadOnly();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	private void newProfile() throws IOException {
		Random r = new Random();
		
		int afkToiletTime = 50000 + r.nextInt(40000);
		int toiletTimesPerHour = 1 + r.nextInt(3);
		
		int afkSandwichTime = 80000 + r.nextInt(60000);
		int sandwichTimesPerHour = 1 + r.nextInt(2);
		
		int afkMomYellingTime = 30000 + r.nextInt(20000);
		int momYellingTimesPerHour = 1 + r.nextInt(1);
		
		int afkPhoneCallingTime = 15000 + r.nextInt(40000);
		int phoneCallingTimesPerHour = 2 + r.nextInt(2);
		
		int afkNeedSomeWaterTime = 35000 + r.nextInt(15000);
		int needSomeWaterTimesPerHour = 1 + r.nextInt(3);
		
		PATH_FILE.createNewFile();
		if (!PATH_FILE.canWrite()) {
			PATH_FILE.setWritable(true);
		}
		prop.clear();

		prop.put("AFK_TOILET_TIME", Integer.toString(afkToiletTime));
		prop.put("TOILET_TIMES_PER_HOUR", Integer.toString(toiletTimesPerHour));
		prop.put("AFK_SANDWICH_TIME", Integer.toString(afkSandwichTime));
		prop.put("SANDWICH_TIMES_PER_HOUR", Integer.toString(sandwichTimesPerHour));
		prop.put("AFK_MOM_YELLING_TIME", Integer.toString(afkMomYellingTime));
		prop.put("MOM_YELLING_TIMES_PER_HOUR", Integer.toString(momYellingTimesPerHour));
		prop.put("AFK_PHONE_CALLING_TIME", Integer.toString(afkPhoneCallingTime));
		prop.put("PHONE_CALLING_TIMES_PER_HOUR", Integer.toString(phoneCallingTimesPerHour));
		prop.put("AFK_NEED_SOME_WATER_TIME", Integer.toString(afkNeedSomeWaterTime));
		prop.put("NEED_SOME_WATER_TIMES_PER_HOUR", Integer.toString(needSomeWaterTimesPerHour));
		
		prop.put("MINUTES_PLAYED", "0");
		
		prop.put("REACTION_TIME", Integer.toString(276 + r.nextInt(100)));
		
		prop.store(new FileOutputStream(PATH_FILE), "Profile Settings");
		PATH_FILE.setReadOnly();
	}
}