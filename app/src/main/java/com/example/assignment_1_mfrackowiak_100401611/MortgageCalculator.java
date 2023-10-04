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


    // P x R x (1+R)^N / [(1+R)^N-1] where
    //P = Principal loan amount
    //N = number of payments
    //R = Monthly interest rate
    public double monthlyPayment(){
        double monthlyInterestRate = interest / 12 / 100;
        double numberOfPayments = amortization * 12;
        double rate = Math.pow(1 + monthlyInterestRate, numberOfPayments);
        double emi = principal * monthlyInterestRate * rate/rate-1;
        return emi;
    }

    // Additional methods for calculations can be added here
}

