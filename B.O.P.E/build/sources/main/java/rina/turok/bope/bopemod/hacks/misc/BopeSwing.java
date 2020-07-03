package rina.turok.bope.bopemod.hacks.misc;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;

// Data.
import rina.turok.bope.bopemod.BopeModule;

// Core.
import rina.turok.bope.Bope;

// Turok.
import rina.turok.turok.draw.TurokRenderHelp;

/**
 * @author CryroByte
 *
 * Created by CryroByte.
 * took a couple trys to make ^_^
 *
 */
public class BopeSwing extends BopeModule {
    BopeSetting sword = create("Only Sword", "BopeSwingOnlySword", false);

    public BopeSwing() {
        super(BopeCategory.BOPE_MISC, false);

        // Info.
        this.name        = "Swing";
        this.tag         = "Swing";
        this.description = "What is swing??";

        // Release.
        release("B.O.P.E - module - B.O.P.E");
    }

    @Override
    public void update() {
        EntityRenderer Entity;
        ItemRenderer Item;

        boolean boo;

        if (sword.get_value(true)) {
            boom : {
                EntityPlayerSP Sp = mc.player;
                ItemStack Stack   = Sp.getHeldItemMainhand();

                if (Stack.getItem() instanceof ItemSword) {
                    Entity = mc.entityRenderer;
                    Item   = Entity.itemRenderer;
 
                    if ((double) Item.prevEquippedProgressMainHand >= 0.9D) {
                        boo = true;

                        break boom;
                    }
                }
                ItemStack var70 = Sp.getHeldItemMainhand();
                boo = false;
            }
        } else {
            boo = true;
        }

        boolean b = boo;

        if (b) {
            Entity                        = mc.entityRenderer;
            Item                          = Entity.itemRenderer;
            Item.equippedProgressMainHand = 1.0F;
            Entity                        = mc.entityRenderer;
            Item                          = Entity.itemRenderer;
            EntityPlayerSP var10001       = mc.player;
            Item.itemStackMainHand        = var10001.getHeldItemMainhand();
        }
    }
}
