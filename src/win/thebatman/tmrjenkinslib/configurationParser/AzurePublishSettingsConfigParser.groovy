package win.thebatman.tmrjenkinslib.configurationParser

//import groovy.util.XmlSlurper;

class AzurePublishSettingsConfigParser implements IConfigurationParser{

    private ctx
    private String configFileId
    private resultMap

    AzurePublishSettingsConfigParser(context, String publishSettingsFileId){
        this.ctx = context
        this.configFileId = publishSettingsFileId
        this.resultMap = [:]
        this.parseFile(publishSettingsFileId)
    }

    @Override
    boolean hasConfig(String configName) {
        return this.resultMap.containsKey(configName)
    }

    @Override
    String getConfig(String configName) {
        try{
            return this.resultMap[configName]
        }catch(Exception ex){
            return null
        }
    }

    private void parseFile(String fileId){
        this.ctx.configFileProvider([configFile(fileId: "$fileId", targetLocation: 'XML_TEXT')]) {
            def publishSettings = new XmlSlurper().parseText("$XML_TEXT")
            int msDeployIndex = publishSettings.publishData.publishProfile[0].publishMethod.text() == "MSDeploy" ? 0 : 1
            def publishProfile = publishSettings.publishData.publishProfile[msDeployIndex]
            this.resultMap["profileName"] = publishProfile.profileName.text()
            this.resultMap["publishMethod"] = publishProfile.publishMethod.text()
            this.resultMap["publishUrl"] = publishProfile.publishUrl.text()
            this.resultMap["msdeploySite"] = publishProfile.msdeploySite.text()
            this.resultMap["userName"] = publishProfile.userName.text()
            this.resultMap["userPWD"] = publishProfile.userPWD.text()
            this.resultMap["destinationAppUrl"] = publishProfile.destinationAppUrl.text()
            this.resultMap["SQLServerDBConnectionString"] = publishProfile.SQLServerDBConnectionString.text()
            this.resultMap["mySQLDBConnectionString"] = publishProfile.mySQLDBConnectionString.text()
            this.resultMap["hostingProviderForumLink"] = publishProfile.hostingProviderForumLink.text()
            this.resultMap["controlPanelLink"] = publishProfile.controlPanelLink.text()
            this.resultMap["webSystem"] = publishProfile.webSystem.text()
        }
    }
}
