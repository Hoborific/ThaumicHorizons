//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.lib;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

import thaumcraft.api.aspects.AspectList;

import com.kentington.thaumichorizons.common.ThaumicHorizons;

public class EntityInfusionProperties implements IExtendedEntityProperties {

    public static final String EXT_PROP_NAME = "CreatureInfusion";
    public static final int NUM_INFUSIONS = 12;
    public int[] infusions;
    public int[] playerInfusions;
    public AspectList infusionCosts;
    boolean infusionsApplied;
    String owner;
    boolean sitting;
    Entity entity;
    public int tumorWarp;
    public int tumorWarpTemp;
    public int tumorWarpPermanent;
    public boolean toggleClimb;
    public boolean toggleInvisible;

    public EntityInfusionProperties() {
        this.infusions = new int[NUM_INFUSIONS];
        this.playerInfusions = new int[NUM_INFUSIONS];
        this.infusionCosts = new AspectList();
        this.infusionsApplied = false;
    }

    public void saveNBTData(final NBTTagCompound compound) {
        final NBTTagCompound properties = new NBTTagCompound();
        properties.setIntArray("Infusions", this.infusions);
        properties.setIntArray("PlayerInfusions", this.playerInfusions);
        final NBTTagCompound aspects = new NBTTagCompound();
        this.infusionCosts.writeToNBT(aspects);
        properties.setTag("InfusionCosts", (NBTBase) aspects);
        if (this.owner != null && this.owner != "") {
            properties.setString("owner", this.owner);
        }
        properties.setBoolean("sitting", this.sitting);
        properties.setInteger("tumorWarp", this.tumorWarp);
        properties.setInteger("tumorWarpTemp", this.tumorWarpTemp);
        properties.setInteger("tumorWarpPermanent", this.tumorWarpPermanent);
        properties.setBoolean("toggleClimb", this.toggleClimb);
        properties.setBoolean("toggleInvisible", this.toggleInvisible);
        compound.setTag("CreatureInfusion", (NBTBase) properties);
    }

    public void loadNBTData(final NBTTagCompound compound) {
        final NBTTagCompound properties = (NBTTagCompound) compound.getTag("CreatureInfusion");
        this.infusionCosts = new AspectList();
        if (properties != null) {
            this.owner = properties.getString("owner");
            this.sitting = properties.getBoolean("sitting");
            this.infusions = properties.getIntArray("Infusions");
            if (this.infusions.length < NUM_INFUSIONS) {
                this.convertInfusions();
            }
            this.playerInfusions = properties.getIntArray("PlayerInfusions");
            if (this.playerInfusions.length == 0) {
                this.playerInfusions = new int[NUM_INFUSIONS];
            } else if (this.playerInfusions.length < NUM_INFUSIONS) {
                this.convertPlayerInfusions();
            }
            this.tumorWarp = properties.getInteger("tumorWarp");
            this.tumorWarpTemp = properties.getInteger("tumorWarpTemp");
            this.tumorWarpPermanent = properties.getInteger("tumorWarpPermanent");
            this.toggleClimb = properties.getBoolean("toggleClimb");
            this.toggleInvisible = properties.getBoolean("toggleInvisible");
            this.infusionCosts.readFromNBT(properties.getCompoundTag("InfusionCosts"));
            if (!this.infusionsApplied && this.entity instanceof EntityLivingBase
                    && !(this.entity instanceof EntityPlayer)) {
                ThaumicHorizons.instance.eventHandlerEntity.applyInfusions((EntityLivingBase) this.entity);
                this.infusionsApplied = true;
            } else if (this.infusionsApplied || this.entity instanceof EntityPlayer) {}
        }
    }

    void convertInfusions() {
        final int[] newArray = new int[NUM_INFUSIONS];
        System.arraycopy(this.infusions, 0, newArray, 0, this.infusions.length);
        this.infusions = newArray;
    }

    void convertPlayerInfusions() {
        final int[] newArray = new int[NUM_INFUSIONS];
        System.arraycopy(this.playerInfusions, 0, newArray, 0, this.playerInfusions.length);
        this.playerInfusions = newArray;
    }

    public void init(final Entity entity, final World world) {
        this.entity = entity;
    }

    public void addInfusion(final int id) {
        if (id != 0) {
            for (int i = 0; i < this.infusions.length; ++i) {
                if (this.infusions[i] == 0) {
                    this.infusions[i] = id;
                    break;
                }
            }
        }
    }

    public void addPlayerInfusion(final int id) {
        if (id != 0) {
            for (int i = 0; i < this.playerInfusions.length; ++i) {
                if (this.playerInfusions[i] == 0) {
                    this.playerInfusions[i] = id;
                    break;
                }
            }
        }
    }

    public void addCost(final AspectList cost) {
        this.infusionCosts.add(cost);
    }

    public boolean hasInfusion(final int id) {
        for (int i = 0; i < this.infusions.length; ++i) {
            if (this.infusions[i] == id) {
                return true;
            }
        }
        return false;
    }

    public boolean hasPlayerInfusion(final int id) {
        for (int i = 0; i < this.playerInfusions.length; ++i) {
            if (this.playerInfusions[i] == id) {
                return true;
            }
        }
        return false;
    }

    public int[] getInfusions() {
        return this.infusions;
    }

    public int[] getPlayerInfusions() {
        return this.playerInfusions;
    }

    public void resetPlayerInfusions() {
        this.playerInfusions = new int[NUM_INFUSIONS];
        this.tumorWarpPermanent = 0;
        this.tumorWarp = 0;
        this.tumorWarpTemp = 0;
    }

    public AspectList getInfusionCosts() {
        return this.infusionCosts;
    }

    public void setOwner(final String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setSitting(final boolean sit) {
        this.sitting = sit;
    }

    public boolean isSitting() {
        return this.sitting;
    }
}
