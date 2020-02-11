package CustomItemler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


import EventDosyasi.EventsClass;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import AnaDizinDosyasi.BaturPlugin;
import EventDosyasi.ArmorEquipEvent;

import static EventDosyasi.EventsClass.getzarAtiklik;
import static EventDosyasi.EventsClass.getzarDayaniklilik;


public class CustomItems implements Listener
{

    public static int SilahAltZar(String silahisim)
    {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Silahlar/"+silahisim+".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        int className = pConfig.getInt("SilahAltZar");
        return className;
    }

    public static int SilahUstZar(String silahisim)
    {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Silahlar/"+silahisim+".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        int className = pConfig.getInt("SilahUstZar");
        return className;
    }
    public static String SilahTuru(String silahisim)
    {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Silahlar/"+silahisim+".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        String className = pConfig.getString("SilahTuru");

        return className;
    }
    public static String SilahZari(String silahisim)
    {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Silahlar/"+silahisim+".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        String className = pConfig.getString("SilahZari");

        return className;
    }
    public static int VatandasNo(String silahisim)
    {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Silahlar/"+silahisim+".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        int className = pConfig.getInt("VatandasNo");

        return className;
    }
    public static String AktifPasif(String silahisim)
    {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Silahlar/"+silahisim+".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        String className = pConfig.getString("Durum");

        return className;
    }

    public static String SilahAd(String silahisim)
    {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder().getAbsolutePath(), "/Silahlar/"+silahisim+".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        String className = pConfig.getString("SilahAD");

        return className;
    }

    public static String SilahLore1(String SilahIsim)
    {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder().getAbsolutePath(), "/Silahlar/"+SilahIsim+".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        String className = pConfig.getString("SilahLore1");

        return className;
    }

    public static String SilahLore2(String SilahIsim)
    {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder().getAbsolutePath(), "/Silahlar/"+SilahIsim+".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        String className = pConfig.getString("SilahLore2");

        return className;
    }

    public static String SilahLore3(String SilahIsim)
    {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder().getAbsolutePath(), "/Silahlar/"+SilahIsim+".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        String className = pConfig.getString("SilahLore3");

        return className;
    }

    public static String SilahLore4(String SilahIsim)
    {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder().getAbsolutePath(), "/Silahlar/"+SilahIsim+".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        String className = pConfig.getString("SilahLore4");

        return className;
    }

    public static String SilahLore5(String SilahIsim)
    {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder().getAbsolutePath(), "/Silahlar/"+SilahIsim+".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        String className = pConfig.getString("SilahLore5");

        return className;
    }

    public static int SilahDur1(String SilahIsim)
    {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder().getAbsolutePath(), "/Silahlar/"+SilahIsim+".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        int className = pConfig.getInt("SilahDurability1");

        return className;
    }

    public static int SilahDur2(String SilahIsim)
    {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder().getAbsolutePath(), "/Silahlar/"+SilahIsim+".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        int className = pConfig.getInt("SilahDurability2");

        return className;
    }
    public static int SilahDur3(String SilahIsim)
    {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder().getAbsolutePath(), "/Silahlar/"+SilahIsim+".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        int className = pConfig.getInt("SilahDurability3");

        return className;
    }
    public static int SilahDur4(String SilahIsim)
    {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder().getAbsolutePath(), "/Silahlar/"+SilahIsim+".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        int className = pConfig.getInt("SilahDurability4");

        return className;
    }
    public static int SilahDur5(String SilahIsim)
    {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder().getAbsolutePath(), "/Silahlar/"+SilahIsim+".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        int className = pConfig.getInt("SilahDurability5");

        return className;
    }

    public static String SilahRenk1(String SilahIsim)
    {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder().getAbsolutePath(), "/Silahlar/"+SilahIsim+".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        String className = pConfig.getString("SilahRenk1");

        return className;
    }

    public static String SilahRenk2(String SilahIsim)
    {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder().getAbsolutePath(), "/Silahlar/"+SilahIsim+".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        String className = pConfig.getString("SilahRenk2");

        return className;
    }

    public static String SilahRenk3(String SilahIsim)
    {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder().getAbsolutePath(), "/Silahlar/"+SilahIsim+".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        String className = pConfig.getString("SilahRenk3");

        return className;
    }

    public static String SilahRenk4(String SilahIsim)
    {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder().getAbsolutePath(), "/Silahlar/"+SilahIsim+".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        String className = pConfig.getString("SilahRenk4");

        return className;
    }

    public static String SilahRenk5(String SilahIsim)
    {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder().getAbsolutePath(), "/Silahlar/"+SilahIsim+".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        String className = pConfig.getString("SilahRenk5");

        return className;
    }

    public static String ACHELM(String SilahIsim)
    {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder().getAbsolutePath(), "/Silahlar/"+SilahIsim+".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        String className = pConfig.getString("ACHelm");

        return className;
    }

    public static String ACCHEST(String SilahIsim)
    {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder().getAbsolutePath(), "/Silahlar/"+SilahIsim+".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        String className = pConfig.getString("ACChest");

        return className;
    }

    public static String ACLEGG(String SilahIsim)
    {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder().getAbsolutePath(), "/Silahlar/"+SilahIsim+".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        String className = pConfig.getString("ACLegg");

        return className;
    }

    public static String ACBOOTS(String SilahIsim)
    {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder().getAbsolutePath(), "/Silahlar/"+SilahIsim+".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        String className = pConfig.getString("ACBoot");

        return className;
    }

    @EventHandler
    public void equip(ArmorEquipEvent event){

        Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"[Zirh Giyildi] "+ChatColor.BLUE+event.getPlayer().getName());
        System.out.println("ArmorEquipEvent - " + event.getMethod());
        System.out.println("Type: " + event.getType());
        System.out.println("New: " + (event.getNewArmorPiece() != null ? event.getNewArmorPiece().getType() : "null"));
        System.out.println("Old: " + (event.getOldArmorPiece() != null ? event.getOldArmorPiece().getType() : "null"));

        try
        {
            if(event.getOldArmorPiece().getType().equals(Material.LEATHER_BOOTS) || event.getOldArmorPiece().getType().equals(Material.IRON_BOOTS) || event.getOldArmorPiece().getType().equals(Material.GOLDEN_BOOTS))
            {
                //if(event.getOldArmorPiece().getItemMeta().getDisplayName().contains(ChatColor.stripColor(SilahAd(event.get))))
                event.getPlayer().sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.YELLOW+"Botunu cikardin.");

            }
            if(event.getOldArmorPiece().getType().equals(Material.LEATHER_CHESTPLATE) || event.getOldArmorPiece().getType().equals(Material.IRON_CHESTPLATE) || event.getOldArmorPiece().getType().equals(Material.GOLDEN_CHESTPLATE))
            {
                event.getPlayer().sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.YELLOW+"Goguslugunu cikardin.");
                System.out.println("--"+SilahAd(ChatColor.stripColor(event.getOldArmorPiece().getItemMeta().getDisplayName()))+"--");
                if(event.getOldArmorPiece().getItemMeta().getDisplayName().contains(ChatColor.stripColor(SilahAd(ChatColor.stripColor(event.getOldArmorPiece().getItemMeta().getDisplayName())))))
                {


                    int bonuscev = EventsClass.BonusCeviklik(EventsClass.GetIrk(event.getPlayer().getName()));
                    int bonusceviklikac = EventsClass.BonusCeviklik(event.getPlayer().getName());
                    int tempac = ((Integer.valueOf(getzarAtiklik(event.getPlayer().getName())) + bonuscev - 10) / 2);
                    tempac = tempac + 10;
                    EventsClass.setAC(event.getPlayer().getName(),tempac);


                 //   int Temp = EventDosyasi.EventsClass.getAC(event.getPlayer().getName());
                    //	int TempAc = Integer.valueOf(ACCHEST((SilahAd(ChatColor.stripColor(event.getOldArmorPiece().getItemMeta().getDisplayName())))));
              //      int TempAc = EventDosyasi.EventsClass.getAC(event.get)
                 //   EventDosyasi.EventsClass.setAC(event.getPlayer().getName(), 10);
                }
            }
            if(event.getOldArmorPiece().getType().equals(Material.LEATHER_HELMET) || event.getOldArmorPiece().getType().equals(Material.IRON_HELMET) || event.getOldArmorPiece().getType().equals(Material.GOLDEN_HELMET))
            {
                event.getPlayer().sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.YELLOW+"Kaskini cikardin.");

            }
            if(event.getOldArmorPiece().getType().equals(Material.LEATHER_LEGGINGS) || event.getOldArmorPiece().getType().equals(Material.IRON_LEGGINGS) || event.getOldArmorPiece().getType().equals(Material.GOLDEN_LEGGINGS))
            {
                event.getPlayer().sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.YELLOW+"Pantalonunu cikardin.");

            }

            if(event.getNewArmorPiece().getType().equals(Material.LEATHER_BOOTS) || event.getNewArmorPiece().getType().equals(Material.IRON_BOOTS) || event.getNewArmorPiece().getType().equals(Material.GOLDEN_BOOTS))
            {
                event.getPlayer().sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.YELLOW+"Botunu giydin.");

            }
            if(event.getNewArmorPiece().getType().equals(Material.LEATHER_CHESTPLATE) || event.getNewArmorPiece().getType().equals(Material.IRON_CHESTPLATE) || event.getNewArmorPiece().getType().equals(Material.GOLDEN_CHESTPLATE))
            {
                event.getPlayer().sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.YELLOW+"Goguslugunu giydin.");
                System.out.println("--"+SilahAd(ChatColor.stripColor(event.getNewArmorPiece().getItemMeta().getDisplayName()))+"--");
                if(event.getNewArmorPiece().getItemMeta().getDisplayName().contains(ChatColor.stripColor(SilahAd(ChatColor.stripColor(event.getNewArmorPiece().getItemMeta().getDisplayName())))))
                {
                    int Temp = EventDosyasi.EventsClass.getAC(event.getPlayer().getName());
                    int TempAc = Integer.parseInt(ACCHEST((SilahAd(ChatColor.stripColor(event.getNewArmorPiece().getItemMeta().getDisplayName())))));
                    EventDosyasi.EventsClass.setAC(event.getPlayer().getName(), Integer.valueOf(10+TempAc));
                }
            }
            if(event.getNewArmorPiece().getType().equals(Material.LEATHER_HELMET) || event.getNewArmorPiece().getType().equals(Material.IRON_HELMET) || event.getNewArmorPiece().getType().equals(Material.GOLDEN_HELMET))
            {
                event.getPlayer().sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.YELLOW+"Kaskini giydin.");


            }
            if(event.getNewArmorPiece().getType().equals(Material.LEATHER_LEGGINGS) || event.getNewArmorPiece().getType().equals(Material.IRON_LEGGINGS) || event.getNewArmorPiece().getType().equals(Material.GOLDEN_LEGGINGS))
            {
                event.getPlayer().sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.YELLOW+"Pantalonunu giydin.");

            }
        }
        catch(Exception ex)
        {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"[Hata]: "+ChatColor.YELLOW+"Zirh sisteminde hata olustu.");
        }





    }


    public static void EmeraldSword()
    {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);

        // The meta of the diamond sword where we can change the name, and properties of the item.
        ItemMeta meta = item.getItemMeta();

        // We will initialise the next variable after changing the properties of the sword

        // This sets the name of the item.
        meta.setDisplayName("Emerald Sword");
        meta.setLocalizedName(ChatColor.GREEN + "kisa");

        // Set the meta of the sword to the edited meta.
        item.setItemMeta(meta);

        // Add the custom enchantment to make the emerald sword special
        // In this case, we're adding the permission that modifies the damage value on level 5
        // Level 5 is represented by the second parameter. You can change this to anything compatible with a sword
        item.addEnchantment(Enchantment.DAMAGE_ALL, 5);


        // create a NamespacedKey for your recipe
        NamespacedKey key = new NamespacedKey(BaturPlugin.plugin, "Emerald_Sword");

        // Create our custom recipe variable
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        // Here we will set the places. E and S can represent anything, and the letters can be anything. Beware; this is case sensitive.
        recipe.shape(" E ", " E ", " S ");

        // Set what the letters represent.
        // E = Emerald, S = Stick
        recipe.setIngredient('E', Material.EMERALD);
        recipe.setIngredient('S', Material.STICK);

        // Finally, add the recipe to the bukkit recipes
        Bukkit.addRecipe(recipe);

    }
    public static void Sifac()
    {
        try
        {
            ItemStack item = new ItemStack(Material.PAPER,1);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("Bandaj");
            meta.setLocalizedName("Bandaj");
            item.setItemMeta(meta);
            NamespacedKey key = new NamespacedKey(BaturPlugin.plugin, "Bandaj");
            ShapedRecipe recipe = new ShapedRecipe(key, item);
            recipe.shape("AAA");

            recipe.setIngredient('A',Material.WHITE_WOOL);
            Bukkit.addRecipe(recipe);
        }
        catch(Exception ex)
        {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"Sifaci craftinda hata!");
        }

    }
    public static void Kalkanlar()
    {
        ItemStack item = new ItemStack(Material.SHIELD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Ucgen Tahta Kalkan");
        meta.setLocalizedName("Ucgen Tahta Kalkan");
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(BaturPlugin.plugin, "Ucgen_Tahta_Kalkan");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("ABA", "ACA", "ACA");

        recipe.setIngredient('A',Material.DARK_OAK_PLANKS);
        recipe.setIngredient('C', Material.IRON_INGOT);
        Bukkit.addRecipe(recipe);

        ItemStack item2 = new ItemStack(Material.SHIELD);
        ItemMeta meta2 = item2.getItemMeta();
        meta2.setDisplayName("Ucgen Tahta Kalkan");
        meta2.setLocalizedName("Ucgen Tahta Kalkan");
        item2.setItemMeta(meta2);
        NamespacedKey key2 = new NamespacedKey(BaturPlugin.plugin, "Ucgen_Tahta_Kalkan2");
        ShapedRecipe recipe2 = new ShapedRecipe(key2, item2);
        recipe2.shape("ABA", "ACA", "ACA");

        recipe2.setIngredient('A',Material.SPRUCE_PLANKS);
        recipe2.setIngredient('C', Material.IRON_INGOT);
        Bukkit.addRecipe(recipe2);

        ItemStack item3 = new ItemStack(Material.SHIELD);
        ItemMeta meta3 = item3.getItemMeta();
        meta3.setDisplayName(ChatColor.GREEN + "Demir Kalkan");
        meta3.setLocalizedName(ChatColor.GREEN + "Demir Kalkan");
        item3.setItemMeta(meta3);
        NamespacedKey key3 = new NamespacedKey(BaturPlugin.plugin, "Demir_Kalkan");
        ShapedRecipe recipe3 = new ShapedRecipe(key3, item3);
        recipe3.shape("ACA", "ACA", "ADA");

        recipe3.setIngredient('A',Material.DARK_OAK_PLANKS);
        recipe3.setIngredient('C', Material.IRON_INGOT);
        recipe3.setIngredient('D', Material.IRON_BLOCK);
        Bukkit.addRecipe(recipe3);

        ItemStack item4 = new ItemStack(Material.SHIELD);
        ItemMeta meta4 = item4.getItemMeta();
        meta4.setDisplayName(ChatColor.GREEN + "Demir Kalkan");
        meta4.setLocalizedName(ChatColor.GREEN + "Demir Kalkan");
        item4.setItemMeta(meta4);
        NamespacedKey key4 = new NamespacedKey(BaturPlugin.plugin, "Demir_Kalkan2");
        ShapedRecipe recipe4 = new ShapedRecipe(key4, item4);
        recipe4.shape("ACA", "ACA", "ADA");

        recipe4.setIngredient('A',Material.SPRUCE_PLANKS);
        recipe4.setIngredient('C', Material.IRON_INGOT);
        recipe4.setIngredient('D', Material.IRON_BLOCK);
        Bukkit.addRecipe(recipe4);

        ItemStack item5 = new ItemStack(Material.SHIELD);
        ItemMeta meta5 = item5.getItemMeta();
        meta5.setDisplayName(ChatColor.GREEN + "Yuvarlak Tahta Kalkan");
        meta5.setLocalizedName(ChatColor.GREEN + "Yuvarlak Tahta Kalkan");
        item5.setItemMeta(meta5);
        NamespacedKey key5 = new NamespacedKey(BaturPlugin.plugin, "Yuvarlak_Tahta_Kalkan");
        ShapedRecipe recipe5 = new ShapedRecipe(key5, item5);
        recipe5.shape("ABA", "ABA", "AAA");

        recipe5.setIngredient('A',Material.DARK_OAK_PLANKS);
        recipe5.setIngredient('B', Material.IRON_INGOT);
        Bukkit.addRecipe(recipe5);

        ItemStack item6 = new ItemStack(Material.SHIELD);
        ItemMeta meta6 = item6.getItemMeta();
        meta6.setDisplayName(ChatColor.GREEN + "Yuvarlak Tahta Kalkan");
        meta6.setLocalizedName(ChatColor.GREEN + "Yuvarlak Tahta Kalkan");
        item6.setItemMeta(meta6);
        NamespacedKey key6 = new NamespacedKey(BaturPlugin.plugin, "Yuvarlak_Tahta_Kalkan2");
        ShapedRecipe recipe6 = new ShapedRecipe(key6, item6);
        recipe6.shape("ABA", "ABA", "AAA");

        recipe6.setIngredient('A',Material.SPRUCE_PLANKS);
        recipe6.setIngredient('B', Material.IRON_INGOT);
        Bukkit.addRecipe(recipe6);


        ItemStack item7 = new ItemStack(Material.SHIELD);
        ItemMeta meta7 = item7.getItemMeta();
        meta7.setDisplayName(ChatColor.GREEN + "Kemik Kalkan");
        meta7.setLocalizedName(ChatColor.GREEN + "Kemik Kalkan");
        item7.setItemMeta(meta7);
        NamespacedKey key7 = new NamespacedKey(BaturPlugin.plugin, "Kemik_Kalkan");
        ShapedRecipe recipe7 = new ShapedRecipe(key7, item7);
        recipe7.shape("ADA", "ABA", "AAA");

        recipe7.setIngredient('A',Material.BONE_BLOCK);
        recipe7.setIngredient('B', Material.BONE);
        Bukkit.addRecipe(recipe7);

        ItemStack item8 = new ItemStack(Material.SHIELD);
        ItemMeta meta8 = item8.getItemMeta();
        meta8.setDisplayName(ChatColor.GREEN + "Kemik Kalkan");
        meta8.setLocalizedName(ChatColor.GREEN + "Kemik Kalkan");
        item8.setItemMeta(meta8);
        NamespacedKey key8 = new NamespacedKey(BaturPlugin.plugin, "Kemik_Kalkan2");
        ShapedRecipe recipe8 = new ShapedRecipe(key8, item8);
        recipe8.shape("ADA", "ABA", "AAA");

        recipe8.setIngredient('A',Material.BONE_BLOCK);
        recipe8.setIngredient('B', Material.BONE);
        Bukkit.addRecipe(recipe8);



        ItemStack item9 = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta9 = item9.getItemMeta();
        meta9.setDisplayName(ChatColor.GREEN + "Kuzeyli Teberi");
        meta9.setLocalizedName(ChatColor.GREEN + "Kuzeyli Teberi");
        item9.setItemMeta(meta9);
        NamespacedKey key9 = new NamespacedKey(BaturPlugin.plugin, "Kuzeyli_Teberi");
        ShapedRecipe recipe9 = new ShapedRecipe(key9, item9);
        recipe9.shape("AAD", "ABD", "DCD");

        recipe9.setIngredient('A',Material.IRON_INGOT);
        recipe9.setIngredient('B', Material.STICK);
        recipe9.setIngredient('C', Material.SPRUCE_FENCE);
        Bukkit.addRecipe(recipe9);

        ItemStack item10 = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta10 = item10.getItemMeta();
        meta10.setDisplayName(ChatColor.GREEN + "Kuzeyli Teberi");
        meta10.setLocalizedName(ChatColor.GREEN + "Kuzeyli Teberi");
        item10.setItemMeta(meta10);
        NamespacedKey key10 = new NamespacedKey(BaturPlugin.plugin, "Kuzeyli_Teberi2");
        ShapedRecipe recipe10 = new ShapedRecipe(key10, item10);
        recipe10.shape("AAD", "ABD", "DCD");

        recipe10.setIngredient('A',Material.IRON_INGOT);
        recipe10.setIngredient('B', Material.STICK);
        recipe10.setIngredient('C', Material.DARK_OAK_FENCE);
        Bukkit.addRecipe(recipe10);


        ItemStack item11 = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta11 = item11.getItemMeta();
        meta11.setDisplayName(ChatColor.GREEN + "Pic Kilici");
        meta11.setLocalizedName(ChatColor.GREEN + "Pic Kilic");
        item11.setItemMeta(meta11);
        NamespacedKey key11 = new NamespacedKey(BaturPlugin.plugin, "Pic_Kilici");
        ShapedRecipe recipe11 = new ShapedRecipe(key11, item11);
        recipe11.shape("ADD", "DAB", "DBA");

        recipe11.setIngredient('A',Material.IRON_INGOT);
        recipe11.setIngredient('B', Material.STICK);
        Bukkit.addRecipe(recipe11);

        ItemStack item12 = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta12 = item12.getItemMeta();
        meta12.setDisplayName(ChatColor.GREEN + "Hancer");
        meta12.setLocalizedName(ChatColor.GREEN + "Hancer");
        item12.setItemMeta(meta12);
        NamespacedKey key12 = new NamespacedKey(BaturPlugin.plugin, "Hancer");
        ShapedRecipe recipe12 = new ShapedRecipe(key12, item12);
        recipe12.shape("ADD", "DBD", "DDB");

        recipe12.setIngredient('A',Material.STICK);
        recipe12.setIngredient('B', Material.IRON_INGOT);
        Bukkit.addRecipe(recipe12);



    }

    public static void Arbalet()
    {
        ItemStack item1 = new ItemStack(Material.BOW);
        ItemMeta meta1 = item1.getItemMeta();
        meta1.setDisplayName(ChatColor.GREEN + "Tek Elli Arbalet");
        meta1.setLocalizedName(ChatColor.GREEN + "Tek Elli Arbalet");
        item1.setItemMeta(meta1);
        NamespacedKey key1 = new NamespacedKey(BaturPlugin.plugin, "Tek_Elli_Arbalet");
        ShapedRecipe recipe1 = new ShapedRecipe(key1, item1);
        recipe1.shape("ABA", "CDC", "GDG");

        recipe1.setIngredient('B',Material.STRING);
        recipe1.setIngredient('A', Material.SPRUCE_PLANKS);
        recipe1.setIngredient('C', Material.STICK);
        recipe1.setIngredient('D', Material.IRON_INGOT);
        Bukkit.addRecipe(recipe1);

        ItemStack item2 = new ItemStack(Material.BOW);
        ItemMeta meta2 = item2.getItemMeta();
        meta2.setDisplayName(ChatColor.GREEN + "Tek Elli Arbalet");
        meta2.setLocalizedName(ChatColor.GREEN + "Tek Elli Arbalet");
        item2.setItemMeta(meta2);
        NamespacedKey key2 = new NamespacedKey(BaturPlugin.plugin, "Tek_Elli_Arbalet2");
        ShapedRecipe recipe2 = new ShapedRecipe(key2, item2);
        recipe2.shape("ABA", "CDC", "GDG");

        recipe2.setIngredient('B',Material.STRING);
        recipe2.setIngredient('A', Material.DARK_OAK_PLANKS);
        recipe2.setIngredient('C', Material.STICK);
        recipe2.setIngredient('D', Material.IRON_INGOT);
        Bukkit.addRecipe(recipe2);


        ItemStack item3 = new ItemStack(Material.BOW);
        ItemMeta meta3 = item3.getItemMeta();
        meta3.setDisplayName(ChatColor.GREEN + "Cift Elli Arbalet");
        meta3.setLocalizedName(ChatColor.GREEN + "Cift Elli Arbalet");
        item3.setItemMeta(meta3);
        NamespacedKey key3 = new NamespacedKey(BaturPlugin.plugin, "Cift_Elli_Arbalet");
        ShapedRecipe recipe3 = new ShapedRecipe(key3, item3);
        recipe3.shape("ABA", "ABA", "BCD");

        recipe3.setIngredient('B',Material.IRON_INGOT);
        recipe3.setIngredient('A', Material.SPRUCE_PLANKS);
        recipe3.setIngredient('C', Material.STICK);
        recipe3.setIngredient('D', Material.STRING);
        Bukkit.addRecipe(recipe3);

        ItemStack item4 = new ItemStack(Material.BOW);
        ItemMeta meta4 = item4.getItemMeta();
        meta4.setDisplayName(ChatColor.GREEN + "Cift Elli Arbalet");
        meta4.setLocalizedName(ChatColor.GREEN + "Cift Elli Arbalet");
        item4.setItemMeta(meta4);
        NamespacedKey key4 = new NamespacedKey(BaturPlugin.plugin, "Cift_Elli_Arbalet2");
        ShapedRecipe recipe4 = new ShapedRecipe(key4, item4);
        recipe4.shape("ABA", "ABA", "BCD");

        recipe4.setIngredient('B',Material.IRON_INGOT);
        recipe4.setIngredient('A', Material.DARK_OAK_PLANKS);
        recipe4.setIngredient('C', Material.STICK);
        recipe4.setIngredient('D', Material.STRING);
        Bukkit.addRecipe(recipe4);

        ItemStack item5 = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta5 = item5.getItemMeta();
        meta5.setDisplayName(ChatColor.GREEN + "Sivri Uclu Mizrak");
        meta5.setLocalizedName(ChatColor.GREEN + "Sivri Uclu Mizrak");
        item5.setItemMeta(meta5);
        NamespacedKey key5 = new NamespacedKey(BaturPlugin.plugin, "Sivri_Uclu_Mizrak");
        ShapedRecipe recipe5 = new ShapedRecipe(key5, item5);
        recipe5.shape("ADD", "DAB", "DBC");

        recipe5.setIngredient('B',Material.STICK);
        recipe5.setIngredient('A', Material.IRON_INGOT);
        recipe5.setIngredient('C', Material.DARK_OAK_FENCE);
        Bukkit.addRecipe(recipe5);

        ItemStack item6 = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta6 = item6.getItemMeta();
        meta6.setDisplayName(ChatColor.GREEN + "Sivri Uclu Mizrak");
        meta6.setLocalizedName(ChatColor.GREEN + "Sivri Uclu Mizrak");
        item6.setItemMeta(meta6);
        NamespacedKey key6 = new NamespacedKey(BaturPlugin.plugin, "Sivri_Uclu_Mizrak2");
        ShapedRecipe recipe6 = new ShapedRecipe(key6, item6);
        recipe6.shape("ADD", "DAB", "DBC");

        recipe6.setIngredient('B',Material.STICK);
        recipe6.setIngredient('A', Material.IRON_INGOT);
        recipe6.setIngredient('C', Material.SPRUCE_FENCE);
        Bukkit.addRecipe(recipe6);
    }


    public static void Itemlerv2()
    {
        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Sasumata");
        meta.setLocalizedName(ChatColor.GREEN + "Sasumata");
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(BaturPlugin.plugin, "Sasumata");
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("ACA", "BAB", "CBC");

        recipe.setIngredient('B',Material.STICK);
        recipe.setIngredient('A', Material.IRON_INGOT);
        Bukkit.addRecipe(recipe);

        ItemStack item2 = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta2 = item2.getItemMeta();
        meta2.setDisplayName(ChatColor.GREEN + "Nodachi");
        meta2.setLocalizedName(ChatColor.GREEN + "Nodachi");
        item2.setItemMeta(meta2);
        NamespacedKey key2 = new NamespacedKey(BaturPlugin.plugin, "Nodachi");
        ShapedRecipe recipe2 = new ShapedRecipe(key2, item2);
        recipe2.shape("ACC", "CAC", "CCB");

        recipe2.setIngredient('B',Material.SPRUCE_FENCE);
        recipe2.setIngredient('A', Material.IRON_BLOCK);
        Bukkit.addRecipe(recipe2);

        ItemStack item3 = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta3 = item3.getItemMeta();
        meta3.setDisplayName(ChatColor.GREEN + "Nodachi");
        meta3.setLocalizedName(ChatColor.GREEN + "Nodachi");
        item3.setItemMeta(meta3);
        NamespacedKey key3 = new NamespacedKey(BaturPlugin.plugin, "Nodachi2");
        ShapedRecipe recipe3 = new ShapedRecipe(key3, item3);
        recipe3.shape("ACC", "CAC", "CCB");

        recipe3.setIngredient('B',Material.DARK_OAK_FENCE);
        recipe3.setIngredient('A', Material.IRON_BLOCK);
        Bukkit.addRecipe(recipe3);

        ItemStack item4 = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta4 = item4.getItemMeta();
        meta4.setDisplayName(ChatColor.GREEN + "Gumus Baltakilic");
        meta4.setLocalizedName(ChatColor.GREEN + "Gumus Baltakilic");
        item4.setItemMeta(meta4);
        NamespacedKey key4 = new NamespacedKey(BaturPlugin.plugin, "Gumus_Baltakilic");
        ShapedRecipe recipe4 = new ShapedRecipe(key4, item4);
        recipe4.shape("ADD", "DBC", "DCC");

        recipe4.setIngredient('B',Material.IRON_INGOT);
        recipe4.setIngredient('A', Material.IRON_BLOCK);
        recipe4.setIngredient('C', Material.STICK);
        Bukkit.addRecipe(recipe4);

        ItemStack item5 = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta5 = item5.getItemMeta();
        meta5.setDisplayName(ChatColor.GREEN + "Demir Balta");
        meta5.setLocalizedName(ChatColor.GREEN + "Demir Balta");
        item5.setItemMeta(meta5);
        NamespacedKey key5 = new NamespacedKey(BaturPlugin.plugin, "Demir_Balta");
        ShapedRecipe recipe5 = new ShapedRecipe(key5, item5);
        recipe5.shape("ABD", "BCD", "DDC");

        recipe5.setIngredient('B',Material.IRON_INGOT);
        recipe5.setIngredient('A', Material.IRON_BLOCK);
        recipe5.setIngredient('C', Material.STICK);
        Bukkit.addRecipe(recipe5);

        ItemStack item6 = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta6 = item6.getItemMeta();
        meta6.setDisplayName(ChatColor.GREEN + "Uzun Savas Baltasi");
        meta6.setLocalizedName(ChatColor.GREEN + "Uzun Savas Baltasi");
        item6.setItemMeta(meta6);
        NamespacedKey key6 = new NamespacedKey(BaturPlugin.plugin, "Uzun_Savas_Baltasi");
        ShapedRecipe recipe6 = new ShapedRecipe(key6, item6);
        recipe6.shape("EAB", "ECE", "EDE");

        recipe6.setIngredient('B',Material.IRON_INGOT);
        recipe6.setIngredient('A', Material.IRON_BLOCK);
        recipe6.setIngredient('C', Material.STICK);
        recipe6.setIngredient('D', Material.SPRUCE_FENCE);
        Bukkit.addRecipe(recipe6);

        ItemStack item7 = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta7 = item7.getItemMeta();
        meta7.setDisplayName(ChatColor.GREEN + "Uzun Savas Baltasi");
        meta7.setLocalizedName(ChatColor.GREEN + "Uzun Savas Baltasi");
        item7.setItemMeta(meta7);
        NamespacedKey key7 = new NamespacedKey(BaturPlugin.plugin, "Uzun_Savas_Baltasi2");
        ShapedRecipe recipe7 = new ShapedRecipe(key7, item7);
        recipe7.shape("EAB", "ECE", "EDE");

        recipe7.setIngredient('B',Material.IRON_INGOT);
        recipe7.setIngredient('A', Material.IRON_BLOCK);
        recipe7.setIngredient('C', Material.STICK);
        recipe7.setIngredient('D', Material.DARK_OAK_FENCE);
        Bukkit.addRecipe(recipe7);


        ItemStack item8 = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta8 = item8.getItemMeta();
        meta8.setDisplayName(ChatColor.GREEN + "Demir Sopa");
        meta8.setLocalizedName(ChatColor.GREEN + "Demir Sopa");
        item8.setItemMeta(meta8);
        NamespacedKey key8 = new NamespacedKey(BaturPlugin.plugin, "Demir_Sopa");
        ShapedRecipe recipe8 = new ShapedRecipe(key8, item8);
        recipe8.shape("E","E","E");

        recipe8.setIngredient('E',Material.IRON_INGOT);
        Bukkit.addRecipe(recipe8);

        ItemStack item9 = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta9= item9.getItemMeta();
        meta9.setDisplayName(ChatColor.GREEN + "Demir Gurz");
        meta9.setLocalizedName(ChatColor.GREEN + "Demir Gurz");
        item9.setItemMeta(meta9);
        NamespacedKey key9 = new NamespacedKey(BaturPlugin.plugin, "Demir_Gurz");
        ShapedRecipe recipe9 = new ShapedRecipe(key9, item9);
        recipe9.shape("AAA","CAC","CBC");

        recipe9.setIngredient('A',Material.IRON_INGOT);
        recipe9.setIngredient('B',Material.SPRUCE_FENCE);
        Bukkit.addRecipe(recipe9);

        ItemStack item10 = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta10= item10.getItemMeta();
        meta10.setDisplayName(ChatColor.GREEN + "Demir Gurz");
        meta10.setLocalizedName(ChatColor.GREEN + "Demir Gurz");
        item10.setItemMeta(meta10);
        NamespacedKey key10 = new NamespacedKey(BaturPlugin.plugin, "Demir_Gurz2");
        ShapedRecipe recipe10 = new ShapedRecipe(key10, item10);
        recipe10.shape("AAA","CAC","CBC");

        recipe10.setIngredient('A',Material.IRON_INGOT);
        recipe10.setIngredient('B',Material.DARK_OAK_FENCE);
        Bukkit.addRecipe(recipe10);

    }


    public static void ZirhCraft()
    {
        ItemStack item1 = new ItemStack(Material.GOLDEN_CHESTPLATE);
        ItemMeta meta1= item1.getItemMeta();
        meta1.setDisplayName(ChatColor.GREEN + "Toussaint Zirhi");
        meta1.setLocalizedName(ChatColor.GREEN + "Toussaint Zirhi");
        item1.setItemMeta(meta1);
        NamespacedKey key1 = new NamespacedKey(BaturPlugin.plugin, "Toussaint_Zirhi");
        ShapedRecipe recipe1 = new ShapedRecipe(key1, item1);
        recipe1.shape("ACA","ABA","AAA");

        recipe1.setIngredient('A',Material.IRON_INGOT);
        recipe1.setIngredient('B',Material.IRON_BLOCK);
        Bukkit.addRecipe(recipe1);


        ItemStack item2 = new ItemStack(Material.GOLDEN_HELMET);
        ItemMeta meta2= item2.getItemMeta();
        meta2.setDisplayName(ChatColor.GREEN + "Toussaint Zirhi");
        meta2.setLocalizedName(ChatColor.GREEN + "Toussaint Zirhi");
        item2.setItemMeta(meta2);
        NamespacedKey key2 = new NamespacedKey(BaturPlugin.plugin, "Toussaint_Zirhi2");
        ShapedRecipe recipe2 = new ShapedRecipe(key2, item2);
        recipe2.shape("ABA","ACA","DDD");

        recipe2.setIngredient('A',Material.IRON_INGOT);
        recipe2.setIngredient('B',Material.IRON_BLOCK);
        Bukkit.addRecipe(recipe2);

        ItemStack item3 = new ItemStack(Material.GOLDEN_BOOTS);
        ItemMeta meta3= item3.getItemMeta();
        meta3.setDisplayName(ChatColor.GREEN + "Toussaint Zirhi");
        meta3.setLocalizedName(ChatColor.GREEN + "Toussaint Zirhi");
        item3.setItemMeta(meta3);
        NamespacedKey key3 = new NamespacedKey(BaturPlugin.plugin, "Toussaint_Zirhi3");
        ShapedRecipe recipe3 = new ShapedRecipe(key3, item3);
        recipe3.shape("ABA","ABA","DCD");

        recipe3.setIngredient('A',Material.IRON_INGOT);
        recipe3.setIngredient('C',Material.IRON_BLOCK);
        Bukkit.addRecipe(recipe3);

        ItemStack item4 = new ItemStack(Material.GOLDEN_LEGGINGS);
        ItemMeta meta4= item4.getItemMeta();
        meta4.setDisplayName(ChatColor.GREEN + "Toussaint Zirhi");
        meta4.setLocalizedName(ChatColor.GREEN + "Toussaint Zirhi");
        item4.setItemMeta(meta4);
        NamespacedKey key4 = new NamespacedKey(BaturPlugin.plugin, "Toussaint_Zirhi4");
        ShapedRecipe recipe4 = new ShapedRecipe(key4, item4);
        recipe4.shape("ABA","ACA","ACA");

        recipe4.setIngredient('A',Material.IRON_INGOT);
        recipe4.setIngredient('B',Material.IRON_BLOCK);
        Bukkit.addRecipe(recipe4);


        ItemStack item5 = new ItemStack(Material.GOLDEN_CHESTPLATE);
        ItemMeta meta5= item5.getItemMeta();
        meta5.setDisplayName(ChatColor.GREEN + "Celik Zirh");
        meta5.setLocalizedName(ChatColor.GREEN + "Celik Zirh");
        item5.setItemMeta(meta5);
        NamespacedKey key5 = new NamespacedKey(BaturPlugin.plugin, "Celik_Zirh");
        ShapedRecipe recipe5 = new ShapedRecipe(key5, item5);
        recipe5.shape("ACA","ABA","ABA");

        recipe5.setIngredient('A',Material.IRON_INGOT);
        recipe5.setIngredient('B',Material.IRON_BLOCK);
        Bukkit.addRecipe(recipe5);

        ItemStack item6 = new ItemStack(Material.GOLDEN_HELMET);
        ItemMeta meta6= item6.getItemMeta();
        meta6.setDisplayName(ChatColor.GREEN + "Celik Zirh");
        meta6.setLocalizedName(ChatColor.GREEN + "Celik Zirh");
        item6.setItemMeta(meta6);
        NamespacedKey key6 = new NamespacedKey(BaturPlugin.plugin, "Celik_Zirh2");
        ShapedRecipe recipe6 = new ShapedRecipe(key6, item6);
        recipe6.shape("BAB","ACA","DDD");

        recipe6.setIngredient('A',Material.IRON_INGOT);
        recipe6.setIngredient('B',Material.IRON_BLOCK);
        Bukkit.addRecipe(recipe6);

        ItemStack item7 = new ItemStack(Material.GOLDEN_LEGGINGS);
        ItemMeta meta7= item7.getItemMeta();
        meta7.setDisplayName(ChatColor.GREEN + "Celik Zirh");
        meta7.setLocalizedName(ChatColor.GREEN + "Celik Zirh");
        item7.setItemMeta(meta7);
        NamespacedKey key7 = new NamespacedKey(BaturPlugin.plugin, "Celik_Zirh3");
        ShapedRecipe recipe7 = new ShapedRecipe(key7, item7);
        recipe7.shape("ACA","BCB","DDD");

        recipe7.setIngredient('A',Material.IRON_INGOT);
        recipe7.setIngredient('B',Material.IRON_BLOCK);
        Bukkit.addRecipe(recipe7);

        ItemStack item8 = new ItemStack(Material.GOLDEN_LEGGINGS);
        ItemMeta meta8= item8.getItemMeta();
        meta8.setDisplayName(ChatColor.GREEN + "Celik Zirh");
        meta8.setLocalizedName(ChatColor.GREEN + "Celik Zirh");
        item8.setItemMeta(meta8);
        NamespacedKey key8 = new NamespacedKey(BaturPlugin.plugin, "Celik_Zirh4");
        ShapedRecipe recipe8 = new ShapedRecipe(key8, item8);
        recipe8.shape("BAB","ACA","ACA");

        recipe8.setIngredient('A',Material.IRON_INGOT);
        recipe8.setIngredient('B',Material.IRON_BLOCK);
        Bukkit.addRecipe(recipe8);

        ItemStack item9 = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta9= item9.getItemMeta();
        meta9.setDisplayName(ChatColor.GREEN + "Kisa Kilic");
        meta9.setLocalizedName(ChatColor.GREEN + "Kisa Kilic");
        item9.setItemMeta(meta9);
        NamespacedKey key9 = new NamespacedKey(BaturPlugin.plugin, "Kisa_Kilic2");
        ShapedRecipe recipe9 = new ShapedRecipe(key9, item9);
        recipe9.shape("BAA","CCC","CCC");

        recipe9.setIngredient('A',Material.IRON_INGOT);
        recipe9.setIngredient('B',Material.STICK);
        Bukkit.addRecipe(recipe9);

        ItemStack item10 = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta10= item10.getItemMeta();
        meta10.setDisplayName(ChatColor.GREEN + "Rapier");
        meta10.setLocalizedName(ChatColor.GREEN + "Rapier");
        item10.setItemMeta(meta10);
        NamespacedKey key10 = new NamespacedKey(BaturPlugin.plugin, "Rapier");
        ShapedRecipe recipe10 = new ShapedRecipe(key10, item10);
        recipe10.shape("CCC","AAB","CCC");

        recipe10.setIngredient('A',Material.IRON_INGOT);
        recipe10.setIngredient('B',Material.STICK);
        Bukkit.addRecipe(recipe10);

        ItemStack item11 = new ItemStack(Material.BOW);
        ItemMeta meta11= item11.getItemMeta();
        meta11.setDisplayName(ChatColor.GREEN + "Kisa Yay");
        meta11.setLocalizedName(ChatColor.GREEN + "Kisa Yay");
        item11.setItemMeta(meta11);
        NamespacedKey key11 = new NamespacedKey(BaturPlugin.plugin, "Kisa_Yay");
        ShapedRecipe recipe11 = new ShapedRecipe(key11, item11);
        recipe11.shape("AAA","DBD","CCC");

        recipe11.setIngredient('A',Material.STRING);
        recipe11.setIngredient('B',Material.IRON_INGOT);
        recipe11.setIngredient('C',Material.SPRUCE_PLANKS);
        Bukkit.addRecipe(recipe11);

        ItemStack item16 = new ItemStack(Material.BOW);
        ItemMeta meta16= item16.getItemMeta();
        meta16.setDisplayName(ChatColor.GREEN + "Kisa Yay");
        meta16.setLocalizedName(ChatColor.GREEN + "Kisa Yay");
        item16.setItemMeta(meta16);
        NamespacedKey key16 = new NamespacedKey(BaturPlugin.plugin, "Kisa_Yay2");
        ShapedRecipe recipe16 = new ShapedRecipe(key16, item16);
        recipe16.shape("AAA","DBD","CCC");

        recipe16.setIngredient('A',Material.STRING);
        recipe16.setIngredient('B',Material.IRON_INGOT);
        recipe16.setIngredient('C',Material.DARK_OAK_PLANKS);
        Bukkit.addRecipe(recipe16);

        ItemStack item12 = new ItemStack(Material.BOW);
        ItemMeta meta12= item12.getItemMeta();
        meta12.setDisplayName(ChatColor.GREEN + "Uzun Yay");
        meta12.setLocalizedName(ChatColor.GREEN + "Uzun Yay");
        item12.setItemMeta(meta12);
        NamespacedKey key12 = new NamespacedKey(BaturPlugin.plugin, "Uzun_Yay");
        ShapedRecipe recipe12 = new ShapedRecipe(key12, item12);
        recipe12.shape("AAA","BBB","CCC");

        recipe12.setIngredient('A',Material.IRON_INGOT);
        recipe12.setIngredient('B',Material.STRING);
        recipe12.setIngredient('C',Material.SPRUCE_PLANKS);
        Bukkit.addRecipe(recipe12);

        ItemStack item13 = new ItemStack(Material.BOW);
        ItemMeta meta13= item13.getItemMeta();
        meta13.setDisplayName(ChatColor.GREEN + "Uzun Yay");
        meta13.setLocalizedName(ChatColor.GREEN + "Uzun Yay");
        item13.setItemMeta(meta13);
        NamespacedKey key13 = new NamespacedKey(BaturPlugin.plugin, "Uzun_Yay2");
        ShapedRecipe recipe13 = new ShapedRecipe(key13, item13);
        recipe13.shape("AAA","BBB","CCC");

        recipe13.setIngredient('A',Material.IRON_INGOT);
        recipe13.setIngredient('B',Material.STRING);
        recipe13.setIngredient('C',Material.SPRUCE_PLANKS);
        Bukkit.addRecipe(recipe13);

    }



    @SuppressWarnings("deprecation")
    @EventHandler
    public static void DemirZirh(CraftItemEvent e)
    {
        Player player = (Player) e.getWhoClicked();



        if(e.getRecipe().getResult().getType() == Material.IRON_CHESTPLATE || e.getRecipe().getResult().getType() == Material.IRON_LEGGINGS || e.getRecipe().getResult().getType() == Material.IRON_HELMET || e.getRecipe().getResult().getType() == Material.IRON_BOOTS)
        {

            if(EventDosyasi.EventsClass.getMeslek(player.getPlayer().getName()).equals("Demirci"))
            {
                //   if(ChatColor.stripColor(e.getInventory().getResult().getType().toString()).contains(CustomItemler.CustomItems.SilahAd(ChatColor.stripColor(ChatColor.RESET+e.getInventory().getResult().getType().toString()))))
                //   {

                int Temp;
                Temp = Integer.valueOf(EventDosyasi.EventsClass.getMeslekXP(player.getName()));
                Temp = Temp + 1;
                EventDosyasi.EventsClass.setMeslekXP(player.getName(),Temp);

                List<String> Lore = new ArrayList<>();
                Lore.add(SilahLore1(CustomItemler.CustomItems.SilahAd(ChatColor.stripColor(ChatColor.RESET+e.getInventory().getResult().getType().toString()))));
                ItemStack item = e.getInventory().getResult();
                item.setDurability((short) 222);
                ItemMeta meta = item.getItemMeta();
                meta.setLore(Lore);
                meta.setDisplayName(SilahAd(e.getInventory().getResult().getType().toString()));
                item.setItemMeta(meta);
                player.updateInventory();
                // }
            }
            else
            {
                e.setCancelled(true);
                player.sendMessage(ChatColor.RED+"[Hata]: "+ChatColor.YELLOW+"Mesleginiz demirci degil.");
                Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE+"[Bilgi Mesaji]: "+ChatColor.RED+player.getName()+ChatColor.YELLOW+" isimli oyuncu demirci olmadigi halde craft yapmaya calisti.");
            }


        }



    }


    @EventHandler
    public static void Ciftcilik(CraftItemEvent e)
    {

        Player p = (Player) e.getWhoClicked();

        if(e.getRecipe().getResult().getType() == Material.BREAD || e.getRecipe().getResult().getType() == Material.CAKE || e.getRecipe().getResult().getType() == Material.BEETROOT_SOUP || e.getRecipe().getResult().getType() == Material.MUSHROOM_STEW || e.getRecipe().getResult().getType() == Material.RABBIT_STEW || e.getRecipe().getResult().getType() == Material.PUMPKIN_PIE)
        {

            if(EventDosyasi.EventsClass.getMeslek(p.getPlayer().getName()).equals("Ciftci"))
            {

                int Temp;
                Temp = Integer.valueOf(EventDosyasi.EventsClass.getMeslekXP(p.getName()));
                Temp = Temp + 1;
                EventDosyasi.EventsClass.setMeslekXP(p.getName(),Temp);

            }
            else
            {
                e.setCancelled(true);
                p.sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.YELLOW+"Ciftci olmadiginiz icin yemek yapamazsiniz.");
            }
        }

    }

    @EventHandler
    public static void CiftciHasat(BlockBreakEvent e)
    {
        Player p = e.getPlayer();

        if(e.getBlock().getType() == Material.WHEAT || e.getBlock().getType() == Material.POTATO || e.getBlock().getType() == Material.BEETROOT || e.getBlock().getType() == Material.MELON || e.getBlock().getType() == Material.PUMPKIN)
        {

            if(EventDosyasi.EventsClass.getMeslek(p.getPlayer().getName()).equals("Ciftci"))
            {


            }
            else
            {
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED+"[Hata]: "+ChatColor.YELLOW+"Mesleginiz ciftci degil!");
                Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE+"[Bilgi Mesaji]: "+ChatColor.RED+p.getName()+ChatColor.YELLOW+" isimli oyuncu ciftci olmadigi halde hasat yapmaya calisti. Basarisiz oldu.");
            }
        }


    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public static void AletlerinCrafti(CraftItemEvent e)
    {
        Player p = (Player) e.getWhoClicked();

        try
        {

            if(e.getRecipe().getResult().getType() == Material.IRON_PICKAXE ||  e.getRecipe().getResult().getType() == Material.IRON_AXE || e.getRecipe().getResult().getType() == Material.IRON_SHOVEL || e.getRecipe().getResult().getType() == Material.IRON_HOE )
            {

                if(EventDosyasi.EventsClass.getMeslek(p.getPlayer().getName()).equals("Demirci"))
                {
                    if(EventDosyasi.EventsClass.getMeslekSeviye(p.getName()).equals("3") || EventDosyasi.EventsClass.getMeslekSeviye(p.getName()).equals("4") || EventDosyasi.EventsClass.getMeslekSeviye(p.getName()).equals("5"))
                    {
                        int Temp;
                        Temp = Integer.valueOf(EventDosyasi.EventsClass.getMeslekXP(p.getName()));
                        Temp = Temp + 1;
                        EventDosyasi.EventsClass.setMeslekXP(p.getName(),Temp);

                        List<String> Lore = new ArrayList<>();
                        Lore.add(ChatColor.LIGHT_PURPLE+"Demirden yapilmis kaliteli bir alet...");
                        ItemStack item = e.getInventory().getResult();
                        ItemMeta meta = item.getItemMeta();
                        meta.setLore(Lore);
                        item.setItemMeta(meta);
                        p.updateInventory();
                    }
                    else
                    {

                        e.setCancelled(true);
                        p.sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.YELLOW+"Demir aletler yapabilmek icin en az 3.Seviye Demirci olmalisiniz.");

                    }

                    if(EventDosyasi.EventsClass.getMeslekXP(p.getName()).equals("30"))
                    {
                        p.sendMessage(ChatColor.GOLD+"[Bilgi]: "+ChatColor.RED+"Artik eskisinden daha kaliteli esyalar yapiyorsun.");
                        p.sendMessage(ChatColor.YELLOW+"(2.Seviye Demir Ustasi oldun!)");

                        EventDosyasi.EventsClass.setMeslekSeviye(p.getName(),2);
                    }
                    else if(EventDosyasi.EventsClass.getMeslekXP(p.getName()).equals("120"))
                    {
                        p.sendMessage(ChatColor.GOLD+"[Bilgi]: "+ChatColor.RED+"Demir esyalar artik kirilmayacak kadar saglam...");
                        p.sendMessage(ChatColor.YELLOW+"(3.Seviye Kirilmaz Irade Demircisi oldun!)");

                        EventDosyasi.EventsClass.setMeslekSeviye(p.getName(),3);
                    }
                    else if(EventDosyasi.EventsClass.getMeslekXP(p.getName()).equals("280"))
                    {
                        p.sendMessage(ChatColor.GOLD+"[Bilgi]: "+ChatColor.RED+"Yaptigin esyalar artik eskisinden daha kaliteli olacak.");
                        p.sendMessage(ChatColor.YELLOW+"(4.Seviye Usta Demirci oldun!");

                        EventDosyasi.EventsClass.setMeslekSeviye(p.getName(),4);
                    }
                    else if(EventDosyasi.EventsClass.getMeslekXP(p.getName()).equals("500"))
                    {

                        p.sendMessage(ChatColor.YELLOW+"(Efsanevi Seviye!!! Rï¿½n ï¿½stadï¿½ Oldun! )");

                        EventDosyasi.EventsClass.setMeslekSeviye(p.getName(),5);
                    }
                }
                else
                {
                    e.setCancelled(true);
                    p.sendMessage(ChatColor.RED+"[Hata]: "+ChatColor.YELLOW+"Mesleginiz demirci deï¿½il!");
                    Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE+"[Bilgi Mesaji]: "+ChatColor.RED+p.getName()+ChatColor.YELLOW+" isimli oyuncu Demirci olmadigi icin craft yapamadi.");
                }



            }






        }

        catch(Exception ex)
        {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"[Hata](Konum AletlerinCrafti)");
        }

    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public static void DeriKiyafet(CraftItemEvent e)
    {
        Player player = (Player) e.getWhoClicked();


        try
        {

            if(e.getRecipe().getResult().getType() == Material.WOODEN_AXE || e.getRecipe().getResult().getType() == Material.WOODEN_HOE || e.getRecipe().getResult().getType() == Material.WOODEN_PICKAXE
                    || e.getRecipe().getResult().getType() == Material.WOODEN_SHOVEL || e.getRecipe().getResult().getType() == Material.WOODEN_SWORD ||
                    e.getRecipe().getResult().getType() == Material.STONE_PICKAXE || e.getRecipe().getResult().getType() == Material.STONE_AXE ||
                    e.getRecipe().getResult().getType() == Material.STONE_SHOVEL || e.getRecipe().getResult().getType() == Material.STONE_SWORD  || e.getRecipe().getResult().getType() == Material.STONE_HOE

            )
            {
                e.setCancelled(true);
                player.sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.YELLOW+"Bu esyalari craftlamak yasaktir.");

            }

            if(e.getRecipe().getResult().getType() == Material.LEATHER_LEGGINGS || e.getRecipe().getResult().getType() == Material.LEATHER_HELMET || e.getRecipe().getResult().getType() == Material.LEATHER_CHESTPLATE || e.getRecipe().getResult().getType() == Material.LEATHER_BOOTS)
            {

                //   if(ChatColor.stripColor(e.getInventory().getResult().getType().toString()).contains(CustomItemler.CustomItems.SilahAd(ChatColor.stripColor(ChatColor.RESET+e.getInventory().getResult().getType().toString()))))
                //   {
                if(EventDosyasi.EventsClass.getMeslek(player.getPlayer().getName()).equals("Demirci"))
                {
                    int Temp;
                    Temp = Integer.valueOf(EventDosyasi.EventsClass.getMeslekXP(player.getName()));
                    Temp = Temp + 1;
                    EventDosyasi.EventsClass.setMeslekXP(player.getName(),Temp);

                    List<String> Lore = new ArrayList<>();
                    Lore.add(SilahLore1(CustomItemler.CustomItems.SilahAd(ChatColor.stripColor(ChatColor.RESET+e.getInventory().getResult().getType().toString()))));
                    ItemStack item = e.getInventory().getResult();
                    item.setDurability((short) SilahDur1(CustomItemler.CustomItems.SilahAd(ChatColor.stripColor(ChatColor.RESET+e.getInventory().getResult().getItemMeta().getDisplayName()))));
                    ItemMeta meta = item.getItemMeta();
                    meta.setLore(Lore);
                    meta.setDisplayName(SilahAd(e.getInventory().getResult().getType().toString()));
                    item.setItemMeta(meta);
                    player.updateInventory();


                    if(
                            e.getRecipe().getResult().getType() == Material.DIAMOND_CHESTPLATE ||
                                    e.getRecipe().getResult().getType() == Material.DIAMOND_BOOTS ||
                                    e.getRecipe().getResult().getType() == Material.DIAMOND_LEGGINGS ||
                                    e.getRecipe().getResult().getType() == Material.DIAMOND_HELMET ||
                                    e.getRecipe().getResult().getType() == Material.DIAMOND_PICKAXE||
                                    e.getRecipe().getResult().getType() == Material.DIAMOND_AXE ||
                                    e.getRecipe().getResult().getType() == Material.DIAMOND_HOE||
                                    e.getRecipe().getResult().getType() == Material.DIAMOND_SHOVEL ||
                                    e.getRecipe().getResult().getType() == Material.DIAMOND_SWORD
                    )
                    {
                        if(player.isOp())
                        {
                            player.sendMessage(ChatColor.BLUE+"[Admin DEBUG Bilgi]: "+ChatColor.YELLOW+"Bu esyalari craftlamak oyunculara yasak. Admin oldugunuz icin craftlayabiliyorsunuz.");
                        }
                        else
                        {

                            e.setCancelled(true);
                            player.sendMessage(ChatColor.BLUE+"[Demircilik DEBUG]: "+ChatColor.YELLOW+"Bu eşyaları yapmak yasaktır. Lütfen forumda verilen listeyi kullanınız.");
                            Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE+"[Demircilik Bilgi]: "+ChatColor.RED+player.getName()+ChatColor.YELLOW+" isimli oyuncu yasaklı eşya craftlamaya çalıştı.");



                        }
                    }

                    if(EventDosyasi.EventsClass.getMeslekXP(player.getName()).equals("30"))
                    {
                        player.sendMessage(ChatColor.GOLD+"[Bilgi]: "+ChatColor.RED+"Artık eskisinden daha kaliteli eşyalar üretebiliyorsun....");
                        player.sendMessage(ChatColor.YELLOW+"(2.Seviye Demir Ustası oldun!)");

                        EventDosyasi.EventsClass.setMeslekSeviye(player.getName(),2);
                    }
                    else if(EventDosyasi.EventsClass.getMeslekXP(player.getName()).equals("120"))
                    {
                        player.sendMessage(ChatColor.GOLD+"[Bilgi]: "+ChatColor.RED+"Yaptığın eşyalar artık daha sağlam. Eski kadar çabuk kırılmayacak.");
                        player.sendMessage(ChatColor.YELLOW+"(3.Seviye Kırılmaz İrade Demircisi oldun!)");

                        EventDosyasi.EventsClass.setMeslekSeviye(player.getName(),3);
                    }
                    else if(EventDosyasi.EventsClass.getMeslekXP(player.getName()).equals("280"))
                    {
                        player.sendMessage(ChatColor.GOLD+"[Bilgi]: "+ChatColor.RED+"Dövdüğün eşyalar artık çok daha kaliteli olacak.");
                        player.sendMessage(ChatColor.YELLOW+"(4.Seviye Usta Demirci oldun!");

                        EventDosyasi.EventsClass.setMeslekSeviye(player.getName(),4);
                    }
                    else if(EventDosyasi.EventsClass.getMeslekXP(player.getName()).equals("500"))
                    {
                        player.sendMessage(ChatColor.GOLD+"[Bilgi]: "+ChatColor.RED+"En bilgelerin bilgesi! Demirin efendisi! Rün Ustası olmayı başardın.");
                        player.sendMessage(ChatColor.YELLOW+"(Efsanevi Seviye!!! Rün Ustası oldun! )");

                        EventDosyasi.EventsClass.setMeslekSeviye(player.getName(),5);
                    }

                    // }
                }
                else
                {
                    e.setCancelled(true);
                    player.sendMessage(ChatColor.RED+"[Hata]: "+ChatColor.YELLOW+"Mesleğiniz demirci değil!");
                    Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE+"[Bilgi Mesajı]: "+ChatColor.RED+player.getName()+ChatColor.YELLOW+" isimli oyuncu Demirci olmadigi halde craft yapmaya calisti.");
                }


            }




        }
        catch(Exception ex)
        {

        }



    }
    @SuppressWarnings("deprecation")
    @EventHandler
    public static void Demircilik(CraftItemEvent e)
    {




        Player player = (Player) e.getWhoClicked();

        try
        {

            if(e.isShiftClick())
            {
                e.setCancelled(true);
                player.sendMessage(ChatColor.RED+"[Hata]: "+ChatColor.YELLOW+"Shift+Click ile bu eşyayı yapamazsınız! Lütfen elinizle sürükleyerek yapınız.");
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"[Hata Bilgi]: "+ChatColor.BLUE+player.getName()+ChatColor.YELLOW+" isimli oyuncu Shift+Click ile craft yapmaya çalıştı.");

            }
            else
            {
                // String RenksizSilahAdi = ChatColor.stripColor(ChatColor.RESET+e.getInventory().getResult().getItemMeta().getDisplayName());
                //  String ConfigSilahAdi = CustomItemler.CustomItems.SilahAd(RenksizSilahAdi);


                if(EventDosyasi.EventsClass.getMeslekSeviye(player.getName()).equals("1"))
                {


                    if(ChatColor.stripColor(e.getInventory().getResult().getItemMeta().getDisplayName().toString()).contains(CustomItemler.CustomItems.SilahAd(ChatColor.stripColor(ChatColor.RESET+e.getInventory().getResult().getItemMeta().getDisplayName()))))
                    {
                        if(EventDosyasi.EventsClass.getMeslek(player.getPlayer().getName()).equals("Demirci"))
                        {

                            int Temp;
                            Temp = Integer.valueOf(EventDosyasi.EventsClass.getMeslekXP(player.getName()));
                            Temp = Temp + 1;
                            EventDosyasi.EventsClass.setMeslekXP(player.getName(),Temp);

                            List<String> Lore = new ArrayList<>();
                            Lore.add(SilahLore1(CustomItemler.CustomItems.SilahAd(ChatColor.stripColor(ChatColor.RESET+e.getInventory().getResult().getItemMeta().getDisplayName()))));
                            ItemStack item = e.getInventory().getResult();
                            item.setDurability((short) SilahDur1(CustomItemler.CustomItems.SilahAd(ChatColor.stripColor(ChatColor.RESET+e.getInventory().getResult().getItemMeta().getDisplayName()))));
                            ItemMeta meta = item.getItemMeta();
                            meta.setLore(Lore);
                            meta.setDisplayName(SilahRenk1(CustomItemler.CustomItems.SilahAd(ChatColor.stripColor(ChatColor.RESET+e.getInventory().getResult().getItemMeta().getDisplayName())))+SilahAd(CustomItemler.CustomItems.SilahAd(ChatColor.stripColor(ChatColor.RESET+e.getInventory().getResult().getItemMeta().getDisplayName()))));
                            item.setItemMeta(meta);
                            player.updateInventory();
                        }
                        else
                        {
                            player.sendMessage(ChatColor.RED+"[Hata]: "+ChatColor.YELLOW+"Mesleğiniz demirci deï¿½il!");
                            Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE+"[Bilgi Mesajı]: "+ChatColor.RED+player.getName()+ChatColor.YELLOW+" isimli oyuncu Demirci olmadigi halde esya craftlamaya calisti.");

                            e.setCancelled(true);
                        }




                    }


                }
                if(EventDosyasi.EventsClass.getMeslekSeviye(player.getName()).equals("2"))
                {
                    if(ChatColor.stripColor(e.getInventory().getResult().getItemMeta().getDisplayName().toString()).contains(CustomItemler.CustomItems.SilahAd(ChatColor.stripColor(ChatColor.RESET+e.getInventory().getResult().getItemMeta().getDisplayName()))))
                    {

                        if(EventDosyasi.EventsClass.getMeslek(player.getPlayer().getName()).equals("Demirci"))
                        {
                            int Temp;
                            Temp = Integer.valueOf(EventDosyasi.EventsClass.getMeslekXP(player.getName()));
                            Temp = Temp + 1;
                            EventDosyasi.EventsClass.setMeslekXP(player.getName(),Temp);

                            List<String> Lore = new ArrayList<>();
                            Lore.add(SilahLore2(CustomItemler.CustomItems.SilahAd(ChatColor.stripColor(ChatColor.RESET+e.getInventory().getResult().getItemMeta().getDisplayName()))));
                            ItemStack item = e.getInventory().getResult();
                            item.setDurability((short) SilahDur2(CustomItemler.CustomItems.SilahAd(ChatColor.stripColor(ChatColor.RESET+e.getInventory().getResult().getItemMeta().getDisplayName()))));
                            ItemMeta meta = item.getItemMeta();
                            meta.setLore(Lore);
                            meta.setDisplayName(SilahRenk2(CustomItemler.CustomItems.SilahAd(ChatColor.stripColor(ChatColor.RESET+e.getInventory().getResult().getItemMeta().getDisplayName())))+SilahAd(CustomItemler.CustomItems.SilahAd(ChatColor.stripColor(ChatColor.RESET+e.getInventory().getResult().getItemMeta().getDisplayName()))));
                            item.setItemMeta(meta);
                            player.updateInventory();
                        }
                        else
                        {
                            player.sendMessage(ChatColor.RED+"[Hata]: "+ChatColor.YELLOW+"Mesleï¿½iniz demirci deï¿½il!");
                            Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE+"[Bilgi Mesajï¿½]: "+ChatColor.RED+player.getName()+ChatColor.YELLOW+" isimli oyuncu Demirci olmadï¿½ï¿½ï¿½ halde silah craftlamaya ï¿½alï¿½ï¿½tï¿½. Baï¿½arï¿½sï¿½z oldu.");

                            e.setCancelled(true);
                        }

                    }
                }
                if(EventDosyasi.EventsClass.getMeslekSeviye(player.getName()).equals("3"))
                {

                    if(ChatColor.stripColor(e.getInventory().getResult().getItemMeta().getDisplayName().toString()).contains(CustomItemler.CustomItems.SilahAd(ChatColor.stripColor(ChatColor.RESET+e.getInventory().getResult().getItemMeta().getDisplayName()))))
                    {
                        if(EventDosyasi.EventsClass.getMeslek(player.getPlayer().getName()).equals("Demirci"))
                        {
                            int Temp;
                            Temp = Integer.valueOf(EventDosyasi.EventsClass.getMeslekXP(player.getName()));
                            Temp = Temp + 1;
                            EventDosyasi.EventsClass.setMeslekXP(player.getName(),Temp);

                            List<String> Lore = new ArrayList<>();
                            Lore.add(SilahLore3(CustomItemler.CustomItems.SilahAd(ChatColor.stripColor(ChatColor.RESET+e.getInventory().getResult().getItemMeta().getDisplayName()))));
                            ItemStack item = e.getInventory().getResult();
                            item.setDurability((short) SilahDur3(CustomItemler.CustomItems.SilahAd(ChatColor.stripColor(ChatColor.RESET+e.getInventory().getResult().getItemMeta().getDisplayName()))));
                            ItemMeta meta = item.getItemMeta();
                            meta.setLore(Lore);
                            meta.setDisplayName(SilahRenk3(CustomItemler.CustomItems.SilahAd(ChatColor.stripColor(ChatColor.RESET+e.getInventory().getResult().getItemMeta().getDisplayName())))+SilahAd(CustomItemler.CustomItems.SilahAd(ChatColor.stripColor(ChatColor.RESET+e.getInventory().getResult().getItemMeta().getDisplayName()))));
                            item.setItemMeta(meta);
                            player.updateInventory();
                        }
                        else
                        {
                            player.sendMessage(ChatColor.RED+"[Hata]: "+ChatColor.YELLOW+"Mesleï¿½iniz demirci deï¿½il!");
                            Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE+"[Bilgi Mesajï¿½]: "+ChatColor.RED+player.getName()+ChatColor.YELLOW+" isimli oyuncu Demirci olmadï¿½ï¿½ï¿½ halde silah craftlamaya ï¿½alï¿½ï¿½tï¿½. Baï¿½arï¿½sï¿½z oldu.");

                            e.setCancelled(true);
                        }


                    }
                }
                if(EventDosyasi.EventsClass.getMeslekSeviye(player.getName()).equals("4"))
                {

                    if(ChatColor.stripColor(e.getInventory().getResult().getItemMeta().getDisplayName().toString()).contains(CustomItemler.CustomItems.SilahAd(ChatColor.stripColor(ChatColor.RESET+e.getInventory().getResult().getItemMeta().getDisplayName()))))
                    {


                        if(EventDosyasi.EventsClass.getMeslek(player.getPlayer().getName()).equals("Demirci"))
                        {
                            int Temp;
                            Temp = Integer.valueOf(EventDosyasi.EventsClass.getMeslekXP(player.getName()));
                            Temp = Temp + 1;
                            EventDosyasi.EventsClass.setMeslekXP(player.getName(),Temp);

                            List<String> Lore = new ArrayList<>();
                            Lore.add(SilahLore4(CustomItemler.CustomItems.SilahAd(ChatColor.stripColor(ChatColor.RESET+e.getInventory().getResult().getItemMeta().getDisplayName()))));
                            ItemStack item = e.getInventory().getResult();
                            item.setDurability((short) SilahDur4(CustomItemler.CustomItems.SilahAd(ChatColor.stripColor(ChatColor.RESET+e.getInventory().getResult().getItemMeta().getDisplayName()))));
                            ItemMeta meta = item.getItemMeta();
                            meta.setLore(Lore);
                            meta.setDisplayName(SilahRenk4(CustomItemler.CustomItems.SilahAd(ChatColor.stripColor(ChatColor.RESET+e.getInventory().getResult().getItemMeta().getDisplayName())))+SilahAd(CustomItemler.CustomItems.SilahAd(ChatColor.stripColor(ChatColor.RESET+e.getInventory().getResult().getItemMeta().getDisplayName()))));
                            item.setItemMeta(meta);
                            player.updateInventory();
                        }
                        else
                        {
                            player.sendMessage(ChatColor.RED+"[Hata]: "+ChatColor.YELLOW+"Mesleï¿½iniz demirci deï¿½il!");
                            Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE+"[Bilgi Mesajï¿½]: "+ChatColor.RED+player.getName()+ChatColor.YELLOW+" isimli oyuncu Demirci olmadï¿½ï¿½ï¿½ halde silah craftlamaya ï¿½alï¿½ï¿½tï¿½. Baï¿½arï¿½sï¿½z oldu.");

                            e.setCancelled(true);
                        }
                    }

                }
                if(EventDosyasi.EventsClass.getMeslekSeviye(player.getName()).equals("5"))
                {


                    if(ChatColor.stripColor(e.getInventory().getResult().getItemMeta().getDisplayName().toString()).contains(CustomItemler.CustomItems.SilahAd(ChatColor.stripColor(ChatColor.RESET+e.getInventory().getResult().getItemMeta().getDisplayName()))))
                    {
                        if(EventDosyasi.EventsClass.getMeslek(player.getPlayer().getName()).equals("Demirci"))
                        {
                            int Temp;
                            Temp = Integer.valueOf(EventDosyasi.EventsClass.getMeslekXP(player.getName()));
                            Temp = Temp + 1;
                            EventDosyasi.EventsClass.setMeslekXP(player.getName(),Temp);

                            List<String> Lore = new ArrayList<>();
                            Lore.add(SilahLore5(CustomItemler.CustomItems.SilahAd(ChatColor.stripColor(ChatColor.RESET+e.getInventory().getResult().getItemMeta().getDisplayName()))));
                            ItemStack item = e.getInventory().getResult();
                            item.setDurability((short) SilahDur5(CustomItemler.CustomItems.SilahAd(ChatColor.stripColor(ChatColor.RESET+e.getInventory().getResult().getItemMeta().getDisplayName()))));
                            ItemMeta meta = item.getItemMeta();
                            meta.setLore(Lore);
                            meta.setDisplayName(SilahRenk5(CustomItemler.CustomItems.SilahAd(ChatColor.stripColor(ChatColor.RESET+e.getInventory().getResult().getItemMeta().getDisplayName())))+SilahAd(CustomItemler.CustomItems.SilahAd(ChatColor.stripColor(ChatColor.RESET+e.getInventory().getResult().getItemMeta().getDisplayName()))));
                            item.setItemMeta(meta);
                            player.updateInventory();
                        }
                        else
                        {
                            player.sendMessage(ChatColor.RED+"[Hata]: "+ChatColor.YELLOW+"Mesleï¿½iniz demirci deï¿½il!");
                            Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE+"[Bilgi Mesajï¿½]: "+ChatColor.RED+player.getName()+ChatColor.YELLOW+" isimli oyuncu Demirci olmadï¿½ï¿½ï¿½ halde silah craftlamaya ï¿½alï¿½ï¿½tï¿½. Baï¿½arï¿½sï¿½z oldu.");

                            e.setCancelled(true);
                        }

                    }
                }


                if(EventDosyasi.EventsClass.getMeslekXP(player.getName()).equals("30"))
                {
                    player.sendMessage(ChatColor.GOLD+"[Bilgi]: "+ChatColor.RED+"Artï¿½k eskisinden daha kaliteli eï¿½yalar ï¿½retebiliyorsun...");
                    player.sendMessage(ChatColor.YELLOW+"(2.Seviye Demir Ustasï¿½ oldun!)");

                    EventDosyasi.EventsClass.setMeslekSeviye(player.getName(),2);
                }
                else if(EventDosyasi.EventsClass.getMeslekXP(player.getName()).equals("120"))
                {
                    player.sendMessage(ChatColor.GOLD+"[Bilgi]: "+ChatColor.RED+"Dï¿½vdï¿½ï¿½ï¿½n eï¿½yalar artï¿½k hiï¿½bir zaman kï¿½rï¿½lmayacak.");
                    player.sendMessage(ChatColor.YELLOW+"(3.Seviye Kï¿½rï¿½lmaz ï¿½rade Demircisi oldun!)");

                    EventDosyasi.EventsClass.setMeslekSeviye(player.getName(),3);
                }
                else if(EventDosyasi.EventsClass.getMeslekXP(player.getName()).equals("280"))
                {
                    player.sendMessage(ChatColor.GOLD+"[Bilgi]: "+ChatColor.RED+"Dï¿½vdï¿½ï¿½ï¿½n eï¿½yalar artï¿½k daha kaliteli olacak.");
                    player.sendMessage(ChatColor.YELLOW+"(4.Seviye Usta Demirci oldun!");

                    EventDosyasi.EventsClass.setMeslekSeviye(player.getName(),4);
                }
                else if(EventDosyasi.EventsClass.getMeslekXP(player.getName()).equals("500"))
                {
                    player.sendMessage(ChatColor.GOLD+"[Bilgi]: "+ChatColor.RED+"En bilgelerin bilgesi! Demirin efendisi! Tï¿½m daï¿½larï¿½n yokedicisi... Usta Rï¿½n ï¿½stadï¿½ olmayï¿½ baï¿½ardï¿½n");
                    player.sendMessage(ChatColor.RED+"Artï¿½k yaptï¿½ï¿½ï¿½n hiï¿½bir ï¿½ey kï¿½rï¿½lmayacak. Eï¿½yalara rï¿½n ekleyebilecek ve gï¿½ï¿½lendirebileceksin. Yaptï¿½ï¿½ï¿½n her silah ï¿½uana kadar ï¿½retilmiï¿½ en keskin silah olacak!");
                    player.sendMessage(ChatColor.YELLOW+"(Efsanevi Seviye!!! Rï¿½n ï¿½stadï¿½ Oldun! )");

                    EventDosyasi.EventsClass.setMeslekSeviye(player.getName(),5);
                }





            }







        }
        catch(Exception ex)
        {

        }






    }






}
