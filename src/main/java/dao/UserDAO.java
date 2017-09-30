package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import library.DBConnect;
import model.User;
import net.sf.json.JSONObject;

public class UserDAO {

    private static final String TABLE_NAME = "users";
    private static final String COL_ID = "id_user";
    private static final String COL_USERNAME = "username";
    private static final String COL_PASSWORD = "pass";
    private static final String COL_EMAIL = "email";
    private static final String COL_ROLES = "roles";

    public int addItem(User item) {
        int result = 0;
        String sql = "INSERT INTO " + TABLE_NAME + "("
                + COL_ID + ","
                + COL_USERNAME + ","
                + COL_PASSWORD + ","
                + COL_EMAIL + ","
                + COL_ROLES + ") VALUES ( ?, ?, ?, ?, ?)";
        Connection connection = DBConnect.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setLong(1, item.getId());
            ps.setString(2, item.getUsername());
            ps.setString(3, item.getPass());
            ps.setString(4, item.getEmail());
            ps.setInt(5, item.getRoles());
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return result;
    }

    public int editItem(User item) {
        int result = 0;
        String sql = "UPDATE " + TABLE_NAME + " SET "
                + COL_PASSWORD + " = ?, "
                + COL_ROLES + " = ? WHERE "
                + COL_ID + " = ?";
        Connection connection = DBConnect.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, item.getPass());
            ps.setInt(2, item.getRoles());
            ps.setLong(3, item.getId());
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public int deleteItem(User item) {
        int result = 0;
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + COL_ID + " = ?";
        Connection connection = DBConnect.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setLong(1, item.getId());
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public int deleteItem(long item) {
        int result = 0;
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + COL_ID + " = ?";
        Connection connection = DBConnect.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setLong(1, item);
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public ArrayList<User> getList() {
        ArrayList<User> result = new ArrayList<>();
        Connection connection = DBConnect.getConnection();
        String sql = "SELECT * FROM " + TABLE_NAME;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                User item = new User();
                item.setId(rs.getLong(COL_ID));
                item.setUsername(rs.getString(COL_USERNAME));
                item.setPass(rs.getString(COL_PASSWORD));
                item.setEmail(rs.getString(COL_EMAIL));
                item.setRoles(rs.getInt(COL_ROLES));
                result.add(item);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return result;
    }

    public ArrayList<User> getList(long start, long num) {
        ArrayList<User> result = new ArrayList<>();
        Connection connection = DBConnect.getConnection();
        String sql = "SELECT * FROM " + TABLE_NAME + " LIMIT ?,?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setLong(1, start);
            ps.setLong(2, num);

            rs = ps.executeQuery();
            while (rs.next()) {
                User item = new User();
                item.setId(rs.getLong(COL_ID));
                item.setUsername(rs.getString(COL_USERNAME));
                item.setPass(rs.getString(COL_PASSWORD));
                item.setEmail(rs.getString(COL_EMAIL));
                item.setRoles(rs.getInt(COL_ROLES));
                result.add(item);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return result;
    }

    public ArrayList<User> getList(long start, long num, String key) {
        ArrayList<User> result = new ArrayList<>();
        Connection connection = DBConnect.getConnection();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE "
                + COL_USERNAME + " like ? OR "
                + COL_EMAIL + " like ?  LIMIT ?,?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ps.setString(2, "%" + key + "%");
            ps.setLong(4, start);
            ps.setLong(5, num);

            rs = ps.executeQuery();
            while (rs.next()) {
                User item = new User();
                item.setId(rs.getLong(COL_ID));
                item.setUsername(rs.getString(COL_USERNAME));
                item.setPass(rs.getString(COL_PASSWORD));
                item.setEmail(rs.getString(COL_EMAIL));
                item.setRoles(rs.getInt(COL_ROLES));
                result.add(item);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return result;
    }

    public User getItem(long id) {
        User item = null;
        Connection connection = DBConnect.getConnection();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_ID + " = ? ";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                item = new User();
                item.setId(rs.getLong(COL_ID));
                item.setUsername(rs.getString(COL_USERNAME));
                item.setPass(rs.getString(COL_PASSWORD));
                item.setEmail(rs.getString(COL_EMAIL));
                item.setRoles(rs.getInt(COL_ROLES));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return item;
    }

    public User getItem(String user, String pass) {
        User item = null;
        Connection connection = DBConnect.getConnection();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE ("
                + COL_USERNAME + "=? AND " 
                + COL_PASSWORD + "=?) OR (" 
                + COL_EMAIL + "=? AND " 
                + COL_PASSWORD + "=?)";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.setString(3, user);
            ps.setString(4, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                item = new User();
                item.setId(rs.getLong(COL_ID));
                item.setUsername(rs.getString(COL_USERNAME));
                item.setPass(rs.getString(COL_PASSWORD));
                item.setEmail(rs.getString(COL_EMAIL));
                item.setRoles(rs.getInt(COL_ROLES));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return item;
    }

    public User getItem(String user) {
        User item = null;
        Connection connection = DBConnect.getConnection();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE "
                + COL_USERNAME + " = ? OR "
                + COL_EMAIL + " = ? ";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, user);
            rs = ps.executeQuery();
            while (rs.next()) {
                item = new User();
                item.setId(rs.getLong(COL_ID));
                item.setUsername(rs.getString(COL_USERNAME));
                item.setPass(rs.getString(COL_PASSWORD));
                item.setEmail(rs.getString(COL_EMAIL));
                item.setRoles(rs.getInt(COL_ROLES));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return item;
    }

    public User getItemByUser(String user) {
        User item = null;
        Connection connection = DBConnect.getConnection();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE "
                + COL_USERNAME + " = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, user);
            rs = ps.executeQuery();
            while (rs.next()) {
                item = new User();
                item.setId(rs.getLong(COL_ID));
                item.setUsername(rs.getString(COL_USERNAME));
                item.setPass(rs.getString(COL_PASSWORD));
                item.setEmail(rs.getString(COL_EMAIL));
                item.setRoles(rs.getInt(COL_ROLES));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return item;
    }

    public User getItemByEmail(String user) {
        User item = null;
        Connection connection = DBConnect.getConnection();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE "
                + COL_EMAIL + " = ? ";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, user);
            rs = ps.executeQuery();
            while (rs.next()) {
                item = new User();
                item.setId(rs.getLong(COL_ID));
                item.setUsername(rs.getString(COL_USERNAME));
                item.setPass(rs.getString(COL_PASSWORD));
                item.setEmail(rs.getString(COL_EMAIL));
                item.setRoles(rs.getInt(COL_ROLES));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return item;
    }

    public long countItems() {
        long result = -1;
        Connection connection = DBConnect.getConnection();
        String sql = "SELECT count(*) FROM " + TABLE_NAME;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getLong(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return result;
    }

    public long countItems(String key) {
        long result = -1;
        Connection connection = DBConnect.getConnection();
        String sql = "SELECT count(*) FROM " + TABLE_NAME + " WHERE "
                + COL_USERNAME + " like ? OR "
                + COL_EMAIL + " like ? ";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ps.setString(2, "%" + key + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getLong(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ArrayList<User> users = new UserDAO().getList(0, 5);
        JSONObject data = new JSONObject();
        for (User u : users) {
            System.out.println(u.toString());
            JSONObject jObj = new JSONObject();
            jObj.put("id", u.getId());
            jObj.put("username", u.getUsername());
            jObj.put("password", u.getPass());
            jObj.put("roles", u.getRoles());
            data.put(u.getId(), jObj);
        }
        System.out.println(data.toString());

        // test count item with key
        System.out.println(new UserDAO().getItemByUser("b").toString());

    }

}
