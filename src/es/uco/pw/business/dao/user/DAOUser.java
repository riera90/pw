package es.uco.pw.business.dao.user;

import java.util.Date;
import java.util.LinkedList;

import es.uco.pw.business.Controlers.TopicController;
import es.uco.pw.business.Utils.JSONParser;

/**
 * The type User.
 */
public class DAOUser {
    /**
     * The Roles.
     */
    public static String[] ROLES = {
            "admin",
            "user",
    };

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private LinkedList<Integer> interests;
    private Date bornAt;
    private Boolean isDeleted;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets interests.
     *
     * @return the interests
     */
    public LinkedList<Integer> getInterests() {
        return interests;
    }

    /**
     * Sets interests.
     *
     * @param interests the interests
     */
    public void setInterests(LinkedList<Integer> interests) {
        this.interests = interests;
    }

    /**
     * Gets born at.
     *
     * @return the born at
     */
    public Date getBornAt() {
        return bornAt;
    }

    /**
     * Sets born at.
     *
     * @param bornAt the born at
     */
    public void setBornAt(Date bornAt) {
        this.bornAt = bornAt;
    }

    /**
     * Gets deleted.
     *
     * @return the deleted
     */
    public Boolean getDeleted() {
        return isDeleted;
    }

    /**
     * Sets deleted.
     *
     * @param deleted the deleted
     */
    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }


    @Override
    public String toString() {
        StringBuilder retval = new StringBuilder("id: " + this.id + "\t" + this.firstName + " " + this.lastName + "\t<" + this.email + ">\t" + (this.role == null ? "" : this.role) + "\tborn at: " + (this.bornAt == null ? "" : JSONParser.getDateAsString(this.bornAt)) + "\n" +
                "\tinterests:\n");
        TopicController topicController = new TopicController();
        if (this.interests.isEmpty()) return retval.toString();
        for (int interestId:this.interests) {
            retval.append("\t\t").append(topicController.get(interestId).toString()).append("\n");
        }
        return retval.toString();
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
        return '{' +
                (id==null ? "":("id:"+id+",")) +
                (firstName==null ? "":("firstName:\""+firstName+"\",")) +
                (lastName==null ? "":("lastName:\""+lastName+"\",")) +
                (email==null ? "":("email:\""+email+"\",")) +
                (password==null ? "":("password:\""+password+"\",")) +
                (role==null ? "":("role:\""+role+"\",")) +
                (interests==null ? "":("interests:"+interests+",")) +
                (bornAt==null ? "":("bornAt:"+JSONParser.getDateAsString(bornAt)+",")) +
                (isDeleted==null ? "":("isDeleted:"+isDeleted+",")) +
                '}';
    }

    /**
     * Instantiates a new User.
     */
    public DAOUser(){
    }

    /**
     * Instantiates a new User.
     *
     * @param json the json
     */
    public DAOUser(String json) {
        JSONParser jsonParser = new JSONParser(json);
        while(jsonParser.getError()==0 && jsonParser.gotoNextField()){
            if (jsonParser.getKey().equals("id")) this.id = jsonParser.getValueAsInt();
            else if (jsonParser.getKey().equals("firstName")) this.firstName = jsonParser.getValueAsString();
            else if (jsonParser.getKey().equals("lastName")) this.lastName = jsonParser.getValueAsString();
            else if (jsonParser.getKey().equals("email")) this.email = jsonParser.getValueAsString();
            else if (jsonParser.getKey().equals("password")) this.password = jsonParser.getValueAsString();
            else if (jsonParser.getKey().equals("role")) this.role = jsonParser.getValueAsString();
            else if (jsonParser.getKey().equals("interests")) this.interests = jsonParser.getValueAsIntegerLinkedList();
            else if (jsonParser.getKey().equals("bornAt")) this.bornAt = jsonParser.getValueAsDate();
            else if (jsonParser.getKey().equals("isDeleted")) this.isDeleted = jsonParser.getValueAsBoolean();
        }
    }
}
