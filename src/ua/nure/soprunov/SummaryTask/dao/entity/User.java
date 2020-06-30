package ua.nure.soprunov.SummaryTask.dao.entity;

/**
 * User entity.This transfer object characterized by id, first and last names,
 * login, password, role in system. Login
 * should be unique. Every field must be filled.
 *
 @authors Soprunov Igor
 */
public class User extends Entity {

    private static final long serialVersionUID = -6889036256149495388L;

    private String login;

    private String password;

    private String firstName;

    private String lastName;

    private String userAvatar;


    private int roleId;

    public User() {
    }

    public User(String login, String password, String firstName, String lastName, String userAvatar, int roleId) {
        super();
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userAvatar = userAvatar;
        this.roleId = roleId;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "User [login=" + login
                + ", firstName=" + firstName
                + ", lastName=" + lastName
                + ", userAvatar=" + userAvatar
                + ", roleId=" + roleId + "]";
    }

}
