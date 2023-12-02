package com.monitor.luckydrawmonitor.entity;

import lombok.Data;

@Data
public class Position {
   private String ip="本地";
   private String city="本地";
   private String province="本地";
   private String country="本地";
   private Double[] gnote=new Double[]{0.0,0.0};
}
