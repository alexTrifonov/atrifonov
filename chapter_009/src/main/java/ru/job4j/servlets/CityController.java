package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 * Class for get list of cities with ajax.
 * @author atrifonov.
 * @version 1.
 * @since 24.01.2018.
 */
public class CityController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String countryName = req.getParameter("countrySelect");
        Country country = CountryStore.INSTANCE.getCountry(countryName);

        LinkedList<City> cityList = (LinkedList<City>) CityStore.INSTANCE.getCities(country);
        writer.append("[");

        for (City city: cityList) {

            writer.append("{").append("\"id\" : ").append(String.format("%d, ", city.getId()));
            writer.append("\"nameCity\" : ").append("\"").append(city.getNameCity()).append("\"}");
            if (!city.getNameCity().equals(cityList.getLast().getNameCity())) {
                writer.append(", ");
            }
        }
        writer.append("]");
        writer.flush();
    }
}
