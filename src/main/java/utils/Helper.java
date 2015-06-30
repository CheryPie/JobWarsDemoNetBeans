/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author Presko
 */
public class Helper {
    public static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    
    public static String toJson(Object object){
        return GSON.toJson(object);
    }
}
