package cc.eslink.util;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Copyright © 2017 华思科互联网科技有限公司. All rights reserved.
 * @Title: String2DateConverter.java
 * @Prject: springmvc
 * @Package: cc.eslink.hzhfpre.util
 * @Description: 自定义日期绑定参数组件
 * @author: yangwei
 * @date: 2017年11月6日 下午3:19:18
 * @version: V1.0
 */
public class String2DateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {

		//将日期字符串转换成日期类型Date
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			return simpleDateFormat.parse(source);
		} catch (Exception e) {
			return null;
		}

	}
	
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(simpleDateFormat.parseObject("2018-01-17 18:08:09"));
	}
	
}
