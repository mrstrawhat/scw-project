package com.atguigu.common.utils;

import java.nio.channels.ScatteringByteChannel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;

public class AppScwUtils {
	//3.将java代码中对象转换成json字符串存入redis
	public static <T>void getObjectToJsonStr(String token,StringRedisTemplate stringRedisTemplate,T type) {
		String jsonString = JSON.toJSONString(type);
		stringRedisTemplate.opsForValue().set(token, jsonString);
	}
	
	//2.将redis中json字符串转换成相应的对象处理	
	public static <T>T getJsonObject(Class<T> type,String token,StringRedisTemplate stringRedisTemplate ) {
		String jsonStr = stringRedisTemplate.opsForValue().get(token);
		if (StringUtils.isEmpty(jsonStr)) {
			return null;
		}	
		return JSON.parseObject(jsonStr, type);
	}
	
	//1.正则表达判断手机号码的正确性
	public static boolean getphoneIsTrue(String phoneNum) {
		boolean flag =true;
		  String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
	         if(phoneNum.length() != 11){
	            flag = false;
	          }else{
	            Pattern p = Pattern.compile(regex);
	             Matcher m = p.matcher(phoneNum);
	             flag = m.matches();
	          }
	         return flag;
	}
}
