package test.servicetest;

import classes.kpi.model.dao.DAOAdmin;
import classes.kpi.model.dao.entities.Admin;
import classes.kpi.model.services.AdminService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceTest {
    DAOAdmin daoAdmin;

    @Before
    public void init(){
        daoAdmin = mock(DAOAdmin.class);
    }

    @Test
    public void getAdminTest0(){
        AdminService adminService = new AdminService(daoAdmin);
        Admin admin = new Admin();
        admin.setAdminID(1);
        admin.setAdminName("username");
        admin.setPassword("password");

        when(daoAdmin.getWhereOne(" WHERE adminName = 'username' AND password = 'password' ")).thenReturn(admin);

        Admin res = adminService.getAdmin("username","password");
        assertEquals(admin.getAdminID(),res.getAdminID());
        assertEquals(admin.getAdminName(),res.getAdminName());

        verify(daoAdmin).getWhereOne(" WHERE adminName = 'username' AND password = 'password' ");
    }

    @Test(expected = RuntimeException.class)
    public void getAdminTest1(){
        AdminService adminService = new AdminService(daoAdmin);

        doThrow(new RuntimeException()).when(daoAdmin).getWhereOne(" WHERE adminName = 'username1' AND password = 'password' ");

        adminService.getAdmin("username1","password");

        verify(daoAdmin).getWhereOne(" WHERE adminName = 'username1' AND password = 'password' ");
    }
}
