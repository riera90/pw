package es.uco.pw.data.dto.topic;

import es.uco.pw.business.Utils.JSONParser;

/**
 * The type Topic.
 */
public class DTOTopic {
    private Integer id;
    private String name;

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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "id: " + this.id + "\t" + this.name;
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
        return '{' +
                (id==null ? "":("id:"+id+",")) +
                (name ==null ? "":("name:\""+ name +"\",")) +
                '}';
    }

    /**
     * Instantiates a new Topic.
     */
    public DTOTopic() {

    }

    /**
     * Instantiates a new Topic.
     *
     * @param json the json
     */
    public DTOTopic(String json) {
        JSONParser jsonParser = new JSONParser(json);
        while(jsonParser.getError()==0 && jsonParser.gotoNextField()){
            if (jsonParser.getKey().equals("id")) this.id = jsonParser.getValueAsInt();
            else if (jsonParser.getKey().equals("name")) this.name = jsonParser.getValueAsString();
        }
    }
}
