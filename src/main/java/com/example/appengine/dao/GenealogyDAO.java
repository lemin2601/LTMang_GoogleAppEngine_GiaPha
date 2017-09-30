package com.example.appengine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.appengine.library.DBConnect;
import com.example.appengine.model.Genealogy;

public class GenealogyDAO {
	   	private static final String TABLE_NAME = "genealogy";
	    private static final String COL_ID = "id_genealogy";
	    private static final String COL_NAME = "name";
	    private static final String COL_NOTE = "note";
	    public int addItem(Genealogy item) {
	        int result = 0;
	        String sql = "INSERT INTO " + TABLE_NAME + "("
	                + COL_ID + ","
	                + COL_NAME + ","
	                + COL_NOTE+ ") VALUES ( ?, ?, ?)";
	        Connection connection = DBConnect.getConnection();
	        PreparedStatement ps = null;
	        try {
	            ps = connection.prepareStatement(sql);
	            ps.setLong(1, item.getId());
	            ps.setString(2, item.getName());
	            ps.setString(3, item.getDescription());
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

	    public int addItem(Genealogy item, long id_user) {
	    	addItem(item);
	        int result = 0;
	        String sql = "INSERT INTO " + "user_genealogy" + "("
	                + "id_user" + ","
	                + "id_genealogy ) VALUES (?, ?)";
	        Connection connection = DBConnect.getConnection();
	        PreparedStatement ps = null;
	        try {
	            ps = connection.prepareStatement(sql);
	            ps.setLong(1, id_user);
	            ps.setLong(2, item.getId());
	            
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
	    
	    public int editItem(Genealogy item) {
	        int result = 0;
	        String sql = "UPDATE " + TABLE_NAME + " SET "
	                + COL_NAME + " = ?, "
	                + COL_NOTE + " = ? WHERE "
	                + COL_ID + " = ?";
	        Connection connection = DBConnect.getConnection();
	        PreparedStatement ps = null;
	        try {
	            ps = connection.prepareStatement(sql);
	            ps.setString(1, item.getName());
	            ps.setString(2, item.getDescription());
	            ps.setLong(3, item.getId());
	            result = ps.executeUpdate();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        return result;
	    }

	    public int deleteItem(Genealogy item) {
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

	    public ArrayList<Genealogy> getList() {
	        ArrayList<Genealogy> result = new ArrayList<>();
	        Connection connection = DBConnect.getConnection();
	        String sql = "SELECT * FROM " + TABLE_NAME;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        try {
	            ps = connection.prepareStatement(sql);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	                Genealogy item = new Genealogy();
	                item.setId(rs.getLong(COL_ID));
	                item.setName(rs.getString(COL_NAME));
	                item.setDescription(rs.getString(COL_NOTE));
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
	    
	    public ArrayList<Genealogy> getList(long id_user) {
	        ArrayList<Genealogy> result = new ArrayList<>();
	        Connection connection = DBConnect.getConnection();
	        String sql = "SELECT genealogy.id_genealogy,genealogy.name,genealogy.note FROM user_genealogy LEFT JOIN genealogy ON user_genealogy.id_genealogy = genealogy.id_genealogy WHERE user_genealogy.id_user = ? ORDER BY genealogy.id_genealogy DESC";
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        try {
	            ps = connection.prepareStatement(sql);
	            ps.setLong(1, id_user);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	                Genealogy item = new Genealogy();
	                item.setId(rs.getLong(COL_ID));
	                item.setName(rs.getString(COL_NAME));
	                item.setDescription(rs.getString(COL_NOTE));
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

	    public ArrayList<Genealogy> getList(long start, long num, String key) {
	        ArrayList<Genealogy> result = new ArrayList<>();
	        Connection connection = DBConnect.getConnection();
	        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE "
	                + COL_NAME + " like ? OR "
	                + COL_NOTE + " like ?  LIMIT ?,?";
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        try {
	            ps = connection.prepareStatement(sql);
	            ps.setString(1, "%" + key + "%");
	            ps.setString(2, "%" + key + "%");
	            ps.setLong(3, start);
	            ps.setLong(4, num);

	            rs = ps.executeQuery();
	            while (rs.next()) {
	                Genealogy item = new Genealogy();
	                item.setId(rs.getLong(COL_ID));
	                item.setName(rs.getString(COL_NAME));
	                item.setDescription(rs.getString(COL_NOTE));
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

	    public Genealogy getLastItem(long id_user) {
	        Genealogy item = null;
	        Connection connection = DBConnect.getConnection();
	        String sql = "SELECT genealogy.id_genealogy,genealogy.name,genealogy.note FROM user_genealogy LEFT JOIN genealogy ON user_genealogy.id_genealogy = genealogy.id_genealogy WHERE user_genealogy.id_user = ? ORDER BY genealogy.id_genealogy DESC LIMIT 1 ";
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        try {
	            ps = connection.prepareStatement(sql);
	            ps.setLong(1, id_user);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	            	item = new Genealogy();
	                item.setId(rs.getLong(COL_ID));
	                item.setName(rs.getString(COL_NAME));
	                item.setDescription(rs.getString(COL_NOTE));
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
	    
	    public Genealogy getItem(long id) {
	        Genealogy item = null;
	        Connection connection = DBConnect.getConnection();
	        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_ID + " = ? ";
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        try {
	            ps = connection.prepareStatement(sql);
	            ps.setLong(1, id);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	            	item = new Genealogy();
	                item.setId(rs.getLong(COL_ID));
	                item.setName(rs.getString(COL_NAME));
	                item.setDescription(rs.getString(COL_NOTE));
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
//			GenealogyDAO dao = new GenealogyDAO();
//			Genealogy g = new Genealogy(1, "h", "ta quang hoang");
//			dao.addItem(g);
//			dao.editItem(g);
//			System.out.println(dao.getItem(1).toString());
//			 ArrayList<Genealogy> result = new ArrayList<>();
//			 result = dao.getList();
//			 for(Genealogy i : result){
//				 System.out.println(i.toString());
//			 }	
//			 dao.deleteItem(1);
			//System.out.println(dao.getLastItem((long)1495354524706).toString());
		}
}
