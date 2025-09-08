package week1.task3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
//import java.util.concurrent.CopyOnWriteArrayList;

public class Task3 {

	public volatile static List<Components> robotComponents; // list of required components for one robot

//	public static CopyOnWriteArrayList<Components> producedComponents = new CopyOnWriteArrayList<>();
	public static List<Components> producedComponents = Collections.synchronizedList(new ArrayList<>());
//	public static List<Components> producedComponents = new ArrayList<>();				// shared collection, list of 10 manufactured components
	static volatile int robotsQ = 100; // required quantity of robots
	public static boolean ready = false; // ready to collect the components from the factory
	public static boolean victory = false; // victory condition to stop the factory and competitors

	public static synchronized void addProducedComponents(Components com) {
		producedComponents.add(com);
	}

	public static synchronized void removeProducedComponents(int index) {
		producedComponents.remove(index);
	}

	public static synchronized int sizeProducedComponents() {
		return producedComponents.size();

	}

	public static synchronized int indexProducedComponents(Components part) {
		return producedComponents.indexOf(part);
	}

	public static synchronized List<Components> printProducedComponents() {
		return producedComponents;
	}

	public static synchronized boolean getReady() {
		return ready;
	}

	public static synchronized void setReady(boolean k) {
		ready = k;
	}

	public static synchronized boolean getVictory() {
		return victory;
	}

	public static synchronized void setVictory(boolean v) {
		victory = v;
	}

	public static void main(String[] args) throws InterruptedException {

		robotComponents = new ArrayList<>(Arrays.asList(Components.values()));
		System.out.println("Required components: " + robotComponents);

		Thread thread1 = new Thread(new Factory());
		Thread thread2 = new Thread(new World());
		Thread thread3 = new Thread(new Wednesday());
		thread1.start();
		Thread.sleep(200);
		thread2.start();
//		Thread.sleep(100);
		thread3.start();
	}
}

class Factory implements Runnable {

	public static List<Components> storage = new ArrayList<>(); // produced components by the factory
	static int counter = 0; // counter of days

	public void run() {
		while (!Task3.getVictory()) { // stop factory when victory is achieved
			counter++;
			try { // waiting till the end of a day
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Factory - day " + counter + ", starting stock: " + Task3.printProducedComponents());
			if (Task3.sizeProducedComponents() < 10) {

				Task3.setReady(false); // factory is closed, stream cannot collect items
				Random random = new Random();
				while (Task3.sizeProducedComponents() < 10) {
					Task3.addProducedComponents(Components.getName(random.nextInt(4))); // adding element via
																						// synchronized method
				}
				Task3.setReady(true); // parts can be collected by streams

				System.out.println(
						"Factory - day " + counter + ", stock a the end of a day: " + Task3.printProducedComponents());
			}
		}

	}
}

class World implements Runnable {

	static List<Components> producingRobots; // collecting full set of elements for one robot
	static int robotsQProduced = 0; // quantity of robots produced by this factory

	@Override
	public void run() {
		while ((Task3.getReady() && (robotsQProduced < Task3.robotsQ)) && !Task3.getVictory()) { // can collect items
																									// when factory is
																									// open

			producingRobots = new ArrayList<>();

			if (Task3.sizeProducedComponents() > 0) {
				for (Components part : Task3.robotComponents) { // Traveling over all required robot components
//			System.out.println("Element " + part);
					int index = Task3.indexProducedComponents(part); // looking for required component in factory store
					if (index > -1) {
//				System.out.println("Found: " + Task3.producedComponents.indexOf(part));
						producingRobots.add(part); // adding component for robot set
						Task3.removeProducedComponents(index); // deleting component from factory store via synchronized
																// method
					}
				}
			}

			System.out.println("World - components collected from the factory: " + producingRobots);
			if (Task3.robotComponents.size() == producingRobots.size()) {

				robotsQProduced++; // counting produced robots
				System.out.println(
						"World - All components received, one robot has produced.                       Total robots quantity is: "
								+ robotsQProduced);
				producingRobots.clear();
				if (robotsQProduced == Task3.robotsQ) {
					Task3.setVictory(true);
					System.out.println("World - Victory!");

				}

			} else {
				System.out.println(
						"World - Not all components available, waiting for the next day. Available components: "
								+ producingRobots);
				try { // waiting till the end of a day
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}

class Wednesday implements Runnable {

	static List<Components> producingRobots; // collecting full set of elements for one robot
	static int robotsQProduced = 0; // quantity of robots produced by this factory

	@Override
	public void run() {
		while ((Task3.getReady() && (robotsQProduced < Task3.robotsQ)) && !Task3.getVictory()) { // can collect items when factory is open

			producingRobots = new ArrayList<>();

			if (Task3.sizeProducedComponents() > 0) {
				for (Components part : Task3.robotComponents) { // Traveling over all required robot components
//			System.out.println("Element " + part);
					int index = Task3.indexProducedComponents(part); // looking for required component in factory store
					if (index > -1) {
//			System.out.println("Found: " + Task3.producedComponents.indexOf(part));
						producingRobots.add(part); // adding component for robot set
						Task3.removeProducedComponents(index); // deleting component from factory store via synchronized
																// method
					}
				}
			}

			System.out.println("Wednesday - components collected from the factory: " + producingRobots);
			if (Task3.robotComponents.size() == producingRobots.size()) {

				robotsQProduced++; // counting produced robots
				System.out.println(
						"Wednesday - All components received, one robot has produced.                       Total robots quantity is: "
								+ robotsQProduced);
				producingRobots.clear();
				if (robotsQProduced == Task3.robotsQ) {
					Task3.setVictory(true);
					System.out.println("World - Victory!");

				}

			} else {
				System.out.println(
						"Wednesday - Not all components available, waiting for the next day. Available components: "
								+ producingRobots);
				try { // waiting till the end of a day
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}

enum Components {
	HEAD(0), TORSO(1), HANDS(2), FEET(3);

	private int code;

	Components(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static Components getName(int idNr) {
		for (Components col : Components.values()) {
			if (col.getCode() == idNr)
				return col;
		}
		return FEET; // default value if ID is out of range
	}

}






//Each faction is trying to create an army of robots, but to do so they need parts for the robots.
//Robot parts are divided into: head, torso, hand, feet.
//They are produced by a neutral Factory, which produces no more than 10 parts every day.
//The type of parts is chosen randomly.
//At night, the factions go to the Factory to get parts for the robots (each faction can carry no more than 5 parts).
//There are two factions: World and Wednesday and neutral object - Factory.
//The factions and the factory each work in their own thread.
//Determine who will have the strongest army after 100 days.







