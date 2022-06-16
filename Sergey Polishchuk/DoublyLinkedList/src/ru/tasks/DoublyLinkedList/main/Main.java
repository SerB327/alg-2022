package ru.tasks.DoublyLinkedList.main;

import ru.DoublyLinkedList.TaskList;
import ru.bgpu.task.list.ITaskList;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		TaskList<Integer> test = new TaskList<>();
		for (int i = 0; i < 100000; i++) {
			test.add(i);
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(test.get(0));
		System.out.println(test.get(test.size() - 1));
		System.out.println(test.get(15000));
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		ITaskList<Integer> taskList = test.subList(80, 90);
		for (Integer item : taskList) {
			System.out.println(item);
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(taskList.remove(5));
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		for (Integer item : taskList) {
			System.out.println(item);
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		Object[] items = taskList.toArray();
		for (Object item : items) {
			System.out.println(item);
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		ArrayList<Integer> arrayList = new ArrayList<>();
		TaskList<Integer> taskList1 = new TaskList<>();
		for (int i = 0; i < 10; i++) {
			arrayList.add(i);
			taskList1.add(i);
		}
		taskList1.addAll(arrayList);
		for (Integer item : taskList1) {
			System.out.println(item);
		}
	}
}