package com.example.luckydomain.record;


import com.example.luckydrawconfig.util.AssertUtil;

public class RecordStatus {

    /**
     * 状态（0，1，2，3）
     */
    private RecordStatusEnum state;

    public Integer getState(){
        return this.state.getValue();
    }


    public RecordStatus(Integer state) {
        AssertUtil.isTrue(state < 0, "记录状态无效！");


        if (RecordStatusEnum.STATUE_1.getValue().equals(state)){
            this.state = RecordStatusEnum.STATUE_1;
            return;
        }

        if (RecordStatusEnum.STATUE_2.getValue().equals(state)){
            this.state = RecordStatusEnum.STATUE_2;
            return;
        }

        if (RecordStatusEnum.STATUE_3.getValue().equals(state)){
            this.state = RecordStatusEnum.STATUE_3;
            return;
        }

        if (RecordStatusEnum.STATUE_4.getValue().equals(state)){
            this.state = RecordStatusEnum.STATUE_4;
            return;
        }

        AssertUtil.isTrue(true, "记录状态无效！");
    }


}
