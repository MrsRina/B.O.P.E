package rina.turok.bope.bopemod.events;

import net.minecraft.entity.MoverType;

// External.
import rina.turok.bope.external.BopeEventCancellable;

/**
* @author Rina
*
* Created by Rina.
* 12/05/20.
*
*/
public class BopeEventMove extends BopeEventCancellable {
	private MoverType move_type;

	public double x, y, z;

	public BopeEventMove(MoverType type, double x, double y, double z) {
		this.move_type = type;
		this.x         = x;
		this.y         = y;
		this.z         = z;
	}

	public void set_move_type(MoverType type) {
		this.move_type = type;
	}

	public void set_x(double x) {
		this.x = x;
	}

	public void set_y(double y) {
		this.y = y;
	}

	public void set_z(double x) {
		this.z = z;
	}

	public MoverType get_move_type() {
		return this.move_type;
	}

	public double get_x() {
		return this.x;
	}
	
	public double get_y() {
		return this.y;
	}
	
	public double get_z() {
		return this.z;
	}
}
