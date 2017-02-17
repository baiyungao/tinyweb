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

@WebServlet(value="/check", name="CheckPointServlet")
public class CheckPointServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        String userid=request.getParameter("user");
        String point = request.getParameter("points");

        int total = TinyDB.getDefault().addPoints(userid, Integer.parseInt(point));

        response.getWriter().println(userid + " total points: " + total);

    }
}