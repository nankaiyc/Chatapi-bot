package org.exmaple.chatbot.api.domain.zsxq.model.req;

public class ReqData {

    private String text;
    private String[] image_ids = new String[]{};

    public ReqData(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getImage_ids() {
        return image_ids;
    }

    public void setImage_ids(String[] image_ids) {
        this.image_ids = image_ids;
    }

}
