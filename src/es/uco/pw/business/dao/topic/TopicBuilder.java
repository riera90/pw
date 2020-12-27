package es.uco.pw.business.dao.topic;

import es.uco.pw.business.Utils.JSONParser;
import es.uco.pw.business.Utils.SqlQuery;
import es.uco.pw.business.dao.common.DBConn;
import es.uco.pw.data.dto.topic.DTOTopic;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Topic builder.
 */
public class TopicBuilder {
    /**
     * Build dto topic.
     *
     * @param rs the rs
     * @return the dto topic
     */
    public static DTOTopic build(ResultSet rs){
        DTOTopic topic = new DTOTopic();
        try {
            topic.setId(rs.getInt("id"));
            topic.setName(rs.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topic;
    }

    /**
     * Build dto topic.
     *
     * @param json the json
     * @return the dto topic
     */
    public static DTOTopic build(String json){
        DTOTopic topic = new DTOTopic();
        JSONParser jsonParser = new JSONParser(json);
        while(jsonParser.getError()==0 && jsonParser.gotoNextField()){
            if (jsonParser.getKey().equals("id")) topic.setId(jsonParser.getValueAsInt());
            else if (jsonParser.getKey().equals("name")) topic.setName(jsonParser.getValueAsString());
        }
        return topic;
    }

    /**
     * To json string.
     *
     * @param topic the topic
     * @return the string
     */
    public static String toJson(DTOTopic topic){
        return '{' +
                (topic.getId()==null ? "":("id:"+topic.getId()+",")) +
                (topic.getName()==null ? "":("name:\""+ topic.getName() +"\",")) +
                '}';
    }
}
