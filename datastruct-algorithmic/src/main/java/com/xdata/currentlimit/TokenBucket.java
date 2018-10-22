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
	@SuppressWarnings("unused")
	private int avgFlowRate;
	
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
