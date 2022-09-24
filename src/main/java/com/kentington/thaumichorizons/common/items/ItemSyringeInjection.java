//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.items;

import com.google.common.collect.HashMultimap;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.entities.EntityBlastPhial;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.util.List;
import java.util.Map;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemSyringeInjection extends ItemPotion {
    private IIcon field_94590_d;
    private IIcon field_94591_c;
    private IIcon field_94592_ct;

    public ItemSyringeInjection() {
        this.setHasSubtypes(true);
        this.setMaxStackSize(1);
        this.setCreativeTab(ThaumicHorizons.tabTH);
    }

    public int getMaxItemUseDuration(final ItemStack p_77626_1_) {
        return 8;
    }

    public EnumAction getItemUseAction(final ItemStack p_77661_1_) {
        return EnumAction.bow;
    }

    public String getItemStackDisplayName(final ItemStack p_77653_1_) {
        return StatCollector.translateToLocal("item.injection." + p_77653_1_.getItemDamage() + ".name");
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(final Item p_150895_1_, final CreativeTabs p_150895_2_, final List p_150895_3_) {}

    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister ir) {
        this.field_94590_d = ir.registerIcon("thaumichorizons:phialBlood");
        this.field_94591_c = ir.registerIcon("thaumichorizons:phialBlood");
        this.field_94592_ct = ir.registerIcon("thaumichorizons:phialBlood");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(final int p_77617_1_) {
        return isSplash(p_77617_1_) ? this.field_94591_c : this.field_94590_d;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamageForRenderPass(final int p_77618_1_, final int p_77618_2_) {
        return (p_77618_2_ == 0) ? this.field_94592_ct : super.getIconFromDamageForRenderPass(p_77618_1_, p_77618_2_);
    }

    @SideOnly(Side.CLIENT)
    public static IIcon func_94589_d(final String p_94589_0_) {
        return p_94589_0_.equals("bottle_drinkable")
                ? ((ItemSyringeInjection) ThaumicHorizons.itemSyringeInjection).field_94590_d
                : (p_94589_0_.equals("bottle_splash")
                        ? ((ItemSyringeInjection) ThaumicHorizons.itemSyringeInjection).field_94591_c
                        : (p_94589_0_.equals("overlay")
                                ? ((ItemSyringeInjection) ThaumicHorizons.itemSyringeInjection).field_94592_ct
                                : null));
    }

    public boolean isPhial(final int p_77831_0_) {
        return p_77831_0_ != 0;
    }

    public ItemStack onItemRightClick(
            final ItemStack p_77659_1_, final World p_77659_2_, final EntityPlayer p_77659_3_) {
        if (this.isPhial(p_77659_1_.getItemDamage())) {
            if (!p_77659_3_.capabilities.isCreativeMode) {
                --p_77659_1_.stackSize;
            }
            p_77659_2_.playSoundAtEntity(
                    (Entity) p_77659_3_,
                    "random.bow",
                    0.5f,
                    0.4f / (ItemSyringeInjection.itemRand.nextFloat() * 0.4f + 0.8f));
            if (!p_77659_2_.isRemote) {
                p_77659_2_.spawnEntityInWorld(
                        (Entity) new EntityBlastPhial(p_77659_2_, (EntityLivingBase) p_77659_3_, 0.5f, p_77659_1_));
            }
            return p_77659_1_;
        }
        p_77659_3_.setItemInUse(p_77659_1_, this.getMaxItemUseDuration(p_77659_1_));
        return p_77659_1_;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(
            final ItemStack p_77624_1_,
            final EntityPlayer p_77624_2_,
            final List p_77624_3_,
            final boolean p_77624_4_) {
        final List<PotionEffect> list1 = Items.potionitem.getEffects(p_77624_1_);
        final HashMultimap hashmultimap = HashMultimap.create();
        if (list1 != null && !list1.isEmpty()) {
            for (PotionEffect potioneffect : list1) {
                String s1 = StatCollector.translateToLocal(potioneffect.getEffectName())
                        .trim();
                final Potion potion = Potion.potionTypes[potioneffect.getPotionID()];
                final Map map = potion.func_111186_k();
                final List<Map.Entry> mapset = (List<Map.Entry>) map.entrySet();
                if (map != null && map.size() > 0) {
                    for (final Map.Entry entry : mapset) {
                        final AttributeModifier attributemodifier = (AttributeModifier) entry.getValue();
                        final AttributeModifier attributemodifier2 = new AttributeModifier(
                                attributemodifier.getName(),
                                potion.func_111183_a(potioneffect.getAmplifier(), attributemodifier),
                                attributemodifier.getOperation());
                        hashmultimap.put(entry.getKey(), (Object) attributemodifier2);
                    }
                }
                if (potioneffect.getAmplifier() > 0) {
                    s1 = s1 + " "
                            + StatCollector.translateToLocal("potion.potency." + potioneffect.getAmplifier())
                                    .trim();
                }
                if (potioneffect.getDuration() > 20) {
                    s1 = s1 + " (" + Potion.getDurationString(potioneffect) + ")";
                }
                if (potion.isBadEffect()) {
                    p_77624_3_.add(EnumChatFormatting.RED + s1);
                } else {
                    p_77624_3_.add(EnumChatFormatting.GRAY + s1);
                }
            }
        } else {
            final String s2 = StatCollector.translateToLocal("potion.empty").trim();
            p_77624_3_.add(EnumChatFormatting.GRAY + s2);
        }
        if (!hashmultimap.isEmpty()) {
            p_77624_3_.add("");
            p_77624_3_.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("potion.effects.whenDrank"));
            for (final Map.Entry entry2 : (List<Map.Entry>) hashmultimap.entries()) {
                final AttributeModifier attributemodifier3 = (AttributeModifier) entry2.getValue();
                final double d0 = attributemodifier3.getAmount();
                double d2;
                if (attributemodifier3.getOperation() != 1 && attributemodifier3.getOperation() != 2) {
                    d2 = attributemodifier3.getAmount();
                } else {
                    d2 = attributemodifier3.getAmount() * 100.0;
                }
                if (d0 > 0.0) {
                    p_77624_3_.add(EnumChatFormatting.BLUE
                            + StatCollector.translateToLocalFormatted(
                                    "attribute.modifier.plus." + attributemodifier3.getOperation(), new Object[] {
                                        ItemStack.field_111284_a.format(d2),
                                        StatCollector.translateToLocal("attribute.name." + entry2.getKey())
                                    }));
                } else {
                    if (d0 >= 0.0) {
                        continue;
                    }
                    d2 *= -1.0;
                    p_77624_3_.add(EnumChatFormatting.RED
                            + StatCollector.translateToLocalFormatted(
                                    "attribute.modifier.take." + attributemodifier3.getOperation(), new Object[] {
                                        ItemStack.field_111284_a.format(d2),
                                        StatCollector.translateToLocal("attribute.name." + entry2.getKey())
                                    }));
                }
            }
        }
    }

    public boolean hitEntity(final ItemStack is, final EntityLivingBase target, final EntityLivingBase hitter) {
        if (!target.worldObj.isRemote) {
            final List<PotionEffect> list = this.getEffects(is);
            if (list != null) {
                for (final PotionEffect potioneffect : list) {
                    target.addPotionEffect(new PotionEffect(potioneffect));
                }
            }
        }
        --is.stackSize;
        return super.hitEntity(is, target, hitter);
    }

    public int getColorFromItemStack(final ItemStack stack, final int p_82790_2_) {
        if (stack.getItem() == ThaumicHorizons.itemSyringeInjection) {
            if (stack.hasTagCompound()) {
                return stack.getTagCompound().getInteger("color");
            }
        } else if (stack.getItem() != ThaumicHorizons.itemSyringeEmpty) {
            return Color.RED.getRGB();
        }
        return 16777215;
    }

    public ItemStack onEaten(final ItemStack p_77654_1_, final World p_77654_2_, final EntityPlayer p_77654_3_) {
        if (!p_77654_3_.capabilities.isCreativeMode) {
            --p_77654_1_.stackSize;
        }
        if (!p_77654_2_.isRemote) {
            final List<PotionEffect> list = this.getEffects(p_77654_1_);
            if (list != null) {
                for (final PotionEffect potioneffect : list) {
                    p_77654_3_.addPotionEffect(new PotionEffect(potioneffect));
                }
            }
        }
        if (p_77654_1_.stackSize <= 0) {
            return null;
        }
        return p_77654_1_;
    }
}
