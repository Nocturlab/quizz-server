package fr.nocturlab.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, updatable = false)
	private Integer id;
	private String pseudo;
	private String email;
	private Float difficulty;
	@Column(insertable = false, updatable = false)
	@JsonIgnore
	private UUID token;
	@JsonIgnore
	private byte[] pass;
	@Column(name = "creation_date", insertable = false, updatable = false)
	private LocalDateTime creationDate;

	@Transient
	public void incDifficulty(Float questionDifficulty){
		this.difficulty+=(questionDifficulty)*0.1f;
	}
	@Transient
	public void decDifficulty(Float questionDifficulty){
		this.difficulty-=(questionDifficulty)*0.1f;
	}
}