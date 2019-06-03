package actions;

import panels.templates.BasePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearAction implements ActionListener {
    BasePanel bp;

    public ClearAction(BasePanel basePanel) {
        bp = basePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            bp.clearFields();
            bp.setStatusLabel("All fields are cleared." +
                    ((bp.getId() >0) ? " You are still modifying entry with id " + bp.getId() + "." : ""));
    }
}
