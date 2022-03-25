package com.revature.modals;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ninjas")
public class Ninja {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true, nullable=false)
	private String ninjaName;
	@Column(nullable=false)
	private String village;
	@Column(nullable=false)
	private String jutsu;
	@Column(nullable=false)
	private String details;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNinjaName() {
		return ninjaName;
	}
	public void setNinjaName(String ninjaName) {
		this.ninjaName = ninjaName;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getJutsu() {
		return jutsu;
	}
	public void setJutsu(String jutsu) {
		this.jutsu = jutsu;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	@Override
	public String toString() {
		return "Ninja [id=" + id + ", ninjaName=" + ninjaName + ", village=" + village + ", jutsu=" + jutsu
				+ ", details=" + details + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(details, id, jutsu, ninjaName, village);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ninja other = (Ninja) obj;
		return Objects.equals(details, other.details) && id == other.id && Objects.equals(jutsu, other.jutsu)
				&& Objects.equals(ninjaName, other.ninjaName) && Objects.equals(village, other.village);
	}
	
	
	
	

	

}
