<h1>
  Simple MVVM Example
</h1>
  
This is a simple example of MVVM architecture making use of:
  - Kotlin
  - RxAndroid
  - LiveData
  - DataBinding
  - ViewModel
  - Dagger2
 
It has a base package to reduce boilerplate code and allow programer to save time and code activities faster and more legible.

This diagram shows the main project's structure
<img src="http://msd117.es/cv/images/mvvm_architecture.png" />    

-----------------------------------------------------------------------------------------------------------------------------------------

<img src="http://msd117.es/cv/images/mvvm_flow.png" />

The architecture works as follow:
  - <strong>UI</strong> elements (Activities, Fragments...) observes ViewModel's data through LiveData and notify ViewModel with user's actions.
  - <strong>ViewModel</strong> request data to domain layer and observe changes making use of Rx (or Coroutines) then notify UI layer by posting LiveData. ViewModels are injected using a factory.
  - The <strong>Navigator</strong> layer keeps a reference to UI layer (Activity) and ViewModel just request changes based on business logic. To avoid memory leaks we use a weak reference and for this reason this layer uses singleton pattern
  - All this structure is injected by <strong>Dagger 2</strong> but we could achieve the same using other DI just like Koin.
