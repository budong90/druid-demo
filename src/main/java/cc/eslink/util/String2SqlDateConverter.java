package cc.eslink.util;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Copyright © 2017 华思科互联网科技有限公司. All rights reserved.
 * @Title: String2SqlDateConverter.java
 * @Prject: springmvc
 * @Package: cc.eslink.hzhfpre.util
 * @Description: 日期参数绑定组件
 * @author: yangwei
 * @date: 2017年11月6日 下午5:44:17
 * @version: V1.0
 */
public class String2SqlDateConverter implements Converter<String, java.sql.Date> {

	@Override
	public java.sql.Date convert(String dateStringToParse) {
		// TODO Auto-generated method stub

		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStringToParse);
			return new java.sql.Date(date.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	public static void main(String[] args) {
		
	}
	
}
