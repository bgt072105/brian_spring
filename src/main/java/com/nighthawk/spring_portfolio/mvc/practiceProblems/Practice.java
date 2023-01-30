package com.nighthawk.spring_portfolio.mvc.practiceProblems;

import javax.persistence.*;

@Entity // Annotation to simplify creating an entity, which is a lightweight persistence domain object. Typically, an entity represents a table in a relational database, and each entity instance corresponds to a row in that table.
public class Practice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String Problem;
    
    @Column
    private int Unit;

    @Column
    private String Topic ;

    @Column
    private String tags;

    private Practice() {   

    }

    protected Practice(Long id, String Problem, int Unit, String Topic, String tags) {
        if (joke == null) throw new NullPointerException("joke");
        this.haha = haha;
        this.boohoo = boohoo;
        System.out.println(joke);
        this.joke = joke;
        this.share = share;
    }

    public String getJoke() {
        return this.joke;
    }

    public int getHaha() {
        return this.haha;
    }

    public int getBoohoo() {
        return this.boohoo;
    }

    public int getShare() {
        return this.share;
    }

    public void setJoke(String joke) {
        this.joke=joke;
    }

    public void setHaha(int haha) {
        this.haha=haha;
    }

    public void setBoohoo(int boohoo) {
        this.boohoo=boohoo;
    }

    public void setShare(int share) {
        this.share=share;
    }
}
