package actions;

import panels.implemented.RentsByAgePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import utils.*;
import models.*;

public class SearchRentByAgeAction implements ActionListener {
    SearchUtil su = new SearchUtil();
    RentsByAgePanel panel;

    public SearchRentByAgeAction(RentsByAgePanel rentsByAgePanel) {
        panel = rentsByAgePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int personAge;
        try {
            personAge = Integer.parseInt(String.valueOf(panel.getPersonAgeCombo().getSelectedItem()));
        } catch
        (Exception e2) {
            personAge = -1;
        }
        String carBrand = panel.getCarBrandCombo().getSelectedItem().toString();

        String sql = "SELECT r.id," +
                "r.start_date \"START DATE\"," +
                "r.finish_date \"FINISH DATE\"," +
                "p.f_name \"FIRST NAME\"," +
                "p.l_name \"LAST NAME\"," +
                "p.age," +
                "c.brand," +
                "c.model " +
                "FROM RENT r," +
                "person p," +
                "car c " +
                "where r.person_id = p.id " +
                "and c.id = r.car_id" +
                (su.hasFilter(personAge) ? " and p.age=?" : "") +
                (su.hasFilter(carBrand) ? " and c.brand=?" : "");

        panel.setConn(DbUtil.getConnection());

        try {
            int id = 1;
            panel.setState(panel.getConn().prepareStatement(sql));

            if (su.hasFilter(personAge)) {
                panel.getState().setInt(id, personAge);
                id++;
            }

            if (su.hasFilter(carBrand)) {
                panel.getState().setString(id, carBrand);
                id++;
            }

            panel.getState().execute();
            panel.getTable().setModel(new MyTableModel(panel.getState().executeQuery()));
            panel.getStatusLabel().setText("Query successfully completed.");
        } catch (Exception e1) {
            panel.getStatusLabel().setText("Invalid data.");
            e1.printStackTrace();
        }
    }
}