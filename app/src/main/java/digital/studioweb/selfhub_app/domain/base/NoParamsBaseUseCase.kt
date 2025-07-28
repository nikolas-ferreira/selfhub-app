package digital.studioweb.selfhub_app.domain.base

abstract class NoParamsBaseUseCase<RESULT> {
    abstract fun runSync(): RESULT
}
