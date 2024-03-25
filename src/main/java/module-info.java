@SuppressWarnings("all")
module Contasoc {
    requires javafx.controls;
    requires javafx.web;
    requires javafx.swing;
    requires org.slf4j;
    requires java.sql;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires com.github.lgooddatepicker;
    requires miglayout;
    requires org.checkerframework.checker.qual;
    requires com.formdev.flatlaf.intellijthemes;
    requires com.formdev.flatlaf;
    requires com.fasterxml.jackson.databind;
    requires org.apache.commons.io;
    requires layout;
    requires kernel;
    requires font.asian;
    requires io;
	requires com.jcraft.jsch;
	requires ch.qos.logback.classic;
    opens dev.galliard.contasoc.database.objects to org.hibernate.orm.core;
}