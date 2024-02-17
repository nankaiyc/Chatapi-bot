package org.exmaple.chatbot.api.application.job;


import com.alibaba.fastjson.JSON;
import org.exmaple.chatbot.api.domain.ai.IZhuPuAI;
import org.exmaple.chatbot.api.domain.zsxq.IZsxqApi;
import org.exmaple.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import org.exmaple.chatbot.api.domain.zsxq.model.vo.Topics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

@EnableScheduling
@Configuration
public class ChatbotSchedule {
    private Logger logger = LoggerFactory.getLogger(ChatbotSchedule.class);

    @Value("${chatbot-api.groupId}")
    private long groupId;
    @Value("${chatbot-api.cookie}")
    private String cookie;


    @Resource
    private IZsxqApi zsxqApi;
    @Resource
    private IZhuPuAI zhuPuAI;

    @Scheduled(cron = "0/5 * * * * ?")
    public void run(){
        try {
//            if (new Random().nextBoolean()){
//                logger.info("随机打烊中...");
//                return;
//            }

            GregorianCalendar claendar = new GregorianCalendar();
            int hour = claendar.get(GregorianCalendar.HOUR_OF_DAY);
            if (hour > 22 && hour < 7){
                logger.info("打烊时间不工作，AI 下班了！");
                return;
            }

            // 1. 检索问题
            UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
            logger.info("测试结果：{}", JSON.toJSONString(unAnsweredQuestionsAggregates));
            List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
            if (null == topics || topics.isEmpty()){
                logger.info("本次检索未查询到待回答问题");
            }

            for (Topics topic : topics){
                // 2. AI 回答
                String answer = zhuPuAI.doZhuPu(topic.getTalk().getText());
                // 3. 问题回复
                boolean status = zsxqApi.answer(groupId, cookie, topic.getTopic_id(), answer);
                logger.info("编号：{} 问题：{} 回答：{}", topic.getTopic_id(), topic.getTalk().getText(), answer);
            }
        }
        catch (Exception e){
            logger.error("自动回答问题异常", e);
        }
    }
}
