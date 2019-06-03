package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import panels.templates.EntityPanel;

public class DelEntityAction extends BaseEntityAction implements ActionListener {
    public DelEntityAction(EntityPanel entityPanel) {
        super(entityPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (intIsEmpty(panel.getId())) {
            return;
        }

        String sql = "delete from " + this.panel.getEntityName() + " where id = ?";

        try {
            prepareState(sql);
            panel.getState().setInt(1, panel.getId());
            executeQuery();
        } catch (Exception e1) {
            panel.getStatusLabel().setText("Invalid operation.");
            e1.printStackTrace();
        }
    }

    @Override
    void successStatusMessage() {
        panel.getStatusLabel().setText("Entry is deleted.");
    }
}