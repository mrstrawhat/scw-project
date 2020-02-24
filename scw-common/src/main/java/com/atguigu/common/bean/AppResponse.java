package com.atguigu.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class AppResponse<T> {
	//响应状态码
	private String code;
	//响应状态描述
	private String status;
	//响应数据
	private T data;
	
	//响应成功后的方法
	public static  <T> AppResponse<T> ok(T data) {
		return new AppResponse<T>("00000","成功",data);
	}
	
	//响应失败后的方法
	public static <T> AppResponse<T> fail(String code, String status){
		return new AppResponse<T>(code,status,null);
	}
	
}
