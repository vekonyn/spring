package by.epam.spring.hometask.domain;

import java.util.*;

/**
 * @author Yuriy_Tkach
 */
public class User extends DomainObject {

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private List<String> luckyEvents = new ArrayList<>();

    private NavigableSet<Ticket> tickets = new TreeSet<>();

    public User(String firstName, String lastName, String email, NavigableSet<Ticket> tickets) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.tickets = tickets;
    }

    public User(String firstName, String lastName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }

    public User(String firstName, String password) {
        this.firstName = firstName;
        this.password = password;
    }

    public User(Long id, String firstName, String lastname, String email) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastname;
        this.email = email;
    }

    public User(Long id, String firstName, String password, String lastName, String email) {
        super(id);
        this.firstName = firstName;
        this.password = password;
        this.lastName = lastName;
        this.email = email;
    }

    public User(Long id, String firstName, String lastName, String email, NavigableSet<Ticket> tickets) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.tickets = tickets;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public NavigableSet<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(NavigableSet<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getLuckyEvents() {
        return luckyEvents;
    }

    public void setLuckyEvents(List<String> luckyEvents) {
        this.luckyEvents = luckyEvents;
    }

    public void setLuckyEvent(String luckyEvent) {
        if (luckyEvent != null && !luckyEvent.isEmpty())
            this.luckyEvents.add(luckyEvent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        if (email == null) {
            if (other.email != null) {
                return false;
            }
        } else if (!email.equals(other.email)) {
            return false;
        }
        if (firstName == null) {
            if (other.firstName != null) {
                return false;
            }
        } else if (!firstName.equals(other.firstName)) {
            return false;
        }
        if (password == null) {
            if (other.password != null) {
                return false;
            }
        } else if (!password.equals(other.password)) {
            return false;
        }
        if (lastName == null) {
            if (other.lastName != null) {
                return false;
            }
        } else if (!lastName.equals(other.lastName)) {
            return false;
        }
        return true;
    }

}
