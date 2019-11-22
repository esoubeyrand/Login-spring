package fr.gtm.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<User, Long> {
	
	User getByNom(String nom);
	
	@Query(value = "SELECT digest FROM Users WHERE user = ?1",
			nativeQuery = true)
	String getDigestByNom(String nom);
}
