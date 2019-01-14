package com.lmq.ui.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class HealthInfo implements Serializable{

    public Health_Base base;
    public ArrayList<HealthProblem> healthProblems;
    public ArrayList<HospitalHistory> healthhospitals;

    public Health_Base getBase() {
        return base;
    }

    public void setBase(Health_Base base) {
        this.base = base;
    }

    public ArrayList<HealthProblem> getHealthProblems() {
        return healthProblems;
    }

    public void setHealthProblems(ArrayList<HealthProblem> healthProblems) {
        this.healthProblems = healthProblems;
    }

    public ArrayList<HospitalHistory> getHealthhospitals() {
        return healthhospitals;
    }

    public void setHealthhospitals(ArrayList<HospitalHistory> healthhospitals) {
        this.healthhospitals = healthhospitals;
    }
}
