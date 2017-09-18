package com.ezgo.index;

import android.util.Log;

import com.ezgo.index.Common.Common;
import com.ezgo.index.MyAsyncTask.worksheetAsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Created by thunder on 2017/5/16.
 */

public class getWorksheet {

    private static String[] question= new String[100];
    private static String[] answer= new String[100];
    private static String[] description= new String[100];
    private static String[] option= new String[100];
    private static String[] record_question_id= new String[100];
    private static String[] option_question_id= new String[100];
    private static byte[] token= new byte[100];
    private static String user_id;
    private static int optionLength ;

    private static int optionCount = 1;
    public  static int[] optionArr = new int[15];

    public static void getJSON() {

        worksheetAsyncTask myAsyncTask = new worksheetAsyncTask(new worksheetAsyncTask.TaskListener() {
            @Override
            public void onFinished(String result) {
                try {
                    JSONObject object = new JSONObject(result);

                    JSONArray jsonArray = object.getJSONArray("record");

                    int j=0;

                    for (int i = 0 ; i<jsonArray.length() ; i++){
                        question[i] = jsonArray.getJSONObject(i).getString("question");
                        answer[i] = jsonArray.getJSONObject(i).getString("answer");
                        description[i] = jsonArray.getJSONObject(i).getString("description");
                        record_question_id[i] = jsonArray.getJSONObject(i).getString("question_id");
                        token[i] = Byte.valueOf(jsonArray.getJSONObject(i).getString("token"));
                            /*
                            if( i != 0){
                                if(question_id[i].equals(question_id[i-1]) ){
                                    optionCount += 1;
                                }else{
                                    optionArr[j] = optionCount;
                                    j+=1;
                                    optionCount =1;
                                }
                                if(i+1 == getWorksheetLength()){
                                    optionArr[j] = optionCount;
                                   // Log.v("Test2","H" + question_id[i]);
                                }
                            }
                            */
                    }
                    jsonArray = object.getJSONArray("1");

                    for (int i = 0 ; i<jsonArray.length() ; i++) {
                        option[i] = jsonArray.getJSONObject(i).getString("qOption");
                        option_question_id[i] = jsonArray.getJSONObject(i).getString("question_id");
                        // Log.v("option",""+option[i]);
                        // Log.v("oqID",""+option_question_id[i]);
                    }
                    optionLength = jsonArray.length();
                    Log.v("length",""+getOptionLength());
                } catch (Exception e) {
                    Log.v("ABC", Log.getStackTraceString(e));
                }
            }
        });
        myAsyncTask.execute(Common.url + getUser_id());
    }
    //-----------------使用者的題目清單maxIndex:15
    public static String getQuestion(int i){
        return question[i];
    }
    public static int getAnswer(int i){
        return Integer.valueOf(answer[i]);
    }
    public static String getDescription(int i){
        return description[i];
    }
    public static String getRecord_Question_id(int i){
        return record_question_id[i];
    }
    public static byte getToken(int i){
        return token[i];
    }
    //-----------------使用者的選項，索引的範圍不確定，
    public static String getOption(int i){
        return option[i];
    }
    public static String getOptionQuestion_id(int i){
        return option_question_id[i];
    }
    //------------------
    public static int getOptionArr(int i){
        return  optionArr[i] ;
    }
    public static int getOptionLength(){
        return optionLength;
    }
    public static int getUser_id(){
        return  Integer.valueOf(user_id) ;
    }  //user_id 為數字型態

    protected static void postUser_id(String user){
        user_id = user ;
    }


}
