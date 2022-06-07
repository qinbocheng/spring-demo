package com.example.utils;

import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Auther: mayanze
 * @Date: 2018/9/21 18:11
 * @Description:
 */
public class BaiDuMapUtil {
    public static String MAP_AK = "Puzsuhx0wmeNTQFoHgfDEf9U";

    public static String MAP_URL = "http://api.map.baidu.com/geocoder/v2/?output=json&ak=" + MAP_AK;

    /**
     * 将经纬度获取解析成详细地址
     *
     * @param lng
     *            经度
     * @param lat
     *            纬度
     * @return
     */
    public static String getAddress(double lng, double lat) {
        String address = "";
        String location = lat + "," + lng;
        BufferedReader in = null;
        URL url = null;
        URLConnection connection = null;
        try {
            url = new URL(MAP_URL + "&location=" + location);
            connection = url.openConnection();
            connection.setDoOutput(true);
            in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            String line;
            StringBuilder text = new StringBuilder("");
            while ((line = in.readLine()) != null) {
                text.append(line.trim());
            }
            JSONObject result = JSONObject.parseObject(text.toString());
            if (result != null && result.getIntValue("status") == 0) {
                address = result.getJSONObject("result").getString("formatted_address");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(address);
        return address != null & !address.equals("") ? address : "null";
    }

    /**
     * 将地址解析成经纬度
     *
     * @param address
     *            地址，例：浙江省杭州市西湖区
     * @return 返回经纬度数据。例：{"lng":120.08899292561351,"lat":30.207036169515438}
     */
    public static JSONObject getPosition(String address) {
        BufferedReader in = null;
        URL url = null;
        URLConnection connection = null;
        try {
            url = new URL(MAP_URL + "&address=" + address);
            connection = url.openConnection();
            connection.setDoOutput(true);
            in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            String line;
            StringBuilder text = new StringBuilder("");
            while ((line = in.readLine()) != null) {
                text.append(line.trim());
            }
            JSONObject result = JSONObject.parseObject(text.toString());
            if (result != null && result.getIntValue("status") == 0) {
                return result.getJSONObject("result").getJSONObject("location");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
//        System.out.println(getAddress(114.07065439904879, 22.519647444317396));
        System.out.println(getAddress(121.6618278,31.8321278));
        System.out.println(getPosition("浙江省杭州市西湖区"));
    }
}
