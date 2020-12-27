package es.uco.pw.business.dao.post;

import es.uco.pw.business.Utils.JSONParser;
import es.uco.pw.business.Utils.SqlQuery;
import es.uco.pw.business.dao.common.DBConn;
import es.uco.pw.data.dto.post.DTOPost;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class PostBuilder {
    public static DTOPost build(ResultSet rs){
        DTOPost post = new DTOPost();
        try {
            post.setId(rs.getInt("id"));
            post.setTitle(rs.getString("title"));
            post.setBody(rs.getString("body"));
            post.setCreatedAt(rs.getDate("created_at"));
            post.setPublishedAt(rs.getDate("published_at"));
            post.setDeletedAt(rs.getDate("deleted_at"));
            post.setOwnerId(rs.getInt("owner_id"));
            post.setType(rs.getString("type"));
            post.setState(rs.getString("state"));
            DBConn conn = new DBConn();
            String query = SqlQuery.getQuery("selectPostTopics");
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, post.getId());
            ResultSet rs_topics =  conn.execQuery(ps);
            while (rs_topics.next()) {
                post.addTopic(rs_topics.getInt("topic_id"));
            }
            query = SqlQuery.getQuery("selectPostUserapp");
            ps = conn.prepareStatement(query);
            ps.setInt(1, post.getId());
            ResultSet rs_sent_to =  conn.execQuery(ps);
            while (rs_sent_to.next()) {
                post.addSentTo(rs_sent_to.getInt("userapp_id"));
            }
            conn.close();
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return post;
    }

    public static DTOPost build(String json){
        DTOPost post = new DTOPost();
        JSONParser jsonParser = new JSONParser(json);
        while(jsonParser.getError()==0 && jsonParser.gotoNextField()){
            if (jsonParser.getKey().equals("id")) post.setId(jsonParser.getValueAsInt());
            else if (jsonParser.getKey().equals("title")) post.setTitle(jsonParser.getValueAsString());
            else if (jsonParser.getKey().equals("body")) post.setBody(jsonParser.getValueAsString());
            else if (jsonParser.getKey().equals("createdAt")) post.setCreatedAt(jsonParser.getValueAsDate());
            else if (jsonParser.getKey().equals("publishedAt")) post.setPublishedAt(jsonParser.getValueAsDate());
            else if (jsonParser.getKey().equals("deletedAt")) post.setDeletedAt(jsonParser.getValueAsDate());
            else if (jsonParser.getKey().equals("ownerId")) post.setOwnerId(jsonParser.getValueAsInt());
            else if (jsonParser.getKey().equals("type")) post.setType(jsonParser.getValueAsString());
            else if (jsonParser.getKey().equals("state")) post.setState(jsonParser.getValueAsString());
            else if (jsonParser.getKey().equals("sentTo")){
                for (Integer interest_id : jsonParser.getValueAsIntegerLinkedList())
                    post.addSentTo(interest_id);
            }
            else if (jsonParser.getKey().equals("topics")){
                for (Integer interest_id : jsonParser.getValueAsIntegerLinkedList())
                    post.addTopic(interest_id);
            }
        }
        return post;
    }

    public static String toJson(DTOPost post){
        return '{' +
                (post.getId()==null ? "":("id:"+post.getId()+",")) +
                (post.getTitle()==null ? "":("title:\""+post.getTitle()+"\",")) +
                (post.getBody()==null ? "":("body:\""+post.getBody()+"\",")) +
                (post.getCreatedAt()==null ? "":("createdAt:"+JSONParser.getDateAsString(post.getCreatedAt())+",")) +
                (post.getPublishedAt()==null ? "":("publishedAt:"+JSONParser.getDateAsString(post.getPublishedAt())+",")) +
                (post.getDeletedAt()==null ? "":("deletedAt:"+JSONParser.getDateAsString(post.getDeletedAt())+",")) +
                (post.getOwnerId() ==null ? "":("ownerId:"+ post.getOwnerId() +",")) +
                (post.getSentTo()==null ? "":("sentTo:"+post.getSentTo()+",")) +
                (post.getType()==null ? "":("type:\""+post.getType()+"\",")) +
                (post.getState()==null ? "":("state:\""+post.getState()+"\",")) +
                (post.getTopics()==null ? "":("topics:"+post.getTopics()+",")) +
                '}';
    }
}
