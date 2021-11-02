package com.example.springsecurity.mock;

import java.util.List;
import java.util.Optional;

import com.example.springsecurity.auth.ApplicationUser;
import com.example.springsecurity.auth.ApplicationUserDAO;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import static com.example.springsecurity.security.ApplicationUserRole.ADMIN;
import static com.example.springsecurity.security.ApplicationUserRole.STUDENT;
import static com.example.springsecurity.security.ApplicationUserRole.TRAINEE;


@RequiredArgsConstructor
@Repository("fakeRepository")
public class MockApplicationUserDaoService implements ApplicationUserDAO {

    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers().stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        return Lists.newArrayList(
                ApplicationUser.builder()
                        .username("ush")
                        .password(passwordEncoder.encode("student"))
                        .grantedAuthorities(STUDENT.getGrantedAuthorities())
                        .isAccountNonExpired(true)
                        .isAccountNonLocked(true)
                        .isCredentialsNonExpired(true)
                        .isEnabled(true)
                        .build(),
                ApplicationUser.builder()
                        .username("ref")
                        .password(passwordEncoder.encode("admin"))
                        .grantedAuthorities(ADMIN.getGrantedAuthorities())
                        .isAccountNonExpired(true)
                        .isAccountNonLocked(true)
                        .isCredentialsNonExpired(true)
                        .isEnabled(true)
                        .build(),
                ApplicationUser.builder()
                        .username("tom")
                        .password(passwordEncoder.encode("trainee"))
                        .grantedAuthorities(TRAINEE.getGrantedAuthorities())
                        .isAccountNonExpired(true)
                        .isAccountNonLocked(true)
                        .isCredentialsNonExpired(true)
                        .isEnabled(true)
                        .build()
        );
    }
}
