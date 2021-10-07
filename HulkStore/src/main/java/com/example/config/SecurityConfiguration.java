package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    final DataSource dataSource;
    private final AccessDeniedHandler accessDeniedHandler;

    private String homePath ="/home";

    @Autowired
    public SecurityConfiguration(AccessDeniedHandler accessDeniedHandler, DataSource dataSource) {
        this.dataSource = dataSource;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) 
      throws Exception {
        auth
          .inMemoryAuthentication()
          .withUser("user").password(passwordEncoder().encode("password")).roles("USER")
          .and()
          .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");

        // Database authentication
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery("select username, password, state from hs_user where username=?")
                .authoritiesByUsernameQuery("select u.username, r.name from hs_user u inner join user_role ur on(u.id=ur.id) inner join hs_role r on(ur.id_rol=r.id_rol) where u.username=?")
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder());
    }
    
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers( homePath,"/new", "/edit/**", "/delete/**","/buy/**","/orderList","/order/**","/userList","/user/**","/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl(homePath)
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);

        http.logout().logoutSuccessUrl(homePath);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}