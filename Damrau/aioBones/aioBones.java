package scripts.Damrau.aioBones;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Skills;
import org.tribot.api2007.types.RSObject;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Painting;

import scripts.Damrau.Methods.Methods;
import scripts.Damrau.aioBones.nodes.*;
import scripts.Damrau.api.Node;

@ScriptManifest(authors = { "Wes" }, category = "Prayer", name = "DamrauAioBones", description = "Banks and buries bones. (Local)")
public class aioBones extends Script implements Painting {
	public static ArrayList<Node> nodes = new ArrayList<>();

	public static String skill = "Prayer";

	public static int boneID;
	public static int[] junk = { 288, 526, 554, 559, 564, 826, 1207, 1279,
			1295, 1454, 1917, 1969, 1971, 5098, 5101, 5102, 5103, 5104, 5105,
			5281, 5282, 5291, 5292, 5293, 5301, 5305, 5306, 5307, 5308, 5309,
			5318, 5319, 5320, 5322, 5323, 5324, 9003 };

	public static char control = 17;

	public static boolean guiWait = true;

	public String version = "1.00";
	public static String status = "Starting";
	private long startTime;
	public int skillStartExp = Skills.getXP(skill);
	public int skillStartLevel = Skills.getActualLevel(skill);

	Bones g = new Bones();

	@Override
	public void run() {
		g.setVisible(true);
		startTime = System.currentTimeMillis();
		while (guiWait)
			sleep(500);
		Collections.addAll(nodes, new openBank(), new useBank(),
				new buryBones());
		loop(20, 40);
	}

	public static boolean clickObject(String object, String task) {
		RSObject[] obj = Objects.findNearest(20, object);
		return obj.length > 0 && obj[0].getModel().click(task);

	}

	public void onPaint(Graphics g) {

		int x = 350;
		int x2 = 12;
		int y = 18;
		int y2 = 359;
		final Color color1 = new Color(50, 50, 50, 200);
		final Color color2 = new Color(255, 255, 255);

		int expGained = Skills.getXP(skill) - skillStartExp;
		int expToLevel = Skills.getXPToNextLevel(skill);

		long Time = System.currentTimeMillis() - startTime;

		g.setColor(color1);
		g.fillRect(x - 5, y - 14, 171, 65);
		g.setColor(color2);
		g.drawRect(x - 5, y - 14, 171, 65);
		g.drawString("Version: " + version, x, y);
		y += 15;
		g.drawString("Runtime: " + Timing.msToString(Time), x, y);
		y += 15;
		g.drawString("Stage: " + status, x, y);
		y += 15;
		g.drawString("Bone id: " + boneID, x, y);
		y += 15;
		Methods.paintSkill(g, Time, expGained, skillStartLevel, expToLevel, x2,
				y2, color1, color2, skill);
	}

	public class Bones extends JFrame {
		public Bones() {
			initComponents();
		}

		private void startActionPerformed(ActionEvent e) {
			boneID = Integer.parseInt(bones.getText());
			guiWait = false;
			startTime = System.currentTimeMillis();
			Mouse.setSpeed(General.random(200, 250));
			g.dispose();
		}

		private void initComponents() {
			// JFormDesigner - Component initialization - DO NOT MODIFY
			// //GEN-BEGIN:initComponents
			// Generated using JFormDesigner Evaluation license - Zack Damrau
			bones = new JTextField();
			start = new JButton();

			// ======== this ========
			setTitle("Damrau Bones");
			Container contentPane = getContentPane();
			contentPane.setLayout(null);

			// ---- bones ----
			bones.setText("Bone ID");
			contentPane.add(bones);
			bones.setBounds(5, 5, 215, bones.getPreferredSize().height);

			// ---- start ----
			start.setText("Start");
			start.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					startActionPerformed(e);
				}
			});
			contentPane.add(start);
			start.setBounds(5, 30, 215, start.getPreferredSize().height);

			{ // compute preferred size
				Dimension preferredSize = new Dimension();
				for (int i = 0; i < contentPane.getComponentCount(); i++) {
					Rectangle bounds = contentPane.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width,
							preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height,
							preferredSize.height);
				}
				Insets insets = contentPane.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				contentPane.setMinimumSize(preferredSize);
				contentPane.setPreferredSize(preferredSize);
			}
			pack();
			setLocationRelativeTo(getOwner());
			// JFormDesigner - End of component initialization
			// //GEN-END:initComponents
		}

		// JFormDesigner - Variables declaration - DO NOT MODIFY
		// //GEN-BEGIN:variables
		// Generated using JFormDesigner Evaluation license - Zack Damrau
		private JTextField bones;
		private JButton start;
		// JFormDesigner - End of variables declaration //GEN-END:variables
	}

	private void loop(int min, int max) {
		while (true) {
			for (final Node node : nodes) { // loop through the nodes
				if (node.validate()) { // validate each node
					node.execute(); // execute
					sleep(General.random(min, max)); // time inbetween executing
														// nodes
				}
			}
		}
	}
}