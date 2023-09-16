package com.database;

//Modified

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.net.DatabaseHelper;

import com.helper.StringHelper;

public class DBUtils /* extends DatabaseHelper */ {

	public static void updateMails() {
		String query = "SELECT * FROM `emailmaster`";
		ArrayList list = (ArrayList) getMapList(query);
		query = "update emailmaster set erecipient=? where eid=?";
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			HashMap object = (HashMap) iterator.next();
			String rec = StringHelper.n2s(object.get("erecipient"));
			String eid = StringHelper.n2s(object.get("erecipient"));

			// System.out.println(rec);
			if (rec.toLowerCase().indexOf(">") != -1) {

				rec = rec.replace(">", ">#");

			} else if (rec.toLowerCase().indexOf(".com") != -1) {

				rec = rec.replace(".com", ".com#");

			} else if (rec.toLowerCase().indexOf(".in") != -1) {
				rec = rec.replace(".in", ".in#");

			}
			if (rec.length() > 0) {
				rec = rec.substring(0, rec.length() - 1);
				executeUpdate(query, new Object[] { rec, eid });
			}
			System.out.println(rec);
		}
	}

	
	public static int executeUpdate(String query, Object... param) {
		Connection conn = null;
		int beans = 0;
		try {
			conn = ConnectionManager.getDBConnection();
			QueryRunner qRunner = new QueryRunner();
			if (param != null) {
				beans = qRunner.update(conn, query, param);
			} else {
				beans = qRunner.update(conn, query);
			}
			// System.out.println("Executing " + query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(conn);
		}
		return beans;
	}

	public static int executeUpdate(String query, HashMap data) {
		String str[] = query.split("=");
		Object param[] = new Object[str.length - 1];
		for (int i = 0; i < str.length; i++) {
			int ind = str[i].lastIndexOf(",");
			if (ind < 0) {
				ind = str[i].lastIndexOf(" ");
			}
			if (ind >= 0) {
				String colname = str[i].substring(ind + 1).trim();
				String value = StringHelper.n2s(data.get(colname));
				param[i] = value;
				System.out.println(colname + ":" + value);
			}

		}
		return executeUpdate(query, param);
	}

	public static boolean saveImageInDB(byte imagedata[], String query) {
		Connection connection = ConnectionManager.getDBConnection();
		java.sql.PreparedStatement psmnt;
		boolean success = false;

		try {
			ByteArrayInputStream inStream = new ByteArrayInputStream(imagedata);
			psmnt = connection.prepareStatement(query);
			psmnt.setBinaryStream(1, inStream, imagedata.length);
			int i = psmnt.executeUpdate();
			success = true;
			System.out.println("Image Uploaded To Database ...");
			psmnt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			success = false;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return success;
	}

	
	
	
	// String sql = "SELECT img FROM Images where imageId=" + imageId;
	public static BufferedImage getImageFromDB(String query) {
		String sql = query;
		BufferedImage image = null;
		Connection c = null;
		try {
			c = ConnectionManager.getDBConnection();
			Statement stmt;
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				java.sql.Blob b = rs.getBlob(1);
				InputStream in = b.getBinaryStream();
				int length = 0;
				int bufferSize = 4096;
				byte[] buffer = new byte[bufferSize];
				try {
					image = ImageIO.read(b.getBinaryStream());
				} catch (Exception ex) {
					System.out.println("error is " + ex);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (c != null) {
					c.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return image;
		}
	}

	public static void main(String ar[]) {
		updateMails();
	}

	public static List getMapList(String query) {
		Connection conn = null;
		List beans = null;
		try {
			conn = ConnectionManager.getDBConnection();

			QueryRunner qRunner = new QueryRunner();
			beans = (List) qRunner.query(conn, query, new MapListHandler());
			 System.out.println("Executing " + query);
		} catch (SQLException e) {
			// handle the exception
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(conn);
		}
		return beans;
	}

	public static List getMapList(String query, Object... param) {
		Connection conn = null;
		List beans = null;
		try {
			conn = ConnectionManager.getDBConnection();

			QueryRunner qRunner = new QueryRunner();
			beans = (List) qRunner.query(conn, query, param,
					new MapListHandler());
			System.out.println("Executing " + query);
		} catch (SQLException e) {
			// handle the exception
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(conn);
		}
		return beans;
	}

	public static List getBeanList(Class cls, String query) {
		Connection conn = null;
		List beans = null;
		try {
			conn = ConnectionManager.getDBConnection();

			QueryRunner qRunner = new QueryRunner();
			beans = (List) qRunner.query(conn, query, new BeanListHandler(cls));
			System.out.println("Executing " + query);
		} catch (SQLException e) {
			// handle the exception
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(conn);
		}
		return beans;
	}

	// pass parameters in hashmap.
	// parameter names should be similar to database column name
	// sql query should contain column names

	public static int runInsertQuery(String sql, HashMap pageParameters) {
		return executeUpdate(sql, processQuery(sql, pageParameters));
	}

	public static Object[] processQuery(String sql, HashMap pageParameters) {
		String params = sql.substring(sql.indexOf("(") + 1, sql.indexOf(")"));
		String paramarray[] = params.split(",");
		Object[] param = new Object[paramarray.length];
		for (int i = 0; i < paramarray.length; i++) {
			String key = StringHelper.n2s(paramarray[i]).toLowerCase();
			String value = StringHelper.n2s(pageParameters.get(key));
			param[i] = value;
		}

		return param;
	}

	public static List getBeanList(Class cls, String query, Object... param) {
		Connection conn = null;
		List beans = null;
		try {
			conn = ConnectionManager.getDBConnection();

			QueryRunner qRunner = new QueryRunner();
			beans = (List) qRunner.query(conn, query, new BeanListHandler(cls),	param);
			System.out.println("Executing " + query);
		} catch (SQLException e) {
			// handle the exception
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(conn);
		}
		return beans;
	}

	public static List getParameterizedList(String query, Object... param) {
		Connection conn = null;
		List beans = null;
		try {
			conn = ConnectionManager.getDBConnection();

			QueryRunner qRunner = new QueryRunner();
			beans = (List) qRunner.query(conn, query, new MapListHandler(),
					param);
			System.out.println("Executing " + query);
		} catch (SQLException e) {
			// handle the exception
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(conn);
		}
		return beans;
	}

	public static String getMaxValueStr(String query) {

		String success = "";
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = ConnectionManager.getDBConnection();
			rs = conn.createStatement().executeQuery(query);
			if (rs.next()) {
				success = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(conn);
		}
		return success;
	}

	public static int getMaxValueInt(String query) {

		int success = -1;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = ConnectionManager.getDBConnection();
			rs = conn.createStatement().executeQuery(query);
			if (rs.next()) {
				success = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return success;
	}
	public static HashMap getDBCombo(String query) {

		int success = -1;
		Connection conn = null;
		ResultSet rs = null;
		HashMap val=new HashMap();
		try {
			
			conn = ConnectionManager.getDBConnection();
			rs = conn.createStatement().executeQuery(query);
			while(rs.next()) {
				val.put(rs.getString(1), rs.getString(2));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return val;
	}

	public static boolean dataExists(String query) {

		boolean success = false;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = ConnectionManager.getDBConnection();
			rs = conn.createStatement().executeQuery(query);
			System.out.println("Executing " + query);
			if (rs.next()) {
				success = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return success;
	}
}
