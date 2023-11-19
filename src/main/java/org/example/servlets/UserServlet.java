package org.example.servlets;

import org.example.entity.User;
import org.example.service.Service;
import org.example.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends AbstractServlet{

    private Service<User> service;

    @Override
    public void init() throws ServletException {
        super.init();
        this.service = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        User user = service.findById(id);
        sendResp(resp, user, 200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getFromRequest(req, User.class);
        User createUser = service.add(user);
        sendResp(resp, createUser, 201);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = getFromRequest(req, User.class);
        User updateUser = service.update(user);
        req.getSession().setAttribute("roles", updateUser.getRole());
        sendResp(resp, updateUser, 201);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        boolean result = service.remove(id);
        if(result) {
            req.getSession().invalidate();
            sendResp(resp, result, 201);
        }
        sendResp(resp, result, 500);
    }
}
