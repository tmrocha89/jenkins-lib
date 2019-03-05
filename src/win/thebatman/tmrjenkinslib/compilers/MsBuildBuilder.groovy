package win.thebatman.tmrjenkinslib.compilers

import win.thebatman.tmrjenkinslib.platformRunners.IPlatformRunner

class MsBuildBuilder implements ICompiler {

    private ctx
    private msbuild
    private String projectPath
    private List<String> parametersList
    private IPlatformRunner runner
    private boolean deployOnBuild
    private String publishProfileName
    private String passwordCredentialId

    MsBuildBuilder(){
        this.msbuild = "msbuild.exe"
        this.projectPath = ""
        this.parametersList = new ArrayList<>()
        this.deployOnBuild = false
        this.publishProfileName = null
        this.passwordCredentialId = null
    }

    MsBuildBuilder(String toolPath, context, IPlatformRunner runner){
        this()
        this.msbuild = toolPath
        this.ctx = context
        this.runner = runner
    }

    @Override
    MsBuildBuilder addProject(String project){
        if(project != null && project.trim().length() > 0){
            this.projectPath = project.trim()
        }
        return this
    }

    @Override
    MsBuildBuilder addParameter(String parameter){
        if(parameter != null && parameter.trim().length() > 0){
            this.parametersList.add(parameter.trim())
        }
        return this
    }

    @Override
    MsBuildBuilder setToClean() {
        return this.addParameter("/t:clean")
    }

    @Override
    MsBuildBuilder setToBuild() {
        return this.addParameter("/t:build")
    }

    @Override
    MsBuildBuilder setToRebuild() {
        return this.addParameter("/t:rebuild")
    }

    MsBuildBuilder setToRestore() {
        return this.addParameter("/t:Restore")
    }

    @Override
    MsBuildBuilder setPlatform(String platform) {
        if(platform != null && platform.trim().length() > 0){
            return this.addParameter("/p:Platform=${platform.trim()}")
        }
        return this
    }

    @Override
    MsBuildBuilder setConfiguration(String configuration) {
        if(configuration != null && configuration.trim().length() > 0){
            return this.addParameter("/p:Configuration=${configuration.trim()}")
        }
        return this
    }

    MsBuildBuilder setPublishProfileByName(String name) {
        return this.addParameter("/p:PublishProfile=\"$name\"")
    }

    MsBuildBuilder setDeployOnBuild() {
        return this.addParameter("/p:DeployOnBuild=true")
    }

    MsBuildBuilder setPassword(String passwordCredentialId) {
        this.passwordCredentialId = passwordCredentialId
        return this
    }


    @Override
    void run(){
        if(this.passwordCredentialId == null )
            this.runner.run(this.getCommand())
        else
            this.ctx.withCredentials([this.ctx.string(credentialsId: "${this.passwordCredentialId}", variable: 'PASSWORD')]) {
                this.runner.run(this.getCommand()+ " " + this.getPasswordParam("${this.ctx.env.PASSWORD}"))
            }
    }

    @Override
    String getCommand(){
        return "\"${this.msbuild}\" \"${this.projectPath}\" ${this.getParams()}"
    }

    private String getParams(){
        StringBuilder strBuilder = new StringBuilder()
        if (this.parametersList.size() > 0){
            this.parametersList.each{
                strBuilder.append(it)
                strBuilder.append(" ")
            }
            strBuilder.setLength(strBuilder.length() - 1)
        }

        return strBuilder.toString()
    }

    private String getPasswordParam(String password){
        return "/p:Password=\"$password\""
    }
}
