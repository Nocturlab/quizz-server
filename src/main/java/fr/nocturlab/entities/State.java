package fr.nocturlab.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.nocturlab.manager.AccountManager;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class State {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, updatable = false)
	private Integer id;
	@Column(unique = true)
	private String name;

	// Value for each level of a state
	private Integer low;
	private Integer medium;
	private Integer high;

	// each is for example : 1 times each 5 minutes
	private Integer each;

	public State(){}

	public State(String name) {
		this.name = name;
	}
	public State(String name, String low, Integer medium, Integer high) {
		this(pseudo);
		this.low = low;
		this.medium = medium;
		this.high = high;
		this.each = 1;
	}
	
	public State(String name, String low, Integer medium, Integer high, Integer each) {
		this(pseudo, low, medium, high);
		this.each = each;
	}

	@Transient
	@Override
	public String toString() {
		return this.name;
	}
}
