package app;

import compailer.Compailer;
import compailer.CompailerImpl;
import database.Database;
import database.DatabaseImpl;
import database.MSSQLrepository;
import database.settings.Settings;
import database.settings.SettingsImpl;
import gui.table.TableModel;
import observer.Notification;
import observer.enums.NotificationCode;
import observer.implementation.PublisherImpl;
import resource.implementation.InformationResource;

public class AppCore extends PublisherImpl {

    private Database database;
    private Settings settings;
    private TableModel tableModel;
    private Compailer compailer;

    public AppCore() {
        this.settings = initSettings();
        this.database = new DatabaseImpl(new MSSQLrepository(this.settings));
        tableModel = new TableModel();
        compailer = new CompailerImpl();
    }

    private Settings initSettings() {
        Settings settingsImplementation = new SettingsImpl();
        settingsImplementation.addParameter("mssql_ip", "147.91.175.155");
        settingsImplementation.addParameter("mssql_database", "bp2021_t1");
        settingsImplementation.addParameter("mssql_username",  "bp2021_t1_readonly");
        settingsImplementation.addParameter("mssql_password",  "bp2021_t1_readonly");
        return settingsImplementation;
    }


    public void loadResource(){
        InformationResource ir = (InformationResource) this.database.loadResource();
        this.notifySubscribers(new Notification(NotificationCode.RESOURCE_LOADED,ir));
    }

    public void readDataFromTable(String fromTable){

        tableModel.setRows(this.database.readDataFromTable(fromTable));

        //Zasto ova linija moze da ostane zakomentarisana?
        //this.notifySubscribers(new Notification(NotificationCode.DATA_UPDATED, this.getTableModel()));
    }
    public void orderColomnsBy(String from,String order){
        tableModel.orderColomns(this.database.orderColomnsBy(from,order));
    }
    public void select(String from,String select){
        tableModel.selectRows(this.database.select(from,select));
    }


    public TableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(TableModel tableModel) {
        this.tableModel = tableModel;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Compailer getCompailer() {
        return compailer;
    }

    public void setCompailer(Compailer compailer) {
        this.compailer = compailer;
    }
}
