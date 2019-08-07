package com.basic.common.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @author 生成32位uuid
 */
public class UuidUntil {

   public static String  getUuid(){
       String  uuid = UUID.randomUUID().toString().replace("-", "");;
        return uuid;
    }
   
   public static String generatorUUID(int len){
   	String uuid=getUuid();
   	uuid=uuid.substring(0, len);
   	return uuid;
   }

   public static String MD5(String key) throws NoSuchAlgorithmException {
       char hexDigits[] = {
               '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
       };
       
           byte[] btInput = key.getBytes();
           // 获得MD5摘要算法的 MessageDigest 对象
           MessageDigest mdInst = MessageDigest.getInstance("MD5");
           // 使用指定的字节更新摘要
           mdInst.update(btInput);
           // 获得密文
           byte[] md = mdInst.digest();
           // 把密文转换成十六进制的字符串形式
           int j = md.length;
           char str[] = new char[j * 2];
           int k = 0;
           for (int i = 0; i < j; i++) {
               byte byte0 = md[i];
               str[k++] = hexDigits[byte0 >>> 4 & 0xf];
               str[k++] = hexDigits[byte0 & 0xf];
           }
           return new String(str);
   }
   public static void main(String[] args){
       int i = 0;
       while(i<20){
           System.out.println(UuidUntil.getUuid());
           i++;
       }
   }
}
