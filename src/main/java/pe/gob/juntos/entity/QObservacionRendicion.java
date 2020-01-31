package pe.gob.juntos.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QObservacionRendicion is a Querydsl query type for ObservacionRendicion
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QObservacionRendicion extends EntityPathBase<ObservacionRendicion> {

    private static final long serialVersionUID = 1492206247L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QObservacionRendicion observacionRendicion = new QObservacionRendicion("observacionRendicion");

    public final StringPath accion = createString("accion");

    public final StringPath comentario = createString("comentario");

    public final StringPath eliminado = createString("eliminado");

    public final DateTimePath<java.util.Date> fechaCreacion = createDateTime("fechaCreacion", java.util.Date.class);

    public final DateTimePath<java.util.Date> fechaModificacion = createDateTime("fechaModificacion", java.util.Date.class);

    public final QFuncionario funcionario;

    public final NumberPath<Long> idObservacionRendicion = createNumber("idObservacionRendicion", Long.class);

    public final QRendicion rendicion;

    public final QRendicionCajaChica rendicionCajachica;

    public final StringPath tipoObservacion = createString("tipoObservacion");

    public final StringPath usuarioCreacion = createString("usuarioCreacion");

    public final StringPath usuarioModificacion = createString("usuarioModificacion");

    public final StringPath vigente = createString("vigente");

    public QObservacionRendicion(String variable) {
        this(ObservacionRendicion.class, forVariable(variable), INITS);
    }

    public QObservacionRendicion(Path<? extends ObservacionRendicion> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QObservacionRendicion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QObservacionRendicion(PathMetadata metadata, PathInits inits) {
        this(ObservacionRendicion.class, metadata, inits);
    }

    public QObservacionRendicion(Class<? extends ObservacionRendicion> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.funcionario = inits.isInitialized("funcionario") ? new QFuncionario(forProperty("funcionario"), inits.get("funcionario")) : null;
        this.rendicion = inits.isInitialized("rendicion") ? new QRendicion(forProperty("rendicion"), inits.get("rendicion")) : null;
        this.rendicionCajachica = inits.isInitialized("rendicionCajachica") ? new QRendicionCajaChica(forProperty("rendicionCajachica")) : null;
    }

}

