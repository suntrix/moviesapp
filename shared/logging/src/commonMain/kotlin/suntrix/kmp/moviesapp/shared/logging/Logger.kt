package suntrix.kmp.moviesapp.shared.logging

public interface Logger {
    public enum class Level { DEBUG, INFO, WARNING, ERROR }

    public fun setupTag(name: String)
    public fun debug(message: () -> String)
    public fun debug(functionName: String, params: Map<String, String> = emptyMap(), message: (() -> String)? = null)
    public fun info(message: () -> String)
    public fun info(functionName: String, params: Map<String, String> = emptyMap(), message: (() -> String)? = null)
    public fun warning(message: () -> String, throwable: Throwable? = null)
    public fun warning(functionName: String, params: Map<String, String> = emptyMap(), message: (() -> String)? = null, throwable: Throwable? = null)
    public fun error(message: () -> String, throwable: Throwable? = null)
    public fun error(functionName: String, params: Map<String, String> = emptyMap(), message: (() -> String)? = null, throwable: Throwable? = null)
}

public fun injectLogger(): Logger = KodeinLogger(
    if (BuildKonfig.DEBUG) {
        Logger.Level.DEBUG
    } else {
        Logger.Level.ERROR
    }
)