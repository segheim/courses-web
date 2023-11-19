package org.example.servlets;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractServlet extends HttpServlet {

    private Gson gson;

    @Override
    public void init() throws ServletException {
        this.gson = new Gson();
    }

    public void sendResp(HttpServletResponse response, Object o, int code) throws IOException {
        String line = gson.toJson(o);
        response.getWriter().write(line);
        response.setStatus(code);
        response.setContentType("application/json");
    }

    public <T> T getFromRequest(HttpServletRequest request, Class<T> clazz) {
        String res = request.getAttribute("body").toString();
        return gson.fromJson(res, clazz);
    }

}
