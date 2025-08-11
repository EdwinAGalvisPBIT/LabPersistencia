# 🐾 Aplicación CRUD de Mascotas

Aplicación Android desarrollada en **Java** usando **Android Studio 2025** que permite registrar, visualizar y actualizar mascotas.  
La interfaz está construida siguiendo el patrón **Material Design** e incluye manejo de base de datos **SQLite**.

---

## 📌 Características principales

- 📋 **Registro de mascotas** con nombre, edad, raza y foto.
- 📖 **Listado de mascotas** con diseño en **RecyclerView**.
- ✏️ **Edición** de información de mascotas.
- 🗃 Base de datos **SQLite** para almacenamiento local.
- 🎨 Diseño responsivo y adaptado a Material Design.

---

## 🛠 Tecnologías utilizadas

- **Java** (Android)
- **SQLite** (almacenamiento local)
- **RecyclerView** y **CardView**
- **Material Components**
- **ConstraintLayout**
- **View Binding**

---

## 📂 Estructura del proyecto

app/
├── java/com/example/myapplab/
│ ├── MainActivity.java
│ ├── Mascota.java
│ ├── MascotaAdapter.java
│ ├── MascotaDbHelper.java
│ ├── FavoritosActivity.java
│ └── ...
│
├── res/
│ ├── layout/
│ │ ├── activity_main.xml
│ │ ├── activity_favoritos.xml
│ │ └── item_mascota.xml
│ ├── drawable/
│ └── values/
│ ├── strings.xml
│ ├── colors.xml
│ └── styles.xml
│
└── AndroidManifest.xml


---

## 📊 Modelo de base de datos

![Modelo de Base de Datos](modelo_mascota.png)

<img src="C:\Users\ITNVUSER\AndroidStudioProjects\MyAppLabTwo-main\app\src\main\res\drawable\modelo_mascota.png"/>

> Este diagrama muestra la estructura de la tabla utilizada para almacenar los registros de mascotas en SQLite.

---

## 📸 Capturas de pantalla

| Pantalla de inicio | Lista de mascotas |
|--------------------|------------------|
| ![Inicio](captures/inicio.png) | ![Lista](captures/lista.png) |

<img src="C:\Users\ITNVUSER\AndroidStudioProjects\MyAppLabTwo-main\app\src\main\res\drawable\pantalla_principal.png"/>
---
<img src="C:\Users\ITNVUSER\AndroidStudioProjects\MyAppLabTwo-main\app\src\main\res\drawable\pantalla_ranking.png"/>
---
<img src="C:\Users\ITNVUSER\AndroidStudioProjects\MyAppLabTwo-main\app\src\main\res\drawable\pantalla_posiciones.png"/>
---

## 📦 Instalación y ejecución

1. **Clonar el repositorio**

   git clone https://github.com/EdwinAGalvisPBIT/LabPersistencia.git

2. **Abrir en Android Studio**

    Ir a File → Open y seleccionar la carpeta del proyecto.

3. **Ejecutar**

   Conectar un dispositivo físico o usar un emulador.

4. **Presionar ▶️ Run en Android Studio.**
