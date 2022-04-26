package webapp.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // Hibernate
@Table(name = "users") // Name of table
public class User {
    @Id
    @Getter
    @Setter
    @Column(name = "id") // Indicates column in table
    private int id;
    @Getter
    @Setter
    @Column(name = "name")
    private String name;
    @Getter
    @Setter
    @Column(name = "lastname")
    private String lastname;
    @Getter
    @Setter
    @Column(name = "email")
    private String email;
    @Getter
    @Setter
    @Column(name = "phone")
    private String phone;
    @Getter
    @Setter
    @Column(name = "password")
    private String password;

}
