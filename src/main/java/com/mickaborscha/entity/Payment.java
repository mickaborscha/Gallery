package com.mickaborscha.entity;


import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "payment")
public class Payment {


    private int id;


    private double sum;


    private Date date;


    private String comment;


    private User author;




    @ManyToOne
    @JoinColumn(name = "author_id")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Payment(double sum , Date date, String comment){
        this.date = date;
        this.comment = comment;
        this.sum = sum;
    }
    public Payment(){}

    public void setComment(String comment) {
        this.comment = comment;
    }
    @Column(name = "comment",nullable = false)
    public String getComment() {
        return comment;
    }
    @Column(name = "date",nullable = false)
    public Date getDate() {
        return date;
    }
    @Column(name = "sum", nullable = false)
    public double getSum() {
        return sum;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }


}
