package com.xdata.currentlimit;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 令牌桶实现
 * @author zouhuixing
 * 
 * @version one
 * 采用阻塞队列实现
 * 1.当平均速率较大时。补充令牌的需要多次操作引起性能问题。下个版本考虑采用计数器实现
 *
 */
public class TokenBucket implements RateLimiter{
	
	private static final int DEFAULT_BUCKET_SIZE = 60;
	
	/**
	 * 桶的最大容量
	 */
	private int maxFlowRate;
	
	/**
	 * 平均限流速度
	 */
	@SuppressWarnings("unused")
	private int avgFlowRate;
	
	/**
	 * 重入锁 考虑业务 代码有递归的情况
	 */
	private ReentrantLock lock = new ReentrantLock(true);
	
	
	/**
	 * 桶容器
	 */
	private ArrayBlockingQueue<Byte> tokenQueue;
	
	/**
	 * 构造器
	 * @param maxFlowRate
	 * @param avgFlowRate
	 */
	public TokenBucket(int maxFlowRate,int avgFlowRate){
		this.maxFlowRate=maxFlowRate;
		this.avgFlowRate=avgFlowRate;
		this.tokenQueue= new ArrayBlockingQueue<Byte>(this.maxFlowRate);
	}
	public TokenBucket(int avgFlowRate){
		this(DEFAULT_BUCKET_SIZE,avgFlowRate);
	}
	
	@Override
	public boolean acquire() {
		try{
			lock.lock();
			if(null==this.tokenQueue.poll()){
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			lock.unlock();
		}
		return true;
		
	}

	@Override
	public boolean tryAcquire() {
		try{
			lock.lock();
			if(null==this.tokenQueue.poll()){
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			lock.unlock();
		}
		return true;
	}
	@Override
	public void supplement() {
		try{
			lock.lock();
			if(!this.tokenQueue.offer((byte)1)){
				return;
			}
		}catch(Exception e){
			
		}finally{
			System.out.println("bucket size put"+this.tokenQueue.size());
			lock.unlock();
		}
	}

}
