package com.xdata.currentlimit;

public interface RateLimiter {
	
	public void setFlowRate(int rate);
	
	public int getFlowRate();
	
	public void setMaxFlowRate(int rate);
	
	public boolean acquire();
	
	public boolean tryAcquire();
	
	public void supplement();

}
