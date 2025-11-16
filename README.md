# 📱 Registro de Actividades TI - UTA

<div align="center">

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Volley](https://img.shields.io/badge/Volley-1.1.0-orange?style=for-the-badge)
![API](https://img.shields.io/badge/API-21+-brightgreen?style=for-the-badge)

Aplicación móvil Android para la gestión y registro de actividades del área de Tecnologías de la Información de la Universidad Técnica de Ambato.

[Características](#-características) • [Tecnologías](#-tecnologías) • [Instalación](#-instalación) • [Estructura](#-estructura-del-proyecto)

</div>

---

## 📋 Descripción

Sistema móvil desarrollado en Android que permite a los usuarios del área de TI registrar, consultar y gestionar actividades técnicas realizadas en la universidad. La aplicación facilita el seguimiento de configuraciones, soporte técnico y reuniones, manteniendo un registro organizado y accesible desde dispositivos móviles.

## ✨ Características

### 🔐 Autenticación
- Sistema de login seguro con validación de credenciales
- Visualización/ocultación de contraseña
- Registro de nuevos usuarios

### 📝 Gestión de Actividades
- **Registro de Actividades**: Permite registrar diferentes tipos de actividades:
  - ⚙️ Configuración (Teléfono IP, Servidor BD, Servidor Correo, PC, Tablet)
  - 🛠️ Soporte SI (Ventas, Compras, Facturación, Nómina, Inventarios)
  - 📅 Reuniones (Planificación, Diaria)
- Selección de solicitante y estado de la actividad
- Registro de horas trabajadas y observaciones
- Selector de fecha integrado

### 🔍 Consultas
- Visualización de actividades registradas
- Detalles completos de cada actividad
- Filtrado y búsqueda de registros

### ⚙️ Configuración
- Cambio de contraseña de usuario
- Gestión de datos personales

## 🛠️ Tecnologías

- **Lenguaje**: Java
- **Plataforma**: Android SDK
- **API Mínima**: Android 5.0 (API 21)
- **Target SDK**: Android 12 (API 32)
- **Librerías Principales**:
  - [Volley](https://github.com/google/volley) - Para peticiones HTTP
  - AndroidX AppCompat
  - Material Design Components
  - RecyclerView y CardView

## 📦 Instalación

### Requisitos Previos

- Android Studio (versión recomendada: Arctic Fox o superior)
- JDK 8 o superior
- Android SDK con API 21+
- Dispositivo Android o emulador

### Pasos de Instalación

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/tu-usuario/RegistroActividadesTIApp.git
   cd RegistroActividadesTIApp
   ```

2. **Abrir en Android Studio**
   - Abre Android Studio
   - Selecciona "Open an existing project"
   - Navega hasta la carpeta del proyecto y selecciónala

3. **Sincronizar Gradle**
   - Android Studio sincronizará automáticamente las dependencias
   - Si no lo hace, ve a `File > Sync Project with Gradle Files`

4. **Configurar el dispositivo**
   - Conecta un dispositivo Android o inicia un emulador
   - Habilita las opciones de desarrollador y depuración USB en tu dispositivo

5. **Ejecutar la aplicación**
   - Haz clic en el botón "Run" (▶️) o presiona `Shift + F10`
   - Selecciona tu dispositivo y espera a que se instale la aplicación

## 📁 Estructura del Proyecto

```
RegistroActividadesTIApp/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/uta/
│   │   │   │   ├── MainActivity.java          # Pantalla de login
│   │   │   │   ├── Menu.java                  # Menú principal
│   │   │   │   ├── RegistroActividad.java     # Registro de actividades
│   │   │   │   ├── ConsultaActividades.java   # Consulta de actividades
│   │   │   │   ├── CambioContrasena.java      # Cambio de contraseña
│   │   │   │   ├── RegistrarUsario.java       # Registro de usuarios
│   │   │   │   ├── Datos.java                 # Gestión de datos
│   │   │   │   ├── Actividades.java           # Modelo de datos
│   │   │   │   └── AdaptadorActividades.java  # Adaptador para RecyclerView
│   │   │   ├── res/
│   │   │   │   ├── layout/                    # Layouts XML
│   │   │   │   ├── drawable/                  # Recursos gráficos
│   │   │   │   └── values/                    # Strings, colores, temas
│   │   │   └── AndroidManifest.xml
│   │   └── build.gradle
│   └── build.gradle
└── README.md
```

## 🎨 Capturas de Pantalla

> 💡 **Nota**: Agrega capturas de pantalla de tu aplicación aquí para mostrar la interfaz de usuario.

```
📸 Screenshots/
├── login.png
├── menu.png
├── registro_actividad.png
└── consulta_actividades.png
```

## 🔌 API Backend

La aplicación se conecta a un backend PHP alojado en:
- **URL Base**: `https://actividadesuta.000webhostapp.com/`
- **Endpoints principales**:
  - `buscarUsuario.php` - Autenticación de usuarios
  - `RegistrarActividad.php` - Registro de nuevas actividades
  - Endpoints de consulta y actualización

## 🚀 Funcionalidades Principales

### Flujo de Usuario

1. **Login** → El usuario ingresa sus credenciales
2. **Menú Principal** → Acceso a las diferentes funcionalidades
3. **Registro** → Captura de información de actividades
4. **Consulta** → Visualización y búsqueda de registros
5. **Configuración** → Gestión de cuenta y contraseña

## 📝 Licencia

Este proyecto es de uso interno para la Universidad Técnica de Ambato.

## 👨‍💻 Autor

**Tu Nombre**
- GitHub: [@tu-usuario](https://github.com/tu-usuario)
- Email: tu-email@ejemplo.com

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Para cambios importantes:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📞 Soporte

Si tienes preguntas o encuentras algún problema, por favor abre un [issue](https://github.com/tu-usuario/RegistroActividadesTIApp/issues) en el repositorio.

---
