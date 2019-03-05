package win.thebatman.tmrjenkinslib.testRunner

import win.thebatman.tmrjenkinslib.platformRunners.IPlatformRunner

class VSUnitTestRunner implements ITestRunner{

    private ctx
    private String vsUnitTest
    private IPlatformRunner runner
    private String fileName
    private String reportType
    private List<String> testPath
    private boolean isToGenerateReport
    private boolean isToArchiveReport


    VSUnitTestRunner(String toolPath, context, IPlatformRunner runner){
        this.ctx = context
        this.vsUnitTest = toolPath
        this.runner = runner
        this.reportType = "trx"
        this.isToGenerateReport = false
        this.isToArchiveReport = false
        this.fileName = "report_${this.ctx.currentBuild.number}.trx"
        this.testPath = new ArrayList<>()
    }

    @Override
    ITestRunner addTestDirectory(String directoryPath) {
        this.testPath.add(directoryPath)
        return this
    }

    @Override
    ITestRunner setReportType(String reportType) {
        this.reportType = reportType
        return this
    }

    @Override
    ITestRunner setReportName(String reportName) {
        this.fileName = reportName
        return this
    }

    @Override
    ITestRunner generateReport() {
        this.isToGenerateReport = true
        return this
    }

    @Override
    ITestRunner archiveReport() {
        this.isToArchiveReport = true
        return this
    }

    @Override
    void run() {
        this.runner.run("${this.vsUnitTest} ${this.getDllsToTest()} ${this.getReportCommand()}")
        if(this.isToArchiveReport){
            this.ctx.archiveArtifacts "${this.fileName}"
        }
    }

    private String getDllsToTest(){
        StringBuilder strBuilder = new StringBuilder()
        if (this.testPath.size() > 0) {
            this.testPath.each {
                strBuilder.append("\"$it\"")
                strBuilder.append(",")
            }
            strBuilder.setLength(strBuilder.length() - 1)
        }
        return strBuilder.toString()
    }

    private String getReportCommand(){
        String cmd = ""
        if(this.isToGenerateReport){
            cmd = "/logger:${this.reportType};FileName=\"${this.fileName}\""
        }
        return cmd
    }
}
