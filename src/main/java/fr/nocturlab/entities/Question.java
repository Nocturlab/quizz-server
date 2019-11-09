package fr.nocturlab.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
	@ManyToOne
	@NotNull private Category category;
	@ManyToMany
	@JsonIgnore
	@NotNull private List<Answer> validAnswer;
	@Column(name = "creation_date", updatable = false)
	private LocalDateTime creationDate;

	public Question(){
		this.difficulty = 1f;
		this.answers = new ArrayList<>();
		this.validAnswer = new ArrayList<>();
		this.creationDate = LocalDateTime.now();
	}
	private Question(String value, Iterable<Answer> answers){
		this();
		this.value = value;
		this.answers = (ArrayList<Answer>)answers;
	}
	public Question(String value, Iterable<Answer> answers, List<Integer> validAnswer){
		this(value, answers);
		validAnswer.forEach((Integer answer)->{
			this.validAnswer.add(this.answers.get(answer));
		});
	}
	public Question(String value, Iterable<Answer> answers, Integer validAnswer){
		this(value, answers);
		this.validAnswer.add(this.answers.get(validAnswer));
	}

	public Question(String value, Iterable<Answer> answers, List<Integer> validAnswer, Category categ){
		this(value, answers, validAnswer);
		this.category = categ;
	}
	public Question(String value, Iterable<Answer> answers, Integer validAnswer, Category categ){
		this(value, answers, validAnswer);
		this.category = categ;
	}
	
	public Question(String value, Iterable<Answer> answers, List<Integer> validAnswer, Category categ, Resource resource){
		this(value, answers, validAnswer, categ);
		this.resource = resource;
	}
	public Question(String value, Iterable<Answer> answers, Integer validAnswer, Category categ, Resource resource){
		this(value, answers, validAnswer, categ);
		this.resource = resource;
	}
	
	@Transient
	public void incDifficulty(Float accountDifficulty){
		this.difficulty+=(accountDifficulty)*0.1f;
	}
	@Transient
	public void decDifficulty(Float accountDifficulty){
		this.difficulty-=(accountDifficulty)*0.1f;
	}
    @Transient
	public boolean isCorrect(List<Answer> answers) {
		boolean res = answers.size() == this.answers.size();
		if(res)
			for (Answer answer : this.answers) {
				if(!answers.contains(answer))
					res = false;
			}
		return res;
	}

	@Transient
	@Override
	public String toString() {
		return this.value;
	}
	
}