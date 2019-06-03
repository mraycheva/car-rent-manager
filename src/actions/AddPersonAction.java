package actions;

import panels.implemented.PersonPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPersonAction extends AddEntityAction implements ActionListener {
    public AddPersonAction(PersonPanel personPanel) {
        super(personPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PersonPanel pp = (PersonPanel) panel;
        String fName = pp.getFNameTField().getText();
        if (stringIsEmpty(fName)) {
            return;
        }

        String lName = pp.getLNameTField().getText();
        if (stringIsEmpty(lName)) {
            return;
        }

        String gender = (pp.getGenderCombo().getSelectedItem()).toString();
        if (stringIsEmpty(gender)) {
            return;
        }

        String sql = "insert into person values(null, ?, ?, ?, ?, ?);";

        prepareState(sql);

        try {
            int age = Integer.parseInt(pp.getAgeTField().getText());
            float salary = Float.parseFloat(pp.getSalaryTField().getText());

            prepareState(sql);
            pp.getState().setString(1, fName);
            pp.getState().setString(2, lName);
            pp.getState().setInt(3, age);
            pp.getState().setFloat(4, salary);
            pp.getState().setString(5, gender);
            executeQuery();
        } catch (Exception e1) {
            pp.setStatusLabel("Missing or invalid data.");
            e1.printStackTrace();
        }
    }
}