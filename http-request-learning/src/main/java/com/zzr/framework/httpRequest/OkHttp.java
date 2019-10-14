package com.zzr.framework.httpRequest;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class OkHttp {

    private final Logger logger = LoggerFactory.getLogger(OkHttp.class);

    private static final MediaType parse = MediaType.parse("text; charset=utf-8");



    private OkHttpClient client;

    public OkHttp() {
        this.client = new OkHttpClient.Builder()
                .connectionPool(new ConnectionPool(5, 30, TimeUnit.SECONDS))
                .build();
    }

    public static void main(String[] args) {
        OkHttp okHttp = new OkHttp();
        okHttp.execute();
    }

    public void execute() {
        try {
            Request request = new Request.Builder()
                    .addHeader("Content-Type","application/json")
                    .url("http://localhost:9200/user/_doc/4")
                    .post(RequestBody.create("{\"userName\": \"hyy\",\"address\": \"beijing\",\"email\": \"zzr\",\"qq\": \"zzr\"}".getBytes()))
                    .build();
            Call call = this.client.newCall(request);
            Response httpResp = call.execute();
            ResponseBody respBody = httpResp.body();
            String repsStr;
            if (respBody != null) {
                repsStr = respBody.string();
                System.out.println(repsStr);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
