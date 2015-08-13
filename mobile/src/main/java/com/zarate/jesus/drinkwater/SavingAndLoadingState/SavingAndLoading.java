package com.zarate.jesus.drinkwater.SavingAndLoadingState;

import com.google.gson.reflect.TypeToken;
import com.zarate.jesus.drinkwater.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.zarate.jesus.drinkwater.WaterConsumptionHistory.WaterConsumptionHistory;


/**
 * Created by Jesus Zarate on 8/7/15.
 */
public class SavingAndLoading
{
    public static void SaveState(File filesDir)
    {
        Gson _gson = new Gson();

        User user = User.getInstance();
        WaterConsumptionHistory WCH = WaterConsumptionHistory.getInstance();

        String jsonUser = _gson.toJson(user);
        String jsonWCH = _gson.toJson(WCH);

        try
        {
            File file = new File(filesDir, "user-profile.txt");
            saveFile(file, jsonUser);

            file = new File(filesDir, "water-consumption-history.txt");
            saveFile(file, jsonWCH);

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void LoadState(File filesDir)
    {
        Gson _gson = new Gson();
        try
        {
            File file = new File(filesDir, "user-profile.txt");
            loadUserProfile(_gson, file);

            file = new File(filesDir, "water-consumption-history.txt");
            loadWCH(_gson, file);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void saveFile(File file, String jsonString) throws IOException
    {
        FileWriter textWriter;
        textWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(textWriter);

        bufferedWriter.write(jsonString);
        bufferedWriter.close();
    }

    private static void loadWCH(Gson gson, File file) throws IOException
    {
        FileReader textReader = new FileReader(file);
        BufferedReader bufferedTextReader = new BufferedReader(textReader);
        String jsonWCH;
        jsonWCH = bufferedTextReader.readLine();

        Type userType = new TypeToken<WaterConsumptionHistory>()
        {
        }.getType();
        WaterConsumptionHistory WCH = gson.fromJson(jsonWCH, userType);

        WaterConsumptionHistory.setInstance(WCH);

        bufferedTextReader.close();
    }

    private static void loadUserProfile(Gson gson, File file) throws IOException
    {
        FileReader textReader = new FileReader(file);
        BufferedReader bufferedTextReader = new BufferedReader(textReader);
        String jsonUser;
        jsonUser = bufferedTextReader.readLine();

        Type userType = new TypeToken<User>()
        {
        }.getType();
        User user = gson.fromJson(jsonUser, userType);

        User.setInstance(user);

        bufferedTextReader.close();
    }
}
