// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.entities;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import com.kentington.thaumichorizons.common.items.ItemFocusAnimation;
import net.minecraft.world.IBlockAccess;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.entity.effect.EntityLightningBolt;

public class EntityLightningBoltFinite extends EntityLightningBolt
{
    public int boltLength;
    public boolean animate;
    
    public EntityLightningBoltFinite(final World p_i1703_1_, final double p_i1703_2_, final double p_i1703_4_, final double p_i1703_6_, final int boltLength, final boolean animate) {
        super(p_i1703_1_, p_i1703_2_, p_i1703_4_, p_i1703_6_);
        this.boltLength = boltLength;
        this.animate = animate;
    }
    
    public EntityLightningBoltFinite(final World w) {
        super(w, 0.0, 0.0, 0.0);
    }
    
    protected void readEntityFromNBT(final NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        this.boltLength = tag.getInteger("length");
        this.animate = tag.getBoolean("animate");
    }
    
    protected void writeEntityToNBT(final NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setBoolean("animate", this.animate);
        tag.setInteger("length", this.boltLength);
    }
    
    public void onUpdate() {
        super.onUpdate();
        ThaumicHorizons.proxy.lightningBolt(this.worldObj, this.posX, this.posY, this.posZ, this.boltLength);
        if (!this.animate) {
            return;
        }
        final int p_77648_4_ = (int)Math.floor(this.posX);
        final int p_77648_5_ = (int)this.posY;
        final int p_77648_6_ = (int)Math.floor(this.posZ);
        final Block blocky = this.worldObj.getBlock(p_77648_4_, p_77648_5_, p_77648_6_);
        final int md = this.worldObj.getBlockMetadata(p_77648_4_, p_77648_5_, p_77648_6_);
        if (this.worldObj.isRemote) {
            return;
        }
        if (!blocky.hasTileEntity(md) && !blocky.isAir((IBlockAccess)this.worldObj, p_77648_4_, p_77648_5_, p_77648_6_) && (blocky.isOpaqueCube() || ItemFocusAnimation.isWhitelisted(blocky, md)) && blocky.getBlockHardness(this.worldObj, p_77648_4_, p_77648_5_, p_77648_6_) != -1.0f) {
            if (!this.worldObj.isRemote && ThaumicHorizons.blockCloud.getBlockHardness(this.worldObj, p_77648_4_, p_77648_5_, p_77648_6_) > 0.0f) {
                final EntityGolemTH golem = new EntityGolemTH(this.worldObj);
                golem.loadGolem(p_77648_4_ + 0.5, p_77648_5_, p_77648_6_ + 0.5, blocky, md, 1200, false, false, false);
                this.worldObj.setBlockToAir(p_77648_4_, p_77648_5_, p_77648_6_);
                this.worldObj.playSoundEffect(p_77648_4_ + 0.5, p_77648_5_ + 0.5, p_77648_6_ + 0.5, "thaumcraft:wand", 1.0f, 1.0f);
                golem.setHomeArea((int)golem.posX, (int)golem.posY, (int)golem.posZ, 32);
                golem.setOwner("");
                golem.berserk = true;
                golem.extinguish();
                golem.heal(100.0f);
                this.worldObj.spawnEntityInWorld((Entity)golem);
                this.worldObj.setEntityState((Entity)golem, (byte)7);
            }
            else {
                Minecraft.getMinecraft().effectRenderer.addBlockDestroyEffects(p_77648_4_, p_77648_5_, p_77648_6_, blocky, md);
            }
        }
    }
}
