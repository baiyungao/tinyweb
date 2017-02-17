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

@WebServlet(value="/login", name="LoginServlet")
public class LoginServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        String userid=request.getParameter("user");


        String pwd = TinyDB.getDefault().getPassword(userid);

        response.getWriter().println(pwd);

    }
}