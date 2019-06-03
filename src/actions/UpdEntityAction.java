package actions;

import panels.templates.EntityPanel;

class UpdEntityAction extends BaseEntityAction {
    UpdEntityAction(EntityPanel entityPanel) {
        super(entityPanel);
    }

    void executeQuery() {

        try {
            panel.getState().execute();
            successStatusMessage();
            panel.clearFields();
            panel.getTable().setModel(panel.getAllDBModel());
            panel.setId(0);

            if (ep2 != null) {
                ep2.loadUpperPan();
                ep2.getTable().setModel(ep2.getAllDBModel());
            }
        } catch (Exception e1) {
            panel.setStatusLabel("Missing or invalid data.");
            e1.printStackTrace();
        }
    }

    @Override
    void successStatusMessage() {
        panel.setStatusLabel("Entry is updated.");
    }
}
