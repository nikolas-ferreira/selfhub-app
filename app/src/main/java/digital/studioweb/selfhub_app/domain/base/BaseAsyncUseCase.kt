package digital.studioweb.selfhub_app.domain.base

abstract class BaseAsyncUseCase<RESULT, PARAMS> {
    abstract suspend fun runAsync(params: PARAMS): RESULT
}
