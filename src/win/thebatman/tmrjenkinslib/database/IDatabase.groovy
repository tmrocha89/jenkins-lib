package win.thebatman.tmrjenkinslib.database

interface IDatabase {

    //ToDo: Improve this class
    //this only shows what this is intended to do
    //the interface WILL CHANGE
    //ToDo: (FUTURE) add support to store the backup on cloud

    void backupDB();

    void backupTable(String table);

    void backupTables(List<String> tables);

    void restoreDBBackup(String backupFilePath);
}