package rina.turok.bope.bopemod.hacks.combat;

import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
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
	BopeSetting doubles     = create("Double", "AutoCrystalDouble", true);
	BopeSetting ray_trace   = create("Ray Trace", "AutoCrystalRayTrace", true);
	BopeSetting auto_slot   = create("Auto Slot", "AutoCrystalAutoSlot", true); // <3
	BopeSetting place_range = create("Place Range", "AutoCrystalPlaceRange", 5, 0, 6);
	BopeSetting tick_hit    = create("Tick Hit", "AutoCrystalTickHit", 0, 0, 5);
	BopeSetting range_hit   = create("Range Hit", "AutoCrystalRangeHit", 6, 0, 8);
	BopeSetting minimum     = create("Minimum Damage", "AutoCrystalMinimumDamage", 1, 0, 6);

	// Angles events.
	public static boolean spoofing_anlges;
	public static boolean toggling_pitch;

	// Angles values.
	public static double player_pitch;
	public static double player_yaw;

	// Entity specs.
	private Entity   render_entity;
	private BlockPos render_position;

	// Events.
	private boolean switch_cooldown = false;
	private boolean can_attack      = false;

	private int old_slot = -1;
	private int new_slot;

	// Count crystals.
	private int place_count;

	private long system_time = -1l;

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
	private Listener<BopeEventPacket.SendPacket> listner = new Listener<>(event -> {
		if (event.get_packet() instanceof CPacketPlayer && spoofing_anlges) {
			CPacketPlayer player = (CPacketPlayer) event.get_packet();

			player.yaw   = (float) player_yaw;
			player.pitch = (float) player_pitch;
		}
	});

	@Override
	public void enable() {
		old_slot = mc.player.inventory.currentItem;
	}

	@Override
	public void disable() {
		mc.player.inventory.currentItem = old_slot;
	}

	@Override
	public void update() {
		EntityEnderCrystal crystal = request("crystal", 0);

		if (crystal != null && mc.player.getDistance(crystal) <= range_hit.get_value(1)) {
			if (System.nanoTime() / 1000000L - system_time >= tick_hit.get_value(1)) {
				calcule_look_at(crystal.posX, crystal.posY, crystal.posZ, mc.player);

				mc.playerController.attackEntity(mc.player, crystal);
				mc.player.swingArm(EnumHand.MAIN_HAND);

				system_time = System.nanoTime() / 1000000L;

				// ++ to count.
				place_count++;
			}

			if (place_count >= 1 && !doubles.get_value(true)) {
				reset_rotation();

				place_count = 0;

				return;
			}

			if (place_count >= 2 && doubles.get_value(true)) {
				reset_rotation();

				place_count = 0;

				return;
			}
		} else {
			reset_rotation();
		}

		int crystal_slot = (mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL) ? mc.player.inventory.currentItem : -1;
		
		if (crystal_slot == -1) {
			for (int l = 0; l < 9; ++l) {
				if (mc.player.inventory.getStackInSlot(l).getItem() == Items.END_CRYSTAL) {
					crystal_slot = l;

					break;
				}
			}
		}

		boolean off_hand = false;

		if (mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
			off_hand = true;
		} else if (crystal_slot == -1) {
			return;
		}

		List<BlockPos> block  = find_block_to_crystal();
		List<Entity>   entity = new ArrayList<Entity>();

		entity.addAll(mc.world.playerEntities.stream().filter(entity_requested -> !Bope.get_friend_manager().is_friend(entity_requested.getName())).collect(Collectors.toList()));
	
		BlockPos pos = null;

		double damage = 0;

		for (Entity entities : entity) {
			if (entities != mc.player) {
				if (((EntityLivingBase) entities).getHealth() <= 0.0f || ((EntityLivingBase) entities).isDead) {
					continue;
				}

				for (BlockPos blocks : block) {
					double sq = entities.getDistanceSq(blocks);
					double dm = calculate_damage(blocks.x + 0.5, blocks.y + 1, blocks.z + 0.5, entities);
					double sf = calculate_damage(blocks.x + 0.5, blocks.y + 1, blocks.z + 0.5, mc.player);

					if (sq > 169) {
						continue;
					}

					if (dm <= damage) {
						continue;
					}

					if (sf > dm && dm >= ((EntityLivingBase) entities).getHealth()) {
						continue;
					}

					if (sf - 0.4 > mc.player.getHealth()) {
						continue;
					}

					if (dm < minimum.get_value(1)) {
						continue;
					}

					damage        = dm;
					pos           = blocks;
					render_entity = entities;
				}
			}
		}

		if (damage == 0) {
			render_entity   = null;
			render_position = null;

			reset_rotation();

			return;
		}

		render_position = pos;

		if (!off_hand && mc.player.inventory.currentItem != crystal_slot) {
			if (auto_slot.get_value(true)) {
				new_slot                        = crystal_slot;
				mc.player.inventory.currentItem = crystal_slot;

				reset_rotation();
			}
		}

		look_at_packet(pos.x + 0.5, pos.y - 0.5, pos.z + 0.5, mc.player);

		EnumFacing face;

		if (ray_trace.get_value(true)) {
			RayTraceResult result = mc.world.rayTraceBlocks(new Vec3d(mc.player.posX, mc.player.posY + mc.player.getEyeHeight(), mc.player.posZ), new Vec3d(pos.x + 0.5, pos.y - 0.5, pos.z + 0.5));
		
			if (result == null || result.sideHit == null) {
				face = EnumFacing.UP;
			} else {
				face = result.sideHit;
			}
		} else {
			face = EnumFacing.DOWN;
		}

		mc.player.connection.sendPacket((Packet) new CPacketPlayerTryUseItemOnBlock(pos, face, off_hand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
	
		if (spoofing_anlges) {
			if (toggling_pitch) {
				mc.player.rotationPitch += 4.0E-4f;
				toggling_pitch           = false;
			} else {
				mc.player.rotationPitch -= 4.0E-4f;
				toggling_pitch           = true;
			}
		}
	}

	@Override
	public void render(BopeEventRender event) {
		float[] tick_color = {
			(System.currentTimeMillis() % (360 * 32)) / (360f * 32)
		};

		int color_rgb = Color.HSBtoRGB(tick_color[0], 1, 1);

		if (render_entity != null && render_position != null) {
			TurokRenderHelp.prepare("quads");

			TurokRenderHelp.draw_cube_line(render_position, 255, 255, 255, 255, "all");

			TurokRenderHelp.release();
		}
	}

	public void look_at_packet(double px, double py, double pz, EntityPlayer player) {
		double[] view = calcule_look_at(px, py, pz, player);

		set_yaw_and_pitch((float) view[0], (float) view[1]);
	};

	public void set_yaw_and_pitch(float yaw, float pitch) {
		player_yaw      = yaw;
		player_pitch    = pitch;
		spoofing_anlges = true;
	}

	public void reset_rotation() {
		if (spoofing_anlges) {
			player_yaw      = mc.player.rotationYaw;
			player_pitch    = mc.player.rotationPitch;
			spoofing_anlges = false;
		}
	}

	public BlockPos player_pos() {
		return new BlockPos(mc.player.posX, mc.player.posY, mc.player.posZ);
	}

	public List<BlockPos> find_block_to_crystal() {
		NonNullList<BlockPos> position = NonNullList.create();

		// Position.
		position.addAll(sphere(player_pos(), (float) place_range.get_value(1), place_range.get_value(1), false, true).stream().filter(this::can_place_crystal).collect(Collectors.toList()));
	
		return position;
	}

	public List<BlockPos> sphere(BlockPos pos, float r, int h, boolean hollow, boolean sphere) {
		int plus_y = 0;

		List<BlockPos> sphere_block = new ArrayList<BlockPos>();

		int cx = pos.getX();
		int cy = pos.getY();
		int cz = pos.getZ();

		for (int x = cx - (int)r; x <= cx + r; ++x) {
			for (int z = cz - (int)r; z <= cz + r; ++z) {
				for (int y = sphere ? (cy - (int)r) : cy; y < (sphere ? (cy + r) : ((float)(cy + h))); ++y) {
					double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? ((cy - y) * (cy - y)) : 0);
					if (dist < r * r && (!hollow || dist >= (r - 1.0f) * (r - 1.0f))) {
						BlockPos spheres = new BlockPos(x, y + plus_y, z);

						sphere_block.add(spheres);
					}
				}
			}
		}

		return sphere_block;
	}

	public boolean can_place_crystal(BlockPos pos) {
		BlockPos bs1 = pos.add(0, 1, 0);
		BlockPos bs2 = pos.add(0, 2, 0);

		return (mc.world.getBlockState(pos).getBlock() == Blocks.BEDROCK
			||  mc.world.getBlockState(pos).getBlock() == Blocks.OBSIDIAN)
			&&  mc.world.getBlockState(bs1).getBlock() == Blocks.AIR
			&&  mc.world.getBlockState(bs2).getBlock() == Blocks.AIR
			&&  mc.world.getEntitiesWithinAABB((Class) Entity.class, new AxisAlignedBB(bs1)).isEmpty()
			&&  mc.world.getEntitiesWithinAABB((Class) Entity.class, new AxisAlignedBB(bs2)).isEmpty();
	}

	// KAMI :).
	public double[] calcule_look_at(double px, double py, double pz, EntityPlayer player) {
		double diff_x = player.posX - px;
		double diff_y = player.posY - py;
		double diff_z = player.posZ - pz;

		double dif_xyz = Math.sqrt(diff_x * diff_x + diff_y * diff_y + diff_z * diff_z);

		double pitch = Math.asin(diff_y);
		double yaw   = Math.atan2(diff_z, diff_x);

		pitch = pitch * 180.0d / Math.PI;
		yaw   = yaw   * 180.0d / Math.PI;

		yaw += 90f;

		return new double[] {
			yaw, pitch
		};
	}

	public EntityEnderCrystal request(String tag, int index) {
		return (EntityEnderCrystal) mc.world.loadedEntityList.stream().filter(entity -> entity instanceof EntityEnderCrystal).map(entity -> entity).min(Comparator.comparing(d_entity -> mc.player.getDistance(d_entity))).orElse(null);
	}

	public float calculate_damage(double x, double y, double z, Entity entity) {
		float double_explosions = 12.0f;

		double distance_size = entity.getDistance(x, y, z) / double_explosions;

		Vec3d vec3d = new Vec3d(x, y, z);

		double block_density = entity.world.getBlockDensity(vec3d, entity.getEntityBoundingBox());
		double vulgo_kkkkkkk = (1.0 - distance_size) * block_density;
		
		float damage = (float) (int) ((vulgo_kkkkkkk * vulgo_kkkkkkk + vulgo_kkkkkkk) / 2.0 * 7.0 * double_explosions + 1.0);
		
		double finald = 1.0;

		if (entity instanceof EntityLivingBase) {
			finald = get_blast_reduction((EntityLivingBase) entity, get_damage_multiplied(damage), new Explosion(mc.world, null, x, y, z, 6.0f, false, true));
		}

		return (float) finald;
	}

	public float get_blast_reduction(EntityLivingBase entity, float damage, Explosion explosion) {
		if (entity instanceof EntityPlayer) {
			EntityPlayer ep = (EntityPlayer) entity;
			DamageSource ds = DamageSource.causeExplosionDamage(explosion);

			damage = CombatRules.getDamageAfterAbsorb(damage, (float)ep.getTotalArmorValue(), (float)ep.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());

			int k   = EnchantmentHelper.getEnchantmentModifierDamage(ep.getArmorInventoryList(), ds);
			float f = MathHelper.clamp((float) k, 0.0f, 20.0f);

			damage *= 1.0f - f / 25.0f;

			if (entity.isPotionActive(Potion.getPotionById(11))) {
				damage -= damage / 4.0f;
			}
			
			damage = Math.max(damage, 0.0f);
			
			return damage;
		}

		damage = CombatRules.getDamageAfterAbsorb(damage, (float) entity.getTotalArmorValue(), (float) entity.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());

		return damage;
	}

	private float get_damage_multiplied(float damage) {
		int diff = mc.world.getDifficulty().getId();

		return damage * ((diff == 0) ? 0.0f : ((diff == 2) ? 1.0f : ((diff == 1) ? 0.5f : 1.5f)));
	}
}