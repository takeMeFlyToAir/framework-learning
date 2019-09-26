package com.zzr.springweb;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhaozhirong on 2019/6/3.
 */
@Controller
@RequestMapping(value = "/index")
public class controller {

    @RequestMapping(value = "/")
    @ResponseBody
    public Map<String, String> index(){
        Map<String,String> map = new HashMap<>();
        map.put("name","zzr");
        map.put("age","12");
        return map;
    }

    @RequestMapping(value = "/executeGet")
    @ResponseBody
    public Map<String, String> executeGet(String data){
        String url = "http://localhost:5000/v2/execute";
        String params = "?req_content="+data+"&sno=123";
        String result = HttpClientUrl.doGet(url+params);
        Map<String,String> map = new HashMap<>();
        map.put("result",result);
        return map;
    }

    @RequestMapping(value = "/execute")
    @ResponseBody
    public Map<String, String> execute(String data){
        String url = "http://localhost:5000/v2/execute";
        String params = "req_content="+data+"&sno=123";
        String result = HttpClientUrl.doPost(url,params);
        Map<String,String> map = new HashMap<>();
        map.put("result",result);
        return map;
    }

      @RequestMapping(value = "/executeHttpClinet")
    @ResponseBody
    public Map<String, String> executeHttpClinet(String data) throws UnsupportedEncodingException {
          HttpClient client = new HttpClient();
          //创建请求方法实例
          String url = "http://localhost:5000/v2/execute";
          PostMethod method = new PostMethod(url);
          String req = String.format("req_content=%s&sno=%s", data, "333");
          String repsStr = null;
          method.setRequestEntity(new StringRequestEntity(req,"text","UTF-8"));

          //添加参数
          //执行
          try {
              int statusCode = client.executeMethod(method);
              byte[] responseBody = method.getResponseBody();
              repsStr = new String(responseBody);
              System.out.println(repsStr);
          } catch (IOException e) {
              e.printStackTrace();
          }finally {
              method.releaseConnection();
          }
          Map<String,String> map = new HashMap<>();
          map.put("result",repsStr);
          return map;
    }

}
