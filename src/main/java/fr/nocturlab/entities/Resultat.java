package fr.nocturlab.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

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
    private Account account;
    @ManyToOne
    private Question question;
    @OneToMany
    @JoinTable(name = "resultat_answer")
    private List<Answer> answers;
    @Column(name = "creation_date", insertable = false, updatable = false)
    private LocalDateTime creationDate;
    @Column(name = "duration", updatable = false)
    private Integer duration;

	public Resultat(){
        this.creationDate = LocalDateTime.now();
    }

	public Resultat(Account account, Question question, List<Answer> answers, Integer duration){
		this();
        this.account = account;
        this.question = question;
        this.answers = answers;
        this.duration = duration;
    }
    
    @Transient
	@Override
	public String toString() {
		return "Account "+this.account +" has found "+ (this.getQuestion().isCorrect(this.answers)?"correct":"incorrect") +" answer in "+ this.duration +" seconds for question : "+ this.question;
	}
}