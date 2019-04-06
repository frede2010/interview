package com.furui.Iterator.collection.list;

/**
 * 当链表只有一个节点的时候，first和last都是这个节点
 * @author Frede
 *
 */
public class MyLinkedList {
	private Node first;
	private Node last;
	private int size;
	
	public boolean add(Object obj) {
        linkLast(obj);
        return true;
    }
	
	public boolean addFirst(Object obj) {
        linkFirst(obj);
        return true;
    }
	
	public void add(int index, Object obj) {
        checkPositionIndex(index);
        if (index == size)
            linkLast(obj);
        else
            linkBefore(obj, node(index));
    }
	
	public Object remove(int index){
		checkElementIndex(index);
		return unLink(node(index));
	}

	public boolean remove(Object obj){
		if(obj == null){
			for(Node n = first; n != null; n = n.next){
				if(n.item == null)
					unLink(n);
				return true;
			}
		}else{
			for(Node n = first; n != null; n = n.next){
				if(obj.equals(n.item))
					unLink(n);
				return true;
			}
		}
		return false;
	}
	
	public Object set(int index, Object obj){
		checkElementIndex(index);
		Node n = node(index);
		Object oldItem = n.item;
		n.item = obj;
		return oldItem;
	}
	
	public Object get(int index){
		checkElementIndex(index);
		return node(index).item;
	}
	
	/**
	 * 获取此对象在链表中第一个的下标，不存在返回-1
	 * @param obj
	 * @return
	 */
	public int indexOf(Object obj){
		int index = 0;
		if(obj == null){
			for(Node n = first; n != null; n = n.next){
				if(n.item == null)
					return index;
				index++;
			}
		}else{
			for(Node n = first; n != null; n = n.next,index++){
				if(obj.equals(n.item))
					return index;
				index++;
			}
		}
		return -1;
	}

	/**
	 * 在制定节点前连上一个新节点
	 * @param obj
	 * @param node
	 */
	private void linkBefore(Object obj, Node node) {
		final Node p = node.prev;
		final Node newNode = new Node(node, p, obj);
		node.prev = newNode;
		if(p == null) {
			first = newNode;
		} else {
			p.next = newNode;
		}
		size++;
	}
	
	/**
	 * 断开指定节点
	 * @param node
	 * @return
	 */
	private Object unLink(Node node) {
		final Object element = node.item;
		final Node prev = node.prev;
        final Node next = node.next;
        if(prev == null){//说明要移除的node是第一个节点
        	first = next;
        }else{//若不是第一个节点
        	prev.next = next;
        	node.prev = null;
        }
        if(next == null){//说明要移除的node是最后个节点
        	last = prev;
        }else{//若不是最后一个节点
        	next.prev = prev;
        	node.next = null;
        }
        node.item = null;
        size--;
		return element;
	}

	/**
	 * 根据下标返回节点
	 * @param index
	 * @return
	 */
	private Node node(int index) {
		if(index < (size >> 1)){
			Node x = first;
			for(int i = 0; i < index; i++)
				x = x.next;
			return x;
		}else{
			Node x = last;
			for(int i = size - 1; i > index; i--)
				x = x.prev;
			return x;
		}
	}

	private void linkFirst(Object obj) {
		final Node f = first;
		final Node newNode = new Node(f, null, obj);
		first = newNode;
		if(f == null)
			last = newNode;
		else
			f.prev = newNode;
		size++;
	}

	private void linkLast(Object obj) {
		final Node l = last;
		final Node newNode = new Node(null, l, obj);
		last = newNode;
		//如果链表还未插入任何值，那么first和last都是null，此时add应该是第一个node
		if (l == null)
            first = newNode;
        else
            l.next = newNode;
		size++;
	}

	private static class Node {
		Node next;
		Node prev;
		Object item;
		
		public Node(Node next, Node prev, Object item) {
			super();
			this.next = next;
			this.prev = prev;
			this.item = item;
		}

	}
	
	private void checkElementIndex(int index) {
		if (!isElementIndex(index))
            throw new IndexOutOfBoundsException("越界啦");
	}
	
	private boolean isElementIndex(int index) {
		return index >= 0 && index < size;
	}

	private void checkPositionIndex(int index) {
		 if (!isPositionIndex(index))
	            throw new IndexOutOfBoundsException("越界啦");
	}
	
	//判断下标是否在0到节点数量之内
	private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

	public Object[] toArray() {
		Object[] itemArray = new Object[size];
		int i = 0;
		for(Node n = first; n != null; n=n.next){
			itemArray[i++] = n.item;
		}
		return itemArray;
	}
	
	
}

