package com.ey.ejercicio.entities;

import com.ey.ejercicio.dtos.PhoneDTO;
import com.ey.ejercicio.dtos.UserDTO;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Date created;

    @Column(nullable = false)
    private Date modified;

    @Column(name = "last_login", nullable = false)
    private Date lastLogin;

    @Column(nullable = false)
    private String token;

    @Column(name = "isactive", nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phone> phones;

    public void addPhone(Phone phone) {
        phones.add(phone);
        phone.setUser(this);
    }

    public void removePhone(Phone phone) {
        phones.remove(phone);
        phone.setUser(null);
    }

    public void setIsActive(boolean b) {
        isActive = b;
    }

    public User mapToUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setCreated(new Date());
        user.setModified(new Date());
        user.setLastLogin(new Date());
        user.setToken("token");
        user.setIsActive(true);
        List<Phone> phones = new ArrayList<>();
        for (PhoneDTO phoneDTO : userDTO.getPhones()) {
            Phone phone = new Phone();
            phone.setNumber(phoneDTO.getNumber());
            phone.setCityCode(phoneDTO.getCityCode());
            phone.setCountryCode(phoneDTO.getCountryCode());
            phones.add(phone);
        }
        user.setPhones(phones);
        return user;
    }
}