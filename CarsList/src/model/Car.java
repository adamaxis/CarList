package model;

import javax.persistence.*;

@Entity
@Table(name="car_types")
public class Car {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	@Column(name="MAKE")
	private String make;
	@Column(name="MODEL")
	private String model;
	@Column(name="YEAR")
	private int year;
	@Column(name="ENGINE")
	private String engine;
	
	public Car() {
		super();
	}
	
	public Car(String make, String model, int year, String engine) {
		super();
		this.make = make;
		this.model = model;
		this.year = year;
		this.engine = engine;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	public String getEngine() {
		return model;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String returnCarInfo() {
		return "ID:" + id + "\nMake: " + make + "\nModel: " + model + "\nYear: " + year + "\nEngine size: " + engine + "\n";
	}
	
	
	
}
