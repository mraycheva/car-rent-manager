package actions;

import panels.templates.EntityPanel;

public class AddEntityAction extends BaseEntityAction {

    public AddEntityAction(EntityPanel entityPanel) {
        super(entityPanel);
    }

    @Override
    void successStatusMessage() {
        panel.setStatusLabel("Insert successful.");
    }

}