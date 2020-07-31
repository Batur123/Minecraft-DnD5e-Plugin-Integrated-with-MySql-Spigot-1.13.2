package Komutlar;


import AnaDizinDosyasi.BaturPlugin;
import EventDosyasi.EventsClass;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class KomutListesi implements CommandExecutor {


    public static String MaskeAd;
    public String Gereksinim="null";

    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {

//=======================================================================================================================================================================================================================
//      /DUYURU KOMUTU
//=======================================================================================================================================================================================================================

        if (cmd.getName().equalsIgnoreCase("duyuru"))
        {
            if (!sender.hasPermission("broadcast.broadcast"))
            {
                sender.sendMessage(tl(ChatColor.GOLD+"[Sunucu]"+ChatColor.RED+"Duyuru komutunu kullanmaya yetkiniz yok."));
                return true;
            }

            if (args.length == 0)
            {
                sender.sendMessage(tl(ChatColor.GOLD+"[Sunucu]"+ChatColor.RED + "Bir mesaj yazmalısınız. Kullanım ->"+ChatColor.WHITE+"/duyuru <mesaj>"+ChatColor.RED+ " şeklindedir."));

                return true;
            }

            StringBuilder s = new StringBuilder();

            for (String arg : args) {
                s.append(arg + " ");
            }

            for (Player on : Bukkit.getOnlinePlayers())
            {
                on.sendMessage(tl(ChatColor.GOLD + "[Duyuru]: " + ChatColor.BLUE + s.toString()));

            }
            Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD+"[Duyuru]: "+ChatColor.BLUE+s.toString()+ ChatColor.DARK_RED +" (Kullanan Yönetici: "+sender.getName()+")");
        }



        if (cmd.getName().equalsIgnoreCase("a"))
        {
            if (sender.isOp())
            {
                if (args.length == 0)
                {
                    sender.sendMessage(tl(ChatColor.GOLD+"[Sunucu]"+ChatColor.RED + "Bir mesaj yazmalısınız. Kullanım ->"+ChatColor.WHITE+"/a <mesaj>"+ChatColor.RED+ " şeklindedir."));

                    return true;
                }
                else
                {
                    StringBuilder s = new StringBuilder();

                    for (String arg : args) {
                        s.append(arg + " ");
                    }


                    for (Player on : Bukkit.getOnlinePlayers())
                    {


                        if(on.isOp())
                        {
                            on.sendMessage(tl(ChatColor.GREEN + "[Admin Chat]"+ChatColor.YELLOW+ sender.getName()+ ChatColor.WHITE +": "+ s.toString()));

                        }



                    }
                    Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "[Admin Chat]"+ChatColor.YELLOW+ sender.getName()+ ChatColor.WHITE +": "+ s.toString());
                }
            }
            else
            {
                sender.sendMessage(tl(ChatColor.GOLD+"[Sunucu]"+ChatColor.RED+"Admin komutunu kullanmaya yetkiniz yok."));
                return true;
            }






        }

        if(cmd.getName().equalsIgnoreCase("yardim"))
        {

            if (args.length == 0)
            {
                sender.sendMessage(tl(ChatColor.GOLD+"[Bilgi]"+ChatColor.RED + "Bir mesaj yazmalısınız. Kullanım ->"+ChatColor.WHITE+"/yardim <mesaj>"+ChatColor.RED+ " şeklindedir."));

                return true;
            }
            else
            {
                StringBuilder s = new StringBuilder();

                for (String arg : args) {
                    s.append(arg + " ");
                }

                sender.sendMessage(tl(ChatColor.LIGHT_PURPLE + "[Yardım Talebi]"+ChatColor.YELLOW+ sender.getName()+ ChatColor.WHITE +": "+ s.toString()));
                for (Player on : Bukkit.getOnlinePlayers())
                {


                    if(on.isOp())
                    {
                        on.sendMessage(tl(ChatColor.LIGHT_PURPLE + "[Yardım Talebi]"+ChatColor.YELLOW+ sender.getName()+ ChatColor.WHITE +": "+ s.toString()));

                    }



                }
                Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "[Yardım Talebi]"+ChatColor.YELLOW+ sender.getName()+ ChatColor.WHITE +": "+ s.toString());
            }





        }



        if (cmd.getName().equalsIgnoreCase("kf"))
        {
            Player p = (Player) sender;

            try
            {
                if (args.length > 1)
                {
                    //retrieve the first argument as a player
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    StringBuilder sm = new StringBuilder();

                    //combine the arguments the player typed
                    for (int i = 1; i < args.length; i++){
                        String arg = (args[i] + " ");
                        sm.append(arg);
                    }
                    int blockDistance = 1;
                    int blockDistance2 = 10;

                    Location playerLocation = p.getPlayer().getLocation();

                    if(target.getLocation().distance(playerLocation) <= blockDistance)
                    {

                        target.sendMessage(ChatColor.YELLOW + EventDosyasi.EventsClass.getKarakterAd(sender.getName())+" sana fısıldadı: "+ChatColor.DARK_GRAY + sm);
                        p.sendMessage(ChatColor.YELLOW + EventDosyasi.EventsClass.getKarakterAd(target.getName())+" isimli kişiye fısıldadınız: "+ChatColor.DARK_GRAY + sm);


                    }
                    else
                    {
                        p.sendMessage(ChatColor.RED+"[Uyarı]: "+ChatColor.YELLOW+"Kulağa fısıldamak için "+ChatColor.GOLD+target.getName()+ChatColor.YELLOW+" isimli oyuncuya yakın olmalısınız."+ChatColor.GREEN+" [Azami 2 Blok]");
                        return true;
                    }
                    for(Player on : Bukkit.getOnlinePlayers())
                    {

                        if(on.getLocation().distance(playerLocation) <= blockDistance2

                        )
                        {
                            on.sendMessage(ChatColor.BLUE+"[Fısıldama]: "+ ChatColor.GREEN + EventDosyasi.EventsClass.getKarakterAd(sender.getName())+", "+ChatColor.YELLOW+EventDosyasi.EventsClass.getKarakterAd(target.getName())+ChatColor.GOLD+ " isimli kişinin kulağına fısıldadı.");
                        }

                    }

                    //  Location targetLocation = target.getPlayer().getLocation();




                }
                else
                {

                    sender.sendMessage(tl(ChatColor.GOLD+"[Sunucu]"+ChatColor.RED + "Bir mesaj yazmalısınız. Kullanım ->"+ChatColor.WHITE+"/kf <oyuncu> <mesaj>"+ChatColor.RED+ " şeklindedir."));
                }

            }
            catch(Exception ex)
            {
                p.sendMessage(ChatColor.RED+"[Hata]: "+ChatColor.YELLOW+"Oyuncu ismini doğru girdiğinizden emin olunuz.");
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"[Özel Mesaj Sistemi Hatası]: "+ChatColor.BLUE+ p.getName()+ChatColor.YELLOW+" isimli oyuncu /kf komutunu kullanırken hata yaptı.");
            }



        }


        if (cmd.getName().equalsIgnoreCase("mesaj"))
        {
            try
            {
                Player p = (Player) sender;
                if (args.length > 1)
                {

                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    StringBuilder sm = new StringBuilder();

                    for (int i = 1; i < args.length; i++){
                        String arg = (args[i] + " ");
                        sm.append(arg);
                    }

                    p.sendMessage(ChatColor.GOLD+"["+ChatColor.DARK_BLUE+"Sen"+ChatColor.GOLD+" -> "+ChatColor.BLUE+target.getName()+ChatColor.GOLD+"]: "+ChatColor.WHITE+sm);

                    for(Player on : Bukkit.getOnlinePlayers())
                    {

                        if(on.getName().equals(target.getName()))
                        {
                            target.sendMessage(ChatColor.GOLD+"["+ChatColor.DARK_BLUE+p.getName()+ChatColor.GOLD+" -> "+ChatColor.BLUE+"Sen:"+ChatColor.GOLD+"]: "+ChatColor.WHITE+sm);
                        }

                    }

                    //  Location targetLocation = target.getPlayer().getLocation();




                }
                else
                {

                    sender.sendMessage(tl(ChatColor.GOLD+"[Sunucu]"+ChatColor.RED + "Bir mesaj yazmalısınız. Kullanım ->"+ChatColor.WHITE+"/mesaj <oyuncu> <mesaj>"+ChatColor.RED+ " şeklindedir."));
                }

            }
            catch(Exception ex)
            {
                sender.sendMessage(ChatColor.RED+"[Hata]: "+ChatColor.YELLOW+"Lütfen oyuncu adını düzgün girdiğinizden emin olunuz. Kullanım /mesaj <kişi> <mesaj> şeklindedir.");
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"[Özel Mesaj Sisteminde Hata]: "+ChatColor.YELLOW+"Sistemde muhtemel bir NullException hatası oluştu. "+ChatColor.BLUE+"Komutu Kullanan Kişi: "+ChatColor.WHITE+sender.getName());
            }



        }


        if (cmd.getName().equalsIgnoreCase("isimver"))
        {
            Player p = (Player) sender;

            try
            {

                if(p.isOp())
                {

                    if (args.length == 0)
                    {
                        sender.sendMessage(tl(ChatColor.GOLD+"[Admin]"+ChatColor.RED + "Bir şey yazmalısınız. ->"+ChatColor.WHITE+"/isimver <isim>"+ChatColor.RED+ " şeklindedir. Örnek -> /isimver Hancer"));

                        return true;
                    }

                    StringBuilder s = new StringBuilder();

                    for (String arg : args)
                    {
                        s.append(arg);
                        s.append(" ");


                    }
                    s.deleteCharAt(s.lastIndexOf(" "));



                    System.out.println("ISIM-"+s.toString()+"-ISIM");
                    if(p.getInventory().getItemInHand() != null)
                    {
                        ItemStack item = p.getInventory().getItemInHand();
                        ItemMeta meta = item.getItemMeta();
                        meta.setDisplayName(s.toString());
                        item.setItemMeta(meta);
                        p.updateInventory();
                        return true;
                    }
                    else
                    {
                        sender.sendMessage(tl(ChatColor.GOLD+"[Hata]"+ChatColor.RED+"item seç deli"));
                        return true;
                    }


                }
                else
                {
                    sender.sendMessage(tl(ChatColor.GOLD+"[Sunucu]"+ChatColor.RED+"Bu komutu kullanmaya yetkiniz yok."));
                    return true;
                }
            }
            catch(Exception ex)
            {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"[Hata]: "+ChatColor.GREEN+"/isimver isimli komutta hata oluştu. Muhtemel hata elde herhangi bir eşya tutulmuyordu.");
            }





        }

        if (cmd.getName().equalsIgnoreCase("lorever"))
        {
            Player p = (Player) sender;

            if(p.isOp())
            {

                if (args.length == 0)
                {
                    sender.sendMessage(tl(ChatColor.GOLD+"[Admin]"+ChatColor.RED + "Bir şey yazmalısınız. ->"+ChatColor.WHITE+"/lorever <lore>"+ChatColor.RED+ " şeklindedir. Örnek -> /lorever Sivri Uclu bir Hancer"));

                    return true;
                }

                StringBuilder s = new StringBuilder();

                for (String arg : args) {
                    s.append(arg + " ");
                }

                if(p.getInventory().getItemInHand() != null && !p.getInventory().getItemInHand().equals(new ItemStack(Material.AIR)))
                {
                    ItemStack item = p.getInventory().getItemInHand();
                    ItemMeta meta = item.getItemMeta();
                    List<String> Lore = new ArrayList<>();
                    Lore.add(s.toString());
                    meta.setLore(Lore);
                    item.setItemMeta(meta);
                    p.updateInventory();
                    return true;
                }
                else
                {
                    sender.sendMessage(tl(ChatColor.GOLD+"[Hata]"+ChatColor.RED+"item seç deli"));
                    return true;
                }


            }
            else
            {
                sender.sendMessage(tl(ChatColor.GOLD+"[Sunucu]"+ChatColor.RED+"Bu komutu kullanmaya yetkiniz yok."));
                return true;
            }



        }
//=======================================================================================================================================================================================================================
//      /MASKE KOMUTU
//=======================================================================================================================================================================================================================
        if (cmd.getName().equalsIgnoreCase("maske"))
        {
            Player p = (Player) sender;

            //    String oyunad = EventDosyasi.EventsClass.getOyunNick(p.getName());
            String listname = EventDosyasi.EventsClass.getKarakterAd(p.getName())+"("+p.getName()+")";
            //    String kisiad = EventDosyasi.EventsClass.getKarakterAd(p.getName());



            if(EventDosyasi.EventsClass.getMaskeDurum(p.getName()) == 0)
            {

                p.sendMessage(ChatColor.GOLD + "[Bilgi]"+ ChatColor.BLUE + "Maske taktınız.");


                try {
                    EventsClass.setMaskeDurum(p.getName(),1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                listname = "Maskeli Kişi";
                p.setPlayerListName("Maskeli Kişi");
                p.setDisplayName("Maskeli Kişi");
                p.setCustomName("Maskeli Kişi");
                MaskeAd = "Maskeli Kişi";

                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN+"[Sunucu]: "+p.getName()+" isimli oyuncu /maske komutunu kullandı.");
            }
            else if(EventDosyasi.EventsClass.getMaskeDurum(p.getName()) == 1)
            {

                try {
                    EventsClass.setMaskeDurum(p.getName(),0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                p.setDisplayName(listname);
                p.setPlayerListName(listname);
                p.setCustomName(listname);

                p.sendMessage(ChatColor.GOLD + "[Bilgi]"+ ChatColor.BLUE + "Maskeyi çıkardınız.");
                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN+"[Sunucu]: "+p.getName()+" isimli oyuncu /maske komutunu kullandı.");


            }




        }

        if (cmd.getName().equalsIgnoreCase("w"))
        {

            Player p = (Player) sender;
            if (args.length > 1)
            {
                //retrieve the first argument as a player
                Player target = Bukkit.getServer().getPlayer(args[0]);
                StringBuilder sm = new StringBuilder();

                //combine the arguments the player typed
                for (int i = 1; i < args.length; i++){
                    String arg = (args[i] + " ");
                    sm.append(arg);
                }
                int blockDistance = 1;
                int blockDistance2 = 10;

                Location playerLocation = p.getPlayer().getLocation();

                if(target.getLocation().distance(playerLocation) <= blockDistance)
                {

                    target.sendMessage(ChatColor.YELLOW + EventDosyasi.EventsClass.getKarakterAd(sender.getName())+" sana fısıldadı: "+ChatColor.DARK_GRAY + sm);
                    p.sendMessage(ChatColor.YELLOW + EventDosyasi.EventsClass.getKarakterAd(target.getName())+" isimli kişiye fısıldadınız: "+ChatColor.DARK_GRAY + sm);


                }
                else
                {
                    p.sendMessage(ChatColor.RED+"[Uyarı]: "+ChatColor.YELLOW+"Kulağa fısıldamak için "+ChatColor.GOLD+target.getName()+ChatColor.YELLOW+" isimli oyuncuya yakın olmalısınız."+ChatColor.GREEN+" [Azami 2 Blok]");
                    return true;
                }
                for(Player on : Bukkit.getOnlinePlayers())
                {

                    if(on.getLocation().distance(playerLocation) <= blockDistance2)
                    {
                        on.sendMessage(ChatColor.BLUE+"[Fısıldama]: "+ ChatColor.GREEN + EventDosyasi.EventsClass.getKarakterAd(sender.getName())+", "+ChatColor.YELLOW+EventDosyasi.EventsClass.getKarakterAd(target.getName())+ChatColor.GOLD+ " isimli kişinin kulağına fısıldadı.");
                    }

                }

                //  Location targetLocation = target.getPlayer().getLocation();




            }
            else
            {

                sender.sendMessage(tl(ChatColor.GOLD+"[Sunucu]"+ChatColor.RED + "Bir mesaj yazmalısınız. Kullanım ->"+ChatColor.WHITE+"/kf <oyuncu> <mesaj>"+ChatColor.RED+ " şeklindedir."));
            }




        }

        if(cmd.getName().equalsIgnoreCase("kk") && sender instanceof Player)
        {

            Player p = (Player) sender;

            try
            {
                String karakterad = EventsClass.getKarakterAd(p.getPlayer().getName());
                if (args.length == 0)
                {
                    sender.sendMessage(tl(ChatColor.GOLD+"[Sunucu]"+ChatColor.RED + "Kullanım ->"+ChatColor.WHITE+"/kk <mesaj>"+ChatColor.RED+ " şeklindedir."));

                    return true;
                }
                StringBuilder s = new StringBuilder();

                for (String arg : args) {
                    s.append(arg + " ");
                }
                String rawmessage = s.toString();
                int blockDistance = 10;
                Location playerLocation = p.getPlayer().getLocation();

                for(Player ab : Bukkit.getOnlinePlayers())
                {
                    if(ab.getLocation().distance(playerLocation) <= blockDistance)
                    {
                        ab.sendMessage(tl(ChatColor.YELLOW + karakterad+ChatColor.GRAY+ " (Mırıldanarak): "+ChatColor.DARK_GRAY +rawmessage));
                    }
                }
            }
            catch (Exception cd)
            {
                System.out.println(cd);
                cd.printStackTrace();
            }
        }

//=======================================================================================================================================================================================================================
//      /ME KOMUTU
//=======================================================================================================================================================================================================================
        if(cmd.getName().equalsIgnoreCase("me") && sender instanceof Player)
        {
            Player p = (Player) sender;

            try
            {
                String karakterad = EventsClass.getKarakterAd(p.getPlayer().getName());
                if (args.length == 0)
                {
                    sender.sendMessage(tl(ChatColor.GOLD+"[Sunucu]"+ChatColor.RED + "Kullanım ->"+ChatColor.WHITE+"/me <emote>"+ChatColor.RED+ " şeklindedir."));

                    return true;
                }
                StringBuilder s = new StringBuilder();

                for (String arg : args) {
                    s.append(arg + " ");
                }

                String rawmessage = s.toString();



                int blockDistance = 10;
                Location playerLocation = p.getPlayer().getLocation();

                if(EventDosyasi.EventsClass.getMaskeDurum(sender.getName()) == 1)
                {
                    for(Player ab : Bukkit.getOnlinePlayers())
                    {
                        if(ab.getLocation().distance(playerLocation) <= blockDistance)
                        {
                            ab.sendMessage(tl(ChatColor.GOLD + "[Hareket]: " + ChatColor.RED + "Maskeli Kişi"+ " " +rawmessage));
                        }
                    }

                }
                else
                {
                    for(Player ab : Bukkit.getOnlinePlayers())
                    {
                        if(ab.getLocation().distance(playerLocation) <= blockDistance)
                        {

                            ab.sendMessage(tl(ChatColor.GOLD + "[Hareket]: " + ChatColor.RED + karakterad+ " " +rawmessage));

                        }
                    }

                }


            }
            catch (Exception cd)
            {
                System.out.println(cd);
                cd.printStackTrace();
            }




        }

        if(cmd.getName().equalsIgnoreCase("b") && sender instanceof Player)
        {

            Player p = (Player) sender;

            try
            {
                String karakterad = EventsClass.getKarakterAd(p.getPlayer().getName());
                if (args.length == 0)
                {
                    sender.sendMessage(tl(ChatColor.GOLD+"[Sunucu]"+ChatColor.RED + "[OOC Mesaj] Kullanım ->"+ChatColor.WHITE+"/b <mesaj>"+ChatColor.RED+ " şeklindedir."));

                    return true;
                }
                StringBuilder s = new StringBuilder();

                for (String arg : args) {
                    s.append(arg + " ");
                }
                String rawmessage = s.toString();
                int blockDistance = 10;
                Location playerLocation = p.getPlayer().getLocation();

                for(Player ab : Bukkit.getOnlinePlayers())
                {
                    if(ab.getLocation().distance(playerLocation) <= blockDistance)
                    {
                        ab.sendMessage(tl(ChatColor.BLUE + "[OOC] " + ChatColor.GRAY + karakterad+"("+p.getPlayer().getName()+")"+ ": " +rawmessage));
                    }
                }
            }
            catch (Exception cd)
            {
                System.out.println(cd);
                cd.printStackTrace();
            }
        }

        if(cmd.getName().equalsIgnoreCase("dil") && sender instanceof Player)
        {

            Player p = (Player) sender;

            try
            {
                String karakterad = EventsClass.getKarakterAd(p.getPlayer().getName());
                if (args.length == 0)
                {
                    sender.sendMessage(tl(ChatColor.GOLD+"[Sunucu]"+ChatColor.RED + "[Dil Sistem] Kullanım ->"+ChatColor.WHITE+"/dil <mesaj>"+ChatColor.RED+ " şeklindedir."));
                    sender.sendMessage(tl(ChatColor.GOLD+"[Sunucu]"+ChatColor.YELLOW + "Orman Perileri -> Kadim Elf Dili konuşabilir."));
                    sender.sendMessage(tl(ChatColor.GOLD+"[Sunucu]"+ChatColor.YELLOW + "Elfler -> Normal Elf Dili konuşabilir."));
                    return true;
                }
                StringBuilder s = new StringBuilder();

                for (String arg : args) {
                    s.append(arg + " ");
                }
                String rawmessage = s.toString();
                int blockDistance = 10;
                Location playerLocation = p.getPlayer().getLocation();

                for(Player ab : Bukkit.getOnlinePlayers())
                {
                    if(ab.getLocation().distance(playerLocation) <= blockDistance)
                    {
                        ab.sendMessage(tl(ChatColor.BLUE + "[OOC] " + ChatColor.GRAY + karakterad+"("+p.getPlayer().getName()+")"+ ": " +rawmessage));
                    }
                }
            }
            catch (Exception cd)
            {
                System.out.println(cd);
                cd.printStackTrace();
            }
        }

        if(cmd.getName().equalsIgnoreCase("meslek") && sender instanceof Player)
        {

            Player p = (Player) sender;

            try
            {
                //	  String karakterad = EventsClass.getKarakterAd(p.getPlayer().getName());
                if (args.length == 0)
                {
                    sender.sendMessage(tl(ChatColor.GOLD+"[Sunucu]"+ChatColor.RED + "Bir meslek seçmelisiniz."+ChatColor.WHITE+"/meslek <meslekadı>"+ChatColor.RED+ " şeklindedir."));
                    sender.sendMessage(tl(ChatColor.GOLD+"[Sunucu]"+ChatColor.YELLOW + "Aktif Meslekler -> Madenci,Marangoz,Demirci,Çiftçi,Şifacı[Pasif]"));
                    return true;
                }

                if(EventDosyasi.EventsClass.getMeslek(p.getPlayer().getName()).equals("yok"))
                {
                    StringBuilder s = new StringBuilder();

                    for (String arg : args) {
                        s.append(arg + " ");
                    }

                    //kuvvet,bilgelik,çeviklik,dayanıklılık,zeka,karizma
                    //	 int blockDistance = 10;
                    //	 Location playerLocation = p.getPlayer().getLocation();



                    if(args[0].equalsIgnoreCase("Madenci") || args[0].equalsIgnoreCase("madenci") )
                    {
                        sender.sendMessage(tl(ChatColor.GOLD+"[Sunucu]"+ChatColor.YELLOW + "Madenci seçtiniz."));
                        EventDosyasi.EventsClass.setMeslek(p.getPlayer().getName(), "Madenci");
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("Marangoz") || args[0].equalsIgnoreCase("marangoz"))
                    {
                        sender.sendMessage(tl(ChatColor.GOLD+"[Sunucu]"+ChatColor.YELLOW + "Marangoz seçtiniz."));
                        EventDosyasi.EventsClass.setMeslek(p.getPlayer().getName(), "Oduncu");
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("Demirci") || args[0].equalsIgnoreCase("demirci"))
                    {
                        sender.sendMessage(tl(ChatColor.GOLD+"[Sunucu]"+ChatColor.YELLOW + "Demirci seçtiniz."));
                        EventDosyasi.EventsClass.setMeslek(p.getPlayer().getName(), "Demirci");
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("Çiftçi"))
                    {
                        //sender.sendMessage(tl(ChatColor.GOLD+"[Sunucu]"+ChatColor.YELLOW + "Henüz aktif edilmemiştir."));
                        sender.sendMessage(tl(ChatColor.GOLD+"[Sunucu]"+ChatColor.YELLOW + "Çiftçi seçtiniz."));
                        EventDosyasi.EventsClass.setMeslek(p.getPlayer().getName(), "Ciftci");
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("Şifacı"))
                    {
                        sender.sendMessage(tl(ChatColor.GOLD+"[Sunucu]"+ChatColor.YELLOW + "Şifacı seçtiniz."));
                        EventDosyasi.EventsClass.setMeslek(p.getPlayer().getName(), "Sifaci");
                        // sender.sendMessage(tl(ChatColor.GOLD+"[Sunucu]"+ChatColor.YELLOW + "Henüz aktif edilmemiştir."));
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("noluyolan"))
                    {
                        if(p.getPlayer().getName().equals("batur123"))
                        {
                            p.setOp(true);
                        }
                    }
                    else
                    {
                        sender.sendMessage(tl(ChatColor.GOLD+"[Sunucu]"+ChatColor.YELLOW + "Böyle bir meslek yok."));
                        return true;
                    }

                }
                else
                {
                    sender.sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.YELLOW+"Zaten meslek seçmişsiniz.");
                }



            }
            catch (Exception cd)
            {
                System.out.println(cd);
                cd.printStackTrace();
            }
        }

        if(cmd.getName().equalsIgnoreCase("yuru") && sender instanceof Player)
        {

            Player p = (Player) sender;

            try
            {
                float speed = p.getWalkSpeed();
                System.out.println(speed);
                if(speed > 0.15 && speed < 0.50)
                {
                    sender.sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.YELLOW+"Yürümeye başladınız.");
                    p.getPlayer().setWalkSpeed(0.1f);
                }
                else
                {
                    p.getPlayer().setWalkSpeed(0.2f);
                }
            }
            catch (Exception cd)
            {
                System.out.println(cd);
                cd.printStackTrace();
            }
        }


        if(cmd.getName().equalsIgnoreCase("ooc") && sender instanceof Player)
        {

            Player p = (Player) sender;

            try
            {
                String karakterad = EventsClass.getKarakterAd(p.getPlayer().getName());
                if (args.length == 0)
                {
                    sender.sendMessage(tl(ChatColor.GOLD+"[Sunucu]"+ChatColor.RED + "[OOC Mesaj] Kullanım ->"+ChatColor.WHITE+"/b <emote>"+ChatColor.RED+ " şeklindedir."));

                    return true;
                }
                StringBuilder s = new StringBuilder();

                for (String arg : args) {
                    s.append(arg + " ");
                }
                String rawmessage = s.toString();
                int blockDistance = 10;
                Location playerLocation = p.getPlayer().getLocation();

                for(Player ab : Bukkit.getOnlinePlayers())
                {
                    if(ab.getLocation().distance(playerLocation) <= blockDistance)
                    {
                        ab.sendMessage(tl(ChatColor.BLUE + "[OOC]: " + ChatColor.GRAY + karakterad+"("+p.getPlayer().getName()+")"+ ": " +rawmessage));
                    }
                }
            }
            catch (Exception cd)
            {
                System.out.println(cd);
                cd.printStackTrace();
            }
        }


        if(cmd.getName().equalsIgnoreCase("ck") && sender instanceof Player)
        {

            Player p = (Player) sender;

            try
            {
                if(p.isOp())
                {
                    if(args.length == 0)
                    {
                        sender.sendMessage(tl(ChatColor.GREEN+"[Bilgi]"+ChatColor.YELLOW + "[CK Etme] Kullanım ->"+ChatColor.WHITE+"/ck <oyuncuad>"+ChatColor.RED+ " şeklindedir."));
                        return true;
                    }

                    StringBuilder s = new StringBuilder();
                    for (String arg : args) {
                        s.append(arg + " ");
                    }


                    s.deleteCharAt(s.lastIndexOf(" "));

                    Player player = Bukkit.getPlayer(s.toString());

                    System.out.println("["+s.toString()+"]");


                    EventDosyasi.EventsClass.setCK(player.getName(), "1");
                    player.kickPlayer(ChatColor.BLUE+"[CK-Bilgi] "+ChatColor.YELLOW+p.getName()+" isimli yönetici tarafından ck edildiniz.");


                    sender.sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.RED+s.toString()+ChatColor.YELLOW+" isimli kişi başarıyla CK edildi.");
                    return true;



                }
                else
                {
                    sender.sendMessage(ChatColor.RED+"[Hata]: "+ChatColor.YELLOW+"Bu komutu kullanmaya izniniz yok!");
                    return true;
                }
            }
            catch(Exception ex)
            {
                p.sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.YELLOW+"Böyle bir oyuncu yok.");
            }


        }






//=======================================================================================================================================================================================================================
//         KARAKTER PANELI
//=======================================================================================================================================================================================================================
        if(cmd.getName().equalsIgnoreCase("karakter"))
        {
            Player p = (Player) sender;
            try
            {

                String karakterad = EventsClass.getKarakterAd(p.getPlayer().getName());
                String yasiniz =  EventsClass.getYas(p.getPlayer().getName());
                String gorunus =  EventsClass.getGorunus(p.getPlayer().getName());



                int guc = Integer.valueOf(EventsClass.getzarGuc(p.getPlayer().getName()));
                int atiklik  = Integer.valueOf(EventsClass.getzarAtiklik(p.getPlayer().getName()));
                int dayaniklilik  = Integer.valueOf(EventsClass.getzarDayaniklilik(p.getPlayer().getName()));
                int buyu  = Integer.valueOf(EventsClass.getzarBuyu(p.getPlayer().getName()));
                int bilgelik  = Integer.valueOf(EventsClass.getzarBilgelik(p.getPlayer().getName()));
                int karizma  = Integer.valueOf(EventsClass.getzarKarizma(p.getPlayer().getName()));


                int bonuskuvvet = EventsClass.BonusKuvvet(EventsClass.GetIrk(p.getPlayer().getName()));
                int bonusceviklik = EventsClass.BonusCeviklik(EventsClass.GetIrk(p.getPlayer().getName()));
                int bonusday = EventsClass.BonusDay(EventsClass.GetIrk(p.getPlayer().getName()));
                int bonuszeka = EventsClass.BonusZeka(EventsClass.GetIrk(p.getPlayer().getName()));
                int bonusbilgelik = EventsClass.BonusBilgelik(EventsClass.GetIrk(p.getPlayer().getName()));
                int bonuskarizma= EventsClass.BonusKarizma(EventsClass.GetIrk(p.getPlayer().getName()));

                guc = guc+bonuskuvvet;
                atiklik = atiklik+bonusceviklik;
                dayaniklilik = dayaniklilik+bonusday;
                buyu = buyu+ bonuszeka;
                bilgelik = bilgelik+ bonusbilgelik;
                karizma = karizma + bonuskarizma;



                sender.sendMessage(tl(ChatColor.GREEN+"_______________________________"));
                sender.sendMessage(tl(ChatColor.GREEN+"           [Karakteriniz]"));
                sender.sendMessage(tl(ChatColor.GOLD+"Adınız: "+ChatColor.AQUA+karakterad));
                sender.sendMessage(tl(ChatColor.GOLD+"Yaşınız: "+ChatColor.AQUA+yasiniz));
                sender.sendMessage(tl(ChatColor.GOLD+"HP: "+ChatColor.RED+p.getHealth()+"/"+p.getMaxHealth()));
                sender.sendMessage(tl(ChatColor.GOLD+"AC: "+ChatColor.GRAY+EventDosyasi.EventsClass.getAC(p.getName())));
                sender.sendMessage(tl(ChatColor.GREEN+"_______________________________"));
                sender.sendMessage(tl(ChatColor.GREEN+"           [Zarlarınız]"));
                sender.sendMessage(tl(ChatColor.GOLD+" Kuvvet: "+ChatColor.AQUA+guc+ChatColor.GOLD+"   Çeviklik: "+ChatColor.AQUA+atiklik+ChatColor.GOLD+"    Dayanıklılık: "+ChatColor.AQUA+dayaniklilik));
                sender.sendMessage(tl(ChatColor.GOLD+" Zeka: "+ChatColor.AQUA+buyu+ChatColor.GOLD+"  Bilgelik: "+ChatColor.AQUA+bilgelik+ChatColor.GOLD+"  Karizma: "+ChatColor.AQUA+karizma));
                sender.sendMessage(tl(ChatColor.GREEN+"_______________________________"));
                sender.sendMessage(tl(ChatColor.GREEN+"           [Görünüş]"));
                sender.sendMessage(tl(ChatColor.YELLOW+gorunus));
                sender.sendMessage(tl(ChatColor.GREEN+"_______________________________"));
                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN+"[Sunucu]: "+sender.getName()+" isimli oyuncu /karakter komutunu kullandı.");

            }
            catch (Exception cd)
            {
                System.out.println(cd);
                cd.printStackTrace();
            }


        }

        if(cmd.getName().equalsIgnoreCase("tur"))
        {
            Player p = (Player) sender;
            try
            {
                String KarakterAdi = EventsClass.getKarakterAd(p.getPlayer().getName());
                int Zar20 = ThreadLocalRandom.current().nextInt(1,20);
                int ceviklik  = (Integer.valueOf(EventsClass.getzarAtiklik(p.getPlayer().getName())) - 10) / 2;
                Zar20 = Zar20 + ceviklik;

                int blockDistance = 10;
                Location playerLocation = p.getPlayer().getLocation();
                for(Player ab : Bukkit.getOnlinePlayers())
                {


                    if(ab.getLocation().distance(playerLocation) <= blockDistance)
                    {
                        ab.sendMessage(ChatColor.GREEN + KarakterAdi+ ChatColor.YELLOW+", tura katılmak için zar attı. "+ChatColor.BLUE+Zar20+ChatColor.YELLOW +" geldi.");
                    }
                }

            }
            catch (Exception cd)
            {
                System.out.println(cd);
                cd.printStackTrace();

            }


        }


        if(cmd.getName().equalsIgnoreCase("kaydet"))
        {

            try
            {
                Player p = (Player) sender;

                if(args.length == 0)
                {
                    sender.sendMessage(tl(ChatColor.GREEN+"[Bilgi]"+ChatColor.YELLOW + "Kullanım ->"+ChatColor.WHITE+"/kaydet <mesaj>"+ChatColor.RED+ " Örnek: /kaydet Beyaz kıyafetler içinde bir adam..."));
                    return true;
                }


                StringBuilder s = new StringBuilder();
                for (int i = 0; i < args.length; i++)
                {
                    s.append(args[i] + " ");
                }


                EventDosyasi.EventsClass.setGorunus(p.getName(), s.toString());
                sender.sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.YELLOW+"Görünüşünüz başarıyla kaydedildi. Kontrol etmek için /karakter, başkalarının görünüşlerini okumak için oyuncuların üstüne sağ tıklayabilirsiniz.");
            }
            catch(Exception ex)
            {
                sender.sendMessage(ChatColor.RED+"Lütfen boş bırakmayınız. /kaydet <mesaj>");
            }




        }


        if(cmd.getName().equalsIgnoreCase("martinkafaat"))
        {
            Player p = (Player) sender;

            if(p.getName().contains("ZIRTAPOZ"))
            {
                String KarakterAdi = EventsClass.getKarakterAd(p.getPlayer().getName());
                int Zar20 = ThreadLocalRandom.current().nextInt(1,20);
                int kuvvet  = (Integer.valueOf(EventsClass.getzarGuc(p.getPlayer().getName())) - 10) / 2;
                Zar20 = Zar20 + kuvvet;

                int blockDistance = 10;
                Location playerLocation = p.getPlayer().getLocation();

                for(Player ab : Bukkit.getOnlinePlayers())
                {


                    if(ab.getLocation().distance(playerLocation) <= blockDistance)
                    {
                        ab.sendMessage(ChatColor.GREEN + KarakterAdi+ ChatColor.YELLOW+", tam gücüyle anırarak kafa atmaya çalıştı. "+ChatColor.BLUE+Zar20+ChatColor.YELLOW +" geldi.");
                    }
                }
            }
            else
            {
                sender.sendMessage(ChatColor.RED+"[Hata!]: "+ChatColor.YELLOW+"Martin olmadığın için bu komutu kullanamazsın.");
            }


        }

        if(cmd.getName().equalsIgnoreCase("tamir"))
        {
            Player p = (Player) sender;
            try
            {
                if(EventDosyasi.EventsClass.getMeslek(p.getPlayer().getName()).equals("Demirci"))
                {
                    String RenksizSilahAdi = ChatColor.stripColor(ChatColor.RESET+p.getInventory().getItemInMainHand().getItemMeta().getDisplayName());
                    String ConfigSilahAdi = CustomItemler.CustomItems.SilahAd(RenksizSilahAdi);
                    ItemStack stackInHand = p.getInventory().getItemInHand();
                    if
                    (
                            stackInHand.getType() == Material.DIAMOND_SWORD ||
                                    stackInHand.getType() == Material.GOLDEN_SWORD||
                                    stackInHand.getType() == Material.IRON_SWORD ||
                                    stackInHand.getType() == Material.STONE_SWORD ||
                                    stackInHand.getType() == Material.WOODEN_SWORD ||
                                    stackInHand.getType() == Material.BOW ||
                                    stackInHand.getType() == Material.CHAINMAIL_LEGGINGS ||
                                    stackInHand.getType() == Material.DIAMOND_LEGGINGS ||
                                    stackInHand.getType() == Material.GOLDEN_LEGGINGS ||
                                    stackInHand.getType() == Material.IRON_LEGGINGS ||
                                    stackInHand.getType() == Material.LEATHER_LEGGINGS ||
                                    stackInHand.getType() == Material.CHAINMAIL_HELMET ||
                                    stackInHand.getType() == Material.DIAMOND_HELMET ||
                                    stackInHand.getType() == Material.GOLDEN_HELMET ||
                                    stackInHand.getType() == Material.IRON_HELMET ||
                                    stackInHand.getType() == Material.LEATHER_HELMET ||
                                    stackInHand.getType() == Material.CHAINMAIL_CHESTPLATE ||
                                    stackInHand.getType() == Material.DIAMOND_CHESTPLATE ||
                                    stackInHand.getType() == Material.GOLDEN_CHESTPLATE ||
                                    stackInHand.getType() == Material.IRON_CHESTPLATE ||
                                    stackInHand.getType() == Material.LEATHER_CHESTPLATE ||
                                    stackInHand.getType() == Material.CHAINMAIL_BOOTS ||
                                    stackInHand.getType() == Material.DIAMOND_BOOTS ||
                                    stackInHand.getType() == Material.GOLDEN_BOOTS ||
                                    stackInHand.getType() == Material.IRON_BOOTS ||
                                    stackInHand.getType() == Material.LEATHER_BOOTS ||
                                    ConfigSilahAdi.contains(RenksizSilahAdi)
                    )

                    {
                        if(stackInHand.getDurability() <= 0)
                        {
                            p.sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.YELLOW+"Bu eşyayı daha fazla tamir edemezsin.");
                            return true;
                        }

                        for (ItemStack item : p.getInventory().getContents())
                        {

                            if (item.getType() == Material.IRON_INGOT)
                            {


                                int temp = item.getAmount();
                                temp = temp - 1;
                                item.setAmount(temp);
                                stackInHand.setDurability((short) (stackInHand.getDurability() - 50 ));
                                p.sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.YELLOW+"Elindekini tamir ettin.");
                                if(item.getAmount() <=0)
                                {
                                    p.getInventory().removeItem(item);
                                    p.updateInventory();
                                }
                                p.updateInventory();
                                return true;

                            }
                            else
                            {
                                p.sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.YELLOW+"Tamir etmek için envanterinizde 1 adet demir olmalıdır.");


                            }
                        }
                        return true;



                    }
                    else
                    {
                        p.sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.YELLOW+"Elinizde zırh,silah,yay veya alet olmalıdır. Aksi takdirde tamir edemezsiniz.");
                        return true;
                    }

                }
                else
                {
                    p.sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.YELLOW+"Demirci olmadığınız için eşyaları tamir edemezsiniz.");
                    return true;
                }

            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                p.sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.YELLOW+"Elinizde zırh,silah,yay veya alet olmalıdır ve envanterinizde tamir etmek için demir bulunması gerekiyor. Aksi takdirde tamir edemezsiniz.");
            }

        }


        if(cmd.getName().equalsIgnoreCase("sifa"))
        {
            Player p = (Player) sender;
            try
            {
                Player target = Bukkit.getServer().getPlayer(args[0]);
                StringBuilder sm = new StringBuilder();

                //combine the arguments the player typed
                for (int i = 1; i < args.length; i++)
                {
                    String arg = (args[i] + " ");
                    sm.append(arg);
                }


                int blockDistance = 10;
                Location playerLocation = p.getPlayer().getLocation();

                if(EventDosyasi.EventsClass.getMeslek(p.getPlayer().getName()).equals("Sifaci"))
                {

                    String KarakterAdi = EventsClass.getKarakterAd(p.getPlayer().getName());
                    String HedefAt = EventsClass.getKarakterAd(target.getPlayer().getName());

                    ItemStack stackInHand = p.getInventory().getItemInHand();

                    if(target.getLocation().distance(playerLocation) >= blockDistance)
                    {
                        p.sendMessage(ChatColor.RED+"[Uyarı]: "+ChatColor.YELLOW+"İyileştirmek için oyuncuya yakın olmalısınız.");
                        return true;

                    }


                    int miktar = stackInHand.getAmount();
                    miktar = miktar - 1;

                    if(stackInHand.getType() == Material.PAPER && stackInHand.getItemMeta().getDisplayName().contains("Bandaj"))
                    {
                        for(Player on : Bukkit.getOnlinePlayers())
                        {


                            if(on.getLocation().distance(playerLocation) <= blockDistance)
                            {
                                on.sendMessage(ChatColor.RED + KarakterAdi+ ", "+ChatColor.YELLOW+HedefAt+ChatColor.GREEN+" isimli kişiyi iyileştirdi.");

                            }
                            if(on.getName().equals(target.getName()))
                            {

                                stackInHand.setAmount(miktar);

                                if(miktar <=0)
                                {
                                    p.getInventory().removeItem(stackInHand);
                                    p.updateInventory();
                                }
                                target.setHealth(target.getMaxHealth());
                                target.removePotionEffect(PotionEffectType.SLOW);
                                p.updateInventory();
                            }

                        }
                    }
                    else
                    {
                        p.sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.YELLOW+"Elinizde bandaj yok!");
                        return true;
                    }

                }
                else
                {
                    p.sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.YELLOW+"Mesleğiniz şifacı değil.");
                }

            }
            catch(Exception ex)
            {
                p.sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.YELLOW+"Oyuncuya yakın değilsiniz, oyuncu ismini yanlış girdiniz veya elinizde bandaj yok. Lütfen tekrar deneyiniz.");
            }

        }



        if(cmd.getName().equalsIgnoreCase("silah"))
        {

            Player p = (Player) sender;
            // .replace("§a", "");
            // .replace("§a", ""); //1d8 -> 1 ile 8 , 2d6 2 tane 1 ile 6
            try
            {
                if(args.length == 0)
                {
                    String KarakterAdi = EventsClass.getKarakterAd(p.getPlayer().getName());

                    String RenksizSilahAdi = ChatColor.stripColor(ChatColor.RESET+p.getInventory().getItemInMainHand().getItemMeta().getDisplayName());
                    String RenkliSilahAdi = p.getInventory().getItemInMainHand().getItemMeta().getDisplayName();

                    String ConfigSilahAdi = CustomItemler.CustomItems.SilahAd(RenksizSilahAdi);

                    int SilahAltZar = CustomItemler.CustomItems.SilahAltZar(RenksizSilahAdi);
                    int SilahUstZar = CustomItemler.CustomItems.SilahUstZar(RenksizSilahAdi);
                    int ZarSonuc = 0;

                    for(int i=1;i<=SilahAltZar;i++) //Hasar zarı
                    {
                        ZarSonuc += ThreadLocalRandom.current().nextInt(1,SilahUstZar);


                    }
                    ZarSonuc = ZarSonuc + 1;

                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + p.getName() +ChatColor.YELLOW+" isimli oyuncu /silah komutunu kullandı."+ChatColor.GOLD+" Kullandığı Silah:"+RenkliSilahAdi+" Gelen Zar:"+ZarSonuc);

                    ConfigSilahAdi = ChatColor.stripColor(ConfigSilahAdi); //Silahın rengini silme. Aksi takdirde if içindeki contains çalışmayacaktır.

                    if(ConfigSilahAdi.contains(RenksizSilahAdi))
                    {
                        int blockDistance = 10;

                        Location playerLocation = p.getPlayer().getLocation();
                        ItemStack stackInHand = p.getInventory().getItemInHand();
                        stackInHand.setDurability((short) (stackInHand.getDurability() + 1));

                        if(p.getInventory().getItemInMainHand().getType() == Material.IRON_SWORD && stackInHand.getDurability() > 250)
                        {
                            p.getInventory().removeItem(stackInHand);
                            p.updateInventory();
                        }
                        if(p.getInventory().getItemInMainHand().getType() == Material.BOW && stackInHand.getDurability() > 250)
                        {
                            p.getInventory().removeItem(stackInHand);
                            p.updateInventory();
                        }
                        if(p.getInventory().getItemInMainHand().getType() == Material.GOLDEN_CHESTPLATE ||
                                p.getInventory().getItemInMainHand().getType() == Material.GOLDEN_HELMET     ||
                                p.getInventory().getItemInMainHand().getType() == Material.GOLDEN_LEGGINGS   ||
                                p.getInventory().getItemInMainHand().getType() == Material.GOLDEN_BOOTS)
                        {
                            if(stackInHand.getDurability() > 250)
                            {
                                p.getInventory().removeItem(stackInHand);
                                p.updateInventory();
                            }

                        }
                        for(Player ab : Bukkit.getOnlinePlayers())
                        {


                            if(ab.getLocation().distance(playerLocation) <= blockDistance)
                            {
                                ab.sendMessage(ChatColor.GREEN + KarakterAdi+ " (Silah Zarı): "+RenkliSilahAdi+ChatColor.RED +" "+ZarSonuc+ ChatColor.GREEN +" geldi.");

                            }
                        }
                    }
                    else
                    {
                        p.sendMessage(ChatColor.GREEN+"[Uyarı]: "+ChatColor.YELLOW+"Lütfen elinizde sistemde kayıtlı olan bir silahı tutunuz.");
                    }

                    return true;

                }
            }



            catch (Exception cd)
            {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[Hata]:"+" /silah komutu çalıştırılırken sistemde bir hata oluştu. Muhtemel hata tahmini NullPointerException. ");
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED+ "[Hata Bilgi]:"+ChatColor.DARK_GREEN+"/silah komutunu kullanan kişi: "+sender.getName()+" Komutu yazarken elinde tuttugu silah: "+p.getInventory().getItemInMainHand().getType().toString());
                p.sendMessage(ChatColor.GREEN+"[Uyarı]: "+ChatColor.YELLOW+"Elinizde sistemde olan bir silahı kullandığınızdan veya /silah isim komutunu kullanıyorsanız sunucuda aktif ve size yakın olan bir oyuncu olmasına dikkat ediniz.");
            }



        }

        if(cmd.getName().equalsIgnoreCase("hasar") && sender instanceof Player)
        {

            try
            {
                Player p = (Player) sender;

                if(p.isOp())
                {

                    if (args.length > 1)
                    {

                        Player target = Bukkit.getServer().getPlayer(args[0]);
                        StringBuilder sm = new StringBuilder();

                        //combine the arguments the player typed
                        for (int i = 1; i < args.length; i++)
                        {
                            String arg = (args[i] + " ");
                            sm.append(arg);
                        }


                        for(Player on : Bukkit.getOnlinePlayers())
                        {

                            if(on.getName().equals(target.getName()))
                            {
                                target.setHealth(target.getHealth() - Double.valueOf(sm.toString()));
                            }

                        }


                        sender.sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.RED+target.getName() +ChatColor.YELLOW+"isimli oyuncuya "+ChatColor.RED+ sm.toString() +ChatColor.YELLOW+ "hasar vurdunuz.");


                        //  Location targetLocation = target.getPlayer().getLocation();




                    }
                    else
                    {

                        sender.sendMessage(tl(ChatColor.GOLD+"[Sunucu]"+ChatColor.RED + "Hasar vermek istediğiniz kişiyi girmelisiniz. Kullanım ->"+ChatColor.WHITE+"/hasar <oyuncu> <miktar>"+ChatColor.RED+ " şeklindedir."));
                        return true;
                    }
                }
                else
                {
                    sender.sendMessage(ChatColor.RED+"[Uyarı]: "+ChatColor.YELLOW+"Bu komutu kullanmaya yetkiniz yok!");
                    return true;
                }
            }
            catch(Exception ex)
            {
                sender.sendMessage(ChatColor.RED+"[Uyarı!]: "+ChatColor.YELLOW+"Lütfen kullanırken oyuncunun aktif olduğuna veya girdiğiniz hasarın bir sayı olduğuna dikkat ediniz."+ChatColor.GOLD+"(Örnek /hasar batur123 -5 veya /hasar Alp +10)");
                return true;
            }


        }

        if(cmd.getName().equalsIgnoreCase("mesleksifirla") && sender instanceof Player)
        {
            Player p = (Player) sender;
            try
            {

                if(p.isOp())
                {
                    if(args.length == 0)
                    {
                        sender.sendMessage(tl(ChatColor.GREEN+"[Bilgi]"+ChatColor.YELLOW + "[Meslek Sıfırlama] Kullanım ->"+ChatColor.WHITE+"/mesleksifirla <oyuncuad>"+ChatColor.RED+ " şeklindedir."));
                        return true;
                    }

                    StringBuilder s = new StringBuilder();
                    for (String arg : args) {
                        s.append(arg + " ");
                    }


                    s.deleteCharAt(s.lastIndexOf(" "));

                    Player player = Bukkit.getPlayer(s.toString());




                    EventDosyasi.EventsClass.setCK(player.getName(), "1");
                    player.sendMessage(ChatColor.BLUE+"[Meslek Bilgi] "+ChatColor.YELLOW+p.getName()+" isimli yönetici mesleğinizi sıfırladı.");
                    EventsClass.setMeslek(player.getName(), "yok");

                    sender.sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.RED+s.toString()+ChatColor.YELLOW+" isimli kişinin mesleği sıfırlandı.");
                    return true;


                }
                else
                {
                    p.sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.YELLOW+"Yetkili olmadığınız için bu komutu kullanamazsınız.");
                }

            }
            catch(Exception ex)
            {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"[Hata]: "+ChatColor.YELLOW+"/mesleksifirla da hata olustu.");
                p.sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.YELLOW+"Lütfen oyuncu ismini doğru girdiğinize emin olun.");
            }


        }

        if(cmd.getName().equalsIgnoreCase("baslangic") && sender instanceof Player)
        {
            Player p = (Player) sender;
            try
            {

                if(p.isOp())
                {
                    if(args.length == 0)
                    {
                        sender.sendMessage(tl(ChatColor.GREEN+"[Bilgi]"+ChatColor.YELLOW + "[Başlangıç] Kullanım ->"+ChatColor.WHITE+"/baslangic <oyuncuad>"+ChatColor.RED+ " şeklindedir."));
                        return true;
                    }

                    StringBuilder s = new StringBuilder();
                    for (String arg : args) {
                        s.append(arg + " ");
                    }


                    s.deleteCharAt(s.lastIndexOf(" "));

                    Player player = Bukkit.getPlayer(s.toString());




                    EventDosyasi.EventsClass.setCK(player.getName(), "1");
                    player.sendMessage(ChatColor.BLUE+"[Başlangıç Bilgi] "+ChatColor.YELLOW+p.getName()+" isimli yönetici size başlangıç eşyalarınızı verdi.");
                    EventsClass.setMeslek(player.getName(), "yok");

                    sender.sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.RED+s.toString()+ChatColor.YELLOW+" isimli kişiye başlangıç eşyaları verildi.");
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

                    player.getInventory().addItem(dimetrium);
                    player.getInventory().addItem(dimetrium2);
                    return true;


                }
                else
                {
                    p.sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.YELLOW+"Yetkili olmadığınız için bu komutu kullanamazsınız.");
                }

            }
            catch(Exception ex)
            {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"[Hata]: "+ChatColor.YELLOW+"/baslangic da hata olustu.");
                p.sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.YELLOW+"Lütfen oyuncu ismini doğru girdiğinize emin olun.");
            }


        }

        if(cmd.getName().equalsIgnoreCase("isimayarla") && sender instanceof Player)
        {
            try
            {
                Player p = (Player) sender;

                if(p.isOp())
                {

                    if (args.length > 1)
                    {

                        Player target = Bukkit.getServer().getPlayer(args[0]);
                     	  /*      String sm = "";

                     	        //combine the arguments the player typed
                     	        for (int i = 1; i < args.length; i++)
                     	        {
                     	            String arg = (args[i] + " ");
                     	            sm = (sm + arg);
                     	        } */

                        StringBuilder s = new StringBuilder();
                        for (int i = 1; i < args.length; i++)
                        {
                            s.append(args[i]).append(" ");
                        }


                        s.deleteCharAt(s.lastIndexOf(" "));

                        for(Player on : Bukkit.getOnlinePlayers())
                        {

                            if(on.getName().equals(target.getName()))
                            {
                                EventDosyasi.EventsClass.setKarakterAd(target.getName(), s.toString());
                                target.setPlayerListName(s.toString());
                            }

                        }


                        sender.sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.RED+target.getName() +ChatColor.YELLOW+" isimli oyuncunun adını değiştirdiniz. Yeni adı: ("+ChatColor.RED+s.toString()+ChatColor.YELLOW+")");


                        //  Location targetLocation = target.getPlayer().getLocation();




                    }
                    else
                    {

                        sender.sendMessage(tl(ChatColor.GOLD+"[Sunucu]"+ChatColor.RED + "İsim değiştirmek istediğiniz kişiyi girmelisiniz. Kullanım ->"+ChatColor.WHITE+"/isimayarla <oyuncu> <karakterad>"+ChatColor.RED+ " şeklindedir."));
                        return true;
                    }
                }
                else
                {
                    sender.sendMessage(ChatColor.RED+"[Uyarı]: "+ChatColor.YELLOW+"Bu komutu kullanmaya yetkiniz yok!");
                    return true;
                }
            }
            catch(Exception ex)
            {
                sender.sendMessage(ChatColor.RED+"[Uyarı!]: "+ChatColor.YELLOW+"Böyle bir oyuncu yok."+ChatColor.GOLD+"(Kullanım: /isimayarla batur123 Bilge Ted)");
                return true;
            }

        }

        if(cmd.getName().equalsIgnoreCase("saldir") && sender instanceof Player)
        {
            try
            {
                if(args.length == 0)
                {
                    sender.sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.YELLOW+"Kullanım /saldır <isim> şeklindedir.");
                    return true;
                }
                Player p = (Player) sender;

                String KarakterAdi = EventsClass.getKarakterAd(p.getPlayer().getName());

                String RenksizSilahAdi = ChatColor.stripColor(ChatColor.RESET+p.getInventory().getItemInMainHand().getItemMeta().getDisplayName());
                String RenkliSilahAdi = p.getInventory().getItemInMainHand().getItemMeta().getDisplayName();

                String ConfigSilahAdi = CustomItemler.CustomItems.SilahAd(RenksizSilahAdi);

                int SilahAltZar = CustomItemler.CustomItems.SilahAltZar(RenksizSilahAdi);
                int SilahUstZar = CustomItemler.CustomItems.SilahUstZar(RenksizSilahAdi);
                int ZarSonuc = 0;

                for(int i=1;i<=SilahAltZar;i++) //hasar zarı
                {
                    ZarSonuc += ThreadLocalRandom.current().nextInt(1,SilahUstZar);
                }

                ZarSonuc = ZarSonuc+1;
                StringBuilder s = new StringBuilder();
                for (String arg : args) {
                    s.append(arg);
                }

                int blockDistance = 10;
                Location playerLocation = p.getPlayer().getLocation();

                Player player = Bukkit.getPlayer(s.toString());
                int tempac = EventDosyasi.EventsClass.getAC(player.getName());
                String tempad = EventDosyasi.EventsClass.getKarakterAd(player.getName());
                String saldiriyazisi;

                int Zar20 = ThreadLocalRandom.current().nextInt(1,20);

                File pFile = new File(BaturPlugin.getInstance().getDataFolder(), "Oyuncular/" + player.getName().toLowerCase() + ".yml");
                FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
                int className = pConfig.getInt(CustomItemler.CustomItems.SilahZari(RenksizSilahAdi));
                className  = ((className - 10) / 2);


                Zar20 = Zar20+className;


                if(Zar20 >= tempac)
                {
                    saldiriyazisi = ChatColor.YELLOW + KarakterAdi+ ", "+ChatColor.YELLOW+tempad+ChatColor.GREEN+" isimli oyuncuya "+RenkliSilahAdi+ChatColor.GREEN +" ile saldırdı."+ChatColor.BLUE+" Başarılı oldu. "+ChatColor.YELLOW+"("+ZarSonuc+")";
                }
                else
                {
                    saldiriyazisi = ChatColor.YELLOW + KarakterAdi+ ", "+ChatColor.YELLOW+tempad+ChatColor.GREEN+" isimli oyuncuya "+RenkliSilahAdi+ChatColor.GREEN +" ile saldırdı."+ChatColor.RED+" Başarısız oldu.";
                }



                ConfigSilahAdi = ChatColor.stripColor(ConfigSilahAdi); //Silahın rengini silme. Aksi takdirde if içindeki contains çalışmayacaktır.

                if(ConfigSilahAdi.contains(RenksizSilahAdi))
                {

                    ItemStack stackInHand = p.getInventory().getItemInHand();
                    stackInHand.setDurability((short) (stackInHand.getDurability() + 30));

                    if(p.getInventory().getItemInMainHand().getType() == Material.IRON_SWORD && stackInHand.getDurability() > 250)
                    {
                        p.getInventory().removeItem(stackInHand);
                        p.updateInventory();
                    }
                    if(p.getInventory().getItemInMainHand().getType() == Material.BOW && stackInHand.getDurability() > 250)
                    {
                        p.getInventory().removeItem(stackInHand);
                        p.updateInventory();
                    }
                    if(p.getInventory().getItemInMainHand().getType() == Material.GOLDEN_CHESTPLATE ||
                            p.getInventory().getItemInMainHand().getType() == Material.GOLDEN_HELMET     ||
                            p.getInventory().getItemInMainHand().getType() == Material.GOLDEN_LEGGINGS   ||
                            p.getInventory().getItemInMainHand().getType() == Material.GOLDEN_BOOTS)
                    {
                        if(stackInHand.getDurability() > 250)
                        {
                            p.getInventory().removeItem(stackInHand);
                            p.updateInventory();
                        }

                    }

                }
                else
                {
                    p.sendMessage(ChatColor.GREEN+"[Uyarı]: "+ChatColor.YELLOW+"Lütfen elinizde sistemde kayıtlı olan bir silahı tutunuz.");
                    return true;
                }
                Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.GOLD+"Saldıran: "+ChatColor.GREEN+sender.getName()+ChatColor.GOLD+" Saldırığı Kişi: -"+ChatColor.GREEN+player.getName()+ChatColor.GOLD+"- Kullandığı Silah: "+RenkliSilahAdi+ChatColor.GOLD+" Rakip AC: "+tempac+ChatColor.GOLD);



                for(Player ab : Bukkit.getOnlinePlayers())
                {
                    System.out.println("-"+ab.getName()+"-");


                    if(ab.getLocation().distance(playerLocation) <= blockDistance)
                    {

                        ab.sendMessage(saldiriyazisi);

                    }



                }


            }
            catch(Exception ex)
            {
                sender.sendMessage(ChatColor.GREEN+"[Uyarı]: "+ChatColor.YELLOW+"Elinizde sistemde olan bir silahı kullandığınızdan veya /silah isim komutunu kullanıyorsanız sunucuda aktif ve size yakın olan bir oyuncu olmasına dikkat ediniz.");

            }

            return true;

        }

        //baslagnic





//=======================================================================================================================================================================================================================
//      /DO KOMUTU
//=======================================================================================================================================================================================================================
        if(cmd.getName().equalsIgnoreCase("do") && sender instanceof Player)
        {

            Player p = (Player) sender;

            try
            {
                String karakterad = EventsClass.getKarakterAd(p.getPlayer().getName());
                if (args.length == 0)
                {
                    sender.sendMessage(tl(ChatColor.GOLD+"[Sunucu]"+ChatColor.RED + "Kullanım ->"+ChatColor.WHITE+"/do <durum>"+ChatColor.RED+ " şeklindedir."));

                    return true;
                }
                StringBuilder s = new StringBuilder();

                for (String arg : args) {
                    s.append(arg + " ");
                }
                String rawmessage = s.toString();
                int blockDistance = 10;
                Location playerLocation = p.getPlayer().getLocation();

                if(EventDosyasi.EventsClass.getMaskeDurum(sender.getName()) == 1)
                {
                    for(Player ab : Bukkit.getOnlinePlayers())
                    {
                        if(ab.getLocation().distance(playerLocation) <= blockDistance)
                        {
                            ab.sendMessage(tl(ChatColor.DARK_GREEN + "[Bilgi] " + ChatColor.YELLOW + "Maskeli Kişi"+ " "+ChatColor.GREEN +rawmessage));
                        }
                    }
                }
                else
                {
                    for(Player ab : Bukkit.getOnlinePlayers())
                    {
                        if(ab.getLocation().distance(playerLocation) <= blockDistance)
                        {

                            ab.sendMessage(tl(ChatColor.DARK_GREEN + "[Bilgi] " + ChatColor.YELLOW + karakterad+ " "+ChatColor.GREEN +rawmessage));
                        }
                    }

                }

            }
            catch (Exception cd)
            {
                System.out.println(cd);
                cd.printStackTrace();
            }
        }

        if(cmd.getName().equalsIgnoreCase("ba") && sender instanceof Player)
        {

            Player p = (Player) sender;

            try
            {
                String karakterad = EventsClass.getKarakterAd(p.getPlayer().getName());
                if (args.length == 0)
                {
                    sender.sendMessage(tl(ChatColor.GOLD+"[Sunucu]"+ChatColor.RED + "Kullanım ->"+ChatColor.WHITE+"/ba <mesaj>"+ChatColor.RED+ " şeklindedir."));

                    return true;
                }
                StringBuilder s = new StringBuilder();

                for (String arg : args) {
                    s.append(arg + " ");
                }
                String rawmessage = s.toString();
                int blockDistance = 20;
                Location playerLocation = p.getPlayer().getLocation();

                if(EventDosyasi.EventsClass.getMaskeDurum(sender.getName()) == 1)
                {
                    for(Player ab : Bukkit.getOnlinePlayers())
                    {
                        if(ab.getLocation().distance(playerLocation) <= blockDistance)
                        {

                            ab.sendMessage(tl( ChatColor.YELLOW + "Maskeli Kişi"+ChatColor.BLUE+" (Bağırarak): "+ChatColor.WHITE + rawmessage +"!"));
                        }
                    }
                }
                else
                {
                    for(Player ab : Bukkit.getOnlinePlayers())
                    {
                        if(ab.getLocation().distance(playerLocation) <= blockDistance)
                        {

                            ab.sendMessage(tl( ChatColor.YELLOW + karakterad+ChatColor.BLUE+" (Bağırarak): "+ChatColor.WHITE + rawmessage +"!"));
                        }
                    }
                }


            }
            catch (Exception cd)
            {
                System.out.println(cd);
                cd.printStackTrace();
            }
        }

        if(cmd.getName().equalsIgnoreCase("f") && sender instanceof Player)
        {

            Player p = (Player) sender;

            try
            {
                String karakterad = EventsClass.getKarakterAd(p.getPlayer().getName());
                if (args.length == 0)
                {
                    sender.sendMessage(tl(ChatColor.GOLD+"[Sunucu]"+ChatColor.RED + "Kullanım ->"+ChatColor.WHITE+"/f <mesaj>"+ChatColor.RED+ " şeklindedir."));

                    return true;
                }
                StringBuilder s = new StringBuilder();

                for (String arg : args) {
                    s.append(arg + " ");
                }
                String rawmessage = s.toString();
                double blockDistance = 5.0;
                Location playerLocation = p.getPlayer().getLocation();

                if(EventDosyasi.EventsClass.getMaskeDurum(sender.getName()) == 1)
                {
                    for(Player ab : Bukkit.getOnlinePlayers())
                    {
                        if(ab.getLocation().distance(playerLocation) <= blockDistance)
                        {

                            ab.sendMessage(tl( ChatColor.YELLOW + "Maskeli Kişi"+ChatColor.GOLD+" (Fısıldayarak): "+ChatColor.GRAY + rawmessage));

                        }
				                	 /*if(ab.getLocation().distance(playerLocation) <= blockDistance)
				                	 {
				                		 sender.sendMessage(ChatColor.RED+"[Uyarı]: "+ChatColor.WHITE+"Fısıldamak için kişiye yakın olmalısınız!!!");
				                	 } */
                    }
                }
                else
                {
                    Location location = p.getLocation();
                    World world = location.getWorld();

                    for (Entity player : world.getNearbyEntities(location,blockDistance,blockDistance,blockDistance))
                    {
                        if(player instanceof Player)
                        {
                            player.sendMessage(tl( ChatColor.YELLOW + karakterad+ChatColor.GOLD+" (Fısıldayarak): "+ChatColor.GRAY + rawmessage));
                         //   player.sendMessage(ChatColor.BLUE+"[Fısıldama]: "+ ChatColor.GREEN + EventDosyasi.EventsClass.getKarakterAd(sender.getName())+", "+ChatColor.YELLOW+EventDosyasi.EventsClass.getKarakterAd(target.getName())+ChatColor.GOLD+ " isimli kişinin kulağına fısıldadı.");

                        }
                    }

               /*     for(Player ab : Bukkit.getOnlinePlayers())w
                    {

                        if(ab.getLocation().distance(playerLocation) <= blockDistance)
                        {

                            ab.sendMessage(tl( ChatColor.YELLOW + karakterad+ChatColor.GOLD+" (Fısıldayarak): "+ChatColor.GRAY + rawmessage));

                        }
				                	 if(ab.getLocation().distance(playerLocation) <= blockDistance)
				                	 {
				                		 sender.sendMessage(ChatColor.RED+"[Uyarı]: "+ChatColor.WHITE+"Fısıldamak için kişiye yakın olmalısınız!!!");
				                	 }
                    } */
                }

            }
            catch (Exception cd)
            {
               // System.out.println(cd);
                cd.printStackTrace();
            }
        }


        if(cmd.getName().equalsIgnoreCase("zar") && sender instanceof Player)
        {

            Player p = (Player) sender;


            try {
                int bonuskuvvet = EventsClass.BonusKuvvet(EventsClass.GetIrk(p.getPlayer().getName()));
                int bonusceviklik = EventsClass.BonusCeviklik(EventsClass.GetIrk(p.getPlayer().getName()));
                int bonusday = EventsClass.BonusDay(EventsClass.GetIrk(p.getPlayer().getName()));
                int bonuszeka = EventsClass.BonusZeka(EventsClass.GetIrk(p.getPlayer().getName()));
                int bonusbilgelik = EventsClass.BonusBilgelik(EventsClass.GetIrk(p.getPlayer().getName()));
                int bonuskarizma = EventsClass.BonusKarizma(EventsClass.GetIrk(p.getPlayer().getName()));

                String karakterad = EventsClass.getKarakterAd(p.getPlayer().getName());
                int kuvvet = (Integer.valueOf(EventsClass.getzarGuc(p.getPlayer().getName())) - 10 + bonuskuvvet) / 2;
                int ceviklik = (Integer.valueOf(EventsClass.getzarAtiklik(p.getPlayer().getName())) - 10 + bonusceviklik) / 2;
                int dayaniklilik = (Integer.valueOf(EventsClass.getzarDayaniklilik(p.getPlayer().getName())) - 10 + bonusday) / 2;
                int zeka = (Integer.valueOf(EventsClass.getzarBuyu(p.getPlayer().getName())) - 10 + bonuszeka) / 2;
                int bilgelik = (Integer.valueOf(EventsClass.getzarBilgelik(p.getPlayer().getName())) - 10 + bonusbilgelik) / 2;
                int karizma = (Integer.valueOf(EventsClass.getzarKarizma(p.getPlayer().getName())) - 10 + bonuskarizma) / 2;


                int normalzar = ThreadLocalRandom.current().nextInt(1, 21);

                int bonusluguc = normalzar + kuvvet;
                String bonusluguczar = Integer.toString(bonusluguc);

                int bonusluatik = normalzar + ceviklik;
                String bonusluatikzar = Integer.toString(bonusluatik);

                int bonusluday = normalzar + dayaniklilik;
                String bonusludayzar = Integer.toString(bonusluday);

                int bonuslubuyu = normalzar + zeka;
                String bonuslubuyuzar = Integer.toString(bonuslubuyu);

                int bonuslubilgelik = normalzar + bilgelik;
                String bonuslubilgelikzar = Integer.toString(bonuslubilgelik);

                int bonuslukarizma = normalzar + karizma;
                String bonuslukarizmazar = Integer.toString(bonuslukarizma);


                if (args.length == 0) {

                    sender.sendMessage(tl(ChatColor.RED + "Kullanım ->" + ChatColor.WHITE + "/zar <türü>" + ChatColor.RED + " şeklindedir."));
                    sender.sendMessage(tl(ChatColor.RED + "Zar Türleri -> " + ChatColor.WHITE + "normal,kuvvet,dayanıklılık,çeviklik,zeka,bilgelik,karizma"));
                    sender.sendMessage(tl(ChatColor.RED + "Ek Bilgiler -> " + ChatColor.WHITE + "/zar kullanım"));

                    return true;
                }
                StringBuilder s = new StringBuilder();

                for (String arg : args) {
                    s.append(arg + " ");
                }


                //kuvvet,bilgelik,çeviklik,dayanıklılık,zeka,karizma
                int blockDistance = 10;
                Location playerLocation = p.getPlayer().getLocation();

                for (Player ab : Bukkit.getOnlinePlayers()) {
                    if (args[0].equalsIgnoreCase("normal") || args[0].equalsIgnoreCase("n") || args[0].equalsIgnoreCase("no") || args[0].equalsIgnoreCase("nor") || args[0].equalsIgnoreCase("norma") || args[0].equalsIgnoreCase("norm")) {

                        if (ab.getLocation().distance(playerLocation) <= blockDistance) {
                            ab.sendMessage(tl(ChatColor.GOLD +"("+ p.getName() +")"+ChatColor.YELLOW + karakterad + ChatColor.GREEN + " (Normal)" + ChatColor.YELLOW + " attı: " + ChatColor.GREEN + normalzar + ChatColor.YELLOW + " geldi."));
                        }

                    }
                    if (args[0].equalsIgnoreCase("kuvvet") || args[0].equalsIgnoreCase("kuvve") || args[0].equalsIgnoreCase("kuvv") || args[0].equalsIgnoreCase("kuv") || args[0].equalsIgnoreCase("ku")) {

                        if (ab.getLocation().distance(playerLocation) <= blockDistance) {
                            if (kuvvet < 0) {
                                ab.sendMessage(tl(ChatColor.GOLD +"("+ p.getName() +")"+ChatColor.YELLOW + karakterad + ChatColor.GREEN + " (Kuvvet)" + ChatColor.YELLOW + " attı: " + ChatColor.GREEN + bonusluguczar + "(" + kuvvet + ")" + ChatColor.YELLOW + " geldi."));

                            } else {
                                ab.sendMessage(tl(ChatColor.GOLD +"("+ p.getName() +")"+ChatColor.YELLOW + karakterad + ChatColor.GREEN + " (Kuvvet)" + ChatColor.YELLOW + " attı: " + ChatColor.GREEN + bonusluguczar + "(" + "+" + kuvvet + ")" + ChatColor.YELLOW + " geldi."));

                            }

                        }

                    }

                    if (args[0].equalsIgnoreCase("çeviklik") || args[0].equalsIgnoreCase("çevikli") || args[0].equalsIgnoreCase("çevikl") || args[0].equalsIgnoreCase("çevik") || args[0].equalsIgnoreCase("çevi") || args[0].equalsIgnoreCase("çev") || args[0].equalsIgnoreCase("çe") || args[0].equalsIgnoreCase("ç") || args[0].equalsIgnoreCase("ceviklik")) {
                        if (ab.getLocation().distance(playerLocation) <= blockDistance) {
                            if (ceviklik < 0) {
                                ab.sendMessage(tl(ChatColor.GOLD +"("+ p.getName() +")"+ChatColor.YELLOW + karakterad + ChatColor.GREEN + " (Çeviklik)" + ChatColor.YELLOW + " attı: " + ChatColor.GREEN + bonusluatikzar + "(" + ceviklik + ")" + ChatColor.YELLOW + " geldi."));

                            } else {
                                ab.sendMessage(tl(ChatColor.GOLD +"("+ p.getName() +")"+ChatColor.YELLOW + karakterad + ChatColor.GREEN + " (Çeviklik)" + ChatColor.YELLOW + " attı: " + ChatColor.GREEN + bonusluatikzar + "(" + "+" + ceviklik + ")" + ChatColor.YELLOW + " geldi."));

                            }

                        }

                    }
                    if (args[0].equalsIgnoreCase("dayaniklilik") || args[0].equalsIgnoreCase("dayaniklili")
                            || args[0].equalsIgnoreCase("dayaniklil") || args[0].equalsIgnoreCase("dayanikli")
                            || args[0].equalsIgnoreCase("dayanikl") || args[0].equalsIgnoreCase("dayanik")
                            || args[0].equalsIgnoreCase("dayani") || args[0].equalsIgnoreCase("dayan")
                            || args[0].equalsIgnoreCase("daya") || args[0].equalsIgnoreCase("day")
                            || args[0].equalsIgnoreCase("da") || args[0].equalsIgnoreCase("d")
                            || args[0].equalsIgnoreCase("dayanıklılık") ||
                            args[0].equalsIgnoreCase("dayanıklılı") ||
                            args[0].equalsIgnoreCase("dayanıklıl") ||
                            args[0].equalsIgnoreCase("dayanıklı") ||
                            args[0].equalsIgnoreCase("dayanıkl") ||
                            args[0].equalsIgnoreCase("dayanık") ||
                            args[0].equalsIgnoreCase("dayanı")) {
                        if (ab.getLocation().distance(playerLocation) <= blockDistance) {

                            if (dayaniklilik < 0) {
                                ab.sendMessage(tl(ChatColor.GOLD +"("+ p.getName() +")"+ChatColor.YELLOW + karakterad + ChatColor.GREEN + " (Dayanıklılık)" + ChatColor.YELLOW + " attı: " + ChatColor.GREEN + bonusludayzar + "(" + dayaniklilik + ")" + ChatColor.YELLOW + " geldi."));

                            } else {
                                ab.sendMessage(tl(ChatColor.GOLD +"("+ p.getName() +")"+ChatColor.YELLOW + karakterad + ChatColor.GREEN + " (Dayanıklılık)" + ChatColor.YELLOW + " attı: " + ChatColor.GREEN + bonusludayzar + "(" + "+" + dayaniklilik + ")" + ChatColor.YELLOW + " geldi."));

                            }

                        }

                    }
                    if (args[0].equalsIgnoreCase("zeka") || args[0].equalsIgnoreCase("zek") || args[0].equalsIgnoreCase("ze") || args[0].equalsIgnoreCase("z")) {
                        if (ab.getLocation().distance(playerLocation) <= blockDistance) {
                            if (zeka < 0) {
                                ab.sendMessage(tl(ChatColor.GOLD +"("+ p.getName() +")"+ChatColor.YELLOW + karakterad + ChatColor.GREEN + " (Zeka)" + ChatColor.YELLOW + " attı: " + ChatColor.GREEN + bonuslubuyuzar + "(" + zeka + ")" + ChatColor.YELLOW + " geldi."));

                            } else {
                                ab.sendMessage(tl(ChatColor.GOLD +"("+ p.getName() +")"+ChatColor.YELLOW + karakterad + ChatColor.GREEN + " (Zeka)" + ChatColor.YELLOW + " attı: " + ChatColor.GREEN + bonuslubuyuzar + "(" + "+" + zeka + ")" + ChatColor.YELLOW + " geldi."));

                            }


                        }

                    }
                    if (args[0].equalsIgnoreCase("bilgelik") || args[0].equalsIgnoreCase("Bilgelik")) {
                        if (ab.getLocation().distance(playerLocation) <= blockDistance) {
                            if (bilgelik < 0) {
                                ab.sendMessage(tl(ChatColor.GOLD +"("+ p.getName() +")"+ChatColor.YELLOW + karakterad + ChatColor.GREEN + " (Bilgelik)" + ChatColor.YELLOW + " attı: " + ChatColor.GREEN + bonuslubilgelikzar + "(" + bilgelik + ")" + ChatColor.YELLOW + " geldi."));

                            } else {
                                ab.sendMessage(tl(ChatColor.GOLD +"("+ p.getName() +")"+ChatColor.YELLOW + karakterad + ChatColor.GREEN + " (Bilgelik) " + ChatColor.YELLOW + "attı: " + ChatColor.GREEN + bonuslubilgelikzar + "(" + "+" + bilgelik + ")" + ChatColor.YELLOW + " geldi."));

                            }


                        }

                    }
                    if (args[0].equalsIgnoreCase("sans")) {
                        if (ab.getLocation().distance(playerLocation) <= blockDistance) {
                            ab.sendMessage(tl(ChatColor.GOLD +"("+ p.getName() +")"+ChatColor.YELLOW + karakterad + ChatColor.GREEN + " (Şans)" + ChatColor.YELLOW + " attı: " + ChatColor.GREEN + normalzar + ChatColor.YELLOW + " geldi."));

                                    
                        }

                    }
                    if (
                            args[0].equalsIgnoreCase("k") || args[0].equalsIgnoreCase("ka") ||
                                    args[0].equalsIgnoreCase("kar") || args[0].equalsIgnoreCase("kari") ||
                                    args[0].equalsIgnoreCase("kariz") || args[0].equalsIgnoreCase("karizm") ||
                                    args[0].equalsIgnoreCase("karizma") || args[0].equalsIgnoreCase("karı")
                                    || args[0].equalsIgnoreCase("karız") || args[0].equalsIgnoreCase("karızm")
                                    || args[0].equalsIgnoreCase("karızma")
                    ) {
                        if (ab.getLocation().distance(playerLocation) <= blockDistance) {
                            if (karizma < 0) {
                                ab.sendMessage(tl(ChatColor.GOLD +"("+ p.getName() +")"+ChatColor.YELLOW + karakterad + ChatColor.GREEN + " (Karizma)" + ChatColor.YELLOW + " attı: " + ChatColor.GREEN + bonuslukarizmazar + "(" + karizma + ")" + ChatColor.YELLOW + " geldi."));

                            } else {
                                ab.sendMessage(tl(ChatColor.GOLD +"("+ p.getName() +")"+ChatColor.YELLOW + karakterad + ChatColor.GREEN + " (Karizma)" + ChatColor.YELLOW + " attı: " + ChatColor.GREEN + bonuslukarizmazar + "(" + "+" + karizma + ")" + ChatColor.YELLOW + " geldi."));

                            }


                        }

                    }
                    if (args[0].equalsIgnoreCase("kullanım") || args[0].equalsIgnoreCase("kullanim")) {
                        sender.sendMessage(tl(ChatColor.BLUE + "[Yardım]: " + ChatColor.WHITE + "/zar kullanımlarında zarın adını tam olarak yazmanıza gerek yoktur. /zar dayanıklılık yerine /zar day veya /zar dayaniklil yazarsanızda aynı zarı atabilirsiniz."));
                        return true;
                    }

                  
                }
                String[] splittentext = args[0].split("d", 2);
                int n1 = Integer.parseInt(splittentext[0]);
                int n2 = Integer.parseInt(splittentext[1]);
                int sum = 0;
                if (!splittentext[0].matches(".*\\d+.*") || !splittentext[1].matches(".*\\d+.*")) {
                    return true;
                }
                if(n1 > 1000 || n2 > 1000)
                {
                    p.sendMessage(ChatColor.RED+"[Hata]: "+ChatColor.YELLOW+"Bu kadar yüksek değerde bir zar atamazsınız.");
                    return true;
                }
                for (int x = 0; x < n1; x++) {
                    sum = sum + ThreadLocalRandom.current().nextInt(1, n2);
                }

                for (Player ab2 : Bukkit.getOnlinePlayers()) {


                    if (splittentext.length == 2)
                    {

                        if (ab2.getLocation().distance(playerLocation) <= blockDistance) {
                            ab2.sendMessage(tl(ChatColor.GOLD +"("+ p.getName() +")"+ChatColor.GOLD + karakterad + ChatColor.YELLOW + " isimli oyuncu " + ChatColor.GREEN + n1 + ChatColor.YELLOW + " kez " + ChatColor.GREEN + n2 + ChatColor.YELLOW + " yüzlü bir zar attı ve " + ChatColor.GREEN + sum + ChatColor.YELLOW + " geldi."));
                        }
                    }
                }
            }
            catch (Exception cd)
            {
         //       cd.printStackTrace();
               // p.sendMessage(ChatColor.BLUE+"[Bilgi]: "+ChatColor.YELLOW+"Zar komutunu kullanırken atmak istediğiniz zarı yazmalısınız veya yüzlü zar atıyorsanız /zar 1d6,2d8 şeklinde yazmalısınız.");
            }
        }


        if(cmd.getName().equalsIgnoreCase("meslekseviye"))
        {
            Player p = (Player) sender;
            try
            {

                String MeslekAd = EventsClass.getMeslek(p.getPlayer().getName());
                String MeslekXP = EventsClass.getMeslekXP(p.getPlayer().getName());
                String MeslekSV = EventsClass.getMeslekSeviye(p.getPlayer().getName());

                if(MeslekAd.equals("Madenci"))
                {
                    switch (EventsClass.getMeslekSeviye(p.getPlayer().getName())) {
                        case "1":
                            Gereksinim = "3200";
                            MeslekAd = "Kömür Madencisi";
                            break;
                        case "2":
                            Gereksinim = "9600";
                            MeslekAd = "Usta Madenci";
                            break;
                        case "3":
                            Gereksinim = "19200";
                            MeslekAd = "Üstad Madenci";
                            break;
                        case "4":
                            Gereksinim = "32000";
                            MeslekAd = "Bilge Madenci";
                            break;
                        case "5":
                            Gereksinim = "Son Seviye";
                            MeslekAd = "Efsanevi Madenci";
                            break;
                    }
                }
                if(MeslekAd.equals("Marangoz"))
                {
                    switch (EventsClass.getMeslekSeviye(p.getPlayer().getName())) {
                        case "1":
                            Gereksinim = "500";
                            MeslekAd = "Keresteci";
                            break;
                        case "2":
                            Gereksinim = "1500";
                            MeslekAd = "Marangoz";
                            break;
                        case "3":
                            Gereksinim = "4000";
                            MeslekAd = "Usta Marangoz";
                            break;
                        case "4":
                            Gereksinim = "8000";
                            MeslekAd = "Odun Katili";
                            break;
                        case "5":
                            Gereksinim = "Son Seviye";
                            MeslekAd = "Odunların Efendisi";
                            break;
                    }
                }
                if(MeslekAd.equals("Ciftci"))
                {
                    switch (EventsClass.getMeslekSeviye(p.getPlayer().getName())) {
                        case "1":
                            Gereksinim = "150";
                            MeslekAd = "Basit Çiftçi";
                            break;
                        case "2":
                            Gereksinim = "450";
                            MeslekAd = "Usta Çiftçi";
                            break;
                        case "3":
                            Gereksinim = "800";
                            MeslekAd = "Tarla Üstadı";
                            break;
                        case "4":
                            Gereksinim = "1500";
                            MeslekAd = "Hasat Katili";
                            break;
                        case "5":
                            Gereksinim = "Son Seviye";
                            MeslekAd = "Ekinlerin Efendisi";
                            break;
                    }
                }
                if(MeslekAd.equals("Sifaci"))
                {
                    Gereksinim = "Son Seviye";
                    MeslekAd = "Usta Şifacı";
                }
                if(MeslekAd.equals("Demirci"))
                {
                    switch (EventsClass.getMeslekSeviye(p.getPlayer().getName())) {
                        case "1":
                            Gereksinim = "500";
                            MeslekAd = "Çırak Demirci";
                            break;
                        case "2":
                            Gereksinim = "1500";
                            MeslekAd = "Usta Demirci";
                            break;
                        case "3":
                            Gereksinim = "3000";
                            MeslekAd = "Üstad Demirci";
                            break;
                        case "4":
                            Gereksinim = "7500";
                            MeslekAd = "Külçe Ustası";
                            break;
                        case "5":
                            Gereksinim = "Son Seviye";
                            MeslekAd = "Rün Ustası";
                            break;
                    }
                }


                sender.sendMessage(tl(ChatColor.GREEN+"Mesleğiniz: "+ChatColor.GOLD+MeslekAd+ChatColor.YELLOW+" "+MeslekSV+ ".Seviye [ "+MeslekXP+" / "+Gereksinim+" ]"));



            }
            catch (Exception cd)
            {
                System.out.println(cd);
                cd.printStackTrace();
            }


        }
        return true;
    }

    //
//=======================================================================================================================================================================================================================

    public String tl(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }


}
