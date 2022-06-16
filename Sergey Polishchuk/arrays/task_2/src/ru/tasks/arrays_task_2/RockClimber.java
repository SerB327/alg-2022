package ru.tasks.arrays_task_2;

import java.util.Random;

public class RockClimber {
	private final int maxHeight;
	private final int dropHeight;
	private final int routeLenght;
	private int[] route;

	public RockClimber(int maxHeight, int dropHeight, int routeLenght) {
		if (maxHeight < 0 || dropHeight < 0 || routeLenght < 0) {
			throw new RuntimeException("Invalid value");
		} else {
			this.maxHeight = maxHeight;
			this.dropHeight = dropHeight;
			this.routeLenght = routeLenght;
		}
		generateRoute();
	}

	private void generateRoute() {
		route = new int[routeLenght];
		Random random = new Random();
		route[0] = random.nextInt(maxHeight);
		for (int i = 1; i < routeLenght; i++) {
			route[i] = route[i - 1] + (random.nextInt(dropHeight) - random.nextInt(dropHeight));
			if (route[i] < 0 || route[i] > maxHeight) {
				i--;
			}
		}
	}

	private void printRoute() {
		System.out.println();
		for (int i = 0; i < routeLenght; i++) {
			System.out.print(route[i] + "\t");
		}
	}

	public void renderRoute() {
		char[][] render = new char[maxHeight][routeLenght];
		for (int i = 0; i < routeLenght; i++) {
			for (int j = 0; j < route[i]; j++) {
				render[j][i] = '*';
			}
		}
		for (int i = maxHeight - 1; i >= 0; i--) {
			System.out.println();
			for (int j = 0; j < routeLenght; j++) {
				if (render[i][j] != '\0') {
					System.out.print(render[i][j] + "\t");
				} else
					System.out.print(" \t");
			}
		}
		printRoute();
	}
}