package pe.gob.juntos.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRutaRendicionDetalle is a Querydsl query type for RutaRendicionDetalle
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRutaRendicionDetalle extends EntityPathBase<RutaDetalleRendicion> {

    private static final long serialVersionUID = 426491777L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRutaRendicionDetalle rutaRendicionDetalle = new QRutaRendicionDetalle("rutaRendicionDetalle");

    public final QDetalleRendicion detalleRendicion;

    public final StringPath eliminado = createString("eliminado");

    public final DateTimePath<java.util.Date> fecha = createDateTime("fecha", java.util.Date.class);

    public final DateTimePath<java.util.Date> fechaCreacion = createDateTime("fechaCreacion", java.util.Date.class);

    public final DateTimePath<java.util.Date> fechaModificacion = createDateTime("fechaModificacion", java.util.Date.class);

    public final NumberPath<Long> idRutaRendicionDetalle = createNumber("idRutaRendicionDetalle", Long.class);

    public final NumberPath<Long> importe = createNumber("importe", Long.class);

    public final QRutaSede rutaSede;

    public final StringPath usuarioCreacion = createString("usuarioCreacion");

    public final StringPath usuarioModificacion = createString("usuarioModificacion");

    public final StringPath vigente = createString("vigente");

    public QRutaRendicionDetalle(String variable) {
        this(RutaDetalleRendicion.class, forVariable(variable), INITS);
    }

    public QRutaRendicionDetalle(Path<? extends RutaDetalleRendicion> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRutaRendicionDetalle(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRutaRendicionDetalle(PathMetadata metadata, PathInits inits) {
        this(RutaDetalleRendicion.class, metadata, inits);
    }

    public QRutaRendicionDetalle(Class<? extends RutaDetalleRendicion> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.detalleRendicion = inits.isInitialized("detalleRendicion") ? new QDetalleRendicion(forProperty("detalleRendicion"), inits.get("detalleRendicion")) : null;
        this.rutaSede = inits.isInitialized("rutaSede") ? new QRutaSede(forProperty("rutaSede"), inits.get("rutaSede")) : null;
    }

}

