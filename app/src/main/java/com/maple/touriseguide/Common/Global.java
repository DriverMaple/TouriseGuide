package com.maple.touriseguide.Common;

import com.maple.touriseguide.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rrr on 2017/10/27.
 */

public class Global {
    public static List<Map<String, Object>> dynamic = new ArrayList<>();
    public static List<Map<String, Object>> sug = new ArrayList<>();
    public static String IP = "http://192.168.15.174:8001";
    //public static String MyIP = "http://60.177.49.211:8080";
    public static String MyIP = "http://47.100.97.74:8080/guideserver";

    public static void initdata(){
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("head_pic", R.drawable.tx);
        map1.put("share_name", "萧枫");
        map1.put("share_time", "2017-12-20");
        map1.put("share_content", "第一条！沙发！");
        map1.put("share_pic", R.drawable.fir);
        dynamic.add(map1);

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("head_pic", R.drawable.tx);
        map2.put("share_name", "萧枫");
        map2.put("share_time", "2017-12-21");
        map2.put("share_content", "第一条！沙发！");
        map2.put("share_pic", R.drawable.sec);
        dynamic.add(map2);

        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("head_pic", R.drawable.tx);
        map3.put("share_name", "萧枫");
        map3.put("share_time", "2017-12-22");
        map3.put("share_content", "第一条！沙发！");
        map3.put("share_pic", R.drawable.tx);
        dynamic.add(map3);

        Map<String, Object> map4 = new HashMap<String, Object>();
        map4.put("head_pic", R.drawable.tx);
        map4.put("share_name", "萧枫");
        map4.put("share_time", "2017-12-23");
        map4.put("share_content", "第一条！沙发！");
        map4.put("share_pic", R.drawable.thr);
        dynamic.add(map4);

        Map<String, Object> map5 = new HashMap<String, Object>();
        map5.put("head_pic", R.drawable.tx);
        map5.put("share_name", "萧枫");
        map5.put("share_time", "2017-12-24");
        map5.put("share_content", "第一条！沙发！");
        map5.put("share_pic", R.drawable.forth);
        dynamic.add(map5);

        Map<String, Object> map6 = new HashMap<String, Object>();
        map6.put("head_pic", R.drawable.tx);
        map6.put("share_name", "萧枫");
        map6.put("share_time", "2017-12-25");
        map6.put("share_content", "第一条！沙发！");
        map6.put("share_pic", null);
        dynamic.add(map6);

        Map<String, Object> map7 = new HashMap<String, Object>();
        map7.put("head_pic", R.drawable.tx);
        map7.put("share_name", "萧枫");
        map7.put("share_time", "2017-12-26");
        map7.put("share_content", "第一条！沙发！");
        map7.put("share_pic", null);
        dynamic.add(map7);

        Map<String, Object> map8 = new HashMap<String, Object>();
        map8.put("head_pic", R.drawable.tx);
        map8.put("share_name", "萧枫");
        map8.put("share_time", "2017-12-27");
        map8.put("share_content", "第一条！沙发！");
        map8.put("share_pic", R.drawable.fif);
        dynamic.add(map8);

        Map<String, Object> map9 = new HashMap<String, Object>();
        map9.put("head_pic", R.drawable.tx);
        map9.put("share_name", "萧枫");
        map9.put("share_time", "2017-12-28");
        map9.put("share_content", "第一条！沙发！");
        map9.put("share_pic", null);
        dynamic.add(map9);
    };

    public static void adddata(String user_name, String share_time,String share_content){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("head_pic", R.drawable.tx);
        map.put("share_name", user_name);
        map.put("share_time", share_time);
        map.put("share_content", share_content);
        map.put("share_pic", null);
        dynamic.add(0,map);
    }

    public static void initsug(){
        Map<String, Object> map0 = new HashMap<String, Object>();
        map0.put("sug_id", 0);
        map0.put("sug_pic", R.drawable.pic1);
        map0.put("sug_title", "西湖");
        map0.put("sug_content", "美丽的西湖");
        sug.add(map0);

        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("sug_id", 0);
        map1.put("sug_pic", R.drawable.pic2);
        map1.put("sug_title", "西湖");
        map1.put("sug_content", "美丽的西湖");
        sug.add(map1);

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("sug_id", 0);
        map2.put("sug_pic", R.drawable.pic3);
        map2.put("sug_title", "西湖");
        map2.put("sug_content", "美丽的西湖");
        sug.add(map2);

        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("sug_id", 0);
        map3.put("sug_pic", R.drawable.pic4);
        map3.put("sug_title", "西湖");
        map3.put("sug_content", "美丽的西湖");
        sug.add(map3);

        Map<String, Object> map4 = new HashMap<String, Object>();
        map4.put("sug_id", 0);
        map4.put("sug_pic", R.drawable.pic5);
        map4.put("sug_title", "西湖");
        map4.put("sug_content", "美丽的西湖");
        sug.add(map4);

        Map<String, Object> map5 = new HashMap<String, Object>();
        map5.put("sug_id", 0);
        map5.put("sug_pic", R.drawable.pic6);
        map5.put("sug_title", "西湖");
        map5.put("sug_content", "美丽的西湖");
        sug.add(map5);

        Map<String, Object> map6 = new HashMap<String, Object>();
        map6.put("sug_id", 0);
        map6.put("sug_pic", R.drawable.pic7);
        map6.put("sug_title", "西湖");
        map6.put("sug_content", "美丽的西湖");
        sug.add(map6);

        Map<String, Object> map7 = new HashMap<String, Object>();
        map7.put("sug_id", 0);
        map7.put("sug_pic", R.drawable.pic8);
        map7.put("sug_title", "西湖");
        map7.put("sug_content", "美丽的西湖");
        sug.add(map7);

        Map<String, Object> map8 = new HashMap<String, Object>();
        map8.put("sug_id", 0);
        map8.put("sug_pic", R.drawable.pic9);
        map8.put("sug_title", "西湖");
        map8.put("sug_content", "美丽的西湖");
        sug.add(map8);

        Map<String, Object> map9 = new HashMap<String, Object>();
        map9.put("sug_id", 0);
        map9.put("sug_pic", R.drawable.pic10);
        map9.put("sug_title", "西湖");
        map9.put("sug_content", "美丽的西湖");
        sug.add(map9);

        Map<String, Object> map10 = new HashMap<String, Object>();
        map10.put("sug_id", 0);
        map10.put("sug_pic", R.drawable.pic11);
        map10.put("sug_title", "西湖");
        map10.put("sug_content", "美丽的西湖");
        sug.add(map10);

        Map<String, Object> map11 = new HashMap<String, Object>();
        map11.put("sug_id", 0);
        map11.put("sug_pic", R.drawable.pic12);
        map11.put("sug_title", "西湖");
        map11.put("sug_content", "美丽的西湖");
        sug.add(map11);

        Map<String, Object> map12 = new HashMap<String, Object>();
        map12.put("sug_id", 0);
        map12.put("sug_pic", R.drawable.pic13);
        map12.put("sug_title", "西湖");
        map12.put("sug_content", "美丽的西湖");
        sug.add(map12);

        Map<String, Object> map13 = new HashMap<String, Object>();
        map13.put("sug_id", 0);
        map13.put("sug_pic", R.drawable.pic14);
        map13.put("sug_title", "西湖");
        map13.put("sug_content", "美丽的西湖");
        sug.add(map13);

        Map<String, Object> map14 = new HashMap<String, Object>();
        map14.put("sug_id", 0);
        map14.put("sug_pic", R.drawable.pic15);
        map14.put("sug_title", "西湖");
        map14.put("sug_content", "美丽的西湖");
        sug.add(map14);

        Map<String, Object> map15 = new HashMap<String, Object>();
        map15.put("sug_id", 0);
        map15.put("sug_pic", R.drawable.pic16);
        map15.put("sug_title", "西湖");
        map15.put("sug_content", "美丽的西湖");
        sug.add(map15);

        Map<String, Object> map16 = new HashMap<String, Object>();
        map16.put("sug_id", 0);
        map16.put("sug_pic", R.drawable.pic17);
        map16.put("sug_title", "西湖");
        map16.put("sug_content", "美丽的西湖");
        sug.add(map16);

        Map<String, Object> map17 = new HashMap<String, Object>();
        map17.put("sug_id", 0);
        map17.put("sug_pic", R.drawable.pic18);
        map17.put("sug_title", "西湖");
        map17.put("sug_content", "美丽的西湖");
        sug.add(map17);

        Map<String, Object> map18 = new HashMap<String, Object>();
        map18.put("sug_id", 0);
        map18.put("sug_pic", R.drawable.pic19);
        map18.put("sug_title", "西湖");
        map18.put("sug_content", "美丽的西湖");
        sug.add(map18);

        Map<String, Object> map19 = new HashMap<String, Object>();
        map19.put("sug_id", 0);
        map19.put("sug_pic", R.drawable.pic20);
        map19.put("sug_title", "西湖");
        map19.put("sug_content", "美丽的西湖");
        sug.add(map19);
    }
}