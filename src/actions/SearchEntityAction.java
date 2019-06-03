package actions;

import models.MyTableModel;
import panels.templates.EntityPanel;
import utils.*;

public class SearchEntityAction extends BaseEntityAction {
    SearchUtil su = new SearchUtil();

    SearchEntityAction(EntityPanel entityPanel) {
        super(entityPanel);
    }

    @Override
    void successStatusMessage() {
        panel.getStatusLabel().setText("Search successfully completed.");
    }

    @Override
    void executeQuery() {

        try {
            panel.getState().execute();
            successStatusMessage();
            panel.getTable().setModel(new MyTableModel(panel.getState().executeQuery()));
        } catch (Exception e1) {
            panel.setStatusLabel("Missing or invalid data.");
            e1.printStackTrace();
        }
    }
}
