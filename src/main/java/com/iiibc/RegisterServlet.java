package com.iiibc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gaob on 2/16/2017.
 */

@WebServlet(value="/register", name="RegisterServlet")
public class RegisterServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request,
                              HttpServletResponse response) throws ServletException, IOException {

        String userid=request.getParameter("user");
        String pwd = request.getParameter("pwd");

        TinyDB.getDefault().addUser(userid,pwd);

        response.getWriter().println("Welcome " + userid);

        }
 }


