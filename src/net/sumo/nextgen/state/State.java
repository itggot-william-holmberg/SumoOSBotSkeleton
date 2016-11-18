package net.sumo.nextgen.state;

import java.util.Random;

import org.osbot.rs07.api.Bank.BankMode;
import org.osbot.rs07.api.Players;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.EquipmentSlot;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.event.WalkingEvent;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;

import net.sumo.nextgen.Nextgen;
import net.sumo.nextgen.api.GrandExchangeApiPrice;
import net.sumo.nextgen.data.Resources;
import net.sumo.nextgen.enums.WebBank;
import net.sumo.nextgen.enums.items.GenItem;
import net.sumo.nextgen.enums.skills.Assignment;

public abstract class State {

	protected Script script;

	private int sleepTime;

	public State init(Script script) {

		this.script = script;
		
		return this;
	}

	public abstract boolean active();

	public abstract void execute();

	public abstract String toString();

	// player methods

	public void unequipAmulet() {
		script.equipment.unequip(EquipmentSlot.AMULET);
		sleep(2535);
	}

	public void unequipRing() {
		script.equipment.unequip(EquipmentSlot.RING);
		sleep(1738);
	}

	public boolean playerIsWieldingWeapon(String item) {
		return script.equipment.isWieldingWeapon(item);
	}

	public boolean playerIsWearingAmulet(String item) {
		return script.equipment.isWearingItem(EquipmentSlot.AMULET, item);
	}

	public boolean playerIsWearingRing(String item) {
		return script.equipment.isWearingItem(EquipmentSlot.RING, item);
	}

	public boolean playerIsWearingAmuletThatContains(String item) {
		return script.equipment.isWearingItemThatContains(EquipmentSlot.AMULET, item);
	}

	public boolean playerIsWearingRingThatContains(String item) {
		return script.equipment.isWearingItemThatContains(EquipmentSlot.RING, item);
	}

	public boolean playerInArea(Area area) {
		return area != null && area.contains(script.myPlayer());
	}

	public boolean playerInPosition(Position pos) {
		return pos != null && script.myPosition().equals(pos);
	}

	public boolean playerInPosition(Position pos, int distance) {
		return pos != null && script.myPosition().distance(pos) <= distance;
	}

	// npc methods

	public void interactNpc(String interaction, int npcId) {
		NPC npc = script.npcs.closest(npcId);
		if (npc != null) {
			npc.interact(interaction);
		}
	}

	// inventory methods

	public boolean invIsFull() {
		return script.inventory.isFull();
	}

	public boolean invContains(String item) {
		return script.inventory.contains(item);
	}

	public int getAmount(String item) {
		return (int) script.inventory.getAmount(item);
	}

	public int getAmount(int item) {
		return (int) script.inventory.getAmount(item);
	}

	// equipment methods

	public void equip(String item) {
		if (invContains(item)) {
			script.inventory.interact("Wield", item);
		}
		sleep(1395);
	}

	// other players

	public int playerCounter() {
		int players = 0;

		for (Player p : script.getPlayers().getAll()) {
			if (p != null && p != script.myPlayer()) {
				players++;
			}
		}
		return players;
	}

	public int playerCounter(Area area) {
		int players = 0;

		for (Player p : script.getPlayers().getAll()) {
			if (p != null && p != script.myPlayer() && area.contains(p)) {
				players++;
			}
		}
		return players;
	}

	public int playersInPos(Position pos) {
		int players = 0;

		for (Player p : script.getPlayers().getAll()) {
			if (p != null && p != script.myPlayer() && p.getPosition().equals(pos)) {
				players++;
			}
		}
		return players;
	}

	public int playersInArea(Area area) {
		int players = 0;

		for (Player p : script.getPlayers().getAll()) {
			if (p != null && p != script.myPlayer() && area.contains(p)) {
				players++;
			}
		}
		return players;
	}

	public int playersInMyPos() {
		int players = 0;

		for (Player p : script.getPlayers().getAll()) {
			if (p != null && p != script.myPlayer() && p.getPosition().equals(script.myPosition())) {
				players++;
			}
		}
		return players;
	}

	// walking methods

	public void webWalk(Area area) {
		updateMission("Walking...");
		script.walking.webWalk(area);
	}

	public void webWalk(Position pos) {
		updateMission("Walking...");
		script.walking.webWalk(pos);
	}

	public void walkExact(Position pos) {
		updateMission("Walking exact...");
		WalkingEvent w = new WalkingEvent(pos);
		w.setMiniMapDistanceThreshold(0);
		w.setMinDistanceThreshold(0);
		script.execute(w);
	}

	// banking methods

	public WebBank closestBank() {
		return WebBank.getNearest(script);
	}

	public boolean playerInClosestBank() {
		return playerInArea(closestBank().getArea());
	}
	
	public void withdraw(String item) {
		updateMission("Withdrawing: " + item);
		if (script.bank.isOpen()) {
			if (script.bank.contains(item)) {
				script.bank.withdraw(item, 1);
				sleep(1395);
			} else {
					script.log("we dont have the required item: " + item);
					script.stop();
			}
		} else {
			try {
				script.bank.open();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void depositAll(String item) {
		updateMission("depositing all: " + item);
		if (invContains(item)) {
			if (script.bank.isOpen()) {
				script.bank.depositAll(item);
				sleep(1395);
			} else {
				try {
					script.bank.open();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void depositAllExcept(String item) {
		updateMission("depositing: " + item);
		if (script.bank.isOpen()) {
			script.bank.depositAllExcept(item);
			sleep(1395);
		} else {
			try {
				script.bank.open();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	

	public Assignment currentWCAssignment() {
		Resources.currentWCAssignment = Resources.currentTask.getTask();
		return Resources.currentTask.getTask();
	}

	public boolean playerInWCArea() {
		return playerInArea(wcArea());
	}

	public Area wcArea() {
		return currentWCAssignment().getWcArea();
	}

	public boolean playerHasAxe() {
		if (playerIsWieldingWeapon(getBestAxe()) || invContains(getBestAxe())) {
			return true;
		}
		return false;
	}

	public String getBestAxe() {
		if(Resources.wcAxe != null){
			return Resources.wcAxe;
		}
		if (getWcLevel() < 21) {
			return "Bronze axe";
		} else if (getWcLevel() < 31) {
			return "Mithril axe";
		} else if (getWcLevel() < 41) {
			return "Adamant axe";
		}
		return "Rune axe";
	}

	private int getWcLevel() {
		return getLevel(Skill.WOODCUTTING);
	}

	private int getLevel(Skill woodcutting) {
		return script.skills.getDynamic(woodcutting);
	}

	public boolean playerIsReadyToWc() {
		if (!invIsFull() && playerHasAxe()) {
			return true;
		}
		return false;
	}

	// sleep methods

	public void sleep(int milli) {
		try {
			Resources.timeWhenWeStartedTheSleep = System.currentTimeMillis();
			Script.sleep(milli + (random(milli) / 10));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sleep(int milli, boolean random) {
		if (random) {
			try {
				Script.sleep(Script.random(milli, milli + 350));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			sleep(milli);
		}
	}

	public void reactionSleep() {
		sleep(Resources.reactionTime + random(100));
	}

	// teleport method
	public void teleportWithRing(String option) {
		script.widgets.closeOpenInterface();
		sleep(1395);
		script.getEquipment().interact(EquipmentSlot.RING, option);
		sleep(3582);
	}

	public void teleportWithAmulet(String option) {
		script.widgets.closeOpenInterface();
		sleep(1049);
		script.getEquipment().interact(EquipmentSlot.AMULET, option);
		sleep(4654);
	}

	// antibans

	public void skillingAntiBanAfterInteraction() {
		int random = random(200);
		switch (random) {
		case 11:
			sleepTime = Resources.afkToiletTime + random(3000, 10000);
			updateMission("Lets go to the toilet, brb ~~50 sec");
			Resources.sleeps += 1;
			Resources.totalTimeAntiBanSleep += sleepTime;
			sleep(sleepTime);
			break;
		case 17:
			sleepTime = Resources.afkSandwichTime + random(5000, 15000);
			updateMission("Need a sandwich.. brb 80 sec");
			Resources.sleeps += 1;
			Resources.totalTimeAntiBanSleep += sleepTime;
			sleep(sleepTime);
			break;
		case 21:
			sleepTime = Resources.afkMomYellingTime + random(5000, 15000);
			updateMission("Mom is yelling.. brb atleast 30 sec");
			Resources.sleeps += 1;
			Resources.totalTimeAntiBanSleep += sleepTime;
			sleep(sleepTime);
			break;
		case 69:
			sleepTime = Resources.afkPhoneCallingTime + random(3000, 10000);
			updateMission("phone call incoming, brb atleast 15 sec");
			Resources.sleeps += 1;
			Resources.totalTimeAntiBanSleep += sleepTime;
			sleep(sleepTime);
			break;
		case 100:
			sleepTime = Resources.afkNeedSomeWaterTime + random(5000, 15000);
			updateMission("Need some water, brb atleast 35 sec");
			Resources.sleeps += 1;
			Resources.totalTimeAntiBanSleep += sleepTime;
			sleep(sleepTime);
			break;
		}
	}

	// other methods

	public int random(int i) {
		Random r = new Random();
		return r.nextInt(i);
	}

	public int random(int i, int l) {
		return Script.random(i, l);
	}

	public void updateMission(String s) {
		if (Resources.currentMission == s) {
			return;
		}
		Resources.currentMission = s;
	}

}
