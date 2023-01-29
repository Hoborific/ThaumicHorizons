//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.entities.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.Vec3;
import net.minecraft.world.biome.BiomeGenBase;

import thaumcraft.common.config.Config;
import thaumcraft.common.lib.utils.Utils;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.entities.EntityTaintPig;

public class EntityAIEatTaint extends EntityAIBase {

    private EntityTaintPig thePig;
    private Vec3 targetCoordinates;
    int cooldown;
    int count;

    public EntityAIEatTaint(final EntityTaintPig par1EntityCreature) {
        this.count = 0;
        this.thePig = par1EntityCreature;
    }

    public boolean shouldExecute() {
        if (this.cooldown > 0) {
            --this.cooldown;
            return false;
        }
        return this.findTaint();
    }

    private boolean findTaint() {
        for (int x = -2; x < 3; ++x) {
            for (int y = -2; y < 3; ++y) {
                for (int z = -2; z < 3; ++z) {
                    if (this.thePig.worldObj.getBlock(
                            (int) this.thePig.posX + x,
                            (int) this.thePig.posY + y,
                            (int) this.thePig.posZ + z).getMaterial() == Config.taintMaterial
                            || (this.thePig.worldObj.getBlock(
                                    (int) this.thePig.posX + x,
                                    (int) this.thePig.posY + y,
                                    (int) this.thePig.posZ + z) == Blocks.grass
                                    && this.thePig.worldObj.getBiomeGenForCoords(
                                            (int) this.thePig.posX + x,
                                            (int) this.thePig.posZ + z).biomeID == Config.biomeTaintID)) {
                        this.targetCoordinates = Vec3.createVectorHelper(
                                (double) ((int) this.thePig.posX + x),
                                (double) ((int) this.thePig.posY + y),
                                (double) ((int) this.thePig.posZ + z));
                        return true;
                    }
                }
            }
        }
        for (int tries = 0; tries < 30; ++tries) {
            final int x2 = (int) this.thePig.posX + this.thePig.worldObj.rand.nextInt(17) - 8;
            final int z = (int) this.thePig.posZ + this.thePig.worldObj.rand.nextInt(17) - 8;
            final int y2 = (int) this.thePig.posY + this.thePig.worldObj.rand.nextInt(5) - 2;
            if ((this.thePig.worldObj.isAirBlock(x2, y2 + 1, z)
                    && this.thePig.worldObj.getBlock(x2, y2, z).getMaterial() == Config.taintMaterial)
                    || (this.thePig.worldObj.getBlock(x2, y2, z) == Blocks.grass
                            && this.thePig.worldObj.getBiomeGenForCoords(x2, z).biomeID == Config.biomeTaintID)) {
                this.targetCoordinates = Vec3.createVectorHelper((double) x2, (double) y2, (double) z);
                return true;
            }
        }
        return false;
    }

    public boolean continueExecuting() {
        return this.count-- > 0 && !this.thePig.getNavigator().noPath() && this.cooldown-- <= 0;
    }

    public void resetTask() {
        this.count = 0;
        this.targetCoordinates = null;
        this.thePig.getNavigator().clearPathEntity();
    }

    public void updateTask() {
        if (this.targetCoordinates == null) {
            return;
        }
        this.thePig.getLookHelper().setLookPosition(
                this.targetCoordinates.xCoord + 0.5,
                this.targetCoordinates.yCoord + 0.5,
                this.targetCoordinates.zCoord + 0.5,
                30.0f,
                30.0f);
        final double dist = this.thePig.getDistanceSq(
                this.targetCoordinates.xCoord + 0.5,
                this.targetCoordinates.yCoord + 0.5,
                this.targetCoordinates.zCoord + 0.5);
        if (dist <= 4.0) {
            this.eatTaint();
        }
    }

    private void eatTaint() {
        if (this.thePig.worldObj.getBlock(
                (int) this.targetCoordinates.xCoord,
                (int) this.targetCoordinates.yCoord,
                (int) this.targetCoordinates.zCoord).getMaterial() == Config.taintMaterial) {
            ThaumicHorizons.proxy.blockSplosionFX(
                    (int) this.targetCoordinates.xCoord,
                    (int) this.targetCoordinates.yCoord,
                    (int) this.targetCoordinates.zCoord,
                    this.thePig.worldObj.getBlock(
                            (int) this.targetCoordinates.xCoord,
                            (int) this.targetCoordinates.yCoord,
                            (int) this.targetCoordinates.zCoord),
                    this.thePig.worldObj.getBlockMetadata(
                            (int) this.targetCoordinates.xCoord,
                            (int) this.targetCoordinates.yCoord,
                            (int) this.targetCoordinates.zCoord));
            this.thePig.worldObj.setBlockToAir(
                    (int) this.targetCoordinates.xCoord,
                    (int) this.targetCoordinates.yCoord,
                    (int) this.targetCoordinates.zCoord);
            Utils.setBiomeAt(
                    this.thePig.worldObj,
                    (int) this.targetCoordinates.xCoord,
                    (int) this.targetCoordinates.zCoord,
                    BiomeGenBase.plains);
            this.thePig.worldObj.playSoundAtEntity(
                    (Entity) this.thePig,
                    "random.burp",
                    0.2f,
                    ((this.thePig.worldObj.rand.nextFloat() - this.thePig.worldObj.rand.nextFloat()) * 0.7f + 1.0f)
                            * 2.0f);
            this.thePig.heal(1.0f);
            this.cooldown = 20;
        } else if (this.thePig.worldObj.getBlock(
                (int) this.targetCoordinates.xCoord,
                (int) this.targetCoordinates.yCoord,
                (int) this.targetCoordinates.zCoord) == Blocks.grass
                && this.thePig.worldObj.getBiomeGenForCoords(
                        (int) this.targetCoordinates.xCoord,
                        (int) this.targetCoordinates.zCoord).biomeID == Config.biomeTaintID) {
                            this.thePig.worldObj.setBlock(
                                    (int) this.targetCoordinates.xCoord,
                                    (int) this.targetCoordinates.yCoord,
                                    (int) this.targetCoordinates.zCoord,
                                    Blocks.dirt);
                            Utils.setBiomeAt(
                                    this.thePig.worldObj,
                                    (int) this.targetCoordinates.xCoord,
                                    (int) this.targetCoordinates.zCoord,
                                    BiomeGenBase.plains);
                            this.thePig.worldObj.playSoundAtEntity(
                                    (Entity) this.thePig,
                                    "random.burp",
                                    0.2f,
                                    ((this.thePig.worldObj.rand.nextFloat() - this.thePig.worldObj.rand.nextFloat())
                                            * 0.7f + 1.0f) * 2.0f);
                            this.thePig.heal(1.0f);
                            this.cooldown = 10;
                        } else {
                            this.resetTask();
                        }
    }

    public void startExecuting() {
        this.count = 500;
        if (this.targetCoordinates != null) {
            this.thePig.getNavigator().tryMoveToXYZ(
                    this.targetCoordinates.xCoord + 0.5,
                    this.targetCoordinates.yCoord + 0.5,
                    this.targetCoordinates.zCoord + 0.5,
                    1.0);
        }
    }
}
