package rina.turok.bope.bopemod.hacks.combat;

import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.CombatRules;
import net.minecraft.util.NonNullList;
import net.minecraft.world.Explosion;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.Packet;
import net.minecraft.potion.Potion;
import net.minecraft.util.EnumHand;
import net.minecraft.item.ItemTool;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Collector;
import java.util.List;
import java.util.*;
import java.awt.*;
import java.io.*;

// Zero Alpine.
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;

// Guiscreen.
import rina.turok.bope.bopemod.guiscreen.settings.BopeSetting;
import rina.turok.bope.bopemod.guiscreen.render.BopeDraw;

// Events.
import rina.turok.bope.bopemod.events.BopeEventRender;
import rina.turok.bope.bopemod.events.BopeEventPacket;

// Modules.
import rina.turok.bope.bopemod.hacks.BopeCategory;

// Util.
import rina.turok.bope.bopemod.util.BopeUtilMath;
import rina.turok.bope.bopemod.util.BopeUtilItem;

// Data.
import rina.turok.bope.bopemod.BopeModule;

// Core.
import rina.turok.bope.Bope;

// Turok.
import rina.turok.turok.draw.TurokRenderHelp;

/**
 * @author 086.
 *
 * Update by Rina.
 * 22/05/2020.
 *
 * - 086 thanks for do the original KAMI crystal aura.
 * - This CA was made by 086.
 * - I have update it, I will change but for now is a KAMI base*. :)
 *
 **/
public class BopeAutoCrystal extends BopeModule {
	BopeSetting spearate_1      = create("info", "AutoCrystalInfo1", "Render");
	BopeSetting rgb             = create("RGB Effect", "AutoCrystalRGBEffect", false);
	BopeSetting color_r         = create("R", "AutoCrystalColorR", 255, 0, 255);
	BopeSetting color_g         = create("G", "AutoCrystalColorG", 255, 0, 255);
	BopeSetting color_b         = create("B", "AutoCrystalColorB", 255, 0, 255);
	BopeSetting solid_a         = create("Solid R", "AutoCrystalSolidA", 255, 0, 255);
	BopeSetting outline_a       = create("Outline R", "AutoCrystalOutlineA", 255, 0, 255);
	BopeSetting spearate_2      = create("info", "AutoCrystalInfo2", "Settings");
	BopeSetting double_place    = create("Double Place", "AutoCrystalDoublePlace", true);
	BopeSetting auto_slot       = create("Auto Switch", "AutoCrystalAutoSlot", true);
	BopeSetting anti_weakness   = create("Anti Weakness", "AutoCrystalAntiWeakness", true);
	BopeSetting offhand_crystal = create("Offhand Crystal Hit", "AutoCrystalOffhandCrystalHit", false);
	BopeSetting ray_trace_util  = create("Ray Trace", "AutoCrystalRayTrace", false);
	BopeSetting face_place_min  = create("Minimum", "AutoCrystalMinimum", 2, 0, 36);
	BopeSetting speed_hit       = create("Speed Hit", "AutoCrystalSpeedHit", 16, 0, 20);
	BopeSetting speed_place     = create("Speed Place", "AtuoCrystalSpeedPlace", 9, 0, 10);
	BopeSetting range_hit       = create("Range Hit", "AutoCrystalRangeHit", 5, 0, 8);
	BopeSetting range_place     = create("Range Place", "AutoCrystalRangePlace", 5, 0, 6);
	BopeSetting range_enemy     = create("Range Enemy", "AutoCrystalRangeEnemy", 13, 0, 16);

	private BlockPos render;
	private Entity render_entity;

	private long system_time_hit    = -1;
	private long system_time_place  = -1;
	private long system_time_double = -1;
	private long system_time_ms     = -1;

	private static boolean toggle_pitch = false;

	private boolean switch_cooldown = false;
	private boolean is_attacking    = false;

	private int old_slot = -1;
	private int new_slot;

	private int places;

	private static boolean is_spoofing_angles;

	private static double yaw;
	private static double pitch;

	private static boolean offhand;
	private static boolean only_crystal_render;
	private static boolean can_break;

	public BopeAutoCrystal() {
		super(BopeCategory.BOPE_COMBAT);

		// Info.
		this.name        = "Auto Crystal";
		this.tag         = "AutoCrystal";
		this.description = "Crystal boom.";

		// Release.
		release("B.O.P.E - module - B.O.P.E"); 
	}

	@EventHandler
	private Listener<BopeEventPacket.SendPacket> listener = new Listener<>(event -> {
		Packet packet = event.get_packet();

		if (packet instanceof CPacketPlayer) {
			if (is_spoofing_angles) {
				((CPacketPlayer) packet).yaw   = (float) yaw;
				((CPacketPlayer) packet).pitch = (float) pitch;
			}
		}
	});

	@Override
	public void enable() {
		render        = null;
		render_entity = null;

		reset_rotation();
	}

	@Override
	public void update() {
		EntityEnderCrystal crystal = mc.world.loadedEntityList.stream()
		/* RinaRinaRinaRinaRinaRinaRinaRinaRinaRinaRinaRin */ .filter(entity -> entity instanceof EntityEnderCrystal)
		/* RinaRinaRinaRinaRinaRinaRinaRinaRinaRinaRinaRin */ .map(entity -> (EntityEnderCrystal) entity)
		/* RinaRinaRinaRinaRinaRinaRinaRinaRinaRinaRinaRin */ .min(Comparator.comparing(c -> mc.player.getDistance(c)))
		/* RinaRinaRinaRinaRinaRinaRinaRinaRinaRinaRinaRin */ .orElse(null);

		if (crystal != null && mc.player.getDistance(crystal) <= range_hit.get_value(1)) {
			if (((System.nanoTime() / 1000000) - system_time_hit) >= 400 - speed_hit.get_value(1) * 20) {
				if (anti_weakness.get_value(true) && mc.player.isPotionActive(MobEffects.WEAKNESS)) {
					if (is_attacking) {
						old_slot     = mc.player.inventory.currentItem;
						is_attacking = false;
					}

					new_slot = -1;

					for (int i = 0; i < 9; i++) {
						ItemStack stack = mc.player.inventory.getStackInSlot(i);

						if (stack == ItemStack.EMPTY) {
							continue;
						}

						if (stack.getItem() instanceof ItemSword) {
							new_slot = i;

							break;
						}

						if (stack.getItem() instanceof ItemTool) {
							new_slot = i;

							break;
						}
					}

					if (new_slot != -1) {
						mc.player.inventory.currentItem = new_slot;

						switch_cooldown = true;
					}
				}

				ItemStack off_hand_item = mc.player.getHeldItemOffhand();
				
				if (off_hand_item != null && off_hand_item.getItem() == Items.SHIELD && off_hand_item.getItem() != Items.END_CRYSTAL) {
					mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, mc.player.getHorizontalFacing()));
				}

				BopeUtilMath.calcule_look_at(crystal.posX, crystal.posY, crystal.posZ, mc.player);

				mc.playerController.attackEntity(mc.player, crystal);
				mc.player.swingArm(offhand == true && offhand_crystal.get_value(true) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND);

				system_time_hit = System.nanoTime() / 1000000;
			}

			if (!double_place.get_value(true)) {
				if (System.nanoTime() / 1000000L - system_time_double >= 20 * speed_hit.get_value(1) && System.nanoTime() / 1000000L - system_time_ms <= 400 + (400 - speed_hit.get_value(1) * 20)) {
					system_time_double = System.nanoTime() / 1000000;

					return;
				} else if (System.nanoTime() / 1000000L - system_time_ms <= 400 + (400 - speed_hit.get_value(1) * 20)) {
					return;
				}
			}
		} else {
			reset_rotation();

			if (old_slot != -1) {
				mc.player.inventory.currentItem = old_slot;
				old_slot                        = -1;
			}

			is_attacking = false;
		}

		int crystal_slot = mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL ? mc.player.inventory.currentItem : -1;

		if (crystal_slot == -1) {
			crystal_slot = BopeUtilItem.get_hotbar_item_slot(Items.END_CRYSTAL);
		}

		offhand = false;

		if (mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
			offhand = true;
		} else if (crystal_slot == -1) {
			return;
		}

		List<BlockPos> blocks = find();
		List<Entity> entities = new ArrayList<>();

		entities.addAll(mc.world.playerEntities.stream().filter(entity_player -> !Bope.get_friend_manager().is_friend(entity_player.getName())).collect(Collectors.toList()));
	
		BlockPos q = null;

		double damage = 0;

		for (Entity entity : entities) {
			if (mc.player == entity) {
				continue;
			}

			if (((EntityLivingBase) entity).getHealth() <= 0.0f || ((EntityLivingBase) entity).isDead) {
				continue;
			}

			for (BlockPos pos : blocks) {
				double sq = entity.getDistanceSq(pos);

				if (sq > range_enemy.get_value(1) * range_enemy.get_value(1)) {
					continue;
				}

				double dm = calcule_damage(pos.x + 0.5, pos.y + 1, pos.z + 0.5, entity);
				if (dm <= damage) {
					continue;
				}

				double sf = calcule_damage(pos.x + 0.5, pos.y + 1, pos.z + 0.5, mc.player);

				if (sf > dm && dm >= ((EntityLivingBase) entity).getHealth()) {
					continue;
				}

				if (sf - 0.5 > mc.player.getHealth()) {
					continue;
				}

				if (dm < face_place_min.get_value(1)) {
					continue;
				}

				damage        = dm;
				q             = pos;
				render_entity = entity;
			}
		}

		if (damage == 0) {
			render        = null;
			render_entity = null;

			reset_rotation();

			return;
		}

		render = q;

		if (!offhand && mc.player.inventory.currentItem != crystal_slot) {
			if (auto_slot.get_value(true)) {
				mc.player.inventory.currentItem = crystal_slot;

				reset_rotation();

				switch_cooldown = true;
			}

			return;
		}

		BopeUtilMath.calcule_look_at(q.x + 0.5, q.y - 0.5, q.z + 0.5, mc.player);

		EnumFacing f;

		if (ray_trace_util.get_value(true)) {
			RayTraceResult result = mc.world.rayTraceBlocks(new Vec3d(mc.player.posX, mc.player.posY + mc.player.getEyeHeight(), mc.player.posZ), new Vec3d(q.x + 0.5, q.y - 0.5d, q.z + 0.5));
		
			if (result == null || result.sideHit == null) {
				f = EnumFacing.UP;
			} else {
				f = result.sideHit;
			}
		} else {
			f = EnumFacing.DOWN;
		}

		if (switch_cooldown) {
			switch_cooldown = false;

			return;
		}

		ItemStack off_hand_item = mc.player.getHeldItemOffhand();

		if (System.nanoTime() / 1000000L - system_time_place >= speed_place.get_value(1) * 2) {
			mc.player.connection.sendPacket(new CPacketPlayerTryUseItemOnBlock(q, f, offhand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0, 0, 0));

			system_time_place  = System.nanoTime() / 1000000;
			system_time_ms     = System.nanoTime() / 1000000;
		}

		if (is_spoofing_angles) {
			if (toggle_pitch) {
				mc.player.rotationPitch += 0.0004;
				toggle_pitch             = false;
			} else {
				mc.player.rotationPitch -= 0.0004;
				toggle_pitch             = true;
			}
		}
	}

	@Override
	public void render(BopeEventRender event) {
		float[] tick_color = {
			(System.currentTimeMillis() % (360 * 32)) / (360f * 32)
		};
	
		int color_rgb = Color.HSBtoRGB(tick_color[0], 1, 1);

		int r;
		int g;
		int b;
	
		if (rgb.get_value(true)) {
			r = ((color_rgb >> 16) & 0xFF);
			g = ((color_rgb >> 8) & 0xFF);
			b = (color_rgb & 0xFF);
	
			color_r.set_value(r);
			color_g.set_value(g);
			color_b.set_value(b);
		} else {
			r = color_r.get_value(1);
			g = color_g.get_value(2);
			b = color_b.get_value(3);
		}

		if (render != null) {
			// Solid.			
			TurokRenderHelp.prepare("quads");
			TurokRenderHelp.draw_cube(TurokRenderHelp.get_buffer_build(),
				render.x, render.y, render.z,
				1, 1, 1,
				r, g, b, solid_a.get_value(1),
				"all"
			);

			TurokRenderHelp.release();

			// Outline.
			TurokRenderHelp.prepare("lines");
			TurokRenderHelp.draw_cube_line(TurokRenderHelp.get_buffer_build(),
				render.x, render.y, render.z,
				1, 1, 1,
				r, g, b, outline_a.get_value(1),
				"all"
			);

			TurokRenderHelp.release();
		}
	}

	private boolean can_place_crystal(BlockPos pos) {
		BlockPos boost_1 = pos.add(0, 1, 0);
		BlockPos boost_2 = pos.add(0, 2, 0);

		return (mc.world.getBlockState(pos).getBlock() == Blocks.BEDROCK
				|| mc.world.getBlockState(pos).getBlock() == Blocks.OBSIDIAN)
				&& mc.world.getBlockState(boost_1).getBlock() == Blocks.AIR
				&& mc.world.getBlockState(boost_2).getBlock() == Blocks.AIR
				&& mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(boost_1)).isEmpty()
				&& mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(boost_2)).isEmpty();
		}

	public BlockPos get_player_as_block_pos() {
		return new BlockPos(Math.floor(mc.player.posX), Math.floor(mc.player.posY), Math.floor(mc.player.posZ));
	}

	private List<BlockPos> find() {
		NonNullList<BlockPos> positions = NonNullList.create();

		positions.addAll(get_sphere(get_player_as_block_pos(), (float) range_place.get_value(1), range_place.get_value(1), false, true, 0).stream().filter(this::can_place_crystal).collect(Collectors.toList()));
	
		return positions;
	}

	public List<BlockPos> get_sphere(BlockPos loc, float r, int h, boolean hollow, boolean sphere, int plus_y) {
		List<BlockPos> sphere_list = new ArrayList<>();

		int cx = loc.getX();
		int cy = loc.getY();
		int cz = loc.getZ();

		for (int x = cx - (int) r; x <= cx + r; x++) {
			for (int z = cz - (int) r; z <= cz + r; z++) {
				for (int y = (sphere ? cy - (int) r : cy); y < (sphere ? cy + r : cy + h); y++) {
					double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? (cy - y) * (cy - y) : 0);
					if (dist < r * r && !(hollow && dist < (r - 1) * (r - 1))) {
						BlockPos spheres = new BlockPos(x, y + plus_y, z);
						
						sphere_list.add(spheres);
					}
				}
			}
		}

		return sphere_list;
	}

	public float calcule_damage(double pos_x, double pos_y, double pos_z, Entity entity) {
		float double_explosion_size = 6.0f * 2.0f;

		double distanced_size = entity.getDistance(pos_x, pos_y, pos_z) / (double) double_explosion_size;

		Vec3d vec3d = new Vec3d(pos_x, pos_y, pos_z);

		double block_desinty = (double) entity.world.getBlockDensity(vec3d, entity.getEntityBoundingBox());
		double v             = (1.0d - distanced_size) * block_desinty;

		float damage = (float) ((int) ((v * v + v) / 2.0d * 7.0d * (double) double_explosion_size + 1.0d));

		double finald = 1;

		if (entity instanceof EntityLivingBase) {
		    finald = get_blast_reduction((EntityLivingBase) entity, get_damage_multiplied(damage), new Explosion(mc.world, null, pos_x, pos_y, pos_z, 6f, false, true));
		}

		return (float) finald;
	}

	public float get_blast_reduction(EntityLivingBase entity, float damage, Explosion explosion) {
		if (entity instanceof EntityPlayer) {
			EntityPlayer ep = (EntityPlayer) entity;
			DamageSource ds = DamageSource.causeExplosionDamage(explosion);

			damage = CombatRules.getDamageAfterAbsorb(damage, (float) ep.getTotalArmorValue(), (float) ep.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());

			int k   = EnchantmentHelper.getEnchantmentModifierDamage(ep.getArmorInventoryList(), ds);
			float f = MathHelper.clamp(k, 0.0F, 20.0F);

			damage = damage * (1.0F - f / 25.0F);

			if (entity.isPotionActive(Potion.getPotionById(11))) {
			    damage = damage - (damage / 4);
			}

			damage = Math.max(damage - ep.getAbsorptionAmount(), 0.0f);

			return damage;
		}

		damage = CombatRules.getDamageAfterAbsorb(damage, (float) entity.getTotalArmorValue(), (float) entity.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());

		return damage;
	}

	private float get_damage_multiplied(float damage) {
		int diff = mc.world.getDifficulty().getId();

		return damage * (diff == 0 ? 0 : (diff == 2 ? 1 : (diff == 1 ? 0.5f : 1.5f)));
	}

	public float calcule_damage(EntityEnderCrystal crystal, Entity entity) {
		return calcule_damage(crystal.posX, crystal.posY, crystal.posZ, entity);
	}

	public void set_angles(double new_yaw, double new_pitch) {
		yaw   = new_yaw;
		pitch = new_pitch;

		is_spoofing_angles = true;
	}

	public void reset_rotation() {
		if (is_spoofing_angles) {
			yaw   = mc.player.rotationYaw;
			pitch = mc.player.rotationPitch;

			is_spoofing_angles = false;
		}
	}

	public int get_ping() {
		int ping = -1;

		if (mc.player == null || mc.getConnection() == null || mc.getConnection().getPlayerInfo(mc.player.getName()) == null) {
			return -1;
		} else {
			ping = mc.getConnection().getPlayerInfo(mc.player.getName()).getResponseTime();
		}

		return ping;
	}
}