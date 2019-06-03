package panels.implemented;

import actions.*;
import models.MyTableModel;
import panels.templates.EntityPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PersonPanel extends EntityPanel {

    // region upperPanTop Variables

    // region upperPanTop Labels
    private JLabel _fNameLabel = new JLabel("First name:");
    private JLabel _lNameLabel = new JLabel("Last name:");
    private JLabel _ageLabel = new JLabel("Age:");
    private JLabel _salaryLabel = new JLabel("Salary");
    private JLabel _genderLabel = new JLabel("Gender:");
    //endregion

    // region upperPanTop TFields
    private JTextField fNameTField = new JTextField();
    private JTextField lNameTField = new JTextField();
    private JTextField ageTField = new JTextField();
    private JTextField salaryTField = new JTextField();
    //endregion upperPanTop TFields

    // region upperPanTop ComboBox
    String[] contentCombo = new String[]{"", "Male", "Female"};
    JComboBox<String> genderCombo = new JComboBox<>(contentCombo);
    // endregion upperPanTop ComboBox

    // endregion upperPanTop Variables

    // region PersonAction Variables
    private AddPersonAction addPersonAction;
    private DelEntityAction delPersonAction;
    private UpdPersonAction updPersonAction;
    private CancelAction cancelAction;
    private SearchPersonAction searchPersonAction;
    private ClearAction clearAction;
    // endregion PersonAction Variables

    // region Constructor
    public PersonPanel() {
        entityName = "person";
        generateUpperPan();
        generateBottomPanel();

        loadMainPanSubPanels();
    }
    // endregion Constructor

    // region Methods

    // region generateUpperPan
    private void generateUpperPan(){
        generateUpperPanTopPan();
        generateUpperPanBotPanActions();
    }
    // endregion generateUpperPan

    // region generateUpperPanTopPan Help Methods

    // endregion generateUpperPanTopPan Help Methods

    // region generateUpperPanTopPan
    private void generateUpperPanTopPan() {
        upperPanTop.setLayout(new GridLayout(5, 2));

        upperPanTop.add(_fNameLabel);
        upperPanTop.add(fNameTField);
        upperPanTop.add(_lNameLabel);
        upperPanTop.add(lNameTField);
        upperPanTop.add(_ageLabel);
        upperPanTop.add(ageTField);
        upperPanTop.add(_salaryLabel);
        upperPanTop.add(salaryTField);
        upperPanTop.add(_genderLabel);
        upperPanTop.add(genderCombo);
    }
    // endregion generateUpperPanTopPan

    // region generateUpperPanBotPanActions
    void generateUpperPanBotPanActions() {
        addPersonAction = new AddPersonAction(this);
        addBtn.addActionListener(addPersonAction);
        delPersonAction = new DelEntityAction(this);
        delBtn.addActionListener(delPersonAction);
        updPersonAction = new UpdPersonAction(this);
        updBtn.addActionListener(updPersonAction);
        cancelAction = new CancelAction(this);
        cancelBtn.addActionListener(cancelAction);
        searchPersonAction = new SearchPersonAction(this);
        searchBtn.addActionListener(searchPersonAction);
        clearAction = new ClearAction(this);
        clearBtn.addActionListener(clearAction);

    }
    // endregion generateUpperPanBotPanActions

    // region generateBottomPanel
    void generateBottomPanel() {
        table.setModel(getAllDBModel());
        table.addMouseListener(new TableClick());
    }
    //endregion generateBottomPanel

    // region clearFields
    public void clearFields() {
        fNameTField.setText("");
        lNameTField.setText("");
        ageTField.setText("");
        salaryTField.setText("");
        genderCombo.setSelectedIndex(0);
    }
    // endregion clearFields

    // region getAllDBModel
    public MyTableModel getAllDBModel() {
        return getAllDBModel("person");
    }
    // endregion getAllDBModel

    // region loadTFieldFromEntry
    private void loadTFieldFromEntry(JTextField textField, int columnN) {
        textField.setText(table.getValueAt(getSelectedRow(), columnN).toString());
    }
    // endregion loadTFieldFromEntry

    // region loadGenderComboFromEntry
    private void loadGenderComboFromEntry(String gender) {
        switch (gender) {
            case "Male":
                genderCombo.setSelectedIndex(1);
                break;
            case "Female":
                genderCombo.setSelectedIndex(2);
                break;
            default:
                genderCombo.setSelectedIndex(0);
        }
    }
    // endregion loadGenderComboFromEntry

    // region Getters

    // region getFNameLabel
    public JLabel getFNameLabel() {
        return _fNameLabel;
    }
    // endregion getFNameLabel

    // region getLNameLabel
    public JLabel getLNameLabel() {
        return _lNameLabel;
    }
    // endregion getLNameLabel

    // region getAgeLabel
    public JLabel getAgeLabel() {
        return _ageLabel;
    }
    // endregion getAgeLabel

    // region getSalaryLabel
    public JLabel getSalaryLabel() {
        return _salaryLabel;
    }
    // endregion getSalaryLabel

    // region getGenderLabel
    public JLabel getGenderLabel() {
        return _genderLabel;
    }
    // endregion getGenderLabel

    // region getFNameTField
    public JTextField getFNameTField() {
        return fNameTField;
    }
    // endregion getFNameTField

    // region getLNameTField
    public JTextField getLNameTField() {
        return lNameTField;
    }
    // endregion getLNameTField

    // region getAgeTField
    public JTextField getAgeTField() {
        return ageTField;
    }
    // endregion getAgeTField

    // region getSalaryTField
    public JTextField getSalaryTField() {
        return salaryTField;
    }
    // endregion getSalaryTField

    // region getContentCombo
    public String[] getContentCombo() {
        return contentCombo;
    }
    // endregion getContentCombo

    // region getGenderCombo
    public JComboBox<String> getGenderCombo() {
        return genderCombo;
    }
    // endregion getGenderCombo

    // region getAddAction
    public AddPersonAction getAddAction() {
        return addPersonAction;
    }
    // endregion getAddAction

    // region getDelAction
    public DelEntityAction getDelAction() {
        return delPersonAction;
    }
    // endregion getDelAction

    // region getUpdAction
    public UpdPersonAction getUpdAction() {
        return updPersonAction;
    }

    @Override
    public void loadUpperPan() {

    }
    // endregion getUpdAction

    // endregion Getters

    // endregion Methods

    // region TableClick Inner Class
    class TableClick implements MouseListener {

        // region mouseClicked
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = getSelectedRow();
            id = getSelectedId();

            if (e.getClickCount() > 1) {

                loadTFieldFromEntry(fNameTField, 1);
                loadTFieldFromEntry(lNameTField, 2);
                loadTFieldFromEntry(ageTField, 3);
                loadTFieldFromEntry(salaryTField, 4);

                String genderS = table.getValueAt(row, 5).toString();
                loadGenderComboFromEntry(genderS);

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
    // endregion TableClick Inner Class
}