package actions;

import panels.implemented.RentPanel;
import utils.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class UpdRentAction extends UpdEntityAction implements ActionListener {
    public UpdRentAction(RentPanel rentPanel) {
        super(rentPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RentPanel rp = (RentPanel) panel;
        if (intIsEmpty(rp.getId())) {
            return;
        }

        int idPerson = rp.getItemIdFromCombo(rp.getPersonCombo());
        int idCar = rp.getItemIdFromCombo(rp.getCarCombo());
        String startDateS = rp.getStartDateTField().getText();
        String finishDateS = rp.getFinishDateTField().getText();

        Date startDate = null;
        Date finishDate = null;

        try {
            startDate = new ConvertDateUtil().formatStringToSQLDate(startDateS);
            finishDate = new ConvertDateUtil().formatStringToSQLDate(finishDateS);
        } catch (Exception ex) {
            rp.getStatusLabel().setText("Invalid data.");
            ex.printStackTrace();
        }

        String sql = "update rent set person_id=?, car_id=?, start_date=?, finish_date=? where id=?";

        try {
            prepareState(sql);
            rp.getState().setInt(1, idPerson);
            rp.getState().setInt(2, idCar);
            rp.getState().setDate(3, startDate);
            rp.getState().setDate(4, finishDate);
            rp.getState().setInt(5, rp.getId());
            executeQuery();
        } catch (Exception e1) {
            rp.getStatusLabel().setText("Invalid data.");
            e1.printStackTrace();
        }
    }
}