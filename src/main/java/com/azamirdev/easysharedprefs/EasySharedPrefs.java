package com.azamirdev.easysharedprefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class EasySharedPrefs {
    private final  String LOG_TAG = "easySharedPrefs",
            defaultStringValue="no_shared_prefs_value",
            defaultSplitter="**||easy_shared_prefs_splitter||***";
    private final float defaultFloatValue=0f;
    private final int defaultIntValue=0;
    private final Boolean defaultBooleanValue=false;
    private final SharedPreferences sharedPref;
    private final SharedPreferences.Editor editor;

    public EasySharedPrefs(Context context,String fileName){
        sharedPref = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        editor = sharedPref.edit();
    }


    public Boolean contains(String paramName){
        return sharedPref.contains(paramName);
    }
    //Get Shared
    public String getString(String paramName){return sharedPref.getString(paramName, defaultStringValue);}
    public Boolean getBoolean(String paramName){return sharedPref.getBoolean(paramName, defaultBooleanValue);}
    public int getInt(String paramName){return sharedPref.getInt(paramName, defaultIntValue);}
    public float getFloat(String paramName){return sharedPref.getFloat(paramName, defaultFloatValue);}
    //Add Shared
    public void addString(String paramName,String param){
        editor.putString(paramName, param);
        editor.apply();
    }
    public void addBoolean(String paramName,Boolean param){
        editor.putBoolean(paramName, param);
        editor.apply();
    }
    public void addInt(String paramName, int param){
        editor.putInt(paramName, param);
        editor.apply();
    }
    public void addFloat(String paramName, float param){
        editor.putFloat(paramName, param);
        editor.apply();
    }
    //GetArrayList
    public ArrayList<String> getStringArray(String paramName){
        ArrayList<String> out = new ArrayList<>();
        boolean hasShared = true;
        for(int i =0; hasShared;i++){
            if(contains(paramName+i)){
                 out.add(getString(paramName+i));
                Log.d(LOG_TAG," getStringArray for if add:"+getString(paramName+i));
            }else {hasShared=false;
                Log.d(LOG_TAG," getStringArray for else end arraySize:"+out.size());
            }
        }
        return out;}
    public ArrayList<Integer> getIntArray(String paramName){
        ArrayList<Integer> out = new ArrayList<>();
        boolean hasShared = true;
        for(int i =0; hasShared;i++){
            if(contains(paramName+i)){
                out.add(getInt(paramName+i));
                Log.d(LOG_TAG," getIntArray for if add:"+getInt(paramName+i));
            }else {hasShared=false;
                Log.d(LOG_TAG," getIntArray for else end arraySize:"+out.size());
            }
        }
        return out;}
    public ArrayList<Float> getFloatArray(String paramName){
        ArrayList<Float> out = new ArrayList<>();
        boolean hasShared = true;
        for(int i =0; hasShared;i++){
            if(contains(paramName+i)){
                out.add(getFloat(paramName+i));
                Log.d(LOG_TAG," getFloatArray for if add:"+getFloat(paramName+i));
            }else {hasShared=false;
                Log.d(LOG_TAG," getFloatArray for else end arraySize:"+out.size());
            }
        }
        return out;}
    //AddArrayList
    public void addStringArray(String paramName,ArrayList<String> addingArray){
        removeArray(paramName);
        for(int i =0; i<addingArray.size();i++){
            Log.d(LOG_TAG," AddStringArray for paramName/paramDesc:"+paramName+i+"/"+addingArray.get(i));
            editor.putString(paramName+i, addingArray.get(i));
            editor.apply();
        }        Log.d(LOG_TAG," AddStringArray for end totalSize:"+addingArray.size());
    }
    public void addIntArray(String paramName,ArrayList<Integer> addingArray){
        removeArray(paramName);
        for(int i =0; i<addingArray.size();i++){
            Log.d(LOG_TAG," AddIntArray for paramName/paramDesc:"+paramName+i+"/"+addingArray.get(i));
            editor.putInt(paramName+i, addingArray.get(i));
            editor.apply();
        }        Log.d(LOG_TAG," AddIntArray for end totalSize:"+addingArray.size());
    }
    public void addFloatArray(String paramName,ArrayList<Float> addingArray){
        removeArray(paramName);
        for(int i =0; i<addingArray.size();i++){
            Log.d(LOG_TAG," AddIntArray for paramName/paramDesc:"+paramName+i+"/"+addingArray.get(i));
            editor.putFloat(paramName+i, addingArray.get(i));
            editor.apply();
        }        Log.d(LOG_TAG," AddIntArray for end totalSize:"+addingArray.size());
    }
    //ClearArrayList
    public void removeArray(String paramName){
        boolean hasShared = true;
        for(int i =0; hasShared;i++){
            if(contains(paramName+i)){
                editor.remove(paramName+i);
                editor.commit();
                Log.d(LOG_TAG," RemoveArray for if clear:"+paramName+i);
            }else {hasShared=false;
                Log.d(LOG_TAG," RemoveArray for else end");
            }
        }
    }
    public void remove(String paramName){
                editor.remove(paramName);
                editor.commit();
                Log.d(LOG_TAG," Remove:"+paramName);
    }
    public void removeAllData(){
        Map<String, ?> allPrefs = sharedPref.getAll(); //your sharedPreference
        Set<String> set = allPrefs.keySet();
        for(String s : set){
            remove(s);
        }
    }

    //
    public void checkAllPrefs(){
        Map<String, ?> allPrefs = sharedPref.getAll(); //your sharedPreference
        Set<String> set = allPrefs.keySet();
        for(String s : set){
            Log.d(LOG_TAG, s + "<" + allPrefs.get(s).getClass().getSimpleName() +"> =  "
                    + allPrefs.get(s).toString());
        }
    }
}
