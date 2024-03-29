package com.gzw.util;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * XSS攻击请求参数封装类
 * @author sht
 *
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
	
	HttpServletRequest orgRequest = null;  
	private boolean isIncludeRichText = false;
	
	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		orgRequest = request;
	}
	
	public XssHttpServletRequestWrapper(HttpServletRequest request,boolean isIncludeRichText) {
		super(request);
		orgRequest = request;
	    this.isIncludeRichText = isIncludeRichText;
	}
	
	/** 
	* 覆盖getParameter方法，将参数名和参数值都做xss过滤。<br/> 
	* 如果需要获得原始的值，则通过super.getParameterValues(name)来获取<br/> 
	* getParameterNames,getParameterValues和getParameterMap也可能需要覆盖 
	*/  
	@Override  
	public String getParameter(String name) {  
        if(("content".equals(name) || name.endsWith("WithHtml")) && !isIncludeRichText){
                return super.getParameter(name);
            }
            name = JsoupUtil.clean(name);
        String value = super.getParameter(name);  
        if (StringUtils.isNotBlank(value)) {
            value = JsoupUtil.clean(value);  
            //value = JsoupUtil.xssEncode(value);
        }
        return value;  
	}  
		    
    @Override
    public String[] getParameterValues(String name) {
        String[] arr = super.getParameterValues(name);
        if(arr != null){
            for (int i=0;i<arr.length;i++) {
                arr[i] = JsoupUtil.clean(arr[i]);
                //將英文符符号替换为中文全角符号
                //arr[i] = JsoupUtil.xssEncode(arr[i]);
            }
        }
        return arr;
    }
	    
	  
    /** 
	* 覆盖getHeader方法，将参数名和参数值都做xss过滤。<br/> 
	* 如果需要获得原始的值，则通过super.getHeaders(name)来获取<br/> 
	* getHeaderNames 也可能需要覆盖 
	*/  
    @Override  
    public String getHeader(String name) {  
        name = JsoupUtil.clean(name);
        String value = super.getHeader(name);  
        if (StringUtils.isNotBlank(value)) {  
            value = JsoupUtil.clean(value);
            //將英文符符号替换为中文全角符号
            //value = JsoupUtil.xssEncode(value);
        }  
        return value;  
    }  
  
    /** 
	* 获取最原始的request 
	* 
	* @return 
	*/  
    public HttpServletRequest getOrgRequest() {  
        return orgRequest;  
    }  
  
    /** 
	* 获取最原始的request的静态方法 
	* 
	* @return 
	*/  
	public static HttpServletRequest getOrgRequest(HttpServletRequest req) {  
	    if (req instanceof XssHttpServletRequestWrapper) {  
	        return ((XssHttpServletRequestWrapper) req).getOrgRequest();  
	    }  
	    return req;  
	}

}
