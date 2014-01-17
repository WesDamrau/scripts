package scripts.Damrau.Methods;

import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import org.tribot.api.General;
import org.tribot.api.input.Mouse;
import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.Banking;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.Skills;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;

public class Methods {
	

	public static String perHour(int gained, long runTime) {
		int i = (int) ((gained) * 3600000D / (runTime));
		return "" + NumberFormat.getInstance().format(i);
	}

	public static void paintSkill(Graphics g, long Time, int expGained,
			int skillStartLevel, int expToLevel, int x2, int y2, Color color1,
			Color color2, String skill) {
		int skillCurrentLevel = Skills.getActualLevel(skill);
		if (expGained >= 1) {
			g.setColor(color1);
			g.fillRect(x2 - 5, y2 - 14, 489, 20);
			g.setColor(color2);
			g.drawRect(x2 - 5, y2 - 14, 489, 20);
			g.drawString(
					skill
							+ " || Current Level: "
							+ Skills.getActualLevel(skill)
							+ " (+"
							+ NumberFormat.getInstance().format(
									Skills.getActualLevel(skill)
											- skillStartLevel) + ")"
							+ " || Exp Gained: "
							+ NumberFormat.getInstance().format(expGained)
							+ " ("
							+ NumberFormat.getInstance().format(expToLevel)
							+ " Till " + (skillCurrentLevel + 1) + ")" + " ("
							+ perHour(expGained, Time) + " p/hr)", x2, y2);
			y2 += 20;
		}
	}
	
	public static RSTile pos() {
		return Player.getPosition();
	}

	public static int hp() {
		return Skills.getCurrentLevel("Hitpoints");
	}

	public static RSObject findNearest(int distance, String string) {
		RSObject[] objs = Objects.findNearest(distance, string);
		if (objs.length > 0)
			return objs[0];
		return null;
	}

	public static boolean  isNull(int distance, String string){
		RSObject[] objs = Objects.findNearest(distance, string);
		return objs.length == 0;
	}

	public static void endScript() {
		if (Banking.isBankScreenOpen()) {
			Banking.close();
		}
		Mouse.clickBox(640, 480, 650, 500, 1);
		General.sleep(1000, 1250);
		Mouse.clickBox(580, 365, 700, 380, 1);
		General.sleep(1000, 1250);
		throw new RuntimeException("Shut down");
	}

	public static boolean inArea(RSTile nw, RSTile se, RSTile pos) {

		int posX = pos.getX();
		int posY = pos.getY();
		int nwX = nw.getX();
		int nwY = nw.getY();
		int seX = se.getX();
		int seY = se.getY();

		if (posX >= nwX && posX <= seX && posY >= seY && posY <= nwY) {
			return true;
		} else
			return false;
	}
}
