package bianova.bianova.users;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepo extends MongoRepository<Role, String> {

    public Optional<Role> findRoleByName(String name);

}
