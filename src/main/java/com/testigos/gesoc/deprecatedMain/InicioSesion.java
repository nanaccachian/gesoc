// package MainPackage;

// import com.example.gesoc.persistence.DAO;
// import model.domain.usuarios.NivelPermisos;
// import model.domain.usuarios.Usuario;
// import model.services.passwordValidator.ValidadorContrasenia;

// public class InicioSesion {

// public static Usuario inicioSesion(DAO database) {
// Usuario user = null;
// boolean codIncorrecto = false;

// do {
// System.out.println("Desea crear un usuario (1) o iniciar sesion (2)");
// int codOp = Scaneador.scanearInt();

// if (codOp == 1)
// user = crearUsuario(database);
// else if (codOp == 2)
// user = ingresarUsuario(database);
// else {
// System.out.println("Código de operación inválido");
// codIncorrecto = true;
// }

// } while (codIncorrecto);

// return user;
// }

// public static Usuario crearUsuario(DAO database) {

// database.createEntityManager();
// database.beginTransaction();

// String usuario = pedirUsuarioNuevo(database);
// String contrasenia = pedirContraseniaNueva();
// NivelPermisos permits = pedirPermisos(database);

// Usuario user = new Usuario(usuario, contrasenia, permits);

// System.out.println("Usuario creado con exito: " + user.toString());

// database.persist(user);
// database.commit();
// database.close();

// return user;
// }

// private static String pedirUsuarioNuevo(DAO database) {

// String usuario;
// boolean existeUsuario;

// do {
// System.out.println("Ingrese el nombre del usuario");
// usuario = Scaneador.scanearString();
// existeUsuario = database.findEgreso(Usuario.class, usuario) != null;
// if (existeUsuario)
// System.out.println("Nombre de usuario ya encontrado, elija otro nombre");
// } while (existeUsuario);

// return usuario;
// }

// private static String pedirContraseniaNueva() {

// String contrasenia;
// boolean validacion;

// do {
// System.out.println("Ingrese la contraseña que desea (8 caracteres mínimo, una
// mayúscula mínimo, ");
// System.out.println(
// "un número mínimo, una minúscula mínimo y no son válidos a partir de tres
// caracteres repetidos o secuenciales)");
// contrasenia = Scaneador.scanearString();
// validacion = ValidadorContrasenia.validarContrasenia(contrasenia);
// if (!validacion)
// System.out.println("Contraseña inválida, elija otra");
// } while (!validacion);

// return contrasenia;
// }

// private static NivelPermisos pedirPermisos(DAO database) {

// NivelPermisos permits = null;
// boolean validacion;

// do {
// validacion = true;

// System.out.println("Administrador (1) o Estándar (2) ");
// int codOp = Scaneador.scanearInt();

// if (codOp == 1)
// permits = NivelPermisos.ADMINISTRADOR;
// else if (codOp == 2)
// permits = NivelPermisos.ESTANDAR;
// else {
// System.out.println("Codigo de Operación inválido");
// validacion = false;
// }

// database.persist(permits);

// } while (!validacion);

// return permits;
// }

// public static Usuario ingresarUsuario(DAO database) {

// Usuario usuario = pedirUsuario(database);
// boolean contraseniaCorrecta;

// do {
// System.out.println("Ingrese la contrasenia");
// String contrasenia = Scaneador.scanearString();

// contraseniaCorrecta = usuario.getContrasenia().equals(contrasenia);

// if (!contraseniaCorrecta)
// System.out.println("Contraseña incorrecta");

// } while (!contraseniaCorrecta);

// System.out.println("Sesion iniciada como: " + usuario.toString());

// return usuario;
// }

// private static Usuario pedirUsuario(DAO database) {

// database.createEntityManager();

// Usuario usuario;

// do {
// System.out.println("Ingrese el nombre del usuario");
// String nombre = Scaneador.scanearString();
// usuario = database.findEgreso(Usuario.class, nombre);
// if (usuario == null) {
// System.out.println("Usuario inexistente");
// }
// } while (usuario == null);

// database.close();

// return usuario;
// }
// }
