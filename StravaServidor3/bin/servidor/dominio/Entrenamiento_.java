package servidor.dominio;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.*;

@Generated(value="org.datanucleus.jpa.query.JPACriteriaProcessor")
@StaticMetamodel(Entrenamiento.class)
public class Entrenamiento_
{
    public static volatile SingularAttribute<Entrenamiento, Long> duracion;
    public static volatile SingularAttribute<Entrenamiento, java.lang.String> titulo;
    public static volatile SingularAttribute<Entrenamiento, servidor.dominio.Actividad> actividad;
    public static volatile SingularAttribute<Entrenamiento, Double> distancia;
    public static volatile SingularAttribute<Entrenamiento, java.util.Date> fecha_ini;
    public static volatile SingularAttribute<Entrenamiento, servidor.dominio.Usuario> usuario;
}
