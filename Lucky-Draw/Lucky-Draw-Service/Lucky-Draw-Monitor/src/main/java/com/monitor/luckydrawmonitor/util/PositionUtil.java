package com.monitor.luckydrawmonitor.util;

import com.monitor.luckydrawmonitor.entity.Position;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;

public class PositionUtil {
    private static DatabaseReader reader ;

    static {
        try {
            InputStream inputStream= new ClassPathResource("/GeoLite2-City.mmdb").getInputStream();
            reader=new DatabaseReader.Builder(inputStream).build();
            System.out.println("---------------Geo加载成功--------------");
        } catch (IOException e) {
            System.out.println("---------------Geo加载失败---------------");
        }
    }
    public static Position getPositionInfo(String ip)
    {
        if("127.0.0.1".equals(ip)||
                "localhost".equals(ip)||
                "0:0:0:0:0:0:0:1".equals(ip))
        {
            return new Position();
        }
        try {
            Position position=new Position();
            position.setIp(ip);
            position.setCountry(getCountry(ip));
            position.setProvince(getProvince(ip));
            position.setCity(getCity(ip));
            Double[] location=getLocation(ip);
            position.setGnote(location);
            return position;
        }catch (Exception e)
        {
            throw new RuntimeException("ip地址解析失败: "+ip);
        }
    }


    public static String getCity(String ip) throws IOException, GeoIp2Exception {
        return reader.city(InetAddress.getByName(ip)).getCity().getNames().get("zh-CN");
    }
    public static String getCountry(String ip) throws IOException, GeoIp2Exception {
        return reader.city(InetAddress.getByName(ip)).getCountry().getNames().get("zh-CN");
    }
    public static String getProvince(String ip) throws IOException, GeoIp2Exception {
        return reader.city(InetAddress.getByName(ip)).getMostSpecificSubdivision().getNames().get("zh-CN");
    }

    public static Double[] getLocation(String ip) throws IOException, GeoIp2Exception {
        Double[] location=new Double[2];
        location[0]=reader.city(InetAddress.getByName(ip)).getLocation().getLongitude();
        location[1]=reader.city(InetAddress.getByName(ip)).getLocation().getLatitude();
        return location;
    }
}
