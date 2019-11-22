package fr.gtm.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClientRepository extends JpaRepository<User, Long> {
	
	User getByNom(String nom);
	
	@Query(value = "SELECT digest FROM Users WHERE user = ?1",
			nativeQuery = true)
	String getDigestByNom(String nom);
	
	@Query(value = "INSERT INTO authentification.users (user, password, role, digest) VALUES (?1, ?2, ?3, ?4)", nativeQuery = true)
	@Modifying
	@Transactional
	void createUser(String user,String password,String role, String digest);
}
