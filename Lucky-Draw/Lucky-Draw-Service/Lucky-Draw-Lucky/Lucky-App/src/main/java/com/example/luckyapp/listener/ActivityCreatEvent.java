package com.example.luckyapp.listener;

import com.example.luckyclient.dto.data.ActivityConfigVO;
import org.springframework.context.ApplicationEvent;

public class ActivityCreatEvent extends ApplicationEvent {
    private ActivityConfigVO activityConfigVO;
    public ActivityConfigVO getActivityConfigVO()
    {
        return activityConfigVO;
    }
    public ActivityCreatEvent(Object source,ActivityConfigVO activityConfigVO) {
        super(source);
        this.activityConfigVO=activityConfigVO;
    }


}
