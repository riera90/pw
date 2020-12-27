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
     * Instantiates a new Topic.
     */
    public DTOTopic() {
    }
}
