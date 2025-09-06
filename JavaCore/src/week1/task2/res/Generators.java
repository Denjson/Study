package week1.task2.res;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Generators {
	static int rowsQ = 1000;	// quantity of rows in source file
	
	public static LocalDateTime timeGenerator() {
	      Random random = new Random();
	      int year = 1 + random.nextInt(25); 	// random number between 1 and 25
	      int month = 1 + random.nextInt(12);
	      int day = 1 + random.nextInt(28);
	      LocalDateTime date = LocalDateTime.of(year, month, day, 0, 0, 0, 0);	
		return date;
	}
	
	public static ArrayList<People> generate() throws IOException {
		ArrayList<People> allPeople = new ArrayList<>();
		System.out.println("Please wait for 5 seconds while getting data from external source (on-line shop)...");
		try {
			List<String> names = new ArrayList<String>();
			List<String> surnames = new ArrayList<String>();
			List<String> countries = new ArrayList<String>();
			List<String> languages = new ArrayList<String>();
			List<String> education = new ArrayList<String>();
			List<String> emails = new ArrayList<String>();
			List<String> phones = new ArrayList<String>();
			List<String> cities = new ArrayList<String>();
			// Link on Google Sheets table:
			// https://docs.google.com/spreadsheets/d/1-t5IkZhyxkP7luunBuSYZHLRY3QxEkrADaX9ExQFMz0/edit#gid=0
			URL oracle = new URL(
					"https://docs.google.com/spreadsheets/d/e/2PACX-1vTpBahXQXwwUT1KBXQ_imymoXPZ4IejsSTH-93uXt4kO0NG4noEUPEIFccF6lqxY1ox7U1rwuIrCsNc/pub?gid=0&single=true&output=tsv"); // TSV
			BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
			String inputLine;
			String[] fullname;
			while ((inputLine = in.readLine()) != null) {
				fullname = inputLine.split("\t");
				names.add(fullname[0]);
				surnames.add(fullname[1]);
				if (!fullname[2].equals("0")) // if value is zero, then no country in this cell
					countries.add(fullname[2]);
				if (!fullname[3].equals("0")) // if value is zero, then no language in this cell
					languages.add(fullname[3]);
				if (!fullname[4].equals("0")) // if value is zero, then no education in this cell
					education.add(fullname[4]);
				if (!fullname[5].equals("0")) // if value is zero, then no email in this cell
					emails.add(fullname[5]);
				if (!fullname[6].equals("0")) // if value is zero, then no phone code in this cell
					phones.add(fullname[6]);
				if (!fullname[7].equals("0")) // if value is zero, then no phone code in this cell
					cities.add(fullname[7]);
//				System.out.println(inputLine);
			}
			in.close();
			Random random = new Random();
			LocalDate endDate = LocalDate.now();
			LocalDate startDate = endDate.minusYears(100); // 100 years is good life time for human being
			List<LocalDate> birthDays = startDate.datesUntil(endDate).collect(Collectors.toList()); // creating a list of birthdays between two dates
			char[] sex = { 'M', 'F', 'U' }; // array for sex
			char[] maritalStatus = { 'N', 'Y' };
			emails = EmailGenerators.generate(emails, rowsQ); // getting List with ready e-mails, size is 1 million (can be custom size)
			for (int i = 0; i < rowsQ; i++) {	// adding one million of rows
				int nameIndex = random.nextInt(names.size()); // getting random name from full list
				int surnameIndex = random.nextInt(surnames.size()); // getting random surname from full list
				int dateIndex = random.nextInt(birthDays.size()); // getting random date of Birth from full list
				int sexIndex = random.nextInt(sex.length); // getting random sex from array list
				int maritalIndex = random.nextInt(maritalStatus.length); // getting random marital status from array list
				if (endDate.getYear() - birthDays.get(dateIndex).getYear() < 18) {
					maritalIndex = 0;
//					System.out.println("Cannot get married!");
				}
				int qtyChildren = random.nextInt(12);	// quantity of children: 0-11
				if (endDate.getYear() - birthDays.get(dateIndex).getYear() < 14) {
					qtyChildren = 0;
				}
				int countryIndex = random.nextInt(countries.size()); // getting random country from array list
				int languageIndex = random.nextInt(languages.size()); // getting random language from array list
				int educationIndex = random.nextInt(education.size()); // getting random education from array list
				int annualRevenue = random.nextInt(100) * 1000; // annual salary
				float taxRate = ((float) random.nextInt(50)) / 100; // taxes rate
				int phoneIndex = random.nextInt(phones.size()); // getting random phone code
				String phone = "+(" + phones.get(phoneIndex) + ")" + random.nextInt(Integer.MAX_VALUE);
				int cityIndex = random.nextInt(cities.size()); // getting random surname from full list
//				System.out.println("Person " + (i + 1) + " = " + names.get(nameIndex) + " " + surnames.get(surnameIndex)
//						+ ", Date of birth: " + birthDays.get(dateIndex) + ", Sex: " + sex[sexIndex] + ", Married: "
//						+ maritalStatus[maritalIndex] + ", Children: " + qtyChildren + ", Country: "
//						+ countries.get(countryIndex) + ", Language: " + languages.get(languageIndex) + ", Education: "
//						+ education.get(educationIndex) + ", e-mail: " + emails.get(i) + ", phone: " + phone
//						+ ", Salary: " + annualRevenue + ", Tax: " + taxRate);
				People person = new People(names.get(nameIndex), surnames.get(surnameIndex), birthDays.get(dateIndex),
						sex[sexIndex], maritalStatus[maritalIndex], qtyChildren, countries.get(countryIndex),
						languages.get(languageIndex), education.get(educationIndex), emails.get(i), phone,
						annualRevenue, taxRate, cities.get(cityIndex)); // Object of Entity
//				System.out.println(1 + i +" " + person);
				allPeople.add(person);	//

			}
		} finally {

		}
		return (ArrayList<People>) allPeople;
	} 
}