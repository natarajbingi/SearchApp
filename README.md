sequenceDiagram


    participant Product as Product
    participant Activity as SearchActivity
    participant ViewModel as SearchViewModel
    participant UseCase as SearchProductsUseCase
    participant RepoImpl as DataRepositoryImpl
    participant API as API Service

    Product->>Activity: Types "mug"
    Activity->>ViewModel: searchProducts("mug")
    ViewModel->>ViewModel: Cancels previous job
    ViewModel->>ViewModel: Starts new coroutine (with 300ms delay)
    ViewModel->>ViewModel: Emits `Loading` state
    ViewModel->>UseCase: invoke("mug")
    UseCase->>RepoImpl: searchProducts("mug")
    RepoImpl->>API: HTTP GET /rest/productSearch?s=mug
    API-->>RepoImpl: Returns Product List (JSON)
    RepoImpl->>RepoImpl: Maps ProductResponse to Product domain model
    RepoImpl-->>UseCase: Returns `Result<List<Product>>`
    UseCase-->>ViewModel: Returns `Result<List<Product>>`
    ViewModel->>ViewModel: Emits `Success` state with product list
    ViewModel-->>Activity: Observes `Success` state
    Activity->>Activity: Updates RecyclerView with new data
