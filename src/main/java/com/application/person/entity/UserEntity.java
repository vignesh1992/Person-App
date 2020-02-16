package com.application.person.entity;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.application.person.model.Authorities;
import com.application.person.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "User")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String userName;

    private String password;

    @Builder.Default
    private boolean active = true;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    public User toUser() {
        List<Authorities> authoritiesList = roles.stream().map(r -> Authorities.valueOf(r))
                .collect(Collectors.toList());

        return User.builder().userName(this.userName).active(this.active).roles(authoritiesList).build();
    }
    
    

}
