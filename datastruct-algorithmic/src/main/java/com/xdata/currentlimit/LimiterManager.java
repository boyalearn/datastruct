package com.xdata.currentlimit;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LimiterManager {
	private final ScheduledExecutorService scheduledCheck = Executors.newSingleThreadScheduledExecutor(new LimitThreadFactory());
	
	private RateLimiter rateLimiter=new TokenBucket(10);
	
	public void start(){
		scheduledCheck.scheduleAtFixedRate(new SupplementRateLimiter(),1000*1000, 1000*1000, TimeUnit.MICROSECONDS);
	}
	
	private class SupplementRateLimiter implements Runnable{
        @Override
        public void run(){
        	rateLimiter.supplement();
        }
    }
	public RateLimiter getRateLimiter(){
		return this.rateLimiter;
	}
}
