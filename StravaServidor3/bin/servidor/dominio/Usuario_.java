package servidor.dominio;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.*;

@Generated(value="org.datanucleus.jpa.query.JPACriteriaProcessor")
@StaticMetamodel(Usuario.class)
public class Usuario_
{
    public static volatile SingularAttribute<Usuario, java.lang.String> nombre;
    public static volatile SingularAttribute<Usuario, java.lang.String> email;
    public static volatile SingularAttribute<Usuario, java.util.Date> fecha_nac;
    public static volatile SingularAttribute<Usuario, Integer> peso_kilo;
    public static volatile SingularAttribute<Usuario, Integer> altura;
    public static volatile SingularAttribute<Usuario, Integer> frec_card_max;
    public static volatile SingularAttribute<Usuario, Integer> frec_card_rep;
    public static volatile SingularAttribute<Usuario, servidor.dominio.MetodoLogin> metodo;
    public static volatile ListAttribute<Usuario, servidor.dominio.Entrenamiento> listaEntrenamientos;
    public static volatile ListAttribute<Usuario, servidor.dominio.Reto> listaRetos;
    public static volatile ListAttribute<Usuario, servidor.dominio.Reto> listaRetosCreados;
}
