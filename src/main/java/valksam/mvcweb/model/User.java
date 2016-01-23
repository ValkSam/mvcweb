package valksam.mvcweb.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Valk on 12.01.16.
 */

@Entity
@Table(name = "users")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User extends BaseEntity {
    //@NotEmpty
    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    //@NotNull
    private Date birthDate;

    @Column(name = "mail", unique = true)
    @Email
    //@NotEmpty
    private String mail;

    @Column(name = "role")
    //@NotNull
    @Convert(converter = RoleConverter.class)
    private Role role;

    @Column(name = "password")
    //@NotEmpty
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