package ru.job4j.carstore;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class AddCar extends HttpServlet {
    public static final String SAVE_DIRECTORY = "uploadDir";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/addCar.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        Car car = new Car();

        Query query;

        query = session.createQuery("from MakeCar where make = :make");
        query.setParameter("make", req.getParameter("makeCar"));
        List<MakeCar> makeCars = query.list();
        MakeCar makeCar = makeCars.iterator().next();

        query = session.createQuery("from AutoModel where model = :model");
        query.setParameter("model", req.getParameter("model"));
        List<AutoModel> autoModels = query.list();
        AutoModel model = autoModels.iterator().next();

        query = session.createQuery("from Body where bodyType =:bodyType");
        query.setParameter("bodyType", req.getParameter("body"));
        List<Body> bodies = query.list();
        Body body = bodies.iterator().next();

        query = session.createQuery("from Transmission where transmType = :transmType");
        query.setParameter("transmType", req.getParameter("transmission"));
        List<Transmission> transmissions = query.list();
        Transmission transmission = transmissions.iterator().next();

        query = session.createQuery("from Engine where engineType = :engineType");
        query.setParameter("engineType", req.getParameter("engine"));
        List<Engine> engines = query.list();
        Engine engine = engines.iterator().next();

        query = session.createQuery("from Drive where driveType = :driveType");
        query.setParameter("driveType", req.getParameter("drive"));
        List<Drive> drives = query.list();
        Drive drive = drives.iterator().next();

        HttpSession httpSession = req.getSession();
        User user = UserStore.INSTANCE.getUser((String)httpSession.getAttribute("login"));
        user.setId((Integer) httpSession.getAttribute("id"));


        String fileName = "";
        try {
            String appPath = req.getServletContext().getRealPath("");
            appPath = appPath.replace('\\', '/');
            String fullSavePath = null;
            if (appPath.endsWith("/")) {
                fullSavePath = appPath + SAVE_DIRECTORY;
            } else {
                fullSavePath = appPath + "/" + SAVE_DIRECTORY;
            }

            File fileSaveDir = new File(fullSavePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdir();
            }

            for (Part part : req.getParts()) {
                fileName = extractFileName(part);
                if (fileName != null && fileName.length() > 0) {
                    String filePath = fullSavePath + File.separator + fileName;
                    part.write(filePath);
                } else {
                    fileName = "";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/addCar.html");
            dispatcher.forward(req, resp);
        }

        car.setMakeCar(makeCar);
        car.setAutoModel(model);
        car.setBody(body);
        car.setEngine(engine);
        car.setDrive(drive);
        car.setTransmission(transmission);
        car.setCubicCapacity(Double.parseDouble(req.getParameter("capacity")));
        car.setYear(Integer.parseInt(req.getParameter("year")));
        car.setRunning(Integer.parseInt(req.getParameter("running")));
        car.setStatus(false);
        car.setCost(Integer.parseInt(req.getParameter("cost")));
        car.setSeller(user);
        if (!fileName.isEmpty()) {
            car.setNameImg("./uploadDir/" + fileName);
        } else {
            car.setNameImg("");
        }


        session.save(car);
        session.getTransaction().commit();
        session.close();
        factory.close();

        resp.sendRedirect(String.format("%s/start", req.getContextPath()));
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                clientFileName = clientFileName.replace("\\", "/");
                int i = clientFileName.lastIndexOf('/');
                return clientFileName.substring(i + 1);
            }
        }
        return null;
    }
}
