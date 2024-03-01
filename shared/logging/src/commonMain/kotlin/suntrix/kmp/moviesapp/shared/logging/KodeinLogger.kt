package suntrix.kmp.moviesapp.shared.logging

import org.kodein.log.LoggerFactory
import org.kodein.log.filter.entry.minimumLevel
import org.kodein.log.frontend.defaultLogFrontend
import org.kodein.log.newLogger
import org.kodein.log.Logger.Tag
import org.kodein.log.Logger.Level as LogLevel

internal class KodeinLogger : Logger {
    private val loggerFactory = LoggerFactory(
        frontends = listOf(defaultLogFrontend),
        filters = listOf(minimumLevel(if (BuildKonfig.DEBUG) LogLevel.DEBUG else LogLevel.ERROR))
    )

    private var logger: org.kodein.log.Logger = loggerFactory.newLogger(
        tag = Tag("", "KodeinLogger")
    )

    override fun setup(packageName: String, name: String) {
        logger = loggerFactory.newLogger(
            tag = Tag(packageName, name)
        )
    }

    override fun debug(message: () -> String) {
        logger.debug(message)
    }

    override fun debug(
        functionName: String,
        params: Map<String, String>,
        message: (() -> String)?
    ) {
        logger.debug { "$functionName -> params: $params${message?.run { " | ${invoke()}" } ?: ""}" }
    }

    override fun info(message: () -> String) {
        logger.info(message)
    }

    override fun info(
        functionName: String,
        params: Map<String, String>,
        message: (() -> String)?
    ) {
        logger.info { "$functionName -> params: $params${message?.run { " | ${invoke()}" } ?: ""}" }
    }

    override fun warning(message: () -> String, throwable: Throwable?) {
        logger.warning(throwable, message)
    }

    override fun warning(
        functionName: String,
        params: Map<String, String>,
        message: (() -> String)?,
        throwable: Throwable?
    ) {
        logger.warning(throwable) { "$functionName -> params: $params${message?.run { " | ${invoke()}" } ?: ""}" }
    }

    override fun error(message: () -> String, throwable: Throwable?) {
        logger.error(throwable, message)
    }

    override fun error(
        functionName: String,
        params: Map<String, String>,
        message: (() -> String)?,
        throwable: Throwable?
    ) {
        logger.error(throwable) { "$functionName -> params: $params${message?.run { " | ${invoke()}" } ?: ""}" }
    }
}
