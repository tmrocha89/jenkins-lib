package win.thebatman.tmrjenkinslib.platformRunners;

public class WindowsPlatformRunner implements IPlatformRunner {

    private runner
    private ctx

    WindowsPlatformRunner(context, runner='bat'){
        this.ctx = context
        this.runner = runner
    }

    @Override
    void Run(String command) {

        this.ctx.echo "Runner is $runner"
        if(this.runner.equals("bat")){
            this.ctx.bat "$command"
        }
        this.ctx.echo "end"
    }

}
