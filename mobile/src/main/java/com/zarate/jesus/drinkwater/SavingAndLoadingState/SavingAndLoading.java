package com.zarate.jesus.drinkwater.SavingAndLoadingState;

import com.google.gson.reflect.TypeToken;
import com.zarate.jesus.drinkwater.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;

import com.google.gson.Gson;


/**
 * Created by jesuszarate on 8/7/15.
 */
public class SavingAndLoading
{
    public static void SaveState(File filesDir)
    {
        Gson _gson = new Gson();

        User user = User.getInstance();

        String jsonUser = _gson.toJson(user);

        try {
            File file = new File(filesDir, "user-profile.txt");
            FileWriter textWriter;
            textWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(textWriter);

            // Write the paint points in json format.
            bufferedWriter.write(jsonUser);
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void LoadState(File filesDir)
    {
        Gson _gson = new Gson();
        try {
            File file = new File(filesDir, "user-profile.txt");
            FileReader textReader = new FileReader(file);
            BufferedReader bufferedTextReader = new BufferedReader(textReader);
            String jsonUser;
            jsonUser = bufferedTextReader.readLine();

            Type userType = new TypeToken<User>(){}.getType();
            User user = _gson.fromJson(jsonUser, userType);

            User.setInstance(user);

            bufferedTextReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
