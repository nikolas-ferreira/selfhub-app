package digital.studioweb.selfhub_app.domain.features.models

enum class PaymentMethod(val paymentMethod: String) {
    PIX("PIX"),
    CREDIT_CARD("CREDIT_CARD"),
    DEBIT_CARD("DEBIT_CARD"),
    MONEY("MONEY"),
    UNKNOWN("UNKNOWN")
}