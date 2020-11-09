package uco.i62rorid.pw.Controlers;

import uco.i62rorid.pw.Connectors.FileConn;
import uco.i62rorid.pw.Entities.User;

import java.io.*;
import java.util.LinkedList;
import java.util.Properties;

/**
 * The type User controller.
 */
public class UserController {
    private FileConn conn;

    /**
     * Instantiates a new User controller.
     */
    public UserController(){
        try {
            InputStream in = getClass().getResourceAsStream("/.properties");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            Properties p = new Properties();
            p.load(reader);
            this.conn = new FileConn(p.getProperty("FILE_BASE_DIR")+p.getProperty("USER_TABLE_NAME"));
        } catch (NullPointerException e){
            try {
                FileReader reader=new FileReader(".properties");
                Properties p = new Properties();
                p.load(reader);
                this.conn = new FileConn(p.getProperty("FILE_BASE_DIR") + p.getProperty("USER_TABLE_NAME"));
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
