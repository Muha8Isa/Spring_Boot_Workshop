package se.lexicon.spring_boot_workshop.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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

    @OneToMany(mappedBy = "borrower")
    List<BookLoan> loanList;

    public AppUser(String username, String password, Details details) {
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

    public List<BookLoan> getLoanList() {
        return loanList;
    }

    public void setLoanList(List<BookLoan> loanList) {
        this.loanList = loanList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return AppUserId == appUser.AppUserId && Objects.equals(username, appUser.username) && Objects.equals(password, appUser.password) && Objects.equals(regDate, appUser.regDate) && Objects.equals(details, appUser.details) && Objects.equals(loanList, appUser.loanList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(AppUserId, username, password, regDate, details, loanList);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "AppUserId=" + AppUserId +
                ", username='" + username + '\'' +
                ", regDate=" + regDate +
                ", details=" + details +
                ", loanList=" + loanList +
                '}';
    }
}
