package net.sumo.nextgen.enums.items;

import org.osbot.rs07.api.ui.EquipmentSlot;

public enum GenItem {
	

	YEW_LOG("Yew logs", 1515),NORMAL_LOG("Logs", 1511),

	
	//others
	NOTHING("Nothing", 0);
	
	private String itemName;
	private int ID;
	private EquipmentSlot slot;
	GenItem(String itemName, int ID){
		this.itemName= itemName;
		this.ID = ID;
	}
	
	GenItem(String itemName, int ID, EquipmentSlot slot){
		this.itemName= itemName;
		this.ID = ID;
		this.slot = slot;
	}
	
	
	public String getItemName(){
		return itemName;
	}
	
	public int getItemID(){
		return ID;
	}
	
	public EquipmentSlot getEquipmentSlot(){
		return slot;
	}
	
	public static GenItem genItemExist(String name){
		for(GenItem gen : GenItem.values()){
			if(gen.getItemName() == name){
				return gen;
			}
		}
		return null;
	}
	public static GenItem genItemExist(int id){
		for(GenItem gen : GenItem.values()){
			if(gen.getItemID() == id){
				return gen;
			}
		}
		return null;
	}
}
