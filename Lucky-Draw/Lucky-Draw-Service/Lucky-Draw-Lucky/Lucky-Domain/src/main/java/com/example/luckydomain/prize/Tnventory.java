package com.example.luckydomain.prize;


import com.example.luckydrawconfig.exception.Exception;
import lombok.Getter;

@Getter
public class Tnventory {

    private Integer inventory;

    public Tnventory(Integer inventory) {
        if (inventory < 0) {
            throw new Exception("库存数量请大于等于 0");
        }
        this.inventory = inventory;
    }


}
