package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import panels.implemented.RentPanel;
import utils.*;

public class AddRentAction extends AddEntityAction implements ActionListener {
    public AddRentAction(RentPanel rentPanel) {
        super(rentPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RentPanel rp = (RentPanel) panel;

        int idPerson = rp.getItemIdFromCombo(rp.getPersonCombo());
        if (intIsEmpty(idPerson)) {
            return;
        }

        int idCar = rp.getItemIdFromCombo(rp.getCarCombo());
        if (intIsEmpty(idCar)) {
            return;
        }

        String startDateS = rp.getStartDateTField().getText();
        String finishDateS = rp.getFinishDateTField().getText();

        Date startDate;
        Date finishDate;

        try {
            startDate = new ConvertDateUtil().formatStringToSQLDate(startDateS);
            finishDate = new ConvertDateUtil().formatStringToSQLDate(finishDateS);
        } catch (Exception ex) {
            rp.getStatusLabel().setText("Invalid date.");
            ex.printStackTrace();
            return;
        }

        String sql = "insert into rent values(null, ?, ?, ?, ?);";

        try {
            prepareState(sql);
            rp.getState().setInt(1, idPerson);
            rp.getState().setInt(2, idCar);
            rp.getState().setDate(3, startDate);
            rp.getState().setDate(4, finishDate);
            executeQuery();
        } catch (Exception e1) {
            rp.getStatusLabel().setText("Invalid data.");
            e1.printStackTrace();
        }
    }
}