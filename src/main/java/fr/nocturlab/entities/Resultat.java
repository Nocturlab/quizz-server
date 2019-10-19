package fr.nocturlab.entities;

import java.time.LocalDateTime;
import java.util.List;

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
public class Resultat {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, updatable = false)
    private Integer id;
    @ManyToOne
	@JoinColumn(name="account")
    private Account account;
    @ManyToOne
	@JoinColumn(name="question")
    private Question question;
    @ManyToOne
	@JoinColumn(name="answer")
    private List<Answer> answer;
    @Column(name = "creation_date", insertable = false, updatable = false)
    private LocalDateTime creationDate;
    @Column(name = "duration", updatable = false)
    private Long duration;

	public Resultat(){
        this.creationDate = LocalDateTime.now();
    }

	public Resultat(Account account, Question question, List<Answer> answer, Long duration){
		this();
        this.account = account;
        this.question = question;
        this.answer = answer;
        this.duration = duration;
	}
}