package com.monitor.luckydrawmonitor.servcice;

import com.monitor.luckydrawmonitor.entity.AllInfo;
import com.monitor.luckydrawmonitor.entity.Position;
import com.monitor.luckydrawmonitor.entity.Record;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class RedisService {
    @Resource
    StringRedisTemplate stringRedis;

    private Long lastUv = 0L;

    public void recordPositionInfo(Position position) {
        stringRedis.opsForHyperLogLog().add(Record.date + ":ip", position.getIp());
        stringRedis.opsForZSet().incrementScore(Record.date + ":city", position.getCity(), 1);
        stringRedis.opsForZSet().incrementScore(Record.date + ":province", position.getProvince(), 1);
        stringRedis.opsForZSet().incrementScore(Record.date + ":country", position.getCountry(), 1);
        Point point = new Point(position.getGnote()[0], position.getGnote()[1]);
        RedisGeoCommands.GeoLocation<String> geoLocation = new RedisGeoCommands.GeoLocation<>(position.getIp(), point);
        stringRedis.opsForGeo().add(Record.date + "gnote", geoLocation);
    }


    public void recordInterfaceInfo(String path) {
        stringRedis.opsForZSet().incrementScore(Record.date + ":interface", path, 1);
    }


    public void recordAccessTrends() {
        lastUv = stringRedis.opsForHyperLogLog().size(Record.date + ":ip") - lastUv;
        stringRedis.opsForZSet().add(Record.date + ":accessTrends", String.valueOf(lastUv), System.currentTimeMillis());
    }

    public void recordAllInfo() {
        AllInfo allInfo = new AllInfo();
        Map<String, Double> interfaceRank = getInterfaceRank();
        Set<String> cityRank = getCityRank();
        Set<String> provinceRank = getProvinceRank();
        Set<String> countryRank = getCountryRank();
        Map<Double, String> accessTrends = new LinkedHashMap<>();
        Set<ZSetOperations.TypedTuple<String>> accessTrends1 = stringRedis.opsForZSet().reverseRangeWithScores(Record.date + ":accessTrends", 0, -1);
        Map<String, Long> map = new LinkedHashMap<>();
        if (!accessTrends1.isEmpty()) {
            accessTrends1.forEach(s -> accessTrends.put(s.getScore(), s.getValue()));
        }
        allInfo.setDate(Record.date);
        allInfo.setInterfaceRank(interfaceRank);
        Record.browser.forEach((k, v) -> {
            map.put(k, v.longValue());
        });
        allInfo.setBrowser(map);
        allInfo.setCityRank(cityRank);
        allInfo.setProvinceRank(provinceRank);
        allInfo.setAccessTrends(accessTrends);
        allInfo.setCountryRank(countryRank);
        map.clear();
        Record.mediaTypeNumber.forEach((k, v) -> {
            map.put(k, v.longValue());
        });
        allInfo.setMediaType(map);
        allInfo.setNewUserNumber(Record.newUserNumber.longValue());
        map.clear();
        Record.status.forEach((k, v) -> {
            map.put(k, v.longValue());
        });
        allInfo.setStatus(map);
        stringRedis.opsForSet().add(Record.date + ":allInfo", allInfo.toString());
    }

    public Map<String, Double> getInterfaceRank() {
        Set<ZSetOperations.TypedTuple<String>> interfaceRank = stringRedis.opsForZSet().reverseRangeWithScores(Record.date + ":interface", 0, 6);
        interfaceRank.forEach(s -> Record.interfaceRank.put(s.getValue(), s.getScore()));
        return Record.interfaceRank;
    }

    public Long getUV() {
        return stringRedis.opsForHyperLogLog().size(Record.date + ":ip");
    }

    public List<Point> getGnote() {
        List<Point> points = new ArrayList<>();
        Circle circle = new Circle(new Point(80, 40), new Distance(6371, RedisGeoCommands.DistanceUnit.KILOMETERS));
        GeoResults<RedisGeoCommands.GeoLocation<String>> geoResults = stringRedis.opsForGeo().radius(Record.date + "gnote", circle, RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeCoordinates());
        geoResults.forEach(geoLocation -> {
            points.add(geoLocation.getContent().getPoint());
        });
        return points;
    }

    public Set<String> getCityRank() {
        return stringRedis.opsForZSet().reverseRange(Record.date + ":city", 0, 2);
    }

    public Set<String> getProvinceRank() {
        return stringRedis.opsForZSet().reverseRange(Record.date + ":province", 0, 2);
    }

    public Set<String> getCountryRank() {
        return stringRedis.opsForZSet().reverseRange(Record.date + ":country", 0, 2);
    }

    public Map<Double, String> getAccessTrends() {
        Set<ZSetOperations.TypedTuple<String>> accessTrends1 = stringRedis.opsForZSet().reverseRangeWithScores(Record.date + ":accessTrends", 0, -1);
        if (!accessTrends1.isEmpty()) {
            accessTrends1.forEach(s -> Record.accessTrends.put(s.getScore(), s.getValue()));
        }
        return Record.accessTrends;
    }


}
