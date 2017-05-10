package ProceduralGeneration;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public abstract class ProceduralMapViewer<T> extends JFrame{
	private static final long serialVersionUID = -8845229583920802615L;
	
	protected ProceduralMapGenerator<T> generator;
	protected ProceduralTester<T> tester;
	
	//Objects are indented purposefully to show hierarchy.
	protected javax.swing.JPanel toolBarPanel;
		protected javax.swing.JToolBar toolBar;
			protected javax.swing.JButton generateButton;
			protected javax.swing.JButton stepButton;
			protected javax.swing.JButton runButton;
			protected javax.swing.JButton endButton;
    
	protected javax.swing.JPanel outerVisualPanel;
		protected javax.swing.JScrollPane visualScrollPane;
			protected javax.swing.JPanel innerVisualPanel;

    public ProceduralMapViewer(ProceduralMapGenerator<T> generator){
    	
    	this.generator = generator;
		
		//use nimbus look and feel, otherwise use default
		try {
	        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	            if ("Nimbus".equals(info.getName())) {
	                javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                break;
	            }
	        }
	    } catch (ClassNotFoundException ex) {
	        java.util.logging.Logger.getLogger(ProceduralMapViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	    } catch (InstantiationException ex) {
	        java.util.logging.Logger.getLogger(ProceduralMapViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	    } catch (IllegalAccessException ex) {
	        java.util.logging.Logger.getLogger(ProceduralMapViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	        java.util.logging.Logger.getLogger(ProceduralMapViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	    }
	    
	    initComponents();
    	
    }	
	
	public ProceduralMapViewer(ProceduralMapGenerator<T> generator, ProceduralTester<T> tester){

		this.tester = tester;
		this.generator = generator;
		
		//use nimbus look and feel, otherwise use default
		try {
	        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	            if ("Nimbus".equals(info.getName())) {
	                javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                break;
	            }
	        }
	    } catch (ClassNotFoundException ex) {
	        java.util.logging.Logger.getLogger(ProceduralMapViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	    } catch (InstantiationException ex) {
	        java.util.logging.Logger.getLogger(ProceduralMapViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	    } catch (IllegalAccessException ex) {
	        java.util.logging.Logger.getLogger(ProceduralMapViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	        java.util.logging.Logger.getLogger(ProceduralMapViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	    }
	    
	    initComponents();
	}
	
    private void initComponents() {

        toolBarPanel = new javax.swing.JPanel();
        toolBar = new javax.swing.JToolBar();
        
        generateButton = new javax.swing.JButton();
        stepButton = new javax.swing.JButton();
        runButton = new javax.swing.JButton();
        endButton = new javax.swing.JButton();

        outerVisualPanel = new javax.swing.JPanel();
        visualScrollPane = new javax.swing.JScrollPane();
        innerVisualPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        /*
         *	Create Buttons
		 */
        
        generateButton.setText("|Generate|");
        generateButton.setFocusable(false);
        generateButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        generateButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        generateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonActionPerformed(evt);
            }
        });
        
        stepButton.setText("|Step|");
        stepButton.setVisible(false);
        stepButton.setFocusable(false);
        stepButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        stepButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        stepButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stepButtonActionPerformed(evt);
            }
        });

        runButton.setText("|Run|");
        runButton.setVisible(false);
        runButton.setFocusable(false);
        runButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        runButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        runButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runButtonActionPerformed(evt);
            }
        });

        endButton.setText("|End|");
        endButton.setFocusable(false);
        endButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        endButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        endButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endButtonActionPerformed(evt);
            }
        });

        //TODO: Horrible practice...fix it... (using disabled buttons for spacing...shame on you...)
        javax.swing.JButton spacingButton1 = new javax.swing.JButton();
        spacingButton1.setEnabled(false);
        javax.swing.JButton spacingButton2 = new javax.swing.JButton();
        spacingButton2.setEnabled(false);
        javax.swing.JButton spacingButton3 = new javax.swing.JButton();
        spacingButton3.setEnabled(false);
        
        //add buttons to toolbar
        toolBar.add(generateButton);//Generate
        toolBar.add(spacingButton1);
        toolBar.add(stepButton);//Step
        toolBar.add(runButton);//Run
        toolBar.add(spacingButton2);
        toolBar.add(endButton);//End
        toolBar.add(spacingButton3);
        
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
        
        //grid created and populated (with all dead cells initially)
        updateMap(generator.getMap());
        
        //put grid inside visualScrollPane
        visualScrollPane.setViewportView(innerVisualPanel);

        /*
         * outerVisualPanel gets
         * 		visualScrollPane
         */
        javax.swing.GroupLayout outerVisualPanelLayout = new javax.swing.GroupLayout(outerVisualPanel);
        outerVisualPanel.setLayout(outerVisualPanelLayout);
        outerVisualPanelLayout.setHorizontalGroup(
        		outerVisualPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(visualScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
        );
        outerVisualPanelLayout.setVerticalGroup(
        		outerVisualPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(visualScrollPane)
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
	
    /*
     * BUTTON ACTIONS
     */
    
    //Generate a new map
    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
       
        String width = JOptionPane.showInputDialog(null, "Width - The number of columns in the map.", "Generate", 1);
        String height = JOptionPane.showInputDialog(null, "Height - The number of rows in the map.", "Generate", 1);
        
        generator.generateMap(Integer.parseInt(width), Integer.parseInt(height));
        updateMap(generator.getMap());
        
        stepButton.setVisible(true);
        runButton.setVisible(true);
        endButton.setVisible(true);
    }
    
    //Run map through a single step
    private void stepButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
        generator.step();
        updateMap(generator.getMap());
    }                                        

    //Run map multiple steps
    private void runButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
        String steps = JOptionPane.showInputDialog(null, "How many steps would you like the map to run through?", "Run", 1);
        
        try{//In case user cancels operation.
        	generator.run(Integer.parseInt(steps));
        	updateMap(generator.getMap());	
        }catch(NumberFormatException e){}
    }    

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
    
    /**
     * 
     * Override this method to change how the viewer displays the information
     * from the procedural algorithm.
     * 
     * Make sure to start your overrided updateMap() with:
     * 
     * innerVisualPanel.removeAll();
     * innerVisualPanel.setLayout(new GridLayout(map.length, map.width); (assuming you want a grid)
     * 
     * Then create whatever you wish to put into innerVisualPanel and add it in.
     * Then make sure to end the method by adding the panel back into the scrollPane:
     * 
     * visualScrollPane.setViewportView(innerVisualPanel);
     * 
     * @param map - The map generated by your procedural algorithm.
     */
	public void updateMap(ProceduralMap<T> map){
		System.err.println("You are using the default updateMap() in ProceduralMapViewer.");
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
