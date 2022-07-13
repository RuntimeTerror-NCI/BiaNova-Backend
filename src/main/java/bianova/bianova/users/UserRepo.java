package bianova.bianova.users;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface UserRepo extends MongoRepository<User, String> {

    public Optional<User> findUserByUsername(String username);

    public Long deleteUserByUsername(String username);

}