/**
 * 
 */
package edu.ncsu.csc216.get_outdoors.ui;

import java.awt.Color;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import edu.ncsu.csc216.get_outdoors.model.TrailList;

/**
 * Handles the Trail List Pane of the GUI
 * @author Jack Wasserman
 *
 */
public class TrailListPane extends ListPane {
   
	/** Serial version UID */
    private static final long serialVersionUID = 1L;
    
    /** List of trails */
    private TrailList trailList;
    
    /** The array of the column widths */
    private int[] colWidths = { 50, 125, 300, 75, 75 , 75};
    
    private TrailTableModel ttm;
	
	@Override
	public void update(Observable o, Object arg) {
		if (trailList.equals(o)) {
            // If there is a different number of rows, create a show new
            // ActivityTableModel.
            if (trailList.size() != ttm.getRowCount()) {
                ttm = new TrailTableModel(trailList.get2DArray());
                table.setModel(ttm);
            } else {
                // Otherwise, just update the values directly.
                Object[][] arr = trailList.get2DArray();
                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < ttm.getColumnCount(); j++) {
                        ttm.setValueAt(arr[i][j], i, j);
                    }
                }
            }
        }
		
	}
	/**
	 * Constructs the Trail List Pane
	 * @param trails the list of trails to display
	 */
	public TrailListPane(TrailList trails) {
        super();
        this.trailList = trails;
        trailList.addObserver(this);
        ttm = new TrailTableModel(trails.get2DArray());
        initView();
    }

	private void initView() {
        table = new JTable(ttm);
        for (int i = 0; i < colWidths.length; i++) {
            TableColumn col = table.getColumnModel().getColumn(i);
            col.setPreferredWidth(colWidths[i]);
        }
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(true);
        setViewportView(table);
        setBorder(BorderFactory.createLineBorder(Color.black));
    }
    /**
     * Returns the TableModel.
     * 
     * @return ttm the TableModel
     */
	@Override
	public TableModel getTableModel() {
		return ttm;
	}

}
