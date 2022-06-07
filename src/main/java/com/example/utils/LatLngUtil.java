package com.example.utils;

/**
 * Author: qinbocheng
 * Date: 2022/5/3 21:44
 * Description:
 * History:
 * <author>    <time>          <version> <desc>
 * qinbocheng  2022/5/3 21:44 版本号    描述
 */

import lombok.extern.slf4j.Slf4j;


/*************************************
 *Class Name: LatLngUtil
 *Description: <度分秒，度分，与经纬度互转工具类>
 *@author: Seminar
 *@create: 2022/05/01
 *@since 1.0.0
 *************************************/
@Slf4j
public class LatLngUtil {

    /**
     * 将经纬度转换为度分秒格式
     *
     * @param du 116.418847        40.0897315
     * @return 116°25'7.85"       40°5'23.03"
     */
    public static String latLng2Dfm(double du) {
        int du1 = (int) du;
        double tp = (du - du1) * 60;
        int fen = (int) tp;
        String miao = String.format("%.2f", Math.abs(((tp - fen) * 60)));
        return du1 + "°" + Math.abs(fen) + "'" + miao + "\"";
    }

    /**
     * 度分秒转经纬度
     *
     * @param dms 116°25'7.85"
     * @return 116.418847
     */
    public static double dfm2LatLng(String dms) {
        if (dms == null) return 0;
        try {
            dms = dms.replace(" ", "");
            String[] str2 = dms.split("°");
            if (str2.length < 2) return 0;
            int d = Integer.parseInt(str2[0]);
            String[] str3 = str2[1].split("\'");
            if (str3.length < 2) return 0;
            int f = Integer.parseInt(str3[0]);
            String str4 = str3[1].substring(0, str3[1].length() - 1);
            double m = Double.parseDouble(str4);
            double fen = f + (m / 60);
            double du = (fen / 60) + Math.abs(d);
            if (d < 0) du = -du;
            return Double.parseDouble(String.format("%.7f", du));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 将经纬度转换为度分格式
     *
     * @param du 116.418847      40.0897315
     * @return 116°25'          40°5'
     */
    private static Object latLng2Df(double du) {
        int du1 = (int) du;
        double tp = (du - du1) * 60;
        int fen = (int) tp;
        return du1 + "°" + Math.abs(fen) + "'";
    }

    /**
     * 度分转经纬度
     * 全球经纬度的取值范围为：纬度-90~90，经度-180~180
     * 度分转换： 将度分单位数据转换为度单位数据，公式：度=度+分/60
     * 例如： 经度 = 116°20.12'，纬度 = 39°12.34'
     * 经度 = 116 + 20.12 / 60 = 116.33533°
     * 纬度 = 39 + 12.34 / 60 = 39.20567°
     *
     * @param dm 4005.38389 ddmm.mmmmm
     * @return 40.0897315
     * 11616.02846 dddmm.mmmmm
     * 116.267141
     */
    public static double df2LatLng(String dm) {
        if (dm == null) {
            return 0;
        }
        try {
            dm = dm.replace(" ", "");
            int d = parseInteger(dm.substring(0, dm.lastIndexOf(".") - 2));
            double fen = Double.parseDouble(dm.substring(2));

            double lat = (fen / 60) + Math.abs(d);
            if (lat < 0) {
                lat = -lat;
            }
            return Double.parseDouble(String.format("%.7f", lat));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Integer parseInteger(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        return Integer.valueOf(str);
    }

    public static void main(String[] args) {
        double dfLng = 11616.02846; // 经度  dddmm.mmmmm
        double dfLat = 4005.38389; // 纬度 ddmm.mmmm
        double lng = 116.1602846;
        double lat = 40.0897315;

        log.info("  度分转经纬度：{} {}", df2LatLng(String.valueOf(dfLng)), df2LatLng(String.valueOf(dfLat)));
        log.info("  经纬度转度分：{} {}", latLng2Df(lng), latLng2Df(lat));

//        String dfmLng = "116°25'7.85"; // 经度  ddd°mm'm.mm"
        String dfmLng = "121° 39' 42.58";
//        String dfmLat = "40°5'23.03"; // 纬度 dd°mm'm.mm"
        String dfmLat = "31° 49' 55.66";
        log.info("度分秒转经纬度：{} {}", dfm2LatLng(dfmLng), dfm2LatLng(dfmLat));
        log.info("经纬度转度分秒：{} {}", latLng2Dfm(lng), latLng2Dfm(lat));
    }
}

