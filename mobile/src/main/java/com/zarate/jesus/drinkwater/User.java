package com.zarate.jesus.drinkwater;

/**
 * Created by Jesus Zarate on 7/25/15.
 */
public class User
{
    private String _name;
    private double _weight;
    private String _height;
    private ActivityLevel _activityLevel = ActivityLevel.Moderate;

    private enum ActivityLevel{
        Low,
        Moderate,
        High
    }

    public ActivityLevel get_activityLevel()
    {
        return _activityLevel;
    }

    public void set_activityLevel(ActivityLevel _activityLevel)
    {
        this._activityLevel = _activityLevel;
    }

    public String get_name()
    {
        return _name;
    }

    public void set_name(String _name)
    {
        this._name = _name;
    }

    public double get_weight()
    {
        return _weight;
    }

    public void set_weight(double _weight)
    {
        this._weight = _weight;
    }

    public String get_height()
    {
        return _height;
    }

    public void set_height(String _height)
    {
        this._height = _height;
    }
}
