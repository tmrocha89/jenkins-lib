package win.thebatman.tmrjenkinslib.configurationParser

interface IConfigurationParser {

    boolean hasConfig(String configName)

    String getConfig(String configName);

}