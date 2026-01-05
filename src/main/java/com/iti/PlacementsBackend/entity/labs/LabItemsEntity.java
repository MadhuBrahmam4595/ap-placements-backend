package com.iti.PlacementsBackend.entity.labs;

import java.util.Arrays;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "labitems", schema = "labs")
public class LabItemsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long labItemsId;
	
	private String itemName;
	private Double itemCost;
	private byte[] itemPhoto;
	
	@ManyToOne
	@JoinColumn(name = "labId")
	private LabEntity labEntity;
	private String itiCode;

	public LabItemsEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LabItemsEntity(Long labItemsId, String itemName, Double itemCost, byte[] itemPhoto, LabEntity labEntity,
			String itiCode) {
		super();
		this.labItemsId = labItemsId;
		this.itemName = itemName;
		this.itemCost = itemCost;
		this.itemPhoto = itemPhoto;
		this.labEntity = labEntity;
		this.itiCode = itiCode;
	}

	public Long getLabItemsId() {
		return labItemsId;
	}

	public void setLabItemsId(Long labItemsId) {
		this.labItemsId = labItemsId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Double getItemCost() {
		return itemCost;
	}

	public void setItemCost(Double itemCost) {
		this.itemCost = itemCost;
	}

	public byte[] getItemPhoto() {
		return itemPhoto;
	}

	public void setItemPhoto(byte[] itemPhoto) {
		this.itemPhoto = itemPhoto;
	}

	public LabEntity getLabEntity() {
		return labEntity;
	}

	public void setLabEntity(LabEntity labEntity) {
		this.labEntity = labEntity;
	}

	public String getItiCode() {
		return itiCode;
	}

	public void setItiCode(String itiCode) {
		this.itiCode = itiCode;
	}

	@Override
	public String toString() {
		return "LabItemsEntity [labItemsId=" + labItemsId + ", itemName=" + itemName + ", itemCost=" + itemCost
				+ ", itemPhoto=" + Arrays.toString(itemPhoto) + ", labEntity=" + labEntity + ", itiCode=" + itiCode
				+ "]";
	}

	
	

}
