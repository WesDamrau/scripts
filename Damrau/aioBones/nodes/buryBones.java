package scripts.Damrau.aioBones.nodes;

import org.tribot.api.General;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;

import scripts.Damrau.aioBones.aioBones;
import scripts.Damrau.api.Node;

public class buryBones extends Node {

	@Override
	public void execute() {
		Inventory.find(aioBones.boneID)[0].click("Bury");
		General.sleep(500);
		aioBones.status = "Bury";

	}

	@Override
	public boolean validate() {
		return Inventory.getCount(aioBones.boneID) > 0
				&& !Banking.isBankScreenOpen() && !Banking.isPinScreenOpen();
	}

}
