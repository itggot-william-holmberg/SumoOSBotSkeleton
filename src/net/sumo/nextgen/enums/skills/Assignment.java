package net.sumo.nextgen.enums.skills;

import java.util.ArrayList;
import java.util.List;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.constants.Banks;

import net.sumo.nextgen.data.Areas;
import net.sumo.nextgen.data.ObjectID;
import net.sumo.nextgen.enums.items.GenItem;

public enum Assignment {
	
	NORMAL_TREE_LUMBRIDGE(Areas.NORMAL_TREE_LUMBRIDGE, GenItem.NORMAL_LOG, ObjectID.NORMAL_TREE_ID, 4, 50), 
	//OAK_TREE_LUMBRIDGE("Oak", "Oak logs", Areas.OAK_TREE_LUMBRIDGE_AREA, Banks.LUMBRIDGE_UPPER),
	//WILLOW_TREE_LUMBRIDGE("Willow", "Willow logs", Areas.WILLOW_TREE_LUMBRIDGE_AREA, Banks.DRAYNOR),
	//WILLOW_TREE_DRAYNOR("Willow", "Willow logs", Areas.WILLOW_TREE_DRAYNOR_AREA, Banks.DRAYNOR),
	//MAPLE_TREE_CAMELOT("Maple tree", "Maple logs", Areas.MAPLE_TREE_CAMMY, Banks.CAMELOT),
	//YEW_TREE_CAMELOT("Yew", "Yew logs", Areas.YEW_TREE_SEERS, Banks.CAMELOT),
	SEERS_YEW(Areas.SEERS_YEWS_AREA, GenItem.YEW_LOG, ObjectID.YEW_TREE_ID, 4, 175);

	private Area wcArea;
	private GenItem log;
	private List<Integer> treeID;
	private int peopleAllowedInSameArea;
	private int xpPerLog;

	Assignment(Area wcArea, GenItem log, int[] treeID, int peopleAllowedInSameArea, int xpPerLog) {
		this.wcArea = wcArea;
		this.log = log;
		this.treeID = new ArrayList<Integer>();
		for (Integer i : treeID) {
			this.treeID.add(i);
		}
		this.peopleAllowedInSameArea = peopleAllowedInSameArea;
		this.xpPerLog = xpPerLog;
	}
	
	public Area getWcArea(){
		return wcArea;
	}
	
	public GenItem getLog(){
		return log;
	}
	
	public List<Integer> getTreeID(){
		return treeID;
	}
	
	public int getPeopleAllowedInSameArea(){
		return peopleAllowedInSameArea;
	}
	
	public int getXpPerLog(){
		return xpPerLog;
	}
}
