package org.exmaple.chatbot.api.test;

import com.alibaba.fastjson.JSON;
import org.exmaple.chatbot.api.domain.ai.IZhuPuAI;
import org.exmaple.chatbot.api.domain.zsxq.IZsxqApi;
import org.exmaple.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import org.exmaple.chatbot.api.domain.zsxq.model.vo.Topics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRunTest {
    private Logger logger = LoggerFactory.getLogger(SpringBootRunTest.class);

    @Value("${chatbot-api.groupId}")
    private long groupId;
    @Value("${chatbot-api.cookie}")
    private String cookie;
    @Value("${chatbot-api.API_KEY}")
    private String API_KEY;
    @Value("${chatbot-api.API_SECRET}")
    private String API_SECRET;

    @Resource
    private IZsxqApi zsxqApi;
    @Resource
    private IZhuPuAI zhuPuAI;

    @Test
    public void test_zsxqApi() throws IOException {
        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
        System.out.println(unAnsweredQuestionsAggregates);
        logger.info("测试结果：{}", JSON.toJSONString(unAnsweredQuestionsAggregates));

        List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
        for (Topics topic : topics) {
            long topicId = topic.getTopic_id();
            String text = topic.getTalk().getText();
            logger.info("topicId：{} text：{}", topicId, text);

            // 回答问题
//            zsxqApi.answer(groupId, cookie, topicId, text);
        }
    }

    @Test
    public void test_ZhuPuAIApi() throws IOException{
        String anwser = zhuPuAI.doZhuPu("秦寅畅帅吗");
        logger.info("回答：{}", anwser);
    }
}
