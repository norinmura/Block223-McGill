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
import javax.swing.JLabel;
import javax.swing.JPanel;
import ca.mcgill.ecse223.block.controller.TOBlock;

public class Block223BlockDisplayPanelInLevel extends JPanel {

  ArrayList<TOBlock> blocks = null;
  double width;
  Dimension blockSize;
  int blockXPosition[] = {10, (int) width / 3 + 5, (int) width / 3 * 2};
  ArrayList<Rectangle2D.Double> rectangles;
  HashMap<Rectangle2D.Double, TOBlock> blockPairs;

  private JLabel pointLabelField;
  private JLabel rLabelField;
  private JLabel gLabelField;
  private JLabel bLabelField;
  private JLabel idLabelField;
  private JPanel currentBlock;

  public Block223BlockDisplayPanelInLevel(List<TOBlock> blocks, Dimension dimension,
      JLabel pointField, JLabel rField, JLabel gField, JLabel bField, JPanel currentBlock, JLabel idField) {
    this.blocks = (ArrayList<TOBlock>) blocks;
    this.pointLabelField = pointField;
    this.rLabelField = rField;
    this.gLabelField = gField;
    this.bLabelField = bField;
    this.idLabelField = idField;
    this.currentBlock = currentBlock;

    width = dimension.getWidth() - 10;
    blockSize = new Dimension((int) width / 3 + 5, (int) width / 3 + 5);
    blockXPosition[0] = 10;
    blockXPosition[1] = (int) width / 3 + 34;
    blockXPosition[2] = (int) width / 3 * 2 + 55;
    rectangles = new ArrayList<Rectangle2D.Double>();
    blockPairs = new HashMap<Rectangle2D.Double, TOBlock>();

  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    Graphics2D gp = (Graphics2D) g.create();
    for (int i = 0; i < blocks.size(); i++) {
      Rectangle2D.Double rect = new Rectangle2D.Double(blockXPosition[i % 3],
          (blockSize.height + 10) * (i / 3) + 10, blockSize.width, blockSize.height);
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
            pointLabelField.setText(blockPairs.get(rectangle).getPoints() + "");
            rLabelField.setText(blockPairs.get(rectangle).getRed() + "");
            gLabelField.setText(blockPairs.get(rectangle).getGreen() + "");
            bLabelField.setText(blockPairs.get(rectangle).getBlue() + "");
            idLabelField.setText(blockPairs.get(rectangle).getId()+"");

            break;
          }
        }

      }
    });
  }

}
