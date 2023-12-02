package com.monitor.luckydrawmonitor;

import com.monitor.luckydrawmonitor.entity.Record;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Arrays;


@SpringBootTest
class LuckyDrawMonitorApplicationTests {
    @Resource
    StringRedisTemplate stringRedis;
    @Test
    void test2()
    {
        System.out.println(System.currentTimeMillis());
    }

    @Test
    void contextLoads() {
        ArrayList<ArrayList<Double>> coordinates = new ArrayList<ArrayList<Double>>(Arrays.asList(
                new ArrayList<Double>(Arrays.asList(113.82033, 23.10469)),
                new ArrayList<Double>(Arrays.asList(121.47361, 30.27469)),
                new ArrayList<Double>(Arrays.asList(116.32392, 22.9878)),
                new ArrayList<Double>(Arrays.asList(114.36778, 23.12686)),
                new ArrayList<Double>(Arrays.asList(119.48147, 30.99669)),
                new ArrayList<Double>(Arrays.asList(114.99485, 36.89023)),
                new ArrayList<Double>(Arrays.asList(120.6623, 30.27523)),
                new ArrayList<Double>(Arrays.asList(117.87779, 30.9595)),
                new ArrayList<Double>(Arrays.asList(114.34845, 28.527)),
                new ArrayList<Double>(Arrays.asList(115.62595, 30.45446)),
                new ArrayList<Double>(Arrays.asList(120.40218, 34.96075)),
                new ArrayList<Double>(Arrays.asList(120.85229, 27.39702)),
                new ArrayList<Double>(Arrays.asList(117.63664, 28.0239)),
                new ArrayList<Double>(Arrays.asList(118.59459, 30.04038)),
                new ArrayList<Double>(Arrays.asList(114.82303, 35.7654)),
                new ArrayList<Double>(Arrays.asList(114.46982, 27.34782)),
                new ArrayList<Double>(Arrays.asList(116.54057, 38.55955)),
                new ArrayList<Double>(Arrays.asList(118.75974, 29.4238)),
                new ArrayList<Double>(Arrays.asList(115.8386, 30.6279)),
                new ArrayList<Double>(Arrays.asList(116.68079, 25.4977)),
                new ArrayList<Double>(Arrays.asList(118.78804, 34.5969)),
                new ArrayList<Double>(Arrays.asList(115.66807, 30.7444)),
                new ArrayList<Double>(Arrays.asList(120.66669, 32.5087)),
                new ArrayList<Double>(Arrays.asList(118.37452, 27.9969)),
                new ArrayList<Double>(Arrays.asList(117.03805, 30.5854)),
                new ArrayList<Double>(Arrays.asList(116.48503, 26.4597)),
                new ArrayList<Double>(Arrays.asList(114.52076, 28.6649)),
                new ArrayList<Double>(Arrays.asList(120.88869, 34.0003)),
                new ArrayList<Double>(Arrays.asList(116.4447, 30.565)),
                new ArrayList<Double>(Arrays.asList(114.58579, 29.479)),
                new ArrayList<Double>(Arrays.asList(120.75856, 35.9997)),
                new ArrayList<Double>(Arrays.asList(116.4478, 28.0744)),
                new ArrayList<Double>(Arrays.asList(118.9766, 30.697)),
                new ArrayList<Double>(Arrays.asList(116.3887, 30.723)),
                new ArrayList<Double>(Arrays.asList(115.8576, 30.55)),
                new ArrayList<Double>(Arrays.asList(120.6842, 30.4088)),
                new ArrayList<Double>(Arrays.asList(120.09733, 32.579)),
                new ArrayList<Double>(Arrays.asList(117.65427, 30.774)),
                new ArrayList<Double>(Arrays.asList(114.4403, 35.988)),
                new ArrayList<Double>(Arrays.asList(120.6465, 32.00)),
                new ArrayList<Double>(Arrays.asList(120.6465, 32.00)),
                new ArrayList<Double>(Arrays.asList(120., 30.2331)),
                new ArrayList<Double>(Arrays.asList(119.2816, 26.0782)),
                new ArrayList<Double>(Arrays.asList(113.2644, 23.1291)),
                new ArrayList<Double>(Arrays.asList(106.5046, 29.5332)),
                new ArrayList<Double>(Arrays.asList(121.4737, 31.2304)),
                new ArrayList<Double>(Arrays.asList(104.0665, 30.5728)),
                new ArrayList<Double>(Arrays.asList(103.8343, 36.0611)),
                new ArrayList<Double>(Arrays.asList(114.5149, 38.0428)),
                new ArrayList<Double>(Arrays.asList(117.2830, 31.8612)),
                new ArrayList<Double>(Arrays.asList(112.9823, 28.1941)),
                new ArrayList<Double>(Arrays.asList(108.9480, 34.2632)),
                new ArrayList<Double>(Arrays.asList(126.6425, 45.7560)),
                new ArrayList<Double>(Arrays.asList(102.7123, 25.0406)),
                new ArrayList<Double>(Arrays.asList(120.1551, 30.2741)),
                new ArrayList<Double>(Arrays.asList(113.5491, 22.1987)),
                new ArrayList<Double>(Arrays.asList(116.4074, 39.9042)),
                new ArrayList<Double>(Arrays.asList(123.4315, 41.8057)),
                new ArrayList<Double>(Arrays.asList(87.6177, 43.7928)),
                new ArrayList<Double>(Arrays.asList(106.2309, 38.4872)),
                new ArrayList<Double>(Arrays.asList(111.6080, 40.8086)),
                new ArrayList<Double>(Arrays.asList(118.7969, 32.0603)),
                new ArrayList<Double>(Arrays.asList(125.3245, 43.8868)),
                new ArrayList<Double>(Arrays.asList(114.3054, 30.5928)),
                new ArrayList<Double>(Arrays.asList(113.9213, 35.3013)),
                new ArrayList<Double>(Arrays.asList(120.6196, 31.2994)),
                new ArrayList<Double>(Arrays.asList(121.4890, 25.1055)),
                new ArrayList<Double>(Arrays.asList(109.7813, 39.6083)),
                new ArrayList<Double>(Arrays.asList(112.2384, 30.7378)),
                new ArrayList<Double>(Arrays.asList(114.1988, 22.3794)),
                new ArrayList<Double>(Arrays.asList(121.5440, 25.222))
        ));
       coordinates.forEach(coordinate -> {
           Point point = new Point(coordinate.get(0), coordinate.get(1));
           stringRedis.opsForGeo().add(Record.date+"gnote",point,point.toString());
       });
    }
    }

