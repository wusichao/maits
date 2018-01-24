package com.wusc.pay;

import com.wusc.pay.controller.PayController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayApplicationTests {
	@Autowired
	private PayController payController;
	@Test
	public void sign() throws Exception {
		/*payController.unifiedorder();*/
	}

}
