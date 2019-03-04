package win.thebatman.tmrjenkinslib.deployment

import win.thebatman.tmrjenkinslib.configurationParser.IConfigurationParser

interface IDeployBuilder {

    IDeployBuilder addSourcePackage(String fileToDeploy);

    IDeployBuilder addDestination(IConfigurationParser configParser);

    IDeployBuilder setDeployCredentials(String deployCredentialsId);

    void deploy();
}