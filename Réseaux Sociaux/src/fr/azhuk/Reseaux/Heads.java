package fr.azhuk.Reseaux;

import org.bukkit.inventory.ItemStack;

public enum Heads
{
    TWITTER("NWVkYzgxZWQ1YzBhZWJmM2ZiNTNhYjMyZmFkOTE0ODU1ZTE0Yzc3MWI5MDU0NWYzYjIxZjFiMDkzNTEzNDA1MyJ9fX0=","twitter"),
    INSTAGRAM("YWM4OGQ2MTYzZmFiZTdjNWU2MjQ1MGViMzdhMDc0ZTJlMmM4ODYxMWM5OTg1MzZkYmQ4NDI5ZmFhMDgxOTQ1MyJ9fX0=","instagram"),
    TWITCH("NDZiZTY1ZjQ0Y2QyMTAxNGM4Y2RkZDAxNThiZjc1MjI3YWRjYjFmZDE3OWY0YzFhY2QxNThjODg4NzFhMTNmIn19fQ==","twitch"),
    DISCORD("Nzg3M2MxMmJmZmI1MjUxYTBiODhkNWFlNzVjNzI0N2NiMzlhNzVmZjFhODFjYmU0YzhhMzliMzExZGRlZGEifX19","discord");

    private ItemStack item;
    private String idTag;
    private String prefix = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUv";
    private Heads(String texture, String id)
    {
        item = Main.createSkull(prefix + texture, id);
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