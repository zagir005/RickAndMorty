# 🚀 Rick and Morty Characters

A pet project to demonstrate Android programming skills. Modern Android app built with Clean Architecture, Jetpack Compose, and Decompose.

## ✨ Features

- **🔍 Character Search** - Find your favorite characters by name
- **🎯 Advanced Filtering** - Filter by status, species, and gender
- **💾 Offline Support** - Characters cached locally for offline viewing

## 🏗️ Architecture

This project follows **Clean Architecture** principles with a modular structure:

```
📱 app/                 # Main application module
🔧 core/               # Core utilities, database, network
📊 data/               # Data layer, repositories, mappers
🎯 domain/             # Business logic, use cases
🎨 presentation/        # UI components, screens, navigation
```

## 🛠️ Tech Stack

- **🔄 Kotlin** - Modern programming language
- **🎨 Jetpack Compose** - Declarative UI toolkit
- **🏗️ Clean Architecture** - Separation of concerns
- **💉 Koin** - Dependency injection
- **🗄️ Room** - Local database
- **🌐 Retrofit** - HTTP client
- **📱 Decompose** - Navigation and component management
- **📄 Paging 3** - Efficient data loading
- **🎭 Material 3** - Modern design system

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog or later
- Android SDK 28+
- Kotlin 2.1.10+

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/RickAndMortyTest.git
   cd RickAndMortyTest
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an existing project"
   - Navigate to the cloned directory

3. **Build and Run**
   - Sync project with Gradle files
   - Run on your device or emulator

## 📁 Project Structure

```
RickAndMortyTest/
├── app/                    # Main application
├── core/                   # Core utilities
│   ├── local/             # Database entities and DAOs
│   ├── network/            # API services and DTOs
│   └── di/                # Core dependency injection
├── data/                   # Data layer
│   ├── repository/         # Repository implementations
│   ├── mapper/             # Data mappers
│   └── paging/             # Paging implementation
├── domain/                 # Business logic
│   ├── usecase/            # Use cases
│   └── repository/         # Repository interfaces
└── presentation/           # UI layer
    ├── screen/             # Screen components
    ├── elements/            # Reusable UI elements
    └── theme/              # App theming
```

## 🔧 Configuration

The app uses the [Rick and Morty API](https://rickandmortyapi.com/) for character data. No API key required.

---

<div align="center">

**Made by zagir_lek**
**tg: @zagir_lek**

</div>
