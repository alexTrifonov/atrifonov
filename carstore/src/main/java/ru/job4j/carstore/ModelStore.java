package ru.job4j.carstore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Store for model of car.
 * @author atrifonov.
 * @version 1.
 * @since 06.03.2018.
 */
public enum ModelStore {
    INSTANCE;

    public List<AutoModel> getModels(MakeCar makeCar) {
        List<AutoModel> list = new LinkedList<>();

        try (Connection conn = ConnectionFactory.getDatabaseConnection();
            PreparedStatement selectAllModels = conn.prepareStatement("SELECT * FROM auto_models WHERE make_id = ?")) {
            selectAllModels.setInt(1, makeCar.getId());
            ResultSet setModels = selectAllModels.executeQuery();
            while (setModels.next()) {
                int id = setModels.getInt(1);
                String model = setModels.getString("model");
                AutoModel autoModel = new AutoModel(model, makeCar);
                autoModel.setId(id);
                list.add(autoModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
