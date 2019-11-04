package fr.nocturlab.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, updatable = false)
	private Integer id;
	@Column(unique = true)
	@NotNull private String name;
	private String description;
	@OneToMany(mappedBy = "category")
	private List<Question> questions;
	@Column(name = "creation_date", updatable = false)
	private LocalDateTime creationDate;

	public Category(){
		this.questions = new ArrayList<Question>();
		this.creationDate = LocalDateTime.now();
	}
	
	public Category(String name){
		this();
		this.name = name;
	}
	
	public Category(String name, String description){
		this(name);
		this.description = description;
	}

	@Transient
	@Override
	public String toString() {
		return this.name;
	}
}