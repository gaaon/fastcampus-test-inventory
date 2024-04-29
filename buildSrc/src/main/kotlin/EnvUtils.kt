object EnvUtils {
    fun isAct(): Boolean {
        return System.getenv("ACT") != null
    }
}