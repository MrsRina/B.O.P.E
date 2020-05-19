package rina.turok.bope.bopemod.hacks.render;


import rina.turok.bope.bopemod.BopeModule;
import rina.turok.bope.bopemod.events.BopeEventPacket;
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;
import rina.turok.bope.bopemod.hacks.BopeCategory;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import rina.turok.turok.draw.TurokRenderHelp;



// Modules.
// Guiscreen.
// Data.

// Core.


/**
 * @author CryroByte
 *
 * Created by CryroByte.
 * took a couple trys to make ^_^
 *
 */
public class Swing extends BopeModule {

    public Swing() {
        super(BopeCategory.BOPE_RENDER);

        this.name = "Swing";
        this.tag = "Swing";
        this.description = "This module is for test.";

        release("B.O.P.E - module - B.O.P.E");
    }
    BopeSetting sword = create("Only Sword", "OnlySword", false);
    public void update() {
        EntityRenderer Entity;
        ItemRenderer Item;
        boolean boo;
        if (sword.get_value(true)) {
            boom:
            {
                EntityPlayerSP Sp = TurokRenderHelp.getPlayer();
                ItemStack Stack = Sp.getHeldItemMainhand();
                if (Stack.getItem() instanceof ItemSword) {
                    Entity = TurokRenderHelp.getMinecraft().entityRenderer;
                    Item = Entity.itemRenderer;
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
            Entity = TurokRenderHelp.getMinecraft().entityRenderer;
            Item = Entity.itemRenderer;
            Item.equippedProgressMainHand = 1.0F;
            Entity = TurokRenderHelp.getMinecraft().entityRenderer;
            Item = Entity.itemRenderer;
            EntityPlayerSP var10001 = TurokRenderHelp.getPlayer();
            Item.itemStackMainHand = var10001.getHeldItemMainhand();
        }
    }
}
