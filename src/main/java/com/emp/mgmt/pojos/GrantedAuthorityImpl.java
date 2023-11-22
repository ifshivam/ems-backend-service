package com.emp.mgmt.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "role")
public class GrantedAuthorityImpl implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull(message = "Authority Can't be Null")
    String authority;

    @JsonIgnore
    @ManyToMany(mappedBy = "grantedAuthorities", cascade = CascadeType.ALL)
    List<User> users;

    @Override
    public String getAuthority() {
        return authority;
    }
}

