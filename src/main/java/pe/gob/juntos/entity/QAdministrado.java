package pe.gob.juntos.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAdministrado is a Querydsl query type for Administrado
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAdministrado extends EntityPathBase<Administrado> {

    private static final long serialVersionUID = -96872396L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAdministrado administrado = new QAdministrado("administrado");

    public final StringPath apellidoMaterno = createString("apellidoMaterno");

    public final StringPath apellidoPaterno = createString("apellidoPaterno");

    public final StringPath eliminado = createString("eliminado");

    public final DateTimePath<java.util.Date> fechaCreacion = createDateTime("fechaCreacion", java.util.Date.class);

    public final DateTimePath<java.util.Date> fechaModificacion = createDateTime("fechaModificacion", java.util.Date.class);

    public final StringPath idAdministrado = createString("idAdministrado");

    public final StringPath nombre = createString("nombre");

    public final StringPath numeroDocumento = createString("numeroDocumento");

    public final QSede sede;

    public final StringPath tipoDocumento = createString("tipoDocumento");

    public final StringPath usuarioCreacion = createString("usuarioCreacion");

    public final StringPath usuarioModificacion = createString("usuarioModificacion");

    public final StringPath vigente = createString("vigente");

    public QAdministrado(String variable) {
        this(Administrado.class, forVariable(variable), INITS);
    }

    public QAdministrado(Path<? extends Administrado> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAdministrado(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAdministrado(PathMetadata metadata, PathInits inits) {
        this(Administrado.class, metadata, inits);
    }

    public QAdministrado(Class<? extends Administrado> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.sede = inits.isInitialized("sede") ? new QSede(forProperty("sede")) : null;
    }

}

