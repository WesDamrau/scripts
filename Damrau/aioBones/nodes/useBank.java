package scripts.Damrau.aioBones.nodes;

import org.tribot.api.General;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;

import scripts.Damrau.aioBones.aioBones;
import scripts.Damrau.api.Node;

public class useBank extends Node {

	@Override
	public void execute() {
		if (Banking.isPinScreenOpen()) {
			Banking.inPin();
		}
		if (Banking.isBankScreenOpen()) {
			if(Banking.find(aioBones.boneID).length >0){
			if (Inventory.getCount(aioBones.junk) > 0) {
				Banking.depositAll();
				General.sleep(General.random(1000, 1500));
			}
			if (Inventory.getCount(aioBones.junk) == 0
					&& Inventory.getCount(aioBones.boneID) == 0) {
				Banking.withdraw(28, aioBones.boneID);
				General.sleep(General.random(1000, 1500));
			}
			if (Inventory.getCount(aioBones.boneID) > 0) {
				Banking.close();
				General.sleep(General.random(1000, 1500));
			}
			} else {
			Banking.close();
			General.sleep(1000, 1250);
			Mouse.clickBox(640, 480, 650, 500, 1);
			General.sleep(1000, 1250);
			Mouse.clickBox(580, 365, 700, 380, 1);
			General.sleep(1000, 1250);
			throw new RuntimeException("Shut down");
			}
		}
	}

	@Override
	public boolean validate() {
		return Banking.isBankScreenOpen() || Banking.isPinScreenOpen();
	}

}
