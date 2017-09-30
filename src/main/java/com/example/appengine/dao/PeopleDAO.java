package com.example.appengine.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import library.DBConnect;

import model.People;

public class PeopleDAO {
	private static final String TABLE_NAME = "people";
	private static final String COL_ID = "id_people";
	private static final String COL_FIRSTNAME = "first_name";
	private static final String COL_LASTNAME = "last_name";
	private static final String COL_ALIAS = "alias";
	private static final String COL_BIRTHDAY = "birth_day";
	private static final String COL_DEADDAY = "dead_day";
	private static final String COL_SEX = "sex";
	private static final String COL_ADDRESS = "address";
	private static final String COL_ID_GENEALOGY = "id_genealogy";
	private static final String COL_IMG = "img";

	public int additem(People item) {
		int result = 0;
		String sql = "INSERT INTO " + TABLE_NAME + "(" + COL_ID + "," + COL_FIRSTNAME + "," + COL_LASTNAME + ","
				+ COL_ALIAS + "," + COL_BIRTHDAY + "," + COL_DEADDAY + "," + COL_SEX + "," + COL_ADDRESS + ","
				+ COL_ID_GENEALOGY + "," + COL_IMG + ") VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection connection = DBConnect.getConnection();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, item.getId());
			ps.setString(2, item.getFirstname());
			ps.setString(3, item.getLastname());
			ps.setString(4, item.getAlias());
			ps.setDate(5, new java.sql.Date(item.getBirth().getTime()));
			if (item.getDead() != null) {
				ps.setDate(6, new java.sql.Date(item.getDead().getTime()));
			} else {
				ps.setDate(6, null);
			}
			ps.setInt(7, item.getSex());
			ps.setString(8, item.getAddress());
			ps.setLong(9, item.getId_genealogy());
			ps.setBinaryStream(10, item.getImg());
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

	public int additemWithIdFather(People item, long id_father) {
		int result = 0;
		Connection connection = DBConnect.getConnection();
		String sql = "INSERT INTO " + TABLE_NAME + "(" + COL_ID + "," + COL_FIRSTNAME + "," + COL_LASTNAME + ","
				+ COL_ALIAS + "," + COL_BIRTHDAY + "," + COL_DEADDAY + "," + COL_SEX + "," + COL_ADDRESS + ","
				+ COL_ID_GENEALOGY + "," + COL_IMG + ") VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String sql1 = "INSERT INTO relationship (id,id_father) VALUES (?,?)";
		PreparedStatement ps = null;
		try {
			connection.setAutoCommit(false);
			// add to data people
			ps = connection.prepareStatement(sql);
			ps.setLong(1, item.getId());
			ps.setString(2, item.getFirstname());
			ps.setString(3, item.getLastname());
			ps.setString(4, item.getAlias());
			ps.setDate(5, new java.sql.Date(item.getBirth().getTime()));
			if (item.getDead() != null) {
				ps.setDate(6, new java.sql.Date(item.getDead().getTime()));
			} else {
				ps.setDate(6, null);
			}
			ps.setInt(7, item.getSex());
			ps.setString(8, item.getAddress());
			ps.setLong(9, item.getId_genealogy());
			ps.setBinaryStream(10, item.getImg());
			result = ps.executeUpdate();
			// add relationship
			ps = connection.prepareStatement(sql1);
			ps.setLong(1, item.getId());
			ps.setLong(2, id_father);
			result = ps.executeUpdate();
			// commit
			connection.commit();
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

	public int addWifeOfPeople(People item, long id_husband) {
		int result = 0;
		Connection connection = DBConnect.getConnection();
		String sql = "INSERT INTO " + TABLE_NAME + "(" + COL_ID + "," + COL_FIRSTNAME + "," + COL_LASTNAME + ","
				+ COL_ALIAS + "," + COL_BIRTHDAY + "," + COL_DEADDAY + "," + COL_SEX + "," + COL_ADDRESS + ","
				+ COL_ID_GENEALOGY + "," + COL_IMG + ") VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String sql1 = "INSERT INTO marry (id_male,id_female,active) VALUES (?,?,?)";
		PreparedStatement ps = null;
		try {
			connection.setAutoCommit(false);
			// add to data people
			ps = connection.prepareStatement(sql);
			ps.setLong(1, item.getId());
			ps.setString(2, item.getFirstname());
			ps.setString(3, item.getLastname());
			ps.setString(4, item.getAlias());
			ps.setDate(5, new java.sql.Date(item.getBirth().getTime()));
			if (item.getDead() != null) {
				ps.setDate(6, new java.sql.Date(item.getDead().getTime()));
			} else {
				ps.setDate(6, null);
			}
			ps.setInt(7, item.getSex());
			ps.setString(8, item.getAddress());
			ps.setLong(9, item.getId_genealogy());
			ps.setBinaryStream(10, item.getImg());
			result = ps.executeUpdate();
			// add relationship
			ps = connection.prepareStatement(sql1);
			ps.setLong(1, id_husband);
			ps.setLong(2, item.getId());

			ps.setInt(3, 1);
			result = ps.executeUpdate();
			// commit
			connection.commit();
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

	public int editItem(People item) {
		int result = 0;
		String sql = "UPDATE " + TABLE_NAME + " SET " + COL_FIRSTNAME + "= ?," + COL_LASTNAME + "= ?," + COL_ALIAS
				+ "= ?," + COL_BIRTHDAY + "= ?," + COL_DEADDAY + "= ?," + COL_SEX + "= ?," + COL_ADDRESS + "= ? WHERE "
				+ COL_ID + " = ?";
		Connection connection = DBConnect.getConnection();
		PreparedStatement ps = null;
		try {

			ps = connection.prepareStatement(sql);
			ps.setLong(8, item.getId());
			ps.setString(1, item.getFirstname());
			ps.setString(2, item.getLastname());
			ps.setString(3, item.getAlias());
			ps.setDate(4, new java.sql.Date(item.getBirth().getTime()));
			if (item.getDead() != null) {
				ps.setDate(5, new java.sql.Date(item.getDead().getTime()));
			} else {
				ps.setDate(5, null);
			}
			ps.setInt(6, item.getSex());
			ps.setString(7, item.getAddress());

			result = ps.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public int updateItem(People item) {
		int result = 0;
		String sql1 = "DELETE * FROM marry WHERE id_male = ?";
		String sql2 = "DELETE * FROM relationship WHERE id_father = ?";
		String sql3 = "DELETE * FROM people WHERE people.id_people = (SELECT people.id_people FROM people RIGHT JOIN relationship ON relationship.id = people.id_people WHERE relationship.id_father = ?)";
		String sql4 = "DELETE * FROM people WHERE people.id_people = (SELECT people.id_people FROM people RIGHT JOIN marry ON marry.id_female = people.id_people WHERE marry.id_male = ?)";
		String sql = "UPDATE " + TABLE_NAME + " SET " + COL_FIRSTNAME + "= ?," + COL_LASTNAME + "= ?," + COL_ALIAS
				+ "= ?," + COL_BIRTHDAY + "= ?," + COL_DEADDAY + "= ?," + COL_SEX + "= ?," + COL_ADDRESS + "= ?,"
				+ COL_IMG + " = ? WHERE " + COL_ID + " = ?";
		Connection connection = DBConnect.getConnection();
		PreparedStatement ps = null;
		try {
			connection.setAutoCommit(false);
			// if(item.getSex() ==0){
			// ps = connection.prepareStatement(sql4);
			// ps.setLong(1, item.getId());
			// result = ps.executeUpdate();
			//
			// ps = connection.prepareStatement(sql3);
			// ps.setLong(1, item.getId());
			// result = ps.executeUpdate();
			//
			// ps = connection.prepareStatement(sql2);
			// ps.setLong(1, item.getId());
			// result = ps.executeUpdate();
			//
			// ps = connection.prepareStatement(sql1);
			// ps.setLong(1, item.getId());
			// result = ps.executeUpdate();
			// }
			ps = connection.prepareStatement(sql);
			ps.setString(1, item.getFirstname());
			ps.setString(2, item.getLastname());
			ps.setString(3, item.getAlias());
			ps.setDate(4, new java.sql.Date(item.getBirth().getTime()));
			if (item.getDead() != null) {
				ps.setDate(5, new java.sql.Date(item.getDead().getTime()));
			} else {
				ps.setDate(5, null);
			}
			ps.setInt(6, item.getSex());
			ps.setString(7, item.getAddress());
			ps.setBinaryStream(8, item.getImg());
			ps.setLong(9, item.getId());
			result = ps.executeUpdate();
			connection.commit();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public int deleteItem(People item) {
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
		// delete item.
		// delete relation
		String sql = "DELETE FROM relationship WHERE id= ? OR id_father =?";
		// delete marry
		String sql1 = "DELETE FROM marry WHERE id_male= ? OR id_female =?";
		// delete people
		String sql2 = "DELETE FROM " + TABLE_NAME + " WHERE " + COL_ID + " = ?";
		Connection connection = DBConnect.getConnection();
		PreparedStatement ps = null;
		try {
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(sql);
			ps.setLong(1, item);
			ps.setLong(2, item);
			result = ps.executeUpdate();
			ps = connection.prepareStatement(sql1);
			ps.setLong(1, item);
			ps.setLong(2, item);
			result += ps.executeUpdate();
			ps = connection.prepareStatement(sql2);
			ps.setLong(1, item);
			result += ps.executeUpdate();
			connection.commit();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public int deleteAllBranch(long id_people) {
		int result = 0;
		ArrayList<People> list = getAllChildWithIdPeople(id_people);
		// delete item.
		// delete relation
		String sql = "DELETE FROM relationship WHERE id= ? OR id_father =?";
		// delete marry
		String sql1 = "DELETE FROM marry WHERE id_male= ? OR id_female =?";
		// delete people
		String sql2 = "DELETE FROM " + TABLE_NAME + " WHERE " + COL_ID + " = ?";
		Connection connection = DBConnect.getConnection();
		PreparedStatement ps = null;
		try {
			connection.setAutoCommit(false);
			for (People item : list) {
				long id = item.getId();
				ps = connection.prepareStatement(sql);
				ps.setLong(1, id);
				ps.setLong(2, id);
				result += ps.executeUpdate();
				ps = connection.prepareStatement(sql1);
				ps.setLong(1, id);
				ps.setLong(2, id);
				result += ps.executeUpdate();
				ps = connection.prepareStatement(sql2);
				ps.setLong(1, id);
				result += ps.executeUpdate();
			}
//			long id  = id_people;
//			ps = connection.prepareStatement(sql);
//			ps.setLong(1, id);
//			ps.setLong(2, id);
//			result += ps.executeUpdate();
//			ps = connection.prepareStatement(sql1);
//			ps.setLong(1, id);
//			ps.setLong(2, id);
//			result += ps.executeUpdate();
//			ps = connection.prepareStatement(sql2);
//			ps.setLong(1, id);
//			result += ps.executeUpdate();
			connection.commit();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public ArrayList<People> getList() {
		ArrayList<People> result = new ArrayList<>();
		Connection connection = DBConnect.getConnection();
		String sql = "SELECT * FROM " + TABLE_NAME;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				People item = new People();
				item.setId(rs.getLong(COL_ID));
				item.setFirstname(rs.getString(COL_FIRSTNAME));
				item.setLastname(rs.getString(COL_LASTNAME));
				item.setAlias(rs.getString(COL_ALIAS));
				item.setBirth(rs.getDate(COL_BIRTHDAY));
				item.setDead(rs.getDate(COL_DEADDAY));
				item.setSex(rs.getInt(COL_SEX));
				item.setAddress(rs.getString(COL_ADDRESS));
				item.setId_genealogy(rs.getLong(COL_ID_GENEALOGY));
				// item.setImg(rs.getString(COL_IMG));
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

	public ArrayList<People> getList(long start, long num, String key) {
		ArrayList<People> result = new ArrayList<>();
		Connection connection = DBConnect.getConnection();
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_FIRSTNAME + " like ? OR " + COL_LASTNAME
				+ " like ? OR " + COL_ALIAS + " like ? OR " + COL_BIRTHDAY + " like ? OR " + COL_DEADDAY + " like ? OR "
				+ COL_SEX + " like ? OR " + COL_ADDRESS + " like ? OR " + COL_ID_GENEALOGY + " like ?  LIMIT ?,?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, "%" + key + "%");
			ps.setString(2, "%" + key + "%");
			ps.setString(3, "%" + key + "%");
			ps.setString(4, "%" + key + "%");
			ps.setString(5, "%" + key + "%");
			ps.setString(6, "%" + key + "%");
			ps.setString(7, "%" + key + "%");
			ps.setString(8, "%" + key + "%");
			ps.setLong(9, start);
			ps.setLong(10, num);
			rs = ps.executeQuery();
			while (rs.next()) {
				People item = new People();
				item.setId(rs.getLong(COL_ID));
				item.setFirstname(rs.getString(COL_FIRSTNAME));
				item.setLastname(rs.getString(COL_LASTNAME));
				item.setAlias(rs.getString(COL_ALIAS));
				item.setBirth(rs.getDate(COL_BIRTHDAY));
				item.setDead(rs.getDate(COL_DEADDAY));
				item.setSex(rs.getInt(COL_SEX));
				item.setAddress(rs.getString(COL_ADDRESS));
				item.setId_genealogy(rs.getLong(COL_ID_GENEALOGY));
				// item.setImg(rs.getString(COL_IMG));
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

	public People getRootPeopleByGenealogy(long id_genelogy) {
		// SELECT people.* FROM people RIGHT JOIN relationship ON
		// relationship.id = people.id_people WHERE relationship.id_father = -1
		// AND people.id_genealogy = 1495436340121
		People item = null;
		Connection connection = DBConnect.getConnection();
		String sql = "SELECT people.* FROM people RIGHT JOIN relationship ON relationship.id = people.id_people WHERE relationship.id_father = -1 AND people.id_genealogy = ? ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, id_genelogy);
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new People();
				item.setId(rs.getLong(COL_ID));
				item.setFirstname(rs.getString(COL_FIRSTNAME));
				item.setLastname(rs.getString(COL_LASTNAME));
				item.setAlias(rs.getString(COL_ALIAS));
				item.setBirth(rs.getDate(COL_BIRTHDAY));
				item.setDead(rs.getDate(COL_DEADDAY));
				item.setSex(rs.getInt(COL_SEX));
				item.setAddress(rs.getString(COL_ADDRESS));
				item.setId_genealogy(rs.getLong(COL_ID_GENEALOGY));
				// item.setImg(rs.getString(COL_IMG));
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

	public People getItem(long id) {
		People item = null;
		Connection connection = DBConnect.getConnection();
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_ID + " = ? ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				item = new People();
				item.setId(rs.getLong(COL_ID));
				item.setFirstname(rs.getString(COL_FIRSTNAME));
				item.setLastname(rs.getString(COL_LASTNAME));
				item.setAlias(rs.getString(COL_ALIAS));
				item.setBirth(rs.getDate(COL_BIRTHDAY));
				item.setDead(rs.getDate(COL_DEADDAY));
				item.setSex(rs.getInt(COL_SEX));
				item.setAddress(rs.getString(COL_ADDRESS));
				item.setId_genealogy(rs.getLong(COL_ID_GENEALOGY));
				// item.setImg(rs.getString(COL_IMG));
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

	private ArrayList<People> getAllChildWithIdPeople(long id_people) {
		ArrayList<People> result = new ArrayList<>();
		result.addAll(getListWifeByIdHusband(id_people));
		for (People item : getListChildByIdFather(id_people)) {
			result.add(item);
			result.addAll(getAllChildWithIdPeople(item.getId()));
		}
		return result;
	}

	public ArrayList<People> getListPeopleByGenealodyID(long id_genealogy) {
		ArrayList<People> result = new ArrayList<>();
		Connection connection = DBConnect.getConnection();
		String sql = "SELECT people.id_people,people.first_name,people.last_name,people.alias,people.birth_day,people.dead_day,people.sex,people.address,people.id_genealogy,people.img FROM people RIGHT JOIN user_genealogy ON people.id_genealogy = user_genealogy.id_genealogy LEFT JOIN genealogy ON user_genealogy.id_genealogy = genealogy.id_genealogy WHERE genealogy.id_genealogy = ?";

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, id_genealogy);
			rs = ps.executeQuery();
			while (rs.next()) {
				People item = new People();
				item.setId(rs.getLong(COL_ID));
				item.setFirstname(rs.getString(COL_FIRSTNAME));
				item.setLastname(rs.getString(COL_LASTNAME));
				item.setAlias(rs.getString(COL_ALIAS));
				item.setBirth(rs.getDate(COL_BIRTHDAY));
				item.setDead(rs.getDate(COL_DEADDAY));
				item.setSex(rs.getInt(COL_SEX));
				item.setAddress(rs.getString(COL_ADDRESS));
				item.setId_genealogy(rs.getLong(COL_ID_GENEALOGY));
				// item.setImg(rs.getString(COL_IMG));
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

	public ArrayList<People> getListWifeByIdHusband(long id_husband) {
		ArrayList<People> result = new ArrayList<>();
		Connection connection = DBConnect.getConnection();
		String sql = "SELECT * FROM people RIGHT JOIN marry ON marry.id_female = people.id_people WHERE marry.id_male = ?";

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, id_husband);
			rs = ps.executeQuery();
			while (rs.next()) {
				People item = new People();
				item.setId(rs.getLong(COL_ID));
				item.setFirstname(rs.getString(COL_FIRSTNAME));
				item.setLastname(rs.getString(COL_LASTNAME));
				item.setAlias(rs.getString(COL_ALIAS));
				item.setBirth(rs.getDate(COL_BIRTHDAY));
				item.setDead(rs.getDate(COL_DEADDAY));
				item.setSex(rs.getInt(COL_SEX));
				item.setAddress(rs.getString(COL_ADDRESS));
				item.setId_genealogy(rs.getLong(COL_ID_GENEALOGY));
				// item.setImg(rs.getString(COL_IMG));
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

	public ArrayList<People> getListChildByIdFather(long id_father) {
		ArrayList<People> result = new ArrayList<>();
		Connection connection = DBConnect.getConnection();
		String sql = "SELECT * FROM people RIGHT JOIN relationship ON relationship.id = people.id_people WHERE relationship.id_father = ?";

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, id_father);
			rs = ps.executeQuery();
			while (rs.next()) {
				People item = new People();
				item.setId(rs.getLong(COL_ID));
				item.setFirstname(rs.getString(COL_FIRSTNAME));
				item.setLastname(rs.getString(COL_LASTNAME));
				item.setAlias(rs.getString(COL_ALIAS));
				item.setBirth(rs.getDate(COL_BIRTHDAY));
				item.setDead(rs.getDate(COL_DEADDAY));
				item.setSex(rs.getInt(COL_SEX));
				item.setAddress(rs.getString(COL_ADDRESS));
				item.setId_genealogy(rs.getLong(COL_ID_GENEALOGY));
				// item.setImg(rs.getString(COL_IMG));
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

	public ByteArrayOutputStream getImage(long id_people) {
		ByteArrayOutputStream baos = null;
		Connection connection = DBConnect.getConnection();
		String sql = "SELECT people.img From " + TABLE_NAME + " WHERE  " + COL_ID + "=? LIMIT 1";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, id_people);
			rs = ps.executeQuery();
			while (rs.next()) {
				InputStream input = rs.getBinaryStream("img");
				baos = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				while (input.read(buffer) > 0) {
					baos.write(buffer, 0, 1024);
				}
				input.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return baos;
	}

	public static void main(String[] args) {
		PeopleDAO dao = new PeopleDAO();
		ArrayList<People> list = new ArrayList<>();
		list = dao.getAllChildWithIdPeople(Long.parseLong("1495440994378"));
		for (People i : list) {
			System.out.println(i.toString());
		}

		System.out.println("done" + list.size());
		System.out.println("delete:" + dao.deleteAllBranch(Long.parseLong("1495444520551")));
	}
}
