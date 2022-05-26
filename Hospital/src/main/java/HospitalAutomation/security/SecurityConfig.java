package HospitalAutomation.security;

import HospitalAutomation.security.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/login").permitAll()
                .antMatchers(HttpMethod.GET, "/").hasAnyRole(Role.Admin.name(),Role.User.name())
                .antMatchers(HttpMethod.POST, "/people/*").hasRole(Role.Admin.name())
                .antMatchers(HttpMethod.POST, "/diagnosis/*").hasRole(Role.Admin.name())
                .antMatchers(HttpMethod.POST, "/wards/*").hasRole(Role.Admin.name())
                .antMatchers(HttpMethod.DELETE, "/people/*").hasRole(Role.Admin.name())
                .antMatchers(HttpMethod.DELETE, "/diagnosis/*").hasRole(Role.Admin.name())
                .antMatchers(HttpMethod.DELETE, "/wards/*").hasRole(Role.Admin.name())
                .antMatchers(HttpMethod.PUT, "/people/edit/*").hasRole(Role.Admin.name())
                .antMatchers(HttpMethod.PUT, "/diagnosis/edit/*").hasRole(Role.Admin.name())
                .antMatchers(HttpMethod.PUT, "/wards/edit/*").hasRole(Role.Admin.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("admin")
                        .password(passwordEncoder().encode("admin"))
                        .roles(Role.Admin.name())
                        .build(),
                User.builder()
                        .username("user")
                        .password(passwordEncoder().encode("user"))
                        .roles(Role.User.name())
                        .build()
        );
    }

    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
}
