# TmdbLatestMovies
This app was built using the MVP pattern. It pulls the latest movies from TMDB movie api at [themoviedb.org](http://developers.themoviedb.org).

This app has implemented infinite scrolling and has buttons that can be used to filter latest movies by date.

#### Building and running the project
 To run this app, you need to sign up at [themoviedb.org](http://developers.themoviedb.org) for an API Key.

Copy the API key into `local.properties` file in the root of your project as below -

```
tmdbApiKey=<your_tmdb_api_key>
```

#### Libraries Used
 * Design SupportLib
 * AppCompat
 * Retrofit
 * Gson
 * Fresco
 * Dagger
 * RxJava
 * Okhttp
 * JUnit
 * Mockito-Kotlin
