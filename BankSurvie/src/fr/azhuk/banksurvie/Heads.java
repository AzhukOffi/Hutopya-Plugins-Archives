package fr.azhuk.banksurvie;

import org.bukkit.inventory.ItemStack;

public enum Heads {
    REDUN("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGQyNDU0ZTRjNjdiMzIzZDViZTk1M2I1YjNkNTQxNzRhYTI3MTQ2MDM3NGVlMjg0MTBjNWFlYWUyYzExZjUifX19","redun"),
    REDDIX("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjU5ODdmNDNmZjU3ZDRkYWJhYTJkMmNlYjlmMDFmYzZlZTQ2ZGIxNjJhNWUxMmRmZGJiNTdmZDQ2ODEzMmI4In19fQ==","reddix"),
    GREENUN("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmQ2NWNlODNmMWFhNWI2ZTg0ZjliMjMzNTk1MTQwZDViNmJlY2ViNjJiNmQwYzY3ZDFhMWQ4MzYyNWZmZCJ9fX0=","greenun"),
    GREENDIX("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDFjMWQ0ODZhMmQyNDJkNTdiZDRhY2E0Y2NhOTgxNDViNjEyYWIyYTcwOGQ1OGVlNDVkMDMzNmE5OGZjMzEifX19","greendix"),
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