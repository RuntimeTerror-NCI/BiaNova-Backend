package bianova.bianova.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class User implements Serializable {
    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private List<Role> roles;
    private Collection<SimpleGrantedAuthority> authorities;
//    private Collection<String> savedRecipes;
    private Collection<HashMap> savedRecipesObjects;

    // private BCryptPasswordEncoder bCryptPasswordEncoder;

    // @Autowired
    public User(){
        authorities = new ArrayList<>();
        System.out.println("const 0");
        if (roles == null) {
            roles = new ArrayList<>();
        }
//        savedRecipes = new ArrayList<String>();
        savedRecipesObjects = new ArrayList<HashMap>();
    }
    public User(String username, String password, String email, List<Role> roles) {
        System.out.println("const 1");
        authorities = new ArrayList<>();
        this.username = username;
        this.email = email;
        this.roles = new ArrayList<>();
        roles.stream().forEach(role -> this.roles.add(role));
        // this.password = bCryptPasswordEncoder.encode(password);
        this.password = password;
        setAuthorities();
//        this.savedRecipes = new ArrayList<String>();
        this.savedRecipesObjects = new ArrayList<HashMap>();
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

//    public Collection<String> getSavedRecipes() {
//        return savedRecipes;
//    }

    public String getEmail() {
        return email;
    }

//    public void addRecipe(String recipe) {
//        savedRecipes.add(recipe);
//    }

    public void addRecipe(HashMap recipe) {
            savedRecipesObjects.add(recipe);
    }
    public void deleteRecipe(int recipeID) {
        for (HashMap recipe: this.savedRecipesObjects) {
            if (Integer.parseInt(recipe.get("id").toString()) == recipeID) {
                this.savedRecipesObjects.remove(recipe);
            }
        }
    }
    public void setSavedRecipes(Collection<HashMap> recipes) {
        recipes.stream().forEach(this::addRecipe);
    }

    public Collection<HashMap> getSavedRecipesObjects() {
        return savedRecipesObjects;
    }

    public void setSavedRecipesObjects(Collection<HashMap> savedRecipesObjects) {
        this.savedRecipesObjects = savedRecipesObjects;
    }

}
