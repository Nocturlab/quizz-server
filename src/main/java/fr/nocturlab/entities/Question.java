package fr.nocturlab.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

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
	@NotNull private String value;
	@NotNull private Float difficulty;
	@OneToMany
	private List<Answer> answers;
	@ManyToOne
	private Resource resource;
	@ManyToMany
	@JsonIgnore
	@NotNull private List<Answer> validAnswer;
	@Column(name = "creation_date", insertable = false, updatable = false)
	private LocalDateTime creationDate;

	public Question(){
		this.difficulty = 1f;
		this.answers = new ArrayList<>();
		this.validAnswer = new ArrayList<>();
		this.creationDate = LocalDateTime.now();
	}

	public Question(String value, List<Answer> answers, List<Answer> validAnswer){
		this();
		this.value = value;
		this.answers = answers;
		this.validAnswer = validAnswer;
	}
	public Question(String value, List<Answer> answers, Answer validAnswer){
		this();
		this.value = value;
		this.answers = answers;
		this.validAnswer.add(validAnswer);
	}

	public Question(String value, List<Answer> answers, Answer validAnswer, Category categ){
		this(value, answers, validAnswer);
		categ.addQuestions(this);
	}

	@Transient
	public void incDifficulty(Float accountDifficulty){
		this.difficulty+=(accountDifficulty)*0.1f;
	}
	@Transient
	public void decDifficulty(Float accountDifficulty){
		this.difficulty-=(accountDifficulty)*0.1f;
	}
}