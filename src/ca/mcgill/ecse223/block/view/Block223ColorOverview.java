package ca.mcgill.ecse223.block.view;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
public class Block223ColorOverview extends JComponent{
	public Block223ColorOverview() {
		super();
		//init();
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2=(Graphics2D)g;
		Rectangle2D rect= new Rectangle2D.Double(200,200,50,50);
		g2.draw(rect);
	}
}
