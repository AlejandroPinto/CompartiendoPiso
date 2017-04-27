package es.urjc.code.daw.compartiendoPiso.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByFirstLastNameAndSecondLastName(String firstLastName, String secondLastName);
	User findByName(String name);
	User findByEmail(String email);


}
