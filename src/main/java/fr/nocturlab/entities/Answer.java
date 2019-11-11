package fr.nocturlab.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

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
    private String value;

	@Column(name = "creation_date", updatable = false)
    private LocalDateTime creationDate;

    public Answer() {
        this.creationDate = LocalDateTime.now();
    }
    public Answer(String value) {
        this();
        this.value = value;
    }

    @Transient
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof String)
            return obj.equals(this.value);
        else if(obj instanceof Answer)
            return ((Answer)obj).value.equals(this.value);
        else 
            return obj.equals(this);
    }

    @Transient
    @Override
    public String toString() {
        return this.value;
    }
}