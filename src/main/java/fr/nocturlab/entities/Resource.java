package fr.nocturlab.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
	private String content;
	private String summary;
	private String author;
	@ManyToOne
	@NotNull private Resource resource;
	@Column(name = "creation_date", insertable = false, updatable = false)
	private LocalDateTime creationDate;

	public Resource(){}

	public Resource(String name){
		this();
		this.name = name;
	}
	public Resource(String name, String author){
		this(name);
		this.author = author;
	}
	public Resource(String name, String author, String content){
		this(name, author);
		this.content = content;
	}
	public Resource(String name, String author, String content, String summary){
		this(name, author, content);
		this.summary = summary;
	}
}