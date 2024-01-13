package servidor.dominio;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.*;

@Generated(value="org.datanucleus.jpa.query.JPACriteriaProcessor")
@StaticMetamodel(Reto.class)
public class Reto_
{
    public static volatile SingularAttribute<Reto, Integer> objetivo;
    public static volatile SingularAttribute<Reto, java.lang.String> descripcion;
    public static volatile SingularAttribute<Reto, java.lang.String> nombre;
    public static volatile ListAttribute<Reto, servidor.dominio.Actividad> actividades;
    public static volatile SingularAttribute<Reto, servidor.dominio.Usuario> creador;
    public static volatile SingularAttribute<Reto, servidor.dominio.Usuario> usuario;
    public static volatile SingularAttribute<Reto, java.util.Date> fecha_ini;
    public static volatile SingularAttribute<Reto, java.util.Date> fecha_fin;
    public static volatile SingularAttribute<Reto, servidor.dominio.TipoObjectivo> tipoObjetivo;
}
