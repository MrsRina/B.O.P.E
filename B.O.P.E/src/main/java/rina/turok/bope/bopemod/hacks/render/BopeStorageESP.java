package rina.turok.bope.bopemod.hacks.render;

import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.*;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import java.awt.Color;
import java.util.*;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;

// Events.
import rina.turok.bope.bopemod.events.BopeEventRender;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Data.
import rina.turok.bope.bopemod.BopeModule;

// Core.
import rina.turok.bope.Bope;

// Turok.
import rina.turok.turok.draw.TurokRenderHelp;

/**
* @author Rina
*
* Created by Rina.
* 20/05/2020.
*
*/
public class BopeStorageESP extends BopeModule {
	BopeSetting shu_ = create("Shulker Color", "StorageESPShulker", "HUD", combobox("HUD", "Client"));
	BopeSetting enc_ = create("Enchest Color", "StorageESPEnchest", "Client", combobox("HUD", "Client"));
	BopeSetting che_ = create("Chest Color", "StorageESPChest", "Client", combobox("HUD", "Client"));
	BopeSetting oth_ = create("Others Color", "StorageESPOthers", "Client", combobox("HUD", "Client"));
	BopeSetting ot_a = create("Outline A", "StorageESPOutlineA", 150, 0, 255);
	BopeSetting a    = create("Solid A", "StorageESPSolidA", 150, 0, 255);

	private int color_alpha;

	public BopeStorageESP() {
		super(BopeCategory.BOPE_RENDER);

		// Info.
		this.name        = "Storage ESP";
		this.tag         = "StorageESP";
		this.description = "Is able to see storages in world";
	}

	@Override
	public void render(BopeEventRender event) {
		int nl_r = Bope.client_r;
		int nl_g = Bope.client_g;
		int nl_b = Bope.client_b;

		color_alpha = a.get_value(1);

		for (TileEntity tiles : mc.world.loadedTileEntityList) {
			if (tiles instanceof TileEntityShulkerBox) {
				final TileEntityShulkerBox shulker = (TileEntityShulkerBox) tiles;

				int hex = (255 << 24) | shulker.getColor().getColorValue() & 0xFFFFFFFF;

				if (shu_.in("HUD")) {
					draw(tiles, nl_r, nl_g, nl_b);
				} else {
					draw(tiles, (hex & 0xFF0000) >> 16, (hex & 0xFF00) >> 8, (hex & 0xFF));
				}
			}

			if (tiles instanceof TileEntityEnderChest) {
				if (enc_.in("HUD")) {
					draw(tiles, nl_r, nl_g, nl_b);
				} else {
					draw(tiles, 204, 0, 255);
				}
			}

			if (tiles instanceof TileEntityChest) {
				if (che_.in("HUD")) {
					draw(tiles, nl_r, nl_g, nl_b);
				} else {
					draw(tiles, 153, 102, 0);
				}
			}

			if (tiles instanceof TileEntityDispenser ||
				tiles instanceof TileEntityDropper   ||
				tiles instanceof TileEntityHopper    ||
				tiles instanceof TileEntityFurnace   ||
				tiles instanceof TileEntityBrewingStand) {
				if (oth_.in("HUD")) {
					draw(tiles, nl_r, nl_g, nl_b);
				} else {
					draw(tiles, 190, 190, 190);
				}
			}
		}
	}

	public void draw(TileEntity tile_entity, int r, int g, int b) {
		// Solid.
		TurokRenderHelp.prepare("quads");
		TurokRenderHelp.draw_cube(tile_entity.getPos(), r, g, b, a.get_value(1), "all");
		TurokRenderHelp.release();

		// Outline.
		TurokRenderHelp.prepare("lines");
		TurokRenderHelp.draw_cube_line(tile_entity.getPos(), r, g, b, ot_a.get_value(1), "all");
		TurokRenderHelp.release();
	}
}