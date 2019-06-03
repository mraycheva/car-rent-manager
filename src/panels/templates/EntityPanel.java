package panels.templates;

import actions.AddEntityAction;
import actions.BaseEntityAction;

import javax.swing.*;
import java.awt.*;

abstract public class EntityPanel extends BasePanel {
    // region Variables

    protected String entityName;

    // region upperPanBot Buttons
    protected JButton addBtn;
    protected JButton updBtn;
    protected JButton delBtn;
    //endregion upperPanBot Buttons

    // endregion Variables

    public  EntityPanel(){
        generateUpperPanBotPan();
        generateBottomPan();
    }

    // region Methods

    // region generateMiddlePanButtons

    public void generateMiddlePanButtons() {
        addBtn = new JButton("Add");
        updBtn = new JButton("Update");
        delBtn = new JButton("Delete");

        btnsPanel.add(addBtn);
        btnsPanel.add(delBtn);
        btnsPanel.add(updBtn);

        btnsPanel.add(cancelBtn);
        btnsPanel.add(searchBtn);
        btnsPanel.add(clearBtn);
    }
    // endregion generateMiddlePanButtons

    // region Status Label Messages

    // region selectionMessage
    protected void selectionMessage() {
        statusLabel.setText("You are now modifying entry with id " + id + ".");
    }
    // endregion selectionMessage

    // region emptySelectionMessage
    public void emptySelectionMessage() {
        statusLabel.setText("Please, choose an entry from the table.");
    }
    // endregion emptySelectionMessage

    // endregion Status Label Messages

    // region Table Selection Methods

    // region getSelectedId
    protected int getSelectedId() {
        int row = table.getSelectedRow();
        Object cellValue = table.getValueAt(row, 0);
        return Integer.parseInt(cellValue.toString());
    }
    // endregion getSelectedId

    // region getSelectedRow
    protected int getSelectedRow() {
        return table.getSelectedRow();
    }
    // endregion getSelectedRow

    // endregion Table Selection Methods

    // region Getters and Setters

    // region getAddBtn
    public JButton getAddBtn() {
        return addBtn;
    }
    // endregion getAddBtn

    // region getUpdBtn
    public JButton getUpdBtn() {
        return updBtn;
    }
    // endregion getUpdBtn

    // region getDelBtn
    public JButton getDelBtn() {
        return delBtn;
    }
    // endregion getDelBtn

    public String getEntityName() {
        return entityName;
    }

    // endregion Getters and Setters

    public abstract void loadUpperPan();

    public abstract AddEntityAction getAddAction();

    public abstract BaseEntityAction getDelAction();

    public abstract BaseEntityAction getUpdAction();

    // endregion Methods
}