package com.gzw.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 定时任务使用的例子
 * @author sht
 *
 */

@Component
@Slf4j
public class SchedulingConfig {
	
//	@Resource(name="userServiceT")
//    private IUserService userService;
//	
//	/**
//	 * 每5秒执行一次
//	 */
//	@Scheduled(cron="0/5 * * * * ?")
//	public void getToken() {
//		log.info("getToken定时任务启动");
//		UserT user = userService.getUserById(1);
//		System.out.println(user.toString());
//	}
}
