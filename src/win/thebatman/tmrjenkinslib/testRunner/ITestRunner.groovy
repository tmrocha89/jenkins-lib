package win.thebatman.tmrjenkinslib.testRunner

interface ITestRunner {

    ITestRunner addTestDirectory(String directoryPath)

    ITestRunner setReportType(String reportType)

    ITestRunner setReportName(String reportName)

    ITestRunner generateReport()

    ITestRunner archiveReport()

    void run()

}
