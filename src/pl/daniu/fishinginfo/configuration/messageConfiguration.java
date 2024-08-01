package pl.daniu.fishinginfo.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;

public class messageConfiguration extends OkaeriConfig {

    @Comment("~-~-~-~-~-~-~-~-~-~-~-~~-~-~-~~ #")
    @Comment("                                #")
    @Comment("         FishingInfo            #")
    @Comment("                                #")
    @Comment("~-~-~-~-~-~-~-~-~-~-~-~~-~-~-~~ #")

    @Comment("Za ile bedzie dostepny połów")
    @Comment("UWAGA! dostępne zmienne: {TIME} - czas do połowu")
    private String catchFishFor = "&7Polow za: &f {TIME} s";

    @Comment("wiadomosc jak polow podlywa")
    private String catchFishing = "&aPolow podplywa...";

    public String getCatchFishFor() {return catchFishFor;}
    public String getCatchFishing() {return catchFishing;}
}
