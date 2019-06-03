package actions;

import panels.implemented.RentPanel;
import utils.ConvertDateUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class SearchRentAction extends SearchEntityAction implements ActionListener {
    public SearchRentAction(RentPanel rentPanel) {
        super(rentPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RentPanel rp = (RentPanel) panel;

        int idPerson = rp.getItemIdFromCombo(rp.getPersonCombo());
        int idCar = rp.getItemIdFromCombo(rp.getCarCombo());

        String startDateS = rp.getStartDateTField().getText();
        String finishDateS = rp.getFinishDateTField().getText();

        String sql = "SELECT r.id," +
                "r.start_date \"START DATE\"," +
                "r.finish_date \"FINISH DATE\"," +
                "p.f_name \"FIRST NAME\"," +
                "p.l_name \"LAST NAME\"," +
                "c.brand," +
                "c.model " +
                "FROM RENT r," +
                "person p," +
                "car c " +
                "where r.person_id = p.id " +
                "and c.id = r.car_id" +
                (su.hasFilter(idPerson) ? " and person_id=?" : "") +
                (su.hasFilter(idCar) ? " and car_id=?" : "") +
                (su.hasFilter(startDateS) ? " and start_date=?" : "") +
                (su.hasFilter(finishDateS) ? "and finish_date=?" : "") +
                " order by 1";

        try {
            int ind = 1;

            prepareState(sql);

            if (su.hasFilter(idPerson)) {
                rp.getState().setInt(ind, idPerson);
                ind++;
            }

            if (su.hasFilter(idCar)) {
                rp.getState().setInt(ind, idCar);
                ind++;
            }

            if (su.hasFilter(startDateS)) {
                Date startDate = new ConvertDateUtil().formatStringToSQLDate(startDateS);
                rp.getState().setDate(ind, startDate);
                ind++;
            }

            if (su.hasFilter(finishDateS)) {
                Date finishDate = new ConvertDateUtil().formatStringToSQLDate(finishDateS);
                rp.getState().setDate(ind, finishDate);
                ind++;
            }

            executeQuery();
        } catch (Exception e1) {
            rp.setStatusLabel("Invalid data.");
            e1.printStackTrace();
        }
    }
}