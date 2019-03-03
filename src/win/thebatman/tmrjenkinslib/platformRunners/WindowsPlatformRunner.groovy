package win.thebatman.tmrjenkinslib.platformRunners;

public class WindowsPlatformRunner implements IPlatformRunner {

    private runner

    WindowsPlatformRunner(runner='bat'){
        this.runner = runner
    }

    @Override
    void Run(String command) {

        if(this.runner.equals("bat")){
            bat: "$command"
        }
    }

}
