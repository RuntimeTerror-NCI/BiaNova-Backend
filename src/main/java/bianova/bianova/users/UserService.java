package bianova.bianova.users;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Role createRole(Role role) {
        return roleRepo.save(role);
    }

    public Role findRole(String name) {
        Optional<Role> role = roleRepo.findRoleByName(name);
        if (role.isPresent()) {
            return role.get();
        } else {
            throw new NoRoleFoundException();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findUserByUsername(username);
        if (!user.isPresent()) {
            log.error("user not found");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("user found found");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities = user.get().getAuthorities();
//        user.get().getRoles().forEach(role -> {
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        });
        return new org.springframework.security.core.userdetails.User(
            user.get().getUsername(),
            user.get().getPassword(),
            authorities
        );
    }


    public Role updateRole(Role updatedRole) {
        Role role = findRole(updatedRole.getName());
        updatedRole.setId(role.getId());
        roleRepo.save(updatedRole);
        return updatedRole;
    }

    public Role deleteRole(Role deletedRole) {
        roleRepo.delete(deletedRole);
        return deletedRole;
    }

    public User createUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public User findUser(String username) {
        Optional<User> user = userRepo.findUserByUsername(username);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new NoUserFoundException();
        }
    }

    public User updateUser(User updatedUser) {
        User user = findUser(updatedUser.getUsername());
        updatedUser.setId(user.getId());
        userRepo.save(updatedUser);
        return updatedUser;
    }

    public User deleteUser(User deletedUser) {
        userRepo.delete(deletedUser);
        return deletedUser;
    }

    public User deleteUserByUsername(String username) {
        Optional<User> foundUser = userRepo.findUserByUsername(username);
        if (foundUser.isPresent()) {
            userRepo.delete(foundUser.get());
            return foundUser.get();
        }
        return null;
    }

    public User addRolesToUser(User user, List<Role> roles) {
        roles.stream().forEach(role ->
            user.addRole(role)
        );
        return user;
    }

    public User removeRolesFromUser(User user, List<Role> roles) {
        roles.stream().forEach(role ->
            user.deleteRole(role)
        );
        return user;

    }
}
