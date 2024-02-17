package org.exmaple.chatbot.api.test;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class ApiTest {

    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/28885518425541/topics?scope=all&count=20");

        get.addHeader("cookie", "zsxqsessionid=2cacde64a3152b3c98cfce689031a8c9; abtest_env=product; tfstk=ep_BEN02AvDQn7onCJNN1ZaeyuL739a4RbORi_3EweLKebp1T2kyY_p5FOJGzvP3qhB1IOXFT_75j41cZwPHzWYhtUY83-zVF6fHrLAiMwz45etks-y4uPlXEjxz3MyYcvIeuAngnA1YCV3sy7TRcv4wrFb6OhEN9deVANACsK5dR438MBtBH6pO4n32h9JSVftmPC9415ioq2uWGTPg_wQMvCANu5Nsd0xpsC9415ioqHdM_hP_1vil.; zsxq_access_token=4F8CB0E4-E0F2-0BFC-D71A-56EDEE42C699_73BF88F251BD2382; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22415825151458888%22%2C%22first_id%22%3A%2218d3b5b23d84e4-0541ab0490ae2dc-26001951-1327104-18d3b5b23d955a%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E5%BC%95%E8%8D%90%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC%22%2C%22%24latest_referrer%22%3A%22https%3A%2F%2Fbugstack.cn%2F%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThkM2I1YjIzZDg0ZTQtMDU0MWFiMDQ5MGFlMmRjLTI2MDAxOTUxLTEzMjcxMDQtMThkM2I1YjIzZDk1NWEiLCIkaWRlbnRpdHlfbG9naW5faWQiOiI0MTU4MjUxNTE0NTg4ODgifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22415825151458888%22%7D%2C%22%24device_id%22%3A%2218d3b5b23d84e4-0541ab0490ae2dc-26001951-1327104-18d3b5b23d955a%22%7D\n");
        get.addHeader("Content-Type", "application/json;charset=utf8");

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(res);
            JsonNode questionlist = rootNode.get("resp_data").get("topics");
            System.out.println(questionlist.get(0).get("topic_id").getClass());
            for(int i = 0;i<questionlist.size();i++){

                String type = questionlist.get(i).get("type").toString();

                if (type.equals("\"talk\"") && questionlist.get(i).get("talk").get("owner").get("user_id").toString().equals("812842588851182")){
                    System.out.println(questionlist.get(i).get("talk").get("text"));
                }

//                System.out.println(questionlist.get(i));
            }

        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/811158481511512/comments");
        post.addHeader("cookie", "zsxqsessionid=2cacde64a3152b3c98cfce689031a8c9; abtest_env=product; tfstk=ep_BEN02AvDQn7onCJNN1ZaeyuL739a4RbORi_3EweLKebp1T2kyY_p5FOJGzvP3qhB1IOXFT_75j41cZwPHzWYhtUY83-zVF6fHrLAiMwz45etks-y4uPlXEjxz3MyYcvIeuAngnA1YCV3sy7TRcv4wrFb6OhEN9deVANACsK5dR438MBtBH6pO4n32h9JSVftmPC9415ioq2uWGTPg_wQMvCANu5Nsd0xpsC9415ioqHdM_hP_1vil.; zsxq_access_token=4F8CB0E4-E0F2-0BFC-D71A-56EDEE42C699_73BF88F251BD2382; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22415825151458888%22%2C%22first_id%22%3A%2218d3b5b23d84e4-0541ab0490ae2dc-26001951-1327104-18d3b5b23d955a%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E5%BC%95%E8%8D%90%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC%22%2C%22%24latest_referrer%22%3A%22https%3A%2F%2Fbugstack.cn%2F%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThkM2I1YjIzZDg0ZTQtMDU0MWFiMDQ5MGFlMmRjLTI2MDAxOTUxLTEzMjcxMDQtMThkM2I1YjIzZDk1NWEiLCIkaWRlbnRpdHlfbG9naW5faWQiOiI0MTU4MjUxNTE0NTg4ODgifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22415825151458888%22%7D%2C%22%24device_id%22%3A%2218d3b5b23d84e4-0541ab0490ae2dc-26001951-1327104-18d3b5b23d955a%22%7D\n");
        post.addHeader("Content-Type", "application/json;charset=utf8");

        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"越努力!越幸运!\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"mentioned_user_ids\": []\n" +
                "  }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

}