package cc.eslink.util;

import java.util.Date;
import java.util.Random;

/**
 * 唯一ID生成工具类
 * @author van.zheng
 *
 */
public class IDUtil {

	/**
	 * 生成唯一值
	 * @return
	 */
	public static String getUniqueId(){
		//YYYYMMDDHHMMSSSSS+随机数组成，28位数值
		return DateUtil.getLongDateSSS(new Date()) + buildRandomNum(11);
	}
	
	/**
	 * 生成20位唯一值
	 * @return
	 */
	public static String getShortUniqueId(){
		//YYYYMMDDHHMMSSSSS+3位随机数组成
		return DateUtil.getLongDateSSS(new Date())+ buildRandomNum(3);
	}
	/**
	 * 
	 * TODO 生成20位唯一值
	 * 
	 * @return
	 */
	public static String getShortUniqueIdByTime(){
        //time+7位随机数组成
        return new Date().getTime()+ buildRandomNum(7);
    }
	
	/** 
     * 返回一个定长的随机字符串(只包含数字) 
     *  
     * @param length 
     *            随机字符串长度 
     * @return 随机字符串 
     */  
    private static String buildRandomNum(int length) {  
        StringBuffer sb = new StringBuffer();  
        Random random = new Random();  
        for (int i = 0; i < length; i++) {  
            sb.append(random.nextInt(10));  
        }  
        return sb.toString();  
    }  
    
    /**
     * 得到产品或者服务编码
     * @param code
     * @return
     */
    public static String getProductOrServiceCode(String code){
		String str = String.valueOf(Integer.valueOf(code) + 1);
		int len = code.length();
		int strLen = str.length();
		
		if(len < strLen) {
            return null;
        }
		
		if(len == strLen) {
            return str;
        }
		
		StringBuffer num =  new StringBuffer();
		for(int i=0; i<len-strLen;i++){
			num.append("0");
		}
		num.append(str);
		return num.toString();
	}
    
    /** 
     * 生成短信验证码
     * 
     * @return
     */ 
    public static String getVerCode(){
        return buildRandomNum( 4 );
    }


	public static void main(String[] args) {
		System.out.println(getUniqueId());
		//租户ID
		System.out.println(getShortUniqueId());
	}
}
