package models;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {
    // region Variables
    private static final long serialVersionUID = 1L;
    private ResultSet result;
    private int rowCount;
    private int columnCount;
    private ArrayList<Object> data = new ArrayList<Object>();
    // endregion Variables

    // region Constructor
    public MyTableModel(ResultSet rs) throws Exception {
        setRS(rs);
    }
    // endregion Constructor

    // region Methods

    // region setRS - filling ArrayList with ResultSet values
    public void setRS(ResultSet rs) throws Exception {
        this.result = rs;
        ResultSetMetaData metaData = rs.getMetaData();
        rowCount = 0;
        columnCount = metaData.getColumnCount();

        while (rs.next()) {
            Object[] row = new Object[columnCount];

            for (int j = 0; j < columnCount; j++) {
                row[j] = rs.getObject(j + 1);
            }

            data.add(row);
            rowCount++;
        } // while
    }
    // endregion setRS

    // region getColumnCount
    public int getColumnCount() {
        return columnCount;
    }
    // endregion getColumnCount

    // region getRowCount
    public int getRowCount() {
        return rowCount;
    }
    // endregion getRowCount

    // region getValueAt
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object[] row = (Object[]) data.get(rowIndex);
        return row[columnIndex];
    }
    // endregion getValueAt

    // region getColumnName
    public String getColumnName(int columnIndex) {
        try {
            ResultSetMetaData metaData = result.getMetaData();
            return metaData.getColumnLabel(columnIndex + 1);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    // endregion getColumnName

    // region getArrayList
    public ArrayList<Object> getArrayList(){
        return data;
    }
    // endregion getArrayList

    // endregion Methods
}