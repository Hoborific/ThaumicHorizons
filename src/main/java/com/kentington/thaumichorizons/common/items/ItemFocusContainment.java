// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.items;

import thaumcraft.api.aspects.Aspect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.AxisAlignedBB;
import java.util.List;
import net.minecraft.util.Vec3;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.NetworkRegistry;
import com.kentington.thaumichorizons.common.lib.networking.PacketFXContainment;
import com.kentington.thaumichorizons.common.lib.networking.PacketHandler;
import thaumcraft.common.lib.utils.InventoryUtils;
import thaumcraft.common.config.ConfigBlocks;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.NBTTagCompound;
import com.kentington.thaumichorizons.common.entities.EntitySoul;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.Potion;
import thaumcraft.common.entities.golems.EntityGolemBase;
import net.minecraft.entity.boss.IBossDisplayData;
import thaumcraft.common.Thaumcraft;
import net.minecraft.entity.EntityLiving;
import thaumcraft.common.lib.utils.BlockUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import thaumcraft.common.items.wands.ItemWandCasting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.StatCollector;
import net.minecraft.item.ItemStack;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import thaumcraft.api.aspects.AspectList;
import net.minecraft.util.IIcon;
import net.minecraft.entity.Entity;
import java.util.HashMap;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;

public class ItemFocusContainment extends ItemFocusBasic
{
    public static FocusUpgradeType slow;
    public static HashMap<String, Object> beam;
    public static HashMap<String, Entity> hitCritters;
    public static HashMap<String, Float> contain;
    public static HashMap<String, Long> soundDelay;
    IIcon depthIcon;
    private static final AspectList cost;
    
    public ItemFocusContainment() {
        this.depthIcon = null;
        this.setCreativeTab(ThaumicHorizons.tabTH);
    }
    
    public String getItemStackDisplayName(final ItemStack stack) {
        return StatCollector.translateToLocal("item.focusContainment.name");
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister ir) {
        this.depthIcon = ir.registerIcon("thaumichorizons:focus_containment_depth");
        this.icon = ir.registerIcon("thaumichorizons:focus_containment");
    }
    
    @Override
    public IIcon getFocusDepthLayerIcon(final ItemStack itemstack) {
        return this.depthIcon;
    }
    
    @Override
    public int getFocusColor(final ItemStack itemstack) {
        return 29631;
    }
    
    @Override
    public String getSortingHelper(final ItemStack itemstack) {
        return "CN" + super.getSortingHelper(itemstack);
    }
    
    @Override
    public AspectList getVisCost(final ItemStack focusstack) {
        return ItemFocusContainment.cost.copy();
    }
    
    @Override
    public FocusUpgradeType[] getPossibleUpgradesByRank(final ItemStack focusstack, final int rank) {
        switch (rank) {
            case 1: {
                return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
            }
            case 2: {
                return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, ItemFocusContainment.slow };
            }
            case 3: {
                return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
            }
            case 4: {
                return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, ItemFocusContainment.slow };
            }
            case 5: {
                return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
            }
            default: {
                return null;
            }
        }
    }
    
    @Override
    public ItemStack onFocusRightClick(final ItemStack itemstack, final World world, final EntityPlayer p, final MovingObjectPosition movingobjectposition) {
        final ItemWandCasting wand = (ItemWandCasting)itemstack.getItem();
        p.setItemInUse(itemstack, Integer.MAX_VALUE);
        return itemstack;
    }
    
    @Override
    public void onUsingFocusTick(final ItemStack stack, final EntityPlayer p, final int count) {
        final ItemWandCasting wand = (ItemWandCasting)stack.getItem();
        if (!this.canJarEntity(p)) {
            if (p.ticksExisted % 5 == 0) {
                p.addChatMessage((IChatComponent)new ChatComponentText(EnumChatFormatting.ITALIC + EnumChatFormatting.GRAY + StatCollector.translateToLocal("thaumichorizons.noJar")));
            }
            p.stopUsingItem();
            return;
        }
        if (!wand.consumeAllVis(stack, p, this.getVisCost(stack), false, false)) {
            p.stopUsingItem();
            return;
        }
        final String pp = "R" + p.getDisplayName();
        final Entity ent = getPointedEntity(p.worldObj, (EntityLivingBase)p, 10.0);
        final MovingObjectPosition mop = BlockUtils.getTargetBlock(p.worldObj, (Entity)p, true);
        final Vec3 v = p.getLookVec();
        double tx = p.posX + v.xCoord * 10.0;
        double ty = p.posY + v.yCoord * 10.0;
        double tz = p.posZ + v.zCoord * 10.0;
        byte impact = 0;
        if (ent != null && ent instanceof EntityLiving) {
            tx = ent.posX;
            ty = ent.posY + (ent.boundingBox.maxY - ent.boundingBox.minY) / 2.0;
            tz = ent.posZ;
            impact = 5;
            if (p != null && p.worldObj != null && !p.worldObj.isRemote && (ItemFocusContainment.soundDelay.get(pp) == null || ItemFocusContainment.soundDelay.get(pp) < System.currentTimeMillis())) {
                ent.worldObj.playSoundEffect(tx, ty, tz, "thaumcraft:jacobs", 0.3f, 1.0f);
                ItemFocusContainment.soundDelay.put(pp, System.currentTimeMillis() + 1200L);
            }
        }
        else if (mop != null) {
            tx = mop.hitVec.xCoord;
            ty = mop.hitVec.yCoord;
            tz = mop.hitVec.zCoord;
            impact = 5;
        }
        else {
            ItemFocusContainment.soundDelay.put(pp, 0L);
        }
        if (p.worldObj.isRemote) {
            ItemFocusContainment.beam.put(pp, Thaumcraft.proxy.beamCont(p.worldObj, p, tx, ty, tz, 2, 4482815, true, (impact > 0) ? 2.0f : 0.0f, ItemFocusContainment.beam.get(pp), (int)impact));
        }
        if (ent != null && ent instanceof EntityLiving && !(ent instanceof EntityPlayer) && !ent.isDead && !(ent instanceof IBossDisplayData) && !(ent instanceof EntityGolemBase) && wand.consumeAllVis(stack, p, this.getVisCost(stack), true, false)) {
            if (this.getUpgradeLevel(wand.getFocusItem(stack), ItemFocusContainment.slow) > 0) {
                ((EntityLiving)ent).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 40, this.getUpgradeLevel(wand.getFocusItem(stack), ItemFocusContainment.slow) - 1));
            }
            if (ItemFocusContainment.hitCritters.get(pp) == null || ent.getEntityId() != ItemFocusContainment.hitCritters.get(pp).getEntityId()) {
                ItemFocusContainment.hitCritters.put(pp, ent);
                ItemFocusContainment.contain.put(pp, 2.0f + wand.getFocusPotency(stack) / 2.0f);
            }
            else {
                ItemFocusContainment.contain.put(pp, ItemFocusContainment.contain.get(pp) + 2.0f + wand.getFocusPotency(stack) / 3);
            }
            if (!p.worldObj.isRemote && ItemFocusContainment.contain.get(pp) > ((EntityLiving)ent).getHealth() * 20.0f && !(ent instanceof EntitySoul)) {
                final NBTTagCompound entityData = new NBTTagCompound();
                entityData.setString("id", EntityList.getEntityString(ent));
                ent.writeToNBT(entityData);
                this.jarEntity(p, entityData, ent.getCommandSenderName(), ent.posX, ent.posY + ent.height / 2.0f, ent.posZ);
                p.worldObj.playSoundEffect(ent.posX, ent.posY + (ent.boundingBox.maxY - ent.boundingBox.minY) / 2.0, ent.posZ, "thaumcraft:craftfail", 1.0f, 1.0f);
                ItemFocusContainment.contain.remove(pp);
                ItemFocusContainment.hitCritters.remove(pp);
                p.worldObj.removeEntity(ent);
            }
            else if (!p.worldObj.isRemote && ItemFocusContainment.contain.get(pp) > ((EntityLiving)ent).getHealth() * 20.0f) {
                p.worldObj.playSoundEffect(ent.posX, ent.posY + (ent.boundingBox.maxY - ent.boundingBox.minY) / 2.0, ent.posZ, "thaumcraft:craftfail", 1.0f, 1.0f);
                p.inventory.decrStackSize(InventoryUtils.isPlayerCarrying(p, new ItemStack(ConfigBlocks.blockJar, 1, 0)), 1);
                final ItemStack jar = new ItemStack(ThaumicHorizons.blockJar);
                jar.setTagCompound(new NBTTagCompound());
                jar.getTagCompound().setBoolean("isSoul", true);
                if (!p.inventory.addItemStackToInventory(jar)) {
                    p.entityDropItem(jar, 1.0f);
                }
                if (!p.worldObj.isRemote) {
                    PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXContainment(ent.posX, ent.posY, ent.posZ), new NetworkRegistry.TargetPoint(p.worldObj.provider.dimensionId, ent.posX, ent.posY, ent.posZ, 32.0));
                }
                ItemFocusContainment.contain.remove(pp);
                ItemFocusContainment.hitCritters.remove(pp);
                p.worldObj.removeEntity(ent);
            }
        }
    }
    
    public static Entity getPointedEntity(final World world, final EntityLivingBase entityplayer, final double range) {
        Entity pointedEntity = null;
        final Vec3 vec3d = Vec3.createVectorHelper(entityplayer.posX, entityplayer.posY + entityplayer.getEyeHeight(), entityplayer.posZ);
        final Vec3 vec3d2 = entityplayer.getLookVec();
        final Vec3 vec3d3 = vec3d.addVector(vec3d2.xCoord * range, vec3d2.yCoord * range, vec3d2.zCoord * range);
        final float f1 = 1.1f;
        final List list = world.getEntitiesWithinAABBExcludingEntity((Entity)entityplayer, entityplayer.boundingBox.addCoord(vec3d2.xCoord * range, vec3d2.yCoord * range, vec3d2.zCoord * range).expand((double)f1, (double)f1, (double)f1));
        double d2 = 0.0;
        for (int i = 0; i < list.size(); ++i) {
            final Entity entity = (Entity) list.get(i);
            if (entity.canBeCollidedWith() && world.rayTraceBlocks(Vec3.createVectorHelper(entityplayer.posX, entityplayer.posY + entityplayer.getEyeHeight(), entityplayer.posZ), Vec3.createVectorHelper(entity.posX, entity.posY + entity.getEyeHeight(), entity.posZ), false) == null) {
                final float f2 = Math.max(0.8f, entity.getCollisionBorderSize());
                final AxisAlignedBB axisalignedbb = entity.boundingBox.expand((double)f2, (double)f2, (double)f2);
                final MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(vec3d, vec3d3);
                if (axisalignedbb.isVecInside(vec3d)) {
                    if (0.0 < d2 || d2 == 0.0) {
                        pointedEntity = entity;
                        d2 = 0.0;
                    }
                }
                else if (movingobjectposition != null) {
                    final double d3 = vec3d.distanceTo(movingobjectposition.hitVec);
                    if (d3 < d2 || d2 == 0.0) {
                        pointedEntity = entity;
                        d2 = d3;
                    }
                }
            }
        }
        return pointedEntity;
    }
    
    boolean canJarEntity(final EntityPlayer p) {
        return InventoryUtils.inventoryContains((IInventory)p.inventory, new ItemStack(ConfigBlocks.blockJar, 1, 0), 0, true, true, false) && InventoryUtils.placeItemStackIntoInventory(new ItemStack(ThaumicHorizons.blockJar), (IInventory)p.inventory, 0, false) == null;
    }
    
    void jarEntity(final EntityPlayer p, final NBTTagCompound tag, final String name, final double x, final double y, final double z) {
        p.inventory.decrStackSize(InventoryUtils.isPlayerCarrying(p, new ItemStack(ConfigBlocks.blockJar, 1, 0)), 1);
        final ItemStack jar = new ItemStack(ThaumicHorizons.blockJar);
        jar.setTagCompound(tag);
        jar.getTagCompound().setString("jarredCritterName", name);
        jar.getTagCompound().setBoolean("isSoul", false);
        if (!p.inventory.addItemStackToInventory(jar)) {
            p.entityDropItem(jar, 1.0f);
        }
        if (!p.worldObj.isRemote) {
            PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXContainment(x, y, z), new NetworkRegistry.TargetPoint(p.worldObj.provider.dimensionId, x, y, z, 32.0));
        }
    }
    
    @Override
    public boolean isVisCostPerTick(final ItemStack focusstack) {
        return true;
    }
    
    @Override
    public WandFocusAnimation getAnimation(final ItemStack focusstack) {
        return WandFocusAnimation.CHARGE;
    }
    
    static {
        ItemFocusContainment.slow = new FocusUpgradeType(FocusUpgradeType.types.length, new ResourceLocation("thaumichorizons", "textures/foci/slow.png"), "focus.upgrade.slow.name", "focus.upgrade.slow.text", new AspectList().add(Aspect.TRAP, 8));
        ItemFocusContainment.beam = new HashMap<String, Object>();
        ItemFocusContainment.hitCritters = new HashMap<String, Entity>();
        ItemFocusContainment.contain = new HashMap<String, Float>();
        ItemFocusContainment.soundDelay = new HashMap<String, Long>();
        cost = new AspectList().add(Aspect.AIR, 10).add(Aspect.ENTROPY, 10);
    }
}
