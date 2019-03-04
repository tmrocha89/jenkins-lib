package win.thebatman.tmrjenkinslib.compression

interface ICompressionBuilder {

    ICompressionBuilder setFileName(String fileName);

    ICompressionBuilder setDirectory(String directoryPath);

    ICompressionBuilder addFilePatternToExclude(String pattern);

    ICompressionBuilder archiveAsArtifact(boolean isToArchive);

    void compress();
}