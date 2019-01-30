package com.xdata.currentlimit;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LimiterManager{
	private final ScheduledExecutorService scheduledCheck = Executors.newSingleThreadScheduledExecutor(new LimitThreadFactory());
	
	public void addSchedule(RateLimiter rateLimiter){
		if(null==rateLimiter){
			return ;
		}
		if("0".equals(rateLimiter.getFlowRate())){
			return ;
		}
		scheduledCheck.scheduleAtFixedRate(new SupplementRateLimiter(rateLimiter),
				1000*1000/rateLimiter.getFlowRate(), 
				1000*1000/rateLimiter.getFlowRate(), TimeUnit.MICROSECONDS);
	}
	
	private class SupplementRateLimiter implements Runnable{
		
		private RateLimiter rateLimiter;

		public SupplementRateLimiter(RateLimiter rateLimiter){
			this.rateLimiter=rateLimiter;
		}
		
        @Override
        public void run(){
        	rateLimiter.supplement();
        }
    }
}
