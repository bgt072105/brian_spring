package com.nighthawk.spring_portfolio.mvc.jokes;

import javax.persistence.*;

@Entity // Annotation to simplify creating an entity, which is a lightweight persistence domain object. Typically, an entity represents a table in a relational database, and each entity instance corresponds to a row in that table.
public class Jokes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String joke;
    
    @Column
    private int haha;

    @Column
    private int boohoo;

    @Column
    private int share;

    private Jokes() {   

    }

    protected Jokes(Long id, String joke, int haha, int boohoo, int share) {
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
