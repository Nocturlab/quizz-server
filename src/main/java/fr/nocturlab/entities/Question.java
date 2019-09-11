package fr.nocturlab.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, updatable = false)
	private Integer id;
	private String value;
	private Float difficulty;
	@OneToMany
	private List<Answer> answers;
	@ManyToOne
	@JsonIgnore
	private Answer validAnswer;
	@Column(name = "creation_date", insertable = false, updatable = false)
	private LocalDateTime creationDate;

	@Transient
	public void incDifficulty(Float accountDifficulty){
		this.difficulty+=(accountDifficulty)*0.1f;
	}
	@Transient
	public void decDifficulty(Float accountDifficulty){
		this.difficulty-=(accountDifficulty)*0.1f;
	}
}