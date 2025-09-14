package week1.task3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task3 {

  static final Object lock = new Object();

  public static List<Components> producedComponents = new ArrayList<>();

  public static List<Components> robotComponents;

  public static void main(String[] args) throws InterruptedException {

    robotComponents = new ArrayList<>(Arrays.asList(Components.values()));
    System.out.println("Required components: " + robotComponents);

    Thread thread1 = new Thread(new Factory());
    Thread thread2 = new Thread(new World());
    Thread thread3 = new Thread(new Wednesday());
    thread1.start();
    thread2.start();
    thread3.start();
  }
}
