package com.example.luckyapp.mqproducer;




import com.example.luckydrawconfig.util.AccessGithubToken;

public class Test {



    public static void main(String[] args) {

        AccessGithubToken.getUserInfo("c32d2d8c7e14c79297de");
 //         System.out.println(MD5.create().digestHex("1234"));
//        String url=  "/v1/register/token";
//        String regx="/login|/register";
//        Pattern pattern2= Pattern.compile(regx);
//        Matcher matcher= pattern2.matcher(url);
//        if(matcher.find())
//        {
//            System.out.println(matcher.group(0));
//        }
////
//        ActivityAddCmd activityAddCmd1=new ActivityAddCmd();
//        activityAddCmd1.setActivityName("haha");
//        activityAddCmd1.setStartTime(LocalDateTime.now());
//        activityAddCmd1.setEndTime(LocalDateTime.now());
//        activityAddCmd1.setDescribe("hahaha");
//
//
//        ActivityAddCmd activityAddCmd2=new ActivityAddCmd();
//        activityAddCmd2.setActivityName("wwwww");
//        activityAddCmd2.setStartTime(LocalDateTime.now());
//        activityAddCmd2.setEndTime(LocalDateTime.now());
//        activityAddCmd2.setDescribe("wwwwww");
//
//
//
//        List<Object> list=List.of(activityAddCmd1,activityAddCmd2);
//        JSONArray jsonArray=new JSONArray(list);
//        String test1= JSONArray.toJSONString(jsonArray);
//        System.out.println(test1);
//        JSONArray jsonArray1=  JSONArray.parseArray(test1);
//        ActivityAddCmd jsonObject=jsonArray1.getObject(0,ActivityAddCmd.class);
//        System.out.println(jsonObject);

    }
}
