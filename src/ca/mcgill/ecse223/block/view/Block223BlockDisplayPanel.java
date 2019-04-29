package ca.mcgill.ecse223.block.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import ca.mcgill.ecse223.block.controller.TOBlock;

public class Block223BlockDisplayPanel extends JPanel {

  ArrayList<TOBlock> blocks = null;
  double width;
  Dimension blockSize;
  int blockXPosition[] = {10, (int) width / 3 + 5, (int) width / 3 * 2};
  ArrayList<Rectangle2D.Double> rectangles;
  HashMap<Rectangle2D.Double, TOBlock> blockPairs;
  HashMap<Integer, Integer>blockSelectionIndexes;
  
  private JTextField pointTextField;
  private JTextField rTextField;
  private JTextField gTextField;
  private JTextField bTextField;
  private JComboBox<String> blockIdSelection;
  private JPanel currentBlock;

  public Block223BlockDisplayPanel(List<TOBlock> blocks, Dimension dimension,
      JComboBox<String> idSelection, JTextField pointField, JTextField rField, JTextField gField,
      JTextField bField, JPanel currentBlock, HashMap<Integer, Integer> blockSelectionIndexes) {
    this.blocks = (ArrayList<TOBlock>) blocks;
    this.pointTextField = pointField;
    this.rTextField = rField;
    this.gTextField = gField;
    this.bTextField = bField;
    this.blockIdSelection = idSelection;
    this.currentBlock = currentBlock;
    
    width = dimension.getWidth() - 10;
    blockSize = new Dimension((int) width / 3+5, (int) width / 3+5);
    blockXPosition[0] = 10;
    blockXPosition[1] = (int) width / 3 +34;
    blockXPosition[2] = (int) width / 3 * 2 +55;
    rectangles = new ArrayList<Rectangle2D.Double>();
    blockPairs = new HashMap<Rectangle2D.Double, TOBlock>();
    this.blockSelectionIndexes = blockSelectionIndexes;
    
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    Graphics2D gp = (Graphics2D) g.create();
    for (int i = 0; i < blocks.size(); i++) {
      Rectangle2D.Double rect = new Rectangle2D.Double(blockXPosition[i % 3],
          (blockSize.height+10) * (i / 3) +10, blockSize.width, blockSize.height);
      rectangles.add(rect);
      blockPairs.put(rect, blocks.get(i));
      gp.setColor(
          new Color(blocks.get(i).getRed(), blocks.get(i).getGreen(), blocks.get(i).getBlue()));
      gp.fill(rectangles.get(i));
    }
    
    addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        for (Rectangle2D rectangle : rectangles) {
          if (rectangle.contains(x, y)) {
            currentBlock.setBackground(new Color(blockPairs.get(rectangle).getRed(),
                blockPairs.get(rectangle).getGreen(), blockPairs.get(rectangle).getBlue()));
            pointTextField.setText(blockPairs.get(rectangle).getPoints() + "");
            rTextField.setText(blockPairs.get(rectangle).getRed() + "");
            gTextField.setText(blockPairs.get(rectangle).getGreen() + "");
            bTextField.setText(blockPairs.get(rectangle).getBlue() + "");
            blockIdSelection.setSelectedIndex(blockSelectionIndexes.get(blockPairs.get(rectangle).getId()));
            
            break;
          }
        }

      }
    });
  }

  public void setBlocks(ArrayList<TOBlock> blocks, HashMap<Integer, Integer>blockSelectionIndexes) {
    this.blocks = blocks;
    this.blockSelectionIndexes = blockSelectionIndexes;
    repaint();
  }


}
