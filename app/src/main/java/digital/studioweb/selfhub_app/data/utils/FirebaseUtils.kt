package digital.studioweb.selfhub_app.data.utils

/**
 * Utility class for Firebase constants.
 * Collection names are kept here, but sensitive values like IDs are moved to BuildConfig.
 */
object FirebaseUtils {
    // Collection names
    const val FIREBASE_RESTAURANTS_COLLECTION_NAME = "restaurant"
    const val FIREBASE_CATEGORIES_COLLECTION_NAME = "categories"
    const val FIREBASE_PRODUCTS_COLLECTION_NAME = "products"
    
    // Field names
    const val FIELD_NAME = "name"
    const val FIELD_PRICE = "price"
    const val FIELD_ADD_ITEMS = "addItems"
}
