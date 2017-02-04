// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.entities;

import java.util.HashMap;
import java.util.Map;

public enum EnumGolemTHType
{
    GRASS(10, 0, 0.38f, false, 2, 1, 75, 0, 750), 
    DIRT(10, 0, 0.38f, false, 1, 1, 75, 0, 500), 
    WOOD(20, 6, 0.35f, false, 2, 4, 75, 1, 750), 
    PLANT(10, 0, 0.4f, false, 2, 1, 75, 0, 750), 
    ROCK(30, 12, 0.32f, true, 2, 16, 100, 3, 1000), 
    METAL(35, 15, 0.31f, true, 2, 32, 125, 4, 1250), 
    CLOTH(10, 0, 0.4f, false, 2, 1, 75, 0, 750), 
    SAND(10, 0, 0.4f, false, 1, 1, 75, 0, 500), 
    REDSTONE(20, 9, 0.35f, true, 2, 8, 45, 2, 1000), 
    TNT(10, 0, 0.38f, false, 1, 1, 75, 0, 750), 
    ICE(20, 6, 0.35f, false, 2, 4, 75, 1, 750), 
    CACTUS(20, 6, 0.35f, false, 2, 4, 75, 1, 1000), 
    CLAY(25, 9, 0.33f, true, 1, 8, 100, 2, 750), 
    CAKE(20, 6, 0.35f, false, 2, 4, 25, 1, 750), 
    WEB(15, 6, 0.35f, false, 2, 4, 40, 1, 1000), 
    VOID(25, 15, 0.4f, true, 2, 8, 20, 3, 0);
    
    public final int health;
    public final int armor;
    public final float speed;
    public final boolean fireResist;
    public final int upgrades;
    public final int carry;
    public final int regenDelay;
    public final int strength;
    public final int visCost;
    private static Map<Integer, EnumGolemTHType> codeToTypeMapping;
    
    private EnumGolemTHType(final int health, final int armor, final float speed, final boolean fireResist, final int upgrades, final int carry, final int regenDelay, final int strength, final int special) {
        this.health = health;
        this.armor = armor;
        this.speed = speed;
        this.fireResist = fireResist;
        this.upgrades = upgrades;
        this.carry = carry;
        this.regenDelay = regenDelay;
        this.strength = strength;
        this.visCost = special;
    }
    
    public static EnumGolemTHType getType(final int i) {
        if (EnumGolemTHType.codeToTypeMapping == null) {
            initMapping();
        }
        return EnumGolemTHType.codeToTypeMapping.get(i);
    }
    
    private static void initMapping() {
        EnumGolemTHType.codeToTypeMapping = new HashMap<Integer, EnumGolemTHType>();
        for (final EnumGolemTHType s : values()) {
            EnumGolemTHType.codeToTypeMapping.put(s.ordinal(), s);
        }
    }
}
