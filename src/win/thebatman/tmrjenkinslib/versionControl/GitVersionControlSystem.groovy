package win.thebatman.tmrjenkinslib.versionControl

import win.thebatman.tmrjenkinslib.platformRunners.IPlatformRunner

class GitVersionControlSystem implements IVersionControlSystem {

    private ctx
    private runner

    GitVersionControlSystem(context, IPlatformRunner runner){
        this.ctx = context
        this.runner = runner
    }

    @Override
    void CloneCurrentProject() {
        this.ctxcheckout this.ctx.scm
    }
}
