package ru.DoublyLinkedList;

import ru.bgpu.task.list.ITaskList;
import java.util.Collection;
import java.util.Iterator;

public class TaskList<E> implements ITaskList<E> {
	private Node<E> head;
	private Node<E> tail;
	private int listSize;

	@Override
	public void add(E e) {
		Node<E> node = new Node<>();
		if (head == null) {
			head = node;
		} else {
			tail.nextElementPointer = node;
			tail.nextElementPointer.prevElementPointer = tail;
		}
		tail = node;
		node.data = e;
		listSize++;
	}

	@Override
	public void addAll(Collection<? extends E> collection) {
		if (!collection.isEmpty()) {
			for (E item : collection) {
				add(item);
			}
		}
	}

	@Override
	public void clear() {
		head = null;
		tail = null;
		listSize = 0;
	}

	@Override
	public E get(int index) {
		if (index < 0) {
			throw new RuntimeException("Invalid index");
		}
		if (index >= listSize) {
			throw new IndexOutOfBoundsException("Index (" + index + ") > max index (" + (listSize - 1) + ")");
		}
		if (head == null) {
			return null;
		}
		if (index == 0) {
			return head.data;
		}
		if (index == listSize - 1) {
			return tail.data;
		}
		Node<E> node;
		if (index <= listSize / 2) {
			node = head;
			for (int i = 0; i < index; i++) {
				node = node.nextElementPointer;
			}
		} else {
			node = tail;
			for (int i = listSize - 1; i > index; i--) {
				node = node.prevElementPointer;
			}
		}
		return node.data;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public boolean contains(E value) {
		if (head == null) {
			return false;
		}
		Node<E> node = head;
		for (int i = 0; i < listSize; i++) {
			if (node.data == value) {
				return true;
			}
			node = node.nextElementPointer;
		}
		return false;
	}

	@Override
	public E remove(int index) {
		if (index < 0) {
			throw new RuntimeException("Invalid index");
		}
		if (index >= listSize) {
			throw new IndexOutOfBoundsException("Index (" + index + ") > max index (" + (listSize - 1) + ")");
		}
		if (head == null) {
			return null;
		}
		E tmpData = null;
		Node<E> node;
		if (index == 0) {
			tmpData = head.data;
			head = head.nextElementPointer;
			head.prevElementPointer = null;
		}
		if (index == listSize - 1) {
			tmpData = tail.data;
			tail = tail.prevElementPointer;
			tail.nextElementPointer = null;
		}
		if (index <= listSize / 2) {
			node = head;
			for (int i = 0; i < index; i++) {
				if (i == index - 1) {
					tmpData = node.nextElementPointer.data;
					node.nextElementPointer = node.nextElementPointer.nextElementPointer;
					node.nextElementPointer.prevElementPointer = node;
				}
				node = node.nextElementPointer;
			}
		} else {
			node = tail;
			for (int i = listSize - 1; i > index; i--) {
				if (i == index + 1) {
					tmpData = node.prevElementPointer.data;
					node.prevElementPointer = node.prevElementPointer.prevElementPointer;
					node.prevElementPointer.nextElementPointer = node;
				}
				node = node.prevElementPointer;
			}
		}
		listSize--;
		return tmpData;
	}

	@Override
	public int size() {
		return listSize;
	}

	@Override
	public ITaskList<E> subList(int fromIndex, int toIndex) {
		if (fromIndex < 0 || toIndex < 0) {
			throw new RuntimeException("Invalid index");
		}
		if (fromIndex > toIndex) {
			throw new RuntimeException("Invalid index: fromIndex > toIndex");
		}
		if (fromIndex >= listSize) {
			throw new IndexOutOfBoundsException("fromIndex (" + fromIndex + ") > maxIndex (" + (listSize - 1) + ")");
		}
		if (toIndex >= listSize) {
			throw new IndexOutOfBoundsException("toIndex (" + toIndex + ") > maxIndex (" + (listSize - 1) + ")");
		}
		if (head == null) {
			return null;
		}
		Node<E> node;
		TaskList<E> list = new TaskList<>();
		if (fromIndex <= listSize / 2) {
			node = head;
			for (int i = 0; i <= toIndex; i++) {
				if (i >= fromIndex) {
					list.add(node.data);
				}
				node = node.nextElementPointer;
			}
		} else {
			node = tail;
			for (int i = listSize - 1; i > fromIndex; i--) {
				node = node.prevElementPointer;
			}
			for (int i = fromIndex; i <= toIndex; i++) {
				list.add(node.data);
				node = node.nextElementPointer;
			}
		}
		return list;
	}

	@Override
	public Object[] toArray() {
		if (head == null) {
			return null;
		}
		Object[] elements = new Object[listSize];
		Node<E> node = head;
		for (int i = 0; i < listSize; i++) {
			elements[i] = node.data;
			node = node.nextElementPointer;
		}
		return elements;
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<>() {
			Node<E> node = head;
			E data;

			@Override
			public boolean hasNext() {
				return node != null;
			}

			@Override
			public E next() {
				data = node.data;
				node = node.nextElementPointer;
				return data;
			}
		};
	}
}
