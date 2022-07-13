package bianova.bianova.users;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {

    public Optional<User> findUserByUsername(String username);

    public Long deleteUserByUsername(String username);

}