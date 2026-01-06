# Dynamic Widget Dashboard

A **Jetpack Compose Android application** that renders a **dynamic dashboard** based on backend-driven metadata.  
The app supports multiple widget types (currently **Banners** and **Lists**) without relying on hardcoded layouts, following **clean MVVM architecture** and scalable design principles.

---

## 🏗 Architecture & Approach

This project is built using:

- **Kotlin**
- **Jetpack Compose**
- **MVVM (Model–View–ViewModel)**

The core design philosophy is **Separation of Concerns**, ensuring the app remains maintainable, testable, and scalable.

### Layered Structure

- **Data Layer (`data/`)**
  - Contains a fake repository that simulates network responses.
  - Uses artificial delays to demonstrate loading and state handling.

- **UI Layer (`ui/`)**
  - Composable widgets are modular, reusable, and decoupled.
  - The dashboard renders widgets dynamically based on metadata.

- **ViewModel Layer (`viewmodel/`)**
  - Holds business logic and UI state.
  - Exposes state via `StateFlow` to ensure lifecycle awareness.

---

## 🚀 Key Implementation Details

### 1. Widget Identity Handling

Each widget is uniquely identified using an `instanceId` provided in the backend metadata.

- **Data Fetching**
  - The Repository uses `instanceId` (e.g., `"actors"`, `"shows"`) to determine which dataset to return.

- **State Isolation**
  - `instanceId` is used as a unique key in the UI to scope ViewModels and side effects.

---

### 2. List Widget State Management

The **ListWidget** is a **stateful, self-contained component** that manages its own lifecycle.

- **Scoped ViewModels**
  ```kotlin
  viewModel(key = instanceId)
  ```
  This ensures:

  - "Actors" list and "Shows" list have separate ViewModel instances

  - No shared or conflicting state

  - StateFlow
  - The ViewModel exposes:

    - Loading → Shows CircularProgressIndicator

    - Success → Renders the list content

    - Error → Displays a fallback UI

  - Lifecycle Awareness

    - Data fetching is triggered inside:
      ```kotlin
      LaunchedEffect(instanceId)
      ```

    - Prevents unnecessary re-fetching during recomposition or configuration changes.

---

### 3. Scalability (Banners & Lists)

The architecture is designed to scale easily with additional widget types.

  - Parent-Child Decoupling

    - DashboardScreen does not know how a widget renders.

    - It simply iterates through metadata and delegates rendering based on widget type.

  - Stateless vs Stateful Widgets

    - Banner Widget

      - Stateless
      
      - Receives data and renders immediately
      
      - Lightweight and simple

    - List Widget

      - Stateful
      
      - Handles its own data fetching
      
      - Suitable for complex interactions

  - Extensibility

    - Adding a new widget (e.g., Grid) only requires:
  
      - A new data model
      
      - A new Composable
      
      - One additional branch in the when statement inside the Dashboard
     
---

## 🛠 Trade-offs

- Fake Repository

  - No real API was required.
  
  - A singleton Repository with delay() simulates network latency.

- Error Handling

  - Basic error states are implemented.
  
  - In production, this would include retries and granular error types (Network, Server, Timeout).

- Dependency Injection

  - Repository is instantiated directly for simplicity.
  
  - In a larger app, Hilt/Dagger would be used for better testability and decoupling.
 
--- 

## 🔮 Future Improvements

Given more time, the following enhancements would be implemented:

  - Hilt for Dependency Injection
  
    - Fully decouple ViewModels from data sources.
  
  - Retrofit
  
    - Replace fake data with real backend APIs.
  
  - UI Testing
  
    - Add Compose UI tests to verify different widgets render independent data.
  
  - Image Loading
  
    - Use Coil or Glide to load real banner images instead of static text.
   
---

## 📱 How to Run

1. Clone the repo  
   ```
   git clone https://github.com/SaxenaAmogh/DynamicWidgets.git
   ```
3. Open in Android Studio
4. Sync Gradle
5. Run on Android Emulator or connected device
