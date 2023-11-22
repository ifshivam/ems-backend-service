package com.emp.mgmt.pojos;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String email;

    String name;

    @Temporal(TemporalType.DATE)
    Date dob;

    String password;

    String contact;

    Boolean isDeleted = false;

    Boolean isActive = false;

    Boolean isExpired = false;

    Boolean isCredentialsExpired = false;

    Boolean isLocked = false;

    @Temporal(TemporalType.TIMESTAMP)
    Date dateCreated;

    @Temporal(TemporalType.TIMESTAMP)
    Date lastUpdated;

    String createdBy;

    String updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    Date password_last_updated;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    List<GrantedAuthorityImpl> grantedAuthorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isCredentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    @PrePersist
    protected void onCreate() {
        dateCreated = new Date();
        createdBy = email;
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdated = new Date();
        updatedBy = email;
    }

    public void setPassword(String password) {
        password_last_updated = new Date();
        this.password = password;
    }
}
