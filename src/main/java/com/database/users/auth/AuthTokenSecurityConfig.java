package com.database.users.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class AuthTokenSecurityConfig extends WebSecurityConfigurerAdapter {

    private String authHeaderName ="AUTH_API_KEY";

    private String authHeaderValue = "123";



    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        PreAuthTokenHeaderFilter filter = new PreAuthTokenHeaderFilter(authHeaderName);

//        filter.setAuthenticationManager(authentication -> {
//            String principal = (String) authentication.getPrincipal();
//
////            if (!authHeaderValue.equals(principal)) {
////                throw new BadCredentialsException("The API key was not found "
////                        + "or not the expected value.");
////            }
//            authentication.setAuthenticated(true);
//
//            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//            authorities.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
//
//            Authentication auth
//                    = new UsernamePasswordAuthenticationToken("user", "password",authorities);
//
//
//            SecurityContextHolder.getContext().setAuthentication(auth);
//
//            return authentication;
//        });

        httpSecurity.
                antMatcher("/api/**")
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(filter)
                .authorizeRequests()
                .anyRequest()
                .authenticated();

        httpSecurity
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
    }


}
