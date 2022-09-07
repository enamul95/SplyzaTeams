# SplyzaTeams - Android

# Tech stack 
- Minimum SDK level 21
- Jetpack Compose
- Jdk 11
- dagger:hilt-android
- kotlinx-coroutines
- Retrofit2 & Gson - constructing the REST API
- lifecycle:lifecycle-ViewModel-compose
-  compose_version = '1.3.0-beta01'
-  Kotlin

 # JetPack
     - Lifecycle - perform an action when the lifecycle state changes.
     - ViewModel - store and manage UI-related data in a lifecycle-conscious way.
# Architecture
- MVVM Architecture
- Three project layers:
  - data layer- contains  data, data layer, repository implementation, dto(data transfer object) data to presentable data etc
  - domain layer-  contains   model that contains data like interface for repository, business logic, use case(write logic & connect view model)
  - presentation layer- present something to user & pull all composable,views, XML,view model
- di - contain the provided functions
- common - contain the provided functions

