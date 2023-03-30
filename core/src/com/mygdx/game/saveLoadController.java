package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import jdk.nashorn.internal.parser.JSONParser;
//import org.json.simple.JSONArray;

//import org.json.simple.JSONObject;

//import org.json.simple.parser.*;

import java.io.*;
import java.nio.file.Files;

/**
 *  This class is used for switch the data to json document. Not Complete yet!
 */

public class saveLoadController {
    public static void saveData(Score s){
        Json json = new Json();
        json.toJson(s);
        String path = Gdx.files.getLocalStoragePath() + "database\\data.json";
        System.out.println(path);
        try{
            File file = new File(path);
            if(file.exists())
                file.delete();
            else
                System.out.println("-1");
            file = new File(path);
            if(file.createNewFile())
                System.out.println("");
            else
                System.out.println("出错了，该文件已经存在。");
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
            out.write(json.toString());
            //System.out.println(json.);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void loadData(){
        Json json = new Json();
        String output = "";

        try {
            FileReader reader = new FileReader("/Users/User/Desktop/course.json");
            int i;
            while ((i=reader.read()) != -1){
                output += (char)i;
            }

            //String data = reader.toString()
            //System.out.println(json.prettyPrint(text));
            //Object person2 = json.fromJson(Object.class, text);
            //Object obj = parser.parse(new FileReader("c:\\file.json"));
            reader.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }

        System.out.println(output);


    }
}
