package pe.gob.juntos.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSede is a Querydsl query type for Sede
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSede extends EntityPathBase<Sede> {

    private static final long serialVersionUID = 1930686770L;

    public static final QSede sede = new QSede("sede");

    public final StringPath codigoSede = createString("codigoSede");

    public final StringPath descripcionSede = createString("descripcionSede");

    public final StringPath eliminado = createString("eliminado");

    public final DateTimePath<java.util.Date> fechaCreacion = createDateTime("fechaCreacion", java.util.Date.class);

    public final DateTimePath<java.util.Date> fechaModificacion = createDateTime("fechaModificacion", java.util.Date.class);

    public final StringPath idSede = createString("idSede");

    public final StringPath usuarioCreacion = createString("usuarioCreacion");

    public final StringPath usuarioModificacion = createString("usuarioModificacion");

    public final StringPath vigente = createString("vigente");

    public QSede(String variable) {
        super(Sede.class, forVariable(variable));
    }

    public QSede(Path<? extends Sede> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSede(PathMetadata metadata) {
        super(Sede.class, metadata);
    }

//KUJA : INI
    
    //private static final PathInits INITS = PathInits.DIRECT2;
    
    public QSede(PathMetadata metadata, PathInits inits) {
        this(Sede.class, metadata, inits);
    }
    
    public QSede(Class<? extends Sede> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        //this.sede = inits.isInitialized("sede") ? new QSede(forProperty("sede")) : null;
    }

    
}

