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
        //super(id);
        this.name = name;
        this.birthDate = birthDate;
        this.mail = mail;
        this.role = role;
        this.password = password;
    }

    public User() {
    }

    public boolean isNew(){
        return id == null;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", mail='" + mail + '\'' +
                ", role=" + role +
                ", password='" + password + '\'' +
                '}';
    }
}
