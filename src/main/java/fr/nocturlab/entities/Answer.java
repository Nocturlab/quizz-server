package fr.nocturlab.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Answer {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, updatable = false)
	private Integer id;
    @ManyToOne
	@JoinColumn(name="question")
    private Question question;
    @ManyToOne
	@JoinColumn(name="answer")
    private Answer answer;
    @Column(name = "creation_date", insertable = false, updatable = false)
    private LocalDateTime creationDate;
}