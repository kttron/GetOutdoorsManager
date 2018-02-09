/**
 * 
 */
package edu.ncsu.csc216.get_outdoors.ui;

import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentListener;

import edu.ncsu.csc216.get_outdoors.enums.Difficulty;
import edu.ncsu.csc216.get_outdoors.model.Activity;
import edu.ncsu.csc216.get_outdoors.model.ActivityList;
import edu.ncsu.csc216.get_outdoors.util.SortedArrayList;

/**
 * The Trail edit pane shown in the GUI
 * @author Jack Wasserman
 *
 */
public class TrailEditPane extends EditPane {
    
	/** Serial version UID */
    private static final long serialVersionUID = 2282168724908674161L;

	/** List of activities */
	@SuppressWarnings("unused")
	private ActivityList activityList;
	
	private TrailData data;
	
	/** The text of the trail ID */
	private JTextField txtTrailID;
	
	/** The name of the trail ID */
	private JTextField txtTrailName;
	
	/** The field that shows the snow */
	private JTextField txtSnow;
	
	/** The field that shows the distance */
	private JTextField txtDistance;
	
	/** The check box that shows if closed for maintenance or not */
	private JCheckBox txtClosedForMaintence;
	
	/** The combo box that shows the difficulty */
	@SuppressWarnings("unused")
	private JComboBox<Difficulty> toDifficulty;
	
	/** The field that shows the snow */
	@SuppressWarnings("unused")
	private JTextField toActivities;
	
	/**
	 * Constructor for the TrailEditPane
	 * @param activityList list of activities
	 */
	TrailEditPane(ActivityList activityList) {
		this(null, null);
	}
	
	/**
	 * Constructor for the TrailEditPane with multiple parameters
	 * @param trailData the data of the trail
	 * @param activityList list of activities
	 */
	TrailEditPane(TrailData trailData, ActivityList activityList) {
		
		super();
        this.data = trailData;
        init();
        disableEdit();
	}
	/**
	 * Gets the trailId text
	 * @return txtTrailID the trailId
	 */
	JTextField getTxtTrailID() {
        if (null == txtTrailID) {
        	txtTrailID = new JTextField();
        	txtTrailID.setColumns(5);
        	txtTrailID.setEditable(false);
        	txtTrailID.setVisible(true);
        	txtTrailID.setHorizontalAlignment(SwingConstants.LEFT);
        }
        return txtTrailID;
    }
	
	/**
	 * Gets the trail name
	 * @return txtTrailName the name of the trail
	 */
	JTextField getTxtTrailName() {
        if (null == txtTrailName) {
        	txtTrailName = new JTextField();
        	txtTrailName.setColumns(20);
        	txtTrailName.setEditable(false);
            txtTrailName.setVisible(true);
            txtTrailName.setHorizontalAlignment(SwingConstants.LEFT);
        }
        return txtTrailName;
    }
	
	/**
	 * Gets the closedForMaintenance field
	 * @return txtClosedForMaintenance tells if the trail is closed for maintenance or not
	 */
	JCheckBox getClosedForMaintainance() {
        if (null == txtClosedForMaintence) {
        	txtClosedForMaintence = new JCheckBox();
        	txtClosedForMaintence.setVisible(true);
        }
        return txtClosedForMaintence;
    }
	/**
	 * Gets the snowBoundary field
	 * @return txtSnow the amount of snow on the trail
	 */
	JTextField getSnowBoundary() {
        if (null == txtSnow) {
            txtSnow = new JTextField();
            txtSnow.setColumns(5);
            txtSnow.setEditable(false);
            txtSnow.setVisible(true);
            txtSnow.setHorizontalAlignment(SwingConstants.LEFT);
        }
        return txtSnow;
    }
	
	/**
	 * Gets the distance field
	 * @return txtDistance the distance of the trail
	 */
	JTextField getDistance() {
        if (null == txtDistance) {
        	txtDistance = new JTextField();
        	txtDistance.setColumns(5);
        	txtDistance.setEditable(false);
        	txtDistance.setVisible(true);
        	txtDistance.setHorizontalAlignment(SwingConstants.LEFT);
        }
        return txtDistance;
    }
	
	@Override
	protected void initView() {
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEADING));
        p.add(new JLabel("Trail ID: ", SwingConstants.LEFT));
        p.add(this.getTxtTrailID());
        this.add(p);
        p = new JPanel(new FlowLayout(FlowLayout.LEADING));
        p.add(new JLabel("Trail Name: ", SwingConstants.LEFT));
        p.add(this.getTxtTrailName());
        this.add(p);
        p = new JPanel(new FlowLayout(FlowLayout.LEADING));
        p.add(new JLabel("Closed For Maintainance: ", SwingConstants.LEFT));
        this.add(p);
        p = new JPanel(new FlowLayout(FlowLayout.LEADING));
        p.add(this.getClosedForMaintainance());
        this.add(p);
        p = new JPanel(new FlowLayout(FlowLayout.LEADING));
        p.add(new JLabel("Snow", SwingConstants.LEFT));
        this.add(p);
        p = new JPanel(new FlowLayout(FlowLayout.LEADING));
        p.add(this.getSnowBoundary());
        this.add(p);
        p = new JPanel(new FlowLayout(FlowLayout.LEADING));
        p.add(new JLabel("Distance: ", SwingConstants.LEFT));
        this.add(p);
        p = new JPanel(new FlowLayout(FlowLayout.LEADING));
        p.add(this.getDistance());
        this.add(p);
		
	}

    /**
     * Sets data to d
     * 
     * @param d data to set field to
     */
	@Override
	void setData(Data d) {
		data = (TrailData) d;
        fillFields();
		
	}

    /**
     * Enables edit mode and disables add.
     * 
     * @param d Data to populate the edit area with
     */
	@Override
	void enableEdit(Data d) {
		if (!edit) {
            edit = true;
            data = (TrailData) d;
            fillFields();
        }
		
	}

    /**
     * Clears the fields by setting data to null.
     */
	@Override
	void clearFields() {
		data = null;
        fillFields();
		
	}

    /**
     * Adds the given DocumentListener to the text fields.
     * 
     * @param l DocumentListener to add to text fields
     */
	@Override
	void addFieldListener(DocumentListener l) {
	
		getTxtTrailName().getDocument().addDocumentListener(l);
        getSnowBoundary().getDocument().addDocumentListener(l);
        getDistance().getDocument().addDocumentListener(l);
	}

    /**
     * Fills the fields with the appropriate text from the Data field.
     */
	@Override
	void fillFields() {
		if (null == data) {
            txtTrailID.setText("");
            txtTrailName.setText("");
            txtSnow.setText("");
            txtDistance.setText("");
            txtClosedForMaintence.setSelected(false);

            txtTrailID.setEditable(false);
            txtTrailName.setEditable(false);
            txtSnow.setEditable(false);
            txtDistance.setEditable(false);
            txtClosedForMaintence.setEnabled(false);
        } else {
            txtTrailID.setText(data.getTrailID());
            txtTrailName.setText(data.getTrailName());
            txtSnow.setText(data.getSnow() + "");
            txtDistance.setText("" + data.getDistance());
            txtClosedForMaintence.setSelected(data.isClosedForMaintenance());
        }
        if (add) {
            txtTrailName.setEditable(true);
        }
        if (edit || add) {
            // txtTrailID.setEditable(true);
            txtSnow.setEditable(true);
            txtDistance.setEditable(true);
            txtClosedForMaintence.setEnabled(true);
        }
		
	}

    /**
     * Checks that certain fields are not empty
     */
	@Override
	boolean fieldsNotEmpty() {
		return getTxtTrailName().getDocument().getLength() != 0
                && getSnowBoundary().getDocument().getLength() != 0
                && getDistance().getDocument().getLength() != 0;
	}

    /**
     * Returns the fields as a Data object.
     * 
     * @return the fields as a Data object
     */
	@Override
	Data getFields() {
		
		return new TrailData(this.getTxtTrailID().getText(), this.getTxtTrailName().getText(),
				this.getClosedForMaintainance().isSelected() , Double.parseDouble(this.getSnowBoundary().getText()), 
				Double.parseDouble(this.getDistance().getText()), Difficulty.DIFFICULT, null);
	}
	
	/**
	 * Gets the selected Activities and puts them in a SortedArrayList
	 * @return listOfActivites the list of Activities to be selected
	 */
	public SortedArrayList<Activity> getSelectedActivities() {
		return null;
		
	}
	/**
	 * Gets the tableModel of the TrailPane
	 * @return tableModel of the trailPane
	 */
	public TableModel getTableModel() {
		return null;
	}
	
}
