package com.gzw.controller.system;


import com.gzw.util.ImageCreateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapping("/pictureCheckCode")
@Controller
public class PictureCheckCode {
	private ImageCreateUtil imageCreateUtil = new ImageCreateUtil();
	@RequestMapping("/getCheckCode")
	public void getImage(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ServletOutputStream outputStream = response.getOutputStream();
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		String code = imageCreateUtil.getImage(outputStream);
		HttpSession session = request.getSession();
		session.setAttribute("pictureCheckCode", code);
	}
	@RequestMapping("/checkCode")
	@ResponseBody
	public String doCheckCode(HttpServletRequest request,String code) {
		HttpSession session = request.getSession();
		String sessionCode = (String) session.getAttribute("pictureCheckCode");
		if(sessionCode.equalsIgnoreCase(code)) {
			return "true";
		}
		return "false";
	}
}
