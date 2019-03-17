package com.furui.Iterator.collection.list;

import java.util.Arrays;

/**
 * 自己实现一个ArrayList,帮助理解底层结构
 * @author Frede
 *
 */
public class MyArrayList {
	
	//底层用数组实现
	public Object[] elementData;
	
	//记录当前数组元素个数
	private int size;
	
	//无参构造器
	public MyArrayList(){
		this(10);
	}
	
	/**
	 * 有参构造器
	 * @param initialCapacity 初始化容量
	 */
	public MyArrayList(int initialCapacity){
		if(initialCapacity < 0)
			try {
				throw new Exception("初始化参数不能小于0");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			elementData = new Object[initialCapacity];
	}
	
	//添加元素
	public boolean add(Object obj){
		//数组扩容
		grow(size+1);
		elementData[size++] = obj;
		return true;
	}
	
	/**
	 * 在指定位置添加元素
	 * @param index
	 * @param obj
	 * @return
	 */
	public boolean add(int index, Object obj){
		rangeCheckAdd(index);
		grow(size+1);
		System.arraycopy(elementData, index, elementData, index + 1,
                size - index);
		elementData[index] = obj;
		size++;
		return true;
	}
	
	//获取元素
	public Object get(int index){
		rangeCheck(index);
		return elementData[index]; 
	}
	
	/**
	 * 根据下标移除元素
	 * @param index
	 * @return
	 */
	public Object remove(int index){
		rangeCheck(index);
		//被移除的元素
		Object oldValue = elementData[index];
		//需要移动index后面元素的数量
		int numMoved = size - 1 - index;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                             numMoved);
        elementData[--size] = null; // 减小数量，并制空，以便GC回收

        return oldValue;
	}
	
	/**
	 * 移除指定对象
	 * @param obj
	 * @return
	 */
	public boolean remove(Object obj){
		if(obj == null){
			for(int i = 0; i < size; i++){
				if(elementData[i] == null){
					remove(i);
					return true;
				}
			}
		}else{
			for(int i = 0; i < size; i++){
				if(obj.equals(elementData[i])){
					remove(i);
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 替换指定位置元素
	 * @param index
	 * @param obj
	 * @return
	 */
	public Object set(int index, Object obj){
		rangeCheck(index);
		Object oldValue = elementData[index];
		elementData[index] = obj;
		return oldValue;
	}

	private void grow(int minCapacity) {
		if(minCapacity > elementData.length){
			int oldCapacity = elementData.length;
			elementData = Arrays.copyOf(elementData, oldCapacity + (oldCapacity >> 1));//(原本1.5)
		}
	}
	
	private void rangeCheck(int index){
		if(index < 0 || index >= size)
			try {
				throw new Exception("越界啦");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	private void rangeCheckAdd(int index){
		if(index < 0 || index > size)
			try {
				throw new Exception("越界啦");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public int size() {
        return size;
    }
}
