package actions;

import panels.implemented.PersonPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdPersonAction extends UpdEntityAction implements ActionListener {

    public UpdPersonAction(PersonPanel personPanel) {
        super(personPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PersonPanel pp = (PersonPanel) panel;
        if (intIsEmpty(pp.getId())) {
            return;
        }

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

        String sql = "update person set f_Name=?, l_Name=?, age=?, salary=?, gender=? where id=?";

        try {
            int age = Integer.parseInt(pp.getAgeTField().getText());
            float salary = Float.parseFloat(pp.getSalaryTField().getText());

            prepareState(sql);
            pp.getState().setString(1, fName);
            pp.getState().setString(2, lName);
            pp.getState().setInt(3, age);
            pp.getState().setFloat(4, salary);
            pp.getState().setString(5, gender);
            pp.getState().setInt(6, pp.getId());
            executeQuery();
        } catch (Exception e1) {
            pp.getStatusLabel().setText("Missing or invalid data.");
            e1.printStackTrace();
        }
    }
}
