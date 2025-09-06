package week1.task2.res;

import java.time.LocalDate;

public class People {
	   private int id;
	   private String name;
	   private String surname;	   
	   private LocalDate birthday;
	   private char sex;
	   private char married;
	   private int children;
	   private String country;
	   private String language;
	   private String education;
	   private String email;
	   private String phone;
	   private int salary;
	   private float tax;
	   private String city;
	public People(String name, String surname, LocalDate birthday, char sex, char married, int children, String country,
			String language, String education, String email, String phone, int salary, float tax, String city) {
		super();
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
		this.sex = sex;
		this.married = married;
		this.children = children;
		this.country = country;
		this.language = language;
		this.education = education;
		this.email = email;
		this.phone = phone;
		this.salary = salary;
		this.tax = tax;
		this.city = city;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", surname=" + surname + ", birthday=" + birthday + ", sex="
				+ sex + ", married=" + married + ", children=" + children + ", country=" + country + ", language="
				+ language + ", education=" + education + ", email=" + email + ", phone=" + phone + ", salary=" + salary
				+ ", tax=" + tax + ", city=" + city + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
	}
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	public char getMarried() {
		return married;
	}
	public void setMarried(char married) {
		this.married = married;
	}
	public int getChildren() {
		return children;
	}
	public void setChildren(int children) {
		this.children = children;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public float getTax() {
		return tax;
	}
	public void setTax(float tax) {
		this.tax = tax;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

}