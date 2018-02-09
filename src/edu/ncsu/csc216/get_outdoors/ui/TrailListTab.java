/**
 * 
 */
package edu.ncsu.csc216.get_outdoors.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;

import edu.ncsu.csc216.get_outdoors.enums.Difficulty;
import edu.ncsu.csc216.get_outdoors.model.Activity;
import edu.ncsu.csc216.get_outdoors.model.ActivityList;
import edu.ncsu.csc216.get_outdoors.model.Trail;
import edu.ncsu.csc216.get_outdoors.model.TrailList;
import edu.ncsu.csc216.get_outdoors.util.SortedArrayList;

/**
 * TODO
 * @author Jack Wasserman
 *
 */
public class TrailListTab extends Tab {
	
	/** Serial version UID */
    private static final long serialVersionUID = 542998100968273770L;
	
	/** List of trails */
	private TrailList trails;
	
	/**
	 * Constructs the TrailListTab
	 * @param listOfTrails the list of trails to show in the GUI
	 * @param listOfActivities the list of activities to show in the GUI
	 */
	TrailListTab(TrailList listOfTrails, ActivityList listOfActivities) {
		//TODO
		super();
        this.trails = listOfTrails;

        // Create the ActivityListPane (table)
        this.setListPane(new TrailListPane(this.trails));
        // Add a ListSelectionListener to the listPane so that ActivityTab
        // can respond to selection events.
        this.getListPane().getTable().getSelectionModel().addListSelectionListener(this);

        // Creates the ActivityPane (form)
        this.setEditPane(new TrailEditPane(listOfActivities));
        // Adds a FieldListener to the editPane so that ActivityTab can
        // respond
        // to events in fields that are part of the ActivityPane.
        this.getEditPane().addFieldListener(this);

        // Sets the layout for the tab and adds the element.
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(this.getListPane());
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(this.getEditPane());
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(this.getButtons());
	}
	
	/**
	 * Deletes
	 */
	public void delete() {
		this.trails.removeTrail(this.trails.size() - 1);
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
        if (addMode && e.getActionCommand().equals("add")) {
            try {
                TrailData d = (TrailData) getEditPane().getFields();
                if (d.getTrailName() == null || d.getTrailName().trim().equals("")) {

                    JOptionPane.showMessageDialog(this, "Trail name must be non-whitespace",
                            "Activity Error", JOptionPane.ERROR_MESSAGE);
                }  else

                if (d.getDistance() < 0) {
                    JOptionPane.showMessageDialog(this, "Snow boundary must be non-negative",
                            "Activity Error", JOptionPane.ERROR_MESSAGE);

                } else {
                    getEditPane().setData(d);
                    trails.addTrail(d.getTrailName(), new SortedArrayList<Activity>(), d.isClosedForMaintenance(), d.getSnow(), d.getDistance(), Difficulty.DIFFICULT);
                    enableAdd(false);
                    getEditPane().disableAdd();
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Snow boundary must be a non-negative integer.",
                        "Activity Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException iae) {
                JOptionPane.showMessageDialog(this, "Activity name must be unique.",
                        "Activity Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (editMode && e.getActionCommand().equals("save")) {
        	TrailData d = (TrailData) getEditPane().getFields();
            if (d.getTrailName() == null || d.getTrailName().trim().equals("")) {

                JOptionPane.showMessageDialog(this, "Trail name must be non-whitespace",
                        "Activity Error", JOptionPane.ERROR_MESSAGE);
            }  else

            if (d.getDistance() < 0) {
                JOptionPane.showMessageDialog(this, "Snow boundary must be non-negative",
                        "Activity Error", JOptionPane.ERROR_MESSAGE);

            } else {
                getEditPane().setData(d);
                Trail trail = trails
                        .getTrailAt(trails.indexOfID(d.getTrailID()));
                trail.setSnow(d.getSnow());
                trail.setDistance(d.getDistance());
                
                getListPane().clearSelection();
                enableSave(false);
                getEditPane().disableEdit();
            }
        } else if (e.getActionCommand().equals("cancel")) {
            getEditPane().clearFields();
            if (addMode) {
                enableAdd(false);
                getEditPane().disableAdd();
            }
            if (editMode) {
                getListPane().clearSelection();
                enableSave(false);
                getEditPane().disableEdit();
            }
        }
    }

}
