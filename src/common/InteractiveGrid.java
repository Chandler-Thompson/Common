package common;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;

public class InteractiveGrid extends JFrame{

	//TODO: Add JPanels in between JTabbedPane and JScrollPane
	private static final long serialVersionUID = -7986691603271835612L;
	
	private int tabs;
	private int width;
	private int length;
	private boolean isGridSetup;
	
	private JSplitPane splitPane;
	
	private JPanel toolBarPanel;
	private JToolBar toolBar;
	private ArrayList<JButton> toolBarList;
	
	private JPanel gridPanel;
	private JTabbedPane tabbedPane;
	private ArrayList<JPanel> panelList;
	private ArrayList<String> tabNamesList;
	private ArrayList<JScrollPane> scrollPaneList;
	private ArrayList<ArrayList<ArrayList<JButton>>> buttonList;
	
	private ArrayList<JButton> defaultButtonList;
	private ArrayList<ActionListener> gridButtonActionList;
	
	public InteractiveGrid(String name, int width, int length){
		
		super(name);
		
		/* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InteractiveGrid.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InteractiveGrid.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InteractiveGrid.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InteractiveGrid.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
		
		tabs = 1;
		this.width = width;
		this.length = length;
		isGridSetup = false;
		
		splitPane = new JSplitPane();
		
		toolBarPanel = new JPanel();
		toolBar = new JToolBar();
		toolBarList = new ArrayList<>();
		
		gridPanel = new JPanel();
		tabbedPane = new JTabbedPane();
		
		panelList = new ArrayList<>();
		
		tabNamesList = new ArrayList<>();
		tabNamesList.add("Tab 1");
		
		scrollPaneList = new ArrayList<>();
		scrollPaneList.add(new JScrollPane());
		
		buttonList = new ArrayList<>();
		gridButtonActionList = new ArrayList<>();
		
		scrollPaneList.add(new JScrollPane());
		buttonList.add(new ArrayList<ArrayList<JButton>>());
		for(int x = 0; x < width; x++)
			buttonList.get(buttonList.size()-1).add(new ArrayList<JButton>());
		
		defaultButtonList = new ArrayList<>();
		
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		
	}
	
	public void addToolbarButton(String text, String name, ActionListener actionListener){
		toolBarList.add(new JButton(text));
		toolBarList.get(toolBarList.size()-1).setName(name);
		toolBarList.get(toolBarList.size()-1).addActionListener(actionListener);
		toolBar.add(toolBarList.get(toolBarList.size()-1));
	}
	
	public void addGridButton(JButton button, int tab, int x){
		isGridSetup = true;
		buttonList.get(tab).get(x).add(button);
	}
	
	public void addTab(String title){
		
		tabs++;
		tabNamesList.add(title);
		
		scrollPaneList.add(new JScrollPane());
		buttonList.add(new ArrayList<ArrayList<JButton>>());
		
		for(int x = 0; x < width; x++)
			buttonList.get(buttonList.size()-1).add(new ArrayList<JButton>());
		
		buttonList.get(buttonList.size()-1).add(new ArrayList<JButton>());
	}
	
	public void setTabName(String title, int tab){
		tabNamesList.set(tab, title);
	}
	
	public void setGridButtonAction(ActionListener actionListener, int tab){
		
		if(tab == gridButtonActionList.size())
			gridButtonActionList.add(actionListener);
		else if(tab < gridButtonActionList.size())
			gridButtonActionList.set(tab, actionListener);
		else{
			for(int i = 0; i < tab; i++)
				gridButtonActionList.add(null);
			
			gridButtonActionList.set(tab, actionListener);
		}
		
	}
	
	public void setGridButtonDefault(String text, String name, Color color, int tab, boolean isEnabled){
		JButton defaultButton = new JButton();
		defaultButton.setText(text);
		defaultButton.setName(name);
		defaultButton.setBackground(color);
		defaultButton.setEnabled(isEnabled);
		
		if(tab == defaultButtonList.size())
			defaultButtonList.add(defaultButton);
		else if(tab < defaultButtonList.size())
			defaultButtonList.set(tab, defaultButton);
		else{
			for(int i = 0; i < tab; i++)
				defaultButtonList.add(new JButton("No Template Set."));
			
			defaultButtonList.set(tab, defaultButton);
		}
		
	}
	
	//populates grid w/o changing button text or name
	public void populateDefaultGrid(){
		isGridSetup = true;
		populateDefaultGrid(false, false);
	}
	
	//useText = replaces current text with coords
	//useName = replaces current name with coords
	public void populateDefaultGrid(boolean useText, boolean useName){
		
		isGridSetup = true;
		
		for(int z = 0; z < defaultButtonList.size(); z++){//iterate through each tab 
			populateDefaultGridTab(z, useText, useName, defaultButtonList.get(z));
			System.out.println("Populated default grid.");
		}
	}
	
	/**
	 * 
	 * this method to be used if some tabs are to display coords on buttons and others are to not.
	 * 
	 * @param template
	 * 			template[0] = refers to each tab. ie should be the same size as there are tabs
	 *			template[0][0] = boolean useText
	 *			template[0][1] = boolean useName
	 *
	 * @return boolean 
	 * 			(template.size == number of tabs)
	 */
	public boolean populateDefaultGrid(ArrayList<ArrayList<Boolean>> template){
		
		isGridSetup = true;
		
		if(template.size() != defaultButtonList.size()){
			System.out.println("!ERROR! Interactive Grid Template.size() != defaultButtonList.size() !ERROR!");
			return false;
		}
		
		for(int z = 0; z < defaultButtonList.size(); z++){
			populateDefaultGridTab(z, template.get(z).get(0), template.get(z).get(1), defaultButtonList.get(z));
			System.out.println("Populated default grid.");
		}
		
		return true;
	}
	
	private void populateDefaultGridTab(int z, boolean useText, boolean useName, JButton defaultButton){
		for(int x = 0; x < width; x++){//iterate through each tab's x-values
			for(int y = 0; y < length; y++){//iterate through each tab's y-values
				String coords = ""+x+","+y+","+z;
				JButton button = changeButtonLabel(defaultButton, useText, useName, coords);
				button.addActionListener(gridButtonActionList.get(z));
				buttonList.get(z).get(x).add(button);
				System.out.println("(populateDefaultGridTab) Button made at " + coords);
			}
		}
	}
	
	private JButton changeButtonLabel(JButton button, boolean useText, boolean useName, String coords){
		
		if(useText && !useName)
			button.setText(coords);
		else if(!useText && useName)
			button.setName(coords);
		else if(useText && useName){
			button.setText(coords);
			button.setName(coords);
		}
		
		return button;
		
	}
	
	public void finalize(){
		
		//setup toolbar
		toolBar.setOrientation(javax.swing.SwingConstants.VERTICAL);
        toolBar.setRollover(true);
		
		if(!isGridSetup){
			populateDefaultGrid();
			System.out.println("Grid wasn't set up.");
		}
		
		//setup JPanels
		for(int z = 0; z < buttonList.size(); z++){
			JPanel panel = new JPanel();
			panel.setBackground(Color.BLACK);
			panel.setLayout(new GridLayout(width, length));
			for(int x = 0; x < buttonList.get(z).size(); x++){
				for(int y = 0; y < buttonList.get(z).get(x).size(); y++){
					panel.add(buttonList.get(z).get(x).get(y));
					System.out.println("Button added to panel. ("+x+","+y+","+z+")");
				}
			}
			panelList.add(panel);
		}
			
		//setup scroll panes
		for(int z = 0; z < panelList.size(); z++){
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setViewportView(panelList.get(z));
			scrollPaneList.add(scrollPane);
		}
		
		//setup tabs in tabbedPane
		for(int z = 0; z < tabs; z++){
			JPanel panel = new JPanel();
			panel.add(scrollPaneList.get(z));
			tabbedPane.addTab(tabNamesList.get(z), panel);
			System.out.println("Tab "+z+" made.");
		}
			
		//setup toolBarPanel
		toolBarPanel.add(toolBar);
		toolBarPanel.setBounds(0, 0, 100, 1000);
		
		//setup gridPanel
		gridPanel.setLayout(new BorderLayout());
		tabbedPane.setBounds(100, 0, 900, 1000);
		gridPanel.add(tabbedPane,BorderLayout.CENTER);
		gridPanel.setBounds(100, 0, 900, 1000);
		
		//setup splitPanel
		splitPane.setLeftComponent(toolBarPanel);
		splitPane.setRightComponent(gridPanel);
		add(splitPane);
	
        pack();
        
	}
	
	public void showGrid(boolean isShow){
		setExtendedState(Frame.MAXIMIZED_BOTH);
        setVisible(isShow);
	}

	public static void main(String[] args){
		InteractiveGrid grid = new InteractiveGrid("Test Grid", 10, 10);
		grid.addToolbarButton("Button", "", null);
		grid.setTabName("Tab", 0);
		grid.addTab("Tab 2");
		grid.addTab("Another Tab");
		grid.setGridButtonDefault("", "", Color.MAGENTA, 0, true);
		grid.setGridButtonDefault("", "", Color.BLACK, 1, true);
		grid.setGridButtonDefault("", "", Color.BLUE, 2, true);
		grid.setGridButtonAction(null, 0);
		grid.setGridButtonAction(null, 1);
		grid.setGridButtonAction(null, 2);
		grid.populateDefaultGrid(true, false);
		grid.finalize();
		grid.showGrid(true);
	}
	
}
