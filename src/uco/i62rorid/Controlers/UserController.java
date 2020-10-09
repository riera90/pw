package uco.i62rorid.Controlers;

import uco.i62rorid.Connectors.FileConnector;
import uco.i62rorid.Entities.Topic;
import uco.i62rorid.Entities.User;

import java.util.LinkedList;

public class UserController {
    private FileConnector conn;

    public UserController(){
        this.conn = new FileConnector("user.dat");
    }

    private Integer getNextId() {
        LinkedList<String> all = this.conn.readAll();
        if (all.size() == 0) return 0;
        User lastUser = new User(all.getLast());
        return lastUser.getId()+1;
    }

    public LinkedList<User> get(){
        LinkedList<User> users = new LinkedList<>();
        for (String userJson:conn.readAll()){
            users.add(new User(userJson));
        }
        return users;
    }

    public LinkedList<User> getByField(String key, String value){
        LinkedList<User> users = new LinkedList<>();
        for (String userJson:conn.getLineByField(key, value)){
            users.add(new User(userJson));
        }
        return users;
    }

    public LinkedList<User> getByFieldLike(String key, String value){
        LinkedList<User> users = new LinkedList<>();
        for (String userJson:conn.getLineByFieldLike(key, value)){
            users.add(new User(userJson));
        }
        return users;
    }

    public User get(int id){
        return new User(conn.read(id));
    }

    public User post(User user){
        user.setId(getNextId());
        this.conn.append(user.toJson());
        return new User(this.conn.read(user.getId()));
    }

    public User put(User user){
        this.conn.update(user.toJson());
        return new User(this.conn.read(user.getId()));
    }

    public User patch(User user){
        this.conn.update(this.conn.read(user.getId()) + user.toJson());
        return new User(this.conn.read(user.getId()));
    }

    public Boolean delete(User user){
        return this.conn.delete(user.getId());
    }
}
