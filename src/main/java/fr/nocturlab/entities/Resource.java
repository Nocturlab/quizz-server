package fr.nocturlab.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Resource {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, updatable = false)
	private Integer id;
	@NotNull private String name;
	@ManyToOne
	@NotNull private TypeResource type;
	private String content;
	private String summary;
	private String author;
	@Column(name = "creation_date", insertable = false, updatable = false)
	private LocalDateTime creationDate;

	public Resource(){}

	public Resource(String name){
		this();
		this.name = name;
	}
	public Resource(String name, String author, TypeResource type){
		this(name);
		this.author = author;
		this.type = type;
	}
	public Resource(String name, String author, TypeResource type, String summary){
		this(name, author, type);
		this.summary = summary;
	}
	public Resource(String name, String author, TypeResource type, String summary, String content){
		this(name, author, type, summary);
		this.content = content;
	}

	@Transient
	@Override
	public String toString() {
		return this.name;
	}
}