package com.xdata.currentlimit;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;
/**
 * ����Ͱʵ��
 * @author zouhuixing
 * 
 * @version one
 * ������������ʵ��
 * 1.��ƽ�����ʽϴ�ʱ���������Ƶ���Ҫ��β��������������⡣�¸��汾���ǲ��ü�����ʵ��
 *
 */
public class TokenBucket implements RateLimiter{
	
	private static final int DEFAULT_BUCKET_SIZE = 60;
	
	/**
	 * Ͱ���������
	 */
	private int maxFlowRate;
	
	/**
	 * ƽ�������ٶ�
	 */
	private int flowRate;
	
	/**
	 * ������ ����ҵ�� �����еݹ�����
	 */
	private ReentrantLock lock = new ReentrantLock(true);
	
	
	/**
	 * Ͱ����
	 */
	private ArrayBlockingQueue<Byte> tokenQueue;
	
	/**
	 * ������
	 * @param maxFlowRate
	 * @param flowRate
	 */
	public TokenBucket(int maxFlowRate,int flowRate){
		this.maxFlowRate=maxFlowRate;
		this.flowRate=flowRate;
		this.tokenQueue= new ArrayBlockingQueue<Byte>(this.maxFlowRate);
	}
	public TokenBucket(int flowRate){
		this(DEFAULT_BUCKET_SIZE,flowRate);
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
	@Override
	public void setFlowRate(int rate) {
		this.flowRate=rate;
		
	}
	@Override
	public void setMaxFlowRate(int rate) {
		this.maxFlowRate=rate;
		
	}
	@Override
	public int getFlowRate() {
		// TODO Auto-generated method stub
		return this.flowRate;
	}

}
