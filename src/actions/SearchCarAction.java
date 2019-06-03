package actions;

import panels.implemented.CarPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchCarAction extends SearchEntityAction implements ActionListener {
    public SearchCarAction(CarPanel carPanel) {
        super(carPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CarPanel cp = (CarPanel) panel;
        String brand = cp.getBrandTField().getText();
        String model = cp.getModelTField().getText();
        String engine = cp.getEngineTField().getText();
        String yearS = cp.getYearTField().getText();

        String sql = "select * from car where UPPER(brand) like UPPER(?)" +
                " and UPPER(model) like UPPER(?)" +
                " and UPPER(engine) like UPPER(?)" +
                ((su.hasFilter(yearS)) ? " and year=?" : "") +
                " order by 1";

        try {
            int ind = 1;

            prepareState(sql);

            cp.getState().setString(ind, '%' + brand + '%');
            ind++;

            cp.getState().setString(ind, '%' + model + '%');
            ind++;

            cp.getState().setString(ind, '%' + engine + '%');
            ind++;

            if (su.hasFilter(yearS)) {
                int year = Integer.parseInt(yearS);
                cp.getState().setInt(ind, year);
                ind++;
            }

            executeQuery();
        } catch (Exception e1) {
            cp.setStatusLabel("Invalid data.");
            e1.printStackTrace();
        }
    }
}