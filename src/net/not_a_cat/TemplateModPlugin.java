package net.not_a_cat;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;

public class TemplateModPlugin extends BaseModPlugin {

    @Override
    public void onGameLoad(boolean newGame) {
        super.onGameLoad(newGame);
        addIntel();
    }

    private void addIntel() {
        TradeIntelItem intel = new TradeIntelItem();

        Global.getSector().getIntelManager().addIntel(intel);
    }
}
