package tw.elliot.log.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Service01 {

	public void method01() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			log.error("Thread Trouble!", e);
		}
	}

	public void method02(String name) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			log.error("Thread Trouble!", e);
		}
	}
}
