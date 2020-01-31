package pe.gob.juntos.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEspecifica is a Querydsl query type for Especifica
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEspecifica extends EntityPathBase<Especifica> {

    private static final long serialVersionUID = -121495991L;

    public static final QEspecifica especifica = new QEspecifica("especifica");

    public final StringPath codigoCalse = createString("codigoCalse");

    public final StringPath descripcionEspecifica = createString("descripcionEspecifica");

    public final StringPath eliminado = createString("eliminado");

    public final DateTimePath<java.util.Date> fechaCreacion = createDateTime("fechaCreacion", java.util.Date.class);

    public final DateTimePath<java.util.Date> fechaModificacion = createDateTime("fechaModificacion", java.util.Date.class);

    public final NumberPath<Long> idEspecifica = createNumber("idEspecifica", Long.class);

    public final StringPath usuarioCreacion = createString("usuarioCreacion");

    public final StringPath usuarioModificacion = createString("usuarioModificacion");

    public final StringPath vigente = createString("vigente");

    public QEspecifica(String variable) {
        super(Especifica.class, forVariable(variable));
    }

    public QEspecifica(Path<? extends Especifica> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEspecifica(PathMetadata metadata) {
        super(Especifica.class, metadata);
    }

}

