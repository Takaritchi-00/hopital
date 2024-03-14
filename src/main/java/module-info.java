module sn.exo.hopital {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.sql;


    opens sn.exo.hopital to javafx.fxml;
    exports sn.exo.hopital;

    opens sn.exo.hopital.controller to javafx.fxml;
    exports sn.exo.hopital.controller;

    opens sn.exo.hopital.entities;
}