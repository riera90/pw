package uco.i62rorid.Entities;

import java.util.LinkedList;

import uco.i62rorid.Controlers.TopicController;
import uco.i62rorid.Utils.JSONParser;

public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private LinkedList<Integer> interests;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LinkedList<Integer> getInterests() {
        return interests;
    }

    public void setInterests(LinkedList<Integer> interests) {
        this.interests = interests;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }


    @Override
    public String toString() {
        String retval = "id: " + this.id + "\t" + this.firstName + " " + this.lastName +  "\t<" + this.email + ">\t" + (this.role == null ? "":this.role)+ "\n" +
                "\tinterests:\n";
        TopicController topicController = new TopicController();
        if (this.interests.isEmpty()) return retval;
        for (int interestId:this.interests) {
            retval += "\t\t" + topicController.get(interestId).toString() + "\n";
        }
        return retval;
    }

    public String toJson() {
        return '{' +
                (id==null ? "":("id:"+id+",")) +
                (firstName==null ? "":("firstName:\""+firstName+"\",")) +
                (lastName==null ? "":("lastName:\""+lastName+"\",")) +
                (email==null ? "":("email:\""+email+"\",")) +
                (role==null ? "":("role:\""+role+"\",")) +
                (interests==null ? "":("interests:"+interests+",")) +
                (isDeleted==null ? "":("isDeleted:"+isDeleted+",")) +
                '}';
    }

    public User(){
    }

    public User(String json) {
        JSONParser jsonParser = new JSONParser(json);
        while(jsonParser.getError()==0 && jsonParser.gotoNextField()){
            if (jsonParser.getKey().equals("id")) this.id = jsonParser.getValueAsInt();
            else if (jsonParser.getKey().equals("firstName")) this.firstName = jsonParser.getValueAsString();
            else if (jsonParser.getKey().equals("lastName")) this.lastName = jsonParser.getValueAsString();
            else if (jsonParser.getKey().equals("email")) this.email = jsonParser.getValueAsString();
            else if (jsonParser.getKey().equals("role")) this.role = jsonParser.getValueAsString();
            else if (jsonParser.getKey().equals("interests")) this.interests = jsonParser.getValueAsIntegerLinkedList();
            else if (jsonParser.getKey().equals("isDeleted")) this.isDeleted = jsonParser.getValueAsBoolean();
        }
    }
}
