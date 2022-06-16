package ru.tasks.arrays_task_2;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Кол-во скалолазов: ");
		int rockClimberCount = scanner.nextInt();
		RockClimber[] rockClimbers = new RockClimber[rockClimberCount];
		int maxHeight;
		int dropHeight;
		int routeLenght;
		for (int i = 0; i < rockClimberCount; i++) {
			System.out.printf("Скалолаз [%d]:\n", i + 1);
			System.out.print("Максимальная высота: ");
			maxHeight = scanner.nextInt();
			System.out.print("Высота перепадов: ");
			dropHeight = scanner.nextInt();
			System.out.print("Длина маршрута: ");
			routeLenght = scanner.nextInt();
			rockClimbers[i] = new RockClimber(maxHeight, dropHeight, routeLenght);
		}
		System.out.println("Маршруты скалолазов: ");
		for (int i = 0; i < rockClimberCount; i++) {
			System.out.printf("\nСкалолаз [%d]:\n", i + 1);
			rockClimbers[i].renderRoute();
		}
	}
}