package win.thebatman.tmrjenkinslib.pacakgeManagers

import win.thebatman.tmrjenkinslib.platformRunners.IPlatformRunner

class ComposerPackageManager implements IPackageManager{

    private ctx
    private php
    private composer
    private IPlatformRunner runner

    ComposerPackageManager(String phpPath, String toolPath, context, IPlatformRunner runner){
        this.php = "\"$phpPath\""
        this.composer = "\"$toolPath\""
        this.ctx = context
        this.runner = runner
    }

    ComposerPackageManager(String toolPath, context, IPlatformRunner runner){
        this("php", toolPath, context, runner)
    }

    @Override
    def update() {
        this.runner.run("$php $composer update")
    }
}
