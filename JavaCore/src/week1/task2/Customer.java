package week1.task2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import week1.task2.res.Generators;
import week1.task2.res.People;

class Customer {
    private String customerId;
    private String name;
    private String email;
    private LocalDateTime registeredAt;
    @Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", email=" + email + ", registeredAt="
				+ registeredAt + ", age=" + age + ", city=" + city + "]";
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDateTime getRegisteredAt() {
		return registeredAt;
	}
	public void setRegisteredAt(LocalDateTime registeredAt) {
		this.registeredAt = registeredAt;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public static int getcId() {
		return cId;
	}
	public static void setcId(int cId) {
		Customer.cId = cId;
	}
	private int age;
    private String city;
    static int cId = 0;	// counter for Customer ID
	public Customer(ArrayList<People> all) {
		cId++;
		this.customerId = "CID" + cId;				// customer ID with it's index number
	      Random random = new Random();
	      int randomPerson = random.nextInt(1000); 	// random number between 0 and 999 - selecting random person from the collection.
//	      System.out.println("Collection lenth is " + all.size() + ", Random person numer: " + randomPerson);
		this.name = all.get(randomPerson).getName();
		this.email = all.get(randomPerson).getEmail();
		registeredAt = Generators.timeGenerator();
		this.age = LocalDateTime.now().getYear() - all.get(randomPerson).getBirthday().getYear();
		this.city = all.get(randomPerson).getCity();
//		System.out.println(customerId + ", " + name + ", " + email + ", " +  registeredAt + ", " + age + ", " + city);
	}
}