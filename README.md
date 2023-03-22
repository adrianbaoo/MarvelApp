# MarvelApp
Android(Kotlin) app connected with the Marvel API to show the list and detail of the characters

## Architecture
This application uses a clean architecture to structure its classes, dividing its architecture into 4 layers:
[Presentation(App)] - [UseCases] - [Domain] - [Data]

1. [Presentation]: it is the layer that interacts with the user interface (activities, fragments,...).
2. [UseCases]: are the actions that the user can trigger.
3. [Domain]: contains all the business models.
4. [Data]: it is the data source layer (web services, access to the database, ...)

This application uses the [MVP (Model View Presenter)] pattern in the presentation layer.

### Libraries
* [Android Support Library][support-lib]
* [Retrofit][retrofit] for REST api communication
* [Glide][glide] for image loading

[support-lib]: https://developer.android.com/topic/libraries/support-library/index.html
[retrofit]: http://square.github.io/retrofit
[glide]: https://github.com/bumptech/glide