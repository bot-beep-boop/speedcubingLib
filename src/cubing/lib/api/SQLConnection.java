package cubing.lib.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SQLConnection {

    public Connection connection;

    public SQLConnection(String url, String user, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String SQLdelete(String table, String where) {
        return "DELETE  FROM " + table + " WHERE " + where;
    }

    public String SQLupdate(String table, String field, String where) {
        return "UPDATE " + table + " SET " + field + " WHERE " + where;
    }

    public String SQLselect(String table, String field, String where) {
        return "SELECT " + field + " FROM " + table + " WHERE " + where;
    }

    public int count(String table, String field, String where) {
        return selectInt(table, "count(" + field + ")", where);
    }

    public int selectTop(String table, String field, String orderfield, String value, int index, int count, String sort) {
        return selectInt(table, field, value + " ORDER BY " + orderfield + " " + sort + " LIMIT " + index + ", " + count);
    }

    public void delete(String table, String where) {
        try {
            connection.prepareStatement(SQLdelete(table, where)).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(String table, String field, String where) {
        try {
            connection.prepareStatement(SQLupdate(table, field, where)).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert(String table, String field, String value) {
        try {
            PreparedStatement prepsInsertProduct = connection.prepareStatement("INSERT INTO " + table + " (" + field + ") VALUES (" + value + ")");
            prepsInsertProduct.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isStringExist(String table, String where) {
        try {
            return connection.prepareStatement(SQLselect(table, "*", where)).executeQuery().next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public double selectDouble(String table, String field, String where) {
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(SQLselect(table, field, where));
            resultSet.next();
            return resultSet.getDouble(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int selectInt(String table, String field, String where) {
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(SQLselect(table, field, where));
            resultSet.next();
            return resultSet.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int[] selectInts(String table, String field, String where) {
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(SQLselect(table, field, where));
            resultSet.next();
            int size = resultSet.getMetaData().getColumnCount();
            int[] ints = new int[size];
            for (int i = 0; i < size; i++) {
                ints[i] = resultSet.getInt(i + 1);
            }
            return ints;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean selectBoolean(String table, String field, String where) {
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(SQLselect(table, field, where));
            resultSet.next();
            return resultSet.getBoolean(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean[] selectBooleans(String table, String field, String where) {
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(SQLselect(table, field, where));
            resultSet.next();
            int size = resultSet.getMetaData().getColumnCount();
            boolean[] bs = new boolean[size];
            for (int i = 0; i < size; i++) {
                bs[i] = resultSet.getBoolean(i + 1);
            }
            return bs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String selectString(String table, String field, String where) {
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(SQLselect(table, field, where));
            resultSet.next();
            return resultSet.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] selectStrings(String table, String field, String where) {
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(SQLselect(table, field, where));
            resultSet.next();
            int size = resultSet.getMetaData().getColumnCount();
            String[] str = new String[size];
            for (int i = 0; i < size; i++) {
                str[i] = resultSet.getString(i + 1);
            }
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
