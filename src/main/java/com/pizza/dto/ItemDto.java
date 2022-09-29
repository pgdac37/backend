package com.pizza.dto;

import java.util.Arrays;

public class ItemDto {
	private int itemid;
	private String type;
	private String itemName;
	private String description;
	private byte[] itemImages;
	
	public ItemDto() {
		// TODO Auto-generated constructor stub
	}

	public ItemDto(int itemid, String type, String itemName, String description, byte[] itemImages) {
		super();
		this.itemid = itemid;
		this.type = type;
		this.itemName = itemName;
		this.description = description;
		this.itemImages = itemImages;
	}

	public int getItemid() {
		return itemid;
	}

	public void setItemid(int itemid) {
		this.itemid = itemid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getItemImages() {
		return itemImages;
	}

	public void setItemImages(byte[] itemImages) {
		this.itemImages = itemImages;
	}

	@Override
	public String toString() {
		return "ItemDto [itemid=" + itemid + ", type=" + type + ", itemName=" + itemName + ", description="
				+ description + ", itemImages=" + Arrays.toString(itemImages) + "]";
	}

	

	
	
	
}
