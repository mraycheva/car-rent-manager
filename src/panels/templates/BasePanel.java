package panels.templates;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import models.*;
import utils.*;

abstract public class BasePanel {
    // region Variables

    // region Panels

    // region wholePan
    JPanel wholePan = new JPanel();
    // endregion Panel

    // region wholePan sub-panels
    JPanel upperPan = new JPanel();
    JPanel bottomPan = new JPanel();
    // endregion wholePan sub-panels

    // region upperPan sub-panels
    protected JPanel upperPanTop = new JPanel();
    JPanel upperPanBot = new JPanel();
    // endregion upperPan sub-panels

    // region upperPanBot sub-panels
    protected JPanel btnsPanel = new JPanel();
    JPanel statusPanel = new JPanel();
    //endregion upperPanBot sub-panels

    // endregion Panels

    // region Panels' elements

    // region upperPanBot Status Label
    protected JLabel statusLabel = new JLabel();
    //endregion upperPanBot Status Label

    // region upperPanBot Buttons
    protected JButton cancelBtn = new JButton("Cancel");;
    protected JButton searchBtn = new JButton("Search");;
    protected JButton clearBtn = new JButton("Clear");;
    //endregion upperPanBot Buttons

    // region bottomPan Table and Scroller
    protected JTable table = new JTable();;
    JScrollPane scroller = new JScrollPane(table);
    //endregion bottomPan Table and Scroller

    // endregion Panels' elements

    // region Database variables
    protected Connection conn;

    protected PreparedStatement state;
    protected ResultSet result;
    protected int id;
    //endregion Database variables

    // endregion Variables

    // region Constructor
    public BasePanel() {
        wholePan.setLayout(new BorderLayout());
        upperPan.setLayout(new BorderLayout());
    }
    // endregion Constructor

    // region Methods

    //region generateUpperPanBotPan
    protected void generateUpperPanBotPan() {
        upperPanBot.setLayout(new BorderLayout());

        generateMiddlePanButtons();
        generateMiddlePanStatPanel();
    }
    //endregion generateUpperPanBotPan

    // region MiddlePan Help Methods

    // region generateMiddlePanEntButtons
    public abstract void generateMiddlePanButtons();
    // endregion generateMiddlePanEntButtons

    // region generateMiddlePanStatPanel
    void generateMiddlePanStatPanel() {
        upperPanBot.add(btnsPanel, BorderLayout.NORTH);
        statusLabel.setForeground(Color.RED);
        statusPanel.add(statusLabel);
        upperPanBot.add(statusPanel, BorderLayout.CENTER);
    }
    // endregion generateMiddlePanStatPanel

    // endregion MiddlePan Help Methods

    // region generateBottomPan
    protected void generateBottomPan() {
        scroller.setPreferredSize(new Dimension(450, 200));
        bottomPan.add(scroller);
    }
    // endregion generateBottomPan

    // region loadUpperPanSubPanels
    void loadUpperPanSubPanels(){
        upperPan.add(upperPanTop, BorderLayout.NORTH);
        upperPan.add(upperPanBot, BorderLayout.CENTER);
    }
    // endregion loadUpperPanSubPanels

    // region loadMainPanSubPanels
    protected void loadMainPanSubPanels() {
        loadUpperPanSubPanels();

        wholePan.add(upperPan, BorderLayout.NORTH);
        wholePan.add(bottomPan, BorderLayout.CENTER);
    }
    // endregion loadMainPanSubPanels

    // region getAllDBModel
    protected MyTableModel getAllDBModel(String entity) {
        String sql = "select * from " + entity + " order by 1";
        conn = DbUtil.getConnection();

        try {
            state = conn.prepareStatement(sql);
            result = state.executeQuery();

            return new MyTableModel(result);
        } catch (Exception e) {
            statusLabel.setText("Invalid data.");
            e.printStackTrace();
        }
        return null;
    }
    // endregion getAllDBModel

    // region Table Selection Methods

    // region getColDistinctDbModel
    public MyTableModel getColDistinctDbModel(String entity, String col) {
        String sql = "select distinct " + col + " from " + entity + " order by 1";
        conn = DbUtil.getConnection();

        try {
            state = conn.prepareStatement(sql);
            result = state.executeQuery();

            return new MyTableModel(result);
        } catch (Exception e) {
            statusLabel.setText("Invalid data.");
            e.printStackTrace();
        }
        return null;
    }
    // endregion getColDistinctDbModel

    // endregion Table Selection Methods

    // region Getters and Setters

    // region getWholePan
    public JPanel getWholePan() {
        return wholePan;
    }
    // endregion getWholePan

    // region getUpperPanTop
    public JPanel getUpperPanTop() {
        return upperPanTop;
    }
    // endregion getUpperPanTop

    // region getUpperPanBot
    public JPanel getUpperPanBot() {
        return upperPanBot;
    }
    // endregion getUpperPanBot

    // region getBottomPan
    public JPanel getBottomPan() {
        return bottomPan;
    }
    // endregion getBottomPan

    // region getCancelBtn
    public JButton getCancelBtn() {
        return cancelBtn;
    }
    // endregion getCancelBtn

    // region getBtnsPanel
    public JPanel getBtnsPanel() {
        return btnsPanel;
    }
    // endregion getBtnsPanel

    // region getStatusPanel
    public JPanel getStatusPanel() {
        return statusPanel;
    }
    // endregion getStatusPanel

    // region getStatusLabel
    public JLabel getStatusLabel() {
        return statusLabel;
    }
    // endregion getStatusLabel

    // region setStatusLabel
    public void setStatusLabel(String statusText) {
        this.statusLabel.setText(statusText);
    }
    // endregion setStatusLabel

    // region getTable
    public JTable getTable() {
        return table;
    }
    // endregion getTable

    // region getScroller
    public JScrollPane getScroller() {
        return scroller;
    }
    // endregion getScroller

    // region getConn
    public Connection getConn() {
        return conn;
    }
    // endregion getConn

    // region setConn
    public void setConn(Connection conn) {
        this.conn = conn;
    }
    // endregion setConn

    // region getState
    public PreparedStatement getState() {
        return state;
    }
    // endregion getState

    // region setState
    public void setState(PreparedStatement state) {
        this.state = state;
    }
    // endregion setState

    // region getResult
    public ResultSet getResult() {
        return result;
    }
    // endregion getResult

    // region getId
    public int getId() {
        return id;
    }
    // endregion getId

    // region setId
    public void setId(int id) {
        this.id = id;
    }
    // endregion setId

    // endregion Getters and Setters

    public abstract void clearFields();

    public abstract TableModel getAllDBModel();

    // endregion Methods
}