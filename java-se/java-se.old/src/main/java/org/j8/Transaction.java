package org.j8;

public class Transaction {
    public static int GROCERY = 1;
    public static int NOT_GROCERY = 2;

    private int type;
    private int id;
    private int value;

    public Transaction(int type, int id, int value) {
        this.type = type;
        this.id = id;
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}