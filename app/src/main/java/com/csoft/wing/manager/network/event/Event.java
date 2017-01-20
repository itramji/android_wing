package com.csoft.wing.manager.network.event;

/**
 * Created by tringapps-admin on 20/1/17.
 */

public class Event {

    private int id;
    private String name;
    private Object object;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

}
