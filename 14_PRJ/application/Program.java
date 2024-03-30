package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Employee;

public class Program {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter full file path: ");

		try (BufferedReader br = new BufferedReader(new FileReader(sc.nextLine()))) {
			List<Employee> list = new ArrayList<>();

			String line = br.readLine();
			while (line != null && line != "") {
				String[] fields = line.split(",");

				String name = fields[0];
				String email = fields[1];
				Float salary = Float.parseFloat(fields[2]);

				list.add(new Employee(name, email, salary));
				line = br.readLine();
			}

			System.out.print("Enter salary: $");
			Float salary = sc.nextFloat();

			Comparator<Employee> comp = (E, e) -> E.getEmail().compareTo(e.getEmail());

			
			System.out.println("\nEmail of people whose salary is more than " + String.format("%.2f", salary) + ":");
			list.stream()
					.filter(e -> e.getSalary() > salary)
					.sorted(comp)
					.map(e -> e.getEmail())
					.forEach(System.out::println);

			Float withLetter = list.stream()
					.filter(e -> e.getName()
					.startsWith("m"))
					.map(e -> e.getSalary())
					.reduce(0F, (S, s) -> S + s);
			
			String sal = String.format("%.2f", withLetter);
			System.out.println("\nSum of salary of people those name starts with 'M': $" + sal);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

		sc.close();
	}
}
