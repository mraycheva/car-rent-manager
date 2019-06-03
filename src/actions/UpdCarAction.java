package actions;

import panels.implemented.CarPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdCarAction extends UpdEntityAction implements ActionListener {
    public UpdCarAction(CarPanel carPanel) {
        super(carPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CarPanel cp = (CarPanel) panel;
        if (intIsEmpty(cp.getId())) {
            return;
        }

        String brand = cp.getBrandTField().getText();
        if (stringIsEmpty(brand)) {
            return;
        }

        String model = cp.getModelTField().getText();
        if (stringIsEmpty(model)) {
            return;
        }

        String engine = cp.getEngineTField().getText();
        if (stringIsEmpty(engine)) {
            return;
        }

        String sql = "update car set brand=?, model=?, year=?, engine=? where id=?";

        try {
            int year = Integer.parseInt(cp.getYearTField().getText());

            prepareState(sql);
            cp.getState().setString(1, brand);
            cp.getState().setString(2, model);
            cp.getState().setInt(3, year);
            cp.getState().setString(4, engine);
            cp.getState().setInt(5, cp.getId());
            executeQuery();
        } catch (Exception e1) {
            cp.getStatusLabel().setText("Invalid data.");
            e1.printStackTrace();
        }
    }
}
