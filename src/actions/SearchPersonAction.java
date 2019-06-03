package actions;

import panels.implemented.PersonPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchPersonAction extends SearchEntityAction implements ActionListener {
    public SearchPersonAction(PersonPanel personPanel) {
        super(personPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PersonPanel pp = (PersonPanel) panel;

        String fName = pp.getFNameTField().getText();
        String lName = pp.getLNameTField().getText();
        String gender = (pp.getGenderCombo().getSelectedItem()).toString();
        String ageS = pp.getAgeTField().getText();
        String salaryS = pp.getSalaryTField().getText();

        String sql = "select * from person where UPPER(f_Name)like UPPER(?)" +
                " and UPPER(l_Name) like UPPER(?)" +
                (su.hasFilter(gender) ? " and UPPER(gender) = UPPER(?)" : "") +
                (su.hasFilter(ageS) ? " and age=?" : "") +
                (su.hasFilter(salaryS) ? " and salary=?" : "") +
                " order by 1";

        try {
            int ind = 1;

            prepareState(sql);

            pp.getState().setString(ind, '%' + fName + '%');
            ind++;

            pp.getState().setString(ind, '%' + lName + '%');
            ind++;

            if (su.hasFilter(gender)) {
                pp.getState().setString(ind, gender);
                ind++;
            }

            if (su.hasFilter(ageS)) {
                int age = Integer.parseInt(ageS);
                pp.getState().setInt(ind, age);
                ind++;
            }

            if (su.hasFilter(salaryS)) {
                float salary = Float.parseFloat(salaryS);
                pp.getState().setFloat(ind, salary);
                ind++;
            }

            executeQuery();

        } catch (Exception e1) {
            pp.setStatusLabel("Invalid data.");
            e1.printStackTrace();
        }
    }
}