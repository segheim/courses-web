package org.example.listener;

import org.example.entity.Role;
import org.example.entity.User;
import org.example.service.RoleService;
import org.example.service.Service;
import org.example.service.UserService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Set;

@WebListener
public class FillRepositoryListener implements ServletContextListener {

    private final UserService userService = new UserService();
    private final Service<Role> roleService = new RoleService();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        roleService.add(new Role(null, "ADMIN"));
        roleService.add(new Role(null, "USER"));

        userService.add(new User(null, "1", "admin", Set.of(roleService.findById(1L))));
        userService.add(new User(null, "2", "user", Set.of(roleService.findById(2L))));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
