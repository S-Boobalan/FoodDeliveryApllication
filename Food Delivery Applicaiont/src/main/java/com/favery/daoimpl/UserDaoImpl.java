package com.favery.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.favery.dao.UserDao;
import com.favery.model.User;
import com.favery.util.MyConnection;

public class UserDaoImpl implements UserDao {
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet res = null;
	private Statement stmt = null;
	private List<User> usersList = new ArrayList<User>();
	private User user = null;

	private static final String INSERT_QUERY = "INSERT INTO `user` (`username`, `email`, `password`, `phoneNumber`, `address`) VALUES (?, ?, ?, ?, ?)";
	private static final String SELECT_QUERY = "SELECT * FROM `user` WHERE `email` = ?";
	private static final String UPDATE_QUERY = "UPDATE `user` SET `username` = ?, `email` = ?, `password` = ?, `phoneNumber` = ?, `address` = ? WHERE `userId` = ?";
	private static final String DELETE_QUERY = "DELETE FROM `user` WHERE `email` = ?";
	private static final String SELECT_ALL_QUERY = "SELECT * FROM `user`";

	int status = 0;

	public UserDaoImpl() {
		con = MyConnection.getInstance().getConnection();
	}

	@Override
	public int addUser(User user) {
		try {
			pstmt = con.prepareStatement(INSERT_QUERY);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getPhoneNumber());
			pstmt.setString(5, user.getAddress());
			status = pstmt.executeUpdate();
		} catch (SQLException e) {
			return status;
		}
		return status;
	}

	@Override
	public User getUser(String email) {
		try {
			pstmt = con.prepareStatement(SELECT_QUERY);
			pstmt.setString(1, email);
			res = pstmt.executeQuery();
			usersList = extractUserFromResultSet(res);
			if (!usersList.isEmpty()) {
				user = usersList.get(0);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public int updateUser(User user) {
		try {
			pstmt = con.prepareStatement(UPDATE_QUERY);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getPhoneNumber());
			pstmt.setString(5, user.getAddress());
			pstmt.setInt(6, user.getUserId());
			status = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public int deleteUser(String email) {
		try {
			pstmt = con.prepareStatement(DELETE_QUERY);
			pstmt.setString(1, email);
			status = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public List<User> getAllUsers() {
		try {
			stmt = con.createStatement();
			res = stmt.executeQuery(SELECT_ALL_QUERY);
			usersList = extractUserFromResultSet(res);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usersList;
	}
	
	public List<User> extractUserFromResultSet(ResultSet res) {
		try {
			while (res.next()) {
				int userId = res.getInt("userId");
				String username = res.getString("username");
				String email = res.getString("email");
				String password = res.getString("password");
				String phoneNumber = res.getString("phoneNumber");
				String address = res.getString("address");
				user = new User(userId, username, email, password, phoneNumber, address);
				usersList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usersList;
	}

}
