package com.ey.ejercicio.entities;

import com.ey.ejercicio.dtos.PhoneDTO;
import com.ey.ejercicio.dtos.UserDTO;
import com.ey.ejercicio.dtos.UserResponseDTO;
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

    /**
     * Agrega un teléfono al usuario.
     * @param phone El teléfono a agregar.
     */
    public void addPhone(Phone phone) {
        phones.add(phone);
        phone.setUser(this);
    }

    /**
     * Remueve un teléfono del usuario.
     * @param phone El teléfono a remover.
     */
    public void removePhone(Phone phone) {
        phones.remove(phone);
        phone.setUser(null);
    }

    /**
     * Setea el estado de activación del usuario.
     * @param b El estado a setear.
     */
    public void setIsActive(boolean b) {
        isActive = b;
    }

    /**
     * Mapea los atributos del DTO de usuario a un objeto usuario.
     * @param userDTO El DTO de usuario a mapear.
     * @return El objeto usuario mapeado.
     */
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

    /**
     * Mapea los atributos del usuario a un DTO de respuesta de usuario.
     * @return El DTO de respuesta de usuario mapeado.
     */
    public UserResponseDTO mapToUserResponseDTO() {
        return UserResponseDTO.builder()
                .id(id)
                .created(created)
                .modified(modified)
                .lastLogin(lastLogin)
                .token(token)
                .isActive(isActive)
                .build();
    }

}