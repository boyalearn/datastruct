package com.xdata.currentlimit;

public interface RateLimiter {
	public boolean acquire();
	
	public boolean tryAcquire();
	
	public void supplement();
}
