package org.exmaple.chatbot.api.domain.ai.model.aggregates;

import org.exmaple.chatbot.api.domain.ai.model.vo.Data;

public class AIAnswer {
    private int code;

    private String msg;

    private boolean success;

    private Data data;

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return this.data;
    }
}
