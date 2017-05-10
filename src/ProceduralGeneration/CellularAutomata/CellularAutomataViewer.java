package ProceduralGeneration.CellularAutomata;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ProceduralGeneration.ProceduralMap;
import ProceduralGeneration.ProceduralMapViewer;

public class CellularAutomataViewer extends ProceduralMapViewer<Integer>{
	private static final long serialVersionUID = -469830364840091042L;

	protected javax.swing.JButton rulesButton;
	
	public CellularAutomataViewer(CellularAutomataGenerator generator, CellularAutomataTester tester) {
		super(generator, tester);
		rulesButton = new javax.swing.JButton();
		updateButtons();
		updateActionListeners();
		
		toolBar.add(rulesButton);
		
	}
	
	@Override
	public void updateMap(ProceduralMap<Integer> map){
    	
    	innerVisualPanel.removeAll();
    	innerVisualPanel.setLayout(new GridLayout(map.length, map.width));
    	
    	for(int y = 0; y < map.length; y++){
        	for(int x = 0; x < map.width; x++){
        		
        		JLabel label;
    			label = new JLabel();
    			label.setText(x+","+y);
        		label.setName(x+","+y);
        		
        		JPanel panel = new JPanel();
        		panel.setName(x+","+y);
        		panel.setBackground(((map.get(x, y) == 1) ? Color.GRAY:Color.BLACK));
        		panel.setVisible(true);
        		
        		panel.add(label);
        		innerVisualPanel.add(panel);
        	}
        }
    	
    	visualScrollPane.setViewportView(innerVisualPanel);
    	
    }
	
	public void highlightCell(int x, int y){
		getCell(x, y).setBackground(Color.RED);
	}
	
	public Color getCellColor(int x, int y){
		return getCell(x, y).getBackground();
	}
	
	protected Component getCell(int x, int y){
		for(Component component : innerVisualPanel.getComponents()){
			if(component.getName().equals(x+","+y))
				return component;
		}
		System.err.println("!ERROR! [ProceduralMapViewer] (getCell): Failed to find cell at " + x + "," + y +"...Returning Null.");
		return null;
	}
	
	//used for running and debugging CaveViewer
	public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(CellularAutomataViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CellularAutomataViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CellularAutomataViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CellularAutomataViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @SuppressWarnings("unchecked")
			public void run() {
            	CellularAutomataGenerator caGenerator = new CellularAutomataGenerator();
            	caGenerator.setRules(0, 0, 0);
            	caGenerator.generateMap(0, 10, 10);
            	CellularAutomataTester caTester = new CellularAutomataTester(caGenerator.getMap());
                new CellularAutomataViewer(caGenerator, caTester).setVisible(true);
            }
        });
    }
	
	private void updateButtons(){
		rulesButton.setText("|Set Rules|");
		rulesButton.setFocusable(false);
		rulesButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		rulesButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		rulesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setRulesButtonActionPerformed(evt);
            }
        });
	}
	
	private void updateActionListeners(){
		//give generateButton an updated actionListener to work with this algorithm
		java.awt.event.ActionListener generateButtonActionListener = new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            generateButtonActionPerformed(evt);
	        }
	    };
		replaceActionListener(generateButton, generateButton.getActionListeners()[0], generateButtonActionListener);
	}
	
	//Generate a new map
    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {
       
        String width = JOptionPane.showInputDialog(null, "Width - The number of columns in the map.", "Generate", 1);
        String height = JOptionPane.showInputDialog(null, "Height - The number of rows in the map.", "Generate", 1);
        String lifeChance = JOptionPane.showInputDialog(null, "Life Chance - The chance of a cell being initially alive.", "Generate", 1);
        
        ((CellularAutomataGenerator) generator).setLifeChance(Float.parseFloat(lifeChance));
        
        generator.generateMap(Integer.parseInt(width), Integer.parseInt(height));
        updateMap(generator.getMap());
        
        stepButton.setVisible(true);
        runButton.setVisible(true);
        endButton.setVisible(true);
    }
    
    //Set Rules for Cellular Automata generation
    private void setRulesButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	 String birth = JOptionPane.showInputDialog(null, "Birth Threshold - Number of living cells a dead cell needs to be surrounded by to revive.", "Set Rules", 1);
         String isolation = JOptionPane.showInputDialog(null, "Isolation Threshold- The minimum number of cells a living cell must be surrounded by to stay alive.", "Set Rules", 1);
         String overpopulation = JOptionPane.showInputDialog(null, "Overpopulation Threshold - The maximum number of cells a living cell can be surrounded by before it dies", "Set Rules", 1);
        
        ((CellularAutomataGenerator) generator).setRules(Integer.parseInt(birth), Integer.parseInt(isolation), Integer.parseInt(overpopulation));
    }
    
}
