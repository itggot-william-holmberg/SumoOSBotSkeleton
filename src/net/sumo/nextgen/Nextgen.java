package net.sumo.nextgen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.osbot.rs07.api.Players;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.api.util.GraphicUtilities;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

import net.sumo.nextgen.api.GrandExchangeApiPrice;
import net.sumo.nextgen.data.Resources;

import net.sumo.nextgen.gui.NextgenGUI;
import net.sumo.nextgen.state.State;
import net.sumo.nextgen.task.Task;

@ScriptManifest(author = "Sumo", info = "SumoSkeleton", logo = "", name = "SumoSekelton", version = 0.1)
public class Nextgen extends Script {

	public static List<Task> taskHandler = new ArrayList<Task>();
	public static Task currentTask = null;

	public static List<State> stateHandler = new ArrayList<State>();

	private long timeSpentOnCurrentTask;
	private long totalTimeRan;
	private int currentXP;
	private int xpGained;
	private int xpPerHour;
	private int currentMoneyMade;
	private int currentMoneyPerHour;
	private int totalMoneyMade;
	private int totalMoneyPerHour;
	
	Profile profile;
	Color myColour = new Color(255, 255, 255, 99);
	private NextgenGUI gui;
	private GrandExchangeApiPrice geApi;

	public void onStart() {

		gui = new NextgenGUI();
		geApi = new GrandExchangeApiPrice();
		Resources.timeStarted = System.currentTimeMillis();

		profile = new Profile(new File(
				System.getProperty("user.home") + File.separator + "OSBot" + File.separator + "data" + File.separator),
				this.myPlayer().getName());

	}

	@Override
	public int onLoop() throws InterruptedException {

		if (Resources.shouldRun) {

			checkContinue();
			updateLog();

			if (currentTask != null && !taskIsCompleted(currentTask)) {

				if (!shouldHop()) {

					boolean foundState = false;

					for (State state : stateHandler) {

						if (state.active()) {
							checkContinue();
							Resources.currentState = state;
							state.execute();
							foundState = true;
						}
					}
					if (!foundState) {
						log("We couldnt find a state.. what should we do?");
						log("if this occurs more than 10 times we should do a 'home tele' or something");
						return 5000;
					}
				} else {
					log("We should hop world. This world is either crowded or a 'bad world'");
					hopWorld();
				}

			} else {
				currentTask = newTask();
				return 1000;
			}
		} else {
			log("Waiting for user input");
			return 2500;
		}

		return 250;
	}

	private void updateLog() {
		if (System.currentTimeMillis() - Resources.lastLogUpdate > 60000) {
			log("Updating the log!");
			profile.update(profile.MINUTES_PLAYED, (profile.getInt(profile.MINUTES_PLAYED) + 1));
			Resources.lastLogUpdate = System.currentTimeMillis();
		}

	}

	private void hopWorld() {

		if (worlds.isMembersWorld()) {
			worlds.hopToP2PWorld();
		} else {
			worlds.hopToF2PWorld();
		}

		sleep(3000, 6000);

		if (!shouldHop()) {
			Resources.amountOfWorldHops += 1;
		}
	}

	public boolean shouldHop() {
		if (worlds.getCurrentWorld() == Resources.badWorld) {
			return true;
		}
		return false;
	}

	public void onPaint(Graphics2D g) {

		timeSpentOnCurrentTask = System.currentTimeMillis() - Resources.timeWhenCurrentTaskStarted;
		totalTimeRan = System.currentTimeMillis() - Resources.timeStarted;

		currentXP = skills.getExperience(Skill.WOODCUTTING);
		xpGained = currentXP - Resources.xpWhenTaskStarted;
		xpPerHour = (int) (xpGained / ((timeSpentOnCurrentTask) / 3600000.0D));

		drawBackground(g);
		g.setColor(Color.red);
		g.drawString("Current task: " + currentTask + " ........ Time spent: " + ft(timeSpentOnCurrentTask), 10, 360);
		g.drawString("Current state: " + Resources.currentState, 10, 385);
		g.drawString("Current 'mission': " + currentMission(), 10, 410);

		g.drawString("Nearby players: " + playerCounter(), 355, 380);
		g.drawString("Hopped world: " + Resources.amountOfWorldHops + " times", 355, 405);
		g.drawString("Total time slept: " + Resources.totalTimeAntiBanSleep, 355, 430);
		g.drawString("H sleep: " + Resources.sleeps, 355, 455);


		/*
		 * g.drawString("Current tasks: ", 10, 225); for (int i = 0; i <
		 * taskHandler.size(); i++) {
		 * g.drawString(taskHandler.get(i).toString(), 10, (i * 12) + 237); }
		 * 
		 * g.drawString("Current states: ", 10, 300-12); for (int i = 0; i <
		 * stateHandler.size(); i++) {
		 * g.drawString(stateHandler.get(i).toString(), 10, (i * 12) + 300); }
		 */

		drawSkillingPaint(g);
		drawObjects(g);

	}

	private void drawSkillingPaint(Graphics2D g) {

	}

	private int perHour(int i) {
		return (int) (i / ((timeSpentOnCurrentTask) / 3600000.0D));
	}

	private void drawBackground(Graphics g) {
		g.setColor(myColour);
		g.fillRect(9, 345, 500, 200);
	}

	private String ft(long duration) {

		String res = "";
		long days = TimeUnit.MILLISECONDS.toDays(duration);
		long hours = TimeUnit.MILLISECONDS.toHours(duration)
				- TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(duration));
		long minutes = TimeUnit.MILLISECONDS.toMinutes(duration)
				- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration));
		long seconds = TimeUnit.MILLISECONDS.toSeconds(duration)
				- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration));
		if (days == 0) {
			res = (hours + ":" + minutes + ":" + seconds);
		} else {
			res = (days + ":" + hours + ":" + minutes + ":" + seconds);
		}
		return res;
	}

	private String currentMission() {
		if (shouldHop()) {
			return "Hopping worlds";
		}
		return Resources.currentMission;
	}

	private void drawObjects(Graphics2D g) {
		Entity targetObject = Resources.targetObject;
		if (targetObject != null && GraphicUtilities.getScreenCoordinates(bot, targetObject.getGridX(),
				targetObject.getGridY(), targetObject.getZ(), targetObject.getHeight())[0] != -1) {
			GraphicUtilities.drawModel(bot, g, targetObject.getGridX(), targetObject.getGridY(), targetObject.getZ(),
					targetObject.getModel());
		}

	}

	private boolean taskIsCompleted(Task task) {
		if (task == null) {
			return true;
		}
		if (getSkills().getDynamic(Skill.WOODCUTTING) >= task.getLevelGoal()) {
			return true;
		}
		return false;
	}

	private Task newTask() {
		if (taskIsCompleted(currentTask)) {
			log("Removing current task");
			taskHandler.remove(currentTask);
		}
		if (!taskHandler.isEmpty()) {
			for (Task task : taskHandler) {
				if (!taskIsCompleted(task)) {
					log("We found a suitable task");
					addStates(task);
					totalMoneyMade += currentMoneyMade;
					currentMoneyMade = 0;
					Resources.timeWhenCurrentTaskStarted = System.currentTimeMillis();
					Resources.xpWhenTaskStarted = skills.getExperience(Skill.WOODCUTTING);
					Resources.currentTask = task;
					return task;
				} else {
					log("We already completed that task, lets remove it.");
					taskHandler.remove(task);
				}
			}
		} else {
			log("Stopping script because no task available");
			stop();
			stop();
		}
		return null;
	}

	private void addStates(Task task) {

		stateHandler.clear();
		//stateHandler.add(new Cut().init(this));

		

	}

	public int playerCounter() {
		int players = 0;

		for (Player p : getPlayers().getAll()) {
			if (p != null && p != myPlayer()) {
				players++;
			}
		}
		return players;
	}

	private void sleep(int milli) {
		try {
			Script.sleep(milli);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void sleep(int milli, int millii) {
		try {
			Script.sleep(Script.random(milli, millii));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean continueMessageIsVisible() {
		if (widgets.getWidgetContainingText("Click here to continue") != null) {
			return true;
		}
		return false;
	}

	public void clickContinue() throws InterruptedException {
		getDialogues().clickContinue();
		sleep(1000);
	}

	public void checkContinue() {
		if (continueMessageIsVisible()) {
			try {
				clickContinue();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return;
	}
}
