package win.thebatman.tmrjenkinslib.compilers

import win.thebatman.tmrjenkinslib.platformRunners.IPlatformRunner

class MsBuildBuilder implements ICompiler {

    private steps
    private msbuild
    private String projectPath
    private List<String> parametersList
    private IPlatformRunner runner

    MsBuildBuilder(){
        this.msbuild = "msbuild.exe"
        this.projectPath = ""
        this.parametersList = new ArrayList<>()
    }

    MsBuildBuilder(String toolPath, steps, IPlatformRunner runner){
        this()
        this.msbuild = toolPath
        this.steps = steps
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

    @Override
    void run(){
        this.runner.Run(this.getCommand())
    }

    @Override
    String getCommand(){
        return "${this.msbuild} \"${this.projectPath}\" ${this.getParams()}"
    }

    private String getParams(){
        StringBuilder strBuilder = new StringBuilder()
        this.parametersList.each{
            strBuilder.append(it)
            strBuilder.append(" ")
        }
        strBuilder.setLength(strBuilder.length() - 1,)
        return strBuilder.toString()
    }

}
