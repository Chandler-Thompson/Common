package ProceduralGeneration.DiamondSquare;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ProceduralGeneration.ProceduralMap;
import ProceduralGeneration.ProceduralMapViewer;

public class DiamondSquareViewer extends ProceduralMapViewer<Color>{
	private static final long serialVersionUID = -469830364840091042L;

	public DiamondSquareViewer(DiamondSquareGenerator generator) {
		super(generator);
		
		runButton.setVisible(true);
		stepButton.setVisible(true);
	}
	
	@Override
	public void updateMap(ProceduralMap<Color> map){
		
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
        		panel.setBackground(map.get(x, y));
        		panel.setVisible(true);
        		
        		panel.add(label);
        		innerVisualPanel.add(panel);
        	}
        }
    	
    	visualScrollPane.setViewportView(innerVisualPanel);
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
	            java.util.logging.Logger.getLogger(DiamondSquareViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (InstantiationException ex) {
	            java.util.logging.Logger.getLogger(DiamondSquareViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (IllegalAccessException ex) {
	            java.util.logging.Logger.getLogger(DiamondSquareViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	            java.util.logging.Logger.getLogger(DiamondSquareViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	        }
	        //</editor-fold>

	        /* Create and display the form */
	        java.awt.EventQueue.invokeLater(new Runnable() {
	            public void run() {
	            	DiamondSquareGenerator dsGenerator = new DiamondSquareGenerator(3);
	            	dsGenerator.generateMap();
	                new DiamondSquareViewer(dsGenerator).setVisible(true);
	            }
	        });
	    }
	
}
