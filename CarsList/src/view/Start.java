package view;

import java.util.List;
import java.util.Scanner;

import controller.CarHelper;
import model.Car;

public class Start {

		static Scanner in = new Scanner(System.in);
		static CarHelper ch = new CarHelper();
		
		public static void main(String[] args) {
			runMenu();
		}

		public static void runMenu() {
			boolean goAgain = true;
			System.out.println("--- Welcome to our awesome shopping list! ---");
			while (goAgain) {
				System.out.println("*  Select an item:");
				System.out.println("*  1 -- Add an item");
				System.out.println("*  2 -- Edit an item");
				System.out.println("*  3 -- Delete an item");
				System.out.println("*  4 -- View the list");
				System.out.println("*  5 -- Exit the awesome program");
				System.out.print("*  Your selection: ");
				int selection = in.nextInt();
				in.nextLine();

				if (selection == 1) {
					addCar();
				} else if (selection == 2) {
					editCar();
				} else if (selection == 3) {
					deleteCar();
				} else if (selection == 4) {
					viewCars();
				} else {
					ch.cleanUp();
					System.out.println("   Goodbye!   ");
					goAgain = false;
				}

			}

		}

		private static void addCar() {
			System.out.print("Enter a make: ");
			String make = in.nextLine();
			System.out.print("Model: ");
			String model = in.nextLine();
			System.out.print("Year: ");
			String year = in.nextLine();
			System.out.print("Engine: ");
			String engine = in.nextLine();

			Car toAdd = new Car(make, model, Integer.parseInt(year), engine);
			ch.insertCar(toAdd);
		}

		private static void deleteCar() {
			System.out.print("Enter the make to delete: ");
			String make = in.nextLine();
			
			System.out.print("Model: ");
			String model = in.nextLine();
			
			System.out.print("Year: ");
			String year = in.nextLine();
			
			System.out.print("Engine size(V4, V6, etc...): ");
			String engine = in.nextLine();

			Car toDelete = new Car(make, model, Integer.parseInt(year), engine);
			ch.deleteCar(toDelete);
		}

		private static void editCar() {
			System.out.println("How would you like to search? ");
			System.out.println("1 : Search by Make");
			System.out.println("2 : Search by Model");
			System.out.println("3 : Search by Year");
			int searchBy = in.nextInt();
			in.nextLine();
			List<Car> foundItems;
			if (searchBy == 1) {
				System.out.print("Enter the make: ");
				String make = in.nextLine();
				foundItems = ch.searchForCarByMake(make);
			} else if (searchBy == 2) {
				System.out.print("Enter the model: ");
				String model = in.nextLine();
				foundItems = ch.searchForCarByModel(model);
			} else if (searchBy == 3) {
				System.out.print("Enter the year: ");
				String year = in.nextLine();
				foundItems = ch.searchForCarByYear(year);
			} else if (searchBy == 4) {
				System.out.print("Enter the engine size(V4, V6, etc...): ");
				String engine = in.nextLine();
				foundItems = ch.searchForCarByEngine(engine);
			} else {
				System.out.print("Invalid choice.");
				return;
			}

			if (!foundItems.isEmpty()) {
				System.out.println("Found Results.");
				for (Car c : foundItems) {
					System.out.println(c.getId() + " : " + c.returnCarInfo());
				}
				System.out.print("Which ID to edit: ");
				int idToEdit = in.nextInt();

				Car toEdit = ch.searchForCarById(idToEdit);
				System.out.println("Retrieved: " + toEdit.returnCarInfo());
				System.out.println("1 : Update Make");
				System.out.println("2 : Update Model");
				System.out.println("3 : Update Year");
				System.out.println("4 : Update Engine size");
				int update = in.nextInt();
				in.nextLine();

				if (update == 1) {
					System.out.print("New Make: ");
					String newMake = in.nextLine();
					toEdit.setMake(newMake);
				} else if (update == 2) {
					System.out.print("New Model: ");
					String newModel = in.nextLine();
					toEdit.setModel(newModel);
				} else if (update == 3) {
					System.out.print("New Year: ");
					String newYear = in.nextLine();
					toEdit.setYear(Integer.parseInt(newYear));
				} else {
					System.out.print("Invalid choice.");
					return;
				}
				ch.updateCar(toEdit);

			} else {
				System.out.println("---- No results found");
			}

		}


		private static void viewCars() {
			List<Car> allCars = ch.showAllCars();
			for(Car car : allCars){
				System.out.println(car.returnCarInfo());
			}

		}

	}
