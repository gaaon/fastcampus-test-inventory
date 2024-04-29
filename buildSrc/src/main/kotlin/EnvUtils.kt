object EnvUtils {
    fun isAct(): Boolean {
        return System.getenv("ACT") != null
    }

    fun isLocal(): Boolean {
        return System.getenv("CI") == null
    }

    fun isCI(): Boolean {
        return System.getenv("CI") != null
    }
}