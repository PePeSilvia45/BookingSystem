package jrp.gradedunit_birdstonkennel_v1.security.config;

import jrp.gradedunit_birdstonkennel_v1.models.user.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * This class is used to configure the websites security
 */
@SuppressWarnings("deprecation")
@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {

    //------------------------------ATTRIBUTES-------------------------------------
    /**
     * Stores an instance of the UserService
     */
    private final AppUserService appUserService;
    /**
     * Stores an instance of the password encoder
     */
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    //--------------------------------METHODS--------------------------------------

    /**
     * this is used to configure the security settings and authentication
     *
     * @param http the security object
     * @return the configured security object
     * @throws Exception throws an exception if unsuccessful
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        String[] permits = {"/", "/home/**", "/public/**", "/user/**",
                "resources/**", "assets/**", "css/**", "img/**", "js/**",
                "scss/**", "vendor/**", "/error", "templates/**", "stripe/events", "/assets/favicon.png"
                };

        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(permits)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                    .formLogin()
                    .loginPage("/user/login")
                    .loginProcessingUrl("/perform_login")
                    .defaultSuccessUrl("/user/login_success")
                    .failureUrl("/user/login?error")
                    .permitAll()
                    .usernameParameter("username")
                    .passwordParameter("password")
                .and()
                    .logout().clearAuthentication(true)
                    .logoutUrl("/user/logout")
                    .logoutSuccessUrl("/user/login?logout")
//                    .deleteCookies("JSESSIONID")
//                    .invalidateHttpSession(true)
                    .permitAll()
        ;

        return http.build();
    }


    /**
     * Used to authenticate the User
     *
     * @return the authentication provider
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }


}
