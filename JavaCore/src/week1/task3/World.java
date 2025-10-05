package week1.task3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class World implements Runnable {
  private static volatile boolean running = true;
  Set<Components> robot = new HashSet<>();
  List<Components> factoryRest = new ArrayList<>();

  public static void stopRunningWorld() {
    running = false;
  }

  static List<Components> producingRobots;

  @Override
  public void run() {
    producingRobots = new ArrayList<>();
    while (running) {

      synchronized (Task3.lock) {
        if (Task3.producedComponents.size() > 0) {
          for (Components part : Task3.producedComponents) {
            if (!robot.add(part)) {
              factoryRest.add(part);
            }
          }
        }

        Task3.producedComponents = factoryRest;
        System.out.println("World - components collected from the factory: " + robot);
        System.out.println(
            "World - components remaining at the factory: " + Task3.producedComponents);
        if (robot.size() == Task3.robotComponents.size()) {
          Factory.worldRobotsQ++;
          System.out.println(
              "World - All components received, one robot has produced.                       Total robots quantity is: "
                  + Factory.worldRobotsQ);
          robot.clear();
        }
        factoryRest = new ArrayList<>();
      }
      try {
        Thread.sleep(20);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
