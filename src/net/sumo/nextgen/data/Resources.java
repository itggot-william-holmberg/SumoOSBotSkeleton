package net.sumo.nextgen.data;

import java.util.ArrayList;
import java.util.List;

import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.model.RS2Object;

import net.sumo.nextgen.enums.skills.Assignment;
import net.sumo.nextgen.state.State;
import net.sumo.nextgen.task.Task;

public class Resources {
	
	public static boolean useProfileSleeping = false;
	public static Entity lastClickedObject = null;
	public static Entity targetObject = null;
	public static int badWorld = 0;
	public static int playersAllowedInSamePosition = 0;
	public static int amountOfWorldHops = 0;
	public static String currentMission = "Unknown";
	public static State currentState = null;
	public static long timeWhenCurrentTaskStarted;
	public static long timeStarted;
	public static int currentXPGained;
	public static int xpWhenTaskStarted = 0;
	public static long lastTimeWeResetObjectTimer;
	public static int sleeps;
	public static int totalTimeAntiBanSleep;

	public static long timeWhenWeStartedTheSleep;
	public static Task currentTask = null;
	public static boolean shouldRun = false;
	
	public static long lastLogUpdate;
	
	
	public static int afkToiletTime;
	public static int toiletTimesPerHour;
	public static int afkSandwichTime;
	public static int sandwichTimesPerHour;
	public static int momYellingTimesPerHour;
	public static int afkMomYellingTime;
	public static int afkPhoneCallingTime;
	public static int phoneCallingTimesPerHour;
	public static int afkNeedSomeWaterTime;
	public static int needSomeWaterTimesPerHour;
	public static int reactionTime;
	public static Assignment currentWCAssignment;
	public static String wcAxe;
	

}
