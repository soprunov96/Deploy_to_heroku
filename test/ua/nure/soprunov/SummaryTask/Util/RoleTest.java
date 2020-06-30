package ua.nure.soprunov.SummaryTask.Util;

import org.junit.Test;
import ua.nure.soprunov.SummaryTask.dao.entity.User;

import static org.junit.Assert.*;

public class RoleTest {

    @Test
    public void getRole() {
        User user = new User();
        user.setRoleId(1);
        assertEquals(Role.DISPATCHER, Role.getRole(user));
    }

    @Test
    public void showUserRole() {
        assertEquals("admin", Role.showUserRole("0"));
    }
}