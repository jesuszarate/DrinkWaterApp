package com.zarate.jesus.drinkwater;

import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Stack;

/**
 * Created by Jesus Zarate on 7/25/15.
 */
public class User
{
    private String _name;
    private double _weight = 145;
    private double _height = 0;
    private double TotalWaterConsumptionFill = 0;
    private double TotalWaterConsumption = 0;
    private double TotalWaterPercentage = 0;
    private double RemainingWaterConsumption = 0;
    private double _percentagePortion = 0;
    private int reminderTime = 15; // In minutes
    private int totalWaterNeeded = 64;
    private int _cupsConsumed = 0;
    private int _startTimeHour = 10;
    private int _startTimeMin = 10;
    private String _startTimeMeridiem = "am";
    private int _endTimeHour = 0;
    private int _endTimeMin = 0;
    private String _endTimeMeridiem = "am";


    private Stack<Double> TotalWaterConsumptionStack = new Stack<>();
    private Stack<Double> TotalWaterPercentageStack = new Stack<>();
    private Stack<Double> PortionStack = new Stack<>();

    private int cupSize = 20;
    private MeasurementUnits measurementUnit;


    private static User _instance = null;

    public static User getInstance()
    {
        if (_instance == null)
        {
            _instance = new User();
        }
        return _instance;
    }

    private User()
    {
    }

    public static void setInstance(User user)
    {
        _instance = user;
    }

    private HashMap<Integer, Integer> timeLengths = new HashMap<Integer, Integer>()
    {
        {
            put(5, 0);
            put(10, 1);
            put(15, 2);
            put(20, 3);
            put(25, 4);
            put(30, 5);
            put(35, 6);
            put(40, 7);
            put(45, 8);
            put(50, 9);
            put(55, 10);
            put(60, 11);
        }
    };



    private enum MeasurementUnits
    {
        ENGLISH,
        METRIC
    }

    private ActivityLevel _activityLevel = ActivityLevel.Moderate;

    private enum ActivityLevel
    {
        Low,
        Moderate,
        High
    }

    public HashMap<Integer, Integer> getTimeLengths()
    {
        return timeLengths;
    }

    public ActivityLevel get_activityLevel()
    {
        return _activityLevel;
    }

    public void set_activityLevel(ActivityLevel _activityLevel)
    {
        this._activityLevel = _activityLevel;
    }

    public int get_cupsConsumed()
    {
        return _cupsConsumed;
    }

    public void set_cupsConsumed(int _cupsConsumed)
    {
        this._cupsConsumed = _cupsConsumed;
    }

    public String get_name()
    {
        return _name;
    }

    public void set_name(String _name)
    {
        this._name = _name;
    }

    public double getWeight()
    {
        return _weight;
    }

    public void setWeight(double weight)
    {
        if(weight > 0)
            this._weight = weight;
    }

    public double get_height()
    {
        return _height;
    }

    public void set_height(double _height)
    {
        this._height = _height;
    }


    public double getTotalWaterConsumptionFill()
    {
        return TotalWaterConsumptionFill;
    }

    public void setTotalWaterConsumptionFill(int totalWaterConsumptionFill)
    {
        TotalWaterConsumptionFill = totalWaterConsumptionFill;
    }

    public double getTotalWaterPercentage()
    {
        return TotalWaterPercentage;
    }

    public void setTotalWaterPercentage(double totalWaterPercentage)
    {
        TotalWaterPercentage = totalWaterPercentage;
    }

    public double getTotalWaterConsumption()
    {
        return TotalWaterConsumption;
    }

    public void setTotalWaterConsumption(int totalWaterConsumption)
    {
        TotalWaterConsumption = totalWaterConsumption;
    }

    public boolean addWater(int height)
    {
        double part = 1;
        double amount = 0;
        if (getCupSize() > 0)
        {
            part = totalWaterNeeded / (double)cupSize;
        } else
            Log.e("Error", "In User, method 'addWater(int height) cupSize is 0 and it's trying to divide by 0'");

        if (part > 0)
        {
            amount = height / part;
            //_percentagePortion = totalWaterNeeded / part;
            _percentagePortion = 100 / part;
        } else
            Log.e("Error", "In User, method 'addWater(int height) part is 0 and it's trying to divide by 0'");

        TotalWaterPercentage += _percentagePortion;
        TotalWaterConsumptionFill += amount;

        TotalWaterConsumption += cupSize;
        TotalWaterConsumptionStack.push((double)cupSize);
        TotalWaterPercentageStack.push(_percentagePortion);
        PortionStack.push(part);

        boolean correctWaterAmount = true;
        if(amount > 200)
        {
            return false;
        }
        return correctWaterAmount;
    }

    public boolean removeWater(int height)
    {
        double part = PortionStack.isEmpty() ? 1 : PortionStack.pop();
        double amount = 0;
        double previousCupSize =
                TotalWaterConsumptionStack.isEmpty() ? 0 : TotalWaterConsumptionStack.pop();

        if (part > 0)
        {
            amount = height / part;
            _percentagePortion = 100 / part;
        } else
            Log.e("Error", "In User, method 'addWater(int height) part is 0 and it's trying to divide by 0'");

        if ((TotalWaterConsumptionFill) > 0)
        {
            TotalWaterPercentage -= TotalWaterPercentageStack.isEmpty() ? 0 : TotalWaterPercentageStack.pop();
            TotalWaterConsumptionFill -= amount;
            TotalWaterConsumption -= previousCupSize;
            return true;
        }
        return false;
    }

    public int getCupSize()
    {
        return cupSize;
    }

    public void setCupSize(int cupSize)
    {
        this.cupSize = cupSize;
    }

    public int getReminderTime()
    {
        return calculateReminderTime();
    }

    public int calculateReminderTime()
    {
        return reminderTime;
    }

    public void setReminderTime(int reminderTime)
    {
        this.reminderTime = reminderTime;
    }

    public int getTotalWaterNeeded()
    {
        return totalWaterNeeded;
    }

    public void setTotalWaterNeeded(int totalWaterNeeded)
    {
        this.totalWaterNeeded = totalWaterNeeded;
    }

    public MeasurementUnits getMeasurementUnit()
    {
        return measurementUnit;
    }

    public void setMeasurementUnit(MeasurementUnits measurementUnit)
    {
        this.measurementUnit = measurementUnit;
    }

    public void setStartTime(int hour, int minutes){
        this._startTimeMeridiem = hour < 12 ? "AM" : "PM";
        this._startTimeHour = hour > 12 ? hour - 12 : hour;
        this._startTimeMin = minutes;
    }

    public int getStartTimeHour()
    {
        return _startTimeHour;
    }

    public int getStartTimeMin()
    {
        return _startTimeMin;
    }

    public String getStartTimeMinString()
    {
        return minsToString(this._startTimeMin);
    }

    public String minsToString(int minutes)
    {
        // If the number of digits in the minutes is 1 then add the leading 0
        if(Math.log10(minutes) < 2){
            StringBuilder sb = new StringBuilder()
                    .append("0")
                    .append(minutes);
            return sb.toString();
        }
        return minutes + "";
    }

    public String getStartTimeMeridiem()
    {
        return _startTimeMeridiem;
    }

    public int getEndTimeHour()
    {
        return _endTimeHour;
    }

    public int getEndTimeMin()
    {
        return _endTimeMin;
    }

    public String getEndTimeMinString()
    {
        return minsToString(this._endTimeMin);
    }


    public String getEndTimeMeridiem()
    {
        return _endTimeMeridiem;
    }
}
