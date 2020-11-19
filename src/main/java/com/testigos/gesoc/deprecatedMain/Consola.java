// package MainPackage;

// import static model.domain.entidades.Categorizador.SectoresEnum.AGROPECUARIO;
// import static model.domain.entidades.Categorizador.SectoresEnum.COMERCIO;
// import static model.domain.entidades.Categorizador.SectoresEnum.CONSTRUCCION;
// import static
// model.domain.entidades.Categorizador.SectoresEnum.INDUSTRIAYMINERIA;
// import static model.domain.entidades.Categorizador.SectoresEnum.SERVICIOS;

// import java.time.LocalDate;
// import java.util.ArrayList;
// import java.util.List;

// import model.services.apis.APIManager;
// import model.services.apis.domain.City;
// import model.services.apis.domain.Country;
// import model.services.apis.domain.State;
// import model.domain.egresos.Categoria;
// import model.domain.egresos.CriterioDeCategorizacion;
// import model.domain.egresos.CriterioSeleccion;
// import model.domain.egresos.DocumentoComercial;
// import model.domain.egresos.Egreso;
// import model.domain.egresos.EgresoConPresupuestos;
// import model.domain.egresos.EntidadProveedora;
// import model.domain.egresos.Item;
// import model.domain.egresos.ManejadorDeCategorias;
// import model.domain.egresos.MenorPresupuesto;
// import model.domain.egresos.PersonaProveedora;
// import model.domain.egresos.Presupuesto;
// import model.domain.egresos.Proveedor;
// import model.domain.entidades.Base;
// import model.domain.entidades.DireccionPostal;
// import model.domain.entidades.Juridica;
// import model.domain.entidades.JuridicaIGJ;
// import model.domain.entidades.Categorizador.SectoresEnum;
// import model.domain.entidades.TipoOrg.Micro;
// import model.domain.entidades.TipoOrg.OSC;
// import model.domain.entidades.TipoOrg.TipoOrg;
// import model.domain.ingresos.Ingreso;
// import model.domain.usuarios.Mensaje;
// import model.domain.usuarios.NivelPermisos;
// import model.domain.usuarios.Usuario;
// import com.example.gesoc.persistence.DAO;
// import model.services.budgetValidator.ValidadorPresupuestos;

// public class Consola {

// private static final DAO dbManager = DAO.getInstance();

// private static final SesionActiva sesionActiva = new SesionActiva();

// public static void ejecutar() {

// boolean iniciarSesion;

// do {
// sesionActiva.setUsuario(InicioSesion.inicioSesion(dbManager));
// mensajesPara(sesionActiva.getUsuario());

// operaciones_init();

// System.out.println("Sesion cerrada");
// System.out.println("Desea abrir otra sesion? (true/false)");
// iniciarSesion = Scaneador.scanearBoolean();
// } while (iniciarSesion);
// }

// private static void mensajesPara(Usuario usuario) {
// dbManager.createEntityManager();
// dbManager.beginTransaction();

// List<Mensaje> mensajes = dbManager.getMensajesDe(usuario);
// System.out.println("-----BANDEJA DE MENSAJES-----");
// for (Mensaje mensaje : mensajes) {
// System.out.println(mensaje.getMensaje());
// dbManager.remove(mensaje);
// dbManager.commit();
// }
// System.out.println("-----FIN DE MENSAJES-----");
// dbManager.close();
// }

// public static void operaciones_init() {

// boolean queres = true;

// do {
// System.out.println(
// "Ingrese el código de la operación que desea realizar: Agregar organización
// (1), Agregar egreso (2), Agregar usuario (3), Agregar ingreso (4), Crear /
// Modificar sistema de categorias (5), Definir relacion entre criterios (6),
// Cerrar sesion(0)");
// int codOperacion = Scaneador.scanearInt();

// if (codOperacion == 1)
// agregarEntidad();
// else if (codOperacion == 2)
// agregarEgreso();
// else if (codOperacion == 3)
// InicioSesion.crearUsuario(dbManager);
// else if (codOperacion == 4)
// agregarIngreso();
// else if (codOperacion == 5)
// procesosCategorias();
// else if (codOperacion == 6)
// determinarCriterioPadre();
// else if (codOperacion == 0)
// break;
// else {
// System.out.println("Ingresaste mal el código de operación, intente de
// nuevo");
// continue;
// }

// System.out.println("¿Queres realizar otra operación? (true/false)");
// queres = Scaneador.scanearBoolean();

// } while (queres);
// }

// private static void procesosCategorias() {
// System.out.println("Crear un sistema (1), Modificar el existente (2)");
// int eleccion = Scaneador.scanearInt();

// dbManager.createEntityManager();
// dbManager.beginTransaction();

// if (eleccion == 1)
// crearSistemaCategorias();
// else if (eleccion == 2)
// modificarSistema();
// else
// System.out.println("Ingresaste mal el código de operación, intente de
// nuevo");

// dbManager.commit();
// dbManager.close();
// }

// private static void modificarSistema() {
// System.out.println("Ingrese el nombre del sistema");
// String nombreIngresado = Scaneador.scanearString();

// boolean bl = false;

// ManejadorDeCategorias sist = dbManager.find(ManejadorDeCategorias.class,
// nombreIngresado);

// if (sist == null) {
// do {
// System.out.println(
// "No existe ese sistema, quiere crearlo (true) o volver a intentar (false)?
// (true/false)");
// bl = Scaneador.scanearBoolean();

// if (bl)
// crearSistemaCategorias();
// } while (!bl);
// } else {

// do {
// System.out.println("Crear una categorizacion nueva (1), Crear categoria nueva
// (2)");
// int eleccion = Scaneador.scanearInt();

// if (eleccion == 1)
// crearCategorizaciones();
// if (eleccion == 2) {

// do {
// System.out.println(
// "Ingrese el nombre del criterio de categorizacion en donde quiere crear la
// categoria");
// String critIngresado = Scaneador.scanearString();

// CriterioDeCategorizacion crit = sist.encontrarCriterio(critIngresado);

// if (crit != null)
// crearCategorias(crit);
// else {
// System.out.println(
// "No existe dicho criterio, quiere crearlo (true) o volver a intentar (false)?
// (true/false)");
// bl = Scaneador.scanearBoolean();

// if (bl)
// sist.agregarCategorizaciones(crearCategorizaciones());
// }
// } while (!bl);
// } else {
// System.out.println("Ingresaste mal el código de operación, intente de
// nuevo");
// bl = false;
// }
// } while (!bl);
// }
// }

// private static void crearSistemaCategorias() {
// System.out.println("Ingrese el nombre del sistema");
// String nombreIngresado = Scaneador.scanearString();

// ManejadorDeCategorias sis = new ManejadorDeCategorias(nombreIngresado,
// crearCategorizaciones());
// dbManager.persist(sis);
// sesionActiva.setSistemaEnUso(sis);
// }

// private static void crearCategorias(CriterioDeCategorizacion crit) {
// boolean queres;

// do {
// System.out.println("Ingrese el nombre de la categoria");
// String nombreIngresado = Scaneador.scanearString();

// Categoria cat = new Categoria(nombreIngresado, crit);

// crit.addCategoria(cat);

// dbManager.persist(cat);

// System.out.println("¿Queres agregar otra categoria? (true/false)");
// queres = Scaneador.scanearBoolean();

// } while (queres);

// }

// private static List<CriterioDeCategorizacion> crearCategorizaciones() {
// List<CriterioDeCategorizacion> lista = new ArrayList<>();

// boolean queres;

// do {
// System.out.println("Ingrese el nombre de la categorizacion");
// String nomIngresado = Scaneador.scanearString();

// CriterioDeCategorizacion crit = new CriterioDeCategorizacion(nomIngresado);

// lista.add(crit);

// crearCategorias(crit);

// dbManager.persist(crit);

// System.out.println("Criterio creado con exito: " + crit.toString());

// System.out.println("¿Queres agregar otro criterio de categorizacion?
// (true/false)");
// queres = Scaneador.scanearBoolean();

// } while (queres);

// return lista;
// }

// private static void agregarIngreso() {

// dbManager.createEntityManager();
// dbManager.beginTransaction();
// System.out.println("Ingrese la descripcion del ingreso");
// String descripcionIngresada = Scaneador.scanearString();

// System.out.println("Ingrese el valor del ingreso");
// int valorIngresado = Scaneador.scanearInt();

// dbManager.persist(new Ingreso(descripcionIngresada, valorIngresado));
// dbManager.commit();
// dbManager.close();
// }

// private static void agregarEntidad() {

// boolean queres = true;

// do {
// System.out.println("Ingrese el tipo de entidad de su organización: Juridica
// (1), Base (2)");
// int codTipoEntidad = Scaneador.scanearInt();

// if (codTipoEntidad == 1)
// agregarJuridica();
// else if (codTipoEntidad == 2)
// agregarBase();
// else {
// System.out.println("Ingresaste mal el código de tipo de entidad, intente de
// nuevo");
// continue;
// }

// System.out.println("¿Queres agregar otra organización? (true/false)");
// queres = Scaneador.scanearBoolean();
// } while (queres);
// }

// private static void agregarJuridica() {

// List<Base> basesLista;
// Juridica juridica;

// System.out.println("-----CREACIÓN DE ORGANIZACIÓN JURÍDICA-----");

// System.out.println("Ingrese el nombre de su organización: ");
// String nombreFicticioIngresado = Scaneador.scanearString();

// System.out.println("Ingrese el cuit de su organización: ");
// int cuitIngresado = Scaneador.scanearInt();

// System.out.println("Ingrese la razón social de su organización:");
// String razonSocialIngresada = Scaneador.scanearString();

// TipoOrg tipo = crearTipoOrg();

// System.out.println("¿Su organización está inscripta en el IGJ?
// (true/false)");
// boolean igj = Scaneador.scanearBoolean();

// DireccionPostal direcc = ingresarDireccionPostal();

// dbManager.createEntityManager();
// dbManager.beginTransaction();
// if (igj) {
// System.out.println("Ingrese su código de inscripción: ");
// int codigoIngresado = Scaneador.scanearInt();

// juridica = new JuridicaIGJ(cuitIngresado, direcc, razonSocialIngresada,
// nombreFicticioIngresado, tipo,
// codigoIngresado);
// } else {

// juridica = new Juridica(cuitIngresado, direcc, razonSocialIngresada,
// nombreFicticioIngresado, tipo);
// }
// dbManager.persist(juridica);

// System.out.println("¿Su organización tiene a su cargo entidades bases?
// (true/false)");
// boolean bases = Scaneador.scanearBoolean();

// if (bases) {
// basesLista = agregarListaDeBase();
// juridica.setBases(basesLista);
// System.out.println("Sus organizaciones fueron agregadas con éxito");
// }

// dbManager.commit();
// dbManager.close();

// System.out.println("Su organización JURÍDICA fue creada con éxito: " +
// juridica.toString());
// }

// private static Base agregarBase() {

// System.out.println("-----CREACIÓN DE ENTIDAD BASE-----");

// System.out.println("Ingrese el nombre de su organización: ");
// String nombreFicticioIngresado = Scaneador.scanearString();

// System.out.println("Ingrese una descripcion de su organización: ");
// String descIngresada = Scaneador.scanearString();

// dbManager.createEntityManager();
// dbManager.beginTransaction();

// Base base = new Base(descIngresada, nombreFicticioIngresado, crearTipoOrg());

// dbManager.persist(base);
// dbManager.commit();
// dbManager.close();

// System.out.println("Su organización BASE fue creada con éxito: " +
// base.toString());

// return base;
// }

// private static List<Base> agregarListaDeBase() {

// List<Base> basesCreadas = new ArrayList<>();

// boolean masBases;

// System.out.println("-----CREACIÓN DE ENTIDADES BASE DE UNA ENTIDAD
// JURÍDICA-----");

// do {
// basesCreadas.add(agregarBase());

// System.out.println("¿Queres agregar otra base? (true/false)");
// masBases = Scaneador.scanearBoolean();
// } while (masBases);

// return basesCreadas;
// }

// private static TipoOrg crearTipoOrg() {

// while (true) {
// System.out.println("Ingrese la clasificación de su organización: PYME (1),
// OSC (2)");
// int tipoIngresado = Scaneador.scanearInt();

// if (tipoIngresado == 1)
// return crearEmpresa();
// else if (tipoIngresado == 2) {
// dbManager.createEntityManager();
// dbManager.beginTransaction();
// OSC osc = new OSC();
// dbManager.persist(osc);
// dbManager.commit();
// dbManager.close();
// return osc;
// }

// System.out.println("Ingresaste mal el código de clasificación, intente de
// nuevo");
// }
// }

// private static TipoOrg crearEmpresa() {

// boolean codigoValido;
// SectoresEnum sector = null;

// System.out.println("Ingrese la actividad de su PYME: ");
// String actividadIngresada = Scaneador.scanearString();

// do {
// System.out.println(
// "Ingrese el sector de su PYME: AGROPECUARIO (1), INDUSTRIA Y MINERIA (2),
// SERVICIOS (3), COMERCIO (4), CONSTRUCCION (5)");
// int sectorIngresado = Scaneador.scanearInt();

// codigoValido = true;

// if (sectorIngresado == 1)
// sector = AGROPECUARIO;
// else if (sectorIngresado == 2)
// sector = INDUSTRIAYMINERIA;
// else if (sectorIngresado == 3)
// sector = SERVICIOS;
// else if (sectorIngresado == 4)
// sector = COMERCIO;
// else if (sectorIngresado == 5)
// sector = CONSTRUCCION;
// else {
// System.out.println("Ingresaste mal el código de sector, intente de nuevo");
// codigoValido = false;
// }
// } while (!codigoValido);

// System.out.println("Ingrese el promedio de ventas anuales de su empresa");
// double promVentasIngresado = Scaneador.scanearDouble();

// System.out.println("Ingrese la cantidad de personal de su empresa");
// int cantPersonalIngresada = Scaneador.scanearInt();

// dbManager.createEntityManager();
// dbManager.beginTransaction();

// Micro emp = new Micro(actividadIngresada, sector, cantPersonalIngresada,
// promVentasIngresado);

// try {
// emp.recategorizar();
// } catch (Exception e) {
// System.out.println("Paso algo por aca ni idea");
// }

// dbManager.persist(emp);
// dbManager.commit();
// dbManager.close();

// return emp;
// }

// private static void agregarEgreso() {

// boolean queres;

// do {
// System.out.println("Ingrese la fecha del egreso (con el siguiente formato
// (dd/MM/yyyy))");
// LocalDate fecha = Scaneador.scanearFecha();

// // System.out.println("Ingresa medio de pago de la operación")
// // NO PEDIMOS MEDIO DE PAGO PARA DESPUÉS RELACIONARLA CON LA API DE
// MERCADOLIBRE

// System.out.println("Ingrese el/los item/s del egreso: ");
// dbManager.createEntityManager();
// dbManager.beginTransaction();
// List<Item> itemsIngresados = agregarItems();
// dbManager.commit();
// dbManager.close();

// Proveedor proveedorIngresado = agregarProveedor();

// System.out.println("¿Este egreso contiene un documento asociado?
// (true/false)");
// boolean tieneDocumento = Scaneador.scanearBoolean();

// DocumentoComercial documentoIngresado = null;
// if (tieneDocumento)
// documentoIngresado = agregarDocumento();

// System.out.println("¿Este egreso involucró presupuestos? (true/false)");
// boolean tuvoPresupuesto = Scaneador.scanearBoolean();

// if (tuvoPresupuesto)
// agregarEgresoConPresupuestos(fecha, itemsIngresados, proveedorIngresado,
// documentoIngresado);
// else {
// dbManager.createEntityManager();
// dbManager.beginTransaction();
// Egreso eg = new Egreso(dbManager.getFedesam(), fecha, null, itemsIngresados,
// proveedorIngresado);
// eg.setDocumento(documentoIngresado);
// dbManager.persist(eg);
// dbManager.commit();
// dbManager.close();
// System.out.println("Su egreso fue creado con éxito: " + eg.toString());
// }

// System.out.println("¿Queres agregar otro egreso? (true/false)");
// queres = Scaneador.scanearBoolean();
// } while (queres);
// }

// private static DocumentoComercial agregarDocumento() {

// System.out.println("Ingrese el numero de dicho documento: ");
// int numeroDoc = Scaneador.scanearInt();

// System.out.println("Ingrese el tipo de dicho documento: ");
// String tipoDoc = Scaneador.scanearString();

// dbManager.createEntityManager();
// dbManager.beginTransaction();
// DocumentoComercial doc = new DocumentoComercial(numeroDoc, tipoDoc);
// dbManager.persist(doc);
// dbManager.commit();
// dbManager.close();

// return doc;
// }

// private static void agregarEgresoConPresupuestos(LocalDate fecha, List<Item>
// itemsIngresados,
// Proveedor proveedorIngresado, DocumentoComercial documentoIngresado) {

// dbManager.createEntityManager();
// dbManager.beginTransaction();

// boolean codigoValido;
// CriterioSeleccion criterio = null;

// do {
// System.out.println(
// "Ingrese el criterio de selección de presupuuesto usado en este egreso: Menor
// presupuesto (1)");
// int criterioElegido = Scaneador.scanearInt();

// codigoValido = true;

// if (criterioElegido == 1) {
// criterio = MenorPresupuesto.getInstance();
// dbManager.persist(criterio);
// } else {
// System.out.println("Ingresaste mal el código de criterio de selección,
// intente de nuevo");
// codigoValido = false;
// }
// } while (!codigoValido);

// EgresoConPresupuestos egresoConPresupuestos = new
// EgresoConPresupuestos(dbManager.getFedesam(), fecha, null,
// itemsIngresados, proveedorIngresado, criterio);

// dbManager.persist(egresoConPresupuestos);

// egresoConPresupuestos.setDocumento(documentoIngresado);

// System.out.println("Ingrese el presupuesto elegido de este egreso:");
// egresoConPresupuestos.setPresupuestoElegido(agregarPresupuesto(egresoConPresupuestos));

// System.out.println("Ingrese todos los presupuestos evaluados de este
// egreso:");
// int cantidadPresupuestos = 3; // DEFINIDO ARBITRARIAMENTE
// for (int i = 0; i < cantidadPresupuestos; i++)
// egresoConPresupuestos.addPresupuesto(agregarPresupuesto(egresoConPresupuestos));

// System.out.println("¿Desea agregar revisores de la compra? (true/false)");
// boolean quiere = Scaneador.scanearBoolean();

// while (quiere) {
// Usuario user;
// do {
// do {
// System.out.println("Ingrese el nombre de usuario que quiere declarar como
// revisor de la compra");
// String usuario = Scaneador.scanearString();

// user = dbManager.find(Usuario.class, usuario);

// if (user == null)
// System.out.println("No existe ese usuario");

// } while (user == null);

// egresoConPresupuestos.addRevisor(user);

// System.out.println("¿Desea agregar otro revisor de la compra? (true/false)");
// quiere = Scaneador.scanearBoolean();

// } while (quiere);
// }
// dbManager.commit();
// dbManager.close();

// System.out.println("Su egreso con presupuestos fue creado con éxito: " +
// egresoConPresupuestos.toString());
// }

// private static List<Item> agregarItems() {

// List<Item> items = new ArrayList<>();

// boolean queres;

// do {
// System.out.println("---AGREGAR ITEM---");

// System.out.println("Ingrese la descripcion del producto adquirido:");
// String prod = Scaneador.scanearString();

// System.out.println("Ingrese el valor del producto adquirido:");
// double valorUnitario = Scaneador.scanearDouble();

// System.out.println("Ingrese la cantidad de dicho producto que adquirió:");
// int cantidad = Scaneador.scanearInt();

// Item item = new Item(prod, valorUnitario, cantidad);
// dbManager.persist(item);

// items.add(item);

// System.out.println("Desea categorizar al item?");
// boolean sino = Scaneador.scanearBoolean();

// boolean bl = true;
// do {
// if (sino) {

// System.out.println("Ingrese el sistema de categorizaciones que desea usar");
// String sistema_ingresado = Scaneador.scanearString();

// ManejadorDeCategorias manejadorDeCategorias =
// dbManager.find(ManejadorDeCategorias.class,
// sistema_ingresado);

// System.out.println("Ingrese nombre de la categoria");
// String nombreCategoria = Scaneador.scanearString();

// Categoria cat = null;
// if (manejadorDeCategorias == null) {
// System.out.println("No existe dicho sistema, desea crear uno? (true/false)");
// boolean val = Scaneador.scanearBoolean();

// if (val)
// crearSistemaCategorias();
// else
// break;
// } else {
// cat = manejadorDeCategorias.buscarCategoria(nombreCategoria);
// }

// if (cat != null)
// item.categorizar(cat);
// else
// System.out.println("No existe esa categoria");

// System.out.println("Desea agregar otra categoria?");
// bl = Scaneador.scanearBoolean();
// }
// } while (!bl);

// System.out.println("Queres agregar otro item? (true/false)");
// queres = Scaneador.scanearBoolean();
// } while (queres);
// return items;
// }

// private static Proveedor agregarProveedor() {

// System.out.println("---AGREGAR PROVEEDOR---");

// System.out.println("Ingrese la dirección postal del proveedor de este egreso:
// ");
// DireccionPostal direccIngresada = ingresarDireccionPostal();

// while (true) {
// System.out.println("¿Es una persona o una organización?: Persona (1), Entidad
// (2)");
// int codTipoProovedor = Scaneador.scanearInt();

// if (codTipoProovedor == 1)
// return agregarPersonaProveedora(direccIngresada);
// else if (codTipoProovedor == 2)
// return agregarEntidadProveedora(direccIngresada);

// System.out.println("Ingresaste mal el código de tipo de proveedor, intente de
// nuevo");
// }
// }

// private static Proveedor agregarPersonaProveedora(DireccionPostal
// direccIngresada) {

// System.out.println("Ingrese el nombre del proveedor: ");
// String nombre = Scaneador.scanearString();

// System.out.println("Ingrese el apellido del proveedor: ");
// String apellido = Scaneador.scanearString();

// System.out.println("Ingrese el DNI del proveedor: ");
// int DNI = Scaneador.scanearInt();

// dbManager.createEntityManager();
// dbManager.beginTransaction();

// PersonaProveedora perprov = new PersonaProveedora(nombre, apellido, DNI,
// direccIngresada);
// dbManager.persist(perprov);

// dbManager.commit();
// dbManager.close();

// return perprov;
// }

// private static Proveedor agregarEntidadProveedora(DireccionPostal
// direccIngresada) {

// System.out.println("Ingrese el CUIT del proveedor: ");
// int CUIT = Scaneador.scanearInt();

// System.out.println("Ingrese la razón social del proveedor: ");
// String razonSocial = Scaneador.scanearString();

// dbManager.createEntityManager();
// dbManager.beginTransaction();

// EntidadProveedora entprov = new EntidadProveedora(CUIT, razonSocial,
// direccIngresada);
// dbManager.persist(entprov);

// dbManager.commit();
// dbManager.close();

// return entprov;
// }

// private static Presupuesto agregarPresupuesto(EgresoConPresupuestos egreso) {

// Presupuesto presupuesto;
// boolean validacion;

// while (true) {
// System.out.println("---AGREGAR PRESUPUESTO---");

// System.out.println("Ingrese el/los item/s del presupuesto: ");
// List<Item> itemsIngresados = agregarItems();

// presupuesto = new Presupuesto(itemsIngresados, null, egreso);

// validacion = ValidadorPresupuestos.itemsEnPresupuesto(presupuesto);

// if (validacion) {
// dbManager.persist(presupuesto);
// return presupuesto;
// } else {
// System.out.println(
// "Tu presupuesto no puede ser agregado ya que los items no coinciden con los
// de este egreso, intente de nuevo");
// }
// }
// }

// private static void determinarCriterioPadre() {
// if (sesionActiva.getUsuario().getPermisos() == NivelPermisos.ADMINISTRADOR) {

// System.out.println("Ingrese el nombre del manejador de categorias");
// String sistema = Scaneador.scanearString();

// dbManager.createEntityManager();
// dbManager.beginTransaction();

// ManejadorDeCategorias manej = dbManager.find(ManejadorDeCategorias.class,
// sistema);

// System.out.println("Ingrese el nombre del criterio padre");
// String critPadre = Scaneador.scanearString();

// CriterioDeCategorizacion critFather = manej.encontrarCriterio(critPadre);

// System.out.println("Ingrese el nombre del criterio hijo");
// String critHijo = Scaneador.scanearString();

// CriterioDeCategorizacion critSon = manej.encontrarCriterio(critHijo);

// critSon.setCatPadre(critFather);
// critFather.addCriterioHijo(critSon);

// dbManager.commit();
// dbManager.close();

// } else
// System.out.println("No tenes permisos para hacer eso");
// }

// private static DireccionPostal ingresarDireccionPostal() {

// dbManager.createEntityManager();
// dbManager.beginTransaction();

// System.out.println("Ingrese los datos de la direccion postal");

// // PAIS
// List<String> countries_names = new ArrayList<>();
// List<String> countries_ids = APIManager.getCountries(countries_names);

// System.out.println("Ingrese pais:");

// for (int i = 0; i < countries_ids.size(); i++)
// System.out.println(i + 1 + ") " + countries_names.get(i));

// String country_id;
// while (true) {
// int i = Scaneador.scanearInt();

// if (i <= countries_ids.size()) {
// country_id = countries_ids.get(i - 1);
// break;
// } else
// System.out.println("Intenta de nuevo");
// }

// Country country = dbManager.find(Country.class, country_id);

// if (country == null) {
// country = APIManager.getCountry(country_id);
// dbManager.persist(country);
// }

// // PROVINCIA
// List<Country.State> states = country.getStates();

// System.out.println("Ingrese estado:");

// for (int i = 0; i < states.size(); i++)
// System.out.println(i + 1 + ") " + states.get(i).getName());

// String state_id;
// while (true) {
// int i = Scaneador.scanearInt();

// if (i <= states.size()) {
// state_id = states.get(i - 1).getId();
// break;
// } else
// System.out.println("Intenta de nuevo");
// }

// State state = dbManager.find(State.class, state_id);

// if (state == null) {
// state = APIManager.getState(state_id);
// dbManager.persist(state);

// for (State.City city : state.getCities()) {
// dbManager.persist(APIManager.getCity(city.getId()));
// }
// }

// // CIUDAD
// List<State.City> cities = state.getCities();

// System.out.println("Ingrese ciudad:");

// for (int i = 0; i < cities.size(); i++)
// System.out.println(i + 1 + ") " + cities.get(i).getName());

// String city_id;
// while (true) {
// int i = Scaneador.scanearInt();

// if (i <= cities.size()) {
// city_id = cities.get(i - 1).getId();
// break;
// } else
// System.out.println("Intenta de nuevo");
// }

// City city = dbManager.find(City.class, city_id);

// // RESTO DE LA INFO
// System.out.println("Ingrese calle del domicilio");
// String calle_ingresada = Scaneador.scanearString();

// System.out.println("Ingrese altura del domicilio");
// int altura_ingresada = Scaneador.scanearInt();

// System.out.println("Ingrese piso (sino indiquelo con un 0)");
// int piso_ingresado = Scaneador.scanearInt();

// DireccionPostal direcc = new DireccionPostal(calle_ingresada,
// altura_ingresada, piso_ingresado, city, state,
// country);

// dbManager.persist(direcc);
// dbManager.commit();
// dbManager.close();

// return direcc;
// }
// }
