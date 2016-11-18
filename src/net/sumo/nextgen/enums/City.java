package net.sumo.nextgen.enums;

import org.osbot.rs07.api.map.Area;

import net.sumo.nextgen.data.Areas;


public enum City {
	
	VARROCK(Areas.VARROCK_AREA);
	
	private Area area;
	City(Area cityArea) {
		this.area = cityArea;
	}
	
	public Area getArea(){
		return area;
	}
}
