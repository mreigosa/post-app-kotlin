# post-app-kotlin
Android project using Clean Architecture based on MVP pattern.

The application is written entirely in Kotlin.

The application does network HTTP requests via Retrofit, OkHttp and GSON. Loaded data is saved to
SQL based database Room, which supports offline mode.

Kotlin Coroutines manage background threads with simplified code and reducing needs for callbacks.

Koin is used for dependency injection.

Glide is used for image loading.

The app consists of 2 screens: List of post and post details.

There are three Gradle tasks for testing the project:
* `test` - for running unit tests
* `connectedAndroidTest` - for running Espresso on a connected device
* `jacocoTestReport` - for both unit and Espresso tests generating test report available in `app/build/reports/jacoco/jacocoTestReport`
