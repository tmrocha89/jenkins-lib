package win.thebatman.tmrjenkinslib.platformRunners;

public class WindowsPlatformRunner implements IPlatformRunner {

    private runner

    WindowsPlatformRunner(runner){
        if(runner == null)
            throw new RuntimeException("Valid runner not set")

        this.runner = runner;
    }

    @Override
    void Run(String command) {
        this.runner(command)
    }

}
