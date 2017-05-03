package com.github.chaoswang.learning.java.queue;

/**
 * 每次在队尾插入一个元素是，rear增1；每次在队头删除一个元素时，front增1。
 * 随着插入和删除操作的进行，队列元素的个数不断变化，队列所占的存储空间也在为队列结构所分配的连续空间中移动。
 * 当front=rear时，队列中没有任何元素，称为空队列。
 * 当rear增加到指向分配的连续空间之外时，队列无法再插入新元素，但这时往往还有大量可用空间未被占用，
 * 这些空间是已经出队的队列元素曾经占用过得存储单元。
 * [(front,rear),  ,         ,  ]       1.初始状态，四个空间为空，front和rear指向同一个位置
 * [A(front)    , B       , C, (rear)]  2.插入A，B，C三个元素
 * [            , B(front), C, (rear)]  3.移除A元素
 * [            ,         ,  , (front，rear)]  3.移除B，C元素
 * 用数组实现循环队列
 */
public class CircleQueue <E> {
	
	private final static int DEFAULT_SIZE = 10;
	
	//用数组来保存循环队列的元素
	private Object[] elementData = new Object[DEFAULT_SIZE] ;
	
	//队头
	private int front = 0;  
	//队尾  
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
