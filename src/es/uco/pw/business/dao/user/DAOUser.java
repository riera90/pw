package es.uco.pw.business.dao.user;

import es.uco.pw.business.Connectors.FileConn;
import es.uco.pw.business.dao.common.DBConn;
import es.uco.pw.data.dto.user.DTOUser;

import java.io.*;
import java.util.LinkedList;
import java.util.Properties;

/**
 * The type User controller.
 */
public class DAOUser {
    private DBConn conn;

    /**
     * Instantiates a new User controller.
     */
    public DAOUser(){
        
    }

    /**
     * Get linked list.
     *
     * @return the linked list
     */
    public LinkedList<DTOUser> get(){
        LinkedList<DTOUser> users = new LinkedList<>();

        return users;
    }

    /**
     * Get by field linked list.
     *
     * @param key   the key
     * @param value the value
     * @return the linked list
     */
    public LinkedList<DTOUser> getByField(String key, String value){
        LinkedList<DTOUser> users = new LinkedList<>();

        return users;
    }

    /**
     * Get by field like linked list.
     *
     * @param key   the key
     * @param value the value
     * @return the linked list
     */
    public LinkedList<DTOUser> getByFieldLike(String key, String value){
        LinkedList<DTOUser> users = new LinkedList<>();

        return users;
    }

    /**
     * Get user.
     *
     * @param id the id
     * @return the user
     */
    public DTOUser get(int id){
        return new DTOUser();
    }

    /**
     * Post user.
     *
     * @param user the user
     * @return the user
     */
    public DTOUser post(DTOUser user){

        return new DTOUser();
    }

    /**
     * Put user.
     *
     * @param user the user
     * @return the user
     */
    public DTOUser put(DTOUser user){
        return new DTOUser();
    }

    /**
     * Patch user.
     *
     * @param user the user
     * @return the user
     */
    public DTOUser patch(DTOUser user){

        return new DTOUser();
    }

    /**
     * Delete boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public Boolean delete(DTOUser user){
        return true;
    }
}
