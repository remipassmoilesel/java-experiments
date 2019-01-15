package org.ormliteLab;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;
import java.util.Objects;

/**
 * Created by remipassmoilesel on 01/02/17.
 */
@DatabaseTable(tableName = "connections")
public class Connection {

    private static final String ACCOUNT_FIELD_NAME = "account";

    /**
     * Here only the id of element will be stored.
     * <p>
     * To access data of object, call accountDao.refresh()
     */
    @DatabaseField(foreign = true, columnName = ACCOUNT_FIELD_NAME)
    private Account account;

    private Date date;

    public Connection() {
    }

    public Connection(Account account) {
        this.account = account;
        this.date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Connection that = (Connection) o;
        return Objects.equals(date, that.date) &&
                Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, account);
    }

    @Override
    public String toString() {
        return "Connection{" +
                "date=" + date +
                ", account=" + account +
                '}';
    }
}
