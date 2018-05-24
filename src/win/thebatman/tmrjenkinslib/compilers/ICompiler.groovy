package win.thebatman.tmrjenkinslib.compilers

interface ICompiler {

    ICompiler addProject(String project)

    ICompiler addParameter(String parameter)

    ICompiler setToClean()

    ICompiler setToBuild()

    ICompiler setToRebuild()

    ICompiler setPlatform(String platform)

    ICompiler setConfiguration(String configuration)

    void run()

    String getCommand()
}