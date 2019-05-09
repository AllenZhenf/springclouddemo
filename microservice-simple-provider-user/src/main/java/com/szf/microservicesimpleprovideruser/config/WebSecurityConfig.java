package com.szf.microservicesimpleprovideruser.config;//package com.szf.microservicesimpleprovideruser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @Author Allen_shen
 * @Date: 2019-04-29 11:49
 * @Description: 配置用户登录才能调用
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    volatile

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        //所有的请求，都需要经过HTTP basic认证
        httpSecurity.authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService).passwordEncoder(this.passwordEncoder());
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        //明文编码器，这个是不做任何操作都密码编码器，用做明文测试
        return NoOpPasswordEncoder.getInstance();
    }
}


@Component
class CustomUserDetailsService implements UserDetailsService{

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if("user".equals(username)){
            return new SecurityUser("user","password1","user-role");
        }else if("admin".equals(username)){
            return new SecurityUser("admin","password2","admin-role");
        }

        return null;
    }
}

class SecurityUser implements UserDetails{


    private Long id;
    private String username;
    private String password;
    private String role;

    SecurityUser(String username,String password,String role){
        super();
        this.username=username;
        this.password=password;
        this.role=role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities=new ArrayList<>();
        SimpleGrantedAuthority authority=new SimpleGrantedAuthority(this.role);
        authorities.add(authority);

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}