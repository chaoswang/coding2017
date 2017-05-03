package com.github.chaoswang.learning.java.queue;

/**
 * ÿ���ڶ�β����һ��Ԫ���ǣ�rear��1��ÿ���ڶ�ͷɾ��һ��Ԫ��ʱ��front��1��
 * ���Ų����ɾ�������Ľ��У�����Ԫ�صĸ������ϱ仯��������ռ�Ĵ洢�ռ�Ҳ��Ϊ���нṹ������������ռ����ƶ���
 * ��front=rearʱ��������û���κ�Ԫ�أ���Ϊ�ն��С�
 * ��rear���ӵ�ָ�����������ռ�֮��ʱ�������޷��ٲ�����Ԫ�أ�����ʱ�������д������ÿռ�δ��ռ�ã�
 * ��Щ�ռ����Ѿ����ӵĶ���Ԫ������ռ�ù��ô洢��Ԫ��
 * [(front,rear),  ,         ,  ]       1.��ʼ״̬���ĸ��ռ�Ϊ�գ�front��rearָ��ͬһ��λ��
 * [A(front)    , B       , C, (rear)]  2.����A��B��C����Ԫ��
 * [            , B(front), C, (rear)]  3.�Ƴ�AԪ��
 * [            ,         ,  , (front��rear)]  3.�Ƴ�B��CԪ��
 * ������ʵ��ѭ������
 */
public class CircleQueue <E> {
	
	private final static int DEFAULT_SIZE = 10;
	
	//������������ѭ�����е�Ԫ��
	private Object[] elementData = new Object[DEFAULT_SIZE] ;
	
	//��ͷ
	private int front = 0;  
	//��β  
	private int rear = 0;
	
	public boolean isEmpty() {
		return false;
    }

    public int size() {
        return -1;
    }

    public void enQueue(E data) {
        
    }

    public E deQueue() {
        return null;
    }
}
