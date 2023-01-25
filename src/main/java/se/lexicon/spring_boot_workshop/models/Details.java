package se.lexicon.spring_boot_workshop.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@NamedQuery(name = "details.findById", query = "select d from Details d where d.id = ?1")
//@Table(name = "Table_Details")
public class Details {

    @Id //This command is used to make the next table a primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //This command is used to auto increment the id details table.
    @Column(name = "DTLS_ID", updatable = false) //This command is to change the column name in the database.
    int detailsId; // It is not allowed to update this table in the database, since the command updatable = false is given above.

    /** Alternative 2:
     @Id
     @GeneratedValue(generator  = "UUID")
     @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
     String id;
     */

    @Column(nullable = false, length = 100) //name cannot be null so nullable = false is used, length is set to 100
    String name;

    @Column(nullable = false, length = 120, unique = true) //email should be unique in database
    String email;

    @Column(nullable = false)
    LocalDate birthDate;

    //Constructor

    public Details() {
    }

    public Details(int detailsId, String name, String email, LocalDate birthDate) {
        this.detailsId = detailsId;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    //Setters & Getters

    public int getDetailsId() {
        return detailsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Details details = (Details) o;
        return detailsId == details.detailsId && Objects.equals(name, details.name) && Objects.equals(email, details.email) && Objects.equals(birthDate, details.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(detailsId, name, email, birthDate);
    }

    @Override
    public String toString() {
        return "Details{" +
                "detailsId=" + detailsId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
