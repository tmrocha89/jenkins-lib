/**
 * Runs the stage if the last state is allowed
 * @param parameters Map
 * @param body Function to run
 * @return
 */
def call(parameters, body) {
    def isString = parameters in String || parameters in GString

    String name = null
    boolean ignoreBuildStatus = false
    boolean finisheWithErrorState = 'FAILURE'

    if(isString){
        name = parameters
    } else {
        name = parameters['name']
        ignoreBuildStatus = parameters['ignoreBuildStatus'] ?: false
        finisheWithErrorState = parameters['finishWithErrorState']
    }

    milestone()
    if (ignoreBuildStatus || (currentBuild.result == null ||  currentBuild.result == 'SUCCESS' || currentBuild.result == 'STARTED')){
        stage(name){
            try {
                body()
            } catch (e) {
                echo "[SafeStage][ERROR] ${e}"
                currentBuild.result = "$finisheWithErrorState"
            }
        }
    }
}