package com.example.luckyadapter.scheduled;

import com.example.luckyapp.mqproducer.MailWarnProducer;
import com.example.luckyapp.record.query.RecordDDLQueryExe;
import com.example.luckyclient.dto.query.RecordWarnQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@Component
@AllArgsConstructor
public class MailScheduled {
       @Resource
       private  RecordDDLQueryExe recordDDLQueryExe;
       @Resource
       private  MailWarnProducer mailWarnProducer;
       @Scheduled(cron = "0 30 14 * * ?")
       public  void warnMail()
       {

              List<RecordWarnQuery> allDdl = recordDDLQueryExe.findAllDdl();
              if(allDdl.isEmpty())
              {
                     return;
              }
              allDdl.forEach(mailWarnProducer::send);
       }

}
