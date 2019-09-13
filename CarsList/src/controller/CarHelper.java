package controller;

import java.util.List;

import javax.persistence.*;

import model.Car;

public class CarHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("CarList");

	public void insertCar(Car ca) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(ca);
		em.getTransaction().commit();
		em.close();
	}

	public List<Car> showAllCars() {
		EntityManager em = emfactory.createEntityManager();
		List<Car> query = em.createQuery("SELECT i FROM Car i").getResultList();
		return query;
	}

	public void deleteCar(Car toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Car> typedQuery = em.createQuery(
				"SELECT ca from Car ca where ca.make = :selectedMake and ca.model = :selectedModel and ca.year = :selectedYear and ca.engine = :selectedEngine",
				Car.class);
		// Substitute parameter with actual data from the toDelete item
		typedQuery.setParameter("selectedMake", toDelete.getMake());
		typedQuery.setParameter("selectedModel", toDelete.getModel());
		typedQuery.setParameter("selectedYear", toDelete.getYear());
		typedQuery.setParameter("selectedEngine", toDelete.getEngine());
		// we only want one result
		typedQuery.setMaxResults(1);

		// get the result and save it into a new list item
		Car result = typedQuery.getSingleResult();

		// remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();

	}

	public void updateCar(Car toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

	
	public Car searchForCarById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		Car found = em.find(Car.class, idToEdit);
		em.close();
		return found;
	}

	public List<Car> searchForCarByMake(String makeName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Car> typedQuery = em.createQuery("select ca from Car ca where ca.make = :selectedMake", Car.class);
		typedQuery.setParameter("selectedMake", makeName);

		List<Car> found = typedQuery.getResultList();
		em.close();
		return found;
	}

	public List<Car> searchForCarByModel(String modelName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Car> typedQuery = em.createQuery("select ca from Car ca where ca.model = :selectedModel", Car.class);
		typedQuery.setParameter("selectedModel", modelName);
		List<Car> found = typedQuery.getResultList();
		em.close();
		return found;
	}
	
	public List<Car> searchForCarByYear(String yearName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Car> typedQuery = em.createQuery("select ca from Car ca where ca.year = :selectedYear", Car.class);
		typedQuery.setParameter("selectedYear", Integer.parseInt(yearName));
		List<Car> found = typedQuery.getResultList();
		em.close();
		return found;
	}
	
	public List<Car> searchForCarByEngine(String makeEngine) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Car> typedQuery = em.createQuery("select ca from Car ca where ca.engine = :selectedEngine", Car.class);
		typedQuery.setParameter("selectedEngine", makeEngine);

		List<Car> found = typedQuery.getResultList();
		em.close();
		return found;
	}
	
	
	
	public void cleanUp(){
		emfactory.close();
	}

}
