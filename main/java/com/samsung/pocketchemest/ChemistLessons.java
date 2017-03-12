package com.samsung.pocketchemest;

import java.util.ArrayList;
import java.util.Collection;

public class ChemistLessons {

    private static int maxExperimentID = 1;
    private int activeExperimentID;
    public ArrayList<String> instruments;
    public ArrayList<String> substanses;
    public String description;
    public String formula;

    public ChemistLessons(int activeExperimentID) {
        if (activeExperimentID <= maxExperimentID) {
            this.activeExperimentID = activeExperimentID;
        }
        instruments = new ArrayList<>();
        substanses = new ArrayList<>();
    }

    public int getMaxExperimentID() {
        return maxExperimentID;
    }


    public int getActiveExperimentID() {
        return activeExperimentID;
    }


    public boolean setActiveExperimentID(Integer id) {
        if (id < maxExperimentID) {
            activeExperimentID = id;
            return true;
        } else {
            return false;
        }

    }

    public void startExperiment() {
        switch (activeExperimentID) {
            case 1:
                instruments.add("Пробирка");
                substanses.add("Na2CO3");
                substanses.add("CH3COOH");
                description = "Данный опыт показывает расщепление пищевой соды (щёлочи) при воздействии уксуса(кислоты)";
                formula = "Na2CO3 + 2 CH3-COOH = 2 CH3-COONa + H2O + CO2.";
                break;
        }
    }


}
