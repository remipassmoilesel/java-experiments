package org.ormliteLab;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.geotools.geometry.jts.ReferencedEnvelope;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by remipassmoilesel on 15/11/16.
 */
public class OrmLiteLab {

    public static void main(String[] args) throws SQLException, IOException {

        // this uses h2 by default but change to match your database
        String databaseUrl = "jdbc:sqlite:accounts.sqlite";

        // create a connection source to our database
        ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);

        // instantiate the dao
        Dao<Account, String> accountDao = DaoManager.createDao(connectionSource, Account.class);

        // if you need to create the 'accounts' table make this call
        TableUtils.createTableIfNotExists(connectionSource, Account.class);

        // create an instance of Account
        Account account = new Account();
        account.setName("Jim Coakley");

        // persist the account object to the database
        accountDao.create(account);

        account.setPassword("azerty");
        accountDao.update(account);

        Account account3 = new Account("heyhey", "hoho");
        accountDao.create(account3);
        accountDao.delete(account);


        // dao are iterators
        for (Account a : accountDao) {
            if (account.getName().equals("Bob Smith")) {
                // you can't return, break, or throw from here
                // return a;
                //System.out.println(a);
            }
        }

        // retrieve the account from the database by its id field (name)
        List<Account> account2 = accountDao.queryForAll();
        System.out.println("Accounts: " + account2);

        // instantiate the dao
        Dao<Connection, String> connectionsDao = DaoManager.createDao(connectionSource, Connection.class);

        TableUtils.createTableIfNotExists(connectionSource, Connection.class);

        connectionsDao.create(new Connection(account));
        connectionsDao.create(new Connection(account3));

        for(Connection c : connectionsDao){

            System.out.println();

            // /!\account is not loaded, it show only default values
            Account ac = c.getAccount();
            System.out.println(ac);

            // after refresh it datas are correct
            accountDao.refresh(ac);
            System.out.println(ac);
        }



        // close the connection source
        connectionSource.close();

        /*
        ReferencedEnvelope area = part.getEnvelope();
        Where<SerializableRenderedPartial, ?> statement = dao.queryBuilder().where().raw(
                "ABS(" + SerializableRenderedPartial.PARTIAL_X1_FIELD_NAME + " - ?) < " + PRECISION + " "
                        + "AND ABS(" + SerializableRenderedPartial.PARTIAL_X2_FIELD_NAME + " - ?) < " + PRECISION + " "
                        + "AND ABS(" + SerializableRenderedPartial.PARTIAL_Y1_FIELD_NAME + " - ?) < " + PRECISION + " "
                        + "AND ABS(" + SerializableRenderedPartial.PARTIAL_Y2_FIELD_NAME + " - ?) < " + PRECISION + " "
                        + "AND " + SerializableRenderedPartial.PARTIAL_LAYERID_FIELD_NAME + "=? ",

                new SelectArg(SqlType.DOUBLE, area.getMinX()),
                new SelectArg(SqlType.DOUBLE, area.getMaxX()),
                new SelectArg(SqlType.DOUBLE, area.getMinY()),
                new SelectArg(SqlType.DOUBLE, area.getMaxY()),
                new SelectArg(SqlType.STRING, part.getLayerId())
        );
        List<SerializableRenderedPartial> results = statement.query();
        */

        /*


           try {
            QueryBuilder<Reservation, String> queryBuilder = reservationDao.queryBuilder();
            queryBuilder.orderBy(Reservation.DATEARRIVAL_FIELD_NAME, orderAscending);
            Where<Reservation, String> where = queryBuilder.where();

            where.between(Reservation.DATEARRIVAL_FIELD_NAME, beginDate, endDate)
                    .or().between(Reservation.DATEDEPARTURE_FIELD_NAME, beginDate, endDate);

            List<Reservation> results = queryBuilder.query();

            for (Reservation r : results) {
                // here we must refresh foreign object
                customerDao.refresh(r.getCustomer());
            }

            return results;
        } catch (Exception e) {
            throw new IOException(e);
        }
         */



    }

}
