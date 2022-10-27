package controller;

import DAO.DataCovidDAO;
import model.DataCovidViewModel;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "GetDataServlet", value = "/covid-info")
public class GetDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String date = request.getParameter("date");
        String isDynamo = request.getParameter("dynamoDB");
        if(date == null || date.equals(""))
            date = "01-01-2022";
        ArrayList<DataCovidViewModel> dataCovidViewModels;
        if(isDynamo == null)
            dataCovidViewModels = DataCovidDAO.GetAllByDate(date);
        else {
            request.setAttribute("dynamoDB","true");
            dataCovidViewModels = DataCovidDAO.GetAllByDateDynamoDB(date);
        }
        if(dataCovidViewModels == null){
            request.setAttribute("error","true");
            dataCovidViewModels = new ArrayList<>();
        }
        request.setAttribute("covids",dataCovidViewModels);

        request.getRequestDispatcher("/covid-info.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        DataCovidDAO.Host = request.getParameter("hostname");
        DataCovidDAO.Port = request.getParameter("port");
        DataCovidDAO.Type = request.getParameter("type");
        DataCovidDAO.DriveName = request.getParameter("drivername");
        DataCovidDAO.Name = request.getParameter("username");
        DataCovidDAO.Password = request.getParameter("password");
        DataCovidDAO.DBName = request.getParameter("DBName");

        response.sendRedirect(request.getContextPath() + "/covid-info");
    }
}
