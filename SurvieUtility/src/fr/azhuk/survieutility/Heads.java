package fr.azhuk.survieutility;

import org.bukkit.inventory.ItemStack;

public enum Heads {
    MONITOR("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTk4YzFiYzY4ZDEzYjg2NTY0MzQ0MzlmZDYyOTYyODc4ZjlhNjRiZmZjOGFlNDA5MmFmZGI1MGQ0ZTc4OTM3In19fQ==","monitor");
    private ItemStack item;
    private String idTag;
    private Heads(String texture, String id)
    {
        item = Main.createSkull(texture, id);
        idTag = id;
    }


    public ItemStack getItemStack()
    {
        return item;
    }

    public String getName()
    {
        return idTag;
    }


}