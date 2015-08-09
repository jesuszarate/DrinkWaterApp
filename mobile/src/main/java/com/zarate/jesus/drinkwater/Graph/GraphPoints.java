package com.zarate.jesus.drinkwater.Graph;

import java.util.HashMap;

/**
 * Created by Jesus Zarate on 8/8/15.
 */
public class GraphPoints
{
    private HashMap<Integer, Point> xPoints = new HashMap<>();
    private HashMap<Integer, Point> yPoints = new HashMap<>();

    private static GraphPoints _instance;

    private GraphPoints()
    {
    }

    public static GraphPoints getInstance()
    {
        if(_instance == null)
        {
            _instance = new GraphPoints();
        }
        return _instance;
    }

    public HashMap<Integer, Point> getxPoints()
    {
        return xPoints;
    }


    public HashMap<Integer, Point> getyPoints()
    {
        return yPoints;
    }

}
