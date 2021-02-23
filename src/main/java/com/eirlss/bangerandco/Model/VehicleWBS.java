package com.eirlss.bangerandco.Model;

public class VehicleWBS
{
    private String  carName;
    private String monthlyRate;
    private String weeklyRate;
    private String excessMileage;

    public VehicleWBS()
    {

    }

    public VehicleWBS(String carName, String monthlyRate, String weeklyRate, String excessMileage)
    {
        this.carName = carName;
        this.monthlyRate = monthlyRate;
        this.weeklyRate = weeklyRate;
        this.excessMileage = excessMileage;
    }

    public String getCarName()
    {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getMonthlyRate() {
        return monthlyRate;
    }

    public void setMonthlyRate(String monthlyRate) {
        this.monthlyRate = monthlyRate;
    }

    public String getWeeklyRate() {
        return weeklyRate;
    }

    public void setWeeklyRate(String weeklyRate) {
        this.weeklyRate = weeklyRate;
    }

    public String getExcessMileage() {
        return excessMileage;
    }

    public void setExcessMileage(String excessMileage) {
        this.excessMileage = excessMileage;
    }
}
