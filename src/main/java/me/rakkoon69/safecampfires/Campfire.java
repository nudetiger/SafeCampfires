package me.rakkoon69.safecampfires;

import me.rakkoon69.safecampfires.configuration.Message;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class Campfire {

    private double cooldownMax, cooldownMin;
    private double radius;
    private int disableBelowY;

    private boolean extinguish;

    private List<CampfireEffect> effectList;
    private List<String> usableWorlds;



    public double getCooldownMax() {
        return cooldownMax;
    }

    public double getCooldownMin() {
        return cooldownMin;
    }

    public double getRadius() {
        return radius;
    }

    public int getDisableBelowY() {
        return disableBelowY;
    }

    public List<CampfireEffect> getEffectList() {
        return effectList;
    }

    public List<String> getUsableWorlds() {
        return usableWorlds;
    }

    public boolean isUsable(String worldName) {
        return usableWorlds.contains(worldName);
    }

    public boolean isExtinguish() {
        return extinguish;
    }

    private static class CampfireEffect {
        private final PotionEffectType effect;
        private final int level;
        private final int durationMin, durationMax;
        private final int weight;

        public CampfireEffect(String sss) {
            String[] s = sss.split(":");

            if(s[0].equalsIgnoreCase("nothing")) {

                effect = null;
                level = 0;
                durationMin = 0;
                durationMax = 0;
                weight = Integer.parseInt(s[1]);

                return;
            }

            effect = PotionEffectType.getByName(s[0]);
            level = Integer.parseInt(s[1]);

            durationMin = Integer.parseInt(s[2].split("->")[0]);
            durationMax = Integer.parseInt(s[2].split("->")[1]);

            weight = Integer.parseInt(s[3]);
        }

        public void start(Player p) {

            if(effect == null) {
                Message.NO_EFFECT.send(p);
                return;
            }

            int duration = ((int) (Math.random() * (durationMax - durationMin))) + durationMin;

            p.addPotionEffect(new PotionEffect(effect, duration, level));

            Message.msg(p, Message.EFFECT.get()
                    .replace("<effect>", effect.getName())
                    .replace("<level>", String.valueOf(level))
                    .replace("<duration>", String.valueOf(Math.round(duration * 10) / 10.0))
            );
        }

        public PotionEffectType getEffect() {
            return effect;
        }

        public int getLevel() {
            return level;
        }

        public int getDurationMin() {
            return durationMin;
        }

        public int getDurationMax() {
            return durationMax;
        }

        public int getWeight() {
            return weight;
        }
    }
}
