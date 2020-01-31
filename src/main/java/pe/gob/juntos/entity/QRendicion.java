package pe.gob.juntos.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRendicion is a Querydsl query type for Rendicion
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRendicion extends EntityPathBase<Rendicion> {

    private static final long serialVersionUID = -1761066074L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRendicion rendicion = new QRendicion("rendicion");

    public final QAdministrado administrado;

    public final StringPath comentario = createString("comentario");

    public final StringPath documentoReferencia = createString("documentoReferencia");

    public final StringPath eliminado = createString("eliminado");

    public final StringPath estado = createString("estado");

    public final DateTimePath<java.util.Date> fechaAprobado = createDateTime("fechaAprobado", java.util.Date.class);

    public final DateTimePath<java.util.Date> fechaCreacion = createDateTime("fechaCreacion", java.util.Date.class);

    public final DateTimePath<java.util.Date> fechaInicio = createDateTime("fechaInicio", java.util.Date.class);

    public final DateTimePath<java.util.Date> fechaModificacion = createDateTime("fechaModificacion", java.util.Date.class);

    public final DateTimePath<java.util.Date> fechaPagado = createDateTime("fechaPagado", java.util.Date.class);

    public final DateTimePath<java.util.Date> fechaRendido = createDateTime("fechaRendido", java.util.Date.class);

    public final NumberPath<Long> idRendicion = createNumber("idRendicion", Long.class);

    public final NumberPath<Double> importeTotal = createNumber("importeTotal", Double.class);
    
    public final NumberPath<Long> sedeUnidad = createNumber("sedeUnidad", Long.class);

    public final StringPath pagado = createString("pagado");

    public final QRendicionCajaChica rendicionCajachica;

    public final StringPath usuarioCreacion = createString("usuarioCreacion");

    public final StringPath usuarioModificacion = createString("usuarioModificacion");

    public final StringPath usuarioRegistraAprobado = createString("usuarioRegistraAprobado");

    public final StringPath usuarioRegistraPagado = createString("usuarioRegistraPagado");

    public final StringPath vigente = createString("vigente");

    public QRendicion(String variable) {
        this(Rendicion.class, forVariable(variable), INITS);
    }

    public QRendicion(Path<? extends Rendicion> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRendicion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRendicion(PathMetadata metadata, PathInits inits) {
        this(Rendicion.class, metadata, inits);
    }

    public QRendicion(Class<? extends Rendicion> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.administrado = inits.isInitialized("administrado") ? new QAdministrado(forProperty("administrado"), inits.get("administrado")) : null;
        this.rendicionCajachica = inits.isInitialized("rendicionCajachica") ? new QRendicionCajaChica(forProperty("rendicionCajachica")) : null;
    }

}

