package net.not_a_cat;

import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import com.fs.starfarer.api.ui.SectorMapAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import java.util.HashSet;
import java.util.Set;

public class CommodityDeficitIntel extends BaseIntelPlugin {
    private final String commodityId;
    private final int deficit;

    private final int price;

    public CommodityDeficitIntel(String commodityId, int deficit, int price) {
        this.commodityId = commodityId;
        this.deficit = deficit;
        this.price = price;
    }

    @Override
    public Set<String> getIntelTags(SectorMapAPI map) {
        HashSet<String> aa = new HashSet<>();
        aa.add("Trade");
        return aa;
    }

    @Override
    public String getIcon() {
        return "graphics/icons/rem_salvation.png";
    }

    @Override
    protected String getName() {
        return String.format("Commodity %s deficit", commodityId);
    }

    @Override
    public void createSmallDescription(TooltipMakerAPI info, float width, float height) {
        float opad = 10f;
        info.addImage("graphics/icons/rem_salvation.png", width, height, opad);
        info.addPara("In Jangala there is " + getName(), opad);
        info.addPara(Misc.getAgoStringForTimestamp(timestamp) + ".", opad);
    }
}
