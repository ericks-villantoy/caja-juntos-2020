package pe.gob.juntos.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRutaSede is a Querydsl query type for RutaSede
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRutaSede extends EntityPathBase<RutaSede> {

    private static final long serialVersionUID = -97321534L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRutaSede rutaSede = new QRutaSede("rutaSede");

    public final StringPath codigoRuta = createString("codigoRuta");

    public final StringPath descripcionUbigeoLlegada = createString("descripcionUbigeoLlegada");

    public final StringPath descripcionUbigeoPartida = createString("descripcionUbigeoPartida");

    public final StringPath eliminado = createString("eliminado");

    public final DateTimePath<java.util.Date> fechaCreacion = createDateTime("fechaCreacion", java.util.Date.class);

    public final DateTimePath<java.util.Date> fechaModificacion = createDateTime("fechaModificacion", java.util.Date.class);

    public final NumberPath<Long> idRutaSede = createNumber("idRutaSede", Long.class);

    public final QSede sede;

    public final NumberPath<Integer> tarifa = createNumber("tarifa", Integer.class);

    public final NumberPath<Integer> tiempo = createNumber("tiempo", Integer.class);

    public final StringPath ubigeoLlegada = createString("ubigeoLlegada");

    public final StringPath ubigeoPartida = createString("ubigeoPartida");

    public final StringPath usuarioCreacion = createString("usuarioCreacion");

    public final StringPath usuarioModificacion = createString("usuarioModificacion");

    public final StringPath vigente = createString("vigente");

    public QRutaSede(String variable) {
        this(RutaSede.class, forVariable(variable), INITS);
    }

    public QRutaSede(Path<? extends RutaSede> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRutaSede(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRutaSede(PathMetadata metadata, PathInits inits) {
        this(RutaSede.class, metadata, inits);
    }

    public QRutaSede(Class<? extends RutaSede> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.sede = inits.isInitialized("sede") ? new QSede(forProperty("sede")) : null;
    }

}

