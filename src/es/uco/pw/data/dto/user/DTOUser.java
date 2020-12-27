package es.uco.pw.data.dto.user;

import java.util.Date;
import java.util.LinkedList;

import es.uco.pw.business.Utils.JSONParser;

/**
 * The type User.
 */
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public LinkedList<Integer> getInterests() {
        return interests;
    }

    public void addInterest(Integer interest_id) {
        this.interests.add(interest_id);
    }

    public void removeInterest(Integer interest_id) {
        this.interests.remove(interest_id);
    }

    public Date getBornAt() {
        return bornAt;
    }

    public void setBornAt(Date bornAt) {
        this.bornAt = bornAt;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
    

    @Override
    public String toString() {
        return "id: "+this.id+"\t"+this.firstName+" "+this.lastName+"\t<"+this.email+">\trole_id: "+(this.roleId == null ? "" : this.roleId.toString())+"\tborn at: "+(this.bornAt == null ? "" : JSONParser.getDateAsString(this.bornAt))+"\tinterests: "+this.interests.toString();
    }

    /**
     * Instantiates a new User.
     */
    public DTOUser() {
        this.interests = new LinkedList<Integer>();
    }
}
