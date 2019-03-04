package win.thebatman.tmrjenkinslib.compression

class ZipCompressionBuilder implements ICompressionBuilder{

    private ctx
    private String zipName
    private String dirPath
    private boolean isToArchive
    private List<String> globPattern

    ZipCompressionBuilder(context){
        this.ctx = context
        this.globPattern = new ArrayList<>()
        this.isToArchive = false
    }

    @Override
    ICompressionBuilder setFileName(String fileName) {
        this.zipName = "\"$fileName\""
        return this
    }

    @Override
    ICompressionBuilder setDirectoryPath(String directoryPath) {
        this.dirPath = "\"$directoryPath\""
        return this
    }

    @Override
    ICompressionBuilder addFilePatternToExclude(String pattern) {
        this.globPattern.add(pattern)
        return this
    }

    @Override
    ICompressionBuilder archiveAsArtifact(boolean isToArchive) {
        this.isToArchive = isToArchive
        return this
    }

    @Override
    void compress() {
        this.ctx.zip archive: this.isToArchive, dir: this.dirPath, glob: this.getGlobParams(), zipFile: this.zipName
    }

    private String getGlobParams(){
        StringBuilder strBuilder = new StringBuilder()
        this.globPattern.each{
            strBuilder.append(it)
            strBuilder.append(",")
        }
        strBuilder.setLength(strBuilder.length() - 1,)
        return strBuilder.toString()
    }
}
