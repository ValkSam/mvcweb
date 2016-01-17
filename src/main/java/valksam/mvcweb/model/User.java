package valksam.mvcweb.model;

import org.hibernate.validator.constraints.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Valk on 12.01.16.
 */

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @NotEmpty
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birth_date", nullable = false)
    @NotNull
    private Date birthDate;

    @Column(name = "mail", nullable = false, unique = true)
    @Email
    @NotEmpty
    private String mail;

    @Column(name = "role")
    @NotNull
    private Role role;

    @Column(name = "password", nullable = false)
    @NotEmpty
    @Length(min = 5)
    private String password;

    public User(Integer id, String name, Date birthDate, String mail, Role role, String password) {
        super(id);
        this.name = name;
        this.birthDate = birthDate;
        this.mail = mail;
        this.role = role;
        this.password = password;
    }

    public User() {
    }

    public User(Integer id) {
        super(id);
    }

    public boolean isNew(){
        return id == null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setRole(Integer role) {
        this.role = Role.values()[role];
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getMail() {
        return mail;
    }

    public Role getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", mail='" + mail + '\'' +
                ", role=" + role +
                ", password='" + password + '\'' +
                '}';
    }

}