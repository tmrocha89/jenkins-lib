package win.thebatman.tmrjenkinslib.deployment

import sun.reflect.generics.reflectiveObjects.NotImplementedException
import win.thebatman.tmrjenkinslib.configurationParser.IConfigurationParser
import win.thebatman.tmrjenkinslib.platformRunners.IPlatformRunner

class MsDeployBuilder implements IDeployBuilder{

    private ctx
    private msdeploy
    private IPlatformRunner runner
    private String source
    private String operation
    private String computerName
    private String username
    private String password
    private String authtype
    private boolean includeAcls
    private boolean disableAppPoolExtension
    private boolean disableContentExtension
    private boolean disableCertificateExtension
    private String paramFile
    private boolean allowUntrusted

    MsDeployBuilder(String deployToolPath, context, IPlatformRunner runner){
        this.ctx = context
        this.msdeploy = "\"$deployToolPath\""
        this.runner = runner
        this.setOperationToSync()
        this.includeAcls = false
        this.useBasicAuth()
        this.paramFile = null
        this.disableAppPoolExtension = true
        this.disableContentExtension = true
        this.disableCertificateExtension = true
        this.allowUntrusted = true
    }

    @Override
    IDeployBuilder addSourcePackage(String fileToDeploy) {
        this.source = "-source:package='${fileToDeploy}'"
        return this
    }

    IDeployBuilder setOperationToDump(){
        this.operation = "-verb:dump"
        return this
    }

    IDeployBuilder setOperationToSync(){
        this.operation = "-verb:sync"
        return this
    }

    IDeployBuilder setOperationToDelete(){
        this.operation = "-verb:delete"
        return this
    }

    IDeployBuilder setOperationToGetDependencies(){
        this.operation = "-verb:getDependencies"
        return this
    }

    IDeployBuilder setOperationToGetSystemInfo(){
        this.operation = "-verb:getSystemInfo"
        return this
    }

    IDeployBuilder useBasicAuth(){
        this.authtype = "Basic"
        return this
    }

    IDeployBuilder useNTLMAuth(){
        throw new NotImplementedException("Auth type not supported yet")
    }

    IDeployBuilder disableAppPoolExtension(){
        this.disableAppPoolExtension = true
        return this
    }

    IDeployBuilder enableAppPoolExtension(){
        this.disableAppPoolExtension = false
        return this
    }

    IDeployBuilder disableContentExtension(){
        this.disableContentExtension = true
        return this
    }

    IDeployBuilder enableContentExtension(){
        this.disableContentExtension = false
        return this
    }

    IDeployBuilder disableCertificateExtension(){
        this.disableCertificateExtension = true
        return this
    }

    IDeployBuilder enableCertificateExtension(){
        this.disableCertificateExtension = false
        return this
    }

    IDeployBuilder allowUntrusted(){
        this.allowUntrusted = true
        return this
    }

    IDeployBuilder disaallowUntrusted(){
        this.allowUntrusted = false
        return this
    }

    @Override
    IDeployBuilder addDestination(IConfigurationParser configParser) {
        this.computerName = "https://${configParser.getConfig("publishUrl")}/msdeploy.axd?site=${configParser.getConfig("msdeploySite")}"
        this.username = configParser.getConfig("userName")
        this.password = configParser.getConfig("userPWD")
        return this
    }

    @Override
    IDeployBuilder setDeployCredentials(String deployCredentialsId) {

        return this
    }

    public String getCommand(){
        String cmd = "'$msdeploy' ${this.source} ${this.getDestParam()} ${this.operation}"
        if(this.disableAppPoolExtension) cmd += " -disableLink:AppPoolExtension"
        if(this.disableContentExtension) cmd += " -disableLink:ContentExtension"
        if(this.disableCertificateExtension) cmd += " -disableLink:CertificateExtension"
        if(this.allowUntrusted) cmd += " -allowUntrusted"
        return cmd
    }

    @Override
    void deploy() {
        this.runner.run(this.getCommand())
    }

    private String getDestParam(){
        String cmd = "-dest:auto,computerName='${this.computerName}',"
        if (this.authtype == "Basic"){
            cmd += "username='${this.username}',password='${this.password}',authtype='Basic',"
        }
        cmd += "includeAcls=" + (this.includeAcls ? "'True'" : "'False'")
        return cmd
    }
}
/*
* "msdeploy.exe"
* -source:package='xxxxxxx\DeleteMe_Test001.zip'
* -dest:auto,
*       computerName='https://xxxxx.scm.azurewebsites.net:443/msdeploy.axd?site=xxxx',
*       authtype="Basic",
*       username="xxx",
*       password="xxx",
*       includeAcls="False"
*  -verb:sync
*  -disableLink:AppPoolExtension
*  -disableLink:ContentExtension
*  -disableLink:CertificateExtension
*  -allowUntrusted
* */
