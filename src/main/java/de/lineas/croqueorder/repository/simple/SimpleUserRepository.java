package de.lineas.croqueorder.repository.simple;

import java.util.List;

import de.lineas.croqueorder.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Simple repository interface for {@link de.lineas.croqueorder.domain.User} instances. The interface is used to declare so called query methods,
 * methods to retrieve single entities or collections of them.
 * 
 * @author Oliver Gierke
 */
public interface SimpleUserRepository extends CrudRepository<User, Long> {

	/**
	 * Find all users with the given lastname. This method will be translated into a query by constructing it directly
	 * from the method name as there is no other query declared.
	 * 
	 * @param lastname
	 * @return
	 */
	List<User> findByLastname(String lastname);

	/**
	 * Returns all users with the given firstname. This method will be translated into a query using the one declared in
	 * the {@link Query} annotation declared one.
	 * 
	 * @param firstname
	 * @return
	 */
	@Query("select u from User u where u.firstname = :firstname")
	List<User> findByFirstname(String firstname);

	/**
	 * Returns all users with the given name as first- or lastname. Makes use of the {@link Param} annotation to use named
	 * parameters in queries. This makes the query to method relation much more refactoring safe as the order of the
	 * method parameters is completely irrelevant.
	 * 
	 * @param name
	 * @return
	 */
	@Query("select u from User u where u.firstname = :name or u.lastname = :name")
	List<User> findByFirstnameOrLastname(@Param("name") String name);




}
