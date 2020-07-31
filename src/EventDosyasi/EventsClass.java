package EventDosyasi;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import AnaDizinDosyasi.BaturPlugin;
import Komutlar.KomutListesi;

public class EventsClass implements Listener {


    public String url = "jdbc:mysql://localhost:3306/karakter?autoReconnect=true&useSSL=true";
    public String user = "root";
    public String pass = "test";

    public int BasvuruDurum;
    public String ForumAdi;
    public String OyunAdi;
    public String KarakterAdi;
    public String Sifre;
    public String Yas;
    public int StatTemp;

    public String Meslek;
    public int MeslekXP;
    public int MeslekSV;

    public String MadenciXP1, MadenciSeviye1;

    public String oyuncumadencixp, oyuncudemircixp, oyuncuavcixp, oyuncuoduncuxp;
    public String oyuncumadencisv, oyuncudemircisv, oyuncuavcisv, oyuncuoduncusv;

    public int guc;
    public int bilgelik;
    public int atiklik;
    public int buyu;
    public int dayaniklilik;
    public int karizma;
    public String Irk;
    public String CK_Durum;

    @EventHandler
    public void onMove(PlayerMoveEvent e)
    {
        try
        {
            Player p = e.getPlayer();

            if (getKontrol(Objects.requireNonNull(p.getPlayer()).getName()).equals("hayir"))
            {
                p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 999999999, 999999999));
                p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 99999999, 999999999));

                e.setCancelled(true);
            } else if (getKontrol(p.getPlayer().getName()).equals("evet"))
            {
                p.getActivePotionEffects().clear();
            }


        }
        catch(Exception ex)
        {
            OyuncuKickle(e.getPlayer(), "Sunucu güvenlik koruması aktif edildi. Sistemler kendini korumak için sizi oyundan attı. Batur123 ile iletişime geçin.");
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"[Hata]: onMove"+e.getPlayer().getName());
        }


    }

    @EventHandler
    public void Anvil(InventoryOpenEvent e)
    {
        if (e.getInventory().getType() == InventoryType.ANVIL) {
            e.setCancelled(true);
        }
    }

    /*
     * public void AclikKontrolu(PlayerMoveEvent e) { Player p = e.getPlayer(); }
     */

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent event1)
    {

        try
        {
            Player player = event1.getPlayer();
            Entity entity = event1.getRightClicked();
            if (entity instanceof Player) {

                if (event1.getHand() == EquipmentSlot.HAND)
                {
                    Player clicked = (Player) entity;

                    if (event1.getPlayer().isOp())
                    {

                        int guc = Integer.valueOf(EventsClass.getzarGuc(clicked.getPlayer().getName()));
                        int atiklik  = Integer.valueOf(EventsClass.getzarAtiklik(clicked.getPlayer().getName()));
                        int dayaniklilik  = Integer.valueOf(EventsClass.getzarDayaniklilik(clicked.getPlayer().getName()));
                        int buyu  = Integer.valueOf(EventsClass.getzarBuyu(clicked.getPlayer().getName()));
                        int bilgelik  = Integer.valueOf(EventsClass.getzarBilgelik(clicked.getPlayer().getName()));
                        int karizma  = Integer.valueOf(EventsClass.getzarKarizma(clicked.getPlayer().getName()));


                        int bonuskuvvet = EventsClass.BonusKuvvet(EventsClass.GetIrk(clicked.getPlayer().getName()));
                        int bonusceviklik = EventsClass.BonusCeviklik(EventsClass.GetIrk(clicked.getPlayer().getName()));
                        int bonusday = EventsClass.BonusDay(EventsClass.GetIrk(clicked.getPlayer().getName()));
                        int bonuszeka = EventsClass.BonusZeka(EventsClass.GetIrk(clicked.getPlayer().getName()));
                        int bonusbilgelik = EventsClass.BonusBilgelik(EventsClass.GetIrk(clicked.getPlayer().getName()));
                        int bonuskarizma= EventsClass.BonusKarizma(EventsClass.GetIrk(clicked.getPlayer().getName()));

                        guc = guc+bonuskuvvet;
                        atiklik = atiklik+bonusceviklik;
                        dayaniklilik = dayaniklilik+bonusday;
                        buyu = buyu+ bonuszeka;
                        bilgelik = bilgelik+ bonusbilgelik;
                        karizma = karizma + bonuskarizma;

                        player.sendMessage(ChatColor.GREEN + "_______________________________");
                        player.sendMessage(ChatColor.GREEN + "           [Admin Panel]");
                        player.sendMessage(ChatColor.GOLD + "Adı: " + ChatColor.AQUA + getKarakterAd(clicked.getName()) + "(" + clicked.getName() + ")");
                        player.sendMessage(ChatColor.GOLD + "Yaşı: " + ChatColor.AQUA + getYas(clicked.getName()));
                        player.sendMessage(ChatColor.GOLD + "HP: " + ChatColor.RED + clicked.getHealth() + "/" + clicked.getMaxHealth());
                        player.sendMessage(ChatColor.GOLD + "AC: " + ChatColor.GRAY+EventDosyasi.EventsClass.getAC(clicked.getName()));
                        player.sendMessage(ChatColor.GOLD + "Irk:" + ChatColor.GRAY+EventDosyasi.EventsClass.GetIrk(clicked.getName()));
                        player.sendMessage(ChatColor.GREEN + "_______________________________");
                        player.sendMessage(ChatColor.GREEN + "           [Zarları]");
                        player.sendMessage(ChatColor.GOLD + " Kuvvet: " + ChatColor.AQUA + guc + ChatColor.GOLD + "   Çeviklik: " + ChatColor.AQUA + atiklik + ChatColor.GOLD + "    Dayanıklılık: " + ChatColor.AQUA + dayaniklilik);
                        player.sendMessage(ChatColor.GOLD + " Zeka: " + ChatColor.AQUA + buyu + ChatColor.GOLD + "  Bilgelik: " + ChatColor.AQUA + bilgelik + ChatColor.GOLD + "  Karizma: " + ChatColor.AQUA + karizma);
                        player.sendMessage(ChatColor.GREEN + "_______________________________");
                        player.sendMessage(ChatColor.GREEN + "           [Görünüş]");
                        player.sendMessage(ChatColor.YELLOW + getGorunus(clicked.getName()));
                        player.sendMessage(ChatColor.GREEN + "_______________________________");
                    } else {
                        player.sendMessage(ChatColor.YELLOW + "                       " + getKarakterAd(clicked.getName()) + "(" + clicked.getName() + ")");
                        player.sendMessage(ChatColor.YELLOW + "_______________________[Görünüş]__________________________");
                        player.sendMessage(ChatColor.WHITE + getGorunus(clicked.getName()));
                    }

                }
            }
        }
        catch(Exception ex)
        {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"[Hata]: onPlayerInteract event 1 Satır158"+event1.getPlayer().getName());
        }


    }



/*	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if (GirdiKontrol = false) {
			event.setCancelled(true);
		} else {
			Action action = event.getAction();
			Player player = event.getPlayer();
			Block block = event.getClickedBlock();

			if (action.equals(Action.LEFT_CLICK_BLOCK)) {

				if (block.getType().equals(Material.EMERALD_BLOCK)) {
					if (player.getHealth() != 20.0) {
						player.setHealth(player.getHealth() + 1.0);
						player.sendMessage(ChatColor.RED + "Oyun:" + ChatColor.BLUE + "Bandaj bastï¿½nï¿½z. Mevcut canï¿½nï¿½z:"
								+ (int) player.getHealth());

					} else {
						player.sendMessage(ChatColor.RED + "Oyun:" + ChatColor.BLUE
								+ "Tamamen saï¿½lï¿½ksï¿½nï¿½z. Daha fazla bandaj basamazsï¿½nï¿½z.");
					}

				}
			}
		}

	} */

    @EventHandler
    public void CanYenilenmesiKapatma(EntityRegainHealthEvent e) {

        try
        {
            if(e.getEntity() instanceof Player)
            {
                Player p = (Player) e.getEntity();
                if(getKontrol(Objects.requireNonNull(p.getPlayer()).getName()).equals("hayir"))
                {
                    e.setCancelled(true);
                }
                e.setCancelled(true);
            }


        } catch(Exception ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED +
                    "[Hata]: CanYenilenmesiKapatma'da hata oluştu. Satıt 178"); }


    }


    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {

        if (event instanceof PlayerDeathEvent) {

            Player player = (Player) event.getEntity();

            player.getInventory().clear();
            OyuncuKickle(player, ChatColor.GOLD + "[Flowing Tears] " + ChatColor.RED
                    + "Karakteriniz öldü. Lütfen karakter panelinden karakterinizi tekrardan ayarlayınız.");
            // player.kickPlayer(ChatColor.GOLD+"[Flowing Tears]
            // "+ChatColor.RED+"Karakteriniz ï¿½ldï¿½. Bundan sonra oynamanï¿½z mï¿½mkï¿½n deï¿½il.
            // Lï¿½tfen forumdaki karakter paneline dï¿½nï¿½nï¿½z ve tekrar karakterinizi
            // hazï¿½rlayï¿½nï¿½z.");

            Connection connect = null;
            PreparedStatement pre = null;

            try {

                Class.forName("com.mysql.jdbc.Driver");
                connect = DriverManager
                        .getConnection("jdbc:mysql://localhost:3306/karakter" + "?user=root&password=test");

                String sql = "UPDATE karakter " + "SET KarakterAdi = ? , " + "CK_Durum = ? , Meslek = ? WHERE OyunAdi = ? ";

                pre = connect.prepareStatement(sql);
                pre.setString(1, getKarakterAd(player.getPlayer().getName()));
                pre.setInt(2, 1);
                pre.setString(3, "yok");
                pre.setString(4, player.getPlayer().getName());

                pre.executeUpdate();

                Bukkit.getConsoleSender()
                        .sendMessage(ChatColor.GREEN + "[Bilgi]: " + ChatColor.RED + player.getPlayer().getName()
                                + ChatColor.GREEN + " isimli kişi CK oldu. Güncelleme işlemi yapıldı.");

            } catch (Exception d) {

                d.printStackTrace();
            }

            try {
                if (connect != null) {
                    pre.close();
                    connect.close();
                }
            } catch (SQLException s) {

                s.printStackTrace();
            }
        }
    }

    @EventHandler
    public void OtArastirmaSistemi(BlockBreakEvent e)
    {

        Player p = e.getPlayer();

        if (getKontrol(p.getPlayer().getName()).equals("hayir"))
        {
            e.setCancelled(true);
        } else if (getKontrol(p.getPlayer().getName()).equals("evet")) {

            if (e.getBlock().getType() == Material.GRASS || e.getBlock().getType() == Material.TALL_GRASS
                    || e.getBlock().getType() == Material.FERN || e.getBlock().getType() == Material.LARGE_FERN
                    || e.getBlock().getType() == Material.SEAGRASS
                    || e.getBlock().getType() == Material.TALL_SEAGRASS) {

                double sans = Math.random();

                if (sans >= 0.04636 && sans <= 0.07136) // coal
                {
                    e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                            new ItemStack(Material.BEETROOT_SEEDS, 1));
                    p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                            + "Oyun içinden tohum buldun");

                }
                if (sans >= 0.03135 && sans <= 0.04635) // iron
                {
                    e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                            new ItemStack(Material.MELON_SEEDS, 1));
                    p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                            + "Oyun içinden tohum buldun");
                }
                if (sans >= 0.02134 && sans <= 0.03134) // gold
                {
                    e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                            new ItemStack(Material.WHEAT_SEEDS, 1));
                    p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                            + "Oyun içinden tohum buldun");
                }
                if (sans >= 0.01633 && sans <= 0.02133) // diamond
                {
                    e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                            new ItemStack(Material.PUMPKIN_SEEDS, 1));
                    p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                            + "Oyun içinden tohum buldun");

                }
                if (sans >= 0.01132 && sans <= 0.01632) // redstone
                {
                    e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                            new ItemStack(Material.POISONOUS_POTATO, 2));
                    p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                            + "Otun içinden hafif yeşilleşmiş bir patates buldun.");
                }
                if (sans >= 0.00380 && sans <= 0.00630) // emerald
                {

                    e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                            new ItemStack(Material.SPIDER_SPAWN_EGG, 1));
                    p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                            + "Otun içindeki örümcek yuvasından bir örümcek yumurtası çıkardın.");
                }
                if (sans >= 0 && sans < 0.0020) // ï¿½ok garip bir taï¿½
                {
                    e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.BONE, 1));
                    p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW + "Fosilleşmiş bir kemik buldun.");
                }

            }
        }
    }

    @EventHandler
    public void BalikTutma(PlayerFishEvent e)
    {
        try
        {
            if(e.getCaught().getType() != null)
            {
                e.setCancelled(true);
            }
        }
        catch(Exception ex)
        {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"[Hata]: BalikTutma");
            e.setCancelled(true);
        }

    }


    @SuppressWarnings("deprecation")
    @EventHandler
    public void Madencilik(BlockBreakEvent e) throws IOException {

        Player p = e.getPlayer();
        if (getKontrol(p.getPlayer().getName()).equals("hayir"))
        {
            e.setCancelled(true);
        }
        else if (getKontrol(p.getPlayer().getName()).equals("evet"))
        {


            if(!p.isOp())
            {
                MadenciXP1 = getMeslekXP(e.getPlayer().getName());
                MadenciSeviye1 = getMeslekSeviye(e.getPlayer().getName());
                int MadenciSeviyeInt1 = Integer.valueOf(MadenciSeviye1);


                if (MadenciSeviyeInt1 == 1) {

                    if (e.getBlock().getType() == Material.STONE || e.getBlock().getType() == Material.COAL_ORE
                            || e.getBlock().getType() == Material.DIAMOND_ORE || e.getBlock().getType() == Material.IRON_ORE
                            || e.getBlock().getType() == Material.EMERALD_ORE || e.getBlock().getType() == Material.GOLD_ORE
                            || e.getBlock().getType() == Material.LAPIS_ORE
                            || e.getBlock().getType() == Material.REDSTONE_ORE || e.getBlock().getType() == Material.GRANITE
                            || e.getBlock().getType() == Material.ANDESITE || e.getBlock().getType() == Material.DIORITE) {

                        if(EventDosyasi.EventsClass.getMeslek(p.getPlayer().getName()).equals("Madenci"))
                        {
                            int Temp;
                            // Block Kï¿½rdï¿½kï¿½a Madenci XP'yi arttï¿½rma
                            Temp = Integer.valueOf(MadenciXP1);
                            Temp = Temp + 1;
                            try {
                                setMeslekXP(e.getPlayer().getName(), Temp);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                        else
                        {
                            e.setCancelled(true);
                            p.sendMessage(ChatColor.RED+"[Hata]: "+ChatColor.YELLOW+"Mesleï¿½iniz madenci deï¿½il!");

                        }
                        if (p.getInventory().getItemInHand().getType().equals(Material.DIAMOND_PICKAXE)
                                || p.getInventory().getItemInHand().getType().equals(Material.GOLDEN_PICKAXE)
                                || p.getInventory().getItemInHand().getType().equals(Material.IRON_PICKAXE)
                                || p.getInventory().getItemInHand().getType().equals(Material.STONE_PICKAXE)
                                || p.getInventory().getItemInHand().getType().equals(Material.WOODEN_PICKAXE)

                        ) {

                            ItemStack stackInHand = p.getInventory().getItemInHand();
                            stackInHand.setDurability((short) (stackInHand.getDurability() + 1));

                            if (p.getItemInHand().getType() == Material.DIAMOND_PICKAXE) {
                                if (p.getItemInHand().getDurability() > 1561) {
                                    p.getInventory().removeItem(stackInHand);
                                }

                            }
                            if (p.getItemInHand().getType() == Material.GOLDEN_PICKAXE) {
                                if (p.getItemInHand().getDurability() > 32) {
                                    p.getInventory().removeItem(stackInHand);
                                }
                            }
                            if (p.getItemInHand().getType() == Material.IRON_PICKAXE) {
                                if (p.getItemInHand().getDurability() > 250) {
                                    p.getInventory().removeItem(stackInHand);
                                }
                            }
                            if (p.getItemInHand().getType() == Material.WOODEN_PICKAXE) {
                                if (p.getItemInHand().getDurability() > 59) {
                                    p.getInventory().removeItem(stackInHand);
                                }
                            }
                            if (p.getItemInHand().getType() == Material.STONE_PICKAXE) {
                                if (p.getItemInHand().getDurability() > 131) {
                                    p.getInventory().removeItem(stackInHand);
                                }
                            }

                            p.updateInventory();

                        }



                        // Block Kï¿½rdï¿½kï¿½a Madenci XP'yi arttï¿½rma

                        if (getMeslekXP(e.getPlayer().getName()).equals("3200")) {
                            p.sendMessage(ChatColor.GOLD + "[Bilgi]: " + ChatColor.RED
                                    + "Tebrikler! Damarlarï¿½nda akan o maden coï¿½kusunu hissedebiliyorsun. ");
                            p.sendMessage(ChatColor.YELLOW + "(2.Seviye Madenci Oldun)");
                            setMeslekSeviye(e.getPlayer().getName(), 2);
                        } else if (getMeslekXP(e.getPlayer().getName()).equals("9600")) {
                            p.sendMessage(ChatColor.GOLD + "[Bilgi]: " + ChatColor.RED
                                    + "Tebrikler! Kazdï¿½ï¿½ï¿½n madenlerin seni daha fazla gï¿½ï¿½lï¿½ yaptï¿½ï¿½ï¿½nï¿½ hissetmeye baï¿½ladï¿½n. ");
                            p.sendMessage(ChatColor.YELLOW + "(3.Seviye Madenci Oldun)");
                            setMeslekSeviye(e.getPlayer().getName(), 3);
                        } else if (getMeslekXP(e.getPlayer().getName()).equals("19200")) {
                            p.sendMessage(ChatColor.GOLD + "[Bilgi]: " + ChatColor.RED
                                    + "Etrafï¿½nda garip sesler duymaya baï¿½lï¿½yorsun. Hangi taï¿½ta hangi maden olduï¿½unu gï¿½rebilecek kadar gï¿½ï¿½lendin.");
                            p.sendMessage(ChatColor.YELLOW + "(4.Seviye Madenci Oldun)");
                            setMeslekSeviye(e.getPlayer().getName(), 4);
                        } else if (getMeslekXP(e.getPlayer().getName()).equals("32000")) {
                            p.sendMessage(ChatColor.GOLD + "[Bilgi]: " + ChatColor.RED
                                    + "Karanlï¿½ï¿½ï¿½n gï¿½cï¿½ iï¿½ine doï¿½ru ï¿½ekiliyor. Artï¿½k o kadar ï¿½ok maden iï¿½inde ustalaï¿½tï¿½n ki karanlï¿½kta ï¿½nï¿½nï¿½ gï¿½rebiliyorsun!");
                            p.sendMessage(ChatColor.LIGHT_PURPLE + "(Efsanevi Madenci Oldun)");
                            setMeslekSeviye(e.getPlayer().getName(), 5);
                        }

                    }

                    if (e.getBlock().getType() == Material.COAL_ORE || e.getBlock().getType() == Material.DIAMOND_ORE
                            || e.getBlock().getType() == Material.IRON_ORE || e.getBlock().getType() == Material.EMERALD_ORE
                            || e.getBlock().getType() == Material.GOLD_ORE || e.getBlock().getType() == Material.LAPIS_ORE
                            || e.getBlock().getType() == Material.REDSTONE_ORE

                    ) {
                        if(EventDosyasi.EventsClass.getMeslek(p.getPlayer().getName()).equals("Madenci"))
                        {

                        }
                        else
                        {
                            e.setCancelled(true);
                            p.sendMessage(ChatColor.RED+"[Hata]: "+ChatColor.YELLOW+"Mesleï¿½iniz madenci deï¿½il!");

                        }
                        double sans = Math.random();

                        if (e.getBlock().getType() == Material.COAL_ORE && sans >= 0.4 && sans <= 0.8) // coal
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.COAL, 1));
                            p.sendMessage(
                                    ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW + "Kï¿½mï¿½r madenini baï¿½arï¿½yla kazdï¿½n.");
                            e.getBlock().setType(Material.AIR);

                        } else if (e.getBlock().getType() == Material.IRON_ORE && sans >= 0.01830 && sans <= 0.7) // iron
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.IRON_ORE, 1));
                            p.sendMessage(
                                    ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW + "Demir madenini baï¿½arï¿½yla kazdï¿½n");
                            e.getBlock().setType(Material.AIR);
                        } else if (e.getBlock().getType() == Material.GOLD_ORE && sans >= 0.3 && sans <= 0.2) // gold
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.GOLD_ORE, 1));
                            p.sendMessage(
                                    ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW + "Altï¿½n madenini baï¿½arï¿½yla kazdï¿½n.");
                            e.getBlock().setType(Material.AIR);
                        } else if (e.getBlock().getType() == Material.DIAMOND && sans >= 0.03 && sans <= 0.05) // diamond
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.DIAMOND, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "Elmasa hiï¿½ zarar vermeden ï¿½ï¿½karabildin.");
                            e.getBlock().setType(Material.AIR);
                        } else if (e.getBlock().getType() == Material.REDSTONE && sans >= 0.03 && sans <= 0.05) // redstone
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.REDSTONE, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "Taï¿½ï¿½n iï¿½indeki garip kï¿½zï¿½l toz parï¿½acï¿½klarï¿½nï¿½ ï¿½ï¿½karabildin.");
                            e.getBlock().setType(Material.AIR);
                        } else if (e.getBlock().getType() == Material.LAPIS_LAZULI && sans >= 0.03 && sans <= 0.05) // lapis
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.LAPIS_LAZULI, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "Taï¿½ï¿½n iï¿½indeki gizemli mavi parlak taï¿½ï¿½ ï¿½ï¿½karabildin.");
                            e.getBlock().setType(Material.AIR);
                        } else if (e.getBlock().getType() == Material.EMERALD && sans >= 0.03 && sans <= 0.05) // emerald
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.EMERALD, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "Muhteï¿½em bir parlak yakut ï¿½ï¿½karmayï¿½ baï¿½ardï¿½n.");
                            e.getBlock().setType(Material.AIR);
                        } else {
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.RED
                                    + "Madeni dï¿½zgï¿½n bir ï¿½ekilde ï¿½ï¿½karamadï¿½n.");
                            e.getBlock().setType(Material.AIR);
                        }
                    }

                    if (e.getBlock().getType() == Material.STONE)
                    {


                        double sans = Math.random();

                        if (sans >= 0.3 && sans <= 0.4) // coal
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.COAL, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "ï¿½ok ï¿½anslï¿½sï¿½n! Kazdï¿½ï¿½ï¿½n taï¿½ï¿½n iï¿½inden kï¿½mï¿½r parï¿½acï¿½ï¿½ï¿½ ï¿½ï¿½ktï¿½.");

                        }
                        if (sans >= 0 && sans <= 0.05) // iron
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.IRON_ORE, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "ï¿½ok ï¿½anslï¿½sï¿½n! Kazdï¿½ï¿½ï¿½n taï¿½ï¿½n iï¿½inden iï¿½lenmemiï¿½ demir ï¿½ï¿½ktï¿½.");
                        }
                        if (sans >= 0.00353 && sans <= 0.00503) // gold
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.GOLD_ORE, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "ï¿½ok ï¿½anslï¿½sï¿½n! Kazdï¿½ï¿½ï¿½n taï¿½ï¿½n iï¿½inden iï¿½lenmemiï¿½ altï¿½n ï¿½ï¿½ktï¿½.");
                        }
                        if (sans >= 0.00252 && sans <= 0.00352) // diamond
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.DIAMOND, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "ï¿½ok ï¿½anslï¿½sï¿½n! Kazdï¿½ï¿½ï¿½n taï¿½ï¿½n iï¿½inden pasparlak bir elmas buldun. Bilge Ted parlaklï¿½ï¿½ï¿½nda...");
                        }
                        if (sans >= 0.00151 && sans <= 0.00251) // redstone
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.REDSTONE, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "ï¿½ok ï¿½anslï¿½sï¿½n! Kazdï¿½ï¿½ï¿½n taï¿½ï¿½n iï¿½inden garip gï¿½rï¿½nï¿½mlï¿½ kï¿½zï¿½l bir toz ï¿½ï¿½ktï¿½.");
                        }
                        if (sans >= 0.00051 && sans <= 0.00150) // lapis
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.LAPIS_LAZULI, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "ï¿½ok ï¿½anslï¿½sï¿½n! Kazdï¿½ï¿½ï¿½n taï¿½ï¿½n iï¿½inden gizemli bir mavi taï¿½ ï¿½ï¿½ktï¿½.");
                        }
                        if (sans >= 0 && sans <= 0.00050) // emerald
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.EMERALD, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "ï¿½ok ï¿½anslï¿½sï¿½n! Kazdï¿½ï¿½ï¿½n taï¿½ï¿½n iï¿½inden zï¿½mrï¿½t buldun!");
                        }
                        if (sans >= 0 && sans < 0.000010) // ï¿½ok garip bir taï¿½
                        {
                            ItemStack dimetrium = new ItemStack(Material.NETHER_WART);
                            ItemMeta meta = dimetrium.getItemMeta();
                            meta.setDisplayName(ChatColor.GOLD + "Dimetrium Cevheri");
                            ArrayList<String> lore = new ArrayList<String>();
                            lore.add(ChatColor.LIGHT_PURPLE + "Garip bir cevher..");
                            meta.setLore(lore);
                            dimetrium.setItemMeta(meta);

                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), dimetrium);
                            p.sendMessage(
                                    ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW + "ï¿½ok acayip bir cevher dï¿½ï¿½ï¿½rdï¿½n...");
                        }

                    }

                } else if (MadenciSeviyeInt1 == 2) {

                    if (e.getBlock().getType() == Material.STONE || e.getBlock().getType() == Material.COAL_ORE
                            || e.getBlock().getType() == Material.DIAMOND_ORE || e.getBlock().getType() == Material.IRON_ORE
                            || e.getBlock().getType() == Material.EMERALD_ORE || e.getBlock().getType() == Material.GOLD_ORE
                            || e.getBlock().getType() == Material.LAPIS_ORE
                            || e.getBlock().getType() == Material.REDSTONE_ORE || e.getBlock().getType() == Material.GRANITE
                            || e.getBlock().getType() == Material.ANDESITE || e.getBlock().getType() == Material.DIORITE) {
                        if(EventDosyasi.EventsClass.getMeslek(p.getPlayer().getName()).equals("Madenci"))
                        {

                        }
                        else
                        {
                            e.setCancelled(true);
                            p.sendMessage(ChatColor.RED+"[Hata]: "+ChatColor.YELLOW+"Mesleï¿½iniz madenci deï¿½il!");

                        }

                        if (p.getInventory().getItemInHand().getType().equals(Material.DIAMOND_PICKAXE)
                                || p.getInventory().getItemInHand().getType().equals(Material.GOLDEN_PICKAXE)
                                || p.getInventory().getItemInHand().getType().equals(Material.IRON_PICKAXE)
                                || p.getInventory().getItemInHand().getType().equals(Material.STONE_PICKAXE)
                                || p.getInventory().getItemInHand().getType().equals(Material.WOODEN_PICKAXE)

                        ) {

                            ItemStack stackInHand = p.getInventory().getItemInHand();
                            stackInHand.setDurability((short) (stackInHand.getDurability() + 1));

                            if (p.getItemInHand().getType() == Material.DIAMOND_PICKAXE) {
                                if (p.getItemInHand().getDurability() > 1561) {
                                    p.getInventory().removeItem(stackInHand);
                                }

                            }
                            if (p.getItemInHand().getType() == Material.GOLDEN_PICKAXE) {
                                if (p.getItemInHand().getDurability() > 32) {
                                    p.getInventory().removeItem(stackInHand);
                                }
                            }
                            if (p.getItemInHand().getType() == Material.IRON_PICKAXE) {
                                if (p.getItemInHand().getDurability() > 250) {
                                    p.getInventory().removeItem(stackInHand);
                                }
                            }
                            if (p.getItemInHand().getType() == Material.WOODEN_PICKAXE) {
                                if (p.getItemInHand().getDurability() > 59) {
                                    p.getInventory().removeItem(stackInHand);
                                }
                            }
                            if (p.getItemInHand().getType() == Material.STONE_PICKAXE) {
                                if (p.getItemInHand().getDurability() > 131) {
                                    p.getInventory().removeItem(stackInHand);
                                }
                            }

                            p.updateInventory();

                        }

                        if(EventDosyasi.EventsClass.getMeslek(p.getPlayer().getName()).equals("Madenci"))
                        {
                            int Temp;
                            // Block Kï¿½rdï¿½kï¿½a Madenci XP'yi arttï¿½rma
                            Temp = Integer.valueOf(MadenciXP1);
                            Temp = Temp + 1;
                            try {
                                setMeslekXP(e.getPlayer().getName(), Temp);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }


                        // Block Kï¿½rdï¿½kï¿½a Madenci XP'yi arttï¿½rma

                        if (getMeslekXP(e.getPlayer().getName()).equals("3200")) {
                            p.sendMessage(ChatColor.GOLD + "[Bilgi]: " + ChatColor.RED
                                    + "Tebrikler! Damarlarï¿½nda akan o maden coï¿½kusunu hissedebiliyorsun. " + ChatColor.GREEN
                                    + "(2.Seviye Madenci Oldun)");
                            setMeslekSeviye(e.getPlayer().getName(), 2);
                        } else if (getMeslekXP(e.getPlayer().getName()).equals("9600")) {
                            p.sendMessage(ChatColor.GOLD + "[Bilgi]: " + ChatColor.RED
                                    + "Tebrikler! Kazdï¿½ï¿½ï¿½n madenlerin seni daha fazla gï¿½ï¿½lï¿½ yaptï¿½ï¿½ï¿½nï¿½ hissetmeye baï¿½ladï¿½n. "
                                    + ChatColor.BLUE + "(3.Seviye Madenci Oldun)");
                            setMeslekSeviye(e.getPlayer().getName(), 3);
                        } else if (getMeslekXP(e.getPlayer().getName()).equals("19200")) {
                            p.sendMessage(ChatColor.GOLD + "[Bilgi]: " + ChatColor.RED
                                    + "Etrafï¿½nda garip sesler duymaya baï¿½lï¿½yorsun. Hangi taï¿½ta hangi maden olduï¿½unu gï¿½rebilecek kadar gï¿½ï¿½lendin. "
                                    + ChatColor.YELLOW + "(4.Seviye Madenci Oldun)");
                            setMeslekSeviye(e.getPlayer().getName(), 4);
                        } else if (getMeslekXP(e.getPlayer().getName()).equals("32000")) {
                            p.sendMessage(ChatColor.GOLD + "[Bilgi]: " + ChatColor.RED
                                    + "Karanlï¿½ï¿½ï¿½n gï¿½cï¿½ iï¿½ine doï¿½ru ï¿½ekiliyor. Artï¿½k o kadar ï¿½ok maden iï¿½inde ustalaï¿½tï¿½n ki karanlï¿½kta ï¿½nï¿½nï¿½ gï¿½rebiliyorsun! "
                                    + ChatColor.LIGHT_PURPLE + "(Efsanevi Madenci Oldun)");
                            setMeslekSeviye(e.getPlayer().getName(), 5);
                        }

                    }
                    // Madencinin XP almasï¿½ iï¿½in kï¿½rï¿½lmasï¿½ gereken materyaller

                    // MADENCï¿½Nï¿½N ORJï¿½NAL ORE LARI KAZMASI
                    if (e.getBlock().getType() == Material.COAL_ORE || e.getBlock().getType() == Material.DIAMOND_ORE
                            || e.getBlock().getType() == Material.IRON_ORE || e.getBlock().getType() == Material.EMERALD_ORE
                            || e.getBlock().getType() == Material.GOLD_ORE || e.getBlock().getType() == Material.LAPIS_ORE
                            || e.getBlock().getType() == Material.REDSTONE_ORE

                    ) {
                        if(EventDosyasi.EventsClass.getMeslek(p.getPlayer().getName()).equals("Madenci"))
                        {

                        }
                        else
                        {
                            e.setCancelled(true);
                            p.sendMessage(ChatColor.RED+"[Hata]: "+ChatColor.YELLOW+"Mesleï¿½iniz madenci deï¿½il!");


                        }
                        double sans = Math.random();

                        if (e.getBlock().getType() == Material.COAL_ORE && sans >= 0.3 && sans <= 0.9) // coal
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.COAL, 1));
                            p.sendMessage(
                                    ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW + "Kï¿½mï¿½r madenini baï¿½arï¿½yla kazdï¿½n.");
                            e.getBlock().setType(Material.AIR);

                        } else if (e.getBlock().getType() == Material.IRON_ORE && sans >= 0.3 && sans <= 0.9) // iron
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.IRON_ORE, 1));
                            p.sendMessage(
                                    ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW + "Demir madenini baï¿½arï¿½yla kazdï¿½n");
                            e.getBlock().setType(Material.AIR);
                        } else if (e.getBlock().getType() == Material.GOLD_ORE && sans >= 0.3 && sans <= 0.9) // gold
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.GOLD_ORE, 1));
                            p.sendMessage(
                                    ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW + "Altï¿½n madenini baï¿½arï¿½yla kazdï¿½n.");
                            e.getBlock().setType(Material.AIR);
                        } else if (e.getBlock().getType() == Material.DIAMOND && sans >= 0.1 && sans <= 0.3) // diamond
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.DIAMOND, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "Elmasa hiï¿½ zarar vermeden ï¿½ï¿½karabildin.");
                            e.getBlock().setType(Material.AIR);
                        } else if (e.getBlock().getType() == Material.REDSTONE && sans >= 0.1 && sans <= 0.37) // redstone
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.REDSTONE, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "Taï¿½ï¿½n iï¿½indeki garip kï¿½zï¿½l toz parï¿½acï¿½klarï¿½nï¿½ ï¿½ï¿½karabildin.");
                            e.getBlock().setType(Material.AIR);
                        } else if (e.getBlock().getType() == Material.LAPIS_LAZULI && sans >= 0.1 && sans <= 0.3) // lapis
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.LAPIS_LAZULI, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "Taï¿½ï¿½n iï¿½indeki gizemli mavi parlak taï¿½ï¿½ ï¿½ï¿½karabildin.");
                            e.getBlock().setType(Material.AIR);
                        } else if (e.getBlock().getType() == Material.EMERALD && sans >= 0.1 && sans <= 0.3) // emerald
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.EMERALD, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "Muhteï¿½em bir parlak yakut ï¿½ï¿½karmayï¿½ baï¿½ardï¿½n.");
                            e.getBlock().setType(Material.AIR);
                        } else {
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.RED
                                    + "Madeni dï¿½zgï¿½n bir ï¿½ekilde ï¿½ï¿½karamadï¿½n.");
                            e.getBlock().setType(Material.AIR);
                        }
                    }
                    // MADENCï¿½Nï¿½N ORJï¿½NAL ORE LARI KAZMASI

                    // Eï¿½er Madenci Taï¿½ Kï¿½rarsa Dï¿½ï¿½ecek Eï¿½yalarï¿½n Yï¿½zdelik Kodlarï¿½ ->
                    // Iron,Coal,Gold,Diamond,Redstone,Emerald,Lapis Oranlarï¿½ aï¿½aï¿½ï¿½da verildi.
                    if (e.getBlock().getType() == Material.STONE) {



                        double sans = Math.random();

                        if (sans >= 0.5 && sans <= 0.7) // coal
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.COAL, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "ï¿½ok ï¿½anslï¿½sï¿½n! Kazdï¿½ï¿½ï¿½n taï¿½ï¿½n iï¿½inden kï¿½mï¿½r parï¿½acï¿½ï¿½ï¿½ ï¿½ï¿½ktï¿½.");

                        }
                        if (sans >= 0 && sans <= 0.07) // iron
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.IRON_ORE, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "ï¿½ok ï¿½anslï¿½sï¿½n! Kazdï¿½ï¿½ï¿½n taï¿½ï¿½n iï¿½inden iï¿½lenmemiï¿½ demir ï¿½ï¿½ktï¿½.");
                        }
                        if (sans >= 0.02250 && sans <= 0.03500) // gold
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.GOLD_ORE, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "ï¿½ok ï¿½anslï¿½sï¿½n! Kazdï¿½ï¿½ï¿½n taï¿½ï¿½n iï¿½inden iï¿½lenmemiï¿½ altï¿½n ï¿½ï¿½ktï¿½.");
                        }
                        if (sans >= 0.01000 && sans <= 0.02000) // diamond
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.DIAMOND, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "ï¿½ok ï¿½anslï¿½sï¿½n! Kazdï¿½ï¿½ï¿½n taï¿½ï¿½n iï¿½inden pasparlak bir elmas buldun. Bilge Ted parlaklï¿½ï¿½ï¿½nda...");
                        }
                        if (sans >= 0.00325 && sans <= 0.00585) // redstone
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.REDSTONE, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "ï¿½ok ï¿½anslï¿½sï¿½n! Kazdï¿½ï¿½ï¿½n taï¿½ï¿½n iï¿½inden garip gï¿½rï¿½nï¿½mlï¿½ kï¿½zï¿½l bir toz ï¿½ï¿½ktï¿½.");
                        }
                        if (sans >= 0.00160 && sans <= 0.00300) // lapis
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.LAPIS_LAZULI, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "ï¿½ok ï¿½anslï¿½sï¿½n! Kazdï¿½ï¿½ï¿½n taï¿½ï¿½n iï¿½inden gizemli bir mavi taï¿½ ï¿½ï¿½ktï¿½.");
                        }
                        if (sans >= 0.00011 && sans <= 0.00150) // emerald
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.EMERALD, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "ï¿½ok ï¿½anslï¿½sï¿½n! Kazdï¿½ï¿½ï¿½n taï¿½ï¿½n iï¿½inden zï¿½mrï¿½t buldun!");
                        }
                        if (sans >= 0 && sans < 0.000020) // ï¿½ok garip bir taï¿½
                        {
                            ItemStack dimetrium = new ItemStack(Material.NETHER_WART);
                            ItemMeta meta = dimetrium.getItemMeta();
                            meta.setDisplayName(ChatColor.GOLD + "Dimetrium Cevheri");
                            ArrayList<String> lore = new ArrayList<String>();
                            lore.add(ChatColor.LIGHT_PURPLE + "Garip bir cevher..");
                            meta.setLore(lore);
                            dimetrium.setItemMeta(meta);

                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), dimetrium);
                            p.sendMessage(
                                    ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW + "ï¿½ok acayip bir cevher dï¿½ï¿½ï¿½rdï¿½n...");
                        }

                    }

                } else if (MadenciSeviyeInt1 == 3) {

                    if (e.getBlock().getType() == Material.STONE || e.getBlock().getType() == Material.COAL_ORE
                            || e.getBlock().getType() == Material.DIAMOND_ORE || e.getBlock().getType() == Material.IRON_ORE
                            || e.getBlock().getType() == Material.EMERALD_ORE || e.getBlock().getType() == Material.GOLD_ORE
                            || e.getBlock().getType() == Material.LAPIS_ORE
                            || e.getBlock().getType() == Material.REDSTONE_ORE || e.getBlock().getType() == Material.GRANITE
                            || e.getBlock().getType() == Material.ANDESITE || e.getBlock().getType() == Material.DIORITE) {
                        if(EventDosyasi.EventsClass.getMeslek(p.getPlayer().getName()).equals("Madenci"))
                        {

                        }
                        else
                        {
                            e.setCancelled(true);
                            p.sendMessage(ChatColor.RED+"[Hata]: "+ChatColor.YELLOW+"Mesleï¿½iniz madenci deï¿½il!");

                        }

                        if (p.getInventory().getItemInHand().getType().equals(Material.DIAMOND_PICKAXE)
                                || p.getInventory().getItemInHand().getType().equals(Material.GOLDEN_PICKAXE)
                                || p.getInventory().getItemInHand().getType().equals(Material.IRON_PICKAXE)
                                || p.getInventory().getItemInHand().getType().equals(Material.STONE_PICKAXE)
                                || p.getInventory().getItemInHand().getType().equals(Material.WOODEN_PICKAXE)

                        ) {

                            ItemStack stackInHand = p.getInventory().getItemInHand();
                            stackInHand.setDurability((short) (stackInHand.getDurability() + 1));

                            if (p.getItemInHand().getType() == Material.DIAMOND_PICKAXE) {
                                if (p.getItemInHand().getDurability() > 1561) {
                                    p.getInventory().removeItem(stackInHand);
                                }

                            }
                            if (p.getItemInHand().getType() == Material.GOLDEN_PICKAXE) {
                                if (p.getItemInHand().getDurability() > 32) {
                                    p.getInventory().removeItem(stackInHand);
                                }
                            }
                            if (p.getItemInHand().getType() == Material.IRON_PICKAXE) {
                                if (p.getItemInHand().getDurability() > 250) {
                                    p.getInventory().removeItem(stackInHand);
                                }
                            }
                            if (p.getItemInHand().getType() == Material.WOODEN_PICKAXE) {
                                if (p.getItemInHand().getDurability() > 59) {
                                    p.getInventory().removeItem(stackInHand);
                                }
                            }
                            if (p.getItemInHand().getType() == Material.STONE_PICKAXE) {
                                if (p.getItemInHand().getDurability() > 131) {
                                    p.getInventory().removeItem(stackInHand);
                                }
                            }

                            p.updateInventory();

                        }
                        if(EventDosyasi.EventsClass.getMeslek(p.getPlayer().getName()).equals("Madenci"))
                        {
                            int Temp;
                            // Block Kï¿½rdï¿½kï¿½a Madenci XP'yi arttï¿½rma
                            Temp = Integer.valueOf(MadenciXP1);
                            Temp = Temp + 1;
                            try {
                                setMeslekXP(e.getPlayer().getName(), Temp);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }


                        // Block Kï¿½rdï¿½kï¿½a Madenci XP'yi arttï¿½rma

                        switch (getMeslekXP(e.getPlayer().getName())) {
                            case "3200":
                                p.sendMessage(ChatColor.GOLD + "[Bilgi]: " + ChatColor.RED
                                        + "Tebrikler! Damarlarï¿½nda akan o maden coï¿½kusunu hissedebiliyorsun. " + ChatColor.GREEN
                                        + "(2.Seviye Madenci Oldun)");
                                setMeslekSeviye(e.getPlayer().getName(), 2);
                                break;
                            case "9600":
                                p.sendMessage(ChatColor.GOLD + "[Bilgi]: " + ChatColor.RED
                                        + "Tebrikler! Kazdï¿½ï¿½ï¿½n madenlerin seni daha fazla gï¿½ï¿½lï¿½ yaptï¿½ï¿½ï¿½nï¿½ hissetmeye baï¿½ladï¿½n. "
                                        + ChatColor.BLUE + "(3.Seviye Madenci Oldun)");
                                setMeslekSeviye(e.getPlayer().getName(), 3);
                                break;
                            case "19200":
                                p.sendMessage(ChatColor.GOLD + "[Bilgi]: " + ChatColor.RED
                                        + "Etrafï¿½nda garip sesler duymaya baï¿½lï¿½yorsun. Hangi taï¿½ta hangi maden olduï¿½unu gï¿½rebilecek kadar gï¿½ï¿½lendin. "
                                        + ChatColor.YELLOW + "(4.Seviye Madenci Oldun)");
                                setMeslekSeviye(e.getPlayer().getName(), 4);
                                break;
                            case "32000":
                                p.sendMessage(ChatColor.GOLD + "[Bilgi]: " + ChatColor.RED
                                        + "Karanlï¿½ï¿½ï¿½n gï¿½cï¿½ iï¿½ine doï¿½ru ï¿½ekiliyor. Artï¿½k o kadar ï¿½ok maden iï¿½inde ustalaï¿½tï¿½n ki karanlï¿½kta ï¿½nï¿½nï¿½ gï¿½rebiliyorsun! "
                                        + ChatColor.LIGHT_PURPLE + "(Efsanevi Madenci Oldun)");
                                setMeslekSeviye(e.getPlayer().getName(), 5);
                                break;
                        }

                    }

                    if (e.getBlock().getType() == Material.COAL_ORE || e.getBlock().getType() == Material.DIAMOND_ORE
                            || e.getBlock().getType() == Material.IRON_ORE || e.getBlock().getType() == Material.EMERALD_ORE
                            || e.getBlock().getType() == Material.GOLD_ORE || e.getBlock().getType() == Material.LAPIS_ORE
                            || e.getBlock().getType() == Material.REDSTONE_ORE

                    ) {
                        if(EventDosyasi.EventsClass.getMeslek(p.getPlayer().getName()).equals("Madenci"))
                        {

                        }
                        else
                        {
                            e.setCancelled(true);
                            p.sendMessage(ChatColor.RED+"[Hata]: "+ChatColor.YELLOW+"Mesleï¿½iniz madenci deï¿½il!");

                        }
                        double sans = Math.random();

                        if (e.getBlock().getType() == Material.COAL_ORE && sans >= 0.01230 && sans <= 0.01830) // coal
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.COAL, 1));
                            p.sendMessage(
                                    ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW + "Kï¿½mï¿½r madenini baï¿½arï¿½yla kazdï¿½n.");
                            e.getBlock().setType(Material.AIR);

                        } else if (e.getBlock().getType() == Material.IRON_ORE && sans >= 0.01230 && sans <= 0.01830) // iron
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.IRON_ORE, 1));
                            p.sendMessage(
                                    ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW + "Demir madenini baï¿½arï¿½yla kazdï¿½n");
                            e.getBlock().setType(Material.AIR);
                        } else if (e.getBlock().getType() == Material.GOLD_ORE && sans >= 0 && sans <= 1) // gold
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.GOLD_ORE, 1));
                            p.sendMessage(
                                    ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW + "Altï¿½n madenini baï¿½arï¿½yla kazdï¿½n.");
                            e.getBlock().setType(Material.AIR);
                        } else if (e.getBlock().getType() == Material.DIAMOND && sans >= 0 && sans <= 1) // diamond
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.DIAMOND, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "Elmasa hiï¿½ zarar vermeden ï¿½ï¿½karabildin.");
                            e.getBlock().setType(Material.AIR);
                        } else if (e.getBlock().getType() == Material.REDSTONE && sans >= 0 && sans <= 1) // redstone
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.REDSTONE, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "Taï¿½ï¿½n iï¿½indeki garip kï¿½zï¿½l toz parï¿½acï¿½klarï¿½nï¿½ ï¿½ï¿½karabildin.");
                            e.getBlock().setType(Material.AIR);
                        } else if (e.getBlock().getType() == Material.LAPIS_LAZULI && sans >= 0 && sans <= 1) // lapis
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.LAPIS_LAZULI, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "Taï¿½ï¿½n iï¿½indeki gizemli mavi parlak taï¿½ï¿½ ï¿½ï¿½karabildin.");
                            e.getBlock().setType(Material.AIR);
                        } else if (e.getBlock().getType() == Material.EMERALD && sans >= 0 && sans <= 1) // emerald
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.EMERALD, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "MuhteÅŸem bir parlak yakut ï¿½ï¿½karmayï¿½ baï¿½ardï¿½n.");
                            e.getBlock().setType(Material.AIR);
                        } else {
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.RED
                                    + "Madeni dï¿½zgï¿½n bir ï¿½ekilde ï¿½ï¿½karamadï¿½n.");
                            e.getBlock().setType(Material.AIR);
                        }
                    }

                    if (e.getBlock().getType() == Material.STONE) {


                        double sans = Math.random();

                        if (sans >= 0.4 && sans <= 0.7) // coal
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.COAL, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "ï¿½ok ï¿½anslï¿½sï¿½n! Kazdï¿½ï¿½ï¿½n taï¿½ï¿½n iï¿½inden kï¿½mï¿½r parï¿½acï¿½ï¿½ï¿½ ï¿½ï¿½ktï¿½.");

                        }
                        if(sans >= 0 && sans <= 0.05) // iron
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.IRON_ORE, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "ï¿½ok ï¿½anslï¿½sï¿½n! Kazdï¿½ï¿½ï¿½n taï¿½ï¿½n iï¿½inden iï¿½lenmemiï¿½ demir ï¿½ï¿½ktï¿½.");
                        }
                        if (sans >= 0.00829 && sans <= 0.01229) // gold
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.GOLD_ORE, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "ï¿½ok ï¿½anslï¿½sï¿½n! Kazdï¿½ï¿½ï¿½n taï¿½ï¿½n iï¿½inden iï¿½lenmemiï¿½ altï¿½n ï¿½ï¿½ktï¿½.");
                        }
                        if (sans >= 0.00628 && sans <= 0.00828) // diamond
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.DIAMOND, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "ï¿½ok ï¿½anslï¿½sï¿½n! Kazdï¿½ï¿½ï¿½n taï¿½ï¿½n iï¿½inden pasparlak bir elmas buldun. Bilge Ted parlaklï¿½ï¿½ï¿½nda...");
                        }
                        if (sans >= 0.00427 && sans <= 0.00627) // redstone
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.REDSTONE, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "ï¿½ok ï¿½anslï¿½sï¿½n! Kazdï¿½ï¿½ï¿½n taï¿½ï¿½n iï¿½inden garip gï¿½rï¿½nï¿½mlï¿½ kï¿½zï¿½l bir toz ï¿½ï¿½ktï¿½.");
                        }
                        if (sans >= 0.00226 && sans <= 0.00426) // lapis
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.LAPIS_LAZULI, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "ï¿½ok ï¿½anslï¿½sï¿½n! Kazdï¿½ï¿½ï¿½n taï¿½ï¿½n iï¿½inden gizemli bir mavi taï¿½ ï¿½ï¿½ktï¿½.");
                        }
                        if (sans >= 0.00125 && sans <= 0.00225) // emerald
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.EMERALD, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "ï¿½ok ï¿½anslï¿½sï¿½n! Kazdï¿½ï¿½ï¿½n taï¿½ï¿½n iï¿½inden zï¿½mrï¿½t buldun!");
                        }
                        if (sans >= 0 && sans < 0.000030) // ï¿½ok garip bir taï¿½
                        {
                            ItemStack dimetrium = new ItemStack(Material.NETHER_WART);
                            ItemMeta meta = dimetrium.getItemMeta();
                            meta.setDisplayName(ChatColor.GOLD + "Dimetrium Cevheri");
                            ArrayList<String> lore = new ArrayList<String>();
                            lore.add(ChatColor.LIGHT_PURPLE + "Garip bir cevher..");
                            meta.setLore(lore);
                            dimetrium.setItemMeta(meta);

                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), dimetrium);
                            p.sendMessage(
                                    ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW + "ï¿½ok acayip bir cevher dï¿½ï¿½ï¿½rdï¿½n...");
                        }
                    }

                } else if (MadenciSeviyeInt1 == 4) {

                    if (e.getBlock().getType() == Material.STONE || e.getBlock().getType() == Material.COAL_ORE
                            || e.getBlock().getType() == Material.DIAMOND_ORE || e.getBlock().getType() == Material.IRON_ORE
                            || e.getBlock().getType() == Material.EMERALD_ORE || e.getBlock().getType() == Material.GOLD_ORE
                            || e.getBlock().getType() == Material.LAPIS_ORE
                            || e.getBlock().getType() == Material.REDSTONE_ORE || e.getBlock().getType() == Material.GRANITE
                            || e.getBlock().getType() == Material.ANDESITE || e.getBlock().getType() == Material.DIORITE) {

                        if(!EventDosyasi.EventsClass.getMeslek(p.getPlayer().getName()).equals("Madenci"))
                        {
                            e.setCancelled(true);
                            p.sendMessage(ChatColor.RED+"[Hata]: "+ChatColor.YELLOW+"Mesleğiniz madenci değil!");
                        }


                        if (p.getInventory().getItemInHand().getType().equals(Material.DIAMOND_PICKAXE)
                                || p.getInventory().getItemInHand().getType().equals(Material.GOLDEN_PICKAXE)
                                || p.getInventory().getItemInHand().getType().equals(Material.IRON_PICKAXE)
                                || p.getInventory().getItemInHand().getType().equals(Material.STONE_PICKAXE)
                                || p.getInventory().getItemInHand().getType().equals(Material.WOODEN_PICKAXE)

                        ) {

                            ItemStack stackInHand = p.getInventory().getItemInHand();
                            stackInHand.setDurability((short) (stackInHand.getDurability() + 1));

                            if (p.getItemInHand().getType() == Material.DIAMOND_PICKAXE) {
                                if (p.getItemInHand().getDurability() > 1561) {
                                    p.getInventory().removeItem(stackInHand);
                                }

                            }
                            if (p.getItemInHand().getType() == Material.GOLDEN_PICKAXE) {
                                if (p.getItemInHand().getDurability() > 32) {
                                    p.getInventory().removeItem(stackInHand);
                                }
                            }
                            if (p.getItemInHand().getType() == Material.IRON_PICKAXE) {
                                if (p.getItemInHand().getDurability() > 250) {
                                    p.getInventory().removeItem(stackInHand);
                                }
                            }
                            if (p.getItemInHand().getType() == Material.WOODEN_PICKAXE) {
                                if (p.getItemInHand().getDurability() > 59) {
                                    p.getInventory().removeItem(stackInHand);
                                }
                            }
                            if (p.getItemInHand().getType() == Material.STONE_PICKAXE) {
                                if (p.getItemInHand().getDurability() > 131) {
                                    p.getInventory().removeItem(stackInHand);
                                }
                            }

                            p.updateInventory();

                        }

                        if(EventDosyasi.EventsClass.getMeslek(p.getPlayer().getName()).equals("Madenci"))
                        {
                            int Temp;
                            // Block Kï¿½rdï¿½kï¿½a Madenci XP'yi arttï¿½rma
                            Temp = Integer.valueOf(MadenciXP1);
                            Temp = Temp + 1;
                            setMeslekXP(e.getPlayer().getName(), Temp);
                        }

                        switch (getMeslekXP(e.getPlayer().getName())) {
                            case "3200":
                                p.sendMessage(ChatColor.GOLD + "[Bilgi]: " + ChatColor.RED
                                        + "Tebrikler! Damarlarï¿½nda akan o maden coï¿½kusunu hissedebiliyorsun. " + ChatColor.GREEN
                                        + "(2.Seviye Madenci Oldun)");
                                setMeslekSeviye(e.getPlayer().getName(), 2);
                                break;
                            case "9600":
                                p.sendMessage(ChatColor.GOLD + "[Bilgi]: " + ChatColor.RED
                                        + "Tebrikler! Kazdï¿½ï¿½ï¿½n madenlerin seni daha fazla gï¿½ï¿½lï¿½ yaptï¿½ï¿½ï¿½nï¿½ hissetmeye baï¿½ladï¿½n. "
                                        + ChatColor.BLUE + "(3.Seviye Madenci Oldun)");
                                setMeslekSeviye(e.getPlayer().getName(), 3);
                                break;
                            case "19200":
                                p.sendMessage(ChatColor.GOLD + "[Bilgi]: " + ChatColor.RED
                                        + "Etrafï¿½nda garip sesler duymaya baï¿½lï¿½yorsun. Hangi taï¿½ta hangi maden olduï¿½unu gï¿½rebilecek kadar gï¿½ï¿½lendin. "
                                        + ChatColor.YELLOW + "(4.Seviye Madenci Oldun)");
                                setMeslekSeviye(e.getPlayer().getName(), 4);
                                break;
                            case "32000":
                                p.sendMessage(ChatColor.GOLD + "[Bilgi]: " + ChatColor.RED
                                        + "Karanlï¿½ï¿½ï¿½n gï¿½cï¿½ iï¿½ine doï¿½ru ï¿½ekiliyor. Artï¿½k o kadar ï¿½ok maden iï¿½inde ustalaï¿½tï¿½n ki karanlï¿½kta ï¿½nï¿½nï¿½ gï¿½rebiliyorsun! "
                                        + ChatColor.LIGHT_PURPLE + "(Efsanevi Madenci Oldun)");
                                setMeslekSeviye(e.getPlayer().getName(), 5);
                                break;
                        }

                    }
                    // Madencinin XP almasï¿½ iï¿½in kï¿½rï¿½lmasï¿½ gereken materyaller

                    // MADENCï¿½Nï¿½N ORJï¿½NAL ORE LARI KAZMASI
                    if (e.getBlock().getType() == Material.COAL_ORE || e.getBlock().getType() == Material.DIAMOND_ORE
                            || e.getBlock().getType() == Material.IRON_ORE || e.getBlock().getType() == Material.EMERALD_ORE
                            || e.getBlock().getType() == Material.GOLD_ORE || e.getBlock().getType() == Material.LAPIS_ORE
                            || e.getBlock().getType() == Material.REDSTONE_ORE

                    ) {
                        if(!EventDosyasi.EventsClass.getMeslek(p.getPlayer().getName()).equals("Madenci"))
                        {
                            e.setCancelled(true);
                            p.sendMessage(ChatColor.RED+"[Hata]: "+ChatColor.YELLOW+"Mesleï¿½iniz madenci deï¿½il!");
                        }

                        double sans = Math.random();

                        if (e.getBlock().getType() == Material.COAL_ORE && sans >= 0 && sans <= 1) // coal
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.COAL, 1));
                            p.sendMessage(
                                    ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW + "Kï¿½mï¿½r madenini baï¿½arï¿½yla kazdï¿½n.");
                            e.getBlock().setType(Material.AIR);

                        } else if (e.getBlock().getType() == Material.IRON_ORE && sans >= 0 && sans <= 1) // iron
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.IRON_ORE, 1));
                            p.sendMessage(
                                    ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW + "Demir madenini baï¿½arï¿½yla kazdï¿½n");
                            e.getBlock().setType(Material.AIR);
                        } else if (e.getBlock().getType() == Material.GOLD_ORE && sans >= 0 && sans <= 1) // gold
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.GOLD_ORE, 1));
                            p.sendMessage(
                                    ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW + "Altï¿½n madenini baï¿½arï¿½yla kazdï¿½n.");
                            e.getBlock().setType(Material.AIR);
                        } else if (e.getBlock().getType() == Material.DIAMOND && sans >= 0 && sans <= 1) // diamond
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.DIAMOND, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "Elmasa hiï¿½ zarar vermeden ï¿½ï¿½karabildin.");
                            e.getBlock().setType(Material.AIR);
                        } else if (e.getBlock().getType() == Material.REDSTONE && sans >= 0 && sans <= 1) // redstone
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.REDSTONE, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "Taï¿½ï¿½n iï¿½indeki garip kï¿½zï¿½l toz parï¿½acï¿½klarï¿½nï¿½ ï¿½ï¿½karabildin.");
                            e.getBlock().setType(Material.AIR);
                        } else if (e.getBlock().getType() == Material.LAPIS_LAZULI && sans >= 0 && sans <= 1) // lapis
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.LAPIS_LAZULI, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "Taï¿½ï¿½n iï¿½indeki gizemli mavi parlak taï¿½ï¿½ ï¿½ï¿½karabildin.");
                            e.getBlock().setType(Material.AIR);
                        } else if (e.getBlock().getType() == Material.EMERALD && sans >= 0 && sans <= 1) // emerald
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.EMERALD, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "Muhteï¿½em bir parlak yakut ï¿½ï¿½karmayï¿½ baï¿½ardï¿½n.");
                            e.getBlock().setType(Material.AIR);
                        } else {
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.RED
                                    + "Madeni dï¿½zgï¿½n bir ï¿½ekilde ï¿½ï¿½karamadï¿½n.");
                            e.getBlock().setType(Material.AIR);
                        }
                    }
                    // MADENCï¿½Nï¿½N ORJï¿½NAL ORE LARI KAZMASI

                    // Eï¿½er Madenci Taï¿½ Kï¿½rarsa Dï¿½ï¿½ecek Eï¿½yalarï¿½n Yï¿½zdelik Kodlarï¿½ ->
                    // Iron,Coal,Gold,Diamond,Redstone,Emerald,Lapis Oranlarï¿½ aï¿½aï¿½ï¿½da verildi.
                    if (e.getBlock().getType() == Material.STONE) {

                        if(!EventDosyasi.EventsClass.getMeslek(p.getPlayer().getName()).equals("Madenci"))
                        {
                            e.setCancelled(true);
                            p.sendMessage(ChatColor.RED+"[Hata]: "+ChatColor.YELLOW+"Mesleï¿½iniz madenci deï¿½il!");
                        }

                        double sans = Math.random();

                        if (sans >= 0.2 && sans <= 0.9) // coal
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.COAL, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "ï¿½ok ï¿½anslï¿½sï¿½n! Kazdï¿½ï¿½ï¿½n taï¿½ï¿½n iï¿½inden kï¿½mï¿½r parï¿½acï¿½ï¿½ï¿½ ï¿½ï¿½ktï¿½.");

                        }
                        if( sans >= 0 && sans <= 0.03) // iron
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.IRON_ORE, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "ï¿½ok ï¿½anslï¿½sï¿½n! Kazdï¿½ï¿½ï¿½n taï¿½ï¿½n iï¿½inden iï¿½lenmemiï¿½ demir ï¿½ï¿½ktï¿½.");
                        }
                        if (sans >= 0.01284 && sans <= 0.01884) // gold
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.GOLD_ORE, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "ï¿½ok ï¿½anslï¿½sï¿½n! Kazdï¿½ï¿½ï¿½n taï¿½ï¿½n iï¿½inden iï¿½lenmemiï¿½ altï¿½n ï¿½ï¿½ktï¿½.");
                        }
                        if (sans >= 0.00983 && sans <= 0.01283) // diamond
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.DIAMOND, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "ï¿½ok ï¿½anslï¿½sï¿½n! Kazdï¿½ï¿½ï¿½n taï¿½ï¿½n iï¿½inden pasparlak bir elmas buldun. Bilge Ted parlaklï¿½ï¿½ï¿½nda...");
                        }
                        if (sans >= 0.00682 && sans <= 0.00982) // redstone
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.REDSTONE, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "ï¿½ok ï¿½anslï¿½sï¿½n! Kazdï¿½ï¿½ï¿½n taï¿½ï¿½n iï¿½inden garip gï¿½rï¿½nï¿½mlï¿½ kï¿½zï¿½l bir toz ï¿½ï¿½ktï¿½.");
                        }
                        if (sans >= 0.00381 && sans <= 0.00681) // lapis
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.LAPIS_LAZULI, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "ï¿½ok ï¿½anslï¿½sï¿½n! Kazdï¿½ï¿½ï¿½n taï¿½ï¿½n iï¿½inden gizemli bir mavi taï¿½ ï¿½ï¿½ktï¿½.");
                        }
                        if (sans >= 0.00230 && sans <= 0.00380) // emerald
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.EMERALD, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "ï¿½ok ï¿½anslï¿½sï¿½n! Kazdï¿½ï¿½ï¿½n taï¿½ï¿½n iï¿½inden zï¿½mrï¿½t buldun!");
                        }
                        if (sans >= 0 && sans < 0.000040) // ï¿½ok garip bir taï¿½
                        {
                            ItemStack dimetrium = new ItemStack(Material.NETHER_WART);
                            ItemMeta meta = dimetrium.getItemMeta();
                            meta.setDisplayName(ChatColor.GOLD + "Dimetrium Cevheri");
                            ArrayList<String> lore = new ArrayList<String>();
                            lore.add(ChatColor.LIGHT_PURPLE + "Garip bir cevher..");
                            meta.setLore(lore);
                            dimetrium.setItemMeta(meta);

                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), dimetrium);
                            p.sendMessage(
                                    ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW + "ï¿½ok acayip bir cevher dï¿½ï¿½ï¿½rdï¿½n...");
                        }

                    }

                } else if (MadenciSeviyeInt1 == 5) {

                    if (e.getBlock().getType() == Material.STONE || e.getBlock().getType() == Material.COAL_ORE
                            || e.getBlock().getType() == Material.DIAMOND_ORE || e.getBlock().getType() == Material.IRON_ORE
                            || e.getBlock().getType() == Material.EMERALD_ORE || e.getBlock().getType() == Material.GOLD_ORE
                            || e.getBlock().getType() == Material.LAPIS_ORE
                            || e.getBlock().getType() == Material.REDSTONE_ORE || e.getBlock().getType() == Material.GRANITE
                            || e.getBlock().getType() == Material.ANDESITE || e.getBlock().getType() == Material.DIORITE) {

                        if(!EventDosyasi.EventsClass.getMeslek(p.getPlayer().getName()).equals("Madenci"))
                        {
                            e.setCancelled(true);
                            p.sendMessage(ChatColor.RED+"[Hata]: "+ChatColor.YELLOW+"Mesleginiz madenci degil.");
                        }


                        if (p.getInventory().getItemInHand().getType().equals(Material.DIAMOND_PICKAXE)
                                || p.getInventory().getItemInHand().getType().equals(Material.GOLDEN_PICKAXE)
                                || p.getInventory().getItemInHand().getType().equals(Material.IRON_PICKAXE)
                                || p.getInventory().getItemInHand().getType().equals(Material.STONE_PICKAXE)
                                || p.getInventory().getItemInHand().getType().equals(Material.WOODEN_PICKAXE)

                        ) {

                            ItemStack stackInHand = p.getInventory().getItemInHand();
                            stackInHand.setDurability((short) (stackInHand.getDurability() + 1));

                            if (p.getItemInHand().getType() == Material.DIAMOND_PICKAXE) {
                                if (p.getItemInHand().getDurability() > 1561) {
                                    p.getInventory().removeItem(stackInHand);
                                }

                            }
                            if (p.getItemInHand().getType() == Material.GOLDEN_PICKAXE) {
                                if (p.getItemInHand().getDurability() > 32) {
                                    p.getInventory().removeItem(stackInHand);
                                }
                            }
                            if (p.getItemInHand().getType() == Material.IRON_PICKAXE) {
                                if (p.getItemInHand().getDurability() > 250) {
                                    p.getInventory().removeItem(stackInHand);
                                }
                            }
                            if (p.getItemInHand().getType() == Material.WOODEN_PICKAXE) {
                                if (p.getItemInHand().getDurability() > 59) {
                                    p.getInventory().removeItem(stackInHand);
                                }
                            }
                            if (p.getItemInHand().getType() == Material.STONE_PICKAXE) {
                                if (p.getItemInHand().getDurability() > 131) {
                                    p.getInventory().removeItem(stackInHand);
                                }
                            }

                            p.updateInventory();

                        }
                        if(EventDosyasi.EventsClass.getMeslek(p.getPlayer().getName()).equals("Madenci"))
                        {
                            int Temp;
                            // Block Kï¿½rdï¿½kï¿½a Madenci XP'yi arttï¿½rma
                            Temp = Integer.valueOf(MadenciXP1);
                            Temp = Temp + 1;
                            setMeslekXP(e.getPlayer().getName(), Temp);
                        }

                        // Block Kï¿½rdï¿½kï¿½a Madenci XP'yi arttï¿½rma

                        switch (getMeslekXP(e.getPlayer().getName())) {
                            case "3200":
                                p.sendMessage(ChatColor.GOLD + "[Bilgi]: " + ChatColor.RED
                                        + "Tebrikler! Damarlarï¿½nda akan o maden coï¿½kusunu hissedebiliyorsun. " + ChatColor.GREEN
                                        + "(2.Seviye Madenci Oldun)");
                                setMeslekSeviye(e.getPlayer().getName(), 2);
                                break;
                            case "9600":
                                p.sendMessage(ChatColor.GOLD + "[Bilgi]: " + ChatColor.RED
                                        + "Tebrikler! Kazdï¿½ï¿½ï¿½n madenlerin seni daha fazla gï¿½ï¿½lï¿½ yaptï¿½ï¿½ï¿½nï¿½ hissetmeye baï¿½ladï¿½n. "
                                        + ChatColor.BLUE + "(3.Seviye Madenci Oldun)");
                                setMeslekSeviye(e.getPlayer().getName(), 3);
                                break;
                            case "19200":
                                p.sendMessage(ChatColor.GOLD + "[Bilgi]: " + ChatColor.RED
                                        + "Etrafï¿½nda garip sesler duymaya baï¿½lï¿½yorsun. Hangi taï¿½ta hangi maden olduï¿½unu gï¿½rebilecek kadar gï¿½ï¿½lendin. "
                                        + ChatColor.YELLOW + "(4.Seviye Madenci Oldun)");
                                setMeslekSeviye(e.getPlayer().getName(), 4);
                                break;
                            case "32000":
                                p.sendMessage(ChatColor.GOLD + "[Bilgi]: " + ChatColor.RED
                                        + "Karanlï¿½ï¿½ï¿½n gï¿½cï¿½ iï¿½ine doï¿½ru ï¿½ekiliyor. Artï¿½k o kadar ï¿½ok maden iï¿½inde ustalaï¿½tï¿½n ki karanlï¿½kta ï¿½nï¿½nï¿½ gï¿½rebiliyorsun! "
                                        + ChatColor.LIGHT_PURPLE + "(Efsanevi Madenci Oldun)");
                                setMeslekSeviye(e.getPlayer().getName(), 5);
                                break;
                        }

                    }
                    // Madencinin XP almasï¿½ iï¿½in kï¿½rï¿½lmasï¿½ gereken materyaller

                    // MADENCï¿½Nï¿½N ORJï¿½NAL ORE LARI KAZMASI
                    if (e.getBlock().getType() == Material.COAL_ORE || e.getBlock().getType() == Material.DIAMOND_ORE
                            || e.getBlock().getType() == Material.IRON_ORE || e.getBlock().getType() == Material.EMERALD_ORE
                            || e.getBlock().getType() == Material.GOLD_ORE || e.getBlock().getType() == Material.LAPIS_ORE
                            || e.getBlock().getType() == Material.REDSTONE_ORE

                    ) {
                        if(!EventDosyasi.EventsClass.getMeslek(p.getPlayer().getName()).equals("Madenci"))
                        {
                            e.setCancelled(true);
                            p.sendMessage(ChatColor.RED+"[Hata]: "+ChatColor.YELLOW+"Mesleï¿½iniz madenci deï¿½il!");
                        }

                        double sans = Math.random();

                        if (e.getBlock().getType() == Material.COAL_ORE && sans >= 0 && sans <= 1) // coal
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.COAL, 1));
                            p.sendMessage(
                                    ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW + "Kï¿½mï¿½r madenini baï¿½arï¿½yla kazdï¿½n.");
                            e.getBlock().setType(Material.AIR);
                         //   return;
                        } else if (e.getBlock().getType() == Material.IRON_ORE && sans >= 0 && sans <= 1) // iron
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.IRON_ORE, 1));
                            p.sendMessage(
                                    ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW + "Demir madenini baï¿½arï¿½yla kazdï¿½n");
                            e.getBlock().setType(Material.AIR);
                        } else if (e.getBlock().getType() == Material.GOLD_ORE && sans >= 0 && sans <= 1) // gold
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.GOLD_ORE, 1));
                            p.sendMessage(
                                    ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW + "Altï¿½n madenini baï¿½arï¿½yla kazdï¿½n.");
                            e.getBlock().setType(Material.AIR);
                        } else if (e.getBlock().getType() == Material.DIAMOND && sans >= 0 && sans <= 1) // diamond
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.DIAMOND, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "Elmasa hiï¿½ zarar vermeden ï¿½ï¿½karabildin.");
                            e.getBlock().setType(Material.AIR);
                        } else if (e.getBlock().getType() == Material.REDSTONE && sans >= 0 && sans <= 1) // redstone
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.REDSTONE, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "Taï¿½ï¿½n iï¿½indeki garip kï¿½zï¿½l toz parï¿½acï¿½klarï¿½nï¿½ ï¿½ï¿½karabildin.");
                            e.getBlock().setType(Material.AIR);
                        } else if (e.getBlock().getType() == Material.LAPIS_LAZULI && sans >= 0 && sans <= 1) // lapis
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.LAPIS_LAZULI, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "Taï¿½ï¿½n iï¿½indeki gizemli mavi parlak taï¿½ï¿½ ï¿½ï¿½karabildin.");
                            e.getBlock().setType(Material.AIR);
                        } else if (e.getBlock().getType() == Material.EMERALD && sans >= 0 && sans <= 1) // emerald
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.EMERALD, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "Muhteï¿½em bir parlak yakut ï¿½ï¿½karmayï¿½ baï¿½ardï¿½n.");
                            e.getBlock().setType(Material.AIR);
                        } else {
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.RED
                                    + "Madeni dï¿½zgï¿½n bir ï¿½ekilde ï¿½ï¿½karamadï¿½n.");
                            e.getBlock().setType(Material.AIR);
                        }
                    }
                    // MADENCï¿½Nï¿½N ORJï¿½NAL ORE LARI KAZMASI

                    // Eï¿½er Madenci Taï¿½ Kï¿½rarsa Dï¿½ï¿½ecek Eï¿½yalarï¿½n Yï¿½zdelik Kodlarï¿½ ->
                    // Iron,Coal,Gold,Diamond,Redstone,Emerald,Lapis Oranlarï¿½ aï¿½aï¿½ï¿½da verildi.
                    if (e.getBlock().getType() == Material.STONE) {



                        double sans = Math.random();

                        if (sans >= 0 && sans <= 1) // coal
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.COAL, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "ï¿½ok ï¿½anslï¿½sï¿½n! Kazdï¿½ï¿½ï¿½n taï¿½ï¿½n iï¿½inden kï¿½mï¿½r parï¿½acï¿½ï¿½ï¿½ ï¿½ï¿½ktï¿½.");

                        }
                        if (sans >= 0 && sans <= 0.01) // iron
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.IRON_ORE, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "ï¿½ok ï¿½anslï¿½sï¿½n! Kazdï¿½ï¿½ï¿½n taï¿½ï¿½n iï¿½inden iï¿½lenmemiï¿½ demir ï¿½ï¿½ktï¿½.");
                        }
                        if (sans >= 0.02134 && sans <= 0.03134) // gold
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.GOLD_ORE, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "ï¿½ok ï¿½anslï¿½sï¿½n! Kazdï¿½ï¿½ï¿½n taï¿½ï¿½n iï¿½inden iï¿½lenmemiï¿½ altï¿½n ï¿½ï¿½ktï¿½.");
                        }
                        if (sans >= 0.01633 && sans <= 0.02133) // diamond
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.DIAMOND, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "ï¿½ok ï¿½anslï¿½sï¿½n! Kazdï¿½ï¿½ï¿½n taï¿½ï¿½n iï¿½inden pasparlak bir elmas buldun. Bilge Ted parlaklï¿½ï¿½ï¿½nda...");
                        }
                        if (sans >= 0.01132 && sans <= 0.01632) // redstone
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.REDSTONE, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "ï¿½ok ï¿½anslï¿½sï¿½n! Kazdï¿½ï¿½ï¿½n taï¿½ï¿½n iï¿½inden garip gï¿½rï¿½nï¿½mlï¿½ kï¿½zï¿½l bir toz ï¿½ï¿½ktï¿½.");
                        }
                        if (sans >= 0.00631 && sans <= 0.01131) // lapis
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.LAPIS_LAZULI, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "ï¿½ok ï¿½anslï¿½sï¿½n! Kazdï¿½ï¿½ï¿½n taï¿½ï¿½n iï¿½inden gizemli bir mavi taï¿½ ï¿½ï¿½ktï¿½.");
                        }
                        if (sans >= 0.00380 && sans <= 0.00630) // emerald
                        {
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(),
                                    new ItemStack(Material.EMERALD, 1));
                            p.sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW
                                    + "ï¿½ok ï¿½anslï¿½sï¿½n! Kazdï¿½ï¿½ï¿½n taï¿½ï¿½n iï¿½inden zï¿½mrï¿½t buldun!");
                        }
                        if (sans >= 0 && sans < 0.00200) // ï¿½ok garip bir taï¿½
                        {
                            ItemStack dimetrium = new ItemStack(Material.NETHER_WART);
                            ItemMeta meta = dimetrium.getItemMeta();
                            meta.setDisplayName(ChatColor.GOLD + "Dimetrium Cevheri");
                            ArrayList<String> lore = new ArrayList<String>();
                            lore.add(ChatColor.LIGHT_PURPLE + "Garip bir cevher..");
                            meta.setLore(lore);
                            dimetrium.setItemMeta(meta);

                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), dimetrium);
                            p.sendMessage(
                                    ChatColor.BLUE + "[Bilgi]: " + ChatColor.YELLOW + "Değişik bir cevher çıkardın.");
                        }

                    }

                } else {
                    // p.kickPlayer(ChatColor.RED+"[Uyarï¿½]: "+ChatColor.WHITE+"Sistem madenci
                    // seviyeni algï¿½layamadï¿½. Lï¿½tfen batur123 isimli yï¿½netici ile iletiï¿½ime
                    // geï¿½iniz.");
                    OyuncuKickle(p, ChatColor.RED + "[Uyarï¿½]: " + ChatColor.WHITE
                            + "Sistem madenci seviyenizi algıyalamadı. Batur123 iletişime geçiniz.");

                }
            }


        }
        else
        {
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED+"[Hata]: "+ChatColor.YELLOW+"Mesleğiniz madenci değil!");

        }


    }




    @EventHandler
    public void DemirciBasitCraftlar(CraftItemEvent e)
    {
        Player p = (Player) e.getWhoClicked();
        try
        {

            if(
                    e.getRecipe().getResult().getType() == Material.IRON_BLOCK ||
                            e.getRecipe().getResult().getType() == Material.IRON_BARS ||
                            e.getRecipe().getResult().getType() == Material.COMPASS ||
                            e.getRecipe().getResult().getType() == Material.CLOCK ||
                            e.getRecipe().getResult().getType() == Material.IRON_TRAPDOOR ||
                            e.getRecipe().getResult().getType() == Material.IRON_DOOR

            )
            {

                if(!EventDosyasi.EventsClass.getMeslek(p.getPlayer().getName()).equals("Demirci"))
                {
                    e.setCancelled(true);
                    p.sendMessage(ChatColor.RED+"[Hata]: "+ChatColor.YELLOW+"Mesleğiniz demirci değil!");
                }



            }


        }
        catch(Exception ex)
        {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"[Hata](Konum Demirci)");
        }



    }
    @EventHandler public void Odunculuk(CraftItemEvent e)
    {

        Player p = (Player) e.getWhoClicked();

        try
        {

            if(
                    e.getRecipe().getResult().getType() == Material.OAK_PLANKS ||
                            e.getRecipe().getResult().getType() == Material.ACACIA_PLANKS ||
                            e.getRecipe().getResult().getType() == Material.DARK_OAK_PLANKS ||
                            e.getRecipe().getResult().getType() == Material.JUNGLE_PLANKS ||
                            e.getRecipe().getResult().getType() == Material.SPRUCE_PLANKS ||
                            e.getRecipe().getResult().getType() == Material.BIRCH_PLANKS ||

                            e.getRecipe().getResult().getType() == Material.STICK ||
                            e.getRecipe().getResult().getType() == Material.LADDER ||

                            e.getRecipe().getResult().getType() == Material.CRAFTING_TABLE ||

                            e.getRecipe().getResult().getType() == Material.ACACIA_DOOR ||
                            e.getRecipe().getResult().getType() == Material.BIRCH_DOOR ||
                            e.getRecipe().getResult().getType() == Material.DARK_OAK_DOOR ||
                            e.getRecipe().getResult().getType() == Material.JUNGLE_DOOR ||
                            e.getRecipe().getResult().getType() == Material.OAK_DOOR ||
                            e.getRecipe().getResult().getType() == Material.SPRUCE_DOOR ||

                            e.getRecipe().getResult().getType() == Material.ACACIA_TRAPDOOR ||
                            e.getRecipe().getResult().getType() == Material.BIRCH_TRAPDOOR ||
                            e.getRecipe().getResult().getType() == Material.DARK_OAK_TRAPDOOR ||
                            e.getRecipe().getResult().getType() == Material.JUNGLE_TRAPDOOR ||
                            e.getRecipe().getResult().getType() == Material.OAK_TRAPDOOR ||
                            e.getRecipe().getResult().getType() == Material.SPRUCE_TRAPDOOR ||

                            e.getRecipe().getResult().getType() == Material.ACACIA_STAIRS ||
                            e.getRecipe().getResult().getType() == Material.BIRCH_STAIRS ||
                            e.getRecipe().getResult().getType() == Material.DARK_OAK_STAIRS ||
                            e.getRecipe().getResult().getType() == Material.JUNGLE_STAIRS ||
                            e.getRecipe().getResult().getType() == Material.OAK_STAIRS ||
                            e.getRecipe().getResult().getType() == Material.SPRUCE_STAIRS

            )
            {

                if(EventDosyasi.EventsClass.getMeslek(p.getPlayer().getName()).equals("Oduncu"))
                {
                    StatTemp = 0;
                    StatTemp = Integer.valueOf(EventDosyasi.EventsClass.getMeslekXP(p.getName()));

                    StatTemp = StatTemp + 1;
                    EventDosyasi.EventsClass.setMeslekXP(p.getName(),StatTemp);
                }
                else
                {
                    e.setCancelled(true);
                    p.sendMessage(ChatColor.RED+"[Hata]: "+ChatColor.YELLOW+"Mesleğiniz marangoz değil!");
                }


            }


        }
        catch(Exception ex)
        {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"[Hata](Konum Odunculuk)");
        }






    }



    @EventHandler
    public void onPlace(BlockPlaceEvent event)
    {

        try
        {
            Player p = event.getPlayer();

            if (getKontrol(p.getPlayer().getName()).equals("hayir")) {
                event.setCancelled(true);
            }

            Block block = event.getBlock();
            Player player = event.getPlayer();

            if (block.getType().equals(Material.TNT) || event.getBlockPlaced().getType().equals(Material.LAVA))
            {

                event.setCancelled(true);

                OyuncuKickle(player,
                        ChatColor.BLUE + "Sunucudan atıldınız. \n" + ChatColor.GOLD + "[Sunucu]: " + ChatColor.RED
                                + "Bu bloğu kullanmanız yasaktır.. \n" + ChatColor.GOLD + "Kullandığınız Blok: "
                                + ChatColor.RED + block.getType().toString());
            }
        }
        catch(Exception ex)
        {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"[Hata](onPlace): "+event.getPlayer().getName());
        }





    }

    @EventHandler
    public void SutIcme(PlayerItemConsumeEvent e) {

        Player p = e.getPlayer();
        if (e.getItem().getType().equals(Material.MILK_BUCKET)) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.GREEN + "[Bilgi]: " + ChatColor.YELLOW + "Bu sütü içemezsiniz.");
        }

    }

    /*
     * @EventHandler public void Sakatlanma(PlayerMoveEvent e) { Player p =
     * e.getPlayer();
     *
     * if(p.getGameMode() == GameMode.CREATIVE) {
     *
     * } else { p.getFallDistance();
     *
     * if(p.getFallDistance() > 8) {
     *
     * p.sendMessage(ChatColor.GREEN+"[Bilgi]: "+ChatColor.
     * YELLOW+"Aya&iuml;&iquest;&frac12;&iuml;&iquest;&frac12;ndan &iuml;&iquest;&frac12;at diye bir ses geldi... Sakatland&iuml;&iquest;&frac12;n.");
     * p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999999,
     * 999999999)); p.setSneaking(true); } }
     *
     *
     * }
     *
     */

    @EventHandler
    public void ItemAlma(EntityPickupItemEvent e)
    {
        Player p = (Player) e.getEntity();
        try
        {

            if (!p.isSneaking()) {
                e.setCancelled(true);
            }
        }
        catch(Exception ex)
        {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"[Hata](ItemAlma): "+p.getPlayer().getName());
        }

    }

    public void OyuncuKickle(Player p, String mesaj)
    {
        Bukkit.getScheduler().runTask(JavaPlugin.getPlugin(BaturPlugin.class), new Runnable() {
            public void run()
            {
                p.kickPlayer(mesaj);
            }
        });
    }

    @EventHandler
    public void playerChat(AsyncPlayerChatEvent e)
    {
        try {

            if (getKontrol(e.getPlayer().getName()).equals("hayir")) // ï¿½ifre girmesi lazï¿½m
            {

                String OrjinalSifre = getSifre(e.getPlayer().getName());
                String SifreMesaji = e.getMessage();

                if (OrjinalSifre.equals(SifreMesaji)) {

                    e.getPlayer().sendMessage(
                            ChatColor.GOLD + "[Flowing Tears]: " + ChatColor.GREEN + "Başarıyla giriş yaptınız.");
                    setKontrol(e.getPlayer().getName(), "evet");
                    e.getPlayer().removePotionEffect(PotionEffectType.BLINDNESS);
                    e.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);

                    /*
                     * for (PotionEffect effect : e.getPlayer().getActivePotionEffects()) {
                     * e.getPlayer().removePotionEffect(effect.getType()); }
                     */

                    e.setCancelled(true);

                }
                else
                {

                    setKontrol(e.getPlayer().getName(), "hayir");
                    OyuncuKickle(e.getPlayer(),
                            ChatColor.GOLD + "[Flowing Tears]: " + ChatColor.RED + "Şifrenizi hatalı girdiniz.");
                    // e.getPlayer().kickPlayer(ChatColor.GOLD +"[Flowing Tears]:
                    // "+ChatColor.RED+"ï¿½ifrenizi hatalï¿½ girdiniz." );
                    e.setCancelled(true);

                }
            } else if (getKontrol(e.getPlayer().getName()).equals("evet")) // ï¿½ifre girdiyse chatten konusabilir.
            {

                if (getMaskeDurum(e.getPlayer().getName()) == 1) {

                    String rawmessage = e.getMessage();

                    String message = ChatColor.YELLOW + "Maskeli Kişi: " + ChatColor.WHITE + rawmessage;
                    String ownmessage = ChatColor.GOLD + "Maskeli Kişi: " + ChatColor.WHITE + rawmessage;

                    int blockDistance = 10;
                    Location playerLocation = e.getPlayer().getLocation();

                    for (Player pl : e.getRecipients()) {
                        if (pl.getLocation().distance(playerLocation) <= blockDistance) {

                            if (pl == e.getPlayer()) {

                                pl.sendMessage(ownmessage);
                            } else {
                                pl.sendMessage(message);
                            }

                        }

                    }

                    e.getRecipients().clear();
                }
                if (KomutListesi.MaskeAd != e.getPlayer().getDisplayName()) {

                    String karakterad = getKarakterAd(e.getPlayer().getName());
                    String rawmessage = e.getMessage();
                    String rawmessage2 = rawmessage.substring(0, 1).toUpperCase() + rawmessage.substring(1);

                    String message = ChatColor.GOLD+"("+e.getPlayer().getName()+") "+ChatColor.YELLOW + karakterad + ": " + ChatColor.WHITE + rawmessage2;

                    String ownmessage = ChatColor.YELLOW+"("+e.getPlayer().getName()+") "+ChatColor.GOLD + karakterad + ": " + ChatColor.WHITE + rawmessage2;

                    int blockDistance = 10;
                    Location playerLocation = e.getPlayer().getLocation();

                    for (Player pl : e.getRecipients()) {
                        if (pl.getLocation().distance(playerLocation) <= blockDistance && pl.getWorld() == e.getPlayer().getWorld()) {

                            if (pl == e.getPlayer()) {
                                pl.sendMessage(ownmessage);
                            } else {
                                pl.sendMessage(message);
                            }

                        }

                    }

                    e.getRecipients().clear();
                }

            } else {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED  + "[Hata]: Oyuncu getKontrol metodunda evet veya hayır harici bir cevap geldi ve else bloğu çalıştı. Konum: playerChat");
                OyuncuKickle(e.getPlayer(), ChatColor.RED
                        + "[HATA!]: Chat sisteminde teknik bir arıza oluştu. Hata Kodu #13. Lütfen Batur123 isimli yöneticiye bildirim yapınız. ");
                e.setCancelled(true);
            }

        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"[Hata](playerChat): "+e.getPlayer().getName());
        }

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e)
    {

        e.setQuitMessage("");

        Connection connect = null;
        PreparedStatement pre = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/karakter" + "?useUnicode=true&characterEncoding=utf-8&user=root&password=test");

            String sql = "UPDATE karakter " + "SET KarakterAdi = ? "
                    + ", guc = ? , bilgelik = ? , atiklik = ? , buyu = ? , dayaniklilik = ? , karizma = ? , meslek = ? , MeslekXP = ? , MeslekSV = ?  " + " WHERE OyunAdi = ? ";

            pre = connect.prepareStatement(sql);
            pre.setString(1, getKarakterAd(e.getPlayer().getName()));

            pre.setString(2, getzarGuc(e.getPlayer().getName()));
            pre.setString(3, getzarBilgelik(e.getPlayer().getName()));
            pre.setString(4, getzarAtiklik(e.getPlayer().getName()));
            pre.setString(5, getzarBuyu(e.getPlayer().getName()));
            pre.setString(6, getzarDayaniklilik(e.getPlayer().getName()));
            pre.setString(7, getzarKarizma(e.getPlayer().getName()));

            pre.setString(8, getMeslek(e.getPlayer().getName()));
            pre.setString(9, getMeslekXP(e.getPlayer().getName()));
            pre.setString(10, getMeslekSeviye(e.getPlayer().getName()));
            pre.setString(11, e.getPlayer().getName());

            pre.executeUpdate();

            Bukkit.getConsoleSender().sendMessage(
                    ChatColor.GREEN + "[Bilgi]: " + ChatColor.RED + e.getPlayer().getName() + ChatColor.GREEN
                            + " isimli oyuncu sunucudan çıkış yaptı. Veritabanı güncelleme işlemi yapıldı.");

        } catch (Exception d) {

            d.printStackTrace();
        }

        try {
            if (connect != null) {
                pre.close();
                connect.close();
            }
        } catch (SQLException s) {

            s.printStackTrace();
        }

    }

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent e)
    {
        Player p = e.getPlayer();

        try
        {


            if (getKontrol(p.getPlayer().getName()).equals("hayir"))
            {
                e.setCancelled(true);
                OyuncuKickle(e.getPlayer(), ChatColor.RED
                        + "Şifrenizi girmeden komut yazamazsınız. Şifrenizi sohbet ekranına direk yazarak giriş yapabilirsiniz.");

            }
        }
        catch(Exception ex)
        {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"[Hata](onCommandPreprocess): "+p.getPlayer().getName());
        }


    }

    @EventHandler
    public void playerDropItem(PlayerDropItemEvent e)
    {
        Player p = e.getPlayer();
        try
        {
            if (getKontrol(p.getPlayer().getName()).equals("hayir")) {
                e.setCancelled(true);
            }
        }
        catch(Exception ex)
        {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"[Hata](playerDropItem): "+p.getPlayer().getName());
        }



    }

    @EventHandler
    public void OpenInventory(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        try
        {


            if (getKontrol(p.getPlayer().getName()).equals("hayir")) {
                e.setCancelled(true);
            }
        }
        catch(Exception ex)
        {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"[Hata](OpenInventory): "+p.getPlayer().getName());
        }

    }

    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent e) {

        try
        {
            Player p = e.getPlayer();

            if (getKontrol(p.getPlayer().getName()).equals("hayir"))
            {
                e.setCancelled(true);
            }
            else
            {

            }
        }
        catch(Exception ex)
        {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"[Hata](PlayerInteract) "+e.getPlayer().getName());
        }

    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        e.setJoinMessage("");
        Player p = e.getPlayer();

        createFile(p);

        try {

            setAC(e.getPlayer().getName(), 10);
            setKontrol(e.getPlayer().getName(), "hayir");

            if (getKontrol(p.getPlayer().getName()).equals("hayir")) {
                // Oyuncunun HP'si -> 25 + [(Dayanï¿½klï¿½lï¿½k Bonusu - 10)*2]

                p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 999999999, 999999999));
                p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 99999999, 999999999));

                try {

                    // p.setMaxHealth(Double.valueOf(
                    //                            25 + ((Integer.valueOf(getzarDayaniklilik(p.getPlayer().getName())) + bonusday - 10) / 2)
                    //                                    * 1.5));
                    int bonusday = EventsClass.BonusDay(EventsClass.GetIrk(e.getPlayer().getName()));
                    int bonuscev = EventsClass.BonusCeviklik(EventsClass.GetIrk(e.getPlayer().getName()));
                    int bonusceviklikac = EventsClass.BonusCeviklik(e.getPlayer().getName());
                    p.setMaxHealth(25 + ((Integer.valueOf(getzarDayaniklilik(p.getPlayer().getName())) + bonusday - 10) / 2) * 1.5);
                    int tempac = ((Integer.valueOf(getzarAtiklik(p.getPlayer().getName())) + bonuscev - 10) / 2);
                    tempac = tempac + 10;
                    EventsClass.setAC(e.getPlayer().getName(),tempac);


                } catch (Exception ex) {
                    p.setMaxHealth(20.0);
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[Hata]: " + ChatColor.GREEN
                            + "Oyuncunun canını ayarlamada hata oluştu. Oyuncu Adı: " + ChatColor.YELLOW + p.getName());

                }

            }

        } catch (Exception ex)
        {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[Hata]: " + ChatColor.GREEN + "onJoin'de Hata");
        }

    }

    @EventHandler
    public void onFirstJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        if (!p.hasPlayedBefore()) {
            ItemStack dimetrium = new ItemStack(Material.IRON_NUGGET, 25);
            ItemMeta meta = dimetrium.getItemMeta();
            meta.setDisplayName(ChatColor.GOLD + "Oren");
            ArrayList<String> lore = new ArrayList<String>();
            lore.add(ChatColor.LIGHT_PURPLE + "Madeni Para");
            meta.setLore(lore);
            dimetrium.setItemMeta(meta);

            ItemStack dimetrium2 = new ItemStack(Material.BREAD, 5);
            ItemMeta meta2 = dimetrium2.getItemMeta();
            meta2.setDisplayName(ChatColor.GOLD + "Ekmek");
            ArrayList<String> lore2 = new ArrayList<String>();
            lore2.add(ChatColor.LIGHT_PURPLE + "Sıcacık ekmek...");
            meta2.setLore(lore2);
            dimetrium2.setItemMeta(meta2);

            p.getInventory().addItem(dimetrium);
            p.getInventory().addItem(dimetrium2);

        }
    }

    public void createFile(Player player) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "SELECT * FROM karakter where OyunAdi='" + player.getName() + "'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (!rs.first()) {
                // player.kickPlayer(ChatColor.GOLD+"[Flowing-Tears]:"+ChatColor.RED+"Lï¿½tfen
                // karakter baï¿½vurunuzu yapï¿½nï¿½z. Karakteriniz bulunamadï¿½.");
                OyuncuKickle(player.getPlayer(), ChatColor.GOLD + "[Flowing-Tears]:" + ChatColor.RED
                        + "Lütfen karakter başvurunuzu yapınız. Karakteriniz bulunamadı.");
                Bukkit.getConsoleSender()
                        .sendMessage(ChatColor.BLUE + "[Bilgi]: " + ChatColor.GREEN
                                + "Oyuncunun veritabanında kaydı bulunamadı. (" + ChatColor.GOLD + " Oyuncunun Adı: "
                                + ChatColor.GREEN + player.getPlayer().getName() + ")");

            } else {

                // continue processing the ResultSet further
                do {

                    BasvuruDurum = rs.getInt("Basvuru_Durum");
                    ForumAdi = rs.getString("ForumAdi");
                    OyunAdi = rs.getString("OyunAdi");
                    KarakterAdi = rs.getString("KarakterAdi");
                    Sifre = rs.getString("Sifre");
                    Yas = rs.getString("Yas");
                  //  Gorunus = rs.getString("Gorunus");

                    guc = rs.getInt("guc");
                    bilgelik = rs.getInt("bilgelik");
                    atiklik = rs.getInt("atiklik");
                    buyu = rs.getInt("buyu");
                    dayaniklilik = rs.getInt("dayaniklilik");
                    karizma = rs.getInt("karizma");

                    oyuncumadencixp = rs.getString("oyuncumadencixp");
                    // oyuncuciftcixp = rs.getString("oyuncuciftcixp");
                    oyuncuavcixp = rs.getString("oyuncuavcixp");
                    oyuncuoduncuxp = rs.getString("oyuncuoduncuxp");
                    oyuncudemircixp = rs.getString("oyuncudemircixp");

                    oyuncumadencisv = rs.getString("oyuncumadencisv");
                    // oyuncuciftcisv = rs.getString("oyuncuciftcisv");
                    oyuncuavcisv = rs.getString("oyuncuavcisv");
                    oyuncuoduncusv = rs.getString("oyuncuoduncusv");
                    oyuncudemircisv = rs.getString("oyuncudemircisv");
                    Irk = rs.getString("Irk");
                    Meslek = rs.getString("Meslek");
                    MeslekXP = rs.getInt("MeslekXP");
                    CK_Durum = rs.getString("CK_Durum");
                    MeslekSV = rs.getInt("MeslekSV");

                    if (CK_Durum.equals("1")) {
                        OyuncuKickle(player, ChatColor.GOLD + "[Sunucu]:" + ChatColor.GREEN
                                + " Karakteriniz ölmüş. Lütfen forumdan karakterinizi ayarlayınız..");
                        // player.kickPlayer(ChatColor.GOLD+"[Sunucu]:"+ChatColor.GREEN+" CK olmuï¿½sunuz.
                        // Lï¿½tfen forumdan karakterinizi ayarlayï¿½nï¿½z.");
                        Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "[Bilgi]: " + ChatColor.GREEN
                                + player.getName() + " isimli oyuncu CK olduğu için sunucuya giremedi. ");
                    } else {
                        if (BasvuruDurum == 1) {
                            player.setPlayerListName(KarakterAdi + "(" + player.getName() + ")");
                            try {
                                Object enumTitle = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0]
                                        .getField("TITLE").get(null);
                                Object chat = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0]
                                        .getMethod("a", String.class)
                                        .invoke(null, "{\"text\":\"Flowing Tears'a hoş geldin\"}");

                                Constructor<?> titleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(
                                        getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0],
                                        getNMSClass("IChatBaseComponent"), int.class, int.class, int.class);
                                Object packet = titleConstructor.newInstance(enumTitle, chat, 30, 50, 30); // eskisi 20
                                // 40 20

                                sendPacket(player.getPlayer(), packet);
                            }

                            catch (Exception e1) {
                                e1.printStackTrace();
                            }
                            // sendCenteredMessage(player.getPlayer(),
                            // player.sendMessage(ChatColor.GOLD+"[Evernight-RP]: " +ChatColor.BLUE +
                            // "Sunucuya hoï¿½geldin "+ChatColor.GREEN+KarakterAdi+"("+OyunAdi+")");
                            player.sendMessage(ChatColor.YELLOW + "[Şifre Kontrolü]: " + ChatColor.YELLOW
                                    + "Lütfen şifrenizi giriniz:");

                        } else {
                            OyuncuKickle(player, ChatColor.GOLD + "[Sunucu]:" + ChatColor.RED
                                    + "Lütfen karakter başvurunuzu yapınız. Karakteriniz bulunamadı.");
                            // player.kickPlayer(ChatColor.GOLD+"[Sunucu]:"+ChatColor.RED+"Lï¿½tfen karakter
                            // baï¿½vurunuzu yapï¿½nï¿½z. Karakteriniz bulunamadï¿½.");
                            Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE
                                    + "[Bilgi]: Oyuncunun veritabanında kaydı bulundu fakat başvurusu kabul edilmemiş. ("
                                    + ChatColor.GREEN + " Oyuncunun Adı: " + player.getPlayer().getName() + ")");

                        }

                        Bukkit.getConsoleSender().sendMessage(
                                ChatColor.GREEN + "[Sunucu]: " + OyunAdi + " oyuna bağlantı");

                        File pFileDir = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular");
                        if (!pFileDir.exists()) {
                            pFileDir.mkdir();
                        }
                        File pFile = new File(BaturPlugin.getInstance().getDataFolder(),
                                "Oyuncular/" + player.getName().toLowerCase() + ".yml");
                        // if (!pFile.exists())
                        // {
                        try {
                            pFile.createNewFile();

                            FileConfiguration config = YamlConfiguration.loadConfiguration(pFile);

                            // Oyuncu Bilgileri
                            config.set("Oyuncu:", OyunAdi);
                            config.set("Forum_Adi", ForumAdi);
                            config.set("Minecraft_Adi", OyunAdi);
                            config.set("Karakter_Adi", KarakterAdi);
                            config.set("Sifre", Sifre);
                            config.set("Yas", Yas);
                        //    config.set("Gorunus", Gorunus);
                            config.set("MaskeDurum", 0);
                            // Oyuncu Bilgileri

                            // Temel Zar Statlarï¿½
                            config.set("Kuvvet", guc);
                            config.set("Bilgelik", bilgelik);
                            config.set("Ceviklik", atiklik);
                            config.set("Zeka", buyu);
                            config.set("Dayaniklilik", dayaniklilik);
                            config.set("Karizma", karizma);
                            // Temel Zar Statlarï¿½

                            config.set("Basvuru_Durumu", BasvuruDurum);
                            config.set("Girdi_Kontrol", "hayir"); // Duzenlencek

                            config.set("CK_Durum", 0);
                            config.set("Irk", Irk);

                            config.set("AC", 10);
                            config.set("MadenciSv", oyuncumadencisv);
                            config.set("MadenciXP", oyuncumadencixp);
                            config.set("OduncuSv", oyuncuoduncusv);
                            config.set("OduncuXP", oyuncuoduncuxp);
                            config.set("DemirciXP", oyuncudemircixp);
                            config.set("DemirciSv", oyuncudemircisv);
                            // config.set("CiftciXP",oyuncuciftcixp);
                            // config.set("CiftciSV",oyuncuciftcisv);
                            config.set("AvciXP", oyuncuavcixp);
                            config.set("AvciSV", oyuncuavcisv);
                            config.set("Meslek",Meslek);
                            config.set("MeslekXP",MeslekXP);
                            config.set("MeslekSV",MeslekSV);

                            config.save(pFile);

                        } catch (Exception e) {

                            Bukkit.getConsoleSender().sendMessage(ChatColor.RED
                                    + "[Hata]: Dosya kaydında hata. Satır 1822. Konum -> CreateFile() Fonksiyonu.");

                        }

                    }

                } while (rs.next());

                con.close();

            }

        } catch (ClassNotFoundException | SQLException cd) {
            cd.printStackTrace();
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED
                    + "[Hata]: ClassNotFoundException ve SQLException hata verdi. Hatanın konumu -> onJoin");
            OyuncuKickle(player, ChatColor.GOLD + "[Sunucu]:" + ChatColor.RED
                    + "Girişte hata oluştu. batur123 ile iletişime geçin.");
            // player.kickPlayer("Hata oluï¿½tu.");
        }

    }

    public static String getSifre(String p) {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        String className = pConfig.getString("Sifre");
        return className;

    }

    public static String getKarakterAd(String p) {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        String className = pConfig.getString("Karakter_Adi");
        return className;
    }

    public static String getKontrol(String p) {

        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        String className = pConfig.getString("Girdi_Kontrol");
        return className;

    }

    public static String getOyunNick(String p) {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        return pConfig.getString("Minecraft_Adi");
    }

    public void setKontrol(String p, String kontroldurumu) throws IOException {

        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        pConfig.set("Girdi_Kontrol", kontroldurumu);

            pConfig.save(pFile);

    }

    public static String getMeslekXP(String p) {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        return pConfig.getString("MeslekXP");
    }

    public static void setMeslekXP(String p, int kontroldurumu) throws IOException {

        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        pConfig.set("MeslekXP", kontroldurumu);

            pConfig.save(pFile);

    }

    public static String getMeslekSeviye(String p) {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        return pConfig.getString("MeslekSV");
    }

    public static void setMeslekSeviye(String p, int kontroldurumu) throws IOException {

        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        pConfig.set("MeslekSV", kontroldurumu);

            pConfig.save(pFile);

    }

    public static String getYas(String p) {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        return pConfig.getString("Yas");
    }

    public static String getzarGuc(String p) {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        return pConfig.getString("Kuvvet");
    }

    public static String getzarBilgelik(String p) {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        return pConfig.getString("Bilgelik");
    }

    public static String getzarAtiklik(String p) {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        return pConfig.getString("ï¿½eviklik");
    }

    public static String getzarBuyu(String p) {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        return pConfig.getString("Zeka");
    }

    public static String getzarDayaniklilik(String p) {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        return pConfig.getString("Dayanï¿½klï¿½lï¿½k");
    }

    public static String getzarKarizma(String p) {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        return pConfig.getString("Karizma");
    }

    public static String getGorunus(String p) {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        return pConfig.getString("Gorunus");
    }

    public static void setGorunus(String p, String Gorunus) throws IOException {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        pConfig.set("Gorunus", Gorunus);
            pConfig.save(pFile);

    }


    public static void setCan(String p, String Can) throws IOException {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        pConfig.set("Can", Can);

            pConfig.save(pFile);

    }


    public static int getCan(String p) {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        return pConfig.getInt("Can");
    }

    public static void setArmor(String p, String Can) throws IOException {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        pConfig.set("Can", Can);

            pConfig.save(pFile);

    }

    public static int getArmor(String p) {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        return pConfig.getInt("Can");
    }

    public static String getAclik(String p) {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        return pConfig.getString("Gorunus");
    }

    public static void setAclik(String p, String Aclik) throws IOException {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        pConfig.set("DemirciSv", Aclik);

            pConfig.save(pFile);

    }

    public static void setCK(String p, String CKmi) throws IOException {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        pConfig.set("CK_Durum", CKmi);

        try {
            pConfig.save(pFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getAclikKontrol(String p) {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        String className = pConfig.getString("Gorunus");
        return className;
    }

    public static void setAclikKontrol(String p, String AclikKontrol) throws IOException {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        pConfig.set("AclikKontrolLimiti", AclikKontrol);

            pConfig.save(pFile);

    }

    public void sendPacket(Player player, Object packet) {
        try {
            Object handle = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Class<?> getNMSClass(String name) {
        // org.bukkit.craftbukkit.v1_8_R3...
        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        try {
            return Class.forName("net.minecraft.server." + version + "." + name);
        }

        catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }



    public static String getSinif(String p) {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        String className = pConfig.getString("Sinif");
        return className;
    }

    /*
     * public static String getCiftciXP(String p) { File pFile = new
     * File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" +
     * p.toLowerCase() + ".yml"); FileConfiguration pConfig =
     * YamlConfiguration.loadConfiguration(pFile); String className =
     * pConfig.getString("CiftciXP"); return className; }
     *
     * public static String getCiftciSV(String p) { File pFile = new
     * File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" +
     * p.toLowerCase() + ".yml"); FileConfiguration pConfig =
     * YamlConfiguration.loadConfiguration(pFile); String className =
     * pConfig.getString("CiftciSV"); return className; }
     */



	/*public static String getMeslekXP(String p) {
		File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
		FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
		String className = pConfig.getString("MadenciXP");
		return className;
	} */



    public void setYetkiSeviye(String p, String YetkiSeviyesi) throws IOException {

        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        pConfig.set("YetkiSeviyesi", YetkiSeviyesi);

            pConfig.save(pFile);

    }

    public static String getMeslek(String p) {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        String className = pConfig.getString("Meslek");
        return className;
    }


    public static void setMeslek(String p, String YetkiSeviyesi) throws IOException {

        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        pConfig.set("Meslek", YetkiSeviyesi);

            pConfig.save(pFile);

    }
    // oduncu xp,sv set get tamam
    // avci xp sv set get tamam

    public static String getYetkiSeviye(String p) {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        String className = pConfig.getString("Gorunus");
        return className;
    }

    public static String silahBilgiAl(String p) {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        String className = pConfig.getString("Gorunus");
        return className;
    }

    public void silahBilgiVer(String p, String YetkiSeviyesi) {

        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        pConfig.set("YetkiSeviyesi", YetkiSeviyesi);
        try {
            pConfig.save(pFile);
        } catch (Exception e) {

        }
    }

    public static String GetIrk(String p) {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        String className = pConfig.getString("Irk");
        return className;
    }

    public static String IrkAd(String p) {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Irklar/" + p + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        String className = pConfig.getString("IrkAd");
        return className;
    }

    public static int BonusKarizma(String p) {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Irklar/" + p + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        int className = pConfig.getInt("Karizma");
        return className;
    }

    public static int BonusKuvvet(String p) {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Irklar/" + p + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        int className = pConfig.getInt("Kuvvet");
        return className;
    }

    public static int BonusCeviklik(String p) {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Irklar/" + p + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        int className = pConfig.getInt("Ceviklik");
        return className;
    }

    public static int BonusBilgelik(String p) {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Irklar/" + p + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        int className = pConfig.getInt("Bilgelik");
        return className;
    }

    public static void setKarakterAd(String p, String kontroldurumu) {

        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        pConfig.set("Karakter_Adi", kontroldurumu);
        try {
            pConfig.save(pFile);
        } catch (Exception e) {

        }
    }

    public static int BonusZeka(String p) {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Irklar/" + p + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        int className = pConfig.getInt("Zeka");
        return className;
    }

    public static int BonusDay(String p) {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Irklar/" + p + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        int className = pConfig.getInt("Dayaniklilik");
        return className;
    }

    public static int getMaskeDurum(String p) {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        int className = pConfig.getInt("MaskeDurum");
        return className;
    }

    public static void setMaskeDurum(String p, int MaskeDurum) throws IOException {

        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        pConfig.set("MaskeDurum", MaskeDurum);

            pConfig.save(pFile);

    }

    public static int getAC(String p) {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        int className = pConfig.getInt("AC");
        return className;
    }

    public static void setAC(String p, int AC) throws IOException {

        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        pConfig.set("AC", AC);

            pConfig.save(pFile);

    }

}
