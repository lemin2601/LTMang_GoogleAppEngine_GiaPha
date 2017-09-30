/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import library.DBConnect;
import model.Key;


/**
 *
 * @author Admin
 */
public class KeyDAO {

    private static final String TABLE_NAME = "active_user";
    private static final String COL_ID = "id";
    private static final String COL_KEYUSER = "key_user";
    private static final String COL_IDUSER = "id_user";

    public int addItem(Key item) {
        int result = 0;
        String sql = "INSERT INTO " + TABLE_NAME + "("
                + COL_ID + ","
                + COL_KEYUSER + ","
                + COL_IDUSER + ") VALUES ( ?, ?, ?)";
        Connection connection = DBConnect.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setLong(1, item.getId());
            ps.setString(2, item.getKey());
            ps.setLong(3, item.getId_user());
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

    public int deleteItem(Key item) {
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

    public Key getItem(long idUser) {
        Key item = null;
        Connection connection = DBConnect.getConnection();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_IDUSER + " = ? ";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setLong(1, idUser);
            rs = ps.executeQuery();
            while (rs.next()) {
                item = new Key();
                item.setId(rs.getLong(COL_ID));
                item.setKey(rs.getString(COL_KEYUSER));
                item.setId_user(rs.getLong(COL_IDUSER));
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

    public Key getByKey(String key) {
        Key item = null;
        Connection connection = DBConnect.getConnection();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_KEYUSER + " = ? ";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, key);
            rs = ps.executeQuery();
            while (rs.next()) {
                item = new Key();
                item.setId(rs.getLong(COL_ID));
                item.setKey(rs.getString(COL_KEYUSER));
                item.setId_user(rs.getLong(COL_IDUSER));
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

    public static void main(String[] args) {
        Key item = new Key(System.currentTimeMillis(), 1, "0A3648-FCE262-CD2430-B4D7EA-96BEB4-1D0C87-846F");
        System.out.println(new KeyDAO().addItem(item));
        System.out.println(new KeyDAO().getItem(1));

    }

}
