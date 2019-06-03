package actions;

import panels.implemented.CarPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCarAction extends AddEntityAction implements ActionListener {
    public AddCarAction(CarPanel carPanel){
        super(carPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CarPanel cp = (CarPanel) panel;
        String brand = cp.getBrandTField().getText();
        String model = cp.getModelTField().getText();
        String engine = cp.getEngineTField().getText();

        String sql = "insert into car values(null, ?, ?, ?, ?);";

        try {
            int year = Integer.parseInt(cp.getYearTField().getText());

            prepareState(sql);
            cp.getState().setString(1, brand);
            cp.getState().setString(2, model);
            cp.getState().setInt(3, year);
            cp.getState().setString(4, engine);
            executeQuery();
        } catch (Exception e1) {
            cp.getStatusLabel().setText("Invalid data.");
            e1.printStackTrace();
        }
    }
}