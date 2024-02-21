package net.not_a_cat;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import com.fs.starfarer.api.ui.SectorMapAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CommodityDeficitIntel extends BaseIntelPlugin {
    private final SectorEntityToken entityToken;
    private final String commodityId;
    private final int deficit;
    private final int price;

    private boolean shouldRemove = false;

    public CommodityDeficitIntel(SectorEntityToken entityToken, String commodityId, int deficit, int price) {
        this.entityToken = entityToken;
        this.commodityId = commodityId;
        this.deficit = deficit;
        this.price = price;
    }


    @Override
    public SectorEntityToken getPostingLocation() {
        return Global.getSector().getEntityById("jangala");
    }

    public void markForRemove() {
        shouldRemove = true;
    }

    @Override
    public boolean shouldRemoveIntel() {
        return shouldRemove;
    }

    @Override
    public Set<String> getIntelTags(SectorMapAPI map) {
        HashSet<String> aa = new HashSet<>();
        aa.add("Trade");
        return aa;
    }

    @Override
    public String getIcon() {
        return Global.getSector().getEconomy().getCommoditySpec(commodityId).getIconName();
    }

    @Override
    protected String getName() {
        return String.format("There is %s deficit in %s", commodityId, entityToken.getName());
    }

    @Override
    public void createSmallDescription(TooltipMakerAPI info, float width, float height) {
        float padding = 10f;
        info.addImage(Global.getSector().getEconomy().getCommoditySpec(commodityId).getIconName(), width, height, padding);
        info.addPara(getName(), padding);
        info.addPara(Misc.getAgoStringForTimestamp(timestamp) + ".", padding);
    }
}
