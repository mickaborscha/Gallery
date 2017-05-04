package com.mickaborscha.bindingModel;


import com.mickaborscha.entity.Payment;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class PaymentBindingModel {

    private String comment;

    private Date date;

    private double sum;

    private List<Payment> payments;

    public void setSum(double sum) {
        this.sum = sum;
    }

    public Date getDate() {
        return date;
    }

    public double getSum() {
        return sum;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}
