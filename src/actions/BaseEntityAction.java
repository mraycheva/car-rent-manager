package actions;

import java.sql.SQLException;

import panels.templates.EntityPanel;
import utils.*;

public abstract class BaseEntityAction {
    EntityPanel panel;
    EntityPanel ep2;

    BaseEntityAction(EntityPanel entityPanel) {
        panel = entityPanel;
    }

    public void addPanelAsListener(EntityPanel entityPanel) {
        ep2 = entityPanel;
    }

    boolean stringIsEmpty(String s) {
        boolean isEmpty = false;
        if (s.equals("")) {
            panel.setStatusLabel("Missing data.");
            return true;
        }

        return false;
    }

    boolean intIsEmpty(int id) {
        boolean isEmpty = false;

        if (id < 1) {
            panel.emptySelectionMessage();
            return true;
        }
        return false;
    }

    void prepareState(String state) {
        panel.setConn(DbUtil.getConnection());

        try {
            panel.setState(panel.getConn().prepareStatement(state));
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    abstract void successStatusMessage();
}
