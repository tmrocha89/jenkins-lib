package win.thebatman.tmrjenkinslib.compilers

import org.junit.Assert

class MsBuildBuilderTest extends GroovyTestCase {

    private MsBuildBuilder builder;
    @Override
    void setUp() {
        super.setUp()
        this.builder = new MsBuildBuilder()
    }

    void testAddProject() {
        MsBuildBuilder temp = this.builder.addProject("newProject.sln")
        org.junit.Assert.assertEquals(temp, this.builder)
    }

    void testAddParameter() {
        MsBuildBuilder temp = this.builder.addParameter("parameter")
        org.junit.Assert.assertEquals(temp, this.builder)
    }

    void testGetCommandUsingAddParameter() {
        String project = "project1.sln"
        String param1 = "paramter1"
        String param2 = "parameter2"
        String expected = "msbuild.exe \"$project\" $param1 $param2"
        builder.addProject(project)
                .addParameter(param1)
                .addParameter(param2)
        String command = builder.getCommand()
        Assert.assertEquals(command, expected)
    }

    void testGetCommand() {
        String project = "project1.sln"
        String config = "Debug"
        String platform = "x64"
        String expected = "msbuild.exe \"$project\" /t:clean /t:build /t:rebuild /p:Configuration=$config /p:Platform=$platform"
        builder.addProject(project)
                .setToClean()
                .setToBuild()
                .setToRebuild()
                .setConfiguration(config)
                .setPlatform(platform)
        String command = builder.getCommand()
        Assert.assertEquals(command, expected)
    }
}
