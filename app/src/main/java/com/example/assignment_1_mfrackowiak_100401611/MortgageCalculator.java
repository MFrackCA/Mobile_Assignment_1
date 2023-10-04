package com.example.assignment_1_mfrackowiak_100401611;


import java.io.Serializable;

public class MortgageCalculator implements Serializable {
    private double principal = 0;
    private double interest = 0;
    private double amortization = 0;

    // Constructor
    public MortgageCalculator() {
        principal = 0;
        interest = 0;
        amortization = 0;
    }
    // Setter methods
    public void setPrincipal(String principal) {
        this.principal = Double.parseDouble(principal);
    }

    public void setInterest(String interest) {
        this.interest = Double.parseDouble(interest);
    }

    public void setAmortization(String amortization) {
        this.amortization = Double.parseDouble(amortization);
    }

    // Getter methods
    public double getPrincipal() {
        return principal;
    }

    public double getInterest() {
        return interest;
    }

    public double getAmortization() {
        return amortization;
    }


    public double monthlyPayment(){
        double monthlyInterestRate = interest / 12 / 100;
        double numberOfPayments = amortization * 12;
        double denominator = Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1;
        return principal * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments) / denominator;
    }

    // Additional methods for calculations can be added here
}

