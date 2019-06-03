package panels.implemented;

import actions.*;
import actions.AddCarAction;
import actions.SearchCarAction;
import actions.UpdCarAction;
import models.*;
import panels.templates.EntityPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CarPanel extends EntityPanel {
    // region PersonAction Variables
    private AddCarAction addCarAction;
    private DelEntityAction delCarAction;
    private UpdCarAction updCarAction;
    private CancelAction cancelAction;
    private ClearAction clearAction;
    private SearchCarAction searchCarAction;
    // endregion PersonAction Variables


    // region upperPanTop Variables

    // region upperPanTop Labels
    JLabel brandLabel = new JLabel("Brand:");
    JLabel modelLabel = new JLabel("Model:");
    JLabel yearLabel = new JLabel("Year:");
    JLabel engineLabel = new JLabel("Engine");
    //endregion upperPanTop Labels

    // region upperPanTop Text Fields
    JTextField brandTField = new JTextField();
    JTextField modelTField = new JTextField();
    JTextField yearTField = new JTextField();
    JTextField engineTField = new JTextField();
    //endregion upperPanTop Text Fields

    // endregion upperPanTop Variables

    // region Constructor
    public CarPanel() {
        entityName = "car";

        // region upperPanTop
        upperPanTop.setLayout(new GridLayout(5, 2));

        upperPanTop.add(brandLabel);
        upperPanTop.add(brandTField);
        upperPanTop.add(modelLabel);
        upperPanTop.add(modelTField);
        upperPanTop.add(yearLabel);
        upperPanTop.add(yearTField);
        upperPanTop.add(engineLabel);
        upperPanTop.add(engineTField);
        //endregion

        // region upperPanBot Buttons ActionListeners
        addCarAction = new AddCarAction(this);
        addBtn.addActionListener(addCarAction);
        delCarAction = new DelEntityAction(this);
        delBtn.addActionListener(delCarAction);
        updCarAction = new UpdCarAction(this);
        updBtn.addActionListener(updCarAction);
        cancelAction = new CancelAction(this);
        cancelBtn.addActionListener(cancelAction);
        clearAction = new ClearAction(this);
        clearBtn.addActionListener(clearAction);
        searchCarAction = new SearchCarAction(this);
        searchBtn.addActionListener(searchCarAction);
        // endregion upperPanBot Buttons ActionListeners

        // region bottomPan
        table.setModel(getAllDBModel());
        table.addMouseListener(new TableClick());
        // endregion bottomPan

        // region loadMainPanSubPanels()
        loadMainPanSubPanels();
        // endregion loadMainPanSubPanels()
    }
    // endregion Constructor

    // region Methods

    // region clearFields
    public void clearFields() {
        brandTField.setText("");
        modelTField.setText("");
        yearTField.setText("");
        engineTField.setText("");
    }
    // endregion clearFields

    // region getAllDBModel
    public MyTableModel getAllDBModel(){
        return getAllDBModel("car");
    }
    // endregion getAllDBModel

    // region Getters

    // region getAddAction
    public AddCarAction getAddAction() {
        return addCarAction;
    }
    // endregion getAddAction

    // region getDelAction
    public DelEntityAction getDelAction() {
        return delCarAction;
    }
    // endregion getDelAction

    // region getUpdAction
    public UpdCarAction getUpdAction() {
        return updCarAction;
    }
    // endregion getUpdAction

    public CancelAction getCancelAction() {
        return cancelAction;
    }

    public ClearAction getClearAction() {
        return clearAction;
    }

    public SearchCarAction getSearchCarAction() {
        return searchCarAction;
    }

    public JLabel getBrandLabel() {
        return brandLabel;
    }

    public JLabel getModelLabel() {
        return modelLabel;
    }

    public JLabel getYearLabel() {
        return yearLabel;
    }

    public JLabel getEngineLabel() {
        return engineLabel;
    }

    public JTextField getBrandTField() {
        return brandTField;
    }

    public JTextField getModelTField() {
        return modelTField;
    }

    public JTextField getYearTField() {
        return yearTField;
    }

    public JTextField getEngineTField() {
        return engineTField;
    }

    // endregion Getters

    // region loadUpperPan
    @Override
    public void loadUpperPan() {

    }
    // endregion loadUpperPan

    // endregion Methods

    // region TableClick Inner Class
    class TableClick implements MouseListener {

        // region mouseClicked
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = table.getSelectedRow();
            Object cellValue = table.getValueAt(row, 0);
            id = Integer.parseInt(cellValue.toString());

            if (e.getClickCount() > 1) {
                brandTField.setText(table.getValueAt(row, 1).toString());
                modelTField.setText(table.getValueAt(row, 2).toString());
                yearTField.setText(table.getValueAt(row, 3).toString());
                engineTField.setText(table.getValueAt(row, 4).toString());
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