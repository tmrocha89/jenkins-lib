package win.thebatman.tmrjenkinslib.platformRunners

import org.junit.Assert
import win.thebatman.tmrjenkinslib.compilers.MsBuildBuilder

class WindowsPlatformRunnerTest extends GroovyTestCase {

    private IPlatformRunner runner
    @Override
    void setUp() {
        super.setUp()
        this.runner = new WindowsPlatformRunner()
    }

    void test() {

    }

}
