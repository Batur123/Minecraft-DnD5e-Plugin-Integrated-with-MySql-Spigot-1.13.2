package AnaDizinDosyasi;


import java.io.File;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import com.mysql.jdbc.Connection;

import CustomItemler.CustomItems;
import Komutlar.KomutListesi;
import EventDosyasi.ArmorListener;
import EventDosyasi.DispenserArmorListener;
import EventDosyasi.EventsClass;

import static CustomItemler.CustomItems.*;


public class BaturPlugin extends JavaPlugin implements Listener
{
    private Connection connection;
    public static BaturPlugin plugin;

    @Override
    public void onEnable()
    {

        plugin = this;

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[Sunucu]: Sunucu sistemleri başarıyla aktif edildi.");
        getServer().getPluginManager().registerEvents(new EventsClass(), this);
        getServer().getPluginManager().registerEvents(new CustomItems(), this);

        this.getCommand("duyuru").setExecutor(new KomutListesi());
        this.getCommand("karakter").setExecutor(new KomutListesi());
        this.getCommand("me").setExecutor(new KomutListesi());
        this.getCommand("do").setExecutor(new KomutListesi());
        this.getCommand("ba").setExecutor(new KomutListesi());
        this.getCommand("f").setExecutor(new KomutListesi());
        this.getCommand("b").setExecutor(new KomutListesi());
        this.getCommand("ooc").setExecutor(new KomutListesi());
        this.getCommand("meslekseviye").setExecutor(new KomutListesi());
        this.getCommand("a").setExecutor(new KomutListesi());
        this.getCommand("maske").setExecutor(new KomutListesi());
        this.getCommand("zar").setExecutor(new KomutListesi());
        this.getCommand("silah").setExecutor(new KomutListesi());
        this.getCommand("ck").setExecutor(new KomutListesi());
        this.getCommand("kf").setExecutor(new KomutListesi());
        this.getCommand("w").setExecutor(new KomutListesi());
        this.getCommand("kk").setExecutor(new KomutListesi());
        this.getCommand("isimver").setExecutor(new KomutListesi());
        this.getCommand("lorever").setExecutor(new KomutListesi());
        this.getCommand("tur").setExecutor(new KomutListesi());
        this.getCommand("martinkafaat").setExecutor(new KomutListesi());
        this.getCommand("saldir").setExecutor(new KomutListesi());
        this.getCommand("hasar").setExecutor(new KomutListesi());
        this.getCommand("kaydet").setExecutor(new KomutListesi());
        this.getCommand("tamir").setExecutor(new KomutListesi());
        this.getCommand("isimayarla").setExecutor(new KomutListesi());
        this.getCommand("yardim").setExecutor(new KomutListesi());
        this.getCommand("mesaj").setExecutor(new KomutListesi());
        this.getCommand("yuru").setExecutor(new KomutListesi());
        this.getCommand("meslek").setExecutor(new KomutListesi());
        this.getCommand("sifa").setExecutor(new KomutListesi());
        this.getCommand("mesleksifirla").setExecutor(new KomutListesi());
        this.getCommand("baslangic").setExecutor(new KomutListesi());

        Kalkanlar();
        Arbalet();
        Itemlerv2();
        ZirhCraft();
        Sifac();

        mysqlSetup();

        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new ArmorListener(getConfig().getStringList("blocked")), this);
        try{
            //Better way to check for this? Only in 1.13.1+?
            Class.forName("org.bukkit.event.block.BlockDispenseArmorEvent");
            getServer().getPluginManager().registerEvents(new DispenserArmorListener(), this);
        }catch(Exception ignored){}
        //example();
	/*      Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable()
	        {
	          public void run() {

	        	  for (Player p : Bukkit.getOnlinePlayers()) {

	                    final ItemStack h = p.getInventory().getHelmet();
	                    final ItemStack c = p.getInventory().getChestplate();
	                    final ItemStack l = p.getInventory().getLeggings();
	                    final ItemStack b = p.getInventory().getBoots();

	                    if (h != null && h.hasItemMeta() && h.getItemMeta().hasDisplayName())
	                    {

	                        if (p.getInventory().getHelmet().getItemMeta().getDisplayName().equalsIgnoreCase("§4Zeus Helmet of Power")) {
	                        }
	                    }

	                    if (c.getType() != Material.AIR) {
	                      if (c.getItemMeta().getDisplayName() != null) {
	                        if (p.getInventory().getChestplate().getItemMeta().getDisplayName().equalsIgnoreCase("§4Zeus Chestplate of Speed")) {
	                        }
	                      }
	                    }
	                    if (l.getType() != Material.AIR) {
	                      if (l.getItemMeta().getDisplayName() != null) {
	                        if (p.getInventory().getLeggings().getItemMeta().getDisplayName().equalsIgnoreCase("§4Zeus Leggings of Resistance")) {
	                        }
	                      }
	                    }
	                    if (b.getType() != Material.AIR) {
	                      if (b.getItemMeta().getDisplayName() != null) {
	                        if (p.getInventory().getBoots().getItemMeta().getDisplayName().equalsIgnoreCase("§cBunny Boots")) {
	                        }
	                      }
	                    }

	              }

	          }
	        }, 1L, 20L);
*/
    }



    @Override
    public void onDisable()
    {
        getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "[Sunucu]: Sunucu sistemleri durduruluyor. Sunucu kapanacak.");

        for(Player pm : Bukkit.getOnlinePlayers())
        {


            java.sql.Connection connect = null;
            PreparedStatement pre = null;

            try
            {



                Class.forName("com.mysql.jdbc.Driver");
                connect = DriverManager
                        .getConnection("jdbc:mysql://localhost/karakter" + "?useUnicode=true&characterEncoding=utf-8&user=root&password=test");

                String sql = "UPDATE karakter " + "SET KarakterAdi = ? "
                        + ", guc = ? , bilgelik = ? , atiklik = ? , buyu = ? , dayaniklilik = ? , karizma = ? , meslek = ? , MeslekXP = ? , MeslekSV = ?  " + " WHERE OyunAdi = ? ";

                pre = connect.prepareStatement(sql);
                pre.setString(1, EventDosyasi.EventsClass.getKarakterAd(pm.getPlayer().getName()));
                //	pre.setString(5, EventDosyasi.EventsClass.getCiftciXP(pm.getPlayer().getName()));
                pre.setString(2, EventDosyasi.EventsClass.getzarGuc(pm.getPlayer().getName()));


                pre.setString(3, EventDosyasi.EventsClass.getzarBilgelik(pm.getPlayer().getName()));
                pre.setString(4, EventDosyasi.EventsClass.getzarAtiklik(pm.getPlayer().getName()));
                pre.setString(5, EventDosyasi.EventsClass.getzarBuyu(pm.getPlayer().getName()));
                pre.setString(6,EventDosyasi.EventsClass.getzarDayaniklilik(pm.getPlayer().getName()));
                pre.setString(7, EventDosyasi.EventsClass.getzarKarizma(pm.getPlayer().getName()));
                pre.setString(8, EventDosyasi.EventsClass.getMeslek(pm.getPlayer().getName()));
                pre.setString(9, EventDosyasi.EventsClass.getMeslekXP(pm.getPlayer().getName()));
                pre.setString(10, EventDosyasi.EventsClass.getMeslekSeviye(pm.getPlayer().getName()));

                pre.setString(11, pm.getPlayer().getName());

                pre.executeUpdate();

                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN+"[Bilgi]: "+ChatColor.RED+ pm.getPlayer().getName() +ChatColor.GREEN+" isimli oyuncunun bilgileri reload sırasında veritabanına kaydedildi.");

            }
            catch (Exception d)
            {

                d.printStackTrace();
            }

            try
            {
                if(connect != null)
                {
                    pre.close();
                    connect.close();
                }
            }
            catch (SQLException s)
            {

                s.printStackTrace();
            }
        }
    }

    public static BaturPlugin getInstance()
    {
        if (plugin == null) plugin = new BaturPlugin();
        return plugin;
    }

    public void mysqlSetup()
    {


        String url="jdbc:mysql://localhost:3306/karakter?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&useSSL=true";
        String user="root";
        String pass="test";


        try
        {

            synchronized(this) {
                if(getConnection() != null && !getConnection().isClosed())
                {
                    return;
                }

                Class.forName("com.mysql.jdbc.Driver");

                setConnection((Connection) DriverManager.getConnection(url,user,pass));


                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Sunucu]: Sunucu sistemleri ile websitesi veritabanı arasındaki SQL bağlantısı başarıyla kuruldu.");
            }
        }
        catch (ClassNotFoundException | SQLException cd)
        {
            cd.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection)
    {
        this.connection = connection;
    }


    public static String getKarakterAd(String p)
    {
        File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + p.toLowerCase() + ".yml");
        FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
        String className = pConfig.getString("Karakter_Adi");
        System.out.println(className);
        return className;


    }


}




/*




public void loadConfig()
 {
	 getConfig().options().copyDefaults(true);
	 saveConfig();
 }



 loadConfig();


  /*setConnection((Connection) DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.username,this.password ) ); */












