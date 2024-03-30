package entities;

public class Employee {
	private String name;
	private String email;
	private Float salary;

	public Employee(String name, String email, Float salary) {
		setName(name);
		setEmail(email);
		setSalary(salary);
	}

	public void setName(String name) {
		this.name = name.toLowerCase().trim();
	}

	public void setEmail(String email) {
		this.email = email.toLowerCase().trim();
	}

	public void setSalary(Float salary) {
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public Float getSalary() {
		return salary;
	}
}
