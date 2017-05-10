package common;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GUI extends JFrame{
	private static final long serialVersionUID = -8845229583920802615L;
	
	javax.swing.JPanel[][] gridCellPanels;//[tab][cell]
	
	//gui modifiers
	public static boolean GRID = false;
	public static int GRID_WIDTH = 10;
	public static int GRID_HEIGHT = 10;
	
	//Objects are indented purposefully to show hierarchy.
	protected javax.swing.JPanel toolBarPanel;
		protected javax.swing.JToolBar toolBar;
			protected javax.swing.JButton endButton;
    
	protected javax.swing.JPanel outerVisualPanel;
		protected javax.swing.JTabbedPane tabbedPane;
			protected javax.swing.JScrollPane[] visualScrollPanes;
				protected javax.swing.JPanel[] innerVisualPanels;

    private GUI(String name, String[] tabNames){
		super(name);
    	
		gridCellPanels = new javax.swing.JPanel[tabNames.length][GRID_WIDTH*GRID_HEIGHT];
		
		//use nimbus look and feel, otherwise use default
		try {
	        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	            if ("Nimbus".equals(info.getName())) {
	                javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                break;
	            }
	        }
	    } catch (ClassNotFoundException ex) {
	        java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	    } catch (InstantiationException ex) {
	        java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	    } catch (IllegalAccessException ex) {
	        java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	        java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	    }
	    
	    initComponents(tabNames);
    }	
    
    private void initComponents(String[] tabNames) {

        toolBarPanel = new javax.swing.JPanel();
        toolBar = new javax.swing.JToolBar();
        
        endButton = new javax.swing.JButton();

        outerVisualPanel = new javax.swing.JPanel();
        tabbedPane = new javax.swing.JTabbedPane();
        visualScrollPanes = new javax.swing.JScrollPane[tabNames.length];
        innerVisualPanels = new javax.swing.JPanel[tabNames.length];

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        /*
         *	Create Tabs
         */
        
        for(int i = 0; i < tabNames.length; i++){
        	javax.swing.JPanel newInnerVisualPanel = new javax.swing.JPanel();
        	innerVisualPanels[i] = newInnerVisualPanel;
        	
        	if(GRID){
        		innerVisualPanels[i].setLayout(new GridLayout(GRID_WIDTH, GRID_HEIGHT));
        		
        		for(int x = 0; x < GRID_WIDTH*GRID_HEIGHT; x++){
        			javax.swing.JPanel newGridCellPanel = new javax.swing.JPanel();
        			gridCellPanels[i][x] = newGridCellPanel;        			
        			
        			innerVisualPanels[i].add(gridCellPanels[i][x]);
        		}
        	}
        	
        	javax.swing.JScrollPane newVisualScrollPane = new javax.swing.JScrollPane(innerVisualPanels[i]);
        	newVisualScrollPane.setViewportView(innerVisualPanels[i]);
        	visualScrollPanes[i] = newVisualScrollPane;
        	
        	tabbedPane.addTab(tabNames[i], visualScrollPanes[i]);
        }
        
        /*
         *	Create Buttons
		 */

        endButton.setText("|End|");
        endButton.setFocusable(false);
        endButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        endButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        endButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endButtonActionPerformed(evt);
            }
        });
        
        //add buttons to toolbar
        toolBar.add(endButton);//End
        
        /*
         * CREATE JFRAME LAYOUT
         */
        
        /*
         * toolBarPanel gets
         * 		toolBar
         */
        javax.swing.GroupLayout toolBarPanelLayout = new javax.swing.GroupLayout(toolBarPanel);
        toolBarPanel.setLayout(toolBarPanelLayout);
        toolBarPanelLayout.setHorizontalGroup(
        		toolBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        toolBarPanelLayout.setVerticalGroup(
        		toolBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        /*
         * outerVisualPanel gets
         * 		tabbedPane
         */
        javax.swing.GroupLayout outerVisualPanelLayout = new javax.swing.GroupLayout(outerVisualPanel);
        outerVisualPanel.setLayout(outerVisualPanelLayout);
        outerVisualPanelLayout.setHorizontalGroup(
        		outerVisualPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
        );
        outerVisualPanelLayout.setVerticalGroup(
        		outerVisualPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane)
        );
        
        /*
         * this.getContentPane() gets
         * 		toolBarPanel and
         * 		outerVisualPanel
         */
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(outerVisualPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolBarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(outerVisualPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        pack();
    }

    
    public static GUI create(String name, String[] tabNames){
    	return new GUI(name, tabNames);
    }
    
    /*
     * BUTTON ACTIONS
     */   

    //End Program
    private void endButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
        System.exit(0);
    }  
	
    /*
     * AUXILIARY METHODS
     */
    
    /**
     * 
     * Replace a button's older action listener with a newer one.
     * 
     * @param button - The button to change the action listeners of.
     * @param oldListener - The instance of the old action listener to be removed from button.
     * @param newListener - The new action listener to give to the button.
     */
    public void replaceActionListener(javax.swing.JButton button, java.awt.event.ActionListener oldListener, java.awt.event.ActionListener newListener){
    	button.removeActionListener(oldListener);
    	button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newListener.actionPerformed(evt);
            }
        });
    }
	
	/** Returns an ImageIcon, or null if the path was invalid.
	 *Code retrieved from: https://docs.oracle.com/javase/tutorial/uiswing/components/icon.html
	 * @param path - The file path to the image.
	 * @param description - The image description to be shown when the image can't be displayed.
	 * @return An ImageIcon object of the specified image.
	 */
	protected ImageIcon createImageIcon(String path,
	                                           String description) {
	    java.net.URL imgURL = this.getClass().getResource(path);
	    if (imgURL != null) {
	        return new ImageIcon(imgURL, description);
	    } else {
	        System.err.println("Couldn't find file: " + path);
	        return null;
	    }
	}
	
	//Below all used for JPanel cells in case of creating a grid
	
//	public void highlightCell(int x, int y){
//		getCell(x, y).setBackground(Color.RED);
//	}
//	
//	public Color getCellColor(int x, int y){
//		return getCell(x, y).getBackground();
//	}
//	
//	protected Component getCell(int x, int y){
//		for(Component component : innerVisualPanel.getComponents()){
//			if(component.getName().equals(x+","+y))
//				return component;
//		}
//		System.err.println("!ERROR! [ProceduralMapViewer] (getCell): Failed to find cell at " + x + "," + y +"...Returning Null.");
//		return null;
//	}
//	
//	public int getCellXCoord(JPanel panel){
//		return getCellCoords(panel)[0];
//	}
//	
//	public int getCellYCoord(JPanel panel){
//		return getCellCoords(panel)[1];
//	}
//	
//	//int[0] == x
//	//int[1] == y
//	private int[] getCellCoords(JPanel panel){
//		int[] coords = new int[2];
//		
//		String coordString = panel.getName();
//		String x = coordString.substring(0, coordString.indexOf(','));
//		String y = coordString.substring(coordString.indexOf(',')+1);
//		
//		coords[0] = Integer.parseInt(x);
//		coords[1] = Integer.parseInt(y);
//		
//		return coords;
//		
//	}
	
}
