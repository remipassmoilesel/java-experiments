package org.ormliteLab;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Objects;

@DatabaseTable(tableName = "accounts")
public class Account {

    private static final String PASSWORD_FIELD_NAME = "password";
    private static final String ID_FIELD_NAME = "id";
    private static final String NAME_FIELD_NAME = "name";

    // all field options available here: http://ormlite.com/javadoc/ormlite-core/doc-files/ormlite_2.html#Using
    // types available here: http://ormlite.com/javadoc/ormlite-core/doc-files/ormlite_2.html#Persisted-Types
    // With sqlite: use Xerial driver

    @DatabaseField(generatedId = true, columnName = ID_FIELD_NAME)
    private int id;

    @DatabaseField(columnName = NAME_FIELD_NAME)
    private String name;

    @DatabaseField(columnName = PASSWORD_FIELD_NAME)
    private String password;

    public Account() {
        // ORMLite needs a no-arg constructor 
    }

    public Account(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id &&
                Objects.equals(name, account.name) &&
                Objects.equals(password, account.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
