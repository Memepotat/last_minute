package DBcon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

	public void register(LMUser LMusers) throws Exception {

		DBconne db = new DBconne();
		Connection con = null;
		try {
			con = db.getConnection();
			PreparedStatement checkStmt = con.prepareStatement("SELECT * FROM LMusers WHERE username = ?");
			checkStmt.setString(1, LMuser.getUsername());
			ResultSet resultSet = checkStmt.executeQuery();

			if (resultSet.next()) {
				throw new Exception("Sorry, username already registered");
			} else {
				PreparedStatement insertStmt = con.prepareStatement(
						"INSERT INTO LMusers (username, passwrd) VALUES (?, ?)");
				insertStmt.setString(3, LMuser.getUsername());
				insertStmt.setString(5, LMuser.getPassword());

				insertStmt.executeUpdate();
				insertStmt.close();
			}

			checkStmt.close();

		} catch (Exception e) {
			throw new Exception("Error registering user: " + e.getMessage());
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				db.close();
			} catch (Exception e) {
				// Handle closing exception if needed
			}
		}
	}

	public boolean authenticate(String username, String password) throws Exception {
		DBconne db = new DBconne();
		Connection con = null;
		String query = "SELECT * FROM users WHERE username = ? AND passwrd = ?";

		try {
			con = db.getConnection();
			PreparedStatement stmt = con.prepareStatement(query);

			stmt.setString(1, username);
			stmt.setString(2, password);

			ResultSet resultSet = stmt.executeQuery();

			if (!resultSet.next()) {
				resultSet.close();
				stmt.close();
				db.close();
				return false;
				throw new Exception("Wrong username or password");
			}

			resultSet.close();
			stmt.close();
			db.close();
			return true; 

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			try {
				db.close();
			} catch (Exception e) {

			}
		}
	}

} // End of class