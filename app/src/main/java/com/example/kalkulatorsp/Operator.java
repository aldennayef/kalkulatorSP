package com.example.kalkulatorsp;

public class Operator {
    private String operator;

    public Operator(String inioperator) {
        this.operator = inioperator;
    }

    @Override
    public String toString() {
        return operator;
    }



    public String getOperator() {
        return operator;
    }
}
