//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.client.lib;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.blocks.BlockChocolate;

public class GolemTHTexture extends AbstractTexture {

    IIcon[] icons;
    IResourceManager manager;
    BufferedImage myImage;
    int special;

    public GolemTHTexture(final IIcon[] icons, final int isGrass) {
        this.icons = icons;
        this.special = isGrass;
        this.manager = Minecraft.getMinecraft().getResourceManager();
    }

    public void loadTexture(final IResourceManager p_110551_1_) throws IOException {
        this.deleteGlTexture();
        this.myImage = new BufferedImage(128, 128, 2);
        final TextureMap allBlocks = Minecraft.getMinecraft().getTextureMapBlocks();
        BufferedImage top = this.getIconImage(this.icons[1]);
        if (this.special == 1) {
            top = this.getIconImage(((BlockChocolate) ThaumicHorizons.blockChocolate).coloredGrass);
        }
        if (this.special == 2) {
            top = top.getSubimage(1, 1, 14, 14);
        }
        BufferedImage tmp = this.resize(top, 8, 8);
        this.myImage.setRGB(8, 0, 8, 8, tmp.getRGB(0, 0, 8, 8, null, 0, 8), 0, 8);
        tmp = this.resize(top, 16, 11);
        this.myImage.setRGB(11, 40, 16, 11, tmp.getRGB(0, 0, 16, 11, null, 0, 16), 0, 16);
        tmp = this.resize(top, 9, 6);
        this.myImage.setRGB(6, 70, 9, 6, tmp.getRGB(0, 0, 9, 6, null, 0, 9), 0, 9);
        tmp = this.resize(top, 6, 5);
        this.myImage.setRGB(42, 0, 6, 5, tmp.getRGB(0, 0, 6, 5, null, 0, 6), 0, 6);
        tmp = this.resize(top, 4, 6);
        this.myImage.setRGB(66, 21, 4, 6, tmp.getRGB(0, 0, 4, 6, null, 0, 4), 0, 4);
        tmp = this.resize(top, 16, 16);
        this.myImage.setRGB(112, 48, 16, 16, tmp.getRGB(0, 0, 16, 16, null, 0, 16), 0, 16);
        BufferedImage bottom = this.getIconImage(this.icons[0]);
        if (this.special == 2) {
            bottom = bottom.getSubimage(1, 1, 14, 14);
        }
        tmp = this.resize(bottom, 8, 8);
        this.myImage.setRGB(16, 0, 8, 8, tmp.getRGB(0, 0, 8, 8, null, 0, 8), 0, 8);
        tmp = this.resize(bottom, 16, 11);
        this.myImage.setRGB(27, 40, 16, 11, tmp.getRGB(0, 0, 16, 11, null, 0, 16), 0, 16);
        tmp = this.resize(bottom, 9, 6);
        this.myImage.setRGB(15, 70, 9, 6, tmp.getRGB(0, 0, 9, 6, null, 0, 9), 0, 9);
        tmp = this.resize(bottom, 6, 5);
        this.myImage.setRGB(48, 0, 6, 5, tmp.getRGB(0, 0, 6, 5, null, 0, 6), 0, 6);
        tmp = this.resize(bottom, 4, 6);
        this.myImage.setRGB(70, 21, 4, 6, tmp.getRGB(0, 0, 4, 6, null, 0, 4), 0, 4);
        BufferedImage east = this.getIconImage(this.icons[2]);
        if (this.special == 2) {
            east = east.getSubimage(1, 8, 14, 8);
        }
        tmp = this.resize(east, 8, 9);
        this.myImage.setRGB(0, 8, 8, 9, tmp.getRGB(0, 0, 8, 9, null, 0, 8), 0, 8);
        tmp = this.resize(east, 11, 12);
        this.myImage.setRGB(0, 51, 11, 12, tmp.getRGB(0, 0, 11, 12, null, 0, 11), 0, 11);
        tmp = this.resize(east, 6, 5);
        this.myImage.setRGB(0, 76, 6, 5, tmp.getRGB(0, 0, 6, 5, null, 0, 6), 0, 6);
        tmp = this.resize(east, 5, 16);
        this.myImage.setRGB(37, 5, 5, 16, tmp.getRGB(0, 0, 5, 16, null, 0, 16), 0, 16);
        tmp = this.resize(east, 6, 25);
        this.myImage.setRGB(60, 27, 6, 25, tmp.getRGB(0, 0, 6, 25, null, 0, 6), 0, 6);
        tmp = this.resize(east, 16, 16);
        this.myImage.setRGB(88, 48, 16, 16, tmp.getRGB(0, 0, 16, 16, null, 0, 16), 0, 16);
        BufferedImage west = this.getIconImage(this.icons[3]);
        if (this.special == 2) {
            west = west.getSubimage(1, 8, 14, 8);
        }
        tmp = this.resize(west, 8, 9);
        this.myImage.setRGB(16, 8, 8, 9, tmp.getRGB(0, 0, 8, 9, null, 0, 8), 0, 8);
        tmp = this.resize(west, 11, 12);
        this.myImage.setRGB(27, 51, 11, 12, tmp.getRGB(0, 0, 11, 12, null, 0, 11), 0, 11);
        tmp = this.resize(west, 6, 5);
        this.myImage.setRGB(17, 76, 6, 5, tmp.getRGB(0, 0, 6, 5, null, 0, 6), 0, 6);
        tmp = this.resize(west, 5, 16);
        this.myImage.setRGB(48, 5, 5, 16, tmp.getRGB(0, 0, 5, 16, null, 0, 5), 0, 5);
        tmp = this.resize(west, 6, 25);
        this.myImage.setRGB(70, 27, 6, 25, tmp.getRGB(0, 0, 6, 25, null, 0, 6), 0, 6);
        BufferedImage north = this.getIconImage(this.icons[4]);
        if (this.special == 2) {
            north = north.getSubimage(1, 8, 14, 8);
        }
        tmp = this.resize(north, 8, 9);
        this.myImage.setRGB(8, 8, 8, 9, tmp.getRGB(0, 0, 8, 9, null, 0, 8), 0, 8);
        tmp = this.resize(north, 17, 12);
        this.myImage.setRGB(11, 51, 17, 12, tmp.getRGB(0, 0, 17, 12, null, 0, 17), 0, 17);
        tmp = this.resize(north, 9, 5);
        this.myImage.setRGB(6, 76, 9, 5, tmp.getRGB(0, 0, 9, 5, null, 0, 9), 0, 9);
        tmp = this.resize(north, 6, 16);
        this.myImage.setRGB(42, 5, 6, 16, tmp.getRGB(0, 0, 6, 16, null, 0, 6), 0, 6);
        tmp = this.resize(north, 4, 25);
        this.myImage.setRGB(66, 27, 4, 25, tmp.getRGB(0, 0, 4, 25, null, 0, 4), 0, 4);
        tmp = this.resize(north, 16, 16);
        this.myImage.setRGB(88, 64, 16, 16, tmp.getRGB(0, 0, 16, 16, null, 0, 16), 0, 16);
        BufferedImage south = this.getIconImage(this.icons[5]);
        if (this.special == 2) {
            south = south.getSubimage(1, 8, 14, 8);
        }
        tmp = this.resize(south, 8, 9);
        this.myImage.setRGB(24, 8, 8, 9, tmp.getRGB(0, 0, 8, 9, null, 0, 8), 0, 8);
        tmp = this.resize(south, 17, 12);
        this.myImage.setRGB(37, 51, 17, 12, tmp.getRGB(0, 0, 17, 12, null, 0, 17), 0, 17);
        tmp = this.resize(south, 9, 5);
        this.myImage.setRGB(21, 76, 9, 5, tmp.getRGB(0, 0, 9, 5, null, 0, 9), 0, 9);
        tmp = this.resize(south, 6, 16);
        this.myImage.setRGB(53, 5, 6, 16, tmp.getRGB(0, 0, 6, 16, null, 0, 6), 0, 6);
        tmp = this.resize(south, 4, 25);
        this.myImage.setRGB(76, 27, 4, 25, tmp.getRGB(0, 0, 4, 25, null, 0, 4), 0, 4);
        final int[] eyeOverlay = { -16777216, -16316917, -16185587, -15593446 };
        this.myImage.setRGB(9, 12, 2, 2, eyeOverlay, 0, 2);
        this.myImage.setRGB(13, 12, 2, 2, eyeOverlay, 0, 2);
        TextureUtil.uploadTextureImageAllocate(this.getGlTextureId(), this.myImage, false, false);
    }

    public BufferedImage getIconImage(final IIcon icon) throws IOException {
        final ResourceLocation resourceLocation = this.getResourceLocation(icon.getIconName());
        final IResource resource = this.manager.getResource(resourceLocation);
        final InputStream in = resource.getInputStream();
        return ImageIO.read(in);
    }

    public ResourceLocation getResourceLocation(final String blockTexture) {
        String domain = "minecraft";
        String path = blockTexture;
        final int domainSeparator = blockTexture.indexOf(58);
        if (domainSeparator >= 0) {
            path = blockTexture.substring(domainSeparator + 1);
            if (domainSeparator > 1) {
                domain = blockTexture.substring(0, domainSeparator);
            }
        }
        final String resourcePath = "textures/blocks/" + path + ".png";
        return new ResourceLocation(domain.toLowerCase(), resourcePath);
    }

    public BufferedImage resize(final BufferedImage img, final int newW, final int newH) {
        final Image tmp = img.getScaledInstance(newW, newH, 4);
        final BufferedImage dimg = new BufferedImage(newW, newH, 2);
        final Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return dimg;
    }
}
