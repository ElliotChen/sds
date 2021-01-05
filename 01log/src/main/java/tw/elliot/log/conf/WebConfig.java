package tw.elliot.log.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import tw.elliot.log.interceptor.MDCLogging;
import tw.elliot.log.interceptor.ProfilerInterceptor;
import tw.elliot.log.interceptor.StopInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
	@Autowired
	private MDCLogging mdcLogging;
	@Autowired
	private StopInterceptor stopInterceptor;
	@Autowired
	private ProfilerInterceptor profilerInterceptor;
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(mdcLogging);
		registry.addInterceptor(stopInterceptor);
		registry.addInterceptor(profilerInterceptor);
	}
}
