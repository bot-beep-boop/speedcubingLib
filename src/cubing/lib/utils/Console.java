package cubing.lib.utils;

import org.bukkit.Bukkit;
import org.fusesource.jansi.Ansi;

import java.io.FileWriter;
import java.util.Arrays;
import java.util.Locale;
import java.util.logging.Level;

public class Console {
    public static void sendColoredConsole(String message) {
        Ansi reset = Ansi.ansi().a(Ansi.Attribute.RESET);
        Bukkit.getLogger().log(Level.INFO, message
                .replace("§0", reset.fg(Ansi.Color.BLACK).boldOff().toString())
                .replace("§1", reset.fg(Ansi.Color.BLUE).boldOff().toString())
                .replace("§2", reset.fg(Ansi.Color.GREEN).boldOff().toString())
                .replace("§3", reset.fg(Ansi.Color.CYAN).boldOff().toString())
                .replace("§4", reset.fg(Ansi.Color.RED).boldOff().toString())
                .replace("§5", reset.fg(Ansi.Color.MAGENTA).boldOff().toString())
                .replace("§6", reset.fg(Ansi.Color.YELLOW).boldOff().toString())
                .replace("§7", reset.fg(Ansi.Color.WHITE).boldOff().toString())
                .replace("§8", reset.fg(Ansi.Color.BLACK).bold().toString())
                .replace("§9", reset.fg(Ansi.Color.BLUE).bold().toString())
                .replace("§a", reset.fg(Ansi.Color.GREEN).bold().toString())
                .replace("§b", reset.fg(Ansi.Color.CYAN).bold().toString())
                .replace("§c", reset.fg(Ansi.Color.RED).bold().toString())
                .replace("§d", reset.fg(Ansi.Color.MAGENTA).bold().toString())
                .replace("§e", reset.fg(Ansi.Color.YELLOW).bold().toString())
                .replace("§f", reset.fg(Ansi.Color.WHITE).bold().toString())
                .replace("§k", Ansi.ansi().a(Ansi.Attribute.BLINK_SLOW).toString())
                .replace("§l", Ansi.ansi().a(Ansi.Attribute.UNDERLINE_DOUBLE).toString())
                .replace("§m", Ansi.ansi().a(Ansi.Attribute.STRIKETHROUGH_ON).toString())
                .replace("§n", Ansi.ansi().a(Ansi.Attribute.UNDERLINE).toString())
                .replace("§o", Ansi.ansi().a(Ansi.Attribute.ITALIC).toString())
                .replace("§r", reset.toString()) + reset
        );
    }

    public static String ansiToColoredText(String str) {
        if (str.endsWith("\n"))
            str = str.substring(0, str.length() - 4);
        String[] array = str.split("\u001B");
        String re = array[0];
        if (!re.equals(""))
            array = Arrays.copyOfRange(array, 1, array.length);
        for (String s : array) {
            int m = s.indexOf('m');
            if (m != s.length() - 1) {
                int u = 0;
                for (String n : s.substring(1, m).split(";")) {
                    u += Integer.parseInt(n);
                }
                re += "§" + ansiNumberToColorCode(u) + s.substring(m + 1);
            }
        }
        return re;
    }

    public static String ansiNumberToColorCode(int ansi) {
        switch (ansi) {
            case 52:
                return "0";
            case 56:
                return "1";
            case 54:
                return "2";
            case 58:
                return "3";
            case 53:
                return "4";
            case 57:
                return "5";
            case 55:
                return "6";
            case 59:
                return "7";
            case 31:
                return "8";
            case 35:
                return "9";
            case 33:
                return "a";
            case 37:
                return "b";
            case 32:
                return "c";
            case 36:
                return "d";
            case 34:
                return "e";
            case 38:
                return "f";
            case 5:
                return "k";
            case 21:
                return "l";
            case 9:
                return "m";
            case 4:
                return "n";
            case 3:
                return "o";
            default:
                return null;
        }
    }

    public static String removeAnsi(String str) {
        return str.replaceAll("\u001B\\[[;\\d]*m", "");
    }
}
