package es.uco.pw.data.dto.user;

import java.util.Date;
import java.util.LinkedList;

import es.uco.pw.business.Utils.JSONParser;

public class DTOUser {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Integer roleId;
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
     * Gets role id.
     *
     * @return the role id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * Sets role id.
     *
     * @param roleId the role id
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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
     * Add interest.
     *
     * @param interest_id the interest id
     */
    public void addInterest(Integer interest_id) {
        if (!this.interests.contains(interest_id))
            this.interests.add(interest_id);
    }

    /**
     * Remove interest.
     *
     * @param interest_id the interest id
     */
    public void removeInterest(Integer interest_id) {
        this.interests.remove(interest_id);
    }

    /**
     * Clear interests.
     */
    public void clearInterests(){
        this.interests = new LinkedList<>();
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
        return "id: "+this.id+"\t"+this.firstName+" "+this.lastName+"\t<"+this.email+">\trole_id: "+(this.roleId == null ? "" : this.roleId.toString())+"\tborn at: "+(this.bornAt == null ? "" : JSONParser.getDateAsString(this.bornAt))+"\tinterests: "+this.interests.toString();
    }

    /**
     * Instantiates a new Dto user.
     */
    public DTOUser() {
        this.interests = new LinkedList<Integer>();
    }
}
