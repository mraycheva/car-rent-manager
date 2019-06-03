package panels.implemented;

import actions.CancelAction;
import actions.ClearAction;

import javax.swing.*;
import java.awt.*;

import actions.*;
import models.*;
import panels.templates.BasePanel;
import utils.*;

public class RentsByAgePanel extends BasePanel {
    // region Variables

    // region DbModels
    MyTableModel rentsByAgeTableModel = getAllDBModel();
    MyTableModel personTableModel = new PersonPanel().getColDistinctDbModel("person", "age");
    MyTableModel carTableModel = new CarPanel().getColDistinctDbModel("car", "brand");
    // endregion DbModels

    // region upperPanTop Variables

    // region upperPanTop Labels
    JLabel personAgeLabel = new JLabel("Person's age:");
    JLabel carBrandLabel = new JLabel("Car's brand:");
    //endregion

    // region upperPanTop ComboBoxes
    String[] contentPersonAgeCombo;
    String[] contentCarBrandCombo;
    JComboBox personAgeCombo;
    JComboBox carBrandCombo;
    // endregion upperPanTop ComboBox

    // region upperPanTop TextFields
    JTextField startDateTField;
    JTextField finishDateTField;
    // endregion upperPanTop TextFields

    // endregion upperPanTop Variables

    // endregion Variables

    // region Constructor
    public RentsByAgePanel() {
        getUpperPanTop().setLayout(new GridLayout(4, 2));
        loadUpperPan();
        loadActionListeners();
        table.setModel(rentsByAgeTableModel);
        generateBottomPan();
        loadMainPanSubPanels();
    }
    // endregion Constructor

    // region Methods

    // region loadUpperPan
    public void loadUpperPan() {
        upperPanTop.removeAll();

        loadComboBoxes();

        upperPanTop.add(personAgeLabel);
        upperPanTop.add(personAgeCombo);
        upperPanTop.add(carBrandLabel);
        upperPanTop.add(carBrandCombo);

        generateUpperPanBotPan();
        upperPanTop.revalidate();
        upperPanTop.repaint();
    }
    // endregion loadUpperPan

    // region loadActionListeners
    private void loadActionListeners() {
        cancelBtn.addActionListener(new CancelAction(this));
        clearBtn.addActionListener(new ClearAction(this));
        searchBtn.addActionListener(new SearchRentByAgeAction(this));
    }
    // endregion loadActionListeners

    // region generateMiddlePanButtons

    public void generateMiddlePanButtons() {
        btnsPanel.add(cancelBtn);
        btnsPanel.add(searchBtn);
        btnsPanel.add(clearBtn);
    }

    // endregion generateMiddlePanButtons

    // region loadComboBoxes
    private void loadComboBoxes() {
        personTableModel = new PersonPanel().getColDistinctDbModel("person", "age");
        carTableModel = new CarPanel().getColDistinctDbModel("car", "brand");
        personAgeCombo = populatePersonAgeCombo();
        carBrandCombo = populateCarBrandCombo();
    }
    // endregion loadComboBoxes

    // region populatePersonAgeCombo
    private JComboBox populatePersonAgeCombo() {
        return populateComboWithColumn(personTableModel, contentPersonAgeCombo);
    }
    // endregion populatePersonAgeCombo

    // region populateCarBrandCombo
    private JComboBox populateCarBrandCombo() {
        return populateComboWithColumn(carTableModel, contentCarBrandCombo);
    }
    // endregion populateCarBrandCombo

    // region populateComboWithColumn
    private JComboBox populateComboWithColumn(MyTableModel myTableModel, String[] content) {
        int modelRows = myTableModel.getRowCount();
        content = new String[modelRows + 1];
        content[0] = "";
        for (int i = 0; i < modelRows; i++) {
            content[i+1] = myTableModel.getValueAt(i, 0).toString();
        }

        return new JComboBox(content);
    }
    // endregion populateComboWithColumn

    // region getAllDBModel
    public MyTableModel getAllDBModel() {

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
                "and c.id = r.car_id";
        conn = DbUtil.getConnection();

        try {
            state = conn.prepareStatement(sql);
            result = state.executeQuery();

            return new MyTableModel(result);
        } catch (Exception e) {
            statusLabel.setText("Invalid data.");
            e.printStackTrace();
        }
        return null;
    }

    // endregion getAllDBModel

    // region clearFields
    @Override
    public void clearFields() {
        personAgeCombo.setSelectedIndex(0);
        carBrandCombo.setSelectedIndex(0);
    }
    // endregion clearFields

    // region selectItemFromCombo
    private void selectItemFromCombo(JComboBox comboBox, int idSelItem) {
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            String currItem = comboBox.getItemAt(i).toString();
            String idCurrItemS = currItem.split("\\.")[0];
            int idCurrItem = Integer.parseInt(idCurrItemS);

            if (idCurrItem == idSelItem) {
                comboBox.setSelectedIndex(i);
                break;
            }
        }
    }
    // endregion selectItemFromCombo

    public JComboBox getPersonAgeCombo() {
        return personAgeCombo;
    }

    public JComboBox getCarBrandCombo() {
        return carBrandCombo;
    }

    // endregion Methods
}