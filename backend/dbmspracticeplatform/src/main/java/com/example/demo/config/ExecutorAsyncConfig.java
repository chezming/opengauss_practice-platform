package com.example.demo.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class ExecutorAsyncConfig {
	
	@Bean(name = "asyncExecutor")
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		//设置核心线程数
		taskExecutor.setCorePoolSize(10);
		// 线程池维护线程的最大数量，只有在缓冲队列满了以后才会申请超过核心线程数的线程
		taskExecutor.setMaxPoolSize(50);
		//缓存队列
		taskExecutor.setQueueCapacity(20);
		//允许的空闲时间，当超过了核心线程数之外的线程在空闲时间到达之后会被销毁
		taskExecutor.setKeepAliveSeconds(60);
		//异步方法内部线程名称
		taskExecutor.setThreadNamePrefix("AsyncExecutor-");
		//拒绝策略
		taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();
        
        return taskExecutor;
	}

}