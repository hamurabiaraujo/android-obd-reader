package pt.lighthouselabs.obd.reader.manufacturer;

public class ManufacturerLog {

    /// the database version number
    public static final int DATABASE_VERSION = 1;
    /// the name of the database
    public static final String DATABASE_NAME = "autoboard";
    /// a tag string for debug logging (the name of this class)
    private static final String TAG = Manufacturer.class.getName();
    /// database table names
    private static final String MANUFACTURERS_TABLE = "Manufacturer";
    /// SQL commands to delete the database
    public static final String[] DATABASE_DELETE = new String[]{
            "drop table if exists " + MANUFACTURERS_TABLE + ";",
    };

    private static final String MANUFACTURER_ID = "id";
    private static final String MANUFACTURER_NAME = "name";

    /// array of all column names for RECORDS_TABLE
    private static final String[] RECORDS_TABLE_COLUMNS = new String[] {
            MANUFACTURER_ID,
            MANUFACTURER_NAME
    };

    /// SQL commands to create the database
    public static final String[] DATABASE_CREATE = new String[]{
            "create table " + MANUFACTURERS_TABLE + " ( " +
                    MANUFACTURER_ID + " integer primary key autoincrement, " +
                    MANUFACTURER_NAME + " text" +
                    ");"
    };


}
