package ru.tasks.deque;

import ru.bgpu.task.deque.Deque;

import java.util.Random;

public class Main {
	public static void main(String[] args) {
		Deque<Integer> deque = new Deque<Integer>(10);
		Random random = new Random();
		int randomValue;
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Содержание дека:");
		for (int i = 0; i < deque.getDequeSize(); i++) {
			randomValue = random.nextInt(100);
			deque.pushBack(randomValue);
			System.out.println(randomValue);
		}
		System.out.println("Размер дека: " + deque.getDequeSize());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		for (int i = 0; i < deque.getDequeSize() / 2; i++) {
			System.out.println(deque.popFront());
			System.out.println(deque.popBack());
		}
	}
}