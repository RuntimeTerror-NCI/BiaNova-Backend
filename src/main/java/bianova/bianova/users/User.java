package bianova.bianova.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class User implements Serializable {
    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private List<Role> roles;
    private Collection<SimpleGrantedAuthority> authorities;

    // private BCryptPasswordEncoder bCryptPasswordEncoder;

    // @Autowired
    public User(){
        authorities = new ArrayList<>();
    }
    public User(String username, String password, String email, List<Role> roles) {
        authorities = new ArrayList<>();
        this.username = username;
        this.email = email;
        this.roles = new ArrayList<>();
        roles.stream().forEach(role -> this.roles.add(role));
        // this.password = bCryptPasswordEncoder.encode(password);
        this.password = password;
        setAuthorities();

    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        // this.password = bCryptPasswordEncoder.encode(password);
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
        setAuthorities();
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Role> addRole(Role role) {
        if (!this.roles.contains(role)) {
            this.roles.add(role);
            setAuthorities();
        }
        return this.roles;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public List<Role> deleteRole(Role role) {
        if (this.roles.contains(role)) {
            this.roles.remove(role);
            setAuthorities();
        }
        return roles;
    }

    public String getPassword() {
        return password;
    }

    private void setAuthorities() {
        authorities = new ArrayList<>();
        roles.forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
    }

    public Collection<SimpleGrantedAuthority> getAuthorities() {
        return authorities;
    }
}
