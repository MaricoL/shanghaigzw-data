package com.gzw.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

/**
 * 防XSS工具Jsuop的工具类
 * @author sht 
 *
 */
public class JsoupUtil {
	/**
     * 使用自带的basicWithImages 白名单
     * 允许的便签有a,b,blockquote,br,cite,code,dd,dl,dt,em,i,li,ol,p,pre,q,small,span,
     * strike,strong,sub,sup,u,ul,img
     * 以及a标签的href,img标签的src,align,alt,height,width,title属性
     */
    private static final Whitelist whitelist = Whitelist.basicWithImages();
    /** 配置过滤化参数,不对代码进行格式化 */
    private static final Document.OutputSettings outputSettings = new Document.OutputSettings().prettyPrint(false);
    static {
        // 富文本编辑时一些样式是使用style来进行实现的
        // 比如红色字体 style="color:red;"
        // 所以需要给所有标签添加style属性
        whitelist.addAttributes(":all", "style");
    }

    public static String clean(String content) {
        return Jsoup.clean(content, "", whitelist, outputSettings);
    }
    
	/**
     * 将容易引起xss漏洞的半角字符直接替换成全角字符
     *
     * @param s
     * @return
     */
    public static String xssEncode(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length() + 16);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '\'':
                    // 全角单引号
                    sb.append('‘');
                    break;
                case '\"':
                    // 全角双引号
                    sb.append('“');
                    break;
                case '>':
                    // 全角大于号
                    sb.append('＞');
                    break;
                case '<':
                    // 全角小于号
                    sb.append('＜');
                    break;
                case '&':
                    // 全角&符号
                    sb.append('＆');
                    break;
                case '\\':
                    // 全角斜线
                    sb.append('＼');
                    break;
                case '#':
                    // 全角井号
                    sb.append('＃');
                    break;
                // < 字符的 URL 编码形式表示的 ASCII 字符（十六进制格式） 是: %3c
                case '%':
                    processUrlEncoder(sb, s, i);
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }
    
    public static void processUrlEncoder(StringBuilder sb, String s, int index) {
        if (s.length() >= index + 2) {
            // %3c, %3C
            if (s.charAt(index + 1) == '3' && (s.charAt(index + 2) == 'c' || s.charAt(index + 2) == 'C')) {
                sb.append('＜');
                return;
            }
            // %3c (0x3c=60)
            if (s.charAt(index + 1) == '6' && s.charAt(index + 2) == '0') {
                sb.append('＜');
                return;
            }
            // %3e, %3E
            if (s.charAt(index + 1) == '3' && (s.charAt(index + 2) == 'e' || s.charAt(index + 2) == 'E')) {
                sb.append('＞');
                return;
            }
            // %3e (0x3e=62)
            if (s.charAt(index + 1) == '6' && s.charAt(index + 2) == '2') {
                sb.append('＞');
                return;
            }
        }
        sb.append(s.charAt(index));
    }
}
