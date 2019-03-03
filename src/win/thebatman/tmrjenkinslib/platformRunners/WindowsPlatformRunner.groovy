package win.thebatman.tmrjenkinslib.platformRunners;

public class WindowsPlatformRunner implements IPlatformRunner {

    private runner
    private ctx

    WindowsPlatformRunner(context, runner='bat'){
        this.ctx = context
        this.runner = runner
    }

    @Override
    void run(String command) {
        if(this.runner == "bat"){
            this.ctx.bat "$command"
        }
    }

}
