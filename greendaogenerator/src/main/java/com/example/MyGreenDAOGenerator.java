package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyGreenDAOGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "org.zarroboogs.maps.beans");

        schema.setDefaultJavaPackageDao("org.zarroboogs.maps.dao");

        addCameraTable(schema);

        addPoiSearchTipTable(schema);

        new DaoGenerator().generateAll(schema, "app/src/main/java-gen");


    }


    private static void addCameraTable(Schema schema) {

        Entity camera = schema.addEntity("BJCamera");

        camera.addIdProperty().autoincrement();
        camera.addStringProperty("name").notNull();
        camera.addStringProperty("address");
        camera.addDoubleProperty("latitude");
        camera.addDoubleProperty("longtitude");
        camera.addStringProperty("direction");
    }

    private static void addPoiSearchTipTable(Schema schema){
        Entity tip = schema.addEntity("PoiSearchTip");
        tip.addStringProperty("name");
        tip.addStringProperty("district");
        tip.addStringProperty("adcode");
    }
}
