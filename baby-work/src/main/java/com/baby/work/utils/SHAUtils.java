package com.baby.work.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAUtils {
    public static String encodeData(String str){
        try {
            MessageDigest md=MessageDigest.getInstance("SHA-256");
            md.update(str.getBytes());
            byte byteDate[] = md.digest();
            //将字节转换为十六进制格式方法一
            StringBuffer sb=new StringBuffer();
            for (int i=0;i<byteDate.length;i++){
                sb.append(Integer.toString((byteDate[i]&0xff)+0x100,16).substring(1));
            }
            System.out.println("Hex format : " + sb.toString());
            return sb.toString();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }
}
