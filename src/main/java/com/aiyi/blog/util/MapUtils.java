package com.aiyi.blog.util;

public class MapUtils {
    //private static double EARTH_RADIUS = 6378.137;
    private static double EARTH_RADIUS = 6371.393;
    private static double rad(double d)
    {
        double d1 = d * Math.PI;
        return d1 / 180.0;
    }

    /**
     * 计算两个经纬度之间的距离
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    public static double GetDistance(double lat1, double lng1, double lat2, double lng2)
    {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
                Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 1000);
        if (s == 0){
            s = -1;
        }
        return s;
    }



    public static void main(String[] args) {
        System.out.println(MapUtils.GetDistance(39.65053092,118.18345060,37.74692905,115.68622865));
    }
}
