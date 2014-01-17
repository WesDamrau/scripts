package scripts.Damrau.aioBones.nodes;

import org.tribot.api.General;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;

import scripts.Damrau.aioBones.aioBones;
import scripts.Damrau.api.Node;

public class openBank extends Node {

	@Override
	public void execute() {
		aioBones.clickObject("Bank booth", "Bank");
		General.sleep(General.random(1000, 1500));
	}

	@Override
	public boolean validate() {
		return Inventory.getCount(aioBones.boneID) == 0
				&& !Banking.isBankScreenOpen() && !Banking.isPinScreenOpen() && !aioBones.guiWait;
	}

}
