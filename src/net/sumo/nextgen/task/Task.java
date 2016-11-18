package net.sumo.nextgen.task;

import org.osbot.rs07.api.ui.Skill;

import net.sumo.nextgen.enums.skills.Assignment;

public class Task {

	private Assignment task;
	private int levelGoal;

	public Task(Assignment task, int levelGoal) {
		this.task = task;
		this.levelGoal = levelGoal;
	}

	public Assignment getTask() {
		return task;
	}

	public int getLevelGoal() {
		return levelGoal;
	}

	public String toString() {
		return levelGoal + " wc at " + task.toString();
	}
}