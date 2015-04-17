/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JFrame;

import java.beans.*; //Property change stuff
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

/*
 * DialogDemo.java requires these files:
 *   CustomDialog.java
 *   images/middle.gif
 */
public class DialogDemo extends JPanel {
    JLabel label;
    ImageIcon icon = createImageIcon("images/middle.gif");
    JFrame frame;
    String simpleDialogDesc = "Add a player to a team";
    String iconDesc = "A JOptionPane has its choice of icons";
    String moreDialogDesc = "Get the current roster of a fantasy team";
    CustomDialog customDialog;
    JPanel rosterGrid = null;
    static MLBLeague league;
    static FantasyLeague fantasyLeague;
    /** Creates the GUI shown inside the frame's content pane. */
    public DialogDemo(JFrame frame) {
        super(new BorderLayout());
        this.frame = frame;
        customDialog = new CustomDialog(frame, "geisel", this);
        customDialog.pack();

        //Create the components.
        label = new JLabel("Click the \"Show it!\" button"
                + " to bring up the selected dialog.",
                JLabel.CENTER);
        JPanel addPlayerPanel = createAddPlayerDialogBox();
        JPanel getTeamPanel = createGetTeamDialogBox();
        JPanel getAvailablePlayersPanel = createGetAvailablePlayersDialogBox();

        //Lay them out.
        Border padding = BorderFactory.createEmptyBorder(20,20,5,20);
        addPlayerPanel.setBorder(padding);
        getTeamPanel.setBorder(padding);
        getAvailablePlayersPanel.setBorder(padding);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Add Player Dialog", null,
                          addPlayerPanel,
                          simpleDialogDesc); //tooltip text
        tabbedPane.addTab("Get Team Dialog", null,
        		getTeamPanel,
                          moreDialogDesc); //tooltip text
        tabbedPane.addTab("Dialog Icons", null,
        		getAvailablePlayersPanel,
                          iconDesc); //tooltip text

        add(tabbedPane, BorderLayout.CENTER);
        add(label, BorderLayout.PAGE_END);
        label.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    }

    /** Sets the text displayed at the bottom of the frame. */
    void setLabel(String newText) {
        label.setText(newText);
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = DialogDemo.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /** Creates the panel shown by the first tab. */
    private JPanel createGetTeamDialogBox() {
        FantasyTeam currentlySelectedTeam = null;
        ArrayList<Component> components = new ArrayList<Component>();
        JPanel newRosterGrid = null;
        final ButtonGroup group = new ButtonGroup();
        
    	ArrayList<FantasyTeam> teams = fantasyLeague.getTeams();
        FantasyTeam[] possibilities = teams.toArray(new FantasyTeam[teams.size()]);
        components.add(new JLabel("Team:"));
    	final JComboBox<FantasyTeam> teamBox = new JComboBox(possibilities);
    	components.add(teamBox);
    	currentlySelectedTeam = (FantasyTeam)teamBox.getSelectedItem();
    	ArrayList<String> rosterStringList = null;
    	if(currentlySelectedTeam != null)
    	{
    		rosterStringList =currentlySelectedTeam.getRosterStrings(); 
    		
    	} else
    	{
    		rosterStringList = FantasyTeam.getRosterHeaderStrings();
    	}
    	rosterGrid = createColumnedGrid(rosterStringList.toArray(new String[rosterStringList.size()]), FantasyTeam.NUM_COLUMNS_ROSTER);
    	components.add(rosterGrid);
    	teamBox.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	FantasyTeam currentlySelectedTeam = (FantasyTeam)teamBox.getSelectedItem();
	            	if(currentlySelectedTeam != null)
	            	{
	            		ArrayList<String> rosterStringList =currentlySelectedTeam.getRosterStrings();
		            	rosterGrid = createColumnedGrid(rosterStringList.toArray(new String[rosterStringList.size()]), FantasyTeam.NUM_COLUMNS_ROSTER);
		            	rosterGrid.repaint();
	            	}
	            }
            }
    	);

        return createPane(components,
                          null);
    }

    /**
     * Used by createSimpleDialogBox and createFeatureDialogBox
     * to create a pane containing a description, a single column
     * of radio buttons, and the Show it! button.
     */
    private JPanel createPane(ArrayList<Component> components,
                              JButton showButton) {

        JPanel box = new JPanel();

      
        box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
        box.add(label);

        for (Component c:components) {
            box.add(c);
        }

        JPanel pane = new JPanel(new BorderLayout());
        pane.add(box, BorderLayout.PAGE_START);
        if(showButton != null)
        {
	        pane.add(showButton, BorderLayout.PAGE_END);
        }
        return pane;
    }

    private JPanel createColumnedGrid(String[] strings,
    								  int numColumns)
	{
    	JPanel grid = new JPanel(new GridLayout(0, numColumns));
    	for(String s:strings)
    	{
    		grid.add(new JLabel(s));
    	}
    	return grid;
	}
    /**
     * Like createPane, but creates a pane with 2 columns of radio
     * buttons.  The number of buttons passed in *must* be even.
     */
     private JPanel create2ColPane(String description,
                                  JRadioButton[] radioButtons,
                                  JButton showButton) {
        JLabel label = new JLabel(description);
        int numPerColumn = radioButtons.length/2;

        JPanel grid = new JPanel(new GridLayout(0, 2));
        for (int i = 0; i < numPerColumn; i++) {
            grid.add(radioButtons[i]);
            grid.add(radioButtons[i + numPerColumn]);
        }

        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
        box.add(label);
        grid.setAlignmentX(0.0f);
        box.add(grid);

        JPanel pane = new JPanel(new BorderLayout());
        pane.add(box, BorderLayout.PAGE_START);
        pane.add(showButton, BorderLayout.PAGE_END);

        return pane;
    }

    /*
     * Creates the panel shown by the 3rd tab.
     * These dialogs are implemented using showMessageDialog, but
     * you can specify the icon (using similar code) for any other
     * kind of dialog, as well.
     */
    private JPanel createGetAvailablePlayersDialogBox() {
        JButton showItButton = null;

        final int numButtons = 6;
        JRadioButton[] radioButtons = new JRadioButton[numButtons];
        final ButtonGroup group = new ButtonGroup();

        final String plainCommand = "plain";
        final String infoCommand = "info";
        final String questionCommand = "question";
        final String errorCommand = "error";
        final String warningCommand = "warning";
        final String customCommand = "custom";

        radioButtons[0] = new JRadioButton("Plain (no icon)");
        radioButtons[0].setActionCommand(plainCommand);

        radioButtons[1] = new JRadioButton("Information icon");
        radioButtons[1].setActionCommand(infoCommand);

        radioButtons[2] = new JRadioButton("Question icon");
        radioButtons[2].setActionCommand(questionCommand);

        radioButtons[3] = new JRadioButton("Error icon");
        radioButtons[3].setActionCommand(errorCommand);

        radioButtons[4] = new JRadioButton("Warning icon");
        radioButtons[4].setActionCommand(warningCommand);

        radioButtons[5] = new JRadioButton("Custom icon");
        radioButtons[5].setActionCommand(customCommand);

        for (int i = 0; i < numButtons; i++) {
            group.add(radioButtons[i]);
        }
        radioButtons[0].setSelected(true);

        showItButton = new JButton("Show it!");
        showItButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = group.getSelection().getActionCommand();

                //no icon
                if (command == plainCommand) {
                    JOptionPane.showMessageDialog(frame,
                                    "Eggs aren't supposed to be green.",
                                    "A plain message",
                                    JOptionPane.PLAIN_MESSAGE);
                //information icon
                } else if (command == infoCommand) {
                    JOptionPane.showMessageDialog(frame,
                                    "Eggs aren't supposed to be green.",
                                    "Inane informational dialog",
                                    JOptionPane.INFORMATION_MESSAGE);
                //question icon
                } else if (command == questionCommand) {
                    JOptionPane.showMessageDialog(frame,
                                    "You shouldn't use a message dialog "
                                    + "(like this)\n"
                                    + "for a question, OK?",
                                    "Inane question",
                                    JOptionPane.QUESTION_MESSAGE);
                //error icon
                } else if (command == errorCommand) {
                    JOptionPane.showMessageDialog(frame,
                                    "Eggs aren't supposed to be green.",
                                    "Inane error",
                                    JOptionPane.ERROR_MESSAGE);
                //warning icon
                } else if (command == warningCommand) {
                    JOptionPane.showMessageDialog(frame,
                                    "Eggs aren't supposed to be green.",
                                    "Inane warning",
                                    JOptionPane.WARNING_MESSAGE);
                //custom icon
                } else if (command == customCommand) {
                    JOptionPane.showMessageDialog(frame,
                                    "Eggs aren't supposed to be green.",
                                    "Inane custom dialog",
                                    JOptionPane.INFORMATION_MESSAGE,
                                    icon);
                }
            }
        });

        return create2ColPane(iconDesc + ":",
                              radioButtons,
                              showItButton);
    }

    /** Creates the panel shown by the second tab. */
    private JPanel createAddPlayerDialogBox() {
    	ArrayList<Component> components = new ArrayList<Component>();
    	ArrayList<Player> players = FantasyDraft.getAvailablePlayers();
        Player[] possibilities = players.toArray(new Player[players.size()]);
        components.add(new JLabel(moreDialogDesc));
    	JComboBox<Player> player = new JComboBox(possibilities);
    	components.add(player);
        JButton showItButton = null;

        showItButton = new JButton("Show it!");
        showItButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        
        return createPane(components,
                          showItButton);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     * @param fantasyLeague 
     * @param league 
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("DialogDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        DialogDemo newContentPane = new DialogDemo(frame);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void hopeful(MLBLeague league, FantasyLeague fantasyLeague) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
    	DialogDemo.league = league;
    	DialogDemo.fantasyLeague = fantasyLeague;
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}