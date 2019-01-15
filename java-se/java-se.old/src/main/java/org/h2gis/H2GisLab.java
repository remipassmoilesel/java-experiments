package org.h2gis;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

/**
 * Created by remipassmoilesel on 21/12/16.
 */
public class H2GisLab {

    public static void main(String[] args) throws SQLException {

        Connection conn = createH2Connection(Paths.get("data/h2gislab"));

        //SQLUtils.printH2DatabaseVersion(conn);

        //H2GISFunctions.load(conn);

        String[] requests = new String[]{

                "CREATE ALIAS IF NOT EXISTS H2GIS_SPATIAL FOR \"org.h2gis.functions.factory.H2GISFunctions.load\";"
                        + " CALL H2GIS_SPATIAL()",

                // create and populate first table
                "CREATE TABLE area(idarea INT PRIMARY KEY, the_geom GEOMETRY);",
                "CREATE SPATIAL INDEX myspatialindex ON area(the_geom);",
                "INSERT INTO area VALUES (1,'POLYGON((0 0, 20 0, 20 10, 0 10, 0 0))');",
                "INSERT INTO area VALUES (2,'POLYGON((25 5, 40 5, 40 15, 25 15, 25 5))');",
                "INSERT INTO area VALUES (3,'POLYGON((45 10, 50 10, 50 13, 45 13, 45 10))');",

                // create and populate second table
                "CREATE TABLE roads(idroad INT PRIMARY KEY, the_geom GEOMETRY);",
                "CREATE SPATIAL INDEX ON roads(the_geom);",
                "INSERT INTO roads VALUES (1, 'LINESTRING(2 2, 7 7)');",
                "INSERT INTO roads VALUES (2, 'LINESTRING(15 -1, 30 13)');"
        };

        for (String req : requests) {
            PreparedStatement stat = conn.prepareStatement(req);
            stat.execute();
            stat.close();
        }


        PreparedStatement stat = conn.prepareStatement("SELECT idarea, COUNT(idroad) roadscount" +
                "    FROM area, roads" +
                "    WHERE area.the_geom && roads.the_geom" +
                "    AND ST_Intersects(area.the_geom, roads.the_geom)" +
                "    GROUP BY idarea" +
                "    ORDER BY idarea;");

        ResultSet res = stat.executeQuery();

        while (res.next()) {
            System.out.println();
            System.out.println(res.getObject(1));
            System.out.println(res.getObject(2));
        }

    }

    public static Connection createH2Connection(Path databasePath) throws SQLException {
        return DriverManager.getConnection("jdbc:h2:file:" + databasePath.toAbsolutePath().toString());
    }


}
