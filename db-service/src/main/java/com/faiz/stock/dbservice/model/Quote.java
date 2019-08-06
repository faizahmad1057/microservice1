package com.faiz.stock.dbservice.model;


import javax.persistence.*;

@Entity
@Table( name="quotes")
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "USERNAME")
    private String userName;
    @Column(name = "QUOTE")
    private String quote;

    public Quote(String userName, String quote) {
        this.userName = userName;
        this.quote = quote;
    }

    public Quote() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = userName;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
}
