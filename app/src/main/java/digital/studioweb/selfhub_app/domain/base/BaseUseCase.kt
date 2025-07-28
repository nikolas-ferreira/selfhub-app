package digital.studioweb.selfhub_app.domain.base

abstract class BaseUseCase<RESULT, PARAMS> {
    abstract fun runSync(params: PARAMS): RESULT
}
