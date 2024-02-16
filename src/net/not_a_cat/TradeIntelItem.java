package net.not_a_cat;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StoryPointActionDelegate;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import com.fs.starfarer.api.impl.campaign.intel.inspection.HegemonyInspectionIntel;
import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.SectorMapAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class TradeIntelItem extends BaseIntelPlugin {

    public TradeIntelItem() {
        super();
        Global.getSector().addScript(this);
    }

    @Override
    public String getIcon() {
        return "graphics/icons/rem_salvation.png";
    }

    @Override
    public String getImportantIcon() {
        return getIcon();
    }

    @Override
    public Set<String> getIntelTags(SectorMapAPI map) {
        HashSet<String> aa = new HashSet<>();
        aa.add("Trade");
        return aa;
    }

    @Override
    protected String getName() {
        return "QQQQQQQQQQQQ";
    }

    @Override
    public String getSmallDescriptionTitle() {
        return "AAAAAAADDSDS";
    }

    @Override
    public void createSmallDescription(TooltipMakerAPI info, float width, float height) {
//        super.createSmallDescription(info, width, height);
        float opad = 10f, pad = 3f;
        info.addImage(getIcon(), width, 128, pad);
    }

    //    @Override
//    public void createSmallDescription(TooltipMakerAPI info, float width, float height) {
//        super.smal
//    }

    @Override
    public SectorEntityToken getMapLocation(SectorMapAPI map) {
        return Global.getSector().getEntityById("jangala");
    }
}