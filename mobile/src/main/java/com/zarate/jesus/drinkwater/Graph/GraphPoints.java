package com.zarate.jesus.drinkwater.Graph;

import java.util.HashMap;

/**
 * Created by Jesus Zarate on 8/8/15.
 */
public class GraphPoints
{
    private HashMap<Integer, Point> xPoints = new HashMap<>();
    private HashMap<Integer, Point> yPoints = new HashMap<>();
    private HashMap<Integer, Float> y = new HashMap<>();

//    private static GraphPoints _instance;

    public GraphPoints()
    {
    }

//    public static GraphPoints getInstance()
//    {
//        if(_instance == null)
//        {
//            _instance = new GraphPoints();
//        }
//        return _instance;
//    }

    public HashMap<Integer, Float> getY()
    {
        return y;
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
