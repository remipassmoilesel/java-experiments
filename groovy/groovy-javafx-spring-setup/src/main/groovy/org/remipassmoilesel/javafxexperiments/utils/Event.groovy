package org.remipassmoilesel.javafxexperiments.utils

class Event {

    private String name;
    private Object data;

    Event(String name, Object data) {
        this.name = name
        this.data = data
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    Object getData() {
        return data
    }

    void setData(Object data) {
        this.data = data
    }
}
