package win.thebatman.tmrjenkinslib.database

import sun.reflect.generics.reflectiveObjects.NotImplementedException

class MySqlDatabase implements IDatabase{

    private ctx

    MySqlDatabase(context, String credentialsConfigFileId, String serverAddress){
        this.ctx = context
        throw new NotImplementedException();
    }

    @Override
    void backupDB() {
        throw new NotImplementedException();
    }

    @Override
    void backupTable(String table) {
        throw new NotImplementedException();
    }

    @Override
    void backupTables(List<String> tables) {
        throw new NotImplementedException();
    }

    @Override
    void restoreDBBackup(String backupFilePath) {
        throw new NotImplementedException();
    }
}
