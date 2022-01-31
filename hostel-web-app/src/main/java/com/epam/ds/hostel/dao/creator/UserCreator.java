package com.epam.ds.hostel.dao.creator;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.ds.hostel.bean.builder.UserBuilderInstance;
import com.epam.ds.hostel.entity.User;
import com.epam.ds.hostel.entity.UserDetail;
import com.epam.ds.hostel.entity.UserRole;

public class UserCreator {
	
	private final static UserCreator instance = new UserCreator();
	
	private UserCreator() {}
	
	public static UserCreator getInstance() {
		return instance;
	}
	
	public User create(ResultSet resultSet) throws SQLException {
		User.Builder userBuilder = UserBuilderInstance.getInstance().getUserBuilder();
		int id = resultSet.getInt(1);
		String login = resultSet.getString(2);
		// String password = resultSet.getString(3);
		int status = resultSet.getInt(4);
		UserRole idRole = UserRole.values()[resultSet.getInt(5)];
		int idUser = resultSet.getInt(6);
		String name = resultSet.getString(7);
		String surname = resultSet.getString(8);
		String phoneNumber = resultSet.getString(9);
		String email = resultSet.getString(10);
		Double reiting = resultSet.getDouble(11);
		String passporNumber = resultSet.getString(12);
		String nationality = resultSet.getString(13);
		Date dateOfBirth = resultSet.getDate(14);
		Date passportIssue = resultSet.getDate(15);
		Date passportExpire = resultSet.getDate(16);
		String address = resultSet.getString(17);
		String image = resultSet.getString(18);

		UserDetail userDetail = new UserDetail();
		userDetail.setUserId(idUser);
		userDetail.setName(name);
		userDetail.setSurname(surname);
		userDetail.setPhoneNumber(phoneNumber);
		userDetail.setEmail(email);
		userDetail.setReiting(reiting);
		userDetail.setPassportDateOfExpire(passportExpire);
		userDetail.setDateOfBirth(dateOfBirth);
		userDetail.setPassportDateOfIssue(passportIssue);
		userDetail.setAddress(address);
		userDetail.setPassportNumber(passporNumber);
		userDetail.setNationality(nationality);
		userDetail.setImage(image);
		User user = userBuilder.id(id).login(login).status(status).idRole(idRole).userDetail(userDetail)
				.build();
		userBuilder.clear();
		return user;
	}

}
