package com.xdata.utils;

public final class StringUtil {
	public static final boolean isEmpty(String str){
		if(null==str||"".equals(str)){
			return true;
		}else{
			return false;
		}
	}
}
