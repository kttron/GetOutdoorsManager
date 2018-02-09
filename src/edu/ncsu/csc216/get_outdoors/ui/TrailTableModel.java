/**
 * 
 */
package edu.ncsu.csc216.get_outdoors.ui;

/**
 * Handles the Trail Table Model of the GUI
 * @author Jack Wasserman
 *
 */
public class TrailTableModel extends TableModel {
    
	/** Serial version UID */
    private static final long serialVersionUID = 542998100968273770L;
	
	/** The array of column names */
	private String[] colNames = { "ID", "Trail Name", "Maintainance?", "Snow",
    "Distance", "Difficulty" , "Activities" };
	
    /**
     * Creates the model from the given data.
     * 
     * @param data the data to populate the TableModel
     */
	public TrailTableModel(Object[][] data) {
		super(data);
		// TODO Auto-generated constructor stub
	}
	
    /**
     * Returns the number of columns in the data.
     * 
     * @return the number of columns in the data
     */
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colNames.length;
	}
	
    /**
     * Returns the column name at the given index.
     * 
     * @param col the index for finding the column name
     * @return the column name at the given index
     */
	@Override
	public String getColumnName(int col) {
		// TODO Auto-generated method stub
		return colNames[col];
	}
	
    /**
     * Returns the Data object associated with the given row in the TableModel.
     * 
     * @param row the Data to return
     * @return the Data for the given row
     */
	@Override
	public Data getRowData(int row) {
		// TODO Auto-generated method stub
		return  new TrailData((String) data[row][0], (String) data[row][1], (Boolean) data[row][2],
                (Double) data[row][3], (Double) data[row][4], null, null);
	}
	
    /**
     * Sets the given row with the provided Data.
     * 
     * @param d Data to set in the row
     * @param row the row to set
     */
	@Override
	public void setData(Data d, int row) {
		// TODO Auto-generated method stub
		TrailData act = (TrailData) d;
        setValueAt(act.getTrailID(), row, 0);
        setValueAt(act.getTrailName(), row, 1);
        setValueAt(act.isClosedForMaintenance(), row, 2);
        setValueAt(act.getSnow(), row, 3);
        setValueAt(act.getDistance(), row, 4);
        setValueAt(null, row, 5);
        setValueAt(null, row, 6);
	}
}
