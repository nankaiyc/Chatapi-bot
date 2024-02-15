package org.exmaple.chatbot.api.domain.zsxq;

import org.exmaple.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;

import java.io.IOException;

public interface IZsxqApi {
    UnAnsweredQuestionsAggregates queryUnAnsweredQuestionsTopicId(long groupId, String cookie) throws IOException;

    boolean answer(long groupId, String cookie, long topicId, String text) throws IOException;
}
