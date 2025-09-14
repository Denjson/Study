package week1.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Factory implements Runnable {

  public static List<Components> storage = new ArrayList<>();
  public static int worldRobotsQ = 0;
  public static int wednesdayRobotsQ = 0;
  private static int changesCheck = 0;

  public void run() {
    for (int i = 0; i < 100; i++) {

      synchronized (Task3.lock) {
        System.out.println(
            "Factory - day " + (i + 1) + ", starting stock: " + Task3.producedComponents);
        if (changesCheck == (worldRobotsQ + wednesdayRobotsQ)) {
          Task3.producedComponents.clear();
          System.out.println("Factory refreshed the collect");
        }
        changesCheck = worldRobotsQ + wednesdayRobotsQ;
        if (Task3.producedComponents.size() < 10) {
          Random random = new Random();
          while (Task3.producedComponents.size() < 10) {
            Task3.producedComponents.add(Components.getName(random.nextInt(4))); // adding element
          }
        }

        System.out.println(
            "Factory - day " + (i + 1) + ", stock a the end of a day: " + Task3.producedComponents);
      }

      try {
        Thread.sleep(20);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    World.stopRunningWorld();
    Wednesday.stopRunningWednesday();
    try {
      Thread.sleep(20);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("\nWorld total quantity of robots: " + worldRobotsQ);
    System.out.println("Wednesday total quantity of robots: " + wednesdayRobotsQ);
    if (worldRobotsQ == wednesdayRobotsQ) {
      System.out.println("No winner!");
    } else if (worldRobotsQ > wednesdayRobotsQ) {
      System.out.println("World wins!");
    } else {
      System.out.println("Wednesday wins!");
    }
  }
}
