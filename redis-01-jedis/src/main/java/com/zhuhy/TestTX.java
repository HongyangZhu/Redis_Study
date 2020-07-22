package com.zhuhy;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestTX {
    public static void main(String[] args) {
        // 创建客户端 设置IP和端口
        Jedis jedis = new Jedis("192.168.1.110", 6379);
        // 设置密码
        jedis.auth("7lsysdata7");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello", "world");
        jsonObject.put("name", "zhuhy");
        String result = jsonObject.toJSONString();
        // 开启事务
        Transaction multi = jedis.multi();
        try {
            // 命令入队
            multi.set("user1", result);
            multi.set("user2", result);
            // 执行事务
            multi.exec();
        } catch (Exception e) {
            // 放弃事务
            multi.discard();
            e.printStackTrace();
        } finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            // 关闭连接
            jedis.close();
        }


    }
}
