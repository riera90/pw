package uco.i62rorid.Controlers;

import uco.i62rorid.Connectors.FileConn;
import uco.i62rorid.Entities.User;

import java.util.LinkedList;

/**
 * The type User controller.
 */
public class UserController {
    private FileConn conn;

    /**
     * Instantiates a new User controller.
     */
    public UserController(){
        this.conn = new FileConn("user.db");
    }

    private Integer getNextId() {
        LinkedList<String> all = this.conn.readAll();
        if (all.size() == 0) return 0;
        User lastUser = new User(all.getLast());
        return lastUser.getId()+1;
    }

    /**
     * Get linked list.
     *
     * @return the linked list
     */
    public LinkedList<User> get(){
        LinkedList<User> users = new LinkedList<>();
        for (String userJson:conn.readAll()){
            users.add(new User(userJson));
        }
        return users;
    }

    /**
     * Get by field linked list.
     *
     * @param key   the key
     * @param value the value
     * @return the linked list
     */
    public LinkedList<User> getByField(String key, String value){
        LinkedList<User> users = new LinkedList<>();
        for (String userJson:conn.getLineByField(key, value)){
            users.add(new User(userJson));
        }
        return users;
    }

    /**
     * Get by field like linked list.
     *
     * @param key   the key
     * @param value the value
     * @return the linked list
     */
    public LinkedList<User> getByFieldLike(String key, String value){
        LinkedList<User> users = new LinkedList<>();
        for (String userJson:conn.getLineByFieldLike(key, value)){
            users.add(new User(userJson));
        }
        return users;
    }

    /**
     * Get user.
     *
     * @param id the id
     * @return the user
     */
    public User get(int id){
        return new User(conn.read(id));
    }

    /**
     * Post user.
     *
     * @param user the user
     * @return the user
     */
    public User post(User user){
        user.setId(getNextId());
        this.conn.append(user.toJson());
        return new User(this.conn.read(user.getId()));
    }

    /**
     * Put user.
     *
     * @param user the user
     * @return the user
     */
    public User put(User user){
        this.conn.update(user.toJson());
        return new User(this.conn.read(user.getId()));
    }

    /**
     * Patch user.
     *
     * @param user the user
     * @return the user
     */
    public User patch(User user){
        User oldUser = new User(this.conn.read(user.getId()));
        User newUser = new User(oldUser.toJson() + user.toJson());
        this.conn.update(newUser.toJson());
        return new User(this.conn.read(user.getId()));
    }

    /**
     * Delete boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public Boolean delete(User user){
        return this.conn.delete(user.getId());
    }
}
