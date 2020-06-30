package ua.nure.soprunov.SummaryTask.Util;

import ua.nure.soprunov.SummaryTask.dao.entity.User;

/**
 * Role entity.
 * 
 * @author Soprunov Igor
 * 
 */


public enum Role {
	ADMIN, DISPATCHER,DRIVER;
	
	public static Role getRole(User user) {
		int roleId = user.getRoleId();
		return Role.values()[roleId];
	}



	public String getName() {
		return name().toLowerCase();
	}
	
	public static String showUserRole(String roleId) {
		switch (roleId) {
		case "0":
			return "admin";
		case "1":
			return "dispatcher";
		case "2":
			return "driver";
		default:
			break;
		}
		return null;
	}
	
}
