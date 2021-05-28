package al.ecommerce.shop.auth;

import al.ecommerce.shop.domain.role.model.ERole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final UserDetailsService jwtUserDetailsService;

    private final JwtRequestFilter jwtRequestFilter;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(List.of("Origin", "X-Requested-With", "Accept", "Access-Control-Expose-Headers","Authorization", "Content-Type","Access-Control-Allow-Headers", "Access-Control-Allow-Credentials", "Access-Control-Allow-Origin"));
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT", "OPTIONS", "PATCH", "DELETE"));
        corsConfiguration.setAllowCredentials(true);
// We don't need CSRF for this example
        httpSecurity.cors().configurationSource(request -> corsConfiguration).and().csrf().disable()
// dont authenticate this particular request
                .authorizeRequests()
                .antMatchers("/authenticate").permitAll()
                .antMatchers(HttpMethod.GET, "/user/users").permitAll()
                .antMatchers(HttpMethod.POST, "/upload").hasAuthority(ERole.ADMIN.getName())
                .antMatchers(HttpMethod.GET, "/user/admin/users").hasAuthority(ERole.ADMIN.getName())
                .antMatchers(HttpMethod.POST,"/user/admin/create-user").hasAuthority(ERole.ADMIN.getName())
                .antMatchers(HttpMethod.PUT, "/user/admin/update-user").hasAuthority(ERole.ADMIN.getName())
                .antMatchers(HttpMethod.GET, "/product/product-list").permitAll()
                .antMatchers(HttpMethod.GET, "/product/admin/product-list").permitAll()
                .antMatchers(HttpMethod.POST,"/product/admin/create-product").hasAuthority(ERole.ADMIN.getName())
                .antMatchers(HttpMethod.POST,"/product/admin/update-product/{id}").hasAuthority(ERole.ADMIN.getName())
                .antMatchers(HttpMethod.POST,"/product/admin/delete-product/{id}").hasAuthority(ERole.ADMIN.getName())
                .antMatchers(HttpMethod.GET, "/category/categories").permitAll()
                .antMatchers(HttpMethod.GET, "/category/admin/categories").hasAuthority(ERole.ADMIN.getName())
                .antMatchers(HttpMethod.POST,"/category/admin/create-category").hasAuthority(ERole.ADMIN.getName())
                .antMatchers(HttpMethod.PUT, "/category/admin/update-category/{id}").hasAuthority(ERole.ADMIN.getName())
                .antMatchers(HttpMethod.PUT, "/category/admin/delete-category/{id}").hasAuthority(ERole.ADMIN.getName()).
        anyRequest().fullyAuthenticated()
      .and()
                //logout will log the user out by invalidate session.
                .logout().permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/api/user/logout", "POST")).and()
                //login form and path
                .formLogin().loginPage("/api/user/login").and()
                //enable basic authentication. Http header: basis username:password
                .httpBasic().and()
                //Cross side request forgery.
                .csrf().disable();

// Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}