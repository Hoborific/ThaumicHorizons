// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.tiles;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.items.wands.ItemWandCasting;
import net.minecraft.entity.player.EntityPlayer;
import com.kentington.thaumichorizons.common.lib.PocketPlaneData;
import thaumcraft.common.config.ConfigBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import thaumcraft.api.TileThaumcraft;

public class TileSlot extends TileThaumcraft
{
    public boolean hasKeystone;
    public boolean portalOpen;
    public int pocketID;
    public int which;
    public boolean xAligned;

    public TileSlot() {
        this.hasKeystone = false;
        this.portalOpen = false;
        this.pocketID = 0;
        this.which = 0;
        this.xAligned = false;
    }

    @Override
    public void writeCustomNBT(final NBTTagCompound nbttagcompound) {
        super.writeCustomNBT(nbttagcompound);
        nbttagcompound.setInteger("pocketPlaneID", this.pocketID);
        nbttagcompound.setInteger("which", this.which);
        nbttagcompound.setBoolean("hasKeystone", this.hasKeystone);
        nbttagcompound.setBoolean("portalOpen", this.portalOpen);
        nbttagcompound.setBoolean("aligned", this.xAligned);
    }

    @Override
    public void readCustomNBT(final NBTTagCompound nbttagcompound) {
        super.readCustomNBT(nbttagcompound);
        this.pocketID = nbttagcompound.getInteger("pocketPlaneID");
        this.which = nbttagcompound.getInteger("which");
        this.hasKeystone = nbttagcompound.getBoolean("hasKeystone");
        this.portalOpen = nbttagcompound.getBoolean("portalOpen");
        this.xAligned = nbttagcompound.getBoolean("aligned");
    }

    public void destroyPortal() {
        if (this.worldObj.isRemote) {
            return;
        }
        if (this.xAligned) {
            this.worldObj.setBlock(this.xCoord - 2, this.yCoord, this.zCoord, ConfigBlocks.blockMetalDevice, 3, 1);
            this.worldObj.setBlock(this.xCoord + 2, this.yCoord, this.zCoord, ConfigBlocks.blockMetalDevice, 3, 1);
            this.worldObj.setBlock(this.xCoord - 2, this.yCoord - 4, this.zCoord, ConfigBlocks.blockMetalDevice, 3, 1);
            this.worldObj.setBlock(this.xCoord + 2, this.yCoord - 4, this.zCoord, ConfigBlocks.blockMetalDevice, 3, 1);
            this.worldObj.setBlock(this.xCoord - 1, this.yCoord, this.zCoord, ConfigBlocks.blockCosmeticSolid, 11, 1);
            this.worldObj.setBlock(this.xCoord + 1, this.yCoord, this.zCoord, ConfigBlocks.blockCosmeticSolid, 11, 1);
            this.worldObj.setBlock(this.xCoord - 1, this.yCoord - 4, this.zCoord, ConfigBlocks.blockCosmeticSolid, 11, 1);
            this.worldObj.setBlock(this.xCoord, this.yCoord - 4, this.zCoord, ConfigBlocks.blockCosmeticSolid, 11, 1);
            this.worldObj.setBlock(this.xCoord + 1, this.yCoord - 4, this.zCoord, ConfigBlocks.blockCosmeticSolid, 11, 1);
            this.worldObj.setBlock(this.xCoord - 2, this.yCoord - 1, this.zCoord, ConfigBlocks.blockCosmeticSolid, 11, 1);
            this.worldObj.setBlock(this.xCoord - 2, this.yCoord - 2, this.zCoord, ConfigBlocks.blockCosmeticSolid, 11, 1);
            this.worldObj.setBlock(this.xCoord - 2, this.yCoord - 3, this.zCoord, ConfigBlocks.blockCosmeticSolid, 11, 1);
            this.worldObj.setBlock(this.xCoord + 2, this.yCoord - 1, this.zCoord, ConfigBlocks.blockCosmeticSolid, 11, 1);
            this.worldObj.setBlock(this.xCoord + 2, this.yCoord - 2, this.zCoord, ConfigBlocks.blockCosmeticSolid, 11, 1);
            this.worldObj.setBlock(this.xCoord + 2, this.yCoord - 3, this.zCoord, ConfigBlocks.blockCosmeticSolid, 11, 1);
            for (int x = -1; x <= 1; ++x) {
                for (int y = -1; y >= -3; --y) {
                    this.worldObj.setBlockToAir(this.xCoord + x, this.yCoord + y, this.zCoord);
                }
            }
            for (int x = -2; x <= 2; ++x) {
                for (int y = 0; y >= -4; --y) {
                    this.worldObj.markBlockForUpdate(this.xCoord + x, this.yCoord + y, this.zCoord);
                }
            }
        }
        else {
            this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord - 2, ConfigBlocks.blockMetalDevice, 3, 1);
            this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord + 2, ConfigBlocks.blockMetalDevice, 3, 1);
            this.worldObj.setBlock(this.xCoord, this.yCoord - 4, this.zCoord - 2, ConfigBlocks.blockMetalDevice, 3, 1);
            this.worldObj.setBlock(this.xCoord, this.yCoord - 4, this.zCoord + 2, ConfigBlocks.blockMetalDevice, 3, 1);
            this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord - 1, ConfigBlocks.blockCosmeticSolid, 11, 1);
            this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord + 1, ConfigBlocks.blockCosmeticSolid, 11, 1);
            this.worldObj.setBlock(this.xCoord, this.yCoord - 4, this.zCoord - 1, ConfigBlocks.blockCosmeticSolid, 11, 1);
            this.worldObj.setBlock(this.xCoord, this.yCoord - 4, this.zCoord, ConfigBlocks.blockCosmeticSolid, 11, 1);
            this.worldObj.setBlock(this.xCoord, this.yCoord - 4, this.zCoord + 1, ConfigBlocks.blockCosmeticSolid, 11, 1);
            this.worldObj.setBlock(this.xCoord, this.yCoord - 1, this.zCoord - 2, ConfigBlocks.blockCosmeticSolid, 11, 1);
            this.worldObj.setBlock(this.xCoord, this.yCoord - 2, this.zCoord - 2, ConfigBlocks.blockCosmeticSolid, 11, 1);
            this.worldObj.setBlock(this.xCoord, this.yCoord - 3, this.zCoord - 2, ConfigBlocks.blockCosmeticSolid, 11, 1);
            this.worldObj.setBlock(this.xCoord, this.yCoord - 1, this.zCoord + 2, ConfigBlocks.blockCosmeticSolid, 11, 1);
            this.worldObj.setBlock(this.xCoord, this.yCoord - 2, this.zCoord + 2, ConfigBlocks.blockCosmeticSolid, 11, 1);
            this.worldObj.setBlock(this.xCoord, this.yCoord - 3, this.zCoord + 2, ConfigBlocks.blockCosmeticSolid, 11, 1);
            for (int z = -1; z <= 1; ++z) {
                for (int y = -1; y >= -3; --y) {
                    this.worldObj.setBlockToAir(this.xCoord, this.yCoord + y, this.zCoord + z);
                }
            }
            for (int z = -2; z <= 2; ++z) {
                for (int y = 0; y >= -4; --y) {
                    this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord + y, this.zCoord + z);
                }
            }
        }
        PocketPlaneData.destroyPortal(this.pocketID, this.which);
        this.worldObj.playSoundEffect(this.xCoord + 0.5, this.yCoord + 0.5, this.zCoord + 0.5, "thaumcraft:craftfail", 1.0f, 1.0f);
        this.portalOpen = false;
        this.markDirty();
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
    }

    public void makePortal(final EntityPlayer player) {
        if (this.worldObj.isRemote) {
            return;
        }
        final int portalNum = PocketPlaneData.firstAvailablePortal(this.pocketID);
        final ItemWandCasting wand = (ItemWandCasting)player.getHeldItem().getItem();
        if (portalNum != 0 && wand.consumeAllVisCrafting(player.getHeldItem(), player, new AspectList().add(Aspect.WATER, 250).add(Aspect.EARTH, 250).add(Aspect.ORDER, 250).add(Aspect.FIRE, 250).add(Aspect.AIR, 250).add(Aspect.ENTROPY, 250), false)) {
            boolean madePortal = false;
            if (this.checkPortalX()) {
                this.xAligned = true;
                int md = 0;
                for (int x = -2; x <= 2; ++x) {
                    for (int y = 0; y >= -4; --y) {
                        if (!this.worldObj.isAirBlock(this.xCoord + x, this.yCoord + y, this.zCoord) && (x != 0 || y != 0)) {
                            this.worldObj.setBlock(this.xCoord + x, this.yCoord + y, this.zCoord, ThaumicHorizons.blockGateway, md, 3);
                            ++md;
                        }
                        else if (x != 0 || y != 0) {
                            this.worldObj.setBlock(this.xCoord + x, this.yCoord + y, this.zCoord, ThaumicHorizons.blockPortal, 0, 3);
                        }
                    }
                }
                madePortal = true;
            }
            else if (this.checkPortalZ()) {
                this.xAligned = false;
                int md = 0;
                for (int z = -2; z <= 2; ++z) {
                    for (int y = 0; y >= -4; --y) {
                        if (!this.worldObj.isAirBlock(this.xCoord, this.yCoord + y, this.zCoord + z) && (z != 0 || y != 0)) {
                            this.worldObj.setBlock(this.xCoord, this.yCoord + y, this.zCoord + z, ThaumicHorizons.blockGateway, md, 3);
                            ++md;
                        }
                        else if (z != 0 || y != 0) {
                            this.worldObj.setBlock(this.xCoord, this.yCoord + y, this.zCoord + z, ThaumicHorizons.blockPortal, 0, 3);
                        }
                    }
                }
                madePortal = true;
            }
            if (madePortal) {
                switch (portalNum) {
                    case 1: {
                        PocketPlaneData.planes.get(this.pocketID).portalA[0] = this.xCoord;
                        PocketPlaneData.planes.get(this.pocketID).portalA[1] = this.yCoord;
                        PocketPlaneData.planes.get(this.pocketID).portalA[2] = this.zCoord;
                        PocketPlaneData.planes.get(this.pocketID).portalA[3] = player.dimension;
                        break;
                    }
                    case 2: {
                        PocketPlaneData.planes.get(this.pocketID).portalB[0] = this.xCoord;
                        PocketPlaneData.planes.get(this.pocketID).portalB[1] = this.yCoord;
                        PocketPlaneData.planes.get(this.pocketID).portalB[2] = this.zCoord;
                        PocketPlaneData.planes.get(this.pocketID).portalB[3] = player.dimension;
                        break;
                    }
                    case 3: {
                        PocketPlaneData.planes.get(this.pocketID).portalC[0] = this.xCoord;
                        PocketPlaneData.planes.get(this.pocketID).portalC[1] = this.yCoord;
                        PocketPlaneData.planes.get(this.pocketID).portalC[2] = this.zCoord;
                        PocketPlaneData.planes.get(this.pocketID).portalC[3] = player.dimension;
                        break;
                    }
                    case 4: {
                        PocketPlaneData.planes.get(this.pocketID).portalD[0] = this.xCoord;
                        PocketPlaneData.planes.get(this.pocketID).portalD[1] = this.yCoord;
                        PocketPlaneData.planes.get(this.pocketID).portalD[2] = this.zCoord;
                        PocketPlaneData.planes.get(this.pocketID).portalD[3] = player.dimension;
                        break;
                    }
                }
                PocketPlaneData.makePortal(this.pocketID, portalNum, this.xCoord, this.yCoord, this.zCoord);
                this.which = portalNum;
                this.portalOpen = true;
                wand.consumeAllVisCrafting(player.getHeldItem(), player, new AspectList().add(Aspect.WATER, 250).add(Aspect.EARTH, 250).add(Aspect.ORDER, 250).add(Aspect.FIRE, 250).add(Aspect.AIR, 250).add(Aspect.ENTROPY, 250), true);
                this.worldObj.playSoundEffect(this.xCoord + 0.5, this.yCoord + 0.5, this.zCoord + 0.5, "thaumcraft:wand", 1.0f, 1.0f);
                this.markDirty();
                this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
            }
        }
    }

    boolean checkPortalX() {
        for (int x = -1; x <= 1; ++x) {
            for (int y = -1; y >= -3; --y) {
                if (!this.worldObj.isAirBlock(this.xCoord + x, this.yCoord + y, this.zCoord)) {
                    return false;
                }
            }
        }

        return checkBlocksX(this.xCoord, this.yCoord, this.zCoord);
    }

    boolean checkPortalZ() {
        for (int z = -1; z <= 1; ++z) {
            for (int y = -1; y >= -3; --y) {
                if (!this.worldObj.isAirBlock(this.xCoord, this.yCoord + y, this.zCoord + z)) {
                    return false;
                }
            }
        }

        return checkBlocksZ(this.xCoord, this.yCoord, this.zCoord);
    }

    boolean checkBlocksX(int x, int y, int z) {
        boolean isValid = true;

        isValid &= checkCornerBlock(x - 2, y, z);
        isValid &= checkCornerBlock(x + 2, y, z);
        isValid &= checkCornerBlock(x - 2, y - 4, z);
        isValid &= checkCornerBlock(x + 2, y - 4, z);

        isValid &= checkEdgeBlock(x - 1, y, z);
        isValid &= checkEdgeBlock(x + 1, y, z);
        isValid &= checkEdgeBlock(x - 2, y - 1, z);
        isValid &= checkEdgeBlock(x + 2, y - 1, z);
        isValid &= checkEdgeBlock(x - 2, y - 2, z);
        isValid &= checkEdgeBlock(x + 2, y - 2, z);
        isValid &= checkEdgeBlock(x - 2, y - 3, z);
        isValid &= checkEdgeBlock(x + 2, y - 3, z);
        isValid &= checkEdgeBlock(x - 1, y - 4, z);
        isValid &= checkEdgeBlock(x, y - 4, z);
        isValid &= checkEdgeBlock(x + 1, y - 4, z);

        return isValid;
    }

    boolean checkBlocksZ(int x, int y, int z) {
        boolean isValid = true;

        isValid &= checkCornerBlock(x, y, z - 2);
        isValid &= checkCornerBlock(x, y, z + 2);
        isValid &= checkCornerBlock(x, y - 4, z - 2);
        isValid &= checkCornerBlock(x, y - 4, z + 2);

        isValid &= checkEdgeBlock(x, y, z - 1);
        isValid &= checkEdgeBlock(x, y, z + 1);
        isValid &= checkEdgeBlock(x, y - 1, z - 2);
        isValid &= checkEdgeBlock(x, y - 1, z + 2);
        isValid &= checkEdgeBlock(x, y - 2, z - 2);
        isValid &= checkEdgeBlock(x, y - 2, z + 2);
        isValid &= checkEdgeBlock(x, y - 3, z - 2);
        isValid &= checkEdgeBlock(x, y - 3, z + 2);
        isValid &= checkEdgeBlock(x, y - 4, z - 1);
        isValid &= checkEdgeBlock(x, y - 4, z);
        isValid &= checkEdgeBlock(x, y - 4, z + 1);

        return isValid;
    }

    boolean checkCornerBlock(final int x, final int y, final int z) {
        return this.worldObj.getBlock(x, y, z) == ConfigBlocks.blockMetalDevice && this.worldObj.getBlockMetadata(x, y, z) == 3;
    }

    boolean checkEdgeBlock(final int x, final int y, final int z) {
        return this.worldObj.getBlock(x, y, z) == ConfigBlocks.blockCosmeticSolid && this.worldObj.getBlockMetadata(x, y, z) == 11;
    }

    public void insertKeystone(final int pocketNum) {
        this.hasKeystone = true;
        this.pocketID = pocketNum;
        this.markDirty();
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
    }

    public int removeKeystone() {
        this.hasKeystone = false;
        this.markDirty();
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        return this.pocketID;
    }
}
