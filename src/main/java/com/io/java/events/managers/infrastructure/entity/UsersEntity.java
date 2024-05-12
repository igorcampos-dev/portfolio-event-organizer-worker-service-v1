package com.io.java.events.managers.infrastructure.entity;

import com.io.java.events.managers.infrastructure.entity.field.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_USER", indexes = {
        @Index(name = "IDX_ID", columnList = "PK_ID", unique = true),
        @Index(name = "IDX_NAME", columnList = "USR_ST_NAME", unique = true)
})
public class UsersEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_ID")
    @Comment("Id único do usuário")
    private Long id;

    @Column(name = "USR_ST_NAME", unique = true)
    @Comment("Nome do usuário")
    private String name;

    @Column(name = "USR_ST_EMAIL")
    @Comment("Email do usuário")
    private String email;

    @Column(name = "USR_ST_PASSWORD")
    @Comment("Senha do usuário")
    private String password;

    @Column(name = "USR_ST_ROLE")
    @Enumerated(EnumType.STRING)
    @Comment("Role do usuário")
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        if (this.role == Role.ADMIN) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            authorities.add(new SimpleGrantedAuthority("ROLE_RH"));
        }

        if (this.role == Role.ADMIN || this.role == Role.RH) {
            authorities.add(new SimpleGrantedAuthority("ROLE_RH"));
        }

        if (this.role == Role.ADMIN || this.role == Role.USER) {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
