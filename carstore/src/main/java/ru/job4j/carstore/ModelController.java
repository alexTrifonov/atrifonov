package ru.job4j.carstore;

import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

/**
 * Fill select model.
 * @author atrifonov.
 * @version 1.
 * @since 06.03.2018.
 */
public class ModelController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String makeCarStr = req.getParameter("makeSelect");
        MakeCar makeCar;
        if (makeCarStr.isEmpty()) {
            List<MakeCar> list = MakeStore.INSTANCE.getMakes();
            makeCar = list.iterator().next();
        } else {
            makeCar = MakeStore.INSTANCE.getMakeCar(makeCarStr);
        }
        List<AutoModel> autoModels = ModelStore.INSTANCE.getModels(makeCar);

        ObjectMapper mapper = new ObjectMapper();
        writer.append("[");
        StringBuilder sb = new StringBuilder();
        if (autoModels.size() > 0) {
            Iterator<AutoModel> iterator = autoModels.iterator();
            sb.append(mapper.writeValueAsString(iterator.next()));
            while (iterator.hasNext()) {
                sb.append(",");
                sb.append(mapper.writeValueAsString(iterator.next()));
            }
        }
        writer.append(sb.toString());
        writer.append("]");
        writer.flush();
    }
}
