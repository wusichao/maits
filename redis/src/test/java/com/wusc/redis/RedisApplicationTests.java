package com.wusc.redis;

import com.wusc.vo.ReturnResult;
import com.wusc.redis.dto.RedisDTO;
import com.wusc.redis.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {
	@Autowired
	private RedisService service;

	@Test
	public void contextLoads() {
		RestTemplate template = new RestTemplate();
		RedisDTO wapper = new RedisDTO("set",new Class[]{String.class,String.class},new Object[]{"class","13"});
		ReturnResult re=template.postForEntity("http://localhost:8003/redis/operate",wapper,ReturnResult.class).getBody();
		/*ReturnResult re = (ReturnResult) service.operate(wapper);*/
		System.out.println(re.getData()+","+re.getCode());
	}

}
