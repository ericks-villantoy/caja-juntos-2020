package pe.gob.juntos.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDetalleRendicion is a Querydsl query type for DetalleRendicion
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDetalleRendicion extends EntityPathBase<DetalleRendicion> {

    private static final long serialVersionUID = -549480499L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDetalleRendicion detalleRendicion = new QDetalleRendicion("detalleRendicion");

    public final NumberPath<Integer> cantidadFolios = createNumber("cantidadFolios", Integer.class);

    public final QClase clase;

    public final StringPath detalle = createString("detalle");

    public final StringPath estadoEnvio = createString("estadoEnvio");
    
    public final StringPath documentoReferencia = createString("documentoReferencia");

    public final StringPath eliminado = createString("eliminado");

    public final QEspecifica especifica;

    public final DateTimePath<java.util.Date> fecha = createDateTime("fecha", java.util.Date.class);

    public final DateTimePath<java.util.Date> fechaCreacion = createDateTime("fechaCreacion", java.util.Date.class);

    public final DateTimePath<java.util.Date> fechaModificacion = createDateTime("fechaModificacion", java.util.Date.class);

    public final NumberPath<Long> idDetalleRendicion = createNumber("idDetalleRendicion", Long.class);

    //public final NumberPath<Long> importe = createNumber("importe", Long.class);
    public final NumberPath<Double> importe = createNumber("importe", Double.class);

    public final StringPath numeroRecibo = createString("numeroRecibo");

    public final StringPath razonSocial = createString("razonSocial");

    public final QRendicion rendicion;

    public final StringPath rucEmpresa = createString("rucEmpresa");

    public final StringPath serieRecibo = createString("serieRecibo");

    public final StringPath usuarioCreacion = createString("usuarioCreacion");

    public final StringPath usuarioModificacion = createString("usuarioModificacion");

    public final StringPath vigente = createString("vigente");

    public QDetalleRendicion(String variable) {
        this(DetalleRendicion.class, forVariable(variable), INITS);
    }

    public QDetalleRendicion(Path<? extends DetalleRendicion> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDetalleRendicion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDetalleRendicion(PathMetadata metadata, PathInits inits) {
        this(DetalleRendicion.class, metadata, inits);
    }

    public QDetalleRendicion(Class<? extends DetalleRendicion> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.clase = inits.isInitialized("clase") ? new QClase(forProperty("clase")) : null;
        this.especifica = inits.isInitialized("especifica") ? new QEspecifica(forProperty("especifica")) : null;
        this.rendicion = inits.isInitialized("rendicion") ? new QRendicion(forProperty("rendicion"), inits.get("rendicion")) : null;
    }

}

