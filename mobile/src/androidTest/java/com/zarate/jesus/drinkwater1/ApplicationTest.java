package com.zarate.jesus.drinkwater1;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.MediumTest;

import com.zarate.jesus.drinkwater.WaterConsumptionHistory;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application>
{
    public ApplicationTest()
    {
        super(Application.class);
    }

    @MediumTest
    public void testWaterConsumptionHistoryAddDay() {
        WaterConsumptionHistory.getInstance().addDay(20);
    }
}