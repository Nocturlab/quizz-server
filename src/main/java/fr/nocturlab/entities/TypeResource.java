package fr.nocturlab.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class TypeResource {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, updatable = false)
	private Integer id;
	@Column(unique = true)
	@NotNull private String name;
	@Column(name = "creation_date", updatable = false)
	private LocalDateTime creationDate;

	public TypeResource(){}

	public TypeResource(String name){
		this();
		this.name = name;
	}

	@Transient
	@Override
	public String toString() {
		return this.name;
	}
}