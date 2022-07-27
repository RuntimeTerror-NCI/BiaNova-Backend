package bianova.bianova.security;

import bianova.bianova.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;
//@Configuration

@EnableWebSecurity
@RequiredArgsConstructor
public class SecConf extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final  BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        System.out.println("configure func");
//        httpSecurity.cors(withDefaults());
        httpSecurity
            .cors(withDefaults())
            .csrf().disable()
            //.and()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/searchRecipes").permitAll()
            .antMatchers(HttpMethod.GET, "/externalApi").permitAll()
            .antMatchers(HttpMethod.GET, "/externalApi/{params}").permitAll()
//            .antMatchers(HttpMethod.POST, "/register").permitAll()
            .antMatchers(HttpMethod.GET, "/recipe").permitAll()
            .antMatchers(HttpMethod.GET, "/login").permitAll()
//            .antMatchers("/externalAPI/.*", "/externalAPI/*").permitAll()
            .antMatchers(HttpMethod.GET, "/externalApiIdList").permitAll()
            .antMatchers(HttpMethod.GET, "/externalApiRandom").permitAll()
            .antMatchers(HttpMethod.GET, "/externalApiId").permitAll()
//            .antMatchers(HttpMethod.GET, "/externalAPI/").permitAll()
            .antMatchers(HttpMethod.GET, "/externalApiId/{id}").permitAll()
            .antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL).permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilter(new JWTAuthenticationFilter(authenticationManager()))
            .addFilter(new JWTAuthorizationFilter(authenticationManager()))
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        httpSecurity.cors();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        System.out.println("configure");
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Methods",
            "Access-Control-Allow-Headers",
            "Access-Control-Allow-Origin", "Authorization",
            "Cache-Control", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
 }
