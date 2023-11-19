package org.example.servlets;

import org.example.entity.Role;
import org.example.service.RoleService;
import org.example.service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/role")
public class RoleServlet extends AbstractServlet {

    private Service<Role> service;

    @Override
    public void init() throws ServletException {
        super.init();
        this.service = new RoleService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Role role = service.findById(id);
        sendResp(resp, role, 200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Role roleRequest = getFromRequest(req, Role.class);
        Role role = service.add(roleRequest);
        sendResp(resp, role, 201);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Role roleRequest = getFromRequest(req, Role.class);
        Role role = service.update(roleRequest);
        sendResp(resp, role, 201);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        boolean result = service.remove(id);
        if (result) {
            req.getSession().invalidate();
            sendResp(resp, result, 201);
        }
        sendResp(resp, result, 500);
    }
}
