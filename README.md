# Rick and Morty Explorer App

A simple Android app to browse characters and locations from the [Rick and Morty API](https://rickandmortyapi.com/), built using modern Android development practices.

---

## ✨ Features

* **Characters Screen**

  * Grid list of characters with image, name, and status
  * Pagination support
  * Tap to view character details

* **Character Detail Screen**

  * Image, name, species, origin, location, and episodes

* **Locations Screen**

  * List of locations with name, type, and dimension

* **Location Detail Screen**

  * Location details
  * Horizontally scrollable list of residents (characters)

---

## 🪤 Tech Stack

| Layer                | Library                    |
| -------------------- | -------------------------- |
| UI                   | Jetpack Compose            |
| Navigation           | Jetpack Navigation Compose |
| Dependency Injection | Koin                       |
| Networking           | Retrofit + OkHttp          |
| Image Loading        | Coil                       |
| Async                | Kotlin Coroutines + Flows  |

---

## ⚖️ Dependencies

```kotlin
// Retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // Koin
    implementation(project.dependencies.platform("io.insert-koin:koin-bom:$koin_version"))
    implementation(libs.koin.core)
    implementation (libs.koin.androidx.compose)
    implementation (libs.koin.android)

    // Coil
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    // Compose
    implementation("androidx.compose.ui:ui")
    implementation(libs.androidx.navigation.compose)

// Coroutines & Flow
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android")
```

---

## 👷 Setup

1. Clone the repo
2. Build and run

---

## 🎓 Learnings

* Using Compose for responsive UI
* Async data loading with Kotlin Flows
* Clean architecture with Koin for DI
* Lazy loading and state handling

---

## 🌐 API Reference

* [https://rickandmortyapi.com/documentation](https://rickandmortyapi.com/documentation)

---

## ✨ Screenshots
![Characters Screen](assets/characters_screen.png)
![Character Details Screen](assets/character_details_screen.png)
![Locations Screen](assets/locations_screen.png)
![Location Details Screen](assets/location_details_screen.png)

---

