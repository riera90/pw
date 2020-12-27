package es.uco.pw.data.dto.user;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;

import es.uco.pw.business.Utils.SqlQuery;
import es.uco.pw.business.dao.common.DBConn;
import es.uco.pw.business.Utils.JSONParser;

/**
 * The type User.
 */
public class DTOUser {
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
    private Integer role_id;
    private LinkedList<Integer> interests;
    private Date bornAt;
    private Boolean isDeleted;


    public static String[] getROLES() {
        return ROLES;
    }

    public static void setROLES(String[] ROLES) {
        DTOUser.ROLES = ROLES;
    }

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

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public LinkedList<Integer> getInterests() {
        return interests;
    }

    public void setInterests(LinkedList<Integer> interests) {
        this.interests = interests;
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
        return "id: "+this.id+"\t"+this.firstName+" "+this.lastName+"\t<"+this.email+">\trole_id: "+(this.role_id == null ? "" : this.role_id.toString())+"\tborn at: "+(this.bornAt == null ? "" : JSONParser.getDateAsString(this.bornAt))+"\tinterests: "+this.interests.toString();
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
        return '{' +
                (id == null ? "" : ("id:" + id + ",")) +
                (firstName == null ? "" : ("firstName:\"" + firstName + "\",")) +
                (lastName == null ? "" : ("lastName:\"" + lastName + "\",")) +
                (email == null ? "" : ("email:\"" + email + "\",")) +
                (password == null ? "" : ("password:\"" + password + "\",")) +
                (role_id == null ? "" : ("role_id:" + role_id.toString() + ",")) +
                (interests == null ? "" : ("interests:" + interests + ",")) +
                (bornAt == null ? "" : ("bornAt:" + JSONParser.getDateAsString(bornAt) + ",")) +
                (isDeleted == null ? "" : ("isDeleted:" + isDeleted + ",")) +
                '}';
    }

    /**
     * Instantiates a new User.
     */
    public DTOUser() {
    }

    /**
     * Instantiates a new User.
     *
     * @param json the json
     */
    public DTOUser(String json) {
        JSONParser jsonParser = new JSONParser(json);
        while (jsonParser.getError() == 0 && jsonParser.gotoNextField()) {
            if (jsonParser.getKey().equals("id")) this.id = jsonParser.getValueAsInt();
            else if (jsonParser.getKey().equals("firstName")) this.firstName = jsonParser.getValueAsString();
            else if (jsonParser.getKey().equals("lastName")) this.lastName = jsonParser.getValueAsString();
            else if (jsonParser.getKey().equals("email")) this.email = jsonParser.getValueAsString();
            else if (jsonParser.getKey().equals("password")) this.password = jsonParser.getValueAsString();
            else if (jsonParser.getKey().equals("role_id")) this.role_id = jsonParser.getValueAsInt();
            else if (jsonParser.getKey().equals("interests")) this.interests = jsonParser.getValueAsIntegerLinkedList();
            else if (jsonParser.getKey().equals("bornAt")) this.bornAt = jsonParser.getValueAsDate();
            else if (jsonParser.getKey().equals("isDeleted")) this.isDeleted = jsonParser.getValueAsBoolean();
        }
    }

    public DTOUser(ResultSet rs) {
        try {
            this.id = rs.getInt("id");
            this.firstName = rs.getString("first_name");
            this.lastName = rs.getString("last_name");
            this.email = rs.getString("email");
            this.password = rs.getString("password");
            this.role_id = rs.getInt("role_id");
            this.bornAt = rs.getDate("born_at");
            this.isDeleted = rs.getBoolean("is_deleted");
            DBConn conn = new DBConn();
            String query = SqlQuery.getQuery("selectUserappTopics");
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, this.id);
            ResultSet rs_topics =  conn.execQuery(ps);
            this.interests = new LinkedList<Integer>();
            while (rs_topics.next()) {
                this.interests.add(rs_topics.getInt("topic_id"));
            }
            conn.close();
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
