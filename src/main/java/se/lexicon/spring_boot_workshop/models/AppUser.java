package se.lexicon.spring_boot_workshop.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NamedQuery(name = "appUser.findById", query = "select a from AppUser a where a.id = ?1")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int AppUserId;

    @Column(nullable = false, length = 100,unique = true)
    String username;

    @Column(nullable = false, length = 16)
    String password;
    LocalDateTime regDate;

    @OneToOne
    @JoinColumn( name = "DTLS_ID")
    Details details;

    public AppUser(int appUserId, String username, String password, Details details) {
        AppUserId = appUserId;
        this.username = username;
        this.password = password;
        this.details = details;
    }

    public AppUser(){
        this.regDate = LocalDateTime.now();
    }
    public int getAppUserId() {
        return AppUserId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null) throw new IllegalArgumentException("password was null");
        if (password.length() < 8) throw new IllegalArgumentException("password length was not valid" + "password length should be greater than or equal to 8 and less than or equal to 16");
        if (password.length() > 16) throw new IllegalArgumentException("password length was not valid" + "password length should be greater than ot equal to 8 and less than or equal to 16");
        this.password = password;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }
}
