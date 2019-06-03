package panels.implemented;

import actions.*;
import models.*;
import panels.templates.EntityPanel;
import utils.DbUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;

public class RentPanel extends EntityPanel {
    // region Variables

    // region DbModels
    MyTableModel rentTableModel = getAllDBModel();
    MyTableModel personTableModel = new PersonPanel().getAllDBModel();
    MyTableModel carTableModel = new CarPanel().getAllDBModel();
    // endregion DbModels

    // region upperPanTop Variables

    // region upperPanTop Labels
    JLabel personLabel = new JLabel("Person:");
    JLabel carLabel = new JLabel("Car:");
    JLabel startDateLabel = new JLabel("Start date (YYYY.MM.DD or YYYY-MM-DD):");
    JLabel finishDateLabel = new JLabel("Finish date (YYYY.MM.DD or YYYY-MM-DD):");
    //endregion

    // region upperPanTop ComboBoxes
    String[] contentPersonCombo;
    String[] contentCarCombo;
    JComboBox personCombo;
    JComboBox carCombo;
    // endregion upperPanTop ComboBox

    // region upperPanTop TextFields
    JTextField startDateTField = new JTextField();
    JTextField finishDateTField = new JTextField();
    // endregion upperPanTop TextFields

    // endregion upperPanTop Variables

    // endregion Variables

    // region Constructor
    public RentPanel() {
        entityName = "rent";
        generateSubPanels();
        loadMainPanSubPanels();
    }
    // endregion Constructor

    // region Methods

    // region generatePanels
    void generateSubPanels() {
        getUpperPanTop().setLayout(new GridLayout(4, 2));
        loadUpperPan();
        loadActionListeners();
        generateBottomPanel();
    }
    // endregion generatePanels

    // region generateUpperPan Help Methods

    // endregion generateUpperPan Help Methods

    // region loadUpperPan
    public void loadUpperPan() {
        upperPanTop.removeAll();

        loadComboBoxes();

        upperPanTop.add(personLabel);
        upperPanTop.add(personCombo);
        upperPanTop.add(carLabel);
        upperPanTop.add(carCombo);
        upperPanTop.add(startDateLabel);
        upperPanTop.add(startDateTField);
        upperPanTop.add(finishDateLabel);
        upperPanTop.add(finishDateTField);
        upperPanTop.revalidate();
        upperPanTop.repaint();
    }
    // endregion loadUpperPan

    // region MiddlePan Methods

    // region loadActionListeners
    private void loadActionListeners() {
        addBtn.addActionListener(new AddRentAction(this));
        delBtn.addActionListener(new DelEntityAction(this));
        updBtn.addActionListener(new UpdRentAction(this));
        cancelBtn.addActionListener(new CancelAction(this));
        clearBtn.addActionListener(new ClearAction(this));
        searchBtn.addActionListener(new SearchRentAction(this));
    }
    // endregion loadActionListeners

    // region populateCombo
    private JComboBox populateCombo(MyTableModel myTableModel) {
        int modelRows = myTableModel.getRowCount();
        contentPersonCombo = new String[modelRows + 1];
        contentPersonCombo[0] = "";
        for (int i = 0; i < modelRows; i++) {
            contentPersonCombo[i + 1] = myTableModel.getValueAt(i, 0) + ". "
                    + myTableModel.getValueAt(i, 1) + " "
                    + myTableModel.getValueAt(i, 2);
        }

        return new JComboBox(contentPersonCombo);
    }
    // endregion populateCombo

    // endregion MiddlePan Methods

    // region getAllDBModel
    public MyTableModel getAllDBModel() {

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
                "and c.id = r.car_id " +
                "order by 1";
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

    @Override
    public AddEntityAction getAddAction() {
        return null;
    }

    @Override
    public BaseEntityAction getDelAction() {
        return null;
    }

    @Override
    public BaseEntityAction getUpdAction() {
        return null;
    }
    // endregion getAllDBModel

    // region clearFields
    public void clearFields() {
        personCombo.setSelectedIndex(0);
        carCombo.setSelectedIndex(0);
        startDateTField.setText("");
        finishDateTField.setText("");
    }
    // endregion clearFields

    // region selectItemFromCombo
    private void selectItemFromCombo(JComboBox comboBox, int idSelItem) {
        for (int i = 1; i <= comboBox.getItemCount() - 1; i++) {
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

    // region getIdFromCombo
    public int getItemIdFromCombo(JComboBox comboBox) {
        String selItem = comboBox.getSelectedItem().toString();
        String idItemS = selItem.split("\\.")[0];

        try {
            return Integer.parseInt(idItemS);
        } catch(Exception e) {
            setStatusLabel("Invalid data.");
            return -1;
        }
    }
    // endregion getIdFromCombo

    // region loadComboBoxes
    private void loadComboBoxes() {
        MyTableModel personTableModel = new PersonPanel().getAllDBModel();
        MyTableModel carTableModel = new CarPanel().getAllDBModel();
        personCombo = populateCombo(personTableModel);
        carCombo = populateCombo(carTableModel);
    }
    // endregion loadComboBoxes

    // region loadTable
    void loadTable() {
        table.setModel(rentTableModel);
    }
    // endregion loadTable

    // region generateBottomPanel
    void generateBottomPanel() {
        loadTable();
        table.addMouseListener(new TableClick());
    }
    // endregion generateBottomPanel


    // region Getters

    public JComboBox getPersonCombo() {
        return personCombo;
    }

    public JComboBox getCarCombo() {
        return carCombo;
    }

    public MyTableModel getRentTableModel() {
        return rentTableModel;
    }

    public MyTableModel getPersonTableModel() {
        return personTableModel;
    }

    public MyTableModel getCarTableModel() {
        return carTableModel;
    }

    public JLabel getPersonLabel() {
        return personLabel;
    }

    public JLabel getCarLabel() {
        return carLabel;
    }

    public JLabel getStartDateLabel() {
        return startDateLabel;
    }

    public JLabel getFinishDateLabel() {
        return finishDateLabel;
    }

    public String[] getContentPersonCombo() {
        return contentPersonCombo;
    }

    public String[] getContentCarCombo() {
        return contentCarCombo;
    }

    public JTextField getStartDateTField() {
        return startDateTField;
    }

    public JTextField getFinishDateTField() {
        return finishDateTField;
    }

    // endregion Getters
    // endregion Methods

    // region Inner Classes

    // region RefPanAction
    class RefPanAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            loadUpperPan();
        }
    }
    // endregion RefPanAction

    // region TableClick
    class TableClick implements MouseListener {

        // region mouseClicked
        @Override
        public void mouseClicked(MouseEvent e) {
            int selRow = table.getSelectedRow();
            Object rentId = table.getValueAt(selRow, 0);
            id = Integer.parseInt(rentId.toString());

            if (e.getClickCount() > 1) {
                startDateTField.setText(table.getValueAt(selRow, 1).toString());

                Date finishDate = (Date) table.getValueAt(selRow, 2);

                if (finishDate == null) {
                    finishDateTField.setText("");
                } else {
                    finishDateTField.setText(finishDate.toString());
                }

                int idSelPerson = (int) getAllDBModel("rent").getValueAt(selRow, 1);
                int idSelCar = (int) getAllDBModel("rent").getValueAt(selRow, 2);

                selectItemFromCombo(personCombo, idSelPerson);
                selectItemFromCombo(carCombo, idSelCar);
                selectionMessage();
            }
        }
        // endregion mouseClicked

        // region Unimplemented Methods
        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }
        // endregion Unimplemented Methods

    }
    // endregion TableClick

    // endregion Inner Classes
}