package actions;

import panels.templates.BasePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelAction implements ActionListener {
    BasePanel bp;

    public CancelAction(BasePanel basePanel) {
        bp = basePanel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
            bp.clearFields();
            bp.getTable().clearSelection();
            bp.setStatusLabel("");
            bp.setId(0);
            bp.getTable().setModel(bp.getAllDBModel());
    }
}