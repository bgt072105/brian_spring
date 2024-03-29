package com.nighthawk.spring_portfolio.mvc.schedulestest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nighthawk.spring_portfolio.mvc.person.Person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(columnDefinition = "TEXT") // Allows for longer strings
    private String classname;
    private String period;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false) // Foreign key
    @OnDelete(action = OnDeleteAction.CASCADE) // Delete all equations when a person is deleted
    @JsonIgnore // Prevents infinite recursion
    private Person person;

    public Schedule(String classname, String period, Person person) {
        this.classname = classname;
        this.period = period;
        this.person = person;
    }
}