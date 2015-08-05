package com.zarate.jesus.drinkwater;

import com.zarate.jesus.drinkwater.Settings.Settings;

/**
 * Created by Jesus Zarate on 8/1/15.
 */
public class WaterConsumption
{
    private static WaterConsumption instance = new WaterConsumption();

    public static WaterConsumption getInstance()
    {
        return instance;
    }

    private WaterConsumption()
    {
    }

    private int TotalWaterConsumption = 0;
    private double TotalWaterPercentage = 0;
    private double RemainingWaterConsumption = 0;
    private double _percentagePortion = 0;
//
//    public int getTotalWaterConsumption()
//    {
//        return TotalWaterConsumption;
//    }
//
//    public void setTotalWaterConsumption(int totalWaterConsumption)
//    {
//        TotalWaterConsumption = totalWaterConsumption;
//    }
//
//    public double getTotalWaterPercentage()
//    {
//        return TotalWaterPercentage;
//    }
//
//    public void setTotalWaterPercentage(double totalWaterPercentage)
//    {
//        TotalWaterPercentage = totalWaterPercentage;
//    }
//
//    public boolean addWater(int height)
//    {
//        int part = (Settings.getInstance().getTotalWaterNeeded() / Settings.getInstance().getCupSize());
//        int amount = height / part;
//        _percentagePortion = 100 / part;
//        TotalWaterPercentage += _percentagePortion;
//
//        //if ((TotalWaterConsumption + amount) < height)
//        {
//            TotalWaterConsumption += amount;
//            return true;
//        }
//        //return false;
//    }
//
//    public boolean removeWater(int height){
//        int part = (Settings.getInstance().getTotalWaterNeeded() / Settings.getInstance().getCupSize());
//        int amount = height / part;
//        _percentagePortion = 100 / part;
//
//
//        if ((TotalWaterConsumption) > 0)
//        {
//            TotalWaterPercentage -= _percentagePortion;
//            TotalWaterConsumption -= amount;
//            return true;
//        }
//        return false;
//    }
}
