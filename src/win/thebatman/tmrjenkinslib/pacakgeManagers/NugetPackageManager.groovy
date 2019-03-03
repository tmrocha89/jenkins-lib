package win.thebatman.tmrjenkinslib.pacakgeManagers

import win.thebatman.tmrjenkinslib.platformRunners.IPlatformRunner

class NugetPackageManager implements IPackageManager{

    private ctx
    private nuget
    private IPlatformRunner runner

    NugetPackageManager(String toolPath, context, IPlatformRunner runner){
        this.nuget = "\"$toolPath\""
        this.ctx = context
        this.runner = runner
    }

    @Override
    def update() {
        this.runner.run("$nuget update")
    }
}
