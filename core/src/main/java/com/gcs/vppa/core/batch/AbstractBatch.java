package com.gcs.vppa.core.batch;

import java.util.Arrays;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public abstract class AbstractBatch {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractBatch.class);

    protected static final String URI = "/engine-rest/process-definition/key/%s/start";

    protected String getExecutedMonth(String calculateMonth) {
        int[] array = Arrays.stream(calculateMonth.split("/"))
                .mapToInt(Integer::parseInt)
                .toArray();
        if (array[0] == 1) {
            array[0] = 12;
            array[1] -= 1;
        } else {
            array[0] -= 1;
        }

        return String.valueOf(array[1]).concat("-").concat(array[0] > 10 ? String.valueOf(array[0]) : "0" + String.valueOf(array[0]));
    }

    protected void postCamundaProcess(String host, String key, String executor, String process, String month, String schemes, String bicc, String bau) {
        try {
            JSONObject runId = new JSONObject()
                    .put("value", executor)
                    .put("type", "String");
            JSONObject processId = new JSONObject()
                    .put("value", process)
                    .put("type", "String");
            JSONObject calculateMonth = new JSONObject()
                    .put("value", month)
                    .put("type", "String");
            JSONObject schemeIds = new JSONObject()
                    .put("value", String.join(",", schemes))
                    .put("type", "String");
            JSONObject biccEmail = new JSONObject()
                    .put("value", bicc)
                    .put("type", "String");
            JSONObject bauEmail = new JSONObject()
                    .put("value", bau)
                    .put("type", "String");
            JSONObject variables = new JSONObject()
                    .put("runId", runId)
                    .put("processId", processId)
                    .put("calculateMonth", calculateMonth)
                    .put("schemeIds", schemeIds)
                    .put("biccEmail", biccEmail)
                    .put("bauEmail", bauEmail);
            String payload = new JSONObject()
                    .put("variables", variables)
                    .toString();
            HttpEntity entity = new StringEntity(payload, ContentType.APPLICATION_JSON);
            HttpClient client = HttpClients.createDefault();
            HttpPost post = new HttpPost(host + String.format(URI, key));
            post.setEntity(entity);
            client.execute(post);
        } catch (Exception e) {
            LOGGER.debug(e.getMessage());
        }
    }
}
