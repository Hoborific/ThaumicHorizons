//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.tiles;

import java.awt.Color;
import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import com.kentington.thaumichorizons.common.ThaumicHorizons;

import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.common.config.Config;

public class TileBloodInfuser extends TileThaumcraft implements IAspectContainer, IEssentiaTransport, ISidedInventory {

    public AspectList aspectsSelected;
    public AspectList aspectsAcquired;
    Aspect currentlySucking;
    public int mode;
    public ItemStack syringe;
    public ItemStack[] output;
    private final HashMap<Aspect, HashMap<Integer, Integer>> effectWeights;
    private final HashMap<Integer, Float> duration;
    private Color color;

    public TileBloodInfuser() {
        this.aspectsSelected = new AspectList();
        this.aspectsAcquired = new AspectList();
        this.currentlySucking = null;
        this.mode = 0;
        this.syringe = null;
        this.output = new ItemStack[9];
        this.effectWeights = new HashMap<Aspect, HashMap<Integer, Integer>>();
        this.duration = new HashMap<Integer, Float>();
        final int speed = Potion.moveSpeed.id;
        final int slow = Potion.moveSlowdown.id;
        final int haste = Potion.digSpeed.id;
        final int fatigue = Potion.digSlowdown.id;
        final int strength = Potion.damageBoost.id;
        final int health = Potion.heal.id;
        final int harm = Potion.harm.id;
        final int jump = Potion.jump.id;
        final int nausea = 9;
        final int regen = Potion.regeneration.id;
        final int resist = Potion.resistance.id;
        final int fireres = Potion.fireResistance.id;
        final int water = Potion.waterBreathing.id;
        final int invis = Potion.invisibility.id;
        final int blind = Potion.blindness.id;
        final int night = Potion.nightVision.id;
        final int hunger = Potion.hunger.id;
        final int weak = Potion.weakness.id;
        final int poison = Potion.poison.id;
        final int wither = Potion.wither.id;
        final int hboost = 21;
        final int satur = 23;
        final int taint = Config.potionTaintPoisonID;
        final int visBoost = Config.potionVisExhaustID;
        final int visRegen = ThaumicHorizons.potionVisRegenID;
        final int vacuum = ThaumicHorizons.potionVacuumID;
        final int shock = ThaumicHorizons.potionShockID;
        final int synth = ThaumicHorizons.potionSynthesisID;
        this.duration.put(speed, 1.2f);
        this.duration.put(slow, 0.8f);
        this.duration.put(haste, 1.2f);
        this.duration.put(fatigue, 0.8f);
        this.duration.put(strength, 1.2f);
        this.duration.put(jump, 1.2f);
        this.duration.put(nausea, 0.2f);
        this.duration.put(regen, 0.6f);
        this.duration.put(resist, 1.2f);
        this.duration.put(fireres, 1.2f);
        this.duration.put(water, 1.2f);
        this.duration.put(invis, 1.2f);
        this.duration.put(blind, 0.2f);
        this.duration.put(night, 1.2f);
        this.duration.put(hunger, 0.4f);
        this.duration.put(weak, 0.8f);
        this.duration.put(poison, 0.6f);
        this.duration.put(wither, 0.4f);
        this.duration.put(hboost, 1.2f);
        this.duration.put(satur, 0.4f);
        this.duration.put(taint, 0.6f);
        this.duration.put(visBoost, 1.2f);
        this.duration.put(visRegen, 0.6f);
        this.duration.put(vacuum, 1.2f);
        this.duration.put(shock, 0.8f);
        this.duration.put(synth, 1.5f);
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(speed, 3);
        map.put(jump, 4);
        map.put(water, 3);
        this.effectWeights.put(Aspect.AIR, map);
        map = new HashMap<Integer, Integer>();
        map.put(slow, 3);
        map.put(haste, 4);
        map.put(strength, 1);
        map.put(resist, 2);
        this.effectWeights.put(Aspect.EARTH, map);
        map = new HashMap<Integer, Integer>();
        map.put(speed, 1);
        map.put(strength, 2);
        map.put(fireres, 6);
        map.put(night, 1);
        this.effectWeights.put(Aspect.FIRE, map);
        map = new HashMap<Integer, Integer>();
        map.put(speed, 2);
        map.put(haste, 2);
        map.put(regen, 1);
        map.put(fireres, 2);
        map.put(water, 3);
        this.effectWeights.put(Aspect.WATER, map);
        map = new HashMap<Integer, Integer>();
        map.put(slow, 2);
        map.put(fatigue, 3);
        map.put(resist, 3);
        map.put(hboost, 2);
        this.effectWeights.put(Aspect.ORDER, map);
        map = new HashMap<Integer, Integer>();
        map.put(harm, 1);
        map.put(nausea, 1);
        map.put(hunger, 1);
        map.put(weak, 2);
        map.put(poison, 2);
        map.put(wither, 3);
        this.effectWeights.put(Aspect.ENTROPY, map);
        map = new HashMap<Integer, Integer>();
        map.put(invis, 4);
        map.put(hunger, 2);
        map.put(vacuum, 4);
        this.effectWeights.put(Aspect.VOID, map);
        map = new HashMap<Integer, Integer>();
        map.put(blind, 2);
        map.put(night, 8);
        this.effectWeights.put(Aspect.LIGHT, map);
        map = new HashMap<Integer, Integer>();
        map.put(speed, 1);
        map.put(jump, 2);
        map.put(blind, 1);
        map.put(shock, 8);
        this.effectWeights.put(Aspect.WEATHER, map);
        map = new HashMap<Integer, Integer>();
        map.put(speed, 5);
        map.put(jump, 5);
        this.effectWeights.put(Aspect.MOTION, map);
        map = new HashMap<Integer, Integer>();
        map.put(slow, 2);
        map.put(fatigue, 2);
        map.put(resist, 2);
        map.put(invis, 2);
        map.put(weak, 2);
        this.effectWeights.put(Aspect.COLD, map);
        map = new HashMap<Integer, Integer>();
        map.put(fatigue, 2);
        map.put(strength, 2);
        map.put(invis, 6);
        this.effectWeights.put(Aspect.CRYSTAL, map);
        map = new HashMap<Integer, Integer>();
        map.put(health, 2);
        map.put(regen, 6);
        map.put(hboost, 2);
        this.effectWeights.put(Aspect.LIFE, map);
        map = new HashMap<Integer, Integer>();
        map.put(fatigue, 1);
        map.put(nausea, 1);
        map.put(hunger, 1);
        map.put(poison, 6);
        map.put(weak, 1);
        this.effectWeights.put(Aspect.POISON, map);
        map = new HashMap<Integer, Integer>();
        map.put(speed, 2);
        map.put(haste, 2);
        map.put(strength, 1);
        map.put(jump, 1);
        map.put(visRegen, 1);
        map.put(shock, 3);
        this.effectWeights.put(Aspect.ENERGY, map);
        map = new HashMap<Integer, Integer>();
        map.put(speed, 2);
        map.put(haste, 2);
        map.put(jump, 1);
        map.put(weak, 2);
        map.put(vacuum, 3);
        this.effectWeights.put(Aspect.EXCHANGE, map);
        map = new HashMap<Integer, Integer>();
        map.put(slow, 2);
        map.put(haste, 2);
        map.put(strength, 2);
        map.put(resist, 4);
        this.effectWeights.put(Aspect.METAL, map);
        map = new HashMap<Integer, Integer>();
        map.put(harm, 6);
        map.put(nausea, 1);
        map.put(hunger, 1);
        map.put(weak, 1);
        map.put(wither, 1);
        this.effectWeights.put(Aspect.DEATH, map);
        map = new HashMap<Integer, Integer>();
        map.put(speed, 2);
        map.put(jump, 8);
        this.effectWeights.put(Aspect.FLIGHT, map);
        map = new HashMap<Integer, Integer>();
        map.put(invis, 4);
        map.put(blind, 5);
        map.put(night, 1);
        this.effectWeights.put(Aspect.DARKNESS, map);
        map = new HashMap<Integer, Integer>();
        map.put(health, 2);
        map.put(regen, 1);
        map.put(invis, 3);
        map.put(night, 2);
        map.put(hboost, 1);
        map.put(visRegen, 1);
        this.effectWeights.put(Aspect.SOUL, map);
        map = new HashMap<Integer, Integer>();
        map.put(health, 6);
        map.put(regen, 2);
        map.put(hboost, 2);
        this.effectWeights.put(Aspect.HEAL, map);
        map = new HashMap<Integer, Integer>();
        map.put(speed, 8);
        map.put(jump, 2);
        this.effectWeights.put(Aspect.TRAVEL, map);
        map = new HashMap<Integer, Integer>();
        map.put(invis, 2);
        map.put(blind, 2);
        map.put(night, 2);
        map.put(visBoost, 2);
        map.put(visRegen, 2);
        this.effectWeights.put(Aspect.ELDRITCH, map);
        map = new HashMap<Integer, Integer>();
        map.put(visBoost, 5);
        map.put(visRegen, 5);
        this.effectWeights.put(Aspect.MAGIC, map);
        map = new HashMap<Integer, Integer>();
        map.put(visRegen, 10);
        this.effectWeights.put(Aspect.AURA, map);
        map = new HashMap<Integer, Integer>();
        map.put(visBoost, 2);
        map.put(wither, 2);
        map.put(taint, 6);
        this.effectWeights.put(Aspect.TAINT, map);
        map = new HashMap<Integer, Integer>();
        map.put(slow, 4);
        map.put(fatigue, 2);
        map.put(nausea, 2);
        map.put(poison, 2);
        this.effectWeights.put(Aspect.SLIME, map);
        map = new HashMap<Integer, Integer>();
        map.put(satur, 2);
        map.put(synth, 8);
        this.effectWeights.put(Aspect.PLANT, map);
        map = new HashMap<Integer, Integer>();
        map.put(slow, 2);
        map.put(resist, 2);
        map.put(synth, 6);
        this.effectWeights.put(Aspect.TREE, map);
        map = new HashMap<Integer, Integer>();
        map.put(speed, 1);
        map.put(strength, 5);
        map.put(regen, 1);
        map.put(water, 1);
        map.put(night, 1);
        map.put(hboost, 1);
        this.effectWeights.put(Aspect.BEAST, map);
        map = new HashMap<Integer, Integer>();
        map.put(regen, 2);
        map.put(hboost, 2);
        map.put(satur, 6);
        this.effectWeights.put(Aspect.FLESH, map);
        map = new HashMap<Integer, Integer>();
        map.put(hunger, 2);
        map.put(wither, 2);
        map.put(harm, 6);
        this.effectWeights.put(Aspect.UNDEAD, map);
        map = new HashMap<Integer, Integer>();
        map.put(night, 2);
        map.put(visBoost, 3);
        map.put(visRegen, 3);
        map.put(vacuum, 2);
        this.effectWeights.put(Aspect.MIND, map);
        map = new HashMap<Integer, Integer>();
        map.put(invis, 4);
        map.put(night, 6);
        this.effectWeights.put(Aspect.SENSES, map);
        map = new HashMap<Integer, Integer>();
        map.put(health, 3);
        map.put(regen, 3);
        map.put(hboost, 4);
        this.effectWeights.put(Aspect.MAN, map);
        map = new HashMap<Integer, Integer>();
        map.put(satur, 6);
        map.put(synth, 4);
        this.effectWeights.put(Aspect.CROP, map);
        map = new HashMap<Integer, Integer>();
        map.put(haste, 10);
        this.effectWeights.put(Aspect.MINE, map);
        map = new HashMap<Integer, Integer>();
        map.put(speed, 1);
        map.put(haste, 8);
        map.put(resist, 1);
        this.effectWeights.put(Aspect.TOOL, map);
        map = new HashMap<Integer, Integer>();
        map.put(haste, 2);
        map.put(health, 1);
        map.put(satur, 5);
        map.put(synth, 2);
        this.effectWeights.put(Aspect.HARVEST, map);
        map = new HashMap<Integer, Integer>();
        map.put(haste, 2);
        map.put(strength, 8);
        this.effectWeights.put(Aspect.WEAPON, map);
        map = new HashMap<Integer, Integer>();
        map.put(resist, 8);
        map.put(fireres, 2);
        this.effectWeights.put(Aspect.ARMOR, map);
        map = new HashMap<Integer, Integer>();
        map.put(weak, 1);
        map.put(satur, 8);
        map.put(vacuum, 1);
        this.effectWeights.put(Aspect.HUNGER, map);
        map = new HashMap<Integer, Integer>();
        map.put(satur, 2);
        map.put(vacuum, 8);
        this.effectWeights.put(Aspect.GREED, map);
        map = new HashMap<Integer, Integer>();
        map.put(fatigue, 2);
        map.put(health, 2);
        map.put(resist, 2);
        map.put(hboost, 4);
        this.effectWeights.put(Aspect.CRAFT, map);
        map = new HashMap<Integer, Integer>();
        map.put(fatigue, 2);
        map.put(resist, 4);
        map.put(blind, 2);
        map.put(weak, 2);
        this.effectWeights.put(Aspect.CLOTH, map);
        map = new HashMap<Integer, Integer>();
        map.put(speed, 2);
        map.put(haste, 4);
        map.put(strength, 4);
        this.effectWeights.put(Aspect.MECHANISM, map);
        map = new HashMap<Integer, Integer>();
        map.put(slow, 8);
        map.put(fatigue, 2);
        this.effectWeights.put(Aspect.TRAP, map);
    }

    public void updateEntity() {
        super.updateEntity();
        this.currentlySucking = null;
        if (this.mode > 0 && this.syringe != null && this.syringe.stackSize > 0 && this.emptyOutputSlot()) {
            for (final Aspect asp : this.aspectsSelected.getAspects()) {
                if (this.aspectsAcquired.getAmount(asp) < this.aspectsSelected.getAmount(asp)) {
                    this.currentlySucking = asp;
                    break;
                }
            }
            if (this.currentlySucking == null && this.aspectsAcquired != null
                    && ((this.aspectsAcquired.size() > 0 && this.aspectsAcquired.getAspects()[0] != null)
                            || (this.aspectsAcquired.size() > 1 && this.aspectsAcquired.getAspects()[1] != null))) {
                final ItemStack theInjection = new ItemStack(ThaumicHorizons.itemSyringeInjection);
                theInjection.setItemDamage(this.syringe.getItemDamage());
                this.decrStackSize(0, 1);
                final NBTTagCompound tag = new NBTTagCompound();
                tag.setTag("CustomPotionEffects", (NBTBase) this.getCurrentEffects());
                tag.setInteger("color", this.color.getRGB());
                theInjection.setTagCompound(tag);
                for (int i = 0; i < 9; ++i) {
                    if (this.output[i] == null) {
                        this.output[i] = theInjection;
                        break;
                    }
                }
                this.aspectsAcquired = new AspectList();
                this.markDirty();
                if (this.mode == 1) {
                    this.mode = 0;
                }
            } else {
                this.tryDrawEssentia();
            }
        }
    }

    public NBTTagList getCurrentEffects() {
        final NBTTagList effectList = new NBTTagList();
        if (this.aspectsSelected == null) {
            return effectList;
        }
        final HashMap<Integer, Integer> effects = new HashMap<Integer, Integer>();
        int totalEssentia = 0;
        int green = 0;
        int red = 0;
        int blue = 0;
        for (final Aspect asp : this.aspectsSelected.getAspects()) {
            if (this.effectWeights.get(asp) != null) {
                for (final Integer in : this.effectWeights.get(asp).keySet()) {
                    if (effects.get(in) != null) {
                        effects.put(
                                in,
                                effects.get(in)
                                        + this.aspectsSelected.getAmount(asp) * this.effectWeights.get(asp).get(in));
                    } else {
                        effects.put(in, this.aspectsSelected.getAmount(asp) * this.effectWeights.get(asp).get(in));
                    }
                }
                totalEssentia += this.aspectsSelected.getAmount(asp);
                final Color col = new Color(asp.getColor());
                red += col.getRed() * this.aspectsSelected.getAmount(asp);
                blue += col.getBlue() * this.aspectsSelected.getAmount(asp);
                green += col.getGreen() * this.aspectsSelected.getAmount(asp);
            }
        }
        if (totalEssentia > 0) {
            red /= totalEssentia;
            green /= totalEssentia;
            blue /= totalEssentia;
        }
        for (int i = 0; i < (totalEssentia + 1) / 2; ++i) {
            Integer largestWeight = 0;
            Integer largestEffect = 0;
            for (final Integer key : effects.keySet()) {
                if (effects.get(key) > largestWeight) {
                    largestWeight = effects.get(key);
                    largestEffect = key;
                }
            }
            final NBTTagCompound potTag = new NBTTagCompound();
            potTag.setByte("Id", (byte) (int) largestEffect);
            potTag.setByte("Amplifier", (byte) (largestWeight / 30));
            if (largestEffect != Potion.harm.id && largestEffect != Potion.heal.id) {
                potTag.setInteger("Duration", 100 * largestWeight);
            } else {
                potTag.setInteger("Duration", 1);
            }
            potTag.setBoolean("Ambient", false);
            effectList.appendTag((NBTBase) potTag);
            effects.remove(largestEffect);
        }
        this.color = new Color(red, green, blue);
        return effectList;
    }

    public HashMap<Integer, Integer> getEffects(final Aspect asp) {
        return this.effectWeights.get(asp);
    }

    public void setEssentiaSelected(final AspectList as) {
        this.aspectsSelected = as.copy();
    }

    public boolean emptyOutputSlot() {
        for (int i = 0; i < 9; ++i) {
            if (this.output[i] == null) {
                return true;
            }
        }
        return false;
    }

    void tryDrawEssentia() {
        TileEntity te = null;
        IEssentiaTransport ic = null;
        for (final ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            if (dir != ForgeDirection.UP) {
                te = ThaumcraftApiHelper.getConnectableTile(this.worldObj, this.xCoord, this.yCoord, this.zCoord, dir);
                if (te != null) {
                    ic = (IEssentiaTransport) te;
                    if (ic.getEssentiaAmount(dir.getOpposite()) > 0
                            && ic.getSuctionAmount(dir.getOpposite()) < this.getSuctionAmount(null)
                            && this.getSuctionAmount(null) >= ic.getMinimumSuction()) {
                        final int ess = ic.takeEssentia(this.currentlySucking, 1, dir.getOpposite());
                        if (ess > 0) {
                            this.addToContainer(this.currentlySucking, ess);
                            return;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void writeCustomNBT(final NBTTagCompound nbttagcompound) {
        super.writeCustomNBT(nbttagcompound);
        nbttagcompound.setInteger("mode", this.mode);
        if (this.currentlySucking != null) {
            nbttagcompound.setString("sucking", this.currentlySucking.getTag());
        } else {
            nbttagcompound.setString("sucking", "");
        }
        NBTTagList tlist = new NBTTagList();
        nbttagcompound.setTag("AspectsSelected", (NBTBase) tlist);
        for (final Aspect aspect : this.aspectsSelected.getAspects()) {
            if (aspect != null) {
                final NBTTagCompound f = new NBTTagCompound();
                f.setString("key", aspect.getTag());
                f.setInteger("amount", this.aspectsSelected.getAmount(aspect));
                tlist.appendTag((NBTBase) f);
            }
        }
        tlist = new NBTTagList();
        nbttagcompound.setTag("AspectsAcquired", (NBTBase) tlist);
        for (final Aspect aspect : this.aspectsAcquired.getAspects()) {
            if (aspect != null) {
                final NBTTagCompound f = new NBTTagCompound();
                f.setString("key", aspect.getTag());
                f.setInteger("amount", this.aspectsAcquired.getAmount(aspect));
                tlist.appendTag((NBTBase) f);
            }
        }
        final NBTTagList nbttaglist = new NBTTagList();
        NBTTagCompound nbttagcompound2 = new NBTTagCompound();
        if (this.syringe != null) {
            this.syringe.writeToNBT(nbttagcompound2);
        }
        nbttaglist.appendTag((NBTBase) nbttagcompound2);
        for (int i = 0; i < 9; ++i) {
            nbttagcompound2 = new NBTTagCompound();
            if (this.output[i] != null) {
                this.output[i].writeToNBT(nbttagcompound2);
            }
            nbttaglist.appendTag((NBTBase) nbttagcompound2);
        }
        nbttagcompound.setTag("Items", (NBTBase) nbttaglist);
    }

    @Override
    public void readCustomNBT(final NBTTagCompound nbttagcompound) {
        super.readCustomNBT(nbttagcompound);
        this.mode = nbttagcompound.getInteger("mode");
        this.currentlySucking = Aspect.getAspect(nbttagcompound.getString("sucking"));
        AspectList al = new AspectList();
        NBTTagList tlist = nbttagcompound.getTagList("AspectsSelected", 10);
        for (int j = 0; j < tlist.tagCount(); ++j) {
            final NBTTagCompound rs = tlist.getCompoundTagAt(j);
            if (rs.hasKey("key")) {
                al.add(Aspect.getAspect(rs.getString("key")), rs.getInteger("amount"));
            }
        }
        this.aspectsSelected = al.copy();
        al = new AspectList();
        tlist = nbttagcompound.getTagList("AspectsAcquired", 10);
        for (int j = 0; j < tlist.tagCount(); ++j) {
            final NBTTagCompound rs = tlist.getCompoundTagAt(j);
            if (rs.hasKey("key")) {
                al.add(Aspect.getAspect(rs.getString("key")), rs.getInteger("amount"));
            }
        }
        this.aspectsAcquired = al.copy();
        final NBTTagList nbttaglist = nbttagcompound.getTagList("Items", 10);
        NBTTagCompound nbttagcompound2 = nbttaglist.getCompoundTagAt(0);
        this.syringe = ItemStack.loadItemStackFromNBT(nbttagcompound2);
        for (int i = 0; i < 9; ++i) {
            nbttagcompound2 = nbttaglist.getCompoundTagAt(i + 1);
            this.output[i] = ItemStack.loadItemStackFromNBT(nbttagcompound2);
        }
    }

    @Override
    public boolean isConnectable(final ForgeDirection face) {
        return face != ForgeDirection.UP;
    }

    @Override
    public boolean canInputFrom(final ForgeDirection face) {
        return face != ForgeDirection.UP;
    }

    @Override
    public boolean canOutputTo(final ForgeDirection face) {
        return false;
    }

    @Override
    public void setSuction(final Aspect aspect, final int amount) {}

    @Override
    public Aspect getSuctionType(final ForgeDirection face) {
        if (face == ForgeDirection.UP) {
            return null;
        }
        return this.currentlySucking;
    }

    @Override
    public int getSuctionAmount(final ForgeDirection face) {
        if (face == ForgeDirection.UP) {
            return 0;
        }
        return (this.currentlySucking != null) ? 128 : 0;
    }

    @Override
    public int takeEssentia(final Aspect aspect, final int amount, final ForgeDirection face) {
        return 0;
    }

    @Override
    public int addEssentia(final Aspect aspect, final int amount, final ForgeDirection face) {
        return this.canInputFrom(face) ? (amount - this.addToContainer(aspect, amount)) : 0;
    }

    @Override
    public Aspect getEssentiaType(final ForgeDirection face) {
        return null;
    }

    @Override
    public int getEssentiaAmount(final ForgeDirection face) {
        return 0;
    }

    @Override
    public int getMinimumSuction() {
        return 0;
    }

    @Override
    public boolean renderExtendedTube() {
        return false;
    }

    @Override
    public AspectList getAspects() {
        if (this.aspectsAcquired.getAspects().length > 0 && this.aspectsAcquired.getAspects()[0] != null) {
            return this.aspectsAcquired;
        }
        return null;
    }

    @Override
    public void setAspects(final AspectList aspects) {}

    @Override
    public boolean doesContainerAccept(final Aspect tag) {
        return tag.getTag().equals(this.currentlySucking.getTag());
    }

    @Override
    public int addToContainer(final Aspect tag, final int amount) {
        this.aspectsAcquired.add(tag, amount);
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        this.markDirty();
        return 0;
    }

    @Override
    public boolean takeFromContainer(final Aspect tag, final int amount) {
        return false;
    }

    @Override
    public boolean takeFromContainer(final AspectList ot) {
        return false;
    }

    @Override
    public boolean doesContainerContainAmount(final Aspect tag, final int amount) {
        return this.aspectsAcquired.getAmount(tag) >= amount;
    }

    @Override
    public boolean doesContainerContain(final AspectList ot) {
        return false;
    }

    @Override
    public int containerContains(final Aspect tag) {
        return this.aspectsAcquired.getAmount(tag);
    }

    public int getSizeInventory() {
        return 10;
    }

    public ItemStack getStackInSlot(final int slot) {
        if (slot == 0) {
            return this.syringe;
        }
        if (slot <= 9) {
            return this.output[slot - 1];
        }
        return null;
    }

    public ItemStack decrStackSize(final int p_70298_1_, final int p_70298_2_) {
        ItemStack theStack;
        if (p_70298_1_ == 0) {
            theStack = this.syringe;
        } else {
            theStack = this.output[p_70298_1_ - 1];
        }
        if (theStack == null) {
            return null;
        }
        if (theStack.stackSize <= p_70298_2_) {
            ItemStack outStack;
            if (p_70298_1_ == 0) {
                outStack = this.syringe.copy();
                this.syringe = null;
            } else {
                outStack = this.output[p_70298_1_ - 1].copy();
                this.output[p_70298_1_ - 1] = null;
            }
            return outStack;
        }
        ItemStack outStack = theStack.splitStack(p_70298_2_);
        if (theStack.stackSize == 0) {
            if (p_70298_1_ == 0) {
                this.syringe = null;
            } else {
                this.output[p_70298_1_ - 1] = null;
            }
        }
        return outStack;
    }

    public ItemStack getStackInSlotOnClosing(final int p_70304_1_) {
        return null;
    }

    public void setInventorySlotContents(final int p_70299_1_, final ItemStack p_70299_2_) {
        if (p_70299_1_ == 0) {
            this.syringe = p_70299_2_;
        } else if (p_70299_1_ < 10) {
            this.output[p_70299_1_ - 1] = p_70299_2_;
        }
    }

    public String getInventoryName() {
        return "container.bloodInfuser";
    }

    public boolean hasCustomInventoryName() {
        return false;
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean isUseableByPlayer(final EntityPlayer p_70300_1_) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this
                && p_70300_1_.getDistanceSq(this.xCoord + 0.5, this.yCoord + 0.5, this.zCoord + 0.5) <= 64.0;
    }

    public void openInventory() {}

    public void closeInventory() {}

    public boolean isItemValidForSlot(final int p_94041_1_, final ItemStack p_94041_2_) {
        return p_94041_1_ == 0 && p_94041_2_.isItemEqual(new ItemStack(ThaumicHorizons.itemSyringeHuman));
    }

    public int[] getAccessibleSlotsFromSide(final int p_94128_1_) {
        return new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    }

    public boolean canInsertItem(final int p_102007_1_, final ItemStack p_102007_2_, final int p_102007_3_) {
        return p_102007_1_ == 0 && p_102007_2_.isItemEqual(new ItemStack(ThaumicHorizons.itemSyringeHuman));
    }

    public boolean canExtractItem(final int p_102008_1_, final ItemStack p_102008_2_, final int p_102008_3_) {
        return p_102008_1_ > 0 && p_102008_2_ != null;
    }

    public boolean hasBlood() {
        if (this.syringe != null && this.syringe.stackSize > 0) {
            return true;
        }
        for (int i = 0; i < 9; ++i) {
            if (this.output[i] != null && this.output[i].stackSize > 0) {
                return true;
            }
        }
        return false;
    }
}
