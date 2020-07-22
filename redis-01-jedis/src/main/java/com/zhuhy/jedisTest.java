package com.zhuhy;

import redis.clients.jedis.Jedis;

import java.util.Set;

public class jedisTest {
    public static void main(String[] args) {
        // 创建客户端 设置IP和端口
//        Jedis jedis = new Jedis("172.16.230.206", 6379);
        Jedis jedis = new Jedis("192.168.1.110", 6379);

        // 设置密码
        jedis.auth("7lsysdata7");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: " + jedis.ping());

        // 获取数据并输出
        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println(key);
        }
        // 关闭连接
        jedis.close();
    }
}
