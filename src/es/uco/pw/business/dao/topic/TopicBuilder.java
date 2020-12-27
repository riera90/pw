package es.uco.pw.business.dao.topic;

import es.uco.pw.data.dto.topic.DTOTopic;

import java.sql.ResultSet;

public class TopicBuilder {
    public static DTOTopic builder(ResultSet rs){
        DTOTopic topic = new DTOTopic();
        return topic;
    }

    public static DTOTopic builder(String json){
        DTOTopic topic = new DTOTopic();
        return topic;
    }

    public static String toJson(DTOTopic topic){
        return "";
    }
}
