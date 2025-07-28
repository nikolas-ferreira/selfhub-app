package digital.studioweb.selfhub_app.domain.base

abstract class NoParamsBaseAsyncUseCase<RESULT> {
    abstract suspend fun runAsync(): RESULT
}
