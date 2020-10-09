package uco.i62rorid.Entities;

import uco.i62rorid.Utils.JSONParser;

public class Topic {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "id: " + this.id + "\t" + this.name;
    }

    public String toJson() {
        return '{' +
                (id==null ? "":("id:"+id+",")) +
                (name ==null ? "":("name:\""+ name +"\",")) +
                '}';
    }

    public Topic() {

    }

    public Topic(String json) {
        JSONParser jsonParser = new JSONParser(json);
        while(jsonParser.getError()==0 && jsonParser.gotoNextField()){
            if (jsonParser.getKey().equals("id")) this.id = jsonParser.getValueAsInt();
            else if (jsonParser.getKey().equals("name")) this.name = jsonParser.getValueAsString();
        }
    }
}
