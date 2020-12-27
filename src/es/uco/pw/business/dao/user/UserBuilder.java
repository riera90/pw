package es.uco.pw.business.dao.user;

import es.uco.pw.business.Utils.JSONParser;
import es.uco.pw.business.Utils.SqlQuery;
import es.uco.pw.business.dao.common.DBConn;
import es.uco.pw.data.dto.user.DTOUser;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type User builder.
 */
public class UserBuilder {
    /**
     * Build dto user.
     *
     * @param rs the rs
     * @return the dto user
     */
    public static DTOUser build(ResultSet rs) {
        DTOUser user = new DTOUser();
        try {
            user.setId(rs.getInt("id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setRoleId(rs.getInt("role_id"));
            user.setBornAt(rs.getDate("born_at"));
            user.setDeleted(rs.getBoolean("is_deleted"));
            DBConn conn = new DBConn();
            String query = SqlQuery.getQuery("selectUserappTopics");
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, user.getId());
            ResultSet rs_topics =  conn.execQuery(ps);
            while (rs_topics.next()) {
                user.addInterest(rs_topics.getInt("topic_id"));
            }
            conn.close();
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Build dto user.
     *
     * @param json the json
     * @return the dto user
     */
    public static DTOUser build(String json) {
        DTOUser user = new DTOUser();
        JSONParser jsonParser = new JSONParser(json);
        while (jsonParser.getError() == 0 && jsonParser.gotoNextField()) {
            if (jsonParser.getKey().equals("id")) user.setId(jsonParser.getValueAsInt());
            else if (jsonParser.getKey().equals("firstName")) user.setFirstName(jsonParser.getValueAsString());
            else if (jsonParser.getKey().equals("lastName")) user.setLastName(jsonParser.getValueAsString());
            else if (jsonParser.getKey().equals("email")) user.setEmail(jsonParser.getValueAsString());
            else if (jsonParser.getKey().equals("password")) user.setPassword(jsonParser.getValueAsString());
            else if (jsonParser.getKey().equals("roleId")) user.setRoleId(jsonParser.getValueAsInt());
            else if (jsonParser.getKey().equals("bornAt")) user.setBornAt(jsonParser.getValueAsDate());
            else if (jsonParser.getKey().equals("isDeleted")) user.setDeleted(jsonParser.getValueAsBoolean());
            else if (jsonParser.getKey().equals("interests")){
                for (Integer interest_id : jsonParser.getValueAsIntegerLinkedList())
                    user.addInterest(interest_id);
            }
        }
        return user;
    }

    /**
     * To json string.
     *
     * @param user the user
     * @return the string
     */
    public static String toJson(DTOUser user){
        return '{' +
                (user.getId() == null ? "" : ("id:" + user.getId() + ",")) +
                (user.getFirstName() == null ? "" : ("firstName:\"" + user.getFirstName() + "\",")) +
                (user.getLastName() == null ? "" : ("lastName:\"" + user.getLastName() + "\",")) +
                (user.getEmail() == null ? "" : ("email:\"" + user.getEmail() + "\",")) +
                (user.getPassword() == null ? "" : ("password:\"" + user.getPassword() + "\",")) +
                (user.getRoleId() == null ? "" : ("roleId:" + user.getRoleId().toString() + ",")) +
                (user.getInterests() == null ? "" : ("interests:" + user.getInterests() + ",")) +
                (user.getBornAt() == null ? "" : ("bornAt:" + JSONParser.getDateAsString(user.getBornAt()) + ",")) +
                (user.getDeleted() == null ? "" : ("isDeleted:" + user.getDeleted() + ",")) +
                '}';
    }
}
