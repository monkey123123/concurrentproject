package me.monkey.gateway.demo.gson;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class GsonDemo {
    public static void main(String[] args) {
        String str = "{\"name\":1}";

        //创建一个JsonParser
        JsonParser parser = new JsonParser();
//通过JsonParser对象可以把json格式的字符串解析成一个JsonElement对象
        JsonElement el = parser.parse(str);
        System.out.println(el.toString());
//        System.out.println(el.getAsString());
        Gson gson = new Gson();
        System.out.println(gson.toJson(str));
    }
}
