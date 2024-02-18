package com.mimoza.furnimoza.Models;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Details {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int length;
	private int width;
	private int thick;
	private int amount;
	private String material;
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getThick() {
		return thick;
	}
	public void setThick(int thick) {
		this.thick = thick;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Details(int length, int width, int thick, int amount, String material) {
		super();
		this.length = length;
		this.width = width;
		this.thick = thick;
		this.amount = amount;
		this.material = material;
	}
	public Details() {
		super();
	}
	
	

}
