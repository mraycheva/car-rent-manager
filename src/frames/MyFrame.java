package frames;

import panels.implemented.CarPanel;
import panels.implemented.PersonPanel;
import panels.implemented.RentPanel;
import panels.implemented.RentsByAgePanel;
import panels.templates.EntityPanel;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

public class MyFrame extends JFrame {
    // region Variables

    // region Db Variables
    Connection conn;
    PreparedStatement state;
    ResultSet result;
    int entryId;
    //endregion Db Variables

    // region Tab
    JTabbedPane tab = new JTabbedPane();;
    //endregion Tab

    // region Panels
    PersonPanel personPanel = new PersonPanel();
    RentPanel rentPanel = new RentPanel();
    CarPanel carPanel = new CarPanel();
    RentsByAgePanel rentsByAgePanel = new RentsByAgePanel();
    //endregion

    // endregion Variables

    // region Constructor
    public MyFrame(String title) {
        super(title);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout());
        generateTab();
        pack();
        setVisible(true);
    }
    // endregion Constructor

    // region Methods

    // region Tab Help Methods

    // region generateTab
    private void generateTab() {
        addPansAsListenersToRentPan();
        addPanelsToTab();
        add(tab);
    }
    // endregion generateTab

    // region addPansAsListenersToRentPan
    private void addPansAsListenersToRentPan() {
        addPansAsListenersToRentPan(personPanel, rentPanel);
        addPansAsListenersToRentPan(carPanel, rentPanel);
    }
    // endregion addPansAsListenersToRentPan

    // region addPansAsListenersToRentPan
    private void addPansAsListenersToRentPan(EntityPanel activePanel, EntityPanel listener) {
        activePanel.getAddAction().addPanelAsListener(listener);
        activePanel.getDelAction().addPanelAsListener(listener);
        activePanel.getUpdAction().addPanelAsListener(listener);
    }
    // endregion addPansAsListenersToRentPan

    // region addPanelsToTab
    private void addPanelsToTab() {
        tab.add("Client", personPanel.getWholePan());
        tab.add("Car", carPanel.getWholePan());
        tab.add("Rent", rentPanel.getWholePan());
        tab.add("Rents by Age", rentsByAgePanel.getWholePan());
    }
    // endregion addPanelsToTab

    // endregion Tab Help Methods

    // endregion Methods
}